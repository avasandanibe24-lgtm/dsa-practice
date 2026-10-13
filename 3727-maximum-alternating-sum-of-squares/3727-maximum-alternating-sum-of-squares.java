import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        // Convert to Integer[] for comparator
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        // Sort by square descending
        Arrays.sort(arr, (a, b) -> Long.compare((long)b * b, (long)a * a));

        long score = 0;
        int left = 0, right = arr.length - 1;
        int index = 0;

        // Greedy placement: largest to even, smallest to odd
        while (left <= right) {
            if (index % 2 == 0) {
                score += (long) arr[left] * arr[left];
                left++;
            } else {
                score -= (long) arr[right] * arr[right];
                right--;
            }
            index++;
        }
        return score;
    }
}
