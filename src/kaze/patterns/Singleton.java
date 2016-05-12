package kaze.patterns;

public class Singleton {
    private Singleton() {
    }

    public static Singleton getInstance() {
        //这个时候才会加载class到方法区中
        return Nested.instance;
    }

    //在第一次被引用时被加载
    static class Nested {
        private static Singleton instance = new Singleton();
        static  {
            System.out.println("????????????????????????????????");
        }
    }

    public static void main(String args[]) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
    }
}