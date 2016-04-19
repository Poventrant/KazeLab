package kaze.other;

public class Test {
    public static int k = 0;
    public static Test t1 = new Test("t1");
    public static Test t2 = new Test("t2");
    public static int i = print("i");
    public static int n = 99;
    private int a = 0;
    {
        print("构造块");
    }

    public int j = print("j");

    static {
        print("静态块");
    }

    public Test(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
        Son t = new Son("init");
    }

    static class Son extends Test {

        public static int si = print("si static son");

        public int sj = print("sj son");

        static {
            System.out.println("son static constructor block");
        }

        {
            System.out.println("son constructor block");
        }


        public Son(String str) {
            super(str);
            System.out.println("son constructor");
        }
    }
}