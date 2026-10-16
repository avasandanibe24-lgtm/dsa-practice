
class Solution {
    static final long MOD = 1_000_000_007L;

    long[] sum;
    long[] sq;
    long[] lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            
            update(1, 0, n - 1, prev, i);
            last[nums[i]] = i + 1;

            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    void update(int node, int start, int end, int l, int r) {
        if (l > end || r < start) {
            return;
        }

        if (l <= start && end <= r) {
            apply(node, start, end);
            return;
        }

        push(node, start, end);

        int mid = (start + end) / 2;

        update(node * 2, start, mid, l, r);
        update(node * 2 + 1, mid + 1, end, l, r);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    void apply(int node, int start, int end) {
        long len = end - start + 1;
        long oldSum = sum[node];

        sq[node] = (sq[node] + 2L * oldSum + len) % MOD;
        sum[node] = (oldSum + len) % MOD;
        lazy[node]++;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = (start + end) / 2;

        applyMultiple(node * 2, start, mid, lazy[node]);
        applyMultiple(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }

    void applyMultiple(int node, int start, int end, long k) {
        long len = end - start + 1;
        long oldSum = sum[node];

        sq[node] = (sq[node] + 2L * k % MOD * oldSum
                    + k * k % MOD * len) % MOD;

        sum[node] = (oldSum + k * len) % MOD;
        lazy[node] += k;
    }
}
