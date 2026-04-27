public class ListLinked<T> {
    protected Node<T> first; // 'protected' para que SortedListLinked lo vea 

    public ListLinked() {
        this.first = null;
    }

    public boolean isEmptyList() {
        return first == null;
    }

    public Node<T> getFirst() {
        return first;
    }

    public void insertFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = first;
        first = newNode; 
    }

    public int length() {
        int count = 0;
        Node<T> aux = first;
        while (aux != null) {
            count++;
            aux = aux.next;
        }
        return count;
    }

    public void print() {
        Node<T> aux = first;
        while (aux != null) {
            System.out.print(aux.value + " -> ");
            aux = aux.next;
        }
        System.out.println("null");
    }
}
