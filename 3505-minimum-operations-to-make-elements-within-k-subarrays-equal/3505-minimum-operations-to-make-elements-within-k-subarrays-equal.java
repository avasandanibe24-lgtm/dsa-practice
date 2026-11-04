import java.util.*;

class Solution {

    class MedianCost {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        long leftSum = 0;
        long rightSum = 0;

        int leftSize = 0;
        int rightSize = 0;

        void add(TreeMap<Integer, Integer> map, int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        void remove(TreeMap<Integer, Integer> map, int x) {
            int cnt = map.get(x);
            if (cnt == 1) map.remove(x);
            else map.put(x, cnt - 1);
        }

        void addNum(int x) {
            if (leftSize == 0 || x <= left.lastKey()) {
                add(left, x);
                leftSize++;
                leftSum += x;
            } else {
                add(right, x);
                rightSize++;
                rightSum += x;
            }
            balance();
        }

        void removeNum(int x) {
            if (left.containsKey(x)) {
                remove(left, x);
                leftSize--;
                leftSum -= x;
            } else {
                remove(right, x);
                rightSize--;
                rightSum -= x;
            }
            balance();
        }

        void balance() {
            while (leftSize < rightSize) {
                int x = right.firstKey();

                remove(right, x);
                rightSize--;
                rightSum -= x;

                add(left, x);
                leftSize++;
                leftSum += x;
            }

            while (leftSize > rightSize + 1) {
                int x = left.lastKey();

                remove(left, x);
                leftSize--;
                leftSum -= x;

                add(right, x);
                rightSize++;
                rightSum += x;
            }
        }

        long cost() {
            int median = left.lastKey();

            long leftCost = 1L * median * leftSize - leftSum;
            long rightCost = rightSum - 1L * median * rightSize;

            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;
        int m = n - x + 1;

        long[] windowCost = new long[m];

        MedianCost mc = new MedianCost();

        for (int i = 0; i < x; i++) {
            mc.addNum(nums[i]);
        }

        windowCost[0] = mc.cost();

        for (int i = x; i < n; i++) {
            mc.removeNum(nums[i - x]);
            mc.addNum(nums[i]);

            windowCost[i - x + 1] = mc.cost();
        }

        long INF = (long) 4e18;

        long[] prev = new long[m + 1];
        Arrays.fill(prev, 0);

        for (int take = 1; take <= k; take++) {

            long[] cur = new long[m + 1];
            Arrays.fill(cur, INF);
            cur[m] = INF;

            for (int i = m - 1; i >= 0; i--) {

                long skip = cur[i + 1];

                int nxt = Math.min(m, i + x);

                long choose = windowCost[i];

                if (prev[nxt] < INF) {
                    choose += prev[nxt];
                } else {
                    choose = INF;
                }

                cur[i] = Math.min(skip, choose);
            }

            prev = cur;
        }

        return prev[0];
    }
}