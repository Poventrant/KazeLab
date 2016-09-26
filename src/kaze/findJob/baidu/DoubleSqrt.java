package kaze.findJob.baidu;

/**
 * Created by kaze on 2016/9/25.
 * 二分逼近实现double的sqrt开平方根
 */
public class DoubleSqrt {

    private static double sqrt(double db) {
        double l = 0.0, h = db;
        final double eps = 1 >> 31;
        while (Math.abs(h - l) > eps) {
            double mid = (h + l) / 2.0;
            double tmp = mid * mid - db;
            if (tmp > eps) {
                h = mid - eps;
            } else if(tmp < -eps) {
                l = mid + eps;
            } else {
                return mid;
            }
        }
        return (h + l) / 2.0;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(9997368478.32399057));
        System.out.println(Math.sqrt(9997368478.32399057));
    }
}
