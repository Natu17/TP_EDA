import org.apache.commons.csv.CSVRecord;

import java.util.Collection;
import java.util.Iterator;

public class CreateMap {
    Graph graph = new Graph();
    public void addBusStation(CSVRecord record){

        Graph.Node node = graph.addNode(Integer.valueOf(record.get("stop_id")),String.valueOf(record.get("route_short_name")),Double.valueOf(record.get("stop_lat")), Double.valueOf(record.get("stop_lon")));
        Collection<Graph.Node> values = graph.nodes();
        Iterator<Graph.Node> it = values.iterator();
        while(it.hasNext()){
            Graph.Node value=  it.next();
            double dist = distance(value.lat, node.lat, value.lng, node.lng);
            if(dist < 10){
                graph.addEdge(node.id, value.id, 2);
            }
        }

    }

    static double distance(double x1, double y1, double x2, double y2)
    {
        // Calculating distance
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}
