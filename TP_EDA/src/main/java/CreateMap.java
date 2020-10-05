import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.text.StyledEditorKit;
import java.util.Collection;
import java.util.Iterator;

public class CreateMap {

    public final static double V_PERSON = 5; //VELOCIDAD PROMEDIO DE UNA PERSONA EN KILOMETROS POR HORA
    public final static double V_SUBWAY = 45;
    public final static double V_BUS = 10.5;
    public final static double T_SUBWAY = 4;
    public final static double T_BUS = 10;
    public final static double RADIO_TIERRA = 6378.0F;
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

            return;
            /*
        Node node = new Node(id,lineName,lat,lng,direction);
        Collection<Node> values = graph.nodes();
        Iterator<Node> it = values.iterator();
            while(it.hasNext()){
                Node value = it.next();

                if(Long.compare(node.id,value.id) != 0){
                    double dist = distanceNormalize(value.lat, node.lat, value.lng, node.lng);
                if(node.name.compareTo(value.name) == 0 && Integer.compare(node.direction,value.direction) == 0){
                    if(value.direction == -1){

                        graph.addEdge(node.id, value.id, dist/V_SUBWAY);
                    }else graph.addEdge(node.id, value.id, dist/V_BUS);
                }else {
                    if(dist < 0.4){
                        if(value.direction == -1 && node.direction == -1) { // los dos son subtes diferentes
                            graph.addEdge(node.id, value.id, dist/V_SUBWAY + T_SUBWAY);
                        }else
                            graph.addEdge(node.id, value.id, dist/V_BUS + T_BUS); //fijarse despues el peso (se modifica dependiendo de la distancia)
                    }
                }
            }

        }
*/

        }

    public double ToGrads(double number){
        return (Math.PI / 180)*number;
    }

    public double distanceNormalize (double lat1, double lat2, double lng1, double lng2)
    {
        double difLatitud = lat1 - lat2;
        //coloca ela diferencia en radianes
        double difLongitud = lng1 - lng2;



        // Calculating distance
       double result = Math.acos(Math.sin(ToGrads(lat1)) *Math.sin(ToGrads(lat2)) +
               Math.cos(ToGrads(lat1)) * Math.cos(ToGrads(lat2)) *
                       Math.cos(ToGrads(difLongitud)))*RADIO_TIERRA;

       return result;



    }



}
