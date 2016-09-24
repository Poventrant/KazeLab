package kaze.findJob.HW;

import java.util.Scanner;

public class Main {
    enum Opt {delete, noop, add}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] lines = new String[n * 2];
        int[][] map = new int[2001][2001];
        Opt[][] alter = new Opt[2001][2001];
        for (int i = 0; i < n * 2; i++) {
            lines[i] = in.next();
        }
        for (int k = 0; k < 2*n; k+=2) {
            int len1 = lines[k].length() + 1, len2 = lines[k + 1].length() + 1;
            map[0][0] = 0;
            for (int i = 1; i < len1; i++) {
                map[i][0] = 2;
                alter[i][0] = Opt.delete;
            }
            for (int i = 1; i < len2; i++) {
                map[0][i] = 2 + i;
                alter[0][i] = Opt.add;
            }
            for (int i = 1; i < len1; i++) {
                for (int j = 1; j < len2; j++) {
                    int dcost = alter[i - 1][j] == Opt.delete ? map[i - 1][j] : map[i - 1][j] + 2;
                    int acost = alter[i][j - 1] == Opt.add ? map[i][j - 1] + 1 : map[i][j - 1] + 3;

                    alter[i][j] = dcost >= acost ? Opt.add : Opt.delete;
                    map[i][j] = Math.min(acost, dcost);
                    int cost = 0;
                    if (lines[k].charAt(i - 1) != lines[k + 1].charAt(j - 1)) {
                        cost = 5;
                    }
                    alter[i][j] = map[i][j] > map[i - 1][j - 1] + cost ? Opt.noop : alter[i][j];
                    map[i][j] = Math.min(map[i][j], map[i - 1][j - 1] + cost);
                }
            }
            System.out.println(map[len1-1][len2-1]);
        }

    }
}