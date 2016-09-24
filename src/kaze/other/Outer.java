package kaze.other;

class Outer {

    public static void main(String[] args) {
//        Outer out = new Outer();
//        Object obj = out.method();
        new B();
    }

    Object method() {
        final int locvar = 1;
        class Inner {
            void displayLocvar() {
                System.out.println("locvar = " + locvar);
            }
        }
        Object in = new Inner();
        return in;
    }


    static class A {
        A() {
            this.setValue();
        }

        void setValue() {
            System.out.println("A set value");
        }
    }

    static class B extends A {

        int value = 0;

        B() {
            value = 1;
            setValue();
        }

        void setValue() {
            System.out.println("B set value" + value);
        }
    }
}