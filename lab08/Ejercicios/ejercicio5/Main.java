public class Main
{
    public static void main(String[] args)
    {
        AVL arbol = new AVL();

        int[] datos = {50,30,70,20,40,60,80,10,25,65}; 

        for (int x : datos)
        {
            arbol.insertar(x);
        }

        System.out.println("[!] ARBOL AVL:"
        );

        arbol.mostrar();

        System.out.println();

        System.out.println("[!] BFS RECURSIVO:");

        arbol.recorridoAmplitud();
    }
}
