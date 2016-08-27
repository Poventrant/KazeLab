package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/649b210ef44446e3b1cd1be6fa4cab5e?tpId=37&tqId=21258&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 */
public class HW_SNACK_MATRIX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int rows = scanner.nextInt();
            int matrix[][] = new int[rows][rows];
            int counter = 1;
            for (int r = 0; r < rows; r++) {
                int sr = r, sc = 0;
                for (int t = 0; t < r + 1; t++) {
                    matrix[sr--][sc++] = counter ++;
                }
            }
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < rows-r-1; c++) {
                    System.out.print(matrix[r][c] + " ");
                }
                System.out.println(matrix[r][rows-r-1]);
            }
        }
    }
}
