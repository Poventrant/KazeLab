package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/9370d298b8894f48b523931d40a9a4aa?tpId=49&tqId=29233&rp=1&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_WIND_PIG {

    public int calculateMax(int[] prices) {
        int len = prices.length;
        if (len < 2)
            return 0;
        int pre[] = new int[len], post[] = new int[len],
                _max = prices[len - 1], _min = prices[0];

        for (int i = 1; i < len; i++) {
            int temp = prices[i];
            pre[i] = Math.max(temp - _min, pre[i - 1]);
            if (_min > temp) {
                _min = temp;
            }
        }

        for (int i = len - 2; i >= 0; --i) {
            int temp = prices[i];
            post[i] = Math.max(_max - temp, post[i + 1]);
            if (_max < temp) {
                _max = temp;
            }
        }

        _max = pre[0] + post[0];
        for (int i = 1; i < len; i++) {
            if (_max < pre[i] + post[i]) {
                _max = pre[i] + post[i];
            }
        }
        return _max;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String ns[] = in.nextLine().split(",");
            int [] prices = new int[ns.length];
            for (int j = 0; j < ns.length; j++) {
                prices[j] = Integer.valueOf(ns[j]);
            }
            new NOWCODER_WIND_PIG().calculateMax(prices);
        }
    }
}
