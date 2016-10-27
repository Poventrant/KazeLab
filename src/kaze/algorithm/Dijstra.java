package kaze.algorithm;

import java.util.Arrays;

/**
 * Created by kaze on 2016/7/26.
 */
public class Dijstra {

    private int dijkstra(int map[][], int s, int e) {
        if (map == null || map.length == 0) return -1;
        int len = map.length;
        boolean[] vis = new boolean[len];
        int[] dis = new int[len];
        Arrays.fill(dis, Integer.MAX_VALUE);
        System.arraycopy(map[s], 0, dis, 0, len);
        vis[s] = true;
        for (int[] ignored : map) {
            int min = Integer.MAX_VALUE, index = -1;
            for (int k = 0; k < len; k++) {
                if (!vis[k] && dis[k] < min) {
                    min = dis[k];
                    index = k;
                }
            }
            if (index == -1) return -1;
            vis[index] = true;
            for (int k = 0; k < len; k++) {
                if (!vis[k] && dis[k] > map[index][k] + dis[index]) {
                    dis[k] = map[index][k] + dis[index];
                }
            }
            if (index == e) break;
        }
        return dis[e];
    }

    private int prim(int map[][], int s) {
        if (map == null || map.length == 0) return -1;
        int len = map.length;
        boolean[] vis = new boolean[len];
        int[] dis = new int[len];
        Arrays.fill(dis, Integer.MAX_VALUE);
        System.arraycopy(map[s], 0, dis, 0, len);
        vis[s] = true;
        int result = 0;
        for (int i = 0; i < len; i++) {
            int min = Integer.MAX_VALUE, index = -1;
            for (int k = 0; k < len; k++) {
                if (!vis[k] && dis[k] < min) {
                    min = dis[k];
                    index = k;
                }
            }
            if (index == -1) return -1;
            vis[index] = true;
            result += min;
            for (int k = 0; k < len; k++) {
                if (!vis[k] && dis[k] > map[index][k]) {
                    dis[k] = map[index][k];
                }
            }
        }
        return result;
    }

}
