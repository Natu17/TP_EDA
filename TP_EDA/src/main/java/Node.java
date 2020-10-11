import javafx.util.Pair;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {
    long id;
    Pair<String,Integer> name;
    Set<Graph.Edge> edges;
    double lat;
    double lng;
    boolean marked;
    double cost;

    public Node(long id, Pair<String,Integer> name, double lat, double lng, Set<Graph.Edge> set) {
        this.lng = lng;
        this.lat = lat;
        this.name = name;
        this.id = id;
        edges = set;
    }

    public Node(long id, Pair<String,Integer> name, double lat, double lng) {
        this.lng = lng;
        this.lat = lat;
        this.name = name;
        this.id = id;
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
