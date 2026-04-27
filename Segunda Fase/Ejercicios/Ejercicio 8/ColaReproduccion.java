public class ColaReproduccion<T> {
    private NodeDoble<T> primero, ultimo, actual;

    public void agregarCancion(T cancion) {
        NodeDoble<T> nuevo = new NodeDoble<>(cancion);
        if (primero == null) {
            primero = ultimo = actual = nuevo;
        } else {
            ultimo.next = nuevo;
            nuevo.prev = ultimo; // Enlace hacia atrás 
            ultimo = nuevo;
        }
    }

    public T reproducirSiguiente() {
        if (actual != null && actual.next != null) actual = actual.next;
        return actual.value; // Avanza al siguiente nodo 
    }

    public T reproducirAnterior() {
        // Operación optimizada O(1) gracias al puntero prev 
        if (actual != null && actual.prev != null) actual = actual.prev;
        return actual.value;
    }

    public void mostrarReproduciendo() {
        if (actual != null) System.out.println(" Reproduciendo: " + actual.value);
    }
}
