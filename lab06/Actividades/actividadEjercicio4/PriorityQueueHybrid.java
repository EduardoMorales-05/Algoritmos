package lab06.Actividades.actividadEjercicio4;

public class PriorityQueueHybrid<E> 
{
    private OrderedQueue<E>[] queues;
    private int levels;

    @SuppressWarnings("unchecked")
    public PriorityQueueHybrid(int levels) 
    {
        this.levels = levels;
        queues = new OrderedQueue[levels];

        for (int i = 0; i < levels; i++) 
        {
            queues[i] = new OrderedQueue<>();
        }
    }

    public void enqueue(E data, int priority, int value) 
    {
        if (priority < 0 || priority >= levels) 
        {
            System.out.println("Prioridad inválida");
            return;
        }

        queues[priority].enqueue(data, value);
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
