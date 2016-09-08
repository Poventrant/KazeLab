package kaze.other;

/**
 * Created by kaze on 2016/9/7.
 */
public class NOWCODER_FLIP {

    public int[][] flipChess(int[][] A, int[][] f) {
        int r = A.length;
        if (r == 0) return A;
        int c = A[0].length;
        for (int i = 0; i < f.length; i++) {
            int x = f[i][0] - 1, y = f[i][1] - 1;
            if (y - 1 >= 0) {
                A[x][y - 1] ^= 1;
            }
            if (y + 1 < r) {
                A[x][y + 1] ^= 1;
            }
            if (x - 1 >= 0) {
                A[x - 1][y] ^= 1;
            }
            if (x + 1 < c) {
                A[x + 1][y] ^= 1;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int res[][] = new NOWCODER_FLIP()
                .flipChess(
                        new int[][]{
                                {0, 0, 1, 1},
                                {1, 0, 1, 0},
                                {0, 1, 1, 0},
                                {0, 0, 1, 0}
                        }, new int[][]{
                                {2, 2},
                                {3, 3},
                                {4, 4}
                        });
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
