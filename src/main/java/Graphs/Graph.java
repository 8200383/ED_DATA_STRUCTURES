package Graphs;

import List.UnorderedArrayList;
import List.UnorderedListADT;
import Queue.LinkedQueue;
import Queue.QueueADT;

import java.util.Arrays;
import java.util.Iterator;

public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected boolean[][] adjMatrix;
    protected T[] vertices;

    /**
     * Creates an empty graph
     */
    public Graph() {
        numVertices = 0;
        adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        vertices = (T[]) new Object[DEFAULT_CAPACITY];
    }


    private void expandCapacity() {
        T[] copyVertices = (T[]) new Object[vertices.length * 2];

        for (int i = 0; i < vertices.length; i++) {
            copyVertices[i] = vertices[i];
        }

        vertices = copyVertices;
    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        int pos = getIndex(vertex);

        if (!indexIsValid(pos)) {
            throw new IllegalArgumentException();
        }

        vertices[pos] = null;

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[pos][i]) {
                adjMatrix[pos][i] = false;
            }

            if (adjMatrix[i][pos]) {
                adjMatrix[i][pos] = false;
            }
        }
        numVertices--;
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    private int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }

        return -1;
    }

    private void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    private boolean indexIsValid(int index) {
        return index != -1;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int pos1 = getIndex(vertex1);
        int pos2 = getIndex(vertex2);

        if (!indexIsValid(pos1) && !indexIsValid(pos2)) {
            throw new IllegalArgumentException("Vertex not found!");
        }

        if (!adjMatrix[pos1][pos2] && !adjMatrix[pos2][pos2]) {
            throw new IllegalArgumentException("Edge not found!");
        }

        adjMatrix[pos1][pos2] = false;
        adjMatrix[pos2][pos1] = false;
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        QueueADT<Integer> traversalQueue = new LinkedQueue<>();
        UnorderedListADT<T> unorderedList = new UnorderedArrayList<>();

        int startIndex = getIndex(startVertex);
        if (!indexIsValid(startIndex)) {
            return unorderedList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            Integer x = traversalQueue.dequeue();
            unorderedList.addToRear(vertices[x]);

            /* Find all vertices adjacent to x that have
             not been visited and queue them up */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        return unorderedList.iterator();
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
        return numVertices == 0;
    }

    @Override
    public boolean isConnected() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] != adjMatrix[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < vertices.length; i++) {
            builder.append("[").append(i).append("] ");
            for (int j = 0; j < vertices.length; j++) {
                builder.append(adjMatrix[i][j]).append(" | ");
            }
            builder.append("\n");
        }

        return "Graph{" +
                "\n DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ",\n numVertices=" + numVertices +
                ",\n vertices=" + Arrays.toString(vertices) +
                ",\n adjMatrix=[\n" + builder + "]" +
                '}';
    }
}
