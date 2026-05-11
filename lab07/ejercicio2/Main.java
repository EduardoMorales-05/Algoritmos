package ejercicio2;


public class Main {

    public static void main(String[] args) {

        LinkedBST<Integer> bst = new LinkedBST<Integer>();

        try {

            // --- INSERTAR DATOS ---
            bst.insert(15);
            bst.insert(8);
            bst.insert(22);
            bst.insert(5);
            bst.insert(12);
            bst.insert(18);
            bst.insert(30);

            // --- MOSTRAR ÁRBOL ---
            System.out.println("ÁRBOL BST:");
            System.out.println(bst);

            // --- RECORRIDOS ---
            System.out.println("\nIN-ORDER:");
            bst.inOrder();

            System.out.println("\nPRE-ORDER:");
            bst.preOrder();

            System.out.println("\nPOST-ORDER:");
            bst.postOrder();

            // --- CONTAR TODOS LOS NODOS ---
            System.out.println("\nTOTAL DE NODOS:");
            System.out.println(bst.countAllNodes());

            // --- CONTAR NODOS NO HOJA ---
            System.out.println("\nNODOS NO HOJA:");
            System.out.println(bst.countNodes());

            // --- ALTURA DEL ÁRBOL DESDE UN NODO ---
            System.out.println("\nALTURA DEL SUBÁRBOL CON RAÍZ 22:");
            System.out.println(bst.height(22));

            System.out.println("\nALTURA DEL SUBÁRBOL CON RAÍZ 8:");
            System.out.println(bst.height(8));

            // --- BUSCAR ALTURA DE UN NODO INEXISTENTE ---
            System.out.println("\nALTURA DEL SUBÁRBOL CON RAÍZ 100:");
            System.out.println(bst.height(100));

            // --- AMPLITUD DEL ÁRBOL ---
            System.out.println("\nAMPLITUD DEL ÁRBOL:");
            System.out.println(bst.amplitude());

            // --- MÍNIMO Y MÁXIMO ---
            System.out.println("\nVALOR MÍNIMO:");
            System.out.println(bst.findMin());

            System.out.println("\nVALOR MÁXIMO:");
            System.out.println(bst.findMax());

            // --- ELIMINAR UN NODO ---
            System.out.println("\nELIMINANDO 22...");
            bst.delete(22);

            System.out.println("\nÁRBOL DESPUÉS DE ELIMINAR 22:");
            System.out.println(bst);

            // --- DESTRUIR ÁRBOL ---
            System.out.println("\nDESTRUYENDO ÁRBOL...");
            bst.destroyNodes();

            // --- VERIFICAR SI ESTÁ VACÍO ---
            System.out.println("\n¿ÁRBOL VACÍO?");
            System.out.println(bst.isEmpty());
            // --- BUSCAR ELEMENTOS ---
            System.out.println("\nBUSCAR 12:");
            System.out.println(bst.search(12));

            System.out.println("\nBUSCAR 100:");
            System.out.println(bst.search(100));

        } catch (ItemDuplicated e) {

            System.out.println("ERROR: " + e.getMessage());

        } catch (ItemNoFound e) {

            System.out.println("ERROR: " + e.getMessage());

        } catch (ExceptionIsEmpty e) {

            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
