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





        map.addWalkingStation(-1, -34.575047441959605, -58.48720913923434, -3);
        map.addWalkingStation(-2,-34.57790326089744, -58.479532432890885, -4);



        List<BusInPath> result1 = map.graph.answer(-1, -2); //hasta echeverria
        Assertions.assertEquals("B", result1.get(0).name);
        Assertions.assertEquals(1, result1.size());

        map.addWalkingStation(-1, -34.575047441959605, -58.48720913923434, -3);
        map.addWalkingStation(-2,-34.57790326089744, -58.479532432890885, -4);

        map.deleteStartEnd();
        map.addWalkingStation(-1, -34.589522142255866, -58.38350523026747, -3);
        map.addWalkingStation(-2,-34.60289042752862, -58.36984833413341, -4);


            }


}