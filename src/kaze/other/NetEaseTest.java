package kaze.other;

import java.util.HashSet;

/**
 * Created by kaze on 2016/9/12.
 */
public class NetEaseTest {

    public static void main(String[] args) {
        HashSet<Test> set = new HashSet<>();
        set.add(new Test(2));
        set.add(new Test(3));
        set.add(new Test(4));
        System.out.println(set.size());
    }



    static class Test {

        private final int val;

        public Test(int i) {
            val = i;
        }

        public boolean equals(Test obj) {
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }

        @Override
        public int hashCode() {
            return 32;
        }
    }
}
