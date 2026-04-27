public class Tarea implements Comparable<Tarea> {
    private String titulo;
    private int prioridad; // 1 = Alta, 2 = Media, 3 = Baja
    private String estado;

    public Tarea(String titulo, int prioridad, String estado) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    @Override
    public int compareTo(Tarea otra) {
        // Compara las prioridades numéricamente para mantener el orden ascendente [cite: 168]
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    @Override
    public String toString() {
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }
}
