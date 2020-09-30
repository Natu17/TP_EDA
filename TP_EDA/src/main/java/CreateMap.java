import org.apache.commons.csv.CSVRecord;

public class CreateMap {
    Graph graph = new Graph();
    public void addBusStation(CSVRecord record){
        graph.addNode(Integer.valueOf(record.get("stop_id")),String.valueOf(record.get("route_short_name")),Double.valueOf(record.get("stop_lat")), Double.valueOf(record.get("stop_lon")));

    }
}
