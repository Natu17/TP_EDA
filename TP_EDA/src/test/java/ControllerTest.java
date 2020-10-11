import model.BusInPath;
import model.PlaceLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ControllerTest {
Controller controller = new Controller();
  @Test
  void testFindPath() {
    //Busco paradas cercanas a un trayecto directo para ver i me lo devuelve bien
    List<BusInPath> result1 = controller.findPath(-34.57548058966485,-58.48892596672341,-34.59838628851389,-58.37129094944708); //hasta echeverria
    Assertions.assertEquals("B", result1.get(0).name);
    Assertions.assertEquals(1, result1.size());

    //Busco paradas cercanas a un trayecto directo para ver i me lo devuelve bien
    List<BusInPath> result2 = controller.findPath(-34.58767237715098,-58.38345455986495,-34.60301957329089,-58.36898285104609);
    Assertions.assertEquals("130A", result2.get(0).name);
    Assertions.assertEquals(1, result2.size());

    //Busco paradas de un recorrido conocido a ver si lo devuelve bien
    List<BusInPath> result3 = controller.findPath(-34.625502643245035,-58.44838547569747,-34.60301957329089,-58.36898285104609);
    Assertions.assertEquals("A", result3.get(0).name);
    Assertions.assertEquals("E", result3.get(1).name);
    Assertions.assertEquals(2, result3.size());

    //Busco paradas cercanas a un trayecto directo para ver i me lo devuelve bien
    List<BusInPath> result4 = controller.findPath(-34.59576732577009,-58.386404777906996,-34.590821401232894,-58.40807702674733);
    Assertions.assertEquals("152A", result4.get(0).name);
    Assertions.assertEquals(1, result4.size());

    //Busco paradas muy cercanas, dond el mejor camino es caminar, y por ende el resullt% deberìa estar vacìo
    List<BusInPath> result5 = controller.findPath(-34.5945308722412,-58.402927185438735,-34.59474283701035,-58.40108182563649);
    Assertions.assertEquals(0, result5.size());
  }

  @Test
  void testPlaceLocation() {
    List<PlaceLocation> result;

    //Busco en el csv de espacios culturales ingresando una sola palabra
    result = controller.findPlaces("crematorio");
    Assertions.assertEquals("CREMATORIO MUNICIPAL", result.get(0).getName());
    Assertions.assertEquals("ARCHIVO HISTORICO DEL CREMATORIO MUNICIPAL", result.get(1).getName());

    result = controller.findPlaces("chacabuco");
    Assertions.assertEquals("PARQUE CHACABUCO", result.get(0).getName());

    result = controller.findPlaces("boveda");
    Assertions.assertEquals("BOVEDA QUE GUARDA LOS RESTOS DE CARLOS GARDEL", result.get(0).getName());

    result = controller.findPlaces("francaise");
    Assertions.assertEquals("PANTEON DE LA SOCIETE FRANCAISE DE SECOURS MUTUELS DE BUENOS AIRES", result.get(0).getName());

    //Busco en el csv de espacios culturales ingresando un termino mal escrito
    result = controller.findPlaces("chacavuco");
    Assertions.assertEquals("PARQUE CHACABUCO", result.get(0).getName());

    result = controller.findPlaces("saleziano");
    Assertions.assertEquals("PANTEON DE LOS PADRES SALESIANOS", result.get(0).getName());

    result = controller.findPlaces("rodnei");
    Assertions.assertEquals("BAR RODNEY", result.get(0).getName());
  }

}