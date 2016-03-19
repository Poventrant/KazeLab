package kaze.thread;

import java.util.concurrent.atomic.AtomicInteger;


public class YeildTest {
	static class ThreadA extends Thread {
	    @Override
	    public void run() {
	      for (int i = 0; i < 10; i++) {
	        System.out.println("ThreadA" + i);
	      }
	    }
	  }

	  static class ThreadB extends Thread {
	    ThreadA a;

	    public ThreadB(ThreadA a) {
	      this.a = a;
	    }

	    @Override
	    public void run() {
	      System.out.println("ThreadB start");
	      try {
	        a.join();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	      System.out.println("ThreadB end");
	    }
	  }

	  public static void main(String[] args) {
	    ThreadA a = new ThreadA();
	    ThreadB b = new ThreadB(a);
	    b.start();
	    a.start();
	  }	
	/*static AtomicInteger count = new AtomicInteger(0);
	public static void main(String args []) {
		Thread ts[] = new Thread[10];
		for(int i = 0; i < 10; i++) {
			ts[i] = new Thread() {
				public void run() {
					count.incrementAndGet();
				}
			};
			ts[i].start();
		}
		for(int i = 0; i < 10; i++) {
			try {
				ts[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(count.get());
		System.out.println(Thread.currentThread().getName() + "is dead!");
	}*/
}
