package ejercicio3;

public class Prueba {

    // COMPARAR AREA
    public static boolean sameArea(
        LinkedBST<Integer> a,
        LinkedBST<Integer> b) {

        return a.areaBST() == b.areaBST();
    }

    public static void main(String[] args) {

        try {

            LinkedBST<Integer> bst1 =
                new LinkedBST<Integer>();

            LinkedBST<Integer> bst2 =
                new LinkedBST<Integer>();

            // ARBOL 1
            bst1.insert(15);
            bst1.insert(8);
            bst1.insert(22);
            bst1.insert(5);
            bst1.insert(12);
            bst1.insert(18);
            bst1.insert(30);

            // ARBOL 2
            bst2.insert(50);
            bst2.insert(30);
            bst2.insert(70);
            bst2.insert(20);
            bst2.insert(40);
            bst2.insert(60);
            bst2.insert(80);

            System.out.println("=== BST 1 ===");
            bst1.drawBST();

            System.out.println("\nArea BST1:");
            System.out.println(bst1.areaBST());

            System.out.println("\n=== BST 2 ===");
            bst2.drawBST();

            System.out.println("\nArea BST2:");
            System.out.println(bst2.areaBST());

            System.out.println("\n¿MISMA AREA?");
            System.out.println(
                sameArea(bst1, bst2)
            );

        }
        catch (Exception e) {

            System.out.println(
                "ERROR: " + e.getMessage()
            );
        }
    }
}
