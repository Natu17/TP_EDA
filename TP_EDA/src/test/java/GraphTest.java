import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Graph testGraph = new Graph();

    @Test
    public void graphFiller() {
        testGraph.addNode(0, "A", 0, 0);
        testGraph.addNode(1, "B", 0,1);
        testGraph.addNode(2,"C",0, 2);
        testGraph.addNode(3, "D", 0, 3);
        testGraph.addNode(4,"E", 0, 4);
        testGraph.addNode(5, "F", 0, 5);
        testGraph.addNode(6, "G", 0, 6);
        testGraph.addNode(7, "H", 0, 6);

        testGraph.addEdge(0, 1, 2);
        testGraph.addEdge(0, 2, 4);
        testGraph.addEdge(1, 3, 6);
        testGraph.addEdge(2, 3, 2);
        testGraph.addEdge(2, 5, 1);
        testGraph.addEdge(3, 4, 1);
        testGraph.addEdge(5, 4, 4);
        testGraph.addEdge(4, 6, 2);
        testGraph.addEdge(6, 7, 2);
    }

    @Test
    public void testSmallerDistances() {
        graphFiller();
        List<String> myAnswer = testGraph.answer(0, 6);
        System.out.println(myAnswer.toArray());
        for (String s : myAnswer) {
            System.out.println(s);
        }
    }
}