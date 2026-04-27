public static <T> ListLinked<T> concatenarListas(ListLinked<T> lista1, ListLinked<T> lista2) {
    // Creamos una nueva instancia para no modificar las listas de entrada 
    ListLinked<T> nuevaLista = new ListLinked<>();
    
    // Primero, copiamos todos los elementos de la lista 1
    Node<T> actual = lista1.getFirst();
    while (actual != null) {
        nuevaLista.insertLast(actual.value); // Insertamos al final de la nueva
        actual = actual.next;
    }
    
    // Luego, hacemos lo mismo con la lista 2 
    actual = lista2.getFirst();
    while (actual != null) {
        nuevaLista.insertLast(actual.value);
        actual = actual.next;
    }
    
    return nuevaLista;
}
