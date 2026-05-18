class Nodo {
    int codigo;
    int altura;
    Nodo izq, der;

    Nodo(int codigo) {
        this.codigo = codigo;
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

    void insertar(int codigo) {
        raiz = insertar(raiz, codigo);
    }

    Nodo insertar(Nodo nodo, int codigo) {

        if (nodo == null)
            return new Nodo(codigo);

        if (codigo < nodo.codigo) {
            nodo.izq = insertar(nodo.izq, codigo);

        } else if (codigo > nodo.codigo) {
            nodo.der = insertar(nodo.der, codigo);

        } else {
            return nodo;
        }

        actualizarAltura(nodo);

        int bf = balance(nodo);

        if (bf > 1 && codigo > nodo.der.codigo) {
            return rotarIzquierda(nodo);
        }

        if (bf < -1 && codigo < nodo.izq.codigo) {
            return rotarDerecha(nodo);
        }

        if (bf < -1 && codigo > nodo.izq.codigo) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }

        if (bf > 1 && codigo < nodo.der.codigo) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    Nodo buscar(Nodo nodo, int codigo) {

        if (nodo == null || nodo.codigo == codigo)
            return nodo;

        if (codigo < nodo.codigo)
            return buscar(nodo.izq, codigo);

        return buscar(nodo.der, codigo);
    }

    boolean existe(int codigo) {
        return buscar(raiz, codigo) != null;
    }

    void eliminar(int codigo) {
        raiz = eliminar(raiz, codigo);
    }

    Nodo eliminar(Nodo nodo, int codigo) {

        if (nodo == null)
            return null;

        if (codigo < nodo.codigo) {
            nodo.izq = eliminar(nodo.izq, codigo);

        } else if (codigo > nodo.codigo) {
            nodo.der = eliminar(nodo.der, codigo);

        } else {

            if (nodo.izq == null || nodo.der == null) {

                nodo = (nodo.izq != null) ? nodo.izq : nodo.der;

            } else {

                Nodo temp = minimo(nodo.der);
                nodo.codigo = temp.codigo;
                nodo.der = eliminar(nodo.der, temp.codigo);
            }
        }

        return nodo;
    }

    Nodo minimo(Nodo nodo) {
        while (nodo.izq != null)
            nodo = nodo.izq;
        return nodo;
    }

    void inorden(Nodo n) {
        if (n != null) {
            inorden(n.izq);
            System.out.print(n.codigo + " ");
            inorden(n.der);
        }
    }

    void mostrar() {
        inorden(raiz);
        System.out.println();
    }
}

public class AlmacenProductos {

    public static void main(String[] args) {

        AVL arbol = new AVL();

        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("Productos en orden:");
        arbol.mostrar();

        int buscar = 40;
        System.out.println("¿Existe " + buscar + "? " + arbol.existe(buscar));

        arbol.eliminar(30);

        System.out.println("Después de eliminar 30:");
        arbol.mostrar();
    }
}
