import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Biblioteca
{
    private BTree<Libro> arbol;

    public Biblioteca(int orden)
    {
        arbol = new BTree<>(orden);
    }

    public void agregarLibro(Libro libro)
    {
        arbol.insert(libro);
    }

    public boolean buscarLibro(String isbn)
    {
        Libro temp =
            new Libro(isbn, "", "", 0);

        return arbol.search(temp);
    }

    public void eliminarLibro(String isbn)
    {
        Libro temp =
            new Libro(isbn, "", "", 0);

        arbol.remove(temp);
    }

    public void mostrarLibros()
    {
        System.out.println(arbol);
    }

    public void cargarArchivo(String nombreArchivo)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));

            String linea;

            linea = br.readLine();

            while((linea = br.readLine()) != null)
            {
                String datos[] = linea.split(",");

                String isbn = datos[0];
                String titulo = datos[1];
                String autor = datos[2];
                int anio =
                    Integer.parseInt(datos[3]);

                Libro libro = new Libro(isbn, titulo, autor, anio);

                agregarLibro(libro);
            }

            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al leer archivo");
        }
    }
}
