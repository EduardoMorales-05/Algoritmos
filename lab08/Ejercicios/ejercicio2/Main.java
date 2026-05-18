public class Main
{
    public static void main(String[] args)
    {
        BST bst = new BST();

        AVL avl = new AVL();

        // Caso 1
        int[] datos1 = {10, 20, 30, 40, 50};

        System.out.println("[!] CASO 1:");

        for (int x : datos1)
        {
            bst.insertar(x);

            avl.insertar(x);
        }

        System.out.println("\nBST:");

        bst.mostrar();

        System.out.println("\nAVL:");

        avl.mostrar();

        System.out.println("\nAltura BST: " + bst.altura());

        System.out.println("Altura AVL: " + avl.altura());

        System.out.println("\nBusqueda 40:");

        System.out.println(
            "BST -> " + bst.buscar(40)
        );

        System.out.println(
            "AVL -> " + avl.buscar(40)
        );

        // Caso 2
        BST bst2 = new BST();

        AVL avl2 = new AVL();

        int[] datos2 = {50, 40, 30, 20, 10};

        System.out.println("\n[!] CASO 2:");

        for (int x : datos2)
        {
            bst2.insertar(x);

            avl2.insertar(x);
        }

        System.out.println("\nBST:");

        bst2.mostrar();

        System.out.println("\nAVL:");

        avl2.mostrar();

        System.out.println("\nAltura BST: " + bst2.altura());

        System.out.println("Altura AVL: " + avl2.altura());

        System.out.println("\nBusqueda 20:");

        System.out.println("BST -> " + bst2.buscar(20));

        System.out.println("AVL -> " + avl2.buscar(20));
    }
}
