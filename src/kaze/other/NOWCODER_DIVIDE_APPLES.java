package kaze.other;

/**
 http://www.nowcoder.com/practice/532d89889b974506a0805062fd1089fb?tpId=49&tqId=29307&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_DIVIDE_APPLES {

    public int getInitial(int n) {
        for (int i = n + 1; ; i++) {
            int bear = n;
            int k = i;
            while (bear > 0) {
                if (k % n == 1) {
                    k = k - k / bear - 1;
                    --bear;
                } else {
                    break;
                }
            }
            if (bear == 0) {
                return i;
            }
        }
    }

    public static void main(String[] args) {
    }
}
