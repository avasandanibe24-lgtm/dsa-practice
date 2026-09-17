class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(left, Integer.MAX_VALUE);
        Arrays.fill(right, Integer.MAX_VALUE);

        // Find shortest subarray ending at or before i
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        int best = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            sum += arr[i];

            if (map.containsKey(sum - target)) {
                int start = map.get(sum - target) + 1;
                int len = i - start + 1;

                best = Math.min(best, len);
            }

            left[i] = best;

            map.put(sum, i);
        }


        // Find shortest subarray starting at or after i
        map.clear();
        map.put(0, n);

        sum = 0;
        best = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {

            sum += arr[i];

            if (map.containsKey(sum - target)) {
                int end = map.get(sum - target) - 1;
                int len = end - i + 1;

                best = Math.min(best, len);
            }

            right[i] = best;

            map.put(sum, i);
        }


        // Combine left and right parts
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {

            if (left[i] != Integer.MAX_VALUE &&
                right[i + 1] != Integer.MAX_VALUE) {

                answer = Math.min(answer, left[i] + right[i + 1]);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}