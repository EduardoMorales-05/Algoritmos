package lab06.Actividades.actividadEjercicio4;

public class Node<E> 
{
    E data;
    int priorityValue; // valor para ordenar
    Node<E> next;

    public Node(E data, int value) 
    {
        this.data = data;
        this.priorityValue = value;
        this.next = null;
    }
}
