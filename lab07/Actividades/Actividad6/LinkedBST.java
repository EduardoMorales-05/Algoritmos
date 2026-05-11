package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import exceptions.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    
    // Clase interna Node y atributo root 

    // --- MÉTODO INSERTAR ---
    @Override
    public void insert(E data) throws ItemDuplicated {
        this.root = insertRecursive(this.root, data);
    }

    private Node insertRecursive(Node current, E data) throws ItemDuplicated {
        if (current == null) {
            return new Node(data); 
        }
        int cmp = data.compareTo(current.data);
        if (cmp < 0) {
            current.left = insertRecursive(current.left, data); 
        } else if (cmp > 0) {
            current.right = insertRecursive(current.right, data); 
        } else {
            throw new ItemDuplicated("El dato ya existe en el árbol."); 
        }
        return current;
    }

    // --- MÉTODO BUSCAR ---
    @Override
    public E search(E data) throws ItemNoFound {
        return searchRecursive(this.root, data);
    }

    private E searchRecursive(Node current, E data) throws ItemNoFound {
        if (current == null) {
            throw new ItemNoFound("El dato solicitado no existe."); 
        }
        int cmp = data.compareTo(current.data);
        if (cmp == 0) {
            return current.data; 
        }
        return cmp < 0 ? searchRecursive(current.left, data) : searchRecursive(current.right, data);
    }

    // --- MÉTODO ELIMINAR ---
    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("No se puede eliminar de un árbol vacío.");
        }
        // La implementación del borrado requiere manejar los 3 casos (hoja, 1 hijo, 2 hijos
        this.root = deleteRecursive(this.root, data);
    }

    private Node deleteRecursive(Node current, E data) {
        if (current == null) return null;
        int cmp = data.compareTo(current.data);
        if (cmp < 0) {
            current.left = deleteRecursive(current.left, data);
        } else if (cmp > 0) {
            current.right = deleteRecursive(current.right, data);
        } else {
            // Caso 1 y 2: Nodo hoja o con un solo hijo 
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;
            // Caso 3: Nodo con dos hijos (buscar sucesor en InOrden) 
            current.data = findMin(current.right);
            current.right = deleteRecursive(current.right, current.data);
        }
        return current;
    }

    private E findMin(Node node) {
        E min = node.data;
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    // --- MÉTODO TOSTRING ---
    @Override
    public String toString() {
        return "Árbol BST: [" + toStringRecursive(this.root) + "]"; 
    }

    private String toStringRecursive(Node current) {
        if (current == null) return "";
        return toStringRecursive(current.left) + " " + current.data + " " + toStringRecursive(current.right);
    }
}
