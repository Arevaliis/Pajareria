package modelos;

public class Cliente {
    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    public Cliente(String nombre, String dni, String telefono, String email){
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente: " + this.nombre + ", dni: " + this.dni + ", teléfono: " + this.telefono + ", email: " + this.email;
    }
}