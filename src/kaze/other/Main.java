package kaze.other;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Comparator<Dr> com = new Comparator<Dr>() {
            @Override
            public int compare(Dr o1, Dr o2) {
                if (o1.date == o2.date) return 0;
                return o1.date > o2.date ? 1 : -1;
            }
        };
        loop:
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();  //day
            int m = scanner.nextInt();  //records
            if (m <= 1) {
                System.out.println("IMPOSSIBLE");
                continue;
            }
            List<Dr> drs = new ArrayList<>();
            int _max = -1;
            for (int i = 0; i < m; i++) {
                Dr dr = new Dr();
                dr.date = scanner.nextInt();
                dr.reco = scanner.nextInt();
//                if(dr.reco <= 0) {
//                    System.out.println("IMPOSSIBLE");
//                    continue loop;
//                }
                _max = _max < dr.reco ? dr.reco : _max;
                drs.add(dr);
            }
            Collections.sort(drs, com);
            for (int i = 1; i < m; i++) {
                Dr last = drs.get(i - 1), curr = drs.get(i);
                int d_sub = curr.date - last.date;
                int r_u = last.reco + d_sub,
                        r_d = last.reco - d_sub;
                r_d = r_d < 0 ? 0 : r_d;
                if (curr.reco > r_u || curr.reco < r_d) {
                    System.out.println("IMPOSSIBLE");
                    continue loop;
                } else {
                    //计算最大值
                    for (int len = 0; len <= d_sub; len++) {
                        for (int x = len; x >= 0; x--) {
                            int y = len - x;
                            int temp = last.reco + x - y;
                            if (temp >= 0 && temp == curr.reco) {
                                _max = _max < last.reco + x ?
                                        last.reco + x : _max;
                            }
                        }
                    }
                }
            }
            System.out.println(_max);
        }
    }

    private static class Dr {
        int date;
        int reco;
    }
}
