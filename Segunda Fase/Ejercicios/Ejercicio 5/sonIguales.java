public static <T> boolean sonIguales(ListLinked<T> lista1, ListLinked<T> lista2) {
    // Si los tamaños no coinciden, es imposible que sean iguales 
    if (lista1.length() != lista2.length()) {
        return false;
    }

    // Usamos nodos auxiliares para recorrer ambas listas a la par
    Node<T> aux1 = lista1.getFirst(); 
    Node<T> aux2 = lista2.getFirst();

    while (aux1 != null) {
        // Comparamos el valor de los nodos actuales 
        // Se usa .equals porque son objetos genéricos <T>
        if (!aux1.value.equals(aux2.value)) {
            return false; // Encontró una diferencia
        }
        // Avanzamos al siguiente nodo en ambas listas
        aux1 = aux1.next;
        aux2 = aux2.next;
    }
    return true; // Recorrió todo y no halló diferencias
}
