package actividad3;

import actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

        System.out.println("Insertando elementos con distintas prioridades...");
        pq.enqueue("Proceso C", 1);
        pq.enqueue("Proceso A", 5);
        pq.enqueue("Proceso B", 3);

        System.out.println("Estado actual: " + pq.toString());

        try {
            System.out.println("Frente (Mayor prioridad): " + pq.front());
            System.out.println("Final (Menor prioridad): " + pq.back());
            
            System.out.println("Extrayendo: " + pq.dequeue());
            System.out.println("Nuevo frente: " + pq.front());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
```</E,></E></E,>
