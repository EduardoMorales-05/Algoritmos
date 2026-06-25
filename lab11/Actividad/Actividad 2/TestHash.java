package hash;

public class TestHash {

    public static void main(String[] args) {

        HashC<String> h = new HashC<>(13);

        h.insert(new Register<>(34, "Juan"));
        h.insert(new Register<>(3, "Ana"));
        h.insert(new Register<>(7, "Luis"));
        h.insert(new Register<>(30, "Maria"));
        h.insert(new Register<>(11, "Pedro"));
        h.insert(new Register<>(8, "Rosa"));
        h.insert(new Register<>(7, "Carlos"));
        h.insert(new Register<>(23, "Elena"));
        h.insert(new Register<>(41, "Miguel"));
        h.insert(new Register<>(16, "Lucia"));
        h.insert(new Register<>(34, "Diego"));

        h.printTable();

        System.out.println("\nEliminar 30");
        h.delete(30);

        System.out.println("\nEliminar 3");
        h.delete(3);
        
        h.printTable();

        System.out.println("\nBuscar 23");
        System.out.println(h.search(23));
        
        System.out.println("\nBuscar 3");
        System.out.println(h.search(3));
    }
}
