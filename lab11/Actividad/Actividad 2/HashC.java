package hash;

public class HashC<T> {

    public static final int EMPTY = 0;
    public static final int OCCUPIED = 1;
    public static final int DELETED = 2;

    private Register<T>[] table;
    private int[] status;
    private int size;

    public HashC(int size) {
        this.size = size;

        table = (Register<T>[]) new Register[size];
        status = new int[size];

        for (int i = 0; i < size; i++) {
            status[i] = EMPTY;
        }
    }

    private int hash(int key) {
        return Math.abs(key) % size;
    }

    public boolean insert(Register<T> reg) {

        int pos = hash(reg.getKey());
        int start = pos;

        do {

            if (status[pos] == EMPTY || status[pos] == DELETED) {

                table[pos] = reg;
                status[pos] = OCCUPIED;
                return true;
            }

            pos = (pos + 1) % size;

        } while (pos != start);

        return false;
    }

    public Register<T> search(int key) {

        int pos = hash(key);
        int start = pos;

        do {

            if (status[pos] == EMPTY) {
                return null;
            }

            if (status[pos] == OCCUPIED &&
                table[pos].getKey() == key) {

                return table[pos];
            }

            pos = (pos + 1) % size;

        } while (pos != start);

        return null;
    }

    public boolean delete(int key) {

        int pos = hash(key);
        int start = pos;

        do {

            if (status[pos] == EMPTY) {
                return false;
            }

            if (status[pos] == OCCUPIED &&
                table[pos].getKey() == key) {

                status[pos] = DELETED;
                return true;
            }

            pos = (pos + 1) % size;

        } while (pos != start);

        return false;
    }

    public void printTable() {

        System.out.println("\n----- TABLA HASH -----");

        for (int i = 0; i < size; i++) {

            System.out.print("[" + i + "] ");

            if (status[i] == EMPTY) {
                System.out.println("EMPTY");
            }
            else if (status[i] == DELETED) {
                System.out.println("DELETED");
            }
            else {
                System.out.println(table[i]);
            }
        }
    }
}
