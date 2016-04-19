package kaze.patterns;

interface Window {// 定义Window窗口接口，表示窗口操作

    public void open();// 窗口打开

	public void close();// 窗口关闭

	public void iconified();// 窗口最小化

	public void deiconified();// 窗口恢复

	public void activated();// 窗口活动
}

// 定义抽象类实现接口，在此类中覆写方法，但是所有的方法体为空
abstract class WindowAdapter implements Window {
	public void open() {
	};// 窗口打开

	public void close() {
	};// 窗口关闭

	public void iconified() {
	};// 窗口最小化

	public void deiconified() {
	};// 窗口恢复

	public void activated() {
	};// 窗口活动
}

// 子类继承WindowAdapter抽象类，选择性实现需要的方法
class WindowImpl extends WindowAdapter {
	public void open() {
		System.out.println("窗口打开");// 实现open()方法
	}

	public void close() {
		System.out.println("窗口关闭");// 实现close()方法
	}
}

public class AdapterDemo {
	public static void main(String args[]) {
		Window win = new WindowImpl(); // 实现接口对象
		// 调用方法
		win.open();
		win.close();
	}
}