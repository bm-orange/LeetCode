/**
 * 695. Max Area of Island
 * Created by man on 2018/11/3.
 */
public class Solution695 {
    /*
    描述:
    Given a non-empty 2D array grid of 0's and 1's, an island is a
    group of 1's (representing land) connected 4-directionally
    (horizontal or vertical.) You may assume all four edges of the
    grid are surrounded by water.

    Find the maximum area of an island in the given 2D array.
    (If there is no island, the maximum area is 0.)

    测试用例:
    Example1
    [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,1,1,0,1,0,0,0,0,0,0,0,0],
     [0,1,0,0,1,1,0,0,1,0,1,0,0],
     [0,1,0,0,1,1,0,0,1,1,1,0,0],
     [0,0,0,0,0,0,0,0,0,0,1,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     Given the above grid, return 6. Note the answer is not 11,
     because the island must be connected 4-directionally.

     Example2
     [[0,0,0,0,0,0,0,0]]
    Given the above grid, return 0.
    Note: The length of each dimension in the given grid does not exceed 50.

     */

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                maxArea = Math.max(maxArea, dfs(i, j, grid));
            }
        }

        return maxArea;
    }

    public int dfs(int r, int c, int[][] grid) {
        if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0
                || grid[r][c] == 0) return 0;
        grid[r][c] = 0;
        return 1 + dfs(r - 1, c, grid) + dfs(r + 1, c, grid) +
                dfs(r, c - 1, grid) + dfs(r, c + 1, grid);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(new Solution695().maxAreaOfIsland(grid));
    }
}
