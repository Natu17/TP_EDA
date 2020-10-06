import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CreateMap {

    public final static double V_PERSON = 5; //VELOCIDAD PROMEDIO DE UNA PERSONA EN KILOMETROS POR HORA
    public final static double V_SUBWAY = 45;
    public final static double V_BUS = 10.5;
    public final static double T_SUBWAY = 0.05;
    public final static double T_BUS = 0.083;
    public final static double RADIO_TIERRA = 6378.0F;
    public final static double TOP_LEFT_LAT = -34.53435827348597;
    public final static double TOP_LEFT_LNG = -58.53361252042868;
    public final static double BOTTOM_RIGHT_LAT = -34.70889591496078;
    public final static double BOTTOM_RIGHT_LNG = -58.335773889403406;
    Graph graph = new Graph();
    private List<Node>[][] city;

    public CreateMap() {
       double height = distanceNormalize(TOP_LEFT_LAT, BOTTOM_RIGHT_LAT, TOP_LEFT_LNG, TOP_LEFT_LNG);
       double width = distanceNormalize(BOTTOM_RIGHT_LAT, BOTTOM_RIGHT_LAT, TOP_LEFT_LNG, BOTTOM_RIGHT_LNG);
       int rows = (int) (height + 1);
       int columns = (int) (width +1);
       city = new List[rows][columns];
        System.out.println(rows);
        System.out.println(columns);
        System.out.println("BASE");
    }



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
        Node node = new Node(id,lineName,lat,lng,direction);
        graph.addNode(node);
        if(lat < TOP_LEFT_LAT && lng > TOP_LEFT_LNG && lat > BOTTOM_RIGHT_LAT && lng < BOTTOM_RIGHT_LNG){
            double heightDist = distanceNormalize(TOP_LEFT_LAT, node.lat, TOP_LEFT_LNG, TOP_LEFT_LNG);
            double widthDist = distanceNormalize(TOP_LEFT_LAT, TOP_LEFT_LAT, TOP_LEFT_LNG, node.lng);
            int row = (int) (heightDist);
            int column = (int) (widthDist);

            addCost(node, row, column);
            city[row][column].add(node);

        }




        }

        private void addCost(Node node, int row, int column) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (!(i <= -1 || j <= -1 || i >= city.length || j >= city[0].length)) {
                        if(city[i][j] == null){
                            city[i][j] = new ArrayList<>();

                        }else{


                        Iterator<Node> it = city[i][j].iterator();
                        while (it.hasNext()){
                        Node current = it.next();
                            double dist = distanceNormalize(current.lat, node.lat, current.lng, node.lng);
                            if (node.name.compareTo(current.name) == 0 && Integer.compare(node.direction, current.direction) == 0) {
                                if (current.direction == -1) {

                                    graph.addEdge(node.id, current.id, dist / V_SUBWAY);
                                } else graph.addEdge(node.id, current.id, dist / V_BUS);
                            } else {
                                if(current.direction == -1 && node.direction == -1) { // los dos son subtes diferentes, estoy caminado de uno al otro
                                    graph.addEdge(node.id, current.id, dist/V_PERSON + T_SUBWAY);
                                }else
                                    graph.addEdge(node.id, current.id, dist/V_PERSON + T_BUS); //fijarse despues el peso (se modifica dependiendo de la distancia)
                                 }
                            }
                        }
                    }
                        }
                    }

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
