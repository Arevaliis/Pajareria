package excepciones;

/**
 * Mensaje que se lanza si ya existe esa especie de pájaro en la base de datos
 */
public class ErrorYaExisteEspecie extends Exception {
    public ErrorYaExisteEspecie(String message) {
        super(message);
    }
}
