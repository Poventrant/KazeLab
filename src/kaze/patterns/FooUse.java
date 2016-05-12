package kaze.patterns;

public class FooUse {
    public static void main(String[] args){
        Foo f = Foo.INSTANCE;
        f.print();
        f.testThread(10);
    }
}