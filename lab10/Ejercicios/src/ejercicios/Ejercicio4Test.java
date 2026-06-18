package ejercicios;

public class Ejercicio4Test {
    public static void main(String[] args) {
        probarIsomorfismo();
        probarPlanoConexo();
        probarAutoComplementario();
        probarGraphListEdge();
    }

    private static void probarIsomorfismo() {
        graph.GraphLink<String> g1 = new graph.GraphLink<String>(true);
        g1.insertEdge("A", "B");
        g1.insertEdge("B", "C");
        g1.insertEdge("C", "A");

        graph.GraphLink<String> g2 = new graph.GraphLink<String>(true);
        g2.insertEdge("1", "2");
        g2.insertEdge("2", "3");
        g2.insertEdge("3", "1");

        System.out.println("===== ISOMORFISMO =====");
        System.out.println("Grafo 1:");
        System.out.println(g1);
        System.out.println("Grafo 2:");
        System.out.println(g2);
        System.out.println("isIsomorfo(g1, g2): "
                + graph.GraphAlgorithms.isIsomorfo(g1, g2));
    }

    private static void probarPlanoConexo() {
        graph.GraphLink<String> k5 = new graph.GraphLink<String>(false);

        String[] v = {"A", "B", "C", "D", "E"};

        for (int i = 0; i < v.length; i++) {
            k5.insertVertex(v[i]);
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                k5.insertEdge(v[i], v[j]);
            }
        }

        System.out.println("\n===== PLANO Y CONEXO =====");
        System.out.println("Grafo K5:");
        System.out.println(k5);
        System.out.println("isConexo(): " + graph.GraphAlgorithms.isConexo(k5));
        System.out.println("isPlanoBasico(): " + graph.GraphAlgorithms.isPlanoBasico(k5));
    }

    private static void probarAutoComplementario() {
        graph.GraphLink<Integer> p4 = new graph.GraphLink<Integer>(false);

        p4.insertEdge(1, 2);
        p4.insertEdge(2, 3);
        p4.insertEdge(3, 4);

        System.out.println("\n===== AUTO COMPLEMENTARIO =====");
        System.out.println("Grafo P4:");
        System.out.println(p4);
        System.out.println("Complemento:");
        System.out.println(graph.GraphAlgorithms.complemento(p4));
        System.out.println("isAutoComplementario(): "
                + graph.GraphAlgorithms.isAutoComplementario(p4));
    }

    private static void probarGraphListEdge() {
        graph.GraphListEdge<String> gle = new graph.GraphListEdge<String>(false);

        gle.insertEdgeWeight("A", "B", 7);
        gle.insertEdgeWeight("B", "C", 4);
        gle.insertEdgeWeight("C", "D", 2);

        System.out.println("\n===== GRAPHLISTEDGE =====");
        System.out.println(gle);

        graph.GraphLink<String> convertido = gle.toGraphLink();

        System.out.println("Convertido a GraphLink:");
        System.out.println(convertido);
        System.out.println("isConexo(): " + convertido.isConexo());
    }
}
