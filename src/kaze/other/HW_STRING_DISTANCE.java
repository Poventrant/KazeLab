package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/f549ee08ddd84b8485a4fa9aefaf4a38?tpId=37&tqId=21302&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 解析：http://www.cnblogs.com/biyeymyhjob/archive/2012/09/28/2707343.html
 */
public class HW_STRING_DISTANCE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str1 = scanner.next();
            String str2 = scanner.next();
            //第0行，列用来计算
            int map[][] = new int[str1.length()+1][str2.length()+1];
            for (int i = 0; i <= str2.length(); i++) {
                map[0][i] = i;
            }
            for (int i = 0; i <= str1.length(); i++) {
                map[i][0] = i;
            }
            for (int i = 1; i <= str1.length(); i++) {
                for (int j = 1; j <= str2.length(); j++) {
                    map[i][j] = Math.min(map[i - 1][j] + 1,  //删除str1的字符i
                                Math.min(map[i][j - 1] + 1,      //删除str2的字符j
                                map[i - 1][j - 1] + (str1.charAt(i-1) == str2.charAt(j-1) ? 0 : 1) )); //修改 str1的i字符和str2的字符j不等就在i-1,j-1上+1
                }
            }
            System.out.println("1/" + (1+map[str1.length()][str2.length()]));
        }
    }
}