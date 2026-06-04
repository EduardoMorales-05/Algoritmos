public class TestBTree
{
    public static void main(String[] args)
    {
        BTree<Integer> tree = new BTree<>(4);

        int datos[] =
        {
            50, 20, 70, 10, 30,
            60, 80, 25, 27, 26,
            65, 75, 85, 5
        };

        for(int x : datos)
            tree.insert(x);

        System.out.println("ARBOL INICIAL");
        System.out.println(tree);

        System.out.println("\n========================");
        System.out.println("Eliminar 5");
        tree.remove(5);
        System.out.println(tree);

        System.out.println("\n========================");
        System.out.println("Eliminar 26");
        tree.remove(26);
        System.out.println(tree);

        System.out.println("\n========================");
        System.out.println("Eliminar 85");
        tree.remove(85);
        System.out.println(tree);

        System.out.println("\n========================");
        System.out.println("Eliminar 100");
        tree.remove(100);
        System.out.println(tree);

        System.out.println("\n========================");
        System.out.println("Eliminar 50");
        tree.remove(50);
        System.out.println(tree);
    }
}
