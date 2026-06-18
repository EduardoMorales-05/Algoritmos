package structures;

public class LinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<T>(value);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }

        size++;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<T>(value);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }

        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> current = first;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T value = first.getData();
        first = first.getNext();
        size--;

        if (size == 0) {
            last = null;
        }

        return value;
    }

    public boolean remove(T value) {
        if (isEmpty()) {
            return false;
        }

        if (equalsValue(first.getData(), value)) {
            removeFirst();
            return true;
        }

        Node<T> previous = first;
        Node<T> current = first.getNext();

        while (current != null) {
            if (equalsValue(current.getData(), value)) {
                previous.setNext(current.getNext());

                if (current == last) {
                    last = previous;
                }

                size--;
                return true;
            }

            previous = current;
            current = current.getNext();
        }

        return false;
    }

    public boolean contains(T value) {
        Node<T> current = first;

        while (current != null) {
            if (equalsValue(current.getData(), value)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> result = new ArrayList<T>();
        Node<T> current = first;

        while (current != null) {
            result.add(current.getData());
            current = current.getNext();
        }

        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = first;

        while (current != null) {
            sb.append(current.getData());

            if (current.getNext() != null) {
                sb.append(" ");
            }

            current = current.getNext();
        }

        return sb.toString();
    }
}
