public static <T> boolean sonIguales(ListLinked<T> lista1, ListLinked<T> lista2) {
    // Si tienen distinto tamaño, no pueden ser iguales
    if (lista1.length() != lista2.length()) return false;

    Node<T> aux1 = lista1.getFirst();
    Node<T> aux2 = lista2.getFirst();

    while (aux1 != null) {
        // Comparamos el contenido de cada nodo
        if (!aux1.value.equals(aux2.value)) return false;
        
        aux1 = aux1.next;
        aux2 = aux2.next;
    }
    return true;
}
