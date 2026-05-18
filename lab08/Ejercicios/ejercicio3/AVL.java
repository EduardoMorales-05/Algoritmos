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

    int balance(NodoAVL n)
    {
        if (n == null)
        {
            return 0;
        }

        return altura(n.der) - altura(n.izq);
    }

    void actualizarAltura(NodoAVL n)
    {
        n.altura = 1 + Math.max(
            altura(n.izq),
            altura(n.der)
        );
    }
    NodoAVL rotarIzquierda(NodoAVL x)
    {
        System.out.println("Rotación Simple Izquierda");

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
        System.out.println("Rotación Simple Derecha");

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
    NodoAVL menorValor(NodoAVL nodo)
    {
        NodoAVL actual = nodo;

        while (actual.izq != null)
        {
            actual = actual.izq;
        }

        return actual;
    }
    public void eliminar(int dato)
    {
        System.out.println("[x] Eliminando: " + dato);

        raiz = eliminar(raiz, dato);
    }

    private NodoAVL eliminar(NodoAVL nodo, int dato)
    {
        if (nodo == null)
        {
            return nodo;
        }

        if (dato < nodo.dato)
        {
            nodo.izq = eliminar(nodo.izq, dato);
        }
        else if (dato > nodo.dato)
        {
            nodo.der = eliminar(nodo.der, dato);
        }
        else
        {

            if (nodo.izq == null && nodo.der == null)
            {
                System.out.println("Caso BST: Nodo hoja");

                return null;
            }

            if (nodo.izq == null || nodo.der == null)
            {
                System.out.println("Caso BST: Nodo con un hijo");

                if (nodo.izq != null)
                {
                    return nodo.izq;
                }
                else
                {
                    return nodo.der;
                }
            }

            System.out.println("Caso BST: Nodo con dos hijos");

            NodoAVL sucesor = menorValor(nodo.der);

            System.out.println(
                "Sucesor Inorden: " + sucesor.dato
            );

            nodo.dato = sucesor.dato;

            nodo.der = eliminar(nodo.der, sucesor.dato);
        }

        actualizarAltura(nodo);

        int bf = balance(nodo);
        if (bf < -1 && balance(nodo.izq) <= 0)
        {
            System.out.println(
                "Nodo desbalanceado: " + nodo.dato
            );

            System.out.println(
                "Rotación aplicada: RSD"
            );

            return rotarDerecha(nodo);
        }

        if (bf < -1 && balance(nodo.izq) > 0)
        {
            System.out.println(
                "Nodo desbalanceado: " + nodo.dato
            );

            System.out.println(
                "Rotación aplicada: RID"
            );

            nodo.izq = rotarIzquierda(nodo.izq);

            return rotarDerecha(nodo);
        }
        if (bf > 1 && balance(nodo.der) >= 0)
        {
            System.out.println(
                "Nodo desbalanceado: " + nodo.dato
            );

            System.out.println(
                "Rotación aplicada: RSI"
            );

            return rotarIzquierda(nodo);
        }

        if (bf > 1 && balance(nodo.der) < 0)
        {
            System.out.println(
                "Nodo desbalanceado: " + nodo.dato
            );

            System.out.println(
                "Rotación aplicada: RDI"
            );

            nodo.der = rotarDerecha(nodo.der);

            return rotarIzquierda(nodo);
        }

        return nodo;
    }
    public void mostrar()
    {
        mostrar(raiz, "", true);
    }

    private void mostrar(
        NodoAVL nodo,
        String espacio,
        boolean raiz
    )
    {
        if (nodo != null)
        {
            System.out.println(
                espacio +
                (raiz ? "└── " : "├── ") +
                nodo.dato +
                "(bf=" + balance(nodo) + ")"
            );

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
}
