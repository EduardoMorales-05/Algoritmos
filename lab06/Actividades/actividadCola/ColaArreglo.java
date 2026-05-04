package lab06.Actividades.actividadCola;

public class ColaArreglo 
{
    private int[] array;
    private int front;
    private int rear;
    private int size;

    public ColaArreglo(int capacidad) 
    {
        array = new int[capacidad];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() 
    {
        return size == 0;
    }

    public boolean isFull() 
    {
        return size == array.length;
    }

    public void enqueue(int x) 
    {
        if (isFull()) 
        {
            System.out.println("Cola llena");
            return;
        }

        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }

    public int dequeue() 
    {
        if (isEmpty()) 
        {
            System.out.println("Cola vacía");
            return -1;
        }

        int dato = array[front];
        front = (front + 1) % array.length;
        size--;

        return dato;
    }

    public int front() 
    {
        if (isEmpty()) 
        {
            System.out.println("Cola vacía");
            return -1;
        }

        return array[front];
    }
}
