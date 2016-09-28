package kaze.other;

import java.util.ArrayList;

/**
 * Created by kaze on 2016/9/28.
 */
public class NOWCODER_CONTINUOUS_SUM {

    /**
     * 根据数学公式计算:(a1+an)*n/2=s  n=an-a1+1
     * (an+a1)*(an-a1+1)=2*s=k*i(k>i) k和i必须一偶一奇
     * an=(k+i-1)/2  a1=(k-i+1)/2
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = (int) Math.sqrt(sum << 1); i >= 2; i--) {
            if ((sum << 1) % i == 0) {
                int k = (sum << 1) / i;
                if ((k & 1) == 1 && (i & 1) == 0 ||    //k和i必须一偶一奇
                        (k & 1) == 0 && (i & 1) == 1) {
                    int an = (k + i - 1) >> 1;
                    int a1 = (k - i + 1) >> 1;
                    ArrayList<Integer> tmp = new ArrayList<>();
                    for (int t = a1; t <= an; t++) {
                        tmp.add(t);
                    }
                    list.add(tmp);
                }
            }
        }
        return list;
    }
}
