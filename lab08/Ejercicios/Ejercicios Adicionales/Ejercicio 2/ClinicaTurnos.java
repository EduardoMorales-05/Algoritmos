class Nodo {

    int turno;
    int altura;
    Nodo izq, der;

    Nodo(int turno) {
        this.turno = turno;
        this.altura = 1;
    }
}

class AVL {

    Nodo raiz;

    int altura(Nodo n) {
        return n == null ? 0 : n.altura;
    }

    int balance(Nodo n) {
        return n == null ? 0 : altura(n.der) - altura(n.izq);
    }

    void actualizarAltura(Nodo n) {
        n.altura = 1 + Math.max(altura(n.izq), altura(n.der));
    }

    Nodo rotarIzquierda(Nodo x) {

        Nodo y = x.der;
        Nodo temp = y.izq;

        y.izq = x;
        x.der = temp;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    Nodo rotarDerecha(Nodo y) {

        Nodo x = y.izq;
        Nodo temp = x.der;

        x.der = y;
        y.izq = temp;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    void insertar(int turno) {
        raiz = insertar(raiz, turno);
    }

    Nodo insertar(Nodo nodo, int turno) {

        if (nodo == null)
            return new Nodo(turno);

        if (turno < nodo.turno)
            nodo.izq = insertar(nodo.izq, turno);

        else if (turno > nodo.turno)
            nodo.der = insertar(nodo.der, turno);

        else
            return nodo;

        actualizarAltura(nodo);

        int bf = balance(nodo);

        if (bf > 1 && turno > nodo.der.turno)
            return rotarIzquierda(nodo);

        if (bf < -1 && turno < nodo.izq.turno)
            return rotarDerecha(nodo);

        if (bf < -1 && turno > nodo.izq.turno) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }

        if (bf > 1 && turno < nodo.der.turno) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    Nodo buscar(Nodo nodo, int turno) {

        if (nodo == null || nodo.turno == turno)
            return nodo;

        if (turno < nodo.turno)
            return buscar(nodo.izq, turno);

        return buscar(nodo.der, turno);
    }

    boolean existe(int turno) {
        return buscar(raiz, turno) != null;
    }

    void eliminar(int turno) {
        raiz = eliminar(raiz, turno);
    }

    Nodo eliminar(Nodo nodo, int turno) {

        if (nodo == null)
            return null;

        if (turno < nodo.turno) {

            nodo.izq = eliminar(nodo.izq, turno);

        } else if (turno > nodo.turno) {

            nodo.der = eliminar(nodo.der, turno);

        } else {

            if (nodo.izq == null || nodo.der == null) {
                nodo = (nodo.izq != null) ? nodo.izq : nodo.der;

            } else {

                Nodo temp = minimo(nodo.der);

                nodo.turno = temp.turno;

                nodo.der = eliminar(nodo.der, temp.turno);
            }
        }

        return nodo;
    }

    Nodo minimo(Nodo nodo) {

        while (nodo.izq != null)
            nodo = nodo.izq;

        return nodo;
    }
}

public class ClinicaTurnos {

    public static void main(String[] args) {

        AVL arbol = new AVL();

        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(10);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("Turno 40 existe: " + arbol.existe(40));

        arbol.eliminar(10);
        System.out.println("Turno 10 eliminado");

        arbol.eliminar(30);
        System.out.println("Turno 30 eliminado");
    }
}
