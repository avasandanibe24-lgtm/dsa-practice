class Solution {

    public int rangeSumBST(TreeNode root, int low, int high) {

        return dfs(root, low, high);
    }


    private int dfs(TreeNode root, int low, int high) {

        if(root == null)
            return 0;


        int sum = 0;


        if(root.val >= low && root.val <= high) {
            sum += root.val;
        }


        sum += dfs(root.left, low, high);
        sum += dfs(root.right, low, high);


        return sum;
    }
}