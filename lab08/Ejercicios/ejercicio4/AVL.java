public class AVL
{
    NodoAVL raiz;
    int altura(NodoAVL n)
    {
        if (n == null)
        {
            return 0;
        }

        return n.altura;
    }

    void actualizarAltura(NodoAVL n)
    {
        n.altura = 1 + Math.max(
            altura(n.izq),
            altura(n.der)
        );
    }

    int balance(NodoAVL n)
    {
        if (n == null)
        {
            return 0;
        }

        return altura(n.der) - altura(n.izq);
    }
    NodoAVL rotarIzquierda(NodoAVL x)
    {
        NodoAVL y = x.der;
        NodoAVL temp = y.izq;

        y.izq = x;
        x.der = temp;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    NodoAVL rotarDerecha(NodoAVL y)
    {
        NodoAVL x = y.izq;
        NodoAVL temp = x.der;

        x.der = y;
        y.izq = temp;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }
    public void insertar(int dato)
    {
        raiz = insertar(raiz, dato);
    }

    private NodoAVL insertar(NodoAVL nodo, int dato)
    {
        if (nodo == null)
        {
            return new NodoAVL(dato);
        }

        if (dato < nodo.dato)
        {
            nodo.izq = insertar(nodo.izq, dato);
        }
        else if (dato > nodo.dato)
        {
            nodo.der = insertar(nodo.der, dato);
        }
        else
        {
            return nodo;
        }

        actualizarAltura(nodo);

        int bf = balance(nodo);

        // II
        if (bf < -1 && dato < nodo.izq.dato)
        {
            return rotarDerecha(nodo);
        }

        // DD
        if (bf > 1 && dato > nodo.der.dato)
        {
            return rotarIzquierda(nodo);
        }

        // ID
        if (bf < -1 && dato > nodo.izq.dato)
        {
            nodo.izq = rotarIzquierda(nodo.izq);

            return rotarDerecha(nodo);
        }

        // DI
        if (bf > 1 && dato < nodo.der.dato)
        {
            nodo.der = rotarDerecha(nodo.der);

            return rotarIzquierda(nodo);
        }

        return nodo;
    }
    public void mostrar()
    {
        mostrar(raiz, "", true);
    }

    private void mostrar(NodoAVL nodo,String espacio,boolean raiz)
    {
        if (nodo != null)
        {
            System.out.println(espacio + (raiz ? "└── " : "├── ") + nodo.dato);

            mostrar(nodo.izq, espacio + "    ", false);

            mostrar(nodo.der, espacio + "    ", false);
        }
    }
    public void inorden()
    {
        inorden(raiz);

        System.out.println();
    }

    private void inorden(NodoAVL nodo)
    {
        if (nodo != null)
        {
            inorden(nodo.izq);

            System.out.print(nodo.dato + " ");

            inorden(nodo.der);
        }
    }
    public void preorden()
    {
        preorden(raiz);

        System.out.println();
    }

    private void preorden(NodoAVL nodo)
    {
        if (nodo != null)
        {
            System.out.print(nodo.dato + " ");

            preorden(nodo.izq);

            preorden(nodo.der);
        }
    }
    public void recorridoAmplitud()
    {
        int h = altura(raiz);

        for (int nivel = 1; nivel <= h; nivel++)
        {
            imprimirNivel(raiz, nivel);
        }

        System.out.println();
    }

    private void imprimirNivel(NodoAVL nodo,int nivel)
    {
        if (nodo == null)
        {
            return;
        }

        if (nivel == 1)
        {
            System.out.print(nodo.dato + " ");
        }
        else
        {
            imprimirNivel(nodo.izq, nivel - 1);

            imprimirNivel(nodo.der, nivel - 1);
        }
    }
}
