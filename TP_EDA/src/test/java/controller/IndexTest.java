package controller;

import model.IndexService;
import model.IndexWithDuplicates;
import model.PlaceLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexTest {
    IndexService<String> index = new IndexWithDuplicates<String>();
    PlaceLocation placeLocation = new PlaceLocation("COLEGIO", 34.24334, 54.3435);
    List<String> expected = new ArrayList<>();
    @Test
    void reversedOrderListLev(){
        index.insert("match");
        index.insert("matc");
        index.insert("jekkd");
        index.insert("manjk");
        expected.add("match");
        expected.add("matc");
        Comparable [] i = index.index();
        String[] in = new String[i.length];
        int h = 0;
        for(Comparable s : i){
            in[h] = (String) s;
            h++;
        }



        List<String> list = placeLocation.levenshtein("match", in);
        for(String s : list){
            System.out.println(s);
        }
        //Assertions.assertEquals(expected.get(0), placeLocation.levenshtein("match", index.idex()));
        //Assertions.assertEquals(expected.get(1), placeLocation.levenshtein("match", index.idex()).get(1));
        /*
         */
    }
}
