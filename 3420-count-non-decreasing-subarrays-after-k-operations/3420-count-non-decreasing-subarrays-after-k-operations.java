import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[] nextGreater = new int[n];
        Arrays.fill(nextGreater, n);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                nextGreater[i] = stack.peek();
            }

            stack.push(i);
        }

        long ans = 0;
        long cost = 0;

        Deque<long[]> segments = new ArrayDeque<>();

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            long cnt = 1;

            while (!segments.isEmpty() &&
                    segments.peekFirst()[0] < nums[i]) {

                long[] cur = segments.pollFirst();

                cost += (long) (nums[i] - cur[0]) * cur[1];
                cnt += cur[1];
            }

            segments.offerFirst(new long[]{nums[i], cnt});

            while (cost > k) {

                long val = nums[j];
                long[] last = segments.peekLast();

                cost -= (last[0] - val);

                last[1]--;

                if (last[1] == 0) {
                    segments.pollLast();
                }

                j--;
            }

            ans += (j - i + 1);
        }

        return ans;
    }
}