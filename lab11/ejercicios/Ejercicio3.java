public class Ejercicio3 {
    public static void main(String[] args) {
        HashAbierto tabla = new HashAbierto(7);

        tabla.insertar(10, "Juan");
        tabla.insertar(17, "Ana");
        tabla.insertar(24, "Luis");
        tabla.insertar(31, "Rosa");
        tabla.insertar(5, "Pedro");
        tabla.insertar(12, "Carla");

        System.out.println("== TABLA HASH ABIERTA FINAL ==");
        tabla.imprimir();

        System.out.println("\n== BUSQUEDA CLAVE 24 ==");
        tabla.buscar(24);

        System.out.println("\n== ELIMINANDO CLAVE 17 ==");
        tabla.eliminar(17);

        System.out.println("\n== TABLA DESPUES DE ELIMINAR 17 ==");
        tabla.imprimir();

        int indice = 17 % 7;
        System.out.println("\nNodos restantes en la cadena del indice " + indice
                + ": " + tabla.contarNodos(indice));
    }
}

class HashAbierto {
    private Nodo[] tabla;
    private int size;

    public HashAbierto(int size) {
        this.size = size;
        this.tabla = new Nodo[size];
    }

    private int hash(int key) {
        return key % size;
    }

    public void insertar(int key, String nombre) {
        int indice = hash(key);
        Nodo nuevo = new Nodo(key, nombre);

        if (tabla[indice] == null) {
            tabla[indice] = nuevo;
        } else {
            Nodo actual = tabla[indice];

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }

        System.out.println("Insertado (" + key + ", " + nombre + ") en indice " + indice);
    }

    public void buscar(int key) {
        int indice = hash(key);
        Nodo actual = tabla[indice];
        int posicionNodo = 1;

        while (actual != null) {
            if (actual.key == key) {
                System.out.println("Clave encontrada: " + actual.nombre);
                System.out.println("Indice de tabla: " + indice);
                System.out.println("Nodo en la cadena: " + posicionNodo);
                return;
            }

            actual = actual.siguiente;
            posicionNodo++;
        }

        System.out.println("Clave no encontrada.");
    }

    public void eliminar(int key) {
        int indice = hash(key);
        Nodo actual = tabla[indice];
        Nodo anterior = null;

        while (actual != null) {
            if (actual.key == key) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }

                System.out.println("Clave " + key + " eliminada del indice " + indice);
                return;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        System.out.println("Clave no encontrada.");
    }

    public int contarNodos(int indice) {
        int contador = 0;
        Nodo actual = tabla[indice];

        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        return contador;
    }

    public void imprimir() {
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("Indice " + i + ": ");

            Nodo actual = tabla[i];

            if (actual == null) {
                System.out.println("VACIO");
            } else {
                while (actual != null) {
                    System.out.print("(" + actual.key + ", " + actual.nombre + ")");
                    if (actual.siguiente != null) {
                        System.out.print(" -> ");
                    }
                    actual = actual.siguiente;
                }
                System.out.println();
            }
        }
    }

    private static class Nodo {
        int key;
        String nombre;
        Nodo siguiente;

        Nodo(int key, String nombre) {
            this.key = key;
            this.nombre = nombre;
        }
    }
}
