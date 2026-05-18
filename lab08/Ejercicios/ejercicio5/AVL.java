public class AVL
{
    NodoAVL raiz;
    int altura(NodoAVL nodo)
    {
        if (nodo == null)
        {
            return 0;
        }

        return nodo.altura;
    }

    void actualizarAltura(NodoAVL nodo)
    {
        nodo.altura = 1 + Math.max(altura(nodo.izq),altura(nodo.der));
    }

    int balance(NodoAVL nodo)
    {
        if (nodo == null)
        {
            return 0;
        }

        return altura(nodo.der) - altura(nodo.izq);
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

    public int alturaArbol()
    {
        return altura(raiz);
    }

    public void recorridoAmplitud()
    {
        int h = alturaArbol();

        System.out.println(
            "Altura del árbol: " + h
        );

        for (int nivel = 1; nivel <= h; nivel++)
        {
            System.out.print(
                "Nivel " + (nivel - 1) + ": "
            );

            imprimirNivel(raiz, nivel);

            System.out.println();
        }
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
            imprimirNivel(nodo.izq,nivel - 1);

            imprimirNivel(nodo.der,nivel - 1);
        }
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
            System.out.println(espacio+(raiz ? "└──" : "├──") + nodo.dato); 

            mostrar(nodo.izq,espacio + "    ",false); 

            mostrar(nodo.der,espacio+"    ",false);
        }
    }
}
