
class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            int increase = dp.getOrDefault(num, 0) + 1;
            int same = dp.getOrDefault(num - 1, 0) + 1;

            dp.put(num + 1, Math.max(
                dp.getOrDefault(num + 1, 0), increase
            ));

            dp.put(num, Math.max(
                dp.getOrDefault(num, 0), same
            ));

            ans = Math.max(ans, Math.max(
                dp.get(num), dp.get(num + 1)
            ));
        }

        return ans;
    }
}
