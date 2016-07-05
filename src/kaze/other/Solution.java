package kaze.other;

/**
 * Created by kaze on 2016/7/4.
 */
public class Solution {
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        int max_col = grid[0].length;
        int max_row = grid.length;
        boolean[][] visited = new boolean[max_row][max_col];
        int count = 0;
        for (int i = 0; i < max_row; i++) {
            for (int j = 0; j < max_col; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j, max_row, max_col, visited);
                    count ++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j, int max_row, int max_col, boolean[][] visited) {
        if(i == max_row || j == max_col || i == -1 || j == -1 || grid[i][j] == '0') {
            return;
        }
        if(grid[i][j] == '1' && !visited[i][j]) {
            visited[i][j] = true;
            dfs(grid, i + 1, j, max_row, max_col, visited);
            dfs(grid, i - 1, j, max_row, max_col, visited);
            dfs(grid, i, j - 1, max_row, max_col, visited);
            dfs(grid, i, j + 1, max_row, max_col, visited);
        }
    }


    public static void main(String[] args) {
//        char[][] grid = new char[4][5];
//        String [] strs = new String[] { "11000",
//                                        "11000",
//                                        "00100",
//                                        "00011"};
//        for (int i = 0; i < strs.length; i++) {
//            for (int j = 0; j < strs[i].length(); j++) {
//                grid[i][j] = strs[i].charAt(j);
//            }
//        }
//        System.out.println(new Solution().numIslands(grid));
        System.out.println(System.getProperties());
    }
}
