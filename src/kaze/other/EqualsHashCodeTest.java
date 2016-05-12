package kaze.other;

import java.util.Objects;

/**
 * Created by 枫叶 on 2016/5/8.
 */
public class EqualsHashCodeTest {

    public static void main(String[] args) {
        String test = new String("123");
        String test1 = new String("123");
        System.out.println(test == test1);
        System.out.println("test hashcode" + test.hashCode());
        System.out.println("test1 hashcode" + test1.hashCode());


        Student s1 = new Student("13349086", "pwq", 21);
        Student s2 = new Student("13349086", "kaze", 21);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode() == s2.hashCode());

    }

    static class Student {
        String id;
        String name;
        int age;

        public Student(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Student)) return false;
            if(this == obj) return true;
            Student temp = (Student) obj;
            if(this.id == temp.id || this.id.equals(temp.id)) {
                return true;
            }
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.id);
        }
    }
}
