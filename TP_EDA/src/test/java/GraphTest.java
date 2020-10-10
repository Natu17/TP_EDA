import javafx.util.Pair;
import model.BusInPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    Graph testGraph1 = new Graph();
    Graph testGraph2 = new Graph();
    Graph testGraph3 = new Graph();

    public void graphFiller1() {
        testGraph1.addNode(new Node(0, new Pair<>("start", 0), 0, 0, new HashSet<>()));
        testGraph1.addNode(new Node(1, new Pair<>("A", 0), 1, 0, new HashSet<>()));
        testGraph1.addNode(new Node(2, new Pair<>("A", 0), 2, 0, new HashSet<>()));
        testGraph1.addNode(new Node(3, new Pair<>("B", 0), 3, 0, new HashSet<>()));
        testGraph1.addNode(new Node(4, new Pair<>("B", 0), 4, 0, new HashSet<>()));
        testGraph1.addNode(new Node(5, new Pair<>("B", 0), 5, 0, new HashSet<>()));
        testGraph1.addNode(new Node(6, new Pair<>("End", 0), 6, 0, new HashSet<>()));

        testGraph1.addEdge(0, 1, 2);
        testGraph1.addEdge(0, 3, 1);
        testGraph1.addEdge(1, 2, 2);
        testGraph1.addEdge(2, 6, 2);
        testGraph1.addEdge(3, 4, 1);
        testGraph1.addEdge(4, 5, 1);
        testGraph1.addEdge(5, 6, 1);

    }

    public void graphFiller2() {
        testGraph2.addNode(new Node(0, new Pair<>("start", 0), 0, 0, new HashSet<>()));
        testGraph2.addNode(new Node(1,new Pair<>("A", 0), 1, 0, new HashSet<>()));
        testGraph2.addNode(new Node(2,new Pair<>("A", 0), 2, 0, new HashSet<>()));
        testGraph2.addNode(new Node(3,new Pair<>("B", 0), 3, 0, new HashSet<>()));
        testGraph2.addNode(new Node(4,  new Pair<>("B", 0), 4, 0, new HashSet<>()));
        testGraph2.addNode(new Node(5, new Pair<>("B", 0), 5, 0, new HashSet<>()));
        testGraph2.addNode(new Node(6, new Pair<>("End", 0), 6, 0, new HashSet<>()));

        testGraph2.addEdge(0, 1, 2);
        testGraph2.addEdge(1, 2, 1);
        testGraph2.addEdge(2, 3, 2);
        testGraph2.addEdge(3, 4, 2);
        testGraph2.addEdge(4, 5, 1);
        testGraph2.addEdge(5, 6, 1);
    }

    public void graphFiller3() {
        testGraph3.addNode(new Node(0, new Pair<>("start", 0), 0, 0, new HashSet<>()));
        testGraph3.addNode(new Node(1,new Pair<>("A", 0), 1, 0, new HashSet<>()));
        testGraph3.addNode(new Node(2,new Pair<>("A", 0), 2, 0, new HashSet<>()));
        testGraph3.addNode(new Node(3,new Pair<>("A", 0), 3, 0, new HashSet<>()));
        testGraph3.addNode(new Node(4,  new Pair<>("B", 0), 4, 0, new HashSet<>()));
        testGraph3.addNode(new Node(5, new Pair<>("C", 0), 5, 0, new HashSet<>()));
        testGraph3.addNode(new Node(6, new Pair<>("C", 0), 6, 0, new HashSet<>()));
        testGraph3.addNode(new Node(7, new Pair<>("End", 0), 7, 0, new HashSet<>()));

        testGraph3.addEdge(0, 1, 2);
        testGraph3.addEdge(1, 2, 1);
        testGraph3.addEdge(2, 3, 2);
        testGraph3.addEdge(3, 4, 2);
        testGraph3.addEdge(4, 5, 1);
        testGraph3.addEdge(5, 6, 1);
        testGraph3.addEdge(6, 7, 1);
    }

    //Analizo si toma el camino más corto y si elimina las estaciones del medio de un misma linea
    @Test
    public void testSmallerDistances1() {
        graphFiller1();
        List<BusInPath> myAnswer = testGraph1.answer(0, 6);
        Assertions.assertEquals("B", myAnswer.get(0).name);
    }

    //Analizo si marca bien a los parents cuando toma más de una línea y sisaltea las paradas innecesarias
    @Test
    public void testSmallerDistances2() {
        graphFiller2();
        List<BusInPath> myAnswer = testGraph2.answer(0, 6);
        Assertions.assertEquals("A", myAnswer.get(0).name);
        Assertions.assertEquals("B", myAnswer.get(1).name);
    }

    //Analizo si saltea los nodos únicos (ya que estos nodos no nos interesan)
    @Test
    public void testSmallerDistances3() {
        graphFiller3();
        List<BusInPath> myAnswer = testGraph3.answer(0, 7);
        for (BusInPath busInPath : myAnswer) {
            System.out.println(busInPath.name);
        }
        Assertions.assertEquals("A", myAnswer.get(0).name);
        Assertions.assertEquals("C", myAnswer.get(1).name);
    }
}