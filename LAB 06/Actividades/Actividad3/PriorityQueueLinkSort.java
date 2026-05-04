package actividad3;

import actividad1.ExceptionIsEmpty;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    
    class EntryNode {
        E data;
        N priority;
        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void enqueue(E x, N pr) {
        EntryNode newEntry = new EntryNode(x, pr);
        Node<EntryNode> newNode = new Node<>(newEntry);

        if (isEmpty() || pr.compareTo(first.getData().priority) > 0) {
            newNode.setNext(first);
            first = newNode;
            if (last == null) last = newNode;
        } else {
            Node<EntryNode> aux = first;
            while (aux.getNext() != null && aux.getNext().getData().priority.compareTo(pr) >= 0) {
                aux = aux.getNext();
            }
            newNode.setNext(aux.getNext());
            aux.setNext(newNode);
            if (newNode.getNext() == null) last = newNode;
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        E data = first.getData().data;
        first = first.getNext();
        if (first == null) last = null;
        return data;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        return first.getData().data;
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía");
        return last.getData().data;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        String res = "Cola Prioridad: ";
        Node<EntryNode> aux = first;
        while (aux != null) {
            res += "[" + aux.getData().data + ", P:" + aux.getData().priority + "] ";
            aux = aux.getNext();
        }
        return res;
    }
}
