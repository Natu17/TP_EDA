import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import model.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static spark.Spark.*;
import static utils.Json.json;

public class Start {

  public static void main(String[] args) throws IOException {
    Controller controller = new Controller();
    cors();
    after((req, res) -> res.type("application/json"));
    get("/path", (req, res) -> {
      double fromLat = Double.parseDouble(req.queryParams("fromLat"));
      double fromLng = Double.parseDouble(req.queryParams("fromLng"));
      double toLat = Double.parseDouble(req.queryParams("toLat"));
      double toLng = Double.parseDouble(req.queryParams("toLng"));
      return controller.findPath(fromLat, fromLng, toLat, toLng);
    }, json());
    get("/place", (req, res) -> {
      String searchTerm = req.queryParams("searchTerm");
      return controller.findPlaces(searchTerm);
    }, json());


    String bus= "/paradas-de-colectivo.csv";
    InputStream is = Start.class.getClass().getResourceAsStream(bus);
    Reader in = new InputStreamReader(is);
    Iterable<CSVRecord> recordsBus = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .parse(in);

    //Graph graph = new Graph();
    IndexService<IdxRecord<Double, CSVRecord>> dataCol = new IndexWithDuplicates<>();
    for (CSVRecord record : recordsBus) {
      dataCol.insert(new IdxRecord(String.valueOf(record.get("stop_id")), record));
    }
    System.out.println(dataCol.getMin());



    String sub= "/estaciones-de-subte.csv";
    InputStream isSub = Start.class.getClass().getResourceAsStream(sub);
    Reader inSub = new InputStreamReader(isSub);
    Iterable<CSVRecord> recordsSub = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .parse(inSub);

    //Graph graph = new Graph();
    IndexService<IdxRecord<Double, CSVRecord>> dataSub = new IndexWithDuplicates<>();
    for (CSVRecord record : recordsSub) {
      dataSub.insert(new IdxRecord(String.valueOf(record.get("id")), record));
    }
    System.out.println(dataSub.getMin());





    String places= "/espacios-culturales.csv";
    InputStream isEsp = Start.class.getClass().getResourceAsStream(places);
    Reader inEsp = new InputStreamReader(isEsp);
    Iterable<CSVRecord> recordsEsp = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .parse(inEsp);

    IndexService<IdxRecord<Double, CSVRecord>> dataEsp = new IndexWithDuplicates<>();
    for (CSVRecord record : recordsEsp) {
      dataEsp.insert(new IdxRecord(String.valueOf(record.get("establecimiento")), record));
    }
    System.out.println(dataEsp.getMin());
  }



  public static void cors() {
    before((req, res) -> {
      res.header("Access-Control-Allow-Methods", "*");
      res.header("Access-Control-Allow-Origin", "*");
      res.header("Access-Control-Allow-Headers", "*");
      res.header("Access-Control-Allow-Credentials", "true");
    });
    options("/*", (request, response) -> {
      String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
      if (accessControlRequestHeaders != null) {
        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
      }
      String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
      if (accessControlRequestMethod != null) {
        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
      }
      return "OK";
    });
  }
}
