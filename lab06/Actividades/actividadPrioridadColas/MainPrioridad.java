package lab06.Actividades.actividadPrioridadColas;

public class MainPrioridad 
{
    public static void main(String[] args) 
    {
        PriorityQueue<String> pq = new PriorityQueue<>(3);

        pq.enqueue("A", 0);
        pq.enqueue("B", 2);
        pq.enqueue("C", 1);
        pq.enqueue("D", 2);

        while (!pq.isEmpty()) 
        {
            System.out.println(pq.dequeue());
        }
    }
}
