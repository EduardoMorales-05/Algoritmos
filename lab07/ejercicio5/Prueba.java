package ejercicio5;

public class Prueba {

    public static void main(String[] args) {

        try {

            LinkedBST<Integer> inventario =
                new LinkedBST<Integer>();

            // INSERTAR PRODUCTOS
            inventario.insert(50);
            inventario.insert(30);
            inventario.insert(70);
            inventario.insert(20);
            inventario.insert(40);
            inventario.insert(60);
            inventario.insert(80);

            System.out.println("=== INVENTARIO ===");
            inventario.inOrder();

            // BUSCAR RANGO
            System.out.println("\n=== PRODUCTOS ENTRE 35 Y 75 ===");
            inventario.searchRange(35, 75);

            // CONTAR HOJAS
            System.out.println("\n=== PRODUCTOS EN NODOS HOJA ===");
            System.out.println(inventario.countLeaves());

            // DESCENDENTE
            System.out.println("\n=== INVENTARIO DESCENDENTE ===");
            inventario.printDescending();

        }
        catch (Exception e) {

            System.out.println(
                "ERROR: " + e.getMessage()
            );
        }
    }
}
