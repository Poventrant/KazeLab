package kaze.other;

/**
 http://www.nowcoder.com/practice/376ede61d9654bc09dd7d9fa9a4b0bcd?tpId=49&tqId=29366&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_NEIBOR_MAX_SUB {
    public int findMaxDivision(int[] A, int n) {
        int max = -1, pre = -1;
        boolean mask[] = new boolean[65536];
        for (int i = 0; i < n; i++) {
            mask[A[i]] = true;
        }

        for (int i = 0; i < 65536; i++) {
            if(mask[i]) {
                if(pre != -1)
                    max = i - pre > max ? i - pre : max;
                pre = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new NOWCODER_NEIBOR_MAX_SUB().findMaxDivision(new int[] {
                9,3,1,10
        }, 4));
    }
}
