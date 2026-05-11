package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import exceptions.*;

/*
 * Implementación de un Árbol de Búsqueda Binaria (BST) Genérico.
 * La restricción Comparable garantiza que los elementos puedan ordenarse
 */
public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    // Estructura del Nodo: Base para la representación enlazada 
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

    // Atributo Raíz: Único nodo sin padre en el árbol 
    protected Node root;

    public LinkedBST() {
        this.root = null; // Inicializa como árbol vacío 
    }

    @Override
    public boolean isEmpty() {
        return this.root == null; // Verifica si el BST está vacío 
    }
