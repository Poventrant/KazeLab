package kaze.patterns;

public class FooUse {
    public static void main(String[] args){
        Foo f = Foo.INSTANCE;
        f.print();
        f.testThread(10);
        Foo t = Foo.INSTANCE;
        System.out.println(f == t);
    }
}