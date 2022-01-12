package Demos;

import Graphs.LinkedGraph;

import java.util.Arrays;

public class GraphDemo {

    public static void main(String[] args) {
        /*
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        System.out.println(graph);
        graph.removeEdge(1, 2);
        System.out.println(graph); */

        LinkedGraph<Integer> linkedGraph = new LinkedGraph<>();
        linkedGraph.addVertex(1);
        linkedGraph.addVertex(2);
        linkedGraph.addEdge(1, 2);
        System.out.println(Arrays.toString(linkedGraph.vertices));
    }
}
