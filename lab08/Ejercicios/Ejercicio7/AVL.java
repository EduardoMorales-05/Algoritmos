// Archivo: AVL.java

class Nodo {

    int dato, altura;
    Nodo izq, der;

    Nodo(int dato) {
        this.dato = dato;
        this.altura = 1;
    }
}

class AVLTree {

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

    void insertar(int dato) {
        raiz = insertar(raiz, dato);
    }

    Nodo insertar(Nodo nodo, int dato) {

        if (nodo == null)
            return new Nodo(dato);

        if (dato < nodo.dato) {
            nodo.izq = insertar(nodo.izq, dato);

        } else if (dato > nodo.dato) {
            nodo.der = insertar(nodo.der, dato);

        } else {
            return nodo;
        }

        actualizarAltura(nodo);

        int bf = balance(nodo);

        if (bf > 1 && dato > nodo.der.dato) {

            System.out.println("Rotación Izquierda (DD)");

            return rotarIzquierda(nodo);
        }

        if (bf < -1 && dato < nodo.izq.dato) {

            System.out.println("Rotación Derecha (II)");

            return rotarDerecha(nodo);
        }

        if (bf < -1 && dato > nodo.izq.dato) {

            System.out.println("Rotación Izquierda-Derecha (ID)");

            nodo.izq = rotarIzquierda(nodo.izq);

            return rotarDerecha(nodo);
        }

        if (bf > 1 && dato < nodo.der.dato) {

            System.out.println("Rotación Derecha-Izquierda (DI)");

            nodo.der = rotarDerecha(nodo.der);

            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    void eliminar(int dato) {
        raiz = eliminar(raiz, dato);
    }

    Nodo eliminar(Nodo nodo, int dato) {

        if (nodo == null)
            return nodo;

        if (dato < nodo.dato) {

            nodo.izq = eliminar(nodo.izq, dato);

        } else if (dato > nodo.dato) {

            nodo.der = eliminar(nodo.der, dato);

        } else {

            if (nodo.izq == null || nodo.der == null) {

                Nodo temp;

                if (nodo.izq != null)
                    temp = nodo.izq;
                else
                    temp = nodo.der;

                if (temp == null) {

                    nodo = null;

                } else {

                    nodo = temp;
                }

            } else {

                Nodo temp = minimo(nodo.der);

                nodo.dato = temp.dato;

                nodo.der = eliminar(nodo.der, temp.dato);
            }
        }

        if (nodo == null)
            return nodo;

        actualizarAltura(nodo);

        int bf = balance(nodo);

        if (bf < -1 && balance(nodo.izq) <= 0) {

            System.out.println("Rotación Derecha por eliminación");

            return rotarDerecha(nodo);
        }

        if (bf < -1 && balance(nodo.izq) > 0) {

            System.out.println("Rotación Izquierda-Derecha por eliminación");

            nodo.izq = rotarIzquierda(nodo.izq);

            return rotarDerecha(nodo);
        }

        if (bf > 1 && balance(nodo.der) >= 0) {

            System.out.println("Rotación Izquierda por eliminación");

            return rotarIzquierda(nodo);
        }

        if (bf > 1 && balance(nodo.der) < 0) {

            System.out.println("Rotación Derecha-Izquierda por eliminación");

            nodo.der = rotarDerecha(nodo.der);

            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    Nodo minimo(Nodo nodo) {

        Nodo actual = nodo;

        while (actual.izq != null)
            actual = actual.izq;

        return actual;
    }
}

public class AVL {

    public static void main(String[] args) {

        AVLTree arbol = new AVLTree();

        int[] datos = {40, 30, 20, 60, 50, 70};

        System.out.println("Insertando datos:");

        for (int x : datos) {

            System.out.println("Insertar: " + x);

            arbol.insertar(x);
        }

        System.out.println("\nRaíz final después de inserciones: "
                + arbol.raiz.dato);

        System.out.println("\nEliminar 20");

        arbol.eliminar(20);

        System.out.println("Raíz actual: " + arbol.raiz.dato);

        System.out.println("\nEliminar 70");

        arbol.eliminar(70);

        System.out.println("Raíz actual: " + arbol.raiz.dato);

        System.out.println("\nEliminar 60");

        arbol.eliminar(60);

        System.out.println("Raíz final: " + arbol.raiz.dato);
    }
}
        System.out.println("\nNueva raíz: " + arbol.raiz.dato);
    }
}
