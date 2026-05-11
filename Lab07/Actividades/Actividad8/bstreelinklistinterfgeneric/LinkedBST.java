package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import exceptions.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    
    // --- ESTRUCTURA BASE ---
    protected class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    protected Node root; 

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

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
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;
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

    // --- MÉTODO IN-ORDER ---
    @Override
    public void inOrder() {
        inOrder(this.root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }
    
  // RECORRIDO PRE-ORDER ---
    @Override
    public void preOrder() {
        System.out.print("Recorrido Pre-Order: ");
        preOrder(this.root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node != null) {
            // 1. Visitar la raíz (cabecera)
            System.out.print(node.data + " ");
            
            // 2. Recorrer el subárbol izquierdo
            preOrder(node.left);
            
            // 3. Recorrer el subárbol derecho
            preOrder(node.right);
        }
    }
}