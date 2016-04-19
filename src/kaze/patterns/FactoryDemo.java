package kaze.patterns;

interface Animal { // 定义一个动物的接口
    public void say(); // 说话方法
}

class Cat implements Animal { // 定义子类Cat
	@Override
	public void say() { // 覆写say()方法
		System.out.println("我是猫咪，喵呜！");
	}
}

class Dog implements Animal { // 定义子类Dog

	@Override
	public void say() { // 覆写say()方法
		System.out.println("我是小狗，汪汪！");
	}
}

class Factory { // 定义工厂类
	public static Animal getInstance(String className) {
		Animal a = null; // 定义接口对象
		if ("Cat".equals(className)) { // 判断是哪个子类的标记
			a = new Cat(); // 通过Cat子类实例化接口
		}
		if ("Dog".equals(className)) { // 判断是哪个子类的标记
			a = new Dog(); // 通过Dog子类实例化接口
		}
		return a;
	}
}

public class FactoryDemo {

	public static void main(String[] args) {
		Animal a = null; // 定义接口对象
		a = Factory.getInstance(args[0]); // 通过工厂获取实例
		if (a != null) { // 判断对象是否为空
			a.say(); // 调用方法
		}
	}
}
