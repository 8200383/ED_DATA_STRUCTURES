package Demos;

import Graphs.Graph;

public class GraphDemo {

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        System.out.println(graph);
        graph.removeVertex(2);
        System.out.println(graph);
    }
}
