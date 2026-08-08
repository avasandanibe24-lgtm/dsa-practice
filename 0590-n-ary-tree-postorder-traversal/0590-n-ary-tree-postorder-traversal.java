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
    public List<Integer> postorder(Node root) {
        List<Integer> pre=new ArrayList<>();

        if(root==null)
        return pre;
        postorder(root,pre);
        return pre;
    }

        private void postorder(Node root,List<Integer> pre)
        {
            if(root==null)
            return ;
            for(Node temp:root.children)
            {
                postorder(temp,pre);
            }
            pre.add(root.val);
        }
        
    
}