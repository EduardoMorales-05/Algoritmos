package structures;

public class PriorityQueue<T> {
    private ArrayList<PriorityNode<T>> heap;

    public PriorityQueue() {
        heap = new ArrayList<PriorityNode<T>>();
    }

    public void add(T value, int priority) {
        PriorityNode<T> node = new PriorityNode<T>(value, priority);
        heap.add(node);
        heapifyUp(heap.size() - 1);
    }

    public PriorityNode<T> poll() {
        if (heap.isEmpty()) {
            return null;
        }

        PriorityNode<T> min = heap.get(0);
        PriorityNode<T> last = heap.removeAt(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(parent).getPriority() <= heap.get(index).getPriority()) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int left = (index * 2) + 1;
            int right = (index * 2) + 2;
            int smallest = index;

            if (left < heap.size()
                    && heap.get(left).getPriority() < heap.get(smallest).getPriority()) {
                smallest = left;
            }

            if (right < heap.size()
                    && heap.get(right).getPriority() < heap.get(smallest).getPriority()) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        PriorityNode<T> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
