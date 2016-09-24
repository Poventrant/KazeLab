package kaze.algorithm;

import java.util.Stack;

/**
 * Created by kaze on 2016/9/19.
 */
public class TwoStackImplQueue<E> {

    Stack<E> stack1 = new Stack<E>();
    Stack<E> stack2 = new Stack<E>();

    public static void main(String[] args) {
        TwoStackImplQueue<Integer> tsiq = new TwoStackImplQueue<>();
        for (int i = 0; i < 10; i++) {
            tsiq.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(tsiq.pop());
        }
    }

    public int size() {
        return stack1.empty() ? stack2.size() : stack1.size();
    }

    public boolean isEmpty() {
        return stack1.empty() && stack2.empty();
    }

    public void push(E e) {
        if(stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        stack1.push(e);
    }

    public E pop() {
        if(isEmpty()) return null;
        if(stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

}
