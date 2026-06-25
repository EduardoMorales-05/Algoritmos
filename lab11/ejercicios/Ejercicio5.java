public class Ejercicio5 {
    public static void main(String[] args) {
        HashRehash tabla = new HashRehash(7);

        int[] valores = {2, 9, 16, 23, 4, 11};

        System.out.println("== EJERCICIO 5: FACTOR DE CARGA Y REHASHING ==");

        for (int i = 0; i < valores.length; i++) {
            tabla.insertar(valores[i]);
            tabla.imprimirFactorCarga();
        }

        System.out.println("\n== TABLA FINAL ==");
        tabla.imprimir();
    }
}

class HashRehash {
    private static final int EMPTY = -1;
    private int[] tabla;
    private int size;
    private int cantidad;

    public HashRehash(int size) {
        this.size = size;
        this.tabla = new int[size];
        this.cantidad = 0;

        for (int i = 0; i < size; i++) {
            tabla[i] = EMPTY;
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insertar(int key) {
        insertarSinRehash(key);
        double factor = (double) cantidad / size;

        if (factor > 0.75) {
            System.out.println("\nFactor de carga supero 0.75. Ejecutando rehashing...");
            System.out.println("Tabla antes del rehashing:");
            imprimir();

            rehash(17);

            System.out.println("Tabla despues del rehashing:");
            imprimir();
        }
    }

    private void insertarSinRehash(int key) {
        int indiceInicial = hash(key);

        for (int i = 0; i < size; i++) {
            int indice = (indiceInicial + i) % size;

            if (tabla[indice] == EMPTY) {
                tabla[indice] = key;
                cantidad++;
                System.out.println("Insertado " + key + " en indice " + indice);
                return;
            }
        }

        System.out.println("Tabla llena. No se pudo insertar " + key);
    }

    private void rehash(int nuevoSize) {
        int[] antigua = tabla;

        this.size = nuevoSize;
        this.tabla = new int[nuevoSize];
        this.cantidad = 0;

        for (int i = 0; i < nuevoSize; i++) {
            tabla[i] = EMPTY;
        }

        for (int i = 0; i < antigua.length; i++) {
            if (antigua[i] != EMPTY) {
                insertarSinRehash(antigua[i]);
            }
        }
    }

    public void imprimirFactorCarga() {
        double factor = (double) cantidad / size;
        System.out.printf("Factor de carga: %d/%d = %.3f%n%n", cantidad, size, factor);
    }

    public void imprimir() {
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("[" + i + ": ");
            if (tabla[i] == EMPTY) {
                System.out.print("VACIO");
            } else {
                System.out.print(tabla[i]);
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}
