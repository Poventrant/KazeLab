package kaze.other;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
http://www.nowcoder.com/practice/34a597ee15eb4fa2b956f4c595f03218?tpId=37&tqId=21262&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
255 254 252 248 240 224 192 128 0
 */
public class HW_MASK {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> correctMask = new HashSet<Integer>(16) {{
           add(255);add(254);add(252);add(248);
            add(240);add(224);add(192);add(128);add(0);
        }};
        String mask, ip1, ip2;
        while (scanner.hasNext()) {
            mask = scanner.next();
            ip1 = scanner.next();
            ip2 = scanner.next();
            String masks[] = mask.split("\\."),
                    ip1s[] = ip1.split("\\."),
                    ip2s[] = ip2.split("\\.");
            int res = 0;
            for (int i = 0; i < masks.length; i++) {
                int imask;
                if(i>=masks.length) {
                    imask = 0;
                } else {
                    imask = Integer.valueOf(masks[i]);
                }
                int iip1 = Integer.valueOf(ip1s[i]),
                        iip2 = Integer.valueOf(ip2s[i]);
                if(!correctMask.contains(imask) || (i+1 < masks.length
                        && imask != 255 && masks[i+1].equals("255"))) {
                    res = 1;
                    break;
                } else if(iip1 < 0 || iip1 > 255) {
                    res = 1;
                    break;
                } else {
                    if((imask & iip1) == (imask & iip2)) {
                        if(imask != 255 && res == 0) {
                            res = 0;
                        }
                    } else {
                        res = 2;
                    }
                }
            }
            System.out.println(res);
        }
    }
}
