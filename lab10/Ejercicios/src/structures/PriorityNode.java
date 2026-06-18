package structures;

public class PriorityNode<T> {
    private T data;
    private int priority;

    public PriorityNode(T data, int priority) {
        this.data = data;
        this.priority = priority;
    }

    public T getData() {
        return data;
    }

    public int getPriority() {
        return priority;
    }

    public String toString() {
        return data + "(" + priority + ")";
    }
}
