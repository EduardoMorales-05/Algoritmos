package lab06.Actividades.actividadPilaLista;

import lab06.Actividades.Actividad1.ExceptionIsEmpty;

public class Main 
{
    public static void main(String[] args) 
    {
        StackLink<Integer> pila = new StackLink<Integer>();

        try 
        {
            System.out.println("¿Está vacía? " + pila.isEmpty());

            pila.push(10);
            pila.push(20);
            pila.push(30);

            System.out.println("Pila: " + pila);

            System.out.println("Tope: " + pila.top());

            System.out.println("Elemento eliminado: " + pila.pop());
            System.out.println("Pila después de pop: " + pila);

            System.out.println("Elemento eliminado: " + pila.pop());
            System.out.println("Elemento eliminado: " + pila.pop());

            System.out.println("Intentando hacer pop en pila vacía...");
            pila.pop();
        } 
        catch (ExceptionIsEmpty e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
