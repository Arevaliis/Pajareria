package gestores;

import excepciones.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import modelos.Cliente;
import util.*;

/**
 * Clase que gestiona la funcionalidad de las opciones del menu cliente,
 * incluyendo el alta, modificación, listado, búsqueda y baja de clientes.
 *
 *  @author Jose Iglesias
 *  @version 3.0
 */
public class GestorClientes {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Cliente> baseClientes = new ArrayList<>(
            List.of(new Cliente("JUAN", "45454545F", "654545454", "jj@jj.com"),
                    new Cliente("JOSE", "54545454A", "654454545", "aa@jj.com"))
    );

    /**
     * Muestra el menu clientes hasta que el usuario elija una opción válida
     */
    public static void ejecutarMenuCliente() {
        int opc;
        do {
            Mensajes.menuClientes();
            opc = SelectorOpciones.elegir_opcion(6);
            comprobarBaseDatosVacia(opc);
            seguirMenuClientes(opc);
        } while (opc == -1);
    }

    /**
     * Comprueba que la base de datos clientes no se encuentre vacía.
     * Sí es el caso, solo se podrán ejecutar las opciones alta cliente y volver.
     * Si ingresa otro valor, saltará un mensaje de advertencia.
     *
     * @param opc Número ingresado por el usuario
     */
    public static void comprobarBaseDatosVacia(int opc){
        if (baseClientes.isEmpty() && opc != 1 && opc != 6 ){
            Mensajes.baseClientesVacia();
        } else {
            ejecutarOpcionClientes(opc);
        }
    }

    /**
     * Ejecuta la opción elegida por el usuario.
     *
     * @param opc Número ingresado por el usuario.
     */
    public static void ejecutarOpcionClientes(int opc){
        do {
            switch (opc) {
                case 1 -> agregarCliente();
                case 2 -> eliminarCliente();
                case 3 -> modificarCliente();
                case 4 -> {
                    try{
                        String dni = ingresarDni();
                        Cliente cliente = buscarPorDni(dni);
                        Validador.validandoExistenciaCliente(cliente);
                    } catch (ErrorClienteNoExiste e) {
                        System.out.println(e.getMessage());
                    }
                    Mensajes.buscarDeNuevoCliente();
                }
                case 5 -> {
                    listarClientes();
                    return;
                }
                case 6 -> {
                    return;
                }
            }
        } while (Repetir.deseaRepetirAccion());
    }

    /**
     * Busca al cliente mediante su DNI
     *
     * @param dni DNI ingresado por el usuario
     * @return Si existe el cliente devuelve una instancia de {@code Cliente}, si no devolverá {@code null}
     */
    public static Cliente buscarPorDni(String dni){
        Cliente cliente = null;

        for (Cliente usuario : baseClientes) {
            if (usuario.getDni().equalsIgnoreCase(dni)) {
                Mensajes.mostrarCliente(usuario);
                cliente = usuario;
            }
        }
        return cliente;
    }

    /**
     * Pide al usuario que ingrese el nombre del cliente y comprueba que sea válido
     *
     * @return String con nombre del cliente
     */
    public static String ingresarNombre(){
        while (true){
            try {
                Mensajes.mensajeNombre();
                String nombre = scanner.nextLine().trim().toUpperCase();
                Validador.validandoNombre(nombre);
                return nombre;
            } catch (ErrorIngresoNombreException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el DNI del cliente y comprueba que sea válido
     *
     * @return String con el DNI del cliente
     */
    public static String ingresarDni() {
        String dni;
        while (true) {
            try {
                Mensajes.mensajeDni();
                dni = scanner.nextLine().trim().toUpperCase();
                Validador.valindandoDni(dni);
                return dni;
            } catch (ErrorIngresoDniException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el teléfono del cliente y comprueba que sea válido
     *
     * @return String Teléfono del cliente
     */
    public static String ingresarTelefono() {
        String telefono;
        while (true) {
            try {
                Mensajes.mensajeTelefono();
                telefono = scanner.nextLine().trim();
                Validador.validandoTelefono(telefono);
                Validador.telefonoDuplicado(telefono, baseClientes);
                return telefono;
            } catch (ErrorIngresoTelefonoException | ErrorTelefonoDuplicado e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el email del cliente y comprueba que sea válido
     *
     * @return String Email del cliente
     */
    public static String ingresarEmail(){
        String email;
        while (true){
            try {
                Mensajes.mensajeEmail();
                email = scanner.nextLine().trim();
                Validador.validandoEmail(email);
                Validador.emailDuplicado(email, baseClientes);
                return email;
            } catch (ErrorIngresoEmailException | ErrorEmailDuplicado e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Agrega un nuevo cliente
     */
    public static void agregarCliente(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        try {
            Validador.existeCliente(cliente);
            Cliente clienteNuevo = new Cliente(ingresarNombre(), dni, ingresarTelefono(), ingresarEmail());
            baseClientes.add(clienteNuevo);
            Mensajes.clienteAgregado();
        } catch (ErrorYaExisteCliente e) {
            System.out.println(e.getMessage());
        }

        Mensajes.ingresarNuevoCliente();
    }

    /**
     * Eliminar a un cliente
     */
    public static void  eliminarCliente(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        try{
            Validador.validandoExistenciaCliente(cliente);
            confirmarEliminacion(cliente);
        } catch (ErrorClienteNoExiste e) {
            System.out.println(e.getMessage());
        }
        Mensajes.ingresarOtroDni();
    }

    /**
     * Solicita confirmación al usuario para llevar a cabo la eliminación del cliente de la base de datos
     *
     * @param cliente {@code Cliente} que va a ser eliminado
     */
    public static void confirmarEliminacion(Cliente cliente){
        Mensajes.confirmarEliminacion();
        if (Repetir.deseaRepetirAccion()){
            baseClientes.remove(cliente);
            Mensajes.clienteEliminado();
        }
    }

    /**
     * Modifica la información de un cliente
     */
    public static void modificarCliente(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        try {
            Validador.validandoExistenciaCliente(cliente);
            Mensajes.menuModificarValores();
            int opc = SelectorOpciones.elegir_opcion(5);
            switch (opc){
                case 1 -> confirmarCambio("NOMBRE", cliente, ingresarNombre(), cliente.getNombre());
                case 2 -> {
                    String nuevoDni = ingresarDni();
                    Validador.dniDuplicado(dni, baseClientes);
                    confirmarCambio("DNI", cliente, nuevoDni, cliente.getDni());
                }
                case 3 -> confirmarCambio("TELEFONO", cliente, ingresarTelefono(), cliente.getTelefono());
                case 4 -> confirmarCambio("EMAIL", cliente, ingresarEmail(), cliente.getEmail());
                case 5 -> { return; }
            }
        } catch (ErrorClienteNoExiste | ErrorDniDuplicado e) {
            System.out.println(e.getMessage());
        }
        Mensajes.buscarDeNuevoCliente();
    }

    /**
     * Solicita confirmación al usuario para llevar a cabo la modificación de la información del cliente
     *
     * @param campo Campo que va a ser modificado
     * @param cliente Cliente modificado
     * @param valorNuevo Valor nuevo
     * @param valorAntiguo  Valor antiguo
     */
    public static void confirmarCambio(String campo, Cliente cliente, String valorNuevo, String valorAntiguo){
        Mensajes.modificarCampo(campo);
        if (Repetir.deseaRepetirAccion()){ // Se reutiliza el método para obtener la confirmación del usuario
            switch (campo){
                case "NOMBRE" -> cliente.setNombre(valorNuevo);
                case "DNI" -> cliente.setDni(valorNuevo);
                case "TELEFONO" -> cliente.setTelefono(valorNuevo);
                case "EMAIL" -> cliente.setEmail(valorNuevo);
            }
            Mensajes.campoModificado(campo, valorNuevo, valorAntiguo);
        }
    }

    /**
     * Muestra un listado con todos los clientes de la base de datos.
     */
    public static void listarClientes(){
        baseClientes.sort(Comparator.comparing(Cliente::getNombre)); // Ordena el ArrayList por el nombre de los clientes

        for (Cliente cliente: baseClientes){
            Mensajes.mostrarCliente(cliente);
        }
    }

    /**
     * Pregunta al usuario si desea volver al menu clientes.
     * Si ingresa "S" vuelve al menu clientes, si no vuelve al menu principal.
     * Si el usuario selecciono la opción 6 no se ejecuta.
     *
     * @param opc Valor numérico ingresado en el menu clientes
     */
    public static void seguirMenuClientes(int opc){
        if (opc != 6){
            Mensajes.mensajeVolverMenuClientes();
            if (Repetir.deseaRepetirAccion()){
                ejecutarMenuCliente();
            }
        }
    }
}