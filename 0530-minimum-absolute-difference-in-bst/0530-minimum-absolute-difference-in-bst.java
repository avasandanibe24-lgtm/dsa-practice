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
    public int getMinimumDifference(TreeNode root) {
        List<Integer> ans=new ArrayList<>();
        inorder(root,ans);
        int l=ans.size();
              int min = Integer.MAX_VALUE;

        for (int i = 1; i < l; i++) {
            min = Math.min(min, Math.abs(ans.get(i) - ans.get(i - 1)));
        }

        return min;
    }

        private void inorder(TreeNode root,List<Integer> ans)
        {
            if(root==null)
            return;
            inorder(root.left,ans);
            ans.add(root.val);
            inorder(root.right,ans);
        }

        
    }
