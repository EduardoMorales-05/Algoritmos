public static <T> ListLinked<T> invertirLista(ListLinked<T> lista)
{
    ListLinked<T> nuevaLista = new ListLinked<>();
    Node<T> actual = lista.getFirst();

    while(actual != null)
    {
        nuevaLista.insertFirst(actual.value);
        actual = actual.next;
    }

    return nuevaLista;
}
