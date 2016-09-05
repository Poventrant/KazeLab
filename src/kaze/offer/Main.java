package kaze.offer;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int times = 0;
            int sum = 0;
            for (int i = 2; i < n; i++) {
                sum += getSolve(n, i);
                ++ times;
            }
            System.out.println(sum + "/" + times);
        }
    }

    private static int getSolve(int n, int jz) {
        int sum = 0;
        while(n != 0) {
            sum += n % jz;
            n /= jz;
        }
        return sum;
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Comparator<Dr> com = new Comparator<Dr>() {
//            @Override
//            public int compare(Dr o1, Dr o2) {
//                if (o1.date == o2.date) return 0;
//                return o1.date > o2.date ? 1 : -1;
//            }
//        };
//        loop:
//        while (scanner.hasNextInt()) {
//            int n = scanner.nextInt();  //day
//            int m = scanner.nextInt();  //records
//            if (m <= 1) {
//                System.out.println("IMPOSSIBLE");
//                continue;
//            }
//            List<Dr> drs = new ArrayList<>();
//            int _max = -1;
//            for (int i = 0; i < m; i++) {
//                Dr dr = new Dr();
//                dr.date = scanner.nextInt();
//                dr.reco = scanner.nextInt();
//                _max = _max < dr.reco ? dr.reco : _max;
//                drs.add(dr);
//            }
//            Collections.sort(drs, com);
//            for (int i = 1; i < m; i++) {
//                Dr last = drs.get(i - 1), curr = drs.get(i);
//                int d_sub = curr.date - last.date;
//                int r_u = last.reco + d_sub,
//                        r_d = last.reco - d_sub;
//                r_d = r_d < 0 ? 0 : r_d;
//                if (curr.reco > r_u || curr.reco < r_d) {
//                    System.out.println("IMPOSSIBLE");
//                    continue loop;
//                } else {
//                    //计算最大值
//                    int max_temp = last.reco + d_sub/2;
//                    _max = _max < max_temp ? max_temp : _max;
//                }
//            }
//            System.out.println(_max);
//        }
//    }
//
//    private static class Dr {
//        int date;
//        int reco;
//    }


}