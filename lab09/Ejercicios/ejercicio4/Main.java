public class Main
{
    public static void main(String[] args)
    {
        Biblioteca b =
            new Biblioteca(4);

        b.cargarArchivo("biblioteca.txt");

        System.out.println(
            "Contenido del árbol:");

        b.mostrarLibros();

        System.out.println(
            "\nBuscar ISBN:");

        System.out.println(
            b.buscarLibro(
                "9780132350884"));

        System.out.println(
            "\nEliminar libro:");

        b.eliminarLibro(
            "9780132350884");

        b.mostrarLibros();
    }
}
