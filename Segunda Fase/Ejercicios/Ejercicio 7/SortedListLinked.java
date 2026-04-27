public class SortedListLinked<T extends Comparable<T>> extends ListLinked<T> {
    
    // Método para insertar manteniendo el orden ascendente
    public void insertOrden(T x) {
        // Caso: Lista vacía o el dato es menor al primero
        // Usamos getFirst() de tu archivo ListLinked.java
        if (isEmptyList() || x.compareTo(getFirst().value) < 0) {
            insertFirst(x); 
        } else {
            Node<T> actual = getFirst();
            
            // Buscamos la posición correcta comparando valores
            while (actual.next != null && actual.next.value.compareTo(x) < 0) {
                actual = actual.next;
            }
            
            // Insertamos el nuevo nodo entre los nodos encontrados
            Node<T> nuevoNodo = new Node<>(x);
            nuevoNodo.next = actual.next;
            actual.next = nuevoNodo;
        }
    }
}
