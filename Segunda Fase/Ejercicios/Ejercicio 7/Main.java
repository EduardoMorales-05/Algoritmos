public class Main {
    public static void main(String[] args) {
        SortedListLinked<Tarea> gestor = new SortedListLinked<>();
        
        // Insertamos tareas en desorden de prioridad 
        gestor.insertOrden(new Tarea("Algoritmos", 3, "pendiente"));
        gestor.insertOrden(new Tarea("Estadistiaca", 1, "pendiente"));
        gestor.insertOrden(new Tarea("SIA", 2, "completada"));

        System.out.println("=== LISTA DE TAREAS ORDENADA POR PRIORIDAD ===");
        gestor.print(); // Se mostrará automáticamente en orden 1, 2, 3 
    }
}
