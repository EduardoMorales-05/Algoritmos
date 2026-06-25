public class Ejercicio4 {
    public static void main(String[] args) {
        HashCerradoLogico tabla = new HashCerradoLogico(7);

        int[] claves = {5, 12, 19, 26};

        System.out.println("== INSERTANDO CLAVES ==");
        for (int i = 0; i < claves.length; i++) {
            tabla.insertar(claves[i]);
        }

        tabla.imprimir();

        System.out.println("\n== ELIMINANDO LOGICAMENTE LA CLAVE 12 ==");
        tabla.eliminar(12);
        tabla.imprimir();

        System.out.println("\n== BUSCANDO CLAVE 19 DESPUES DE ELIMINAR 12 ==");
        tabla.buscar(19);

        System.out.println("\n== REINSERTANDO CLAVE 33 ==");
        tabla.insertar(33);
        tabla.imprimir();
    }
}

class HashCerradoLogico {
    private static final int EMPTY = 0;
    private static final int OCCUPIED = 1;
    private static final int DELETED = 2;

    private Entry[] tabla;
    private int size;

    public HashCerradoLogico(int size) {
        this.size = size;
        this.tabla = new Entry[size];

        for (int i = 0; i < size; i++) {
            tabla[i] = new Entry();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insertar(int key) {
        int indiceInicial = hash(key);
        int primerDeleted = -1;

        for (int i = 0; i < size; i++) {
            int indice = (indiceInicial + i) % size;

            if (tabla[indice].status == DELETED && primerDeleted == -1) {
                primerDeleted = indice;
            }

            if (tabla[indice].status == EMPTY) {
                int destino = (primerDeleted != -1) ? primerDeleted : indice;
                tabla[destino].key = key;
                tabla[destino].status = OCCUPIED;

                System.out.println("Clave " + key + " insertada en indice " + destino);
                return;
            }
        }

        if (primerDeleted != -1) {
            tabla[primerDeleted].key = key;
            tabla[primerDeleted].status = OCCUPIED;
            System.out.println("Clave " + key + " insertada reutilizando DELETED en indice " + primerDeleted);
        } else {
            System.out.println("Tabla llena. No se pudo insertar " + key);
        }
    }

    public void buscar(int key) {
        int indiceInicial = hash(key);

        for (int i = 0; i < size; i++) {
            int indice = (indiceInicial + i) % size;

            if (tabla[indice].status == EMPTY) {
                System.out.println("Clave " + key + " no encontrada.");
                return;
            }

            if (tabla[indice].status == OCCUPIED && tabla[indice].key == key) {
                System.out.println("Clave " + key + " encontrada en indice " + indice);
                return;
            }
        }

        System.out.println("Clave " + key + " no encontrada.");
    }

    public void eliminar(int key) {
        int indiceInicial = hash(key);

        for (int i = 0; i < size; i++) {
            int indice = (indiceInicial + i) % size;

            if (tabla[indice].status == EMPTY) {
                System.out.println("Clave no encontrada.");
                return;
            }

            if (tabla[indice].status == OCCUPIED && tabla[indice].key == key) {
                tabla[indice].status = DELETED;
                System.out.println("Clave " + key + " marcada como DELETED en indice " + indice);
                return;
            }
        }

        System.out.println("Clave no encontrada.");
    }

    public void imprimir() {
        System.out.println("\n== ESTADO DE LA TABLA ==");
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("Indice " + i + ": ");

            if (tabla[i].status == EMPTY) {
                System.out.println("EMPTY");
            } else if (tabla[i].status == DELETED) {
                System.out.println("DELETED");
            } else {
                System.out.println("OCCUPIED -> " + tabla[i].key);
            }
        }
    }

    private static class Entry {
        int key;
        int status;

        Entry() {
            this.status = EMPTY;
        }
    }
}
