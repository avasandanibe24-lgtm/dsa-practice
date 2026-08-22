class Solution {

    long min = Long.MAX_VALUE;
    long min2 = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {

        dfs(root);

        return min2 == Long.MAX_VALUE ? -1 : (int) min2;
    }

    public void dfs(TreeNode root) {

        if (root == null) {
            return;
        }

        if (root.val < min) {
            min2 = min;
            min = root.val;
        } 
        else if (root.val > min && root.val < min2) {
            min2 = root.val;
        }

        dfs(root.left);
        dfs(root.right);
    }
}