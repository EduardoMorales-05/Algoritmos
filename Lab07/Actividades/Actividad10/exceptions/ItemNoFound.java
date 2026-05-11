package exceptions;

/**
 * Valida que el dato no se encuentra en la estructura de datos.
 */
public class ItemNoFound extends Exception {
    public ItemNoFound(String msg) {
        super(msg);
    }
    public ItemNoFound() {
        super();
    }
}