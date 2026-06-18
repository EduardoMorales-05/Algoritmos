package structures;

public class ArrayList<T> {
    private Object[] data;
    private int size;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            capacity = 10;
        }
        data = new Object[capacity];
        size = 0;
    }

    public void add(T value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            return;
        }

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return (T) data[index];
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            return;
        }

        data[index] = value;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        T removed = (T) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;

        return removed;
    }

    public boolean remove(T value) {
        int index = indexOf(value);

        if (index == -1) {
            return false;
        }

        removeAt(index);
        return true;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (value == null && data[i] == null) {
                return i;
            }

            if (value != null && value.equals(data[i])) {
                return i;
            }
        }

        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size < data.length) {
            return;
        }

        Object[] newData = new Object[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(data[i]);

            if (i < size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
