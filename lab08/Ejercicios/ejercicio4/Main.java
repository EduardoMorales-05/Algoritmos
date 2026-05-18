public class Main
{
    public static void main(String[] args)
    {
        AVL arbol1 = new AVL();

        int[] datos1 = {50,30,70,20,40,60,80,10,25,65}; 

        for (int x : datos1)
        {
            arbol1.insertar(x);
        }

        System.out.println("[!] ARBOL 1:");

        arbol1.mostrar();

        System.out.println();

        System.out.println("Recorrido Inorden:");

        arbol1.inorden();

        System.out.println("Recorrido Preorden:");

        arbol1.preorden();

        System.out.println("Recorrido por Amplitud:");

        arbol1.recorridoAmplitud();

        // =========================

        AVL arbol2 = new AVL();

        int[] datos2 = {40,20,60,10,30,50,70,5,15};

        for (int x : datos2)
        {
            arbol2.insertar(x);
        }

        System.out.println();

        System.out.println("[!] ARBOL 2:");

        arbol2.mostrar();

        System.out.println();

        System.out.println("Recorrido Inorden:");

        arbol2.inorden();

        System.out.println("Recorrido Preorden:");

        arbol2.preorden();

        System.out.println("Recorrido por Amplitud:");

        arbol2.recorridoAmplitud();
    }
}
