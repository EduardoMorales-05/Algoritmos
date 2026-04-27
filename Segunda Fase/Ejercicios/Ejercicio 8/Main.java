public class Main {
    public static void main(String[] args) {
        ColaReproduccion<Cancion> spotify = new ColaReproduccion<>();

        // Agregamos canciones a la cola inicial 
        spotify.agregarCancion(new Cancion("Bohemian Rhapsody", "Queen", 354));
        spotify.agregarCancion(new Cancion("Blinding Lights", "The Weeknd", 200));
        spotify.agregarCancion(new Cancion("Shape of You", "Ed Sheeran", 234));

        System.out.println("=== SISTEMA DE REPRODUCCIÓN SPOTIFY ===");
        spotify.mostrarReproduciendo(); // Inicia en la primera
        
        System.out.println("Siguiente...");
        spotify.reproducirSiguiente();
        spotify.mostrarReproduciendo(); // Blinding Lights
        
        System.out.println("Anterior...");
        spotify.reproducirAnterior();
        spotify.mostrarReproduciendo(); // Regresa a Bohemian Rhapsody instantáneamente 
    }
}
