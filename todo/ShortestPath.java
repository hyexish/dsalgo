import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;

/**
 * ShortestPath
 */
public class ShortestPath {

    public static Logger logger = Logger.getLogger(ShortestPath.class.getName());

    public static void main(String[] args) {
        int[][] data = { { 1, 2, 1 }, { 1, 3, 4 }, { 2, 3, 2 }, { 2, 4, 5 }, { 3, 4, 2 }, { 4, 5, 3 }, { 2, 6, 4 },
                { 5, 7, 4 }, { 6, 7, 9 } };
        int N = 7;
        int M = 9;

        // int[][] grid = new int[N + 1][N + 1];
        // for (int i = 0; i <= N; i++) {
        // Arrays.fill(grid[i], Integer.MAX_VALUE);
        // }
        // for (int i = 0; i < M; i++) {
        // int u = data[i][0];
        // int v = data[i][1];
        // int w = data[i][2];
        // grid[u][v] = w;
        // }
        // dijkstra(grid);

        // List<int[]>[] grid = new List[N + 1];
        // Arrays.setAll(grid, g -> new ArrayList<>());
        // for (int i = 0; i < M; i++) {
        // int u = data[i][0];
        // int v = data[i][1];
        // int w = data[i][2];
        // grid[u].add(new int[] { v, w });
        // }
        // dijkstra(grid);

        // int[][] grid = new int[N + 1][N + 1];
        // for (int i = 0; i <= N; i++) {
        //     Arrays.fill(grid[i], Integer.MAX_VALUE / 2);
        // }
        // for (int i = 0; i < M; i++) {
        //     int u = data[i][0];
        //     int v = data[i][1];
        //     int w = data[i][2];
        //     grid[u][v] = grid[v][u] = w;
        // }
        // floyd(grid);

        // ford(data , N);

        List<int[]>[] grid = new List[N + 1];
        Arrays.setAll(grid, g -> new ArrayList<>());
        for (int i = 0; i < M; i++) {
        int u = data[i][0];
        int v = data[i][1];
        int w = data[i][2];
        grid[u].add(new int[] { v, w });
        }
        ford(grid);
    }

    public static void dijkstra(List<int[]>[] grid) {
        int N = grid.length - 1;
        int[] minDist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] { 1, 0 });
        minDist[1] = 0;
        while (!pq.isEmpty()) {
            int[] v = pq.poll();
            if (visited[v[0]])
                continue;
            visited[v[0]] = true;
            for (int[] edge : grid[v[0]]) {
                if (!visited[edge[0]] && minDist[v[0]] + edge[1] < minDist[edge[0]]) {
                    minDist[edge[0]] = minDist[v[0]] + edge[1];
                    pq.add(new int[] { edge[0], minDist[edge[0]] });
                }
            }
        }
        int shortestPath = minDist[N] == Integer.MAX_VALUE ? -1 : minDist[N];
        logger.info("shortestPath: " + shortestPath);
    }

    // Dijkstra
    public static void dijkstra(int[][] grid) {
        int N = grid.length - 1;
        int[] minDist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            minDist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        minDist[1] = 0;
        for (int i = 1; i <= N; i++) {
            int minVal = Integer.MAX_VALUE;
            int cur = i;
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && minDist[j] < minVal) {
                    minVal = minDist[j];
                    cur = j;
                }
            }
            visited[cur] = true;
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && grid[cur][j] != Integer.MAX_VALUE && minDist[cur] + grid[cur][j] < minDist[j]) {
                    minDist[j] = minDist[cur] + grid[cur][j];
                }
            }
        }
        int shortestPath = minDist[N] == Integer.MAX_VALUE ? -1 : minDist[N];
        logger.info("shortestPath: " + shortestPath);
    }

    // Queue improved Bellman-Ford
    // Bellman_ford algorithm
    public static void ford(List<int[]>[] grid) {
        int N = grid.length - 1;
        int[] minDist = new int[N + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(1);
        minDist[1] = 0;
        while (!dq.isEmpty()) {
            int cur = dq.poll();
            for (int[] edge: grid[cur]) {
                int from = cur;
                int to = edge[0];
                int val = edge[1];
                if (minDist[from] + val < minDist[to]) {
                    minDist[to] = minDist[from] + val;
                    dq.offer(to);
                }
            }
        }
        int shortestPath = minDist[N] == Integer.MAX_VALUE ? -1 : minDist[N];
        logger.info("shortestPath: " + shortestPath);
    }

    // Bellman_ford algorithm
    public static void ford(int[][] edges, int N) {
        int[] minDist = new int[N + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;
        for (int i = 1; i < N; i++) {
            for (int[] edge: edges) {
                int from = edge[0];
                int to = edge[1];
                int val = edge[2];
                if (minDist[from] != Integer.MAX_VALUE && minDist[from] + val < minDist[to]) {
                    minDist[to] = minDist[from] + val;
                }
            }
        }
        int shortestPath = minDist[N] == Integer.MAX_VALUE ? -1 : minDist[N];
        logger.info("shortestPath: " + shortestPath);
    }

    // Floyd-Warshall algorithm
    public static void floyd(int[][] grid) {
        int N = grid.length - 1;
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][k] + grid[k][j]);
                }
            }
        }
        int shortestPath = grid[1][N];
        logger.info("shortestPath: " + shortestPath);
    }
}