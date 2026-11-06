class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;

        int[][] qs = new int[m][2];
        for (int i = 0; i < m; i++) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }

        Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[m];

        Map<Integer, Integer> freq = new HashMap<>();

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int[] q : qs) {
            int time = q[0];
            int idx = q[1];

            // Add logs with time <= query time
            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];

                freq.put(server, freq.getOrDefault(server, 0) + 1);

                if (freq.get(server) == 1) {
                    activeServers++;
                }

                right++;
            }

            // Remove logs with time < time - x
            while (left < right && logs[left][1] < time - x) {
                int server = logs[left][0];

                freq.put(server, freq.get(server) - 1);

                if (freq.get(server) == 0) {
                    freq.remove(server);
                    activeServers--;
                }

                left++;
            }

            ans[idx] = n - activeServers;
        }

        return ans;
    }
}