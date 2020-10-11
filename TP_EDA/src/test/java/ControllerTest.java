import model.BusInPath;
import model.PlaceLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ControllerTest {
Controller controller = new Controller();
  @Test
  void testFindPath() {
      controller.map.addStation(1,"B",-34.57457314259079,-58.48760329603265,-1); //agrego la parada del b "juan manuel de rosas"
      controller.map.addStation(2,"B",-34.578172918809614,-58.48024331449579,-1);//ECHEVERRIA
      controller.map.addStation(3,"B",-34.58167979014384,-58.47294770597528,-1); //los incas
      controller.map.addStation(4,"107",-34.57488194679862,-58.48744619910095,1); //parada del 107 al lado de la primera del subte b

    List<BusInPath> result = controller.findPath(-34.57548058966485,-58.48892596672341,-34.577370048364,-58.478997234219385); //hasta echeverria
    Assertions.assertEquals("B", result.get(0).name);
    Assertions.assertEquals(1, result.size());

    //SEGU AGREGA MAS ESTACIONES Y PROBA MAS CAMINOS (LAS ESTACIONES LA SAQUE A MANO DEL MAPA NO DEL EXCEL NO TE COMPLIQUES)
  }

  @Test
  void testPlaceLocation() {

    controller.dataEsp.put("hospital aleman", new PlaceLocation("hospital aleman", -34.591806, -58.402139));
    controller.dataEsp.put("hospital alemania", new PlaceLocation("hospital alemania", -34.6543773, -58.5324));
    controller.dataEsp.put("hospital italiano", new PlaceLocation("hospital italiano", -34.606583, -58.425));
    controller.dataEsp.put("hospital italienne", new PlaceLocation("hospital italienne", -34.604545, -58.44357));
    controller.dataEsp.put("hospital español", new PlaceLocation("hospital español", -34.654764, -58.32525));
    controller.dataEsp.put("hospital españa", new PlaceLocation("hospital españa", -34.654567, -58.323));

    List<PlaceLocation> result = controller.findPlaces("hospital italiano");


    Assertions.assertEquals("hospital italiano", result.get(0).getName());
    Assertions.assertEquals("hospital italienne", result.get(1).getName());

    result =  controller.findPlaces("hospital aleman");

    Assertions.assertEquals("hospital aleman", result.get(0).getName());
    Assertions.assertEquals("hospital alemania", result.get(1).getName());

    result = controller.findPlaces("hospital español");

    Assertions.assertEquals("hospital español", result.get(0).getName());
    Assertions.assertEquals("hospital españa", result.get(1).getName());

    //Busco un lugar mal escrito
    result =  controller.findPlaces("hospital espanol");

    Assertions.assertEquals("hospital español", result.get(0).getName());
    Assertions.assertEquals("hospital españa", result.get(1).getName());
  }

}