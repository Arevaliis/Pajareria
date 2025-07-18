package modelos;

/**
 * Clase que representa a un cliente
 */
public class Cliente {
    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    /**
     * Información del nuevo cliente
     *
     * @param nombre Nombre cliente
     * @param dni   DNI cliente
     * @param telefono  Teléfono cliente
     * @param email Email cliente
     */
    public Cliente(String nombre, String dni, String telefono, String email){
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    /** @return Nombre del cliente */
    public String getNombre() {
        return nombre;
    }

    /** @return DNI cliente */
    public String getDni() {
        return dni;
    }

    /** @return Teléfono del cliente */
    public String getTelefono() {
        return telefono;
    }

    /** @return Email del cliente */
    public String getEmail() {
        return email;
    }

    /** @param nombre Nuevo nombre del cliente */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @param dni Nuevo DNI del cliente */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /** @param telefono Nuevo teléfono del cliente */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /** @param email Nuevo email del cliente */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return String con la información del cliente */
    @Override
    public String toString() {
        return "Cliente: " + this.nombre + ", dni: " + this.dni + ", teléfono: " + this.telefono + ", email: " + this.email;
    }
}