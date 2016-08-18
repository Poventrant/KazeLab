package kaze.other;

import java.util.Scanner;

/*
Connected to the target VM, address: '127.0.0.1:58609', transport: 'socket'
3 3
...
...
...
0 1
4
1 0
0 1
-1 0
0 -1
 */

public class Netease2 {
    static int steps = 0;
    static boolean success = false;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = 0, col = 0;
        while (in.hasNextInt()) {//注意while处理多个case
            row = in.nextInt();
            col = in.nextInt();
        }
        char mmap[][] = new char[row][col];
        for (int i = 0; i < row; i++) {
            String tmp = in.next();
            for (int j = 0; j < col; j++) {
                mmap[i][j] = tmp.charAt(j);
            }
        }
        int start_r = 0, start_c = 0;
        start_r = in.nextInt();
        start_c = in.nextInt();
        int count_choice = 0;
        count_choice = in.nextInt();
        int choices[][] = new int[count_choice][2];
        for (int i = 0; i < count_choice; i++) {
            for (int j = 0; j < 2; j++) {
                choices[i][j] = in.nextInt();
            }
        }
        tryGetOut(start_r, start_c, mmap, choices, row, col);
        if(success) {
            System.out.println(steps);
        } else {
            System.out.println(-1);
        }
    }

    public static void tryGetOut(int sr, int sc, char[][] mmap, int[][] choices, int rowlen, int collen) {
        mmap[sr][sc] = '#';
        for (int i = 0; i < choices.length; i++) {
            boolean failed = false;
            int rowPos = sr, colPos = sc;
            int wall = 0;
            int singleStep = choices[i][0] < 0 ? -1 : 1;
            for (int j = 0; j < Math.abs(choices[i][0]); j++) {
                rowPos += singleStep;
                if(rowPos < 0 || rowPos >= rowlen) {
                    failed = true;
                    break;
                }
                if(mmap[rowPos][sc] != '.') {
                    wall ++;
                    break;
                }
            }
            if(failed) continue;
            singleStep = choices[i][1] < 0 ? -1 : 1;
            for (int j = 0; j < Math.abs(choices[i][1]); j++) {
                colPos += singleStep;
                if(colPos < 0 || colPos >= collen) {
                    failed = true;
                    break;
                }
                if(mmap[sr][colPos] != '.') {
                    wall ++;
                    break;
                }
            }
            if(failed) continue;
            if(wall > 0) {
                rowPos = sr;
                colPos = sc;
                wall = 0;
                singleStep = choices[i][1] < 0 ? -1 : 1;
                for (int j = 0; j < Math.abs(choices[i][1]); j++) {
                    colPos += singleStep;
                    if(colPos < 0 || colPos >= collen) {
                        failed = true;
                        break;
                    }
                    if(mmap[sr][colPos] != '.') {
                        wall ++;
                        break;
                    }
                }
                if(failed) continue;
                singleStep = choices[i][0] < 0 ? -1 : 1;
                for (int j = 0; j < Math.abs(choices[i][0]); j++) {
                    rowPos += singleStep;
                    if(rowPos < 0 || rowPos >= rowlen) {
                        failed = true;
                        break;
                    }
                    if(mmap[rowPos][sc] != '.') {
                        wall ++;
                        break;
                    }
                }
            }
            if(wall == 0 && rowPos == rowlen -1 && colPos == collen - 1) {
                steps ++;
                success = true;
                return;
            }
            if(failed) continue;
            tryGetOut(rowPos, colPos, mmap, choices, rowlen, collen);
        }
        mmap[sr][sc] = '.';
    }
}
