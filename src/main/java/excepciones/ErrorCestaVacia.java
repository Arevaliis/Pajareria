package excepciones;

/**
 * Mensaje que salta cuando el cliente termina el proceso de comprar con la cesta vacía
 */
public class ErrorCestaVacia extends Exception {
    public ErrorCestaVacia(String message) {
        super(message);
    }
}
