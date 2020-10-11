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
    controller.map.addStation(4,"107",-34.57488194679862,-58.48744619910095,1); //parada del 107 al lado de la primera del subte

    controller.map.addStation(5,"130",-34.58829487351996,-58.38286818192071 ,1);
    controller.map.addStation(6,"130",-34.59018501549182,-58.379563700414366,1);
    controller.map.addStation(7,"131",-34.59838628851389,-58.37129094944708,1);
    controller.map.addStation(8,"131",-34.59838628851389,-58.37129094944708,1);
    controller.map.addStation(9,"10",-34.59328154388841,-58.37431648121588,1);

    List<BusInPath> result1 = controller.findPath(-34.57548058966485,-58.48892596672341,-34.59838628851389,-58.37129094944708); //hasta echeverria
    Assertions.assertEquals("B", result1.get(0).name);
    Assertions.assertEquals(1, result1.size());

    List<BusInPath> result2 = controller.findPath(-34.589522142255866,-58.38350523026747,-34.60289042752862,-58.36984833413341);
    Assertions.assertEquals("130", result2.get(0).name);
    //Assertions.assertEquals("131", result2.get(1).name);
    Assertions.assertEquals(2, result2.size());
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