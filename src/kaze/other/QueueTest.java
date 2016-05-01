package kaze.other;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kaze on 16-4-24.
 */
public class QueueTest extends AbstractCollection<Integer>{
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.poll());
        }

    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
