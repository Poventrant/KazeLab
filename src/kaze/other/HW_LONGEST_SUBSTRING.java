package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/181a1a71c7574266ad07f9739f791506?tpId=37&tqId=21288&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 */
public class HW_LONGEST_SUBSTRING {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            if(line1.length() > line2.length()) {
                String temp = line1;
                line1 = line2;
                line2 = temp;
            }
            for (int i = line1.length()-1; i >= 0; --i) {
                for (int s = 0; s+i < line1.length(); s++) {
                    String temp = line1.substring(s, s+i+1);
                    if(line2.contains(temp)) {
                        System.out.println(temp);
                        i = -1;
                        break;
                    }
                }
            }
        }
    }
}
