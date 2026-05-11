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
            
            System.out.println("--- MIN Y MAX ---");
            
            // Obteniendo el valor mínimo (debería ser 50)
            Integer minimo = bst.findMin();
            System.out.println("El valor mínimo en el árbol es: " + minimo);
            
            // Obteniendo el valor máximo (debería ser 700)
            Integer maximo = bst.findMax();
            System.out.println("El valor máximo en el árbol es: " + maximo);
        
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ItemDuplicated e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}