package lab06.Actividades.actividadPilaLista;
import lab06.Actividades.Actividad1.ExceptionIsEmpty;

class StackLink<E> implements Stack<E> 
{
    private Node<E> top;

    public StackLink() 
    {
        this.top = null;
    }

    public void push(E x) 
    {
        Node<E> nuevo = new Node<E>(x);
        nuevo.setNext(top);
        top = nuevo;
    }

    public E pop() throws ExceptionIsEmpty 
    {
        if (isEmpty()) 
        {
            throw new ExceptionIsEmpty("La pila está vacía");
        }

        E dato = top.getData();
        top = top.getNext();
        return dato;
    }

    public E top() throws ExceptionIsEmpty 
    {
        if (isEmpty()) 
        {
            throw new ExceptionIsEmpty("La pila está vacía");
        }

        return top.getData();
    }

    public boolean isEmpty() 
    {
        return top == null;
    }

    public String toString() 
    {
        String resultado = "";
        Node<E> actual = top;

        while (actual != null) 
        {
            resultado += actual.getData() + " -> ";
            actual = actual.getNext();
        }

        resultado += "null";
        return resultado;
    }
}
