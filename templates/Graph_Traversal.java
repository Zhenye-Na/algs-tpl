/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<UndirectedGraphNode>();
 *     }
 * };
 */


public List<UndirectedGraphNode> getGraphNodes(UndirectedGraphNode node) {

    // BFS to get all nodes
    List<UndirectedGraphNode> graph  = new ArrayList<>();
    Set<UndirectedGraphNode> history = new HashSet<>();
    Queue<UndirectedGraphNode> queue = new LinkedList<>();
    queue.offer(node);
    history.add(node);

    while (!queue.isEmpty()) {

        UndirectedGraphNode tmpNode = queue.poll();
        graph.add(tmpNode);

        for (UndirectedGraphNode neighbor : tmpNode.neighbors) {
            if (!history.contains(neighbor)) {
                queue.offer(neighbor);
                history.add(neighbor);
            }
        }

    }
    return graph;
}
