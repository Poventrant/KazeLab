package kaze.findJob.baidu;

/**
 * Created by kaze on 2016/9/26.
 * 合并多个有序链表
 */
public class MergeSortedLists {
    static class Node {
        int val;
        Node next = null;
        Node(int val) {
            this.val = val;
        }
    }

    private static Node merge(Node[] heads) {
        if(heads == null) return null;
        Node head = null, curr = null;
        while (true) {
            int min = Integer.MAX_VALUE, index = -1;
            for (int i = 0; i < heads.length; i++) {
                if(heads[i] != null && heads[i].val < min) {
                    min = heads[i].val;
                    index = i;
                }
            }
            if(index == -1) break;
            if(head == null) {
                head = heads[index];
                curr = heads[index];
            } else {
                curr.next = heads[index];
                curr = curr.next;
            }
            heads[index] = heads[index].next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node[] nodes = new Node[4];
        for (int k = 0; k < 4; k++) {
            Node head = new Node(k);
            Node list = head;
            for(int i = 0; i < 10; ++ i){
                list.next = new Node((k+1)*(i+3));
                list = list.next;
            }
            nodes[k] = head;
        }
        Node newHead = merge(nodes);
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
        System.out.println();
    }
}
