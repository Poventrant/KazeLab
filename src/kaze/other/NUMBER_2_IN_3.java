package kaze.other;

/**
 * Created by kaze on 2016/9/1.
 */
public class NUMBER_2_IN_3 {

    static int get3IndexNumber(int n, int index) {
        int result = 0;
        for (int i = 0; i <= index; i++) {
            result = n % 3;
            n /= 3;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] arrs = {1,1,1,2,3,3,3,4,4,2,4,9,9,9};
        int ans = 0, pos = 0;
        for (int k = 0; k < Integer.BYTES; k++) {
            int sum = 0;
            for (int i = 0; i < arrs.length; i++) {
                sum += get3IndexNumber(arrs[i], pos);
            }
            sum = (2 * sum) % 3;
            ans += sum * Math.pow(3, pos);
            ++pos;
        }
        System.out.println(ans);
    }
}
