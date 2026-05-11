package bstreelinklistinterfgeneric;

import exceptions.*;

public class Prueba {
    public static void main(String[] args) throws ItemNoFound, ExceptionIsEmpty {
        // Instancia del Árbol de Búsqueda Binaria Genérico para Integers
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            System.out.println("Insertando: 400, 100, 700, 50, 200, 75");
            bst.insert(400);
            bst.insert(100);
            bst.insert(700);
            bst.insert(50);
            bst.insert(200);
            bst.insert(75);

            // Actividad 7: Probar el recorrido In-Orden (debe salir ordenado)
            // Lógica: Izquierda -> Raíz -> Derecha
            bst.inOrder();
            System.out.println(bst.toString());
            
            System.out.println("Verificando Pre-Orden:");
            bst.preOrder();
            
            System.out.println("Verificando Post-Orden:");
            bst.postOrder();
            
        } catch (ItemDuplicated e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}