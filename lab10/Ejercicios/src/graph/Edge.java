package graph;

public class Edge<T> {
    private T origin;
    private T destination;
    private int weight;

    public Edge(T origin, T destination) {
        this(origin, destination, 1);
    }

    public Edge(T origin, T destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public T getOrigin() {
        return origin;
    }

    public T getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString() {
        return origin + " -> " + destination + " (" + weight + ")";
    }
}
