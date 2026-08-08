/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (root == null) {
            return false;
        }

        // Check if current tree is same
        if (isSame(root, subRoot)) {
            return true;
        }

        // Search left and right
        return isSubtree(root.left, subRoot) ||
               isSubtree(root.right, subRoot);
    }


    private boolean isSame(TreeNode root, TreeNode subRoot) {

        // Both are empty
        if (root == null && subRoot == null) {
            return true;
        }

        // One is empty
        if (root == null || subRoot == null) {
            return false;
        }

        // Values don't match
        if (root.val != subRoot.val) {
            return false;
        }

        // Check children
        return isSame(root.left, subRoot.left) &&
               isSame(root.right, subRoot.right);
    }
}