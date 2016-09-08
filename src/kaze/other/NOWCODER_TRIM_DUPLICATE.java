package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/784efd40ed8e465a84821c8f3970b7b5?tpId=49&tqId=29297&rp=1&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_TRIM_DUPLICATE {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            int mask[] = new int[256];
            for (int i = 0; i < line.length(); i++) {
                char temp = line.charAt(i);
                if(mask[temp] == 0) {
                    System.out.print(temp);
                    ++mask[temp];
                }
            }
            System.out.println();
        }
    }
}
