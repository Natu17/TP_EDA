import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.text.StyledEditorKit;
import java.util.Collection;
import java.util.Iterator;

public class CreateMap {

    Graph graph = new Graph();

    public void addWalkingStation(long id, double lat, double lng, int direction){
        addStation(id, "walk", lat, lng, direction);

    }

    public Graph getGraph() {
        return graph;
    }

    public void addBusStation(CSVRecord record){
        addStation(Long.valueOf(record.get("stop_id")), record.get("route_short_name"), Double.valueOf(record.get("stop_lat")), Double.valueOf(record.get("stop_lon")), Integer.valueOf(record.get("direction_id")));
    }
    public void addSubwayStation(CSVRecord record){
        addStation(((long) Double.parseDouble(record.get("id"))), record.get("linea"), Double.valueOf(record.get("lat")), Double.valueOf(record.get("long")), -1);
    }

    private void addStation( long id, String lineName, double lat, double lng, int direction ){
        if(lineName == null )
            return;
        Graph.Node node = graph.addNode(id,lineName,lat,lng,direction);
        Collection<Graph.Node> values = graph.nodes();
        Iterator<Graph.Node> it = values.iterator();
        while(it.hasNext()){
            Graph.Node value = it.next();
            double dist = distanceNormalize(value.lat, node.lat, value.lng, node.lng);
            if(node.name.compareTo(value.name) == 0 && Integer.compare(node.direction,value.direction) == 0){
                if(value.direction == -1){
                    graph.addEdge(node.id, value.id, dist);
                }else graph.addEdge(node.id, value.id, dist*2);
            }else {
                if(dist < 100){
                    if(value.direction == -1 && node.direction == -1) { // los dos son subtes diferentes
                        graph.addEdge(node.id, value.id, dist*4 + 1);
                    }else
                        graph.addEdge(node.id, value.id, dist*4 + 6); //fijarse despues el peso (se modifica dependiendo de la distancia)
                    }
                }
            }

        }
    static double distanceNormalize (double x1, double y1, double x2, double y2)
    {
        // Calculating distance
        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)))*1000; //normalizar!!!
    }

}
