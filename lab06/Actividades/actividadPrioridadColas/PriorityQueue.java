package lab06.Actividades.actividadPrioridadColas;

public class PriorityQueue<E> 
{
    private QueueLink<E>[] queues;
    private int levels;

    @SuppressWarnings("unchecked")
    public PriorityQueue(int levels) 
    {
        this.levels = levels;
        queues = new QueueLink[levels];

        for (int i = 0; i < levels; i++) 
        {
            queues[i] = new QueueLink<>();
        }
    }

    public void enqueue(E x, int priority) 
    {
        if (priority < 0 || priority >= levels) 
        {
            System.out.println("Prioridad inválida");
            return;
        }

        queues[priority].enqueue(x);
    }

    public E dequeue() 
    {
        for (int i = levels - 1; i >= 0; i--) 
        {
            if (!queues[i].isEmpty()) 
            {
                return queues[i].dequeue();
            }
        }

        System.out.println("Cola vacía");
        return null;
    }

    public boolean isEmpty() 
    {
        for (int i = 0; i < levels; i++) 
        {
            if (!queues[i].isEmpty()) return false;
        }
        return true;
    }
}
