package ejercicio3;

/**
 * Valida que esté vacía la estructura de datos.
 */
public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String msg) {
        super(msg);
    }
    public ExceptionIsEmpty() {
        super();
    }
}
