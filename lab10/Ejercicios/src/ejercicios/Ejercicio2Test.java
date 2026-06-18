package ejercicios;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Ejercicio2Test {

    private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;

    public Ejercicio2Test() {
        grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    }

    public void agregarCiudad(String ciudad) {
        grafo.addVertex(ciudad);
    }

    public void agregarCarretera(String origen, String destino, double distancia) {
        grafo.addVertex(origen);
        grafo.addVertex(destino);

        DefaultWeightedEdge arista = grafo.addEdge(origen, destino);

        if (arista != null) {
            grafo.setEdgeWeight(arista, distancia);
        }
    }

    public void mostrarCiudades() {
        System.out.println("LISTA DE CIUDADES:");

        for (String ciudad : grafo.vertexSet()) {
            System.out.println("- " + ciudad);
        }
    }

    public void mostrarCarreteras() {
        System.out.println("\nCARRETERAS REGISTRADAS:");

        for (DefaultWeightedEdge arista : grafo.edgeSet()) {
            String origen = grafo.getEdgeSource(arista);
            String destino = grafo.getEdgeTarget(arista);
            double distancia = grafo.getEdgeWeight(arista);

            System.out.println(origen + " - " + destino + " : " + distancia + " km");
        }
    }

    public void caminoMasCorto(String origen, String destino) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra =
                new DijkstraShortestPath<String, DefaultWeightedEdge>(grafo);

        GraphPath<String, DefaultWeightedEdge> camino = dijkstra.getPath(origen, destino);

        System.out.println("\nCAMINO MÁS CORTO");
        System.out.println("Origen: " + origen);
        System.out.println("Destino: " + destino);

        if (camino == null) {
            System.out.println("No existe camino entre las ciudades.");
            return;
        }

        System.out.println("Ruta: " + camino.getVertexList());
        System.out.println("Costo total: " + camino.getWeight() + " km");
    }

    public static void main(String[] args) {
        Ejercicio2Test red = new Ejercicio2Test();

        red.agregarCiudad("Arequipa");
        red.agregarCiudad("Cusco");
        red.agregarCiudad("Puno");
        red.agregarCiudad("Tacna");
        red.agregarCiudad("Moquegua");

        red.agregarCarretera("Arequipa", "Cusco", 510);
        red.agregarCarretera("Arequipa", "Moquegua", 230);
        red.agregarCarretera("Moquegua", "Tacna", 160);
        red.agregarCarretera("Cusco", "Puno", 390);
        red.agregarCarretera("Puno", "Tacna", 420);

        red.mostrarCiudades();
        red.mostrarCarreteras();

        red.caminoMasCorto("Arequipa", "Tacna");
    }
}
