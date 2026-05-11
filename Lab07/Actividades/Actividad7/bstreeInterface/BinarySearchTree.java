package bstreeInterface;
import exceptions.*;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNoFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();
    // Agrega esta línea para que LinkedBST pueda heredarla correctamente
    void inOrder(); 
}
