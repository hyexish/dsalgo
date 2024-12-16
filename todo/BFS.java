import java.util.ArrayDeque;
import java.util.Deque;

/**
 * BreadthFirstSearch
 */
public class BFS {
    public static void main(String[] args) {
        
    }

    public static int[][] dir = {{0,1},{0, -1},{-1, 0},{1, 0}};
    public static void bfs(int[][] grid, boolean[][] visited, int i, int j) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {i ,j });
        visited[i][j] = true;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            for (int k = 0; k < dir.length; k++) {
                int x = cur[0] + dir[k][0];
                int y = cur[1] + dir[k][1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) continue;
                if (!visited[x][y] && grid[x][y] == 1) {
                    visited[x][y] = true;
                    dq.offer(new int[] {x, y});
                }
            }
        }
    }
}
