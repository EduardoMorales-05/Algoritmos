package ejercicios;

public class Ejercicio1Test {
    public static void main(String[] args) {
        graph.GraphLink<String> g = new graph.GraphLink<String>(false);

        g.insertEdgeWeight("A", "B", 4);
        g.insertEdgeWeight("A", "C", 2);
        g.insertEdgeWeight("C", "B", 1);
        g.insertEdgeWeight("B", "D", 5);
        g.insertEdgeWeight("C", "D", 8);
        g.insertEdgeWeight("D", "E", 6);

        System.out.println("GRAFO NO DIRIGIDO PONDERADO");
        System.out.println(g);

        System.out.println("isConexo(): " + g.isConexo());

        structures.ArrayList<String> ruta = g.shortPath("A", "E");
        System.out.println("shortPath(A, E): " + ruta);
        System.out.println("Costo: " + g.shortPathCost("A", "E"));

        structures.Stack<String> stack = g.Dijsktra("A", "E");
        System.out.print("Dijsktra(A, E) con pop(): ");

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());

            if (!stack.isEmpty()) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }
}
