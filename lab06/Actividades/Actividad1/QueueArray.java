package actividad1;

/**
 * Implementación de cola usando un arreglo circular.
 * Evita el desplazamiento de elementos, mejorando el rendimiento.
 */
public class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public QueueArray(int n) {
        this.array = (E[]) new Object[n];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(E x) {
        if (isFull()) {
            System.out.println("Capacidad alcanzada: No se puede encolar " + x);
            return;
        }
        // Incremento circular del índice final
        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("La cola no tiene elementos.");
        
        E element = array[front];
        array[front] = null; // Ayuda al recolector de basura 
        // Incremento circular del índice frontal
        front = (front + 1) % array.length;
        size--;
        return element;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía.");
        return array[front];
    }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean isFull() { return size == array.length; }
}
