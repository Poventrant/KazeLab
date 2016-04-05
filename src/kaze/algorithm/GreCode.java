package kaze.algorithm;

/**
 * Created by 枫叶 on 2016/4/2.
 */
public class GreCode {
    public static String[] getGray(int n) {
        if (n < 1) {
            System.out.println("格雷码长度必须大于等于1");
            return null;
        }
        int count = 1 << n;
        String[] strArr = new String[count];
        if (n == 1) {
            strArr[0] = "0";
            strArr[1] = "1";
            return strArr;
        } else {

            String[] strArr1 = getGray(n - 1);
            for (int i = 0; i < strArr1.length; i += 1) {
                strArr[i] = "0" + strArr1[i];

            }
            for (int i = 0; i < strArr1.length; i += 1) {
                strArr[strArr.length - i - 1] = "1" + strArr1[i];

            }
            return strArr;

        }
    }

    public static void main(String[] args) {
        String [] strs = getGray(5);
        System.out.println(strs.length);
        for(String s : strs) {
            System.out.println(s);
        }
    }
}
