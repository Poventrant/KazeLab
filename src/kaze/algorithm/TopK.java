package kaze.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by kaze on 2016/9/13.
 */
public class TopK {

    /**
     * 调整堆
     * @param heap 堆
     * @param idx 开始调整的位置
     * @param len 调整的长度
     */
    static void minHeapAdjust(int heap[], int idx, final int len) {
        int minIdx = idx;
        while (true) {
            int lc = idx * 2 + 1, rc = lc + 1;
            if(lc < len && heap[lc] < heap[minIdx]) {
                minIdx = lc;
            }
            if(rc < len && heap[rc] < heap[minIdx]) {
                minIdx = rc;
            }
            if(minIdx == idx) break;
            else {

                int tmp = heap[minIdx];
                heap[minIdx] = heap[idx];
                heap[idx] = tmp;

                idx = minIdx;
            }
        }

    }

    public static void main(String[] args) {
        int len = 500, K = len / 10;
        int sort[] = new int[len];
        Random rand = new Random();
        System.out.print("before sorting: ");
        for (int i = 0; i < sort.length; ++i) {
            sort[i] = rand.nextInt(len * 10);
            System.out.print(sort[i] + " ");
        }
        System.out.println();
        long startTime = System.currentTimeMillis();
        //建堆
        int heap[] = new int[K];
        for (int i = 0; i < K; i++) {
            heap[i] = sort[i];
        }
        //建堆调整，K / 2 - 1为最后一个非叶子节点
        for (int i = K / 2 - 1; i >= 0; i--) {
            minHeapAdjust(heap, i, K);
        }
        //最大的K个数
        for (int i = K; i < sort.length; i++) {
            if(heap[0] < sort[i]) {
                heap[0] = sort[i];
                minHeapAdjust(heap, 0, K);
            }
        }
        //对找到的最大的K个数排序
        for (int i = K-1; i >= 0; i--) {
            int tmp = heap[0];
            heap[0] = heap[i];
            heap[i] = tmp;
            minHeapAdjust(heap, 0, i);
        }
        System.out.println("cost time " + (System.currentTimeMillis() - startTime));
        System.out.print("top K is ");
        for (int i = 0; i < K; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();

        Arrays.sort(sort);
        System.out.print("after sorting: ");
        for (int i = 0; i < sort.length; ++i) {
            System.out.print(sort[i] + " ");
        }
        System.out.println();

    }

}
