package kaze.other;

public class TestDemo {
    public static void main(String[] args) {
        new Sun();
    }
}
class Base {
    //定义一个名为i的实例变量
    private int i = 2;

    public Base() {
        this.display();
    }

    public void display() {
        System.out.println(i);
    }
}
//继承Base的Sun类
class Sun extends Base {
    //定义一个名为i的实例变量
    private int i = 22;

    public Sun() {
        i = 222;
    }

    public void display() {
        System.out.println(i);
    }
}