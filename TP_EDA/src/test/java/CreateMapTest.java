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
        map.addStation(5,"107", -34.57804933193641,-58.49285325522813,1);
        map.addStation(6,"107",-34.57953109349971,-58.49048693304797,0);
        map.addStation(7,"107",-34.58170350348671,-58.49892568065003,1);
        map.addStation(8,"B",-34.60294005384535,-58.36938661922946,-1);//ultima parada del B (a mas de 500 m a la redonda)
        map.addStation(9,"107",-34.59857497382793,-58.51108875625667,1);

        map.addStation(10,"AFUERA",-34.45187918186737,-59.01614428431844,1); //agrego una station por fuera del cuadrado

        map.addWalkingStation(-1, -34.575047441959605, -58.48720913923434, -3);
        map.addWalkingStation(-2,-34.57790326089744, -58.479532432890885, -4);



        List<BusInPath> result1 = map.graph.answer(-1, -2); //hasta echeverria
        Assertions.assertEquals("B", result1.get(0).name);
        Assertions.assertEquals(1, result1.size());

        map.deleteStartEnd();
        map.addWalkingStation(-1, -34.60277668050798, -58.36770755638614, -3);
        map.addWalkingStation(-2,-34.59957292130688, -58.51012316101131, -4);
        List<BusInPath> result2 = map.graph.answer(-1, -2); //desde el itba hasta la plaza devoto
        Assertions.assertEquals("B", result2.get(0).name);
        Assertions.assertEquals("107", result2.get(1).name);
        Assertions.assertEquals(2, result2.size());


        map.deleteStartEnd();
        map.addWalkingStation(-1, -34.57756163015424,-58.4919225383002, -3);
        map.addWalkingStation(-2,-34.57514116649583, -58.48827473403995, -4);
        List<BusInPath> result3 = map.graph.answer(-1, -2); //desde el itba hasta la plaza devoto
        Assertions.assertEquals(0, result3.size()); // mejor caminar


        map.deleteStartEnd();
        map.addWalkingStation(-1, -34.554944332607654,-58.43693015875787, -3);
        map.addWalkingStation(-2,-34.579611018734546, -58.47787139716119, -4);
        List<BusInPath> result4 = map.graph.answer(-1, -2); //desde el itba hasta la plaza devoto
        Assertions.assertEquals(0, result4.size());//no hay ningún medio de transporte cercano
            }


}