package ejercicio4;

/**
 * Valida que el dato ya existe en la estructura de datos.
 */
public class ItemDuplicated extends Exception {
    public ItemDuplicated(String msg) {
        super(msg);
    }
    public ItemDuplicated() {
        super();
    }
}
