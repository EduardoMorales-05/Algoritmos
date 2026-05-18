public class BST
{
    NodoBST raiz;

    public BST()
    {
        raiz = null;
    }

    public void insertar(int dato)
    {
        raiz = insertar(raiz, dato);
    }

    private NodoBST insertar(NodoBST nodo, int dato)
    {
        if (nodo == null)
        {
            return new NodoBST(dato);
        }

        if (dato < nodo.dato)
        {
            nodo.izq = insertar(nodo.izq, dato);
        }
        else if (dato > nodo.dato)
        {
            nodo.der = insertar(nodo.der, dato);
        }

        return nodo;
    }

    public boolean buscar(int dato)
    {
        return buscar(raiz, dato);
    }

    private boolean buscar(NodoBST nodo, int dato)
    {
        if (nodo == null)
        {
            return false;
        }

        if (dato == nodo.dato)
        {
            return true;
        }

        if (dato < nodo.dato)
        {
            return buscar(nodo.izq, dato);
        }

        return buscar(nodo.der, dato);
    }

    public int altura()
    {
        return altura(raiz);
    }

    private int altura(NodoBST nodo)
    {
        if (nodo == null)
        {
            return 0;
        }

        return 1 + Math.max(
            altura(nodo.izq),
            altura(nodo.der)
        );
    }

    public void inorden()
    {
        inorden(raiz);

        System.out.println();
    }

    private void inorden(NodoBST nodo)
    {
        if (nodo != null)
        {
            inorden(nodo.izq);

            System.out.print(nodo.dato + " ");

            inorden(nodo.der);
        }
    }

    public void mostrar()
    {
        mostrar(raiz, "", true);
    }

    private void mostrar(NodoBST nodo, String espacio, boolean raiz)
    {
        if (nodo != null)
        {
            System.out.println( espacio + (raiz ? "└── " : "├── ") + nodo.dato);

            mostrar(nodo.izq, espacio + "    ", false);

            mostrar(nodo.der, espacio + "    ", false);
        }
    }
}
