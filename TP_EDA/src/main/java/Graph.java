import model.BusInPath;

import java.util.*;


public class Graph {
final static long NO_PARENT = -3;

    Map<Long, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public int size(){
        return nodes.size();
    }


    public Collection<Node> nodes(){
        return nodes.values();
    }
    Node addNode(long id, String name, double lat, double lng, int direction) {
        Node node = new Node(id, name, lat, lng,direction);
        nodes.put(id, node);
        return node;
    }

    void addEdge(long id1, long id2, double weight) {
        Node node1 = nodes.get(id1);
        Node node2 = nodes.get(id2);
        if (node1 == null || node2 == null) {
            return;
        }
        node1.edges.add(new Edge(node2, weight));
        node2.edges.add(new Edge(node1, weight));
    }

    private void unmarkAllNodes() {
        nodes.values().forEach(Node::unmark);
    }

    public Map<Long,Long> getSmallerDistance(long startId, long endId) {
        unmarkAllNodes();
        nodes.values().forEach(node -> node.cost = Double.MAX_VALUE);
        PriorityQueue<PqNode> queue = new PriorityQueue<>();
        Map<Long, Long> parents = new HashMap<>(); //mapa de id a id de su parent

        queue.add(new PqNode(nodes.get(startId), 0)); //agrego primer nodo
        parents.put(startId, NO_PARENT);
        PqNode pqNode = new PqNode(nodes.get(startId), 0);
        while (!queue.isEmpty() && pqNode.node.id != endId) { //ESTOY HACIENDO UN WHILE DE MAS
            pqNode = queue.remove();
            if (pqNode.node.marked) continue;
            pqNode.node.mark();


            for (Edge edge : pqNode.node.edges) {
                double targetNodeCost = pqNode.cost + edge.weight;
                if (targetNodeCost <   edge.targetNode.cost) {
                    edge.targetNode.cost = targetNodeCost;
                    queue.add(new PqNode(edge.targetNode, targetNodeCost));
                    if(pqNode.node.id == startId){
                        parents.put(edge.targetNode.id, pqNode.node.id);
                    }else {


                        if (((pqNode.node.name).compareTo(edge.targetNode.name)) == 0 && (edge.targetNode.name).compareTo(nodes.get(parents.get(pqNode.node.id)).name) == 0) {
                            parents.put(edge.targetNode.id, nodes.get(parents.get(pqNode.node.id)).id);
                        }else
                        {
                            parents.put(edge.targetNode.id, pqNode.node.id);
                        }
                    }

                }
            }
        }

        return parents;
    }



    public List<BusInPath> answer(long idStart, long idEnd){
        Map<Long, Long> ans = getSmallerDistance(idStart, idEnd);
        List<BusInPath> result = new ArrayList<>();
        long target =idEnd;
        Node node = nodes.get(idEnd);


        boolean found = false;
        while(!found){

            if(target == idStart)
                found = true;
            else {
                Node endPath = nodes.get(ans.get(target));
                target = ans.get(target);
                if(target == idStart) return result;
                Node startPath = nodes.get(ans.get(target));
                result.add(new BusInPath(endPath.name, startPath.lat, startPath.lng,endPath.lat,endPath.lng));
                target = ans.get(target);
            }
        }

        return result;

    }
    class Node {
        long id;
        String name;
        int direction;
        Set<Edge> edges;
        double lat;
        double lng;
        boolean marked;
        double cost;

        public Node(long id, String name, double lat, double lng, int direction) {
            this.lng = lng;
            this.lat = lat;
            this.name = name;
            this.id = id;
            this.direction = direction;
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
            if(Long.compare(node.id, id) == 0){
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

