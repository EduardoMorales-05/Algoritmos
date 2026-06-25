public class Ejercicio1 {
    private static final int SIZE = 11;
    private static final int EMPTY = -1;

    public static void main(String[] args) {
        int[] tabla = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            tabla[i] = EMPTY;
        }

        int[] valores = {3, 14, 25, 36, 47, 58};

        System.out.println("== EJERCICIO 1: TABLA HASH ==");
        System.out.println("Funcion hash: h(x) = x % 11\n");

        for (int i = 0; i < valores.length; i++) {
            int valor = valores[i];
            int indice = hash(valor);

            System.out.println("Valor: " + valor + " -> h(" + valor + ") = " + indice);

            if (tabla[indice] == EMPTY) {
                tabla[indice] = valor;
                System.out.println("Insertado en indice " + indice);
            } else {
                System.out.println("Colision detectada en indice " + indice
                        + " con el valor " + tabla[indice]);
            }

            System.out.println();
        }

        imprimirTabla(tabla);
    }

    private static int hash(int x) {
        return x % SIZE;
    }

    private static void imprimirTabla(int[] tabla) {
        System.out.println("== TABLA FINAL ==");
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == EMPTY) {
                System.out.println("Indice " + i + ": VACIO");
            } else {
                System.out.println("Indice " + i + ": " + tabla[i]);
            }
        }
    }
}
