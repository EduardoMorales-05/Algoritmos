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
        {
            tree.insert(x);
        }

        System.out.println("ARBOL B");
        System.out.println(tree);

        System.out.println("\n==============================");
        System.out.println("RANGO EXISTENTE [20, 70]");
        System.out.print("Resultado: ");
        tree.searchRange(20, 70);

        System.out.println("\n==============================");
        System.out.println("RANGO EXISTENTE [25, 65]");
        System.out.print("Resultado: ");
        tree.searchRange(25, 65);

        System.out.println("\n==============================");
        System.out.println("RANGO INEXISTENTE [90, 100]");
        System.out.print("Resultado: ");
        tree.searchRange(90, 100);

        System.out.println("\n==============================");
        System.out.println("RANGO INVALIDO [70, 20]");
        System.out.print("Resultado: ");
        tree.searchRange(70, 20);

        System.out.println("\n==============================");
        System.out.println("UN SOLO ELEMENTO [50, 50]");
        System.out.print("Resultado: ");
        tree.searchRange(50, 50);

        System.out.println();
    }
}
