package kaze.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//TODO 建议使用redis来实现队列的调度

public class SeatQueue {

	/**
	 * 队列最大人数
	 */
	public final int QUEUE_MAX_NUM;
	
	/**
	 * 轮询时最大等待时间
	 */
	private final static int MAX_WAIT_TIME = 60000;
	
	/**
	 * 阻塞队列，用于存放等待最新队伍消息的线程
	 */
	BlockingQueue<Thread> blockQueue = null;

	/*
	 * 代表这个队伍的上次修改队伍的时间，如果被HR工作间请求过了，
	 * 用于对hr工作间获取该队列的时候的长连接，判断请求时间保持连接，
	 * 那么立即获取队伍信息，返回连接。
	 */
	private volatile long lastUpdateTime = System.currentTimeMillis();

	/*
	 * 正在面试的人数
	 */
	private volatile int interviewer = 0;

	/**
	 * List: 按序存放MinUser </p>
	 */
	private ArrayList<MinUser> waitQueue;

	/*
	 * 队列的人数总和
	 */
	public final AtomicInteger totalWaitNum = new AtomicInteger(0);

	/*
	 * 读写锁, true:公平锁, 同步队列
	 */
	private final ReadWriteLock lock = new ReentrantReadWriteLock();


	SeatQueue() {
		QUEUE_MAX_NUM = 50; // 默认50
		waitQueue = new ArrayList<MinUser>(QUEUE_MAX_NUM);
		blockQueue = new ArrayBlockingQueue<Thread>(QUEUE_MAX_NUM);
		initTestData();
	}

	SeatQueue(int queue_max_num) {
		QUEUE_MAX_NUM = queue_max_num;
		waitQueue = new ArrayList<MinUser>(QUEUE_MAX_NUM);
		initTestData();
	}

	/**
	 * 唤醒
	 */
	private void signalBlockQueue() {
		lastUpdateTime = System.currentTimeMillis(); // 队伍被改变
		while(!blockQueue.isEmpty()) {
			try {
				Thread t = blockQueue.take();
//				LockSupport.unpark(t);
                if(!t.isInterrupted()) t.interrupt();
			} catch (InterruptedException e) {
			}
		}
	}
	
	void initTestData() {
		String[] realNames = { "林海荻", "林声远", "林少平" };
		Integer[] userIds = { 3, 4, 5 };
		Integer[] jobIds = { 3, 2, 1 };
		String[] jobNames = { "Android工程师", "ios开发工程师", "硬件开发工程师" };
		for (int i = 0; i < realNames.length; i++) {
			MinUser minUser = new MinUser();
			minUser.userName = realNames[i];
			minUser.userId = userIds[i];
			minUser.jobId = jobIds[i];
			minUser.jobName = jobNames[i];
			minUser.invite = i % 2 == 0 ? false : true;
			minUser.state = i % 3;
			waitQueue.add(minUser);
		}
		lastUpdateTime = System.currentTimeMillis();
	}

	/*
	 * 调整队伍 整个队伍进行调整
	 */
	public void adjust(ArrayList<MinUser> queue) throws HrAbsentException, InterruptedException {
		// 检查HR是否离开了
		if (this.leaveHandler.leaving) {
			throw new HrAbsentException();
		}
		lock.writeLock().lockInterruptibly();
		try {
			waitQueue = queue;
		} finally {
			signalBlockQueue();
			lock.writeLock().unlock();
		}
	}


	/**
	 * 入队
	 * 
	 * @param position
	 *            入队位置,当为-1时添加到队尾
	 * @throws InterruptedException
	 * @throws HrAbsentException
	 */
	public void enqueue(MinUser minUser, int position) throws InterruptedException,
			HrAbsentException {
		// 检查HR是否离开了
		if (this.leaveHandler.leaving) {
			throw new HrAbsentException();
		}
		lock.writeLock().lockInterruptibly();
		try {
			//已经存在
			MinUser tmp = null;
			if((tmp = containUser(minUser.getUserId())) != null) {
				tmp.setInvite(minUser.isInvite());
				tmp.setJobId(minUser.getJobId());
				tmp.setJobName(minUser.getJobName());
				tmp.setState(minUser.getState());
				return;
			}
			int size = waitQueue.size();
			if (size == QUEUE_MAX_NUM) {
				throw new Exception("队伍已经满了~");
			}
			if (position == -1) {
				waitQueue.add(minUser);
			} else if (position <= size && position >= 0) {
				waitQueue.add(position, minUser);
			} else {
				throw new IllegalArgumentException("插入队伍位置错误~");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			signalBlockQueue();
			lock.writeLock().unlock();
		}
	}

	public void enqueue(MinUser minUser) throws HrAbsentException {
		try {
			enqueue(minUser, -1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 出队
	 * 
	 *            用户Id,当为-1时将队尾出队
	 * @return
	 * @throws InterruptedException
	 * @throws HrAbsentException
	 */
	public void dequeue(int position) throws InterruptedException, HrAbsentException {
		// 检查HR是否离开了
		if (this.leaveHandler.leaving) {
			throw new HrAbsentException();
		}
		lock.writeLock().lockInterruptibly();
		try {
			int size = waitQueue.size();
			if (position != -1) {
				MinUser flag = waitQueue.remove(position);
				if (flag != null)
					return;
				else {
					throw new Exception("dequeue position wrong.");
				}
			} else {
				waitQueue.remove(size - 1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			signalBlockQueue();
			lock.writeLock().unlock();
		}
	}

	/**
	 * 通过长轮询堵塞式获取队伍，主要是给HR获取的
	 * @param result 结果
	 * @param lastReqTime 上次请求的时刻
	 * @return 本次获取队列的时间
	 * @throws HrAbsentException
	 * @throws InterruptedException
	 */
	public long getQueueByComet(List<MinUser> result, long lastReqTime) throws HrAbsentException, InterruptedException {
		// 检查HR是否离开了,前端HR点击离开之后，就停止轮询
		if (this.leaveHandler.leaving) {
			throw new HrAbsentException();
		}
		
		if (lastReqTime >= lastUpdateTime) { // 如果没有更新，那么堵塞,在持有读锁之前，避免写锁无法进入
			blockQueue.put(Thread.currentThread());
//			LockSupport.park(Thread.currentThread());
			 try {
                Thread.sleep(MAX_WAIT_TIME);
                return lastReqTime;
            } catch (InterruptedException e) {

            }
		}

		lock.readLock().lockInterruptibly();
		try {
			for(MinUser mu : waitQueue) {
				result.add(mu);
			}
			return lastUpdateTime; // 复制，防止对原对象操作，再返回response的时候发送修改冲突;
		} finally {
			lock.readLock().unlock();
		}
	}

	/*
	 * 获取队伍，主要还是给用户获取的，用户点击一下按钮才会发起请求，无需长轮询
	 */
	public List<MinUser> getQueue() throws InterruptedException, HrAbsentException {
		// 检查HR是否离开了
		if (this.leaveHandler.leaving) {
			throw new HrAbsentException();
		}

		lock.readLock().lockInterruptibly();
		try {
			return (ArrayList<MinUser>) waitQueue.clone(); // 复制，防止对原对象操作，再返回response的时候发送修改冲突;
		} finally {
			lock.readLock().unlock();
		}
	}

	/*
	 * 获取队伍长度
	 */
	public int getSize() throws InterruptedException {
		lock.readLock().lockInterruptibly();
		try {
			return waitQueue.size();
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * 数据库删除职位的时候，更新席位中的职位
	 * 
	 * @return 返回排队这个职位的用户ID
	 * @throws InterruptedException
	 */
	public List<Integer> deleteJob(int jobId) throws InterruptedException {
		lock.writeLock().lockInterruptibly();
		try {
			Iterator<MinUser> it = waitQueue.iterator();
			List<Integer> userIds = new ArrayList<Integer>();
			while (it.hasNext()) {
				MinUser mu = it.next();
				if (mu.getJobId() == jobId) {
					userIds.add(mu.getUserId());
					it.remove();
				}
			}
			return userIds;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			signalBlockQueue();
			lock.writeLock().unlock();
		}
		return null;
	}

	/**
	 * 用户是否在此席位中
	 * 
	 * @return 存在的话返回minUser，不存在null
	 * @throws InterruptedException
	 */
	public MinUser containUser(int userId) throws InterruptedException {
		lock.readLock().lockInterruptibly();
		try {
			for (MinUser mu : waitQueue) {
				if (mu.getUserId() == userId)
					return mu;
			}
		} finally {
			lock.readLock().unlock();
		}
		return null;
	}

	/**
	 * 获取队伍内某个用户的位置
	 * 
	 * @return <b>index</b>: -1时表示队伍不存在或者队伍内不存在该用户
	 * @throws InterruptedException
	 */
	public int getPosition(int userId) throws InterruptedException {


		lock.readLock().lockInterruptibly();
		try {
			for (int i = 0; i < waitQueue.size(); ++i) {
				if (waitQueue.get(i).getUserId() == userId) {
					return i;
				}
			}
			return -1;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * 获取队伍内某个用户的位置
	 * 
	 * @return <b>index</b>: -1时表示队伍不存在或者队伍内不存在该用户
	 * @throws InterruptedException
	 */
	public MinUser getUser(int userId) throws InterruptedException {

		lock.readLock().lockInterruptibly();
		try {
			for (MinUser mu : waitQueue) {
				if (mu.getUserId() == userId) {
					return mu;
				}
			}
			return null;
		} finally {
			lock.readLock().unlock();
		}
	}

	/*
	 * 改变用户的状态
	 */
	public void changeUserState(int userId, int state) throws InterruptedException,
			HrAbsentException {
		// 检查HR是否离开了
		if (this.leaveHandler.leaving) {
			throw new HrAbsentException();
		}
		lock.readLock().lockInterruptibly();
		try {
			for (MinUser mu : waitQueue) {
				if (userId == mu.getUserId()) {
					int oldState = mu.getState();
					mu.setState(state);
					if (state == MinUser.ENTERING && oldState != MinUser.ENTERING) { // 如果改成进入
						this.interviewer++;
					} else if (state != MinUser.ENTERING && oldState == MinUser.ENTERING) {
						this.interviewer--;
					}
				}
			}
		} finally {
			lock.readLock().unlock();
		}
	}

	/*
	 * 获取正在面试的人数
	 */
	public int getInterviewer() {
		return interviewer;
	}

	/**
	 * HR离开--------------------------------------------------------------------
	 */
	public final LeaveHandler leaveHandler = new LeaveHandler();

	public static class LeaveHandler {

		// 开始的时刻
		public Long startMoment;

		// 结束的时刻
		public Long endMoment;

		// 是否超时了
		public volatile boolean isOver = false;

		// 离开、在场状态
		public volatile boolean leaving = false;
	}

	/**
	 * 简化的USER的信息
	 * -------------------------------------------------------------------------
	 */
	public static class MinUser {
		private int userId;
		private String userName;
		private int jobId;
		private String jobName;
		private int state; // 状态 0表示正在排队，1表示表示正在面试， 2表示离开
		private boolean invite; // 约吗

		public static final int WAITING = 0, ENTERING = 1, OFFLIEN = 2;
		public static final String[] stateStr = new String[] { "等待中", "已进入", "游离" };

		public MinUser() {
		}

		public MinUser(int userId, String userName, int jobId, String jobName, int state,
				boolean invite) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.jobId = jobId;
			this.jobName = jobName;
			this.state = state;
			this.invite = invite;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public int getJobId() {
			return jobId;
		}

		public void setJobId(int jobId) {
			this.jobId = jobId;
		}

		public String getJobName() {
			return jobName;
		}

		public void setJobName(String jobName) {
			this.jobName = jobName;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public boolean isInvite() {
			return invite;
		}

		public void setInvite(boolean invite) {
			this.invite = invite;
		}

	}

	/*
	 * 测试用
	 */
	public static void main(String[] args) {
		SeatQueue sq = new SeatQueue();
		testOn(sq);
		testComet(sq);
	}

	private static void testComet(final SeatQueue sq) {
		ExecutorService executor = Executors.newFixedThreadPool(100);
		final Random ran = new Random();
		for (int i = 0; i < 100; i++) {
			Runnable worker = new Thread(new Runnable() {
				@Override
				public void run() {
					long reqTime = 0;
					while (true) {
						try {
							List<MinUser> mu = new ArrayList<MinUser>();
							reqTime = sq.getQueueByComet(mu, reqTime);
							System.out.println("Thread " + Thread.currentThread().getId()
									+ " get queue length: " + mu.size());
							Thread.sleep(ran.nextInt(50));
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (HrAbsentException e) {
							e.printStackTrace();
						}
					}
				}
			});
			executor.execute(worker);
		}
	}

	private static volatile Thread testThread = null;
	private static volatile boolean running = true;

	public static void testOn(final SeatQueue sq) {
		synchronized (sq) {
			testThread = new Thread(new Runnable() {
				@Override
				public void run() {
					Random rand = new Random();
					while (running) {
						try {
							if (rand.nextInt(50) <= 30) {
								int temp = rand.nextInt(sq.getSize() + 1);
								System.out.println("--------------------------------enqueue:"
										+ temp);
								sq.enqueue(new MinUser(temp, "Leonardo Wilelm Dicaprio" + temp,
										temp, "硬件工程师", rand.nextInt(5), rand.nextBoolean()), temp);
							} else {
								int temp = sq.getSize() == 0 ? 0 : rand.nextInt(sq.getSize());
								System.out.println("--------------------------------dequeue:"
										+ temp);
								sq.dequeue(temp);
							}
							Thread.sleep(rand.nextInt(100));
							System.out.println("queue length: " + sq.getSize());
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (HrAbsentException e) {
							e.printStackTrace();
						}
					}
				}
			});
			running = true;
		}
		testThread.start();
	}

	public static void testOff(final SeatQueue sq) {
		synchronized (sq) {
			if (testThread == null)
				return;
			running = false;
			testThread = null;
		}
	}

	public static class HrAbsentException extends Exception {
		public HrAbsentException() {
			super("HR已经离开，请等待......");
		}
	}

}
