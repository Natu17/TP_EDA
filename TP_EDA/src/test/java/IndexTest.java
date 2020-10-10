import model.PlaceLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IndexTest {
    Index lv = new Index();
    @Test
    void correctDistance() {
        Assertions.assertEquals(1, lv.distance("pepe", "pope"));
        Assertions.assertEquals(2,  lv.distance("smart", "art"));
        Assertions.assertEquals(0,  lv.distance("machine", "machine"));

        Assertions.assertEquals(1, lv.similarityWhite("yenny", "yenny"));
        Assertions.assertEquals(0.0551324374886737, lv.similarityWhite("big data", "bigdata"));
        Assertions.assertEquals(0.3333335183766464, lv.similarityWhite("Yenny", "Yenny (abasto)"));
        Assertions.assertEquals(1, lv.similarityWhite("pepe", "pepe"));
        Assertions.assertEquals(0.005349617621108706,lv.similarityWhite("Yenn", "Leon Leon"));

        Assertions.assertEquals(0.6,  lv.normalizedSimilarity("smart", "art"));
        Assertions.assertEquals(0.8, lv.normalizedSimilarity("match", "matc"));
        Assertions.assertEquals(0.4, lv.normalizedSimilarity("match", "manjk"));
        Assertions.assertEquals(0.19999999999999996, lv.normalizedSimilarity("match", "mist"));

    }

    @Test
    void symmetry() {
        Assertions.assertTrue( lv.distance("estructura", "caricatura") ==  lv.distance("caricatura", "estructura"));
        Assertions.assertTrue( lv.distance("java", "ruby") ==  lv.distance("ruby", "java"));
    }

    @Test
    void inequality() {
        String str1 = "algoritmos", str2 = "arquitectura", str3 = "itba";
        Assertions.assertTrue(( lv.distance(str1, str2) +  lv.distance(str2, str3)) >=  lv.distance(str1, str3));
    }

    @Test
    void firstResultCorrect(){
        lv.put("hospital aleman", new PlaceLocation("hospital aleman", -34.591806, -58.402139));
        lv.put("hospital alemania", new PlaceLocation("hospital alemania", -34.6543773, -58.5324));
        lv.put("hospital italiano", new PlaceLocation("hospital italiano", -34.606583, -58.425));
        lv.put("hospital italienne", new PlaceLocation("hospital italienne", -34.604545, -58.44357));
        lv.put("hospital español", new PlaceLocation("hospital español", -34.654764, -58.32525));
        lv.put("hospital españa", new PlaceLocation("hospital españa", -34.654567, -58.323));

        List<PlaceLocation> result = lv.search("hospital italiano");

        Assertions.assertEquals("hospital italiano", result.get(0).getName());
        Assertions.assertEquals("hospital italienne", result.get(1).getName());

        result = lv.search("hospital aleman");

        Assertions.assertEquals("hospital aleman", result.get(0).getName());
        Assertions.assertEquals("hospital alemania", result.get(1).getName());

        result = lv.search("hospital español");

        Assertions.assertEquals("hospital español", result.get(0).getName());
        Assertions.assertEquals("hospital españa", result.get(1).getName());

        //Busco un lugar mal escrito
        result = lv.search("hospital espanol");

        Assertions.assertEquals("hospital español", result.get(0).getName());
        Assertions.assertEquals("hospital españa", result.get(1).getName());
    }

}
