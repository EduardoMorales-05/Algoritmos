public class Ejercicio2 {
    private static final int SIZE = 7;
    private static final int EMPTY = -1;

    public static void main(String[] args) {
        int[] valores = {10, 17, 24, 31, 4};

        System.out.println("== EJERCICIO 2: SONDEO LINEAL ==");
        int[] tablaLineal = crearTabla();
        for (int i = 0; i < valores.length; i++) {
            insertarLineal(tablaLineal, valores[i]);
            imprimirTabla(tablaLineal);
        }

        System.out.println("\n== EJERCICIO 2: SONDEO CUADRATICO ==");
        int[] tablaCuadratica = crearTabla();
        for (int i = 0; i < valores.length; i++) {
            insertarCuadratico(tablaCuadratica, valores[i]);
            imprimirTabla(tablaCuadratica);
        }
    }

    private static int[] crearTabla() {
        int[] tabla = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            tabla[i] = EMPTY;
        }
        return tabla;
    }

    private static int hash(int x) {
        return x % SIZE;
    }

    private static void insertarLineal(int[] tabla, int valor) {
        int indiceInicial = hash(valor);
        int exploradas = 0;

        System.out.println("\nInsertando " + valor + " | h(" + valor + ") = " + indiceInicial);

        for (int i = 0; i < SIZE; i++) {
            int indice = (indiceInicial + i) % SIZE;
            exploradas++;

            if (tabla[indice] == EMPTY) {
                tabla[indice] = valor;
                System.out.println("Insertado en indice " + indice
                        + " | posiciones exploradas: " + exploradas);
                return;
            }
        }

        System.out.println("No hay espacio disponible.");
    }

    private static void insertarCuadratico(int[] tabla, int valor) {
        int indiceInicial = hash(valor);
        int exploradas = 0;

        System.out.println("\nInsertando " + valor + " | h(" + valor + ") = " + indiceInicial);

        for (int i = 0; i < SIZE; i++) {
            int indice = (indiceInicial + (i * i)) % SIZE;
            exploradas++;

            if (tabla[indice] == EMPTY) {
                tabla[indice] = valor;
                System.out.println("Insertado en indice " + indice
                        + " | posiciones exploradas: " + exploradas);
                return;
            }
        }

        System.out.println("No hay espacio disponible.");
    }

    private static void imprimirTabla(int[] tabla) {
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
