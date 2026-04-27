public static <T> Node<T> insertarAlFinal(Node<T> head, T valor)
{
    Node<T> nuevo = new Node<>(valor);

    if(head == null)
    {
        return nuevo;
    }

    Node<T> actual = head;

    while(actual.next != null)
    {
        actual = actual.next;
    }

    actual.next = nuevo;

    return head;
}
