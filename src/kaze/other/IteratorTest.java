package kaze.other;

import java.util.Iterator;

/**
 * Created by kaze on 16-4-24.
 */
public class IteratorTest implements Iterable<Integer>{

    int [] ints = new int[] {2,5,9,7,3,4,6,8,95,46,79,12,96,12};

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < ints.length;
            }

            @Override
            public Integer next() {
                return ints[index++];
            }

            @Override
            public void remove() {

            }
        };
    }

    public static void main(String[] args) {
        for (int i : new IteratorTest()) {
            System.out.println(i);
        }
    }
}
