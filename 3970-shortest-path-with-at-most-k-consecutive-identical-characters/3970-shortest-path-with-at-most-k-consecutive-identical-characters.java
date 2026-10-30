import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // {distance, node, consecutive count}
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] curr = pq.poll();

            long d = curr[0];
            int node = (int) curr[1];
            int count = (int) curr[2];

            if (d != dist[node][count]) {
                continue;
            }

            if (node == n - 1) {
                return (int) d;
            }

            for (int[] next : graph[node]) {

                int neighbor = next[0];
                int weight = next[1];

                int newCount;

                if (labels.charAt(node) == labels.charAt(neighbor)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                if (newCount > k) {
                    continue;
                }

                long newDist = d + weight;

                if (newDist < dist[neighbor][newCount]) {
                    dist[neighbor][newCount] = newDist;

                    pq.offer(new long[]{
                        newDist, neighbor, newCount
                    });
                }
            }
        }

        return -1;
    }
}