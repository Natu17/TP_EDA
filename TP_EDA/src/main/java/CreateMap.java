import org.apache.commons.csv.CSVRecord;

import java.util.Collection;
import java.util.Iterator;

public class CreateMap {
    Graph graph = new Graph();
    public void addBusStation(CSVRecord record){
        addStation(record, "stop_id", "route_short_name", "stop_lat", "stop_long");
    }

    public void addSubwayStation(CSVRecord record){
        addStation(record, "id", "linea", "lat", "long");
    }

    private void addStation(CSVRecord record, String id, String lineName, String lat, String lng){
        Graph.Node node = graph.addNode(Integer.valueOf(record.get(id)),String.valueOf(record.get(lineName)),Double.valueOf(record.get(lat)), Double.valueOf(record.get(lng)));
        Collection<Graph.Node> values = graph.nodes();
        Iterator<Graph.Node> it = values.iterator();
        while(it.hasNext()){
            Graph.Node value = it.next();
            double dist = distance(value.lat, node.lat, value.lng, node.lng);
            //Si es la misma parada
            if(node.equals(value)){
                graph.addEdge(node.id, value.id, 1);
            }
            else if(dist < 0.02){
                // Si las paradas pertenecen a distintas lineas
                if(node.name.compareTo(value.name) != 0){
                    graph.addEdge(node.id, value.id, 5); //fijarse despues el peso (se modifica dependiendo de la distancia)
                }else {
                    graph.addEdge(node.id, value.id, 2);
                }
            }

        }
    }
    static double distance(double x1, double y1, double x2, double y2)
    {
        // Calculating distance
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}
