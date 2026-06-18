package structures;

public class Stack<T> {
    private LinkedList<T> list;

    public Stack() {
        list = new LinkedList<T>();
    }

    public void push(T value) {
        list.addFirst(value);
    }

    public T pop() {
        return list.removeFirst();
    }

    public T peek() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
