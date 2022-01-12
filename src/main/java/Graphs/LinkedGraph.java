package Graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedGraph<T> implements GraphADT<T> {

    private static final int DEFAULT_CAPACITY = 10;
    public Vertex<T>[] vertices;
    protected int numVertices;

    /**
     * Creates an empty graph
     */
    public LinkedGraph() {
        numVertices = 0;
        vertices = new Vertex[DEFAULT_CAPACITY];
    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandArray();
        }

        vertices[numVertices] = new Vertex<>(vertex);
        numVertices++;
    }

    private void expandArray() {
        Vertex<T>[] copyVertices = new Vertex[vertices.length * 2];

        System.arraycopy(vertices, 0, copyVertices, 0, vertices.length);

        vertices = copyVertices;
    }

    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].element == vertex) {

                System.arraycopy(vertices, i, vertices, i + 1, numVertices);
                numVertices--;
                return;
            }
        }
    }

    private void addEdgeToRear(Vertex<T> adjacencyList, T vertex) {
        Vertex<T> head = adjacencyList;
        while (head.next != null) {
            head = head.next;
        }

        head.next = new Vertex<>(vertex);
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        for (Vertex<T> vertex : vertices) {
            if (vertex == null) {
                continue;
            }

            /* Creating bidirectional vertex */
            if (vertex.element == vertex1) {
                addEdgeToRear(vertex, vertex2);
            } else if (vertex.element == vertex2) {
                addEdgeToRear(vertex, vertex1);
            }
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {

    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        return null;
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return null;
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    private static class Vertex<T> {
        private final T element;
        private Vertex<T> next;

        public Vertex(T element) {
            this.element = element;
            this.next = null;
        }

        @Override
        public String toString() {
            List<T> edges = new LinkedList<>();

            Vertex<T> tmp = this.next;
            while (tmp != null) {
                edges.add(tmp.element);
                tmp = tmp.next;
            }

            return "Vertex{" +
                    "element=" + element +
                    ", edges=" + edges +
                    '}';
        }
    }
}
