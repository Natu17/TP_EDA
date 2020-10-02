import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph testGraph1 = new Graph();
    Graph testGraph2 = new Graph();

    public void graphFiller1() {
        testGraph1.addNode(0, "A", 0, 0);
        testGraph1.addNode(1, "B", 0,1);
        testGraph1.addNode(2,"C",0, 2);
        testGraph1.addNode(3, "D", 0, 3);
        testGraph1.addNode(4,"E", 0, 4);
        testGraph1.addNode(5, "F", 0, 5);
        testGraph1.addNode(6, "G", 0, 6);
        testGraph1.addNode(7, "H", 0, 6);

        testGraph1.addEdge(0, 1, 2);
        testGraph1.addEdge(0, 2, 4);
        testGraph1.addEdge(1, 3, 6);
        testGraph1.addEdge(2, 3, 2);
        testGraph1.addEdge(2, 5, 1);
        testGraph1.addEdge(3, 4, 1);
        testGraph1.addEdge(5, 4, 4);
        testGraph1.addEdge(4, 6, 2);
        testGraph1.addEdge(6, 7, 2);
    }

    public void graphFiller2() {
        testGraph2.addNode(0, "A", 0, 0);
        testGraph2.addNode(1, "B", 0,1);
        testGraph2.addNode(2,"C",0, 2);
        testGraph2.addNode(3, "G", 0, 3);
        testGraph2.addNode(4,"G", 0, 4);
        testGraph2.addNode(5, "F", 0, 5);
        testGraph2.addNode(6, "G", 0, 6);
        testGraph2.addNode(7, "H", 0, 6);

        testGraph2.addEdge(0, 1, 2);
        testGraph2.addEdge(0, 2, 4);
        testGraph2.addEdge(1, 3, 6);
        testGraph2.addEdge(2, 3, 2);
        testGraph2.addEdge(2, 5, 1);
        testGraph2.addEdge(3, 4, 1);
        testGraph2.addEdge(5, 4, 4);
        testGraph2.addEdge(4, 6, 2);
        testGraph2.addEdge(6, 7, 2);
    }

    @Test
    public void testSmallerDistances1() {
        graphFiller1();
        List<String> myAnswer = testGraph1.answer(0, 6);
        System.out.println(Arrays.toString(myAnswer.toArray()));
        for (String s : myAnswer) {
            System.out.println(s);
        }
    }

    @Test
    public void testSmallerDistances2() {
        graphFiller2();
        List<String> myAnswer = testGraph2.answer(0, 6);
        System.out.println(Arrays.toString(myAnswer.toArray()));
        for (String s : myAnswer) {
            System.out.println(s);
        }
    }
}