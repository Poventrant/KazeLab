package kaze.algorithm;

public class KthNumbers {

    private int[] findKthNumbers(int[] A, int n, int k) {
        if(n <= 0 || k <= 0) return null;

        build(A, n);
        maxHeapSort(A, n, k);

        int[] result = new int[k];
        for(int i = 0; i < k; ++i) {
            result[i] = A[i];
        }

        return result;
    }

    private static void adjust(int[] A, int index, int len) {
        while(true) {
            int maxIndex = index * 2 + 1;
            if(maxIndex >= len) break;
            if(maxIndex + 1 < len && A[maxIndex+1] > A[maxIndex]) maxIndex ++;
            if(A[maxIndex] > A[index]) {
                int temp = A[maxIndex];
                A[maxIndex] = A[index];
                A[index] = temp;

                index = maxIndex;
            } else break;
        }
    }

    private static void build(int[] A, int len) {
        int index = len / 2 - 1;
        for(int i = index; i >= 0; -- i) {
            adjust(A, i, len);
        }
    }

    private static void maxHeapSort(int[] A, int len, int k) {
        for(int i = k; i < len; ++ i) {
            if(A[0] > A[i]) {
                A[0] = A[i];
                adjust(A, 0, k);
            }
        }
    }

    public static void main(String[] args) {
        int result [] = new KthNumbers().findKthNumbers(new int[] {3188,4522,2526,4085,1621,723,1073}, 7, 2);
        for (int aResult : result) {
            System.out.println(aResult);
        }
    }
}