package excepciones;

/**
 * Mensaje de error que se lanza cuando la base de datos de clientes está vacía
 */
public class ErrorBaseDatosClientesVacia extends RuntimeException {
    public ErrorBaseDatosClientesVacia(String message) {
        super(message);
    }
}