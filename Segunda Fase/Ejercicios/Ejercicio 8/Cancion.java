public class Cancion {
    private String titulo;
    private String artista;
    private int duracionSeg;

    public Cancion(String titulo, String artista, int duracionSeg) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSeg = duracionSeg;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracionSeg + "s)";
    }
}

class NodeDoble<T> {
    T value;
    NodeDoble<T> next; // Puntero al siguiente 
    NodeDoble<T> prev; // Puntero al anterior 

    public NodeDoble(T value) {
        this.value = value;
    }
}
