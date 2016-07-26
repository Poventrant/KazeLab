package kaze.other;

/**
 * Created by kaze on 2016/7/26.
 */
public class ComparableTest implements Comparable<ComparableTest> {

    int value;

    public ComparableTest(int i) {
        value = i;
    }

    @Override
    public int compareTo(ComparableTest o) {
        return value > o.value ? 1 : 0;
    }

    public static void main(String[] args) {
        int res =  new ComparableTest(2).compareTo(new ComparableTest(5));
        System.out.println(res);
    }
}
