package kaze.other;

import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/6a219d196df44d3abd82fbadb1a62c3f?tpId=49&tqId=29331&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_SCANNER {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n, m, k;
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
            int filed[][] = new int[n][m];
            for (int i = 0; i < k; i++) {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                ++filed[x][y];
            }
            int row = n, col = m;
            int _max_ = -1, recoX = 0, recoY = 0;
            for (int x = 0; x < row; x++) {
                for (int y = 0; y < col; y++) {
                    int count = 0;
                    for (int i = x; i < row && i < x + 3; i++) {
                        for (int j = y; j < col && j < y + 3; j++) {
                            if (filed[i][j] > 0) {
                                ++count;
                            }
                        }
                    }
                    if (count > _max_) {
                        recoX = x;
                        recoY = y;
                        _max_ = count;
                    }
                }
            }

            for (int i = recoX; i < row && i < recoX + 3; i++) {
                for (int j = recoY; j < col && j < recoY + 3; j++) {
                    if (filed[i][j] > 0) {
                        --filed[i][j];
                    }
                }
            }

            int _max = -1;
            for (int x = 0; x < row; x++) {
                for (int y = 0; y < col; y++) {
                    int count = 0;
                    for (int i = x; i < row && i < x + 3; i++) {
                        for (int j = y; j < col && j < y + 3; j++) {
                            if (filed[i][j] > 0) {
                                ++count;
                            }
                        }
                    }
                    if (count > _max) {
                        _max = count;
                    }
                }
            }

            System.out.println(_max_ + _max);
        }
    }
}
/*


测试用例:
17 15 46
6 10
13 13
13 15
4 15
13 11
3 8
14 1
6 2
11 11
7 4
5 15
16 14
16 3
1 2
1 3
4 15
2 5
16 2
13 3
1 9
17 6
16 13
15 3
13 12
4 4
10 1
11 13
9 7
8 7
10 13
2 11
17 11
6 4
14 9
7 9
8 13
13 14
13 11
10 14
2 4
4 13
14 8
9 13
11 13
3 11
17 13

对应输出应该为:

9


测试用例:
4 19 100
4 2
2 5
2 8
2 8
2 1
3 1
4 9
2 3
4 10
2 9
1 4
1 15
1 5
1 11
1 11
3 6
3 15
1 18
1 11
2 17
4 13
2 5
1 1
3 3
3 4
3 16
1 15
2 14
1 5
3 7
1 6
2 16
1 3
1 11
1 9
4 11
4 15
1 19
1 4
4 17
1 10
1 6
2 8
3 8
2 6
1 6
3 13
3 3
2 5
3 10
1 3
1 5
1 2
4 10
4 17
1 16
3 4
1 18
3 8
2 9
1 9
2 3
4 17
4 13
2 3
3 4
1 14
4 10
3 6
2 19
4 4
4 12
4 3
3 2
1 14
4 16
1 10
4 7
3 15
2 7
2 2
1 10
2 3
3 18
3 14
1 5
3 16
4 17
4 9
2 4
2 16
1 12
2 15
1 19
4 8
1 15
4 10
4 6
3 9
4 18

对应输出应该为:

18



19 20 33
11 4
7 17
3 18
14 15
9 4
12 8
17 17
14 8
12 15
12 16
13 16
12 19
9 5
12 18
15 8
8 8
6 8
9 10
6 13
11 14
10 7
1 6
14 5
6 3
7 16
9 3
12 8
4 12
12 9
16 18
15 6
12 2
15 12

-- 8
 */