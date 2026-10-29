import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[] parent = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] order = new int[n];
        int[] size = new int[n];

        int[] stack = new int[n];
        int[] next = new int[n];
        int top = 0;
        int timer = 0;

        // Iterative DFS to calculate Euler tour and parents
        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[top - 1];

            if (next[u] == 0) {
                tin[u] = timer;
                order[timer++] = u;
                size[u] = 1;
            }

            if (next[u] < graph[u].size()) {
                int v = graph[u].get(next[u]++);

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                stack[top++] = v;
            } else {
                tout[u] = timer - 1;
                top--;

                if (u != 0) {
                    size[parent[u]] += size[u];
                }
            }
        }

        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        char[] chars = s.toCharArray();
        int[] prefix = new int[n];

        for (int i = 0; i < n; i++) {
            int u = order[i];
            int mask = 1 << (chars[u] - 'a');

            if (u == 0) {
                prefix[u] = mask;
            } else {
                prefix[u] = prefix[parent[u]] ^ mask;
            }
        }

        Fenwick bit = new Fenwick(n);
        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                if (delta != 0) {
                    bit.update(tin[u] + 1, delta);
                    bit.update(tout[u] + 2, delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int maskU = prefix[u] ^ bit.query(tin[u] + 1);
                int maskV = prefix[v] ^ bit.query(tin[v] + 1);

                int ancestor = lca(u, v, tin, tout, up, parent, LOG);

                int pathMask = maskU ^ maskV
                        ^ (1 << (chars[ancestor] - 'a'));

                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private int lca(int u, int v, int[] tin, int[] tout,
                    int[][] up, int[] parent, int LOG) {
        if (isAncestor(u, v, tin, tout)) {
            return u;
        }

        if (isAncestor(v, u, tin, tout)) {
            return v;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (!isAncestor(up[k][u], v, tin, tout)) {
                u = up[k][u];
            }
        }

        return parent[u];
    }

    private boolean isAncestor(int u, int v, int[] tin, int[] tout) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    static class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int value) {
            while (i <= n + 1) {
                tree[i] ^= value;
                i += i & -i;
            }
        }

        int query(int i) {
            int result = 0;

            while (i > 0) {
                result ^= tree[i];
                i -= i & -i;
            }

            return result;
        }
    }
}