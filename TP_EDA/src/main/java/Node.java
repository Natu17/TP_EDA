import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {
    long id;
    String name;
    int direction;
    Set<Graph.Edge> edges;
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
