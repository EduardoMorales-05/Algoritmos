public class Main
{
    public static void main(String[] args)
    {
        AVL arbol = new AVL();

        int[] insertar = {30, 10, 20, 40, 50, 25};

        System.out.println("[+] INSERCIONES:");

        for (int x : insertar)
        {
            arbol.insertar(x);

            arbol.mostrarArbol();

            System.out.println();
        }

        System.out.println("[+] RECORRIDO INORDEN: ");

        arbol.inorden();

        System.out.println();

        System.out.println("[+] BUSQUEDAS: ");

        int[] buscar = {20, 60};

        for (int x : buscar)
        {
            if (arbol.buscar(x))
            {
                System.out.println("[!] Ticket " + x + " encontrado");
            }
            else
            {
                System.out.println("[x] Ticket " + x + " NO encontrado");
            }
        }

        System.out.println();

        System.out.println("[x] ELIMINACIONES:");

        int[] eliminar = {10, 40, 30};

        for (int x : eliminar)
        {
            arbol.eliminar(x);

            arbol.mostrarArbol();

            System.out.println();
        }

        System.out.println("[!] ARBOL FINAL: ");

        arbol.mostrarArbol();

        System.out.println();

        arbol.inorden();
    }
}
