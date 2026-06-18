package ejercicios;

public class Ejercicio3Test {
    public static void main(String[] args) {
        graph.Graph<String> g = new graph.GraphLink<String>(false);

        g.insertVertex("Arequipa");
        g.insertVertex("Cusco");
        g.insertVertex("Puno");
        g.insertVertex("Tacna");
        g.insertVertex("Moquegua");

        g.insertEdgeWeight("Arequipa", "Cusco", 510);
        g.insertEdgeWeight("Arequipa", "Moquegua", 230);
        g.insertEdgeWeight("Moquegua", "Tacna", 160);
        g.insertEdgeWeight("Cusco", "Puno", 390);
        g.insertEdgeWeight("Puno", "Tacna", 420);

        System.out.println("searchVertex(Arequipa): " + g.searchVertex("Arequipa"));
        System.out.println("searchEdge(Arequipa, Moquegua): "
                + g.searchEdge("Arequipa", "Moquegua"));

        System.out.println("adjacentVertices(Arequipa): "
                + g.adjacentVertices("Arequipa"));

        g.removeEdge("Cusco", "Puno");
        System.out.println("Despues de removeEdge(Cusco, Puno): "
                + g.searchEdge("Cusco", "Puno"));

        g.removeVertex("Tacna");
        System.out.println("Despues de removeVertex(Tacna): "
                + g.searchVertex("Tacna"));

        System.out.println("\nGRAFO FINAL:");
        System.out.println(g);
    }
}
