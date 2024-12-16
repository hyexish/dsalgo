public class UnionFind {

    private int[] parent;

    public UnionFind(int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            parent[u] = v;
        }
    }
}
