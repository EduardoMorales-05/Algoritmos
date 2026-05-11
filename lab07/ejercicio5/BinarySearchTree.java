package ejercicio5;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNoFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();
    void inOrder(); 
    void preOrder(); 
    void postOrder();
    E findMin() throws ExceptionIsEmpty;
    E findMax() throws ExceptionIsEmpty;
 }
