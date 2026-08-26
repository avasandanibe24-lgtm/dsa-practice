class Solution {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        dfs(root1, s1);
        dfs(root2, s2);

        return s1.toString().equals(s2.toString());
    }


    private void dfs(TreeNode root, StringBuilder sol) {

        if (root == null) {
            return;
        }


        // leaf node
        if (root.left == null && root.right == null) {
            sol.append(root.val).append(",");
            return;
        }


        dfs(root.left, sol);
        dfs(root.right, sol);
    }
}