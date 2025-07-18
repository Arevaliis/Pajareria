package excepciones;

/**
 * Mensaje que se lanza si ya existe esa instancia de pájaro
 */
public class ErrorYaExisteEspecie extends Exception {
    public ErrorYaExisteEspecie(String message) {
        super(message);
    }
}
