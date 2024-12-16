/**
 * DepthFirstSearch
 */
public class DFS {

    public static void main(String[] args) {

    }

    public static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    public static void dfs(int[][] grid, boolean[][] visited, int i, int j) {
        for (int k = 0; k < dir.length; k++) {
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length)
                continue;
            if (!visited[x][y] && grid[x][y] == 1) {
                visited[x][y] = true;
                dfs(grid, visited, x, y);
            }
        }
    }
}