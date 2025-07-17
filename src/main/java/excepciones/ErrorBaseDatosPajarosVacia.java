package excepciones;

/**
 * Mensaje de error que se lanza cuando la base de datos de pájaros está vacía
 */
public class ErrorBaseDatosPajarosVacia extends Exception {
    public ErrorBaseDatosPajarosVacia(String mensaje) {
        super(mensaje);
    }
}