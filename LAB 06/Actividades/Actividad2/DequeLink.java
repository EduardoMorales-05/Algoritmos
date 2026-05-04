package actividad2;

import actividad1.ExceptionIsEmpty;

public class DequeLink<E> implements Deque<E> {
    private Node<E> first;
    private Node<E> last;

    public DequeLink() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void addFirst(E x) {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = last = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
    }

    @Override
    public void addLast(E x) {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }

    @Override
    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Bicola vacía");
        
        E data = first.getData();
        first = first.getNext();
        
        if (first == null) {
            last = null;
        }
        return data;
    }

    @Override
    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Bicola vacía");
        
        E data = last.getData();
        
        if (first == last) {
            first = last = null;
        } else {
            // Recorrido para encontrar el penúltimo nodo
            Node<E> current = first;
            while (current.getNext() != last) {
                current = current.getNext();
            }
            current.setNext(null);
            last = current;
        }
        return data;
    }

    @Override
    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Bicola vacía");
        return first.getData();
    }

    @Override
    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Bicola vacía");
        return last.getData();
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.getData()).append(" ");
            current = current.getNext();
        }
        return sb.append("]").toString();
    }
}
