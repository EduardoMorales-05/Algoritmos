package lab06.Actividades.actividadEjercicio4;

public class OrderedQueue<E> 
{
    private Node<E> head;

    public OrderedQueue() 
    {
        head = null;
    }

    public boolean isEmpty() 
    {
        return head == null;
    }

    public void enqueue(E data, int value) 
    {
        Node<E> nuevo = new Node<>(data, value);

        // insertar al inicio si está vacía o es menor
        if (head == null || value < head.priorityValue) 
        {
            nuevo.next = head;
            head = nuevo;
        } 
        else 
        {
            Node<E> actual = head;

            while (actual.next != null && actual.next.priorityValue <= value) 
            {
                actual = actual.next;
            }

            nuevo.next = actual.next;
            actual.next = nuevo;
        }
    }

    public E dequeue() 
    {
        if (isEmpty()) return null;

        E dato = head.data;
        head = head.next;
        return dato;
    }
}
