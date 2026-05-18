public class AVL
{
    NodoAVL raiz;

    public AVL()
    {
        raiz = null;
    }

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
        n.altura = 1 + Math.max(altura(n.izq), altura(n.der));
    }

    NodoAVL rotarIzquierda(NodoAVL x)
    {
        System.out.println("Rotación Simple Izquierda en " + x.dato);

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
        System.out.println("Rotación Simple Derecha en " + y.dato);

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
            System.out.println("Insertando: " + dato);
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

        System.out.println("Nodo " + nodo.dato + " -> BF = " + bf);

        // Izquierda-Izquierda
        if (bf < -1 && dato < nodo.izq.dato)
        {
            return rotarDerecha(nodo);
        }

        // Derecha-Derecha
        if (bf > 1 && dato > nodo.der.dato)
        {
            return rotarIzquierda(nodo);
        }

        // Izquierda-Derecha
        if (bf < -1 && dato > nodo.izq.dato)
        {
            System.out.println("Rotación Doble Izquierda-Derecha");

            nodo.izq = rotarIzquierda(nodo.izq);

            return rotarDerecha(nodo);
        }

        // Derecha-Izquierda
        if (bf > 1 && dato < nodo.der.dato)
        {
            System.out.println("Rotación Doble Derecha-Izquierda");

            nodo.der = rotarDerecha(nodo.der);

            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public boolean buscar(int dato)
    {
        return buscar(raiz, dato);
    }

    private boolean buscar(NodoAVL nodo, int dato)
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
            System.out.println("Eliminando: " + dato);

            // Sin hijos o un hijo
            if (nodo.izq == null || nodo.der == null)
            {
                NodoAVL temp;

                if (nodo.izq != null)
                {
                    temp = nodo.izq;
                }
                else
                {
                    temp = nodo.der;
                }

                if (temp == null)
                {
                    nodo = null;
                }
                else
                {
                    nodo = temp;
                }
            }
            else
            {
                NodoAVL temp = menorValor(nodo.der);

                nodo.dato = temp.dato;

                nodo.der = eliminar(nodo.der, temp.dato);
            }
        }

        if (nodo == null)
        {
            return nodo;
        }

        actualizarAltura(nodo);

        int bf = balance(nodo);

        // Izquierda-Izquierda
        if (bf < -1 && balance(nodo.izq) <= 0)
        {
            return rotarDerecha(nodo);
        }

        // Izquierda-Derecha
        if (bf < -1 && balance(nodo.izq) > 0)
        {
            nodo.izq = rotarIzquierda(nodo.izq);

            return rotarDerecha(nodo);
        }

        // Derecha-Derecha
        if (bf > 1 && balance(nodo.der) >= 0)
        {
            return rotarIzquierda(nodo);
        }

        // Derecha-Izquierda
        if (bf > 1 && balance(nodo.der) < 0)
        {
            nodo.der = rotarDerecha(nodo.der);

            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public void inorden()
    {
        System.out.print("Inorden: ");

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

    public void mostrarArbol()
    {
        mostrarArbol(raiz, "", true);
    }

    private void mostrarArbol(NodoAVL nodo, String espacio, boolean esRaiz)
    {
        if (nodo != null)
        {
            System.out.println(espacio + (esRaiz ? "└── " : "├── ") + nodo.dato + "(bf=" + balance(nodo) + ")");

            mostrarArbol(nodo.izq, espacio + "    ", false);

            mostrarArbol(nodo.der, espacio + "    ", false);
        }
    }
}
