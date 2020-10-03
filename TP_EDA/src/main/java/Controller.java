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

  public Controller() throws IOException {
    String bus= "/paradas-de-colectivo.csv";
    InputStream is = Start.class.getClass().getResourceAsStream(bus);
    Reader in = new InputStreamReader(is);
    Iterable<CSVRecord> recordsBus = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .parse(in);

    String sub= "/estaciones-de-subte.csv";
    InputStream isSub = Start.class.getClass().getResourceAsStream(sub);
    Reader inSub = new InputStreamReader(isSub);
    Iterable<CSVRecord> recordsSub = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .parse(inSub);


    String places= "/espacios-culturales.csv";
    InputStream isEsp = Start.class.getClass().getResourceAsStream(places);
    Reader inEsp = new InputStreamReader(isEsp);
    Iterable<CSVRecord> recordsEsp = CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .parse(inEsp);


    for (CSVRecord record : recordsEsp) {
      dataEsp.put(String.valueOf(record.get("establecimiento")), record);
    }
  }

  public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {

    return Arrays.asList(new BusInPath("No implementado", 0, 0, 0, 0));
  }

  public List<PlaceLocation> findPlaces(String searchTerm) {
    return dataEsp.levenshtein(searchTerm);
    //return Arrays.asList(new PlaceLocation("No implementado",0, 0));
  }
}
