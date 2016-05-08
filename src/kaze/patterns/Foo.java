package kaze.patterns;

public enum Foo {
    INSTANCE;

    public void print() {
        System.out.println("hello,world");
    }

    public void testThread(int len) {
        for (int i = 0; i < len; i++) {
            System.out.println(i);
        }
    }
}
