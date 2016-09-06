package kaze.other;

/**
 * Created by kaze on 2016/9/6.
 */
public class NOWCODER_BALLS {

    public int calcDistance(int A, int B, int C, int D) {
        return 3*(A+B+C+D);
    }

    public static void main(String[] args) {
        System.out.println(new NOWCODER_BALLS().calcDistance(100, 90, 80, 70));
    }
}
