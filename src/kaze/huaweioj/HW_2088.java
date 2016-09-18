package kaze.huaweioj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kaze on 2016/9/17.
 */
public class HW_2088 {
    static class Pointer {
        int x, y;

        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
    static List<Pointer> stack = new LinkedList<>();
    static List<Pointer> result = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        byte map[][] = new byte[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = in.nextByte();
            }
        }
        travel(map, 0, 0);
        for (int i = result.size() - 1; i >= 0 ; i--) {
            System.out.println(result.get(i));
        }
    }

    private static void travel(final byte[][] map, int x, int y) {
        if(x == map.length - 1 && y == map[0].length-1) {
            stack.add(0, new Pointer(x, y));
            if(result.size() == 0 || stack.size() < result.size()) {
                result.clear();
                for (Pointer pointer : stack) {
                    result.add(pointer);
                }
            }
            stack.remove(0);
            return;
        }
        if(x >= 0 && x < map.length &&
                y < map[x].length && y >= 0) {
            if(map[x][y] == 0) {
                stack.add(0, new Pointer(x, y));
                map[x][y] = -1;
                travel(map, x+1, y);
                travel(map, x, y+1);
                travel(map, x-1, y);
                travel(map, x, y-1);
                map[x][y] = 0;
                stack.remove(0);
            }
        }
    }
}
