import java.util.*;


public class Graph {
final static int NO_PARENT = -3;

    Map<Integer, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    void addNode(int id, String name, double lat, double lng) {
        nodes.putIfAbsent(id, new Node(id, name, lat, lng));
    }

    void addEdge(int id1, int id2, double weight) {
        Node node1 = nodes.get(id1);
        Node node2 = nodes.get(id2);
        if (node1 == null || node2 == null) {
            return;
        }
        node1.edges.add(new Edge(node2, weight));
    }

    private void unmarkAllNodes() {
        nodes.values().forEach(Node::unmark);
    }

    public Map<Integer,Integer> getSmallerDistance(int startId, int endId) {
        unmarkAllNodes();
        nodes.values().forEach(node -> node.cost = Double.MAX_VALUE);
        PriorityQueue<PqNode> queue = new PriorityQueue<>();
        Map<Integer, Integer> parents = new HashMap<>();

        queue.add(new PqNode(nodes.get(startId), 0)); //agrego primer nodo
        PqNode pqNode = new PqNode(nodes.get(startId), 0);
        while (!queue.isEmpty() && pqNode.node.id != endId) {
            pqNode = queue.remove();
            if (pqNode.node.marked) continue;
            pqNode.node.mark();


            for (Edge edge : pqNode.node.edges) {
                double targetNodeCost = pqNode.cost + edge.weight;
                if (targetNodeCost < edge.targetNode.cost) {
                    edge.targetNode.cost = targetNodeCost;
                    queue.add(new PqNode(edge.targetNode, targetNodeCost));
                    if(Objects.equals(edge.targetNode.name, pqNode.node.name)){
                        parents.put(edge.targetNode.id, parents.get(pqNode.node.id));
                    }else
                        parents.put(edge.targetNode.id, pqNode.node.id);

                }
            }
        }

        return parents;
    }



    public List<String> answer(int idStart, int idEnd){
        Map<Integer, Integer> ans = getSmallerDistance(idStart, idEnd);
        List<String> result = new ArrayList<>();
        int target =ans.get(idEnd);
        boolean found = false;
        while(!found){
            result.add(nodes.get(ans.get(target)).name);
            if(target == idStart)
                found = true;

            target = ans.get(target);
        }

        return result;

    }
    class Node {
        int id;
        String name;
        Set<Edge> edges;
        double lat;
        double lng;
        boolean marked;
        double cost;

        public Node(int id, String name, double lat, double lng) {
            this.lng = lng;
            this.lat = lat;
            this.name = name;
            this.id = id; //PREGUNTAR LO DE ID
            edges = new HashSet<>();
        }

        void mark() {
            marked = true;
        }

        void unmark() {
            marked = false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            if(Integer.compare(node.id, id) == 0){
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    class Edge {
        Node targetNode;
        double weight;

        public Edge(Node targetNode, double weight) {
            this.targetNode = targetNode;
            this.weight = weight;
        }
    }

    class PqNode implements Comparable<PqNode> {
        Node node;
        double cost;

        public PqNode(Node node, double cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(PqNode other) {
            return Double.compare(cost, other.cost);
        }
    }
}

