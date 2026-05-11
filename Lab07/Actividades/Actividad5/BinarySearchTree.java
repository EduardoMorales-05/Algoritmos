package bstreeInterface;

import exceptions.*;

/**
 * Interfaz genérica para un Árbol de Búsqueda Binaria.
 * Define las operaciones esenciales de gestión de nodos.
 * @param <E> Tipo de dato genérico que se almacenará en el árbol.
 */
public interface BinarySearchTree<E> {
    
    // Inserta un nuevo elemento en el árbol siguiendo las reglas de orden
    void insert(E data) throws ItemDuplicated;
    
    // Busca un elemento específico y lo retorna si existe
    E search(E data) throws ItemNoFound;
    
    // Elimina un nodo del árbol manteniendo la estructura jerárquica
    void delete(E data) throws ExceptionIsEmpty, ItemNoFound;
    
    // Indica si el árbol carece de nodos (raíz es null)
    boolean isEmpty();
}
