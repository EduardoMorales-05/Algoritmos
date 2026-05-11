package ejercicio2;


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
    
    //RECORRIDO POST-ORDER ---
    @Override
    public void postOrder() {
        System.out.print("Recorrido Post-Order: ");
        postOrder(this.root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node != null) {
            // 1. Recorrer el subárbol izquierdo
            postOrder(node.left);
            
            // 2. Recorrer el subárbol derecho
            postOrder(node.right);
            
            // 3. Visitar la raíz (cabecera)
            System.out.print(node.data + " ");
        }
    }
    
    // MÍNIMO Y MÁXIMO ---
    
    // Encuentra el valor más pequeño del árbol
    public E findMin() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol está vacío.");
        return findMinNode(this.root);
    }

    private E findMinNode(Node node) {
        // El menor siempre está al extremo izquierdo
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    // Encuentra el valor más grande del árbol
    public E findMax() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol está vacío.");
        return findMaxNode(this.root);
    }

    private E findMaxNode(Node node) {
        // El mayor siempre está al extremo derecho
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }
    // --- DESTRUIR TODOS LOS NODOS ---
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol ya está vacío.");
        }

        this.root = null;
    }
    // --- CONTAR TODOS LOS NODOS ---
    public int countAllNodes() {
      return countAllNodes(this.root);
    }

    private int countAllNodes(Node node) {
      if (node == null) {
          return 0;
      }

      return 1 + countAllNodes(node.left) + countAllNodes(node.right);
    }
    // --- CONTAR NODOS NO HOJA ---
    public int countNodes() {
        return countNodes(this.root);
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }

        // Si es hoja
        if (node.left == null && node.right == null) {
            return 0;
        }

        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    // --- ALTURA DE UN SUBÁRBOL ---
    public int height(E x) {

        Node current = this.root;

        // BÚSQUEDA ITERATIVA
        while (current != null) {

            int cmp = x.compareTo(current.data);

            if (cmp == 0) {
                return calculateHeight(current);
            }

            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        // No existe
        return -1;
    }
    private int calculateHeight(Node node) {

        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }
    // --- AMPLITUD DEL ÁRBOL ---
    public int amplitude() {

        int h = calculateHeight(this.root);

        int max = 0;

        for (int i = 1; i <= h; i++) {

            int nodes = countLevel(this.root, i);

            if (nodes > max) {
                max = nodes;
            }
        }

        return max;
    }
    private int countLevel(Node node, int level) {

        if (node == null) {
            return 0;
        }

        if (level == 1) {
            return 1;
        }

        return countLevel(node.left, level - 1)
             + countLevel(node.right, level - 1);
    }
}
