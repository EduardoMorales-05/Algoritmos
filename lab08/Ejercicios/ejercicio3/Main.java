public class Main
{
    public static void main(String[] args)
    {
        AVL arbol = new AVL();

        int[] datos = {50,30,70,20,40,60,80,10,25,35,45,55,65};

        System.out.println("[+] INSERCION:");

        for (int x : datos)
        {
            arbol.insertar(x);
        }

        arbol.mostrar();

        System.out.println();

        System.out.println("[!] INORDEN: ");

        arbol.inorden();
        int[] eliminar =
        {
            10, // hoja
            60, // un hijo
            30  // dos hijos
        };

        for (int x : eliminar)
        {
            arbol.eliminar(x);

            System.out.println();

            arbol.mostrar();

            System.out.println();
        }

        System.out.println("[+] ARBOL FINAL:");

        arbol.mostrar();

        System.out.println();

        arbol.inorden();
    }
}
