package graph;

public interface Graph<T> {
    boolean insertVertex(T vertex);

    boolean insertEdge(T origin, T destination);

    boolean insertEdgeWeight(T origin, T destination, int weight);

    boolean removeVertex(T vertex);

    boolean removeEdge(T origin, T destination);

    boolean searchVertex(T vertex);

    boolean searchEdge(T origin, T destination);

    structures.ArrayList<T> adjacentVertices(T vertex);
}
