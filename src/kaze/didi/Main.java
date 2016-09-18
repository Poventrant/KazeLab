package kaze.didi;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean isOver = false;

    public static void main(String[] args) {
        int n, m, p;
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            n = in.nextInt();
            m = in.nextInt();
            p = in.nextInt();
            byte map[][] = new byte[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = in.nextByte();
                }
            }
            stack = new LinkedList<>();
            isOver = false;
            travel(map, 0, 0, p);
            if (!isOver) {
                System.out.println("Can not escape!");
            }
        }
    }

    private static void travel(final byte[][] map, int x, int y, int p) {
        if (!isOver && p >= 0) {
            if (x == 0 && y == map[0].length - 1) {
                stack.add(0, new Pointer(x, y));
                for (int i = stack.size() - 1; i > 0; i--) {
                    System.out.print(stack.get(i) + ",");
                }
                System.out.println(stack.get(0));
                isOver = true;
                return;
            }
            if (x >= 0 && x < map.length &&
                    y < map[x].length && y >= 0) {
                if (map[x][y] == 1) {
                    stack.add(0, new Pointer(x, y));
                    map[x][y] = -1;
                    travel(map, x, y + 1, p-1);
                    travel(map, x - 1, y, p - 3);
                    travel(map, x + 1, y, p);
                    travel(map, x, y - 1, p-1);
                    map[x][y] = 1;
                    stack.remove(0);
                }
            }
        }
    }

    static class Pointer {
        int x, y;

        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }

    static List<Pointer> stack;

}
/*
4 4 10
1 0 0 1
1 1 0 1
0 1 1 1
0 0 1 1
 */