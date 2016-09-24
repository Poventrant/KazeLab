package kaze.algorithm;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Created by kaze on 2016/9/24.
 * 单链表快排
 */
public class ListQuickSort {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int i, ListNode o) {
            val = i;
            next = o;
        }
    }

    private static void quickSort(ListNode list, ListNode head, ListNode end) {
        if(head != end) {
            ListNode mid = partition(list, head, end);
            if(mid == null) {
                return;
            }
            quickSort(list, head, mid);
            quickSort(list, mid.next, end);
        }
    }

    private static ListNode partition(ListNode list, ListNode head, ListNode end) {
        if(head == null || end == null) return null;
        int key = head.val;
        ListNode l = head, h = head.next;
        while (h != null) {
            if(h.val < key) {
                l = l.next;
                int temp = l.val;
                l.val = h.val;
                h.val = temp;
            }
            h = h.next;
        }
        head.val = l.val;
        l.val = key;
        return l;
    }

    private static void quickSort(ListNode list) {
        ListNode end = list;
        while (end.next != null) end = end.next;
        quickSort(list, list, end);
    }

    public static void main(String[] args) throws FileNotFoundException {
       Random ran = new Random();
        ListNode head = new ListNode(0, null);
        ListNode list = head;
        for(int i = 0; i < 100; ++ i){
            list.next = new ListNode(ran.nextInt(100), null);
            list = list.next;
        }
        list = head;
        System.out.print("\nbeforeSorting:");
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
        list = head;
        quickSort(list);
        System.out.print("\nafterSorting:");
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
    }
}
