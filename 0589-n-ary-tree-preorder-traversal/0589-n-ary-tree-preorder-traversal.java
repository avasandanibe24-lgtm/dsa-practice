/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> pre=new ArrayList<>();

        if(root==null)
        return pre;
        preorder(root,pre);
        return pre;
    }

        private void preorder(Node root,List<Integer> pre)
        {
            if(root==null)
            return ;
            pre.add(root.val);
            for(Node temp:root.children)
            {
                preorder(temp,pre);
            }
        }
        
    
}