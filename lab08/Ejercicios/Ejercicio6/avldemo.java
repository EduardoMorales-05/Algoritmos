class Node {
    int data;
    int bf; // factor de equilibrio
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.bf = 0;
        this.left = null;
        this.right = null;
    }

    public String toString() {
        return data + "(bf=" + bf + ")";
    }
}

class AVLTree {

    private Node root;

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {

        // 1. Insertar como BST
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node; // no duplicados
        }

        // 2. Actualizar factor de equilibrio
        node.bf = height(node.right) - height(node.left);

        // 3. Desbalance a la derecha
        if (node.bf > 1) {
            System.out.println("Desbalance en nodo " + node.data);
            System.out.println("Se aplica rotación simple izquierda");
            return rotateLeft(node);
        }

        // 4. Desbalance a la izquierda
        if (node.bf < -1) {
            System.out.println("Desbalance en nodo " + node.data);
            System.out.println("Se aplica rotación simple derecha");
            return rotateRight(node);
        }

        return node;
    }

    private Node rotateLeft(Node node) {

        Node newRoot = node.right;
        Node temp = newRoot.left;

        newRoot.left = node;
        node.right = temp;

        // Actualizar factores
        node.bf = height(node.right) - height(node.left);
        newRoot.bf = height(newRoot.right) - height(newRoot.left);

        return newRoot;
    }

    private Node rotateRight(Node node) {

        Node newRoot = node.left;
        Node temp = newRoot.right;

        newRoot.right = node;
        node.left = temp;

        // Actualizar factores
        node.bf = height(node.right) - height(node.left);
        newRoot.bf = height(newRoot.right) - height(newRoot.left);

        return newRoot;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Implementacio recOrrido en preorden

    public void preOrder() {
        System.out.print("Recorrido Preorden: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {

        if (node != null) {

            // Raíz
            System.out.print(node.data + " ");

            // Izquierda
            preOrder(node.left);

            // Derecha
            preOrder(node.right);
        }
    }
    // IMPRIMIR ÁRrbol

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String space, boolean isRoot) {

        if (node != null) {

            System.out.println(space + (isRoot ? "└── " : "├── ") + node);

            printTree(node.left, space + "   ", false);
            printTree(node.right, space + "   ", false);
        }
    }
}

public class avldemo {

    public static void main(String[] args) {

        AVLTree avl = new AVLTree();
        // PRIMER ÁRBOl
        System.out.println("Insertando 10");
        avl.insert(10);
        System.out.println("\nInsertando 20");
        avl.insert(20);
        System.out.println("\nInsertando 30");
        avl.insert(30);
        System.out.println("\nÁrbol AVL:");
        avl.printTree();
        avl.preOrder();
        // SEGUNDO ÁRBOL
        AVLTree avl2 = new AVLTree();
        avl2.insert(30);
        avl2.insert(20);
        avl2.insert(10);
        System.out.println("\n\nSegundo árbol AVL:");
        avl2.printTree();
        avl2.preOrder();
        // TERCER ÁRBOL
        AVLTree avl3 = new AVLTree();
        avl3.insert(50);
        avl3.insert(40);
        avl3.insert(60);
        avl3.insert(30);
        avl3.insert(45);
        System.out.println("\n\nTercer árbol AVL:");
        avl3.printTree();
        avl3.preOrder();
    }
}
