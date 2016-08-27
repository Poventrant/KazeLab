package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/ebe941260f8c4210aa8c17e99cbc663b?tpId=37&tqId=21292&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 */
public class HW_MATRIX_MULTI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int r1 = scanner.nextInt();
            int r2, c1 = r2 = scanner.nextInt();
            int c2 = scanner.nextInt();
            int mat1[][] = new int[r1][c1],
                mat2[][] = new int[r2][c2];
            try {
                for (int r = 0; r < r1; r++) {
                    for (int c = 0; c < c1; c++) {
                        mat1[r][c] = scanner.nextInt();
                    }
                }
                for (int r = 0; r < r2; r++) {
                    for (int c = 0; c < c2; c++) {
                        mat2[r][c] = scanner.nextInt();
                    }
                }
                for (int r = 0; r < r1; r++) {
                    for (int c = 0; c < c2-1; c++) {
                        System.out.print(resolve(mat1, mat2, r, c) + " ");
                    }
                    System.out.println(resolve(mat1, mat2, r, c2-1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int resolve(int[][] mat1, int[][] mat2, int r, int c) {
        int len = mat1[0].length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += mat1[r][i] * mat2[i][c];
        }
        return res;
    }
}
