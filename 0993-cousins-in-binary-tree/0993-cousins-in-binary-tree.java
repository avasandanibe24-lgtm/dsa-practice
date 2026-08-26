class Solution {

    public boolean isCousins(TreeNode root, int x, int y) {

        int[] xInfo = dfs(root, x, null, 0);
        int[] yInfo = dfs(root, y, null, 0);


        // same depth but different parents
        return xInfo[0] == yInfo[0] && xInfo[1] != yInfo[1];
    }


    // returns {depth, parent value}
    private int[] dfs(TreeNode root, int target, TreeNode parent, int depth) {

        if(root == null)
            return new int[]{-1, -1};


        if(root.val == target) {
            int parentVal = (parent == null) ? -1 : parent.val;
            return new int[]{depth, parentVal};
        }


        int[] left = dfs(root.left, target, root, depth + 1);

        if(left[0] != -1)
            return left;


        return dfs(root.right, target, root, depth + 1);
    }
}