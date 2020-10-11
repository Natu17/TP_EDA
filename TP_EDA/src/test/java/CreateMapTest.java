import model.BusInPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateMapTest {
    public CreateMap map = new CreateMap();

    @Test
    void testFindPath() {
        map.addStation(1,"B",-34.57457314259079,-58.48760329603265,-1); //agrego la parada del b "juan manuel de rosas"
        map.addStation(2,"B",-34.578172918809614,-58.48024331449579,-1);//ECHEVERRIA
        map.addStation(3,"B",-34.58167979014384,-58.47294770597528,-1); //los incas
        map.addStation(4,"107",-34.57488194679862,-58.48744619910095,1); //parada del 107 al lado de la primera del subte


        map.addStation(7,"130",-34.58829487351996,-58.38286818192071 ,1);
        map.addStation(8,"130",-34.59018501549182,-58.379563700414366,1);
        map.addStation(9,"131",-34.59838628851389,-58.37129094944708,1);
        map.addStation(10,"131",-34.59838628851389,-58.37129094944708,1);
        map.addStation(11,"10",-34.59328154388841,-58.37431648121588,1);

        map.addWalkingStation(-1, -34.57548058966485, -58.48892596672341, -3);
        map.addWalkingStation(-2,-34.59838628851389, -58.37129094944708, -4);

        List<BusInPath> result1 = map.graph.answer(-1, -2); //hasta echeverria
        Assertions.assertEquals("B", result1.get(0).name);
        Assertions.assertEquals(1, result1.size());

        //List<BusInPath> result2 = controller.findPath(-34.589522142255866,-58.38350523026747,-34.60289042752862,-58.36984833413341);
        //Assertions.assertEquals("130", result2.get(0).name);
        //Assertions.assertEquals("131", result2.get(1).name);
        //Assertions.assertEquals(2, result2.size());
            }
}