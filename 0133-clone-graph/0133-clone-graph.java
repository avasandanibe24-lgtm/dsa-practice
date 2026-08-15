class Solution {

    HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {

        if(node == null)
            return null;

        // already cloned
        if(map.containsKey(node))
            return map.get(node);


        // create clone
        Node copy = new Node(node.val);

        // store before going deeper
        map.put(node, copy);


        // clone neighbors
        for(Node neighbour : node.neighbors) {
            copy.neighbors.add(cloneGraph(neighbour));
        }


        return copy;
    }
}