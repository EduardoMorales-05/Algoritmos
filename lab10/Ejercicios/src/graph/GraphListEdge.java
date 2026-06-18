package graph;

public class GraphListEdge<T> {
    private boolean directed;
    private structures.ArrayList<T> vertices;
    private structures.ArrayList<Edge<T>> edges;

    public GraphListEdge() {
        this(false);
    }

    public GraphListEdge(boolean directed) {
        this.directed = directed;
        vertices = new structures.ArrayList<T>();
        edges = new structures.ArrayList<Edge<T>>();
    }

    public boolean insertVertex(T vertex) {
        if (searchVertex(vertex)) {
            return false;
        }

        vertices.add(vertex);
        return true;
    }

    public boolean insertEdge(T origin, T destination) {
        return insertEdgeWeight(origin, destination, 1);
    }

    public boolean insertEdgeWeight(T origin, T destination, int weight) {
        if (!searchVertex(origin)) {
            insertVertex(origin);
        }

        if (!searchVertex(destination)) {
            insertVertex(destination);
        }

        if (!searchEdge(origin, destination)) {
            edges.add(new Edge<T>(origin, destination, weight));
        }

        return true;
    }

    public boolean searchVertex(T vertex) {
        for (int i = 0; i < vertices.size(); i++) {
            if (equalsValue(vertices.get(i), vertex)) {
                return true;
            }
        }

        return false;
    }

    public boolean searchEdge(T origin, T destination) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);

            boolean normal = equalsValue(edge.getOrigin(), origin)
                    && equalsValue(edge.getDestination(), destination);

            boolean reverse = !directed
                    && equalsValue(edge.getOrigin(), destination)
                    && equalsValue(edge.getDestination(), origin);

            if (normal || reverse) {
                return true;
            }
        }

        return false;
    }

    public structures.ArrayList<T> adjacentVertices(T vertex) {
        structures.ArrayList<T> result = new structures.ArrayList<T>();

        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);

            if (equalsValue(edge.getOrigin(), vertex)) {
                result.add(edge.getDestination());
            }

            if (!directed && equalsValue(edge.getDestination(), vertex)) {
                result.add(edge.getOrigin());
            }
        }

        return result;
    }

    public GraphLink<T> toGraphLink() {
        GraphLink<T> graph = new GraphLink<T>(directed);

        for (int i = 0; i < vertices.size(); i++) {
            graph.insertVertex(vertices.get(i));
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);
            graph.insertEdgeWeight(edge.getOrigin(), edge.getDestination(), edge.getWeight());
        }

        return graph;
    }

    private boolean equalsValue(T a, T b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.equals(b);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < edges.size(); i++) {
            sb.append(edges.get(i)).append("\n");
        }

        return sb.toString();
    }
}
