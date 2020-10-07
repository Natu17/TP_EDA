import model.BusInPath;
import model.PlaceLocation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;


public class Controller {
  Index dataEsp = new Index();
  CreateMap map = new CreateMap();

  final static long ID_START = -1;
  final static  long ID_END = -2;
  final static int DIRECTION_START = -3;
  final static int DIRECTION_END = -4;

  public Controller() {

    String bus= "/paradas-de-colectivo.csv";
    InputStream is = Start.class.getClass().getResourceAsStream(bus);
    Reader in = new InputStreamReader(is);

            try{
              Iterable<CSVRecord> recordsBus = CSVFormat.DEFAULT
                      .withFirstRecordAsHeader()
              .parse(in);
              for (CSVRecord record : recordsBus) {
                map.addBusStation(record);
              }

            } catch (IOException e) {
              e.printStackTrace();
            }

    String sub= "/estaciones-de-subte.csv";
    InputStream isSub = Start.class.getClass().getResourceAsStream(sub);
    Reader inSub = new InputStreamReader(isSub);
   try{
     Iterable<CSVRecord> recordsSub = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .parse(inSub);
    for (CSVRecord record : recordsSub) {
      map.addSubwayStation(record);
    } } catch (IOException e) {
    e.printStackTrace();
  }



  String places= "/espacios-culturales.csv";
    InputStream isEsp = Start.class.getClass().getResourceAsStream(places);
    Reader inEsp = new InputStreamReader(isEsp);
    try{
      Iterable<CSVRecord> recordsEsp = CSVFormat.DEFAULT
              .withFirstRecordAsHeader()
              .parse(inEsp);
    for (CSVRecord record : recordsEsp) {
      dataEsp.put(String.valueOf(record.get("establecimiento")), record);
    } } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
    map.addWalkingStation(ID_START,fromLat,fromLng,DIRECTION_START);
    map.addWalkingStation(ID_END,toLat,toLng,DIRECTION_END);
    Graph graph = map.getGraph();
    map.deleteStartEnd();
    return graph.answer(ID_START,ID_END);

  }

  public List<PlaceLocation> findPlaces(String searchTerm) {
    System.out.println(searchTerm);
    return dataEsp.levenshtein(searchTerm);
  }
}
