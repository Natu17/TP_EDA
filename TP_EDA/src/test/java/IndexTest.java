import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IndexTest {
    Index lv = new Index();
    @Test
    void correctDistance() {
        Assertions.assertEquals(1, lv.distance("pepe", "pope"));
        Assertions.assertEquals(2,  lv.distance("smart", "art"));
        Assertions.assertEquals(0,  lv.distance("machine", "machine"));

        Assertions.assertEquals(1, lv.similarytyWhite("yenny", "yenny"));
        Assertions.assertEquals(0.10775343636417861, lv.similarytyWhite("big data", "bigdata"));
        Assertions.assertEquals(0.3333347687693427, lv.similarytyWhite("Yenny", "Yenny (abasto)"));
        Assertions.assertEquals(1, lv.similarytyWhite("pepe", "pepe"));
        Assertions.assertEquals(0.0014518154494743858,lv.similarytyWhite("Yenny", "Leon Leon"));

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

}
