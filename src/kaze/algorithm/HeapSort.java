package kaze.algorithm;

import java.util.Random;

public class HeapSort {
    /*
	 * 大顶堆排序，用于升序
	 */
    public static void adjustMaxHeap(int sort[], int index, int n) {
        int i = index, lc, rc, maxc;
        while (true) {
            lc = (i << 1) + 1;                                        //数组下标从0开始，所以+1
            rc = lc + 1;
            maxc = i;
            if (lc < n && sort[maxc] < sort[lc]) maxc = lc;
            if (rc < n && sort[maxc] < sort[rc]) maxc = rc;   //取得左右孩子中大的那个的下标
            if (maxc != i) {                            //建立大顶堆
                int temp = sort[i];
                sort[i] = sort[maxc];
                sort[maxc] = temp;
                i = maxc;                                        //以该孩子节点作为下一轮调整的父节点
            } else break;                                        //父节点比孩子节点都大，所以不用调整
        }

    }

    /*
	 * 小顶堆排序, 用于降序
	 */
    public static void adjustMinHeap(int sort[], int index, int n) {
        int i = index;
        int lc, minc;
        while (true) {
            lc = i * 2 + 1;                                        //数组下标从0开始，所以+1
            if (lc < n) minc = lc;
            else break;
            if (lc + 1 < n && sort[minc] > sort[lc + 1])
                minc++;                                        //取得左右孩子中小的那个的下标
            if (sort[minc] < sort[i]) {                            //建立小堆
                int temp = sort[i];
                sort[i] = sort[minc];
                sort[minc] = temp;
                i = minc;                                        //以该孩子节点作为下一轮调整的父节点
                continue;
            } else break;                                        //父节点比孩子节点都小，所以不用调整
        }

    }

    public static void heapSort(int[] sort, int n) {
        //初始建堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjustMaxHeap(sort, i, n);
        }
        while (n > 0) {
            int temp = sort[n - 1];
            sort[n - 1] = sort[0];
            sort[0] = temp;
            adjustMaxHeap(sort, 0, --n);
        }
    }

    public static void main(String[] args) {
        int sort[] = new int[100];
        //Scanner inpit = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("before sorting: ");
        for (int i = 0; i < sort.length; ++i) {
            sort[i] = rand.nextInt(1000);
            System.out.print(sort[i] + " ");
        }
        heapSort(sort, sort.length);
        System.out.print("\nafter sorting: ");
        for (int e : sort) {
            System.out.print(e + " ");
        }
    }
}
