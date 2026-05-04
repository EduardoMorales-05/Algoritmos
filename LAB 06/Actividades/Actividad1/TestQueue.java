package actividad1;

public class TestQueue {
    public static void main(String[] args) {
        // Prueba con Integers
        System.out.println("--- Prueba con Integers ---");
        Queue<Integer> qInt = new QueueArray<>(3);
        try {
            qInt.enqueue(10);
            qInt.enqueue(20);
            qInt.enqueue(30);
            qInt.enqueue(40); // Debería avisar que está llena
            
            System.out.println("Atendiendo: " + qInt.dequeue());
            qInt.enqueue(40); // Ahora sí entra gracias al arreglo circular
            System.out.println("Nuevo frente: " + qInt.front());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        // Prueba con Strings
        System.out.println("\n--- Prueba con Strings ---");
        Queue<String> qStr = new QueueArray<>(2);
        try {
            qStr.enqueue("Sistemas");
            qStr.enqueue("UCSM");
            System.out.println("Salió: " + qStr.dequeue());
            System.out.println("Queda: " + qStr.front());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
