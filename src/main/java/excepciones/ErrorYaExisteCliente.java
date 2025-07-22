package excepciones;

/**
 * Mensaje de error que se lanza cuando el DNI del cliente ingresado ya pertenece a otro cliente de la base de datos
 */
public class ErrorYaExisteCliente extends Exception {
    public ErrorYaExisteCliente(String message) {
        super(message);
    }
}