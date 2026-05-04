package lab06.Actividades.actividadPrioridadColas;

public class QueueLink<E> 
{
    private Node<E> front;
    private Node<E> rear;

    public QueueLink() 
    {
        front = rear = null;
    }

    public boolean isEmpty() 
    {
        return front == null;
    }

    public void enqueue(E x) 
    {
        Node<E> nuevo = new Node<>(x);

        if (isEmpty()) 
        {
            front = rear = nuevo;
        } 
        else 
        {
            rear.setNext(nuevo);
            rear = nuevo;
        }
    }

    public E dequeue() 
    {
        if (isEmpty()) return null;

        E dato = front.getData();
        front = front.getNext();

        if (front == null) rear = null;

        return dato;
    }
}
