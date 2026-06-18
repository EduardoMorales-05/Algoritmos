package graph;

public class AdjList<T> {
    private T vertex;
    private structures.LinkedList<Edge<T>> edges;

    public AdjList(T vertex) {
        this.vertex = vertex;
        this.edges = new structures.LinkedList<Edge<T>>();
    }

    public T getVertex() {
        return vertex;
    }

    public structures.LinkedList<Edge<T>> getEdges() {
        return edges;
    }

    public String toString() {
        return vertex + " -> " + edges;
    }
}
