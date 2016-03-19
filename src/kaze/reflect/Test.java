package kaze.reflect;
public class Test {
    // 这样做必须在类中有一个空构造方法
    public static void main(String[] args) {
        Class<?> c = null;
        try {
            c = Class.forName("kaze.reflect.Person");
            Person p = (Person) c.newInstance();
            p.setName("xy");
            p.setAge(20);
            System.out.println(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
