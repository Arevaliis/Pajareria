package gestores;

import excepciones.ErrorIngresoDniException;
import excepciones.ErrorIngresoEmailException;
import excepciones.ErrorIngresoNombreException;
import excepciones.ErrorIngresoTelefonoException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import modelos.Cliente;
import util.*;

/**
 * Clase que gestiona la funcionalidad de las opciones del menu cliente
 */
public class GestorClientes {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Cliente> baseClientes = new ArrayList<>();

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
     * Comprueba que la base de datos clientes no se encuentre vacía. Sí es el caso, solo se podrán ejecutar las
     * opciones 1 y 6. Si ingresa otro valor, saltará un mensaje de advertencia.
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
                    String dni = ingresarDni();
                    Cliente cliente = buscarPorDni(dni);
                    if (cliente == null) {
                        Mensajes.clienteNoExiste();
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
     * @return Si existe el cliente devuelve una instancia de Cliente, si no devolverá null
     */
    public static Cliente buscarPorDni(String dni){
        Cliente cliente = null;

        for (Cliente usuario : baseClientes) {
            if (usuario.getDni().equalsIgnoreCase(dni)) {
                Mensajes.mostarCliente(usuario);
                cliente = usuario;
            }
        }
        return cliente;
    }

    /**
     * Pide al usuario que ingrese el nombre del cliente
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
     * Pide al usuario que ingrese el DNI del cliente
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
     * Comprueba que el DNI ingresado no pertenezca ya a otro cliente
     *
     * @param dni DNI Ingresado por el usuario
     * @return False si existe. True si no.
     */
    public static boolean comprobarDuplicidadDni(String dni) {
        for (Cliente cliente : baseClientes) {
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                Mensajes.yaExisteDni();
                return false;
            }
        }
        return true;
    }

    /**
     * Pide al usuario que ingrese el teléfono del cliente
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
            } catch (ErrorIngresoTelefonoException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (comprobarDuplicidadTelefono(telefono)) {
                return telefono;
            }
        }
    }

    /**
     * Comprueba que el teléfono ingresado no pertenezca ya un cliente
     *
     * @param telefono Teléfono ingresado por el usuario
     * @return False si existe. True si no.
     */
    public static boolean comprobarDuplicidadTelefono(String telefono) {
        for (Cliente cliente : baseClientes) {
            if (cliente.getTelefono().equalsIgnoreCase(telefono)) {
                Mensajes.telefonoRepetido();
                return false;
            }
        }
        return true;
    }

    /**
     * Pide al usuario que ingrese el email del cliente
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
            } catch (ErrorIngresoEmailException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (comprobarDuplicidadEmail(email)){
                return email;
            }
        }
    }

    /**
     * Comprueba que el email ingresado no pertenezca ya a otro cliente
     *
     * @param email Email del cliente
     * @return False si existe. True si no.
     */
    public static boolean comprobarDuplicidadEmail(String email){
        for (Cliente cliente: baseClientes){
            if (cliente.getEmail().equalsIgnoreCase(email)){
                Mensajes.emailRepetido();
                return false;
            }
        }
        return true;
    }

    /**
     * Permite agregar un nuevo cliente
     */
    public static void agregarCliente(){
        String dni = ingresarDni();
        if (buscarPorDni(dni) == null){
            Cliente cliente = new Cliente(ingresarNombre(), dni, ingresarTelefono(), ingresarEmail());
            baseClientes.add(cliente);
            Mensajes.clienteAgregado();
        }else{
            Mensajes.yaExisteDni();
        }
        Mensajes.ingresarNuevoCliente();
    }

    /**
     * Permite eliminar a un cliente
     */
    public static void  eliminarCliente(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        if (cliente != null){
            confirmarEliminacion(cliente);
        }else{
            Mensajes.clienteNoExiste();
        }
        Mensajes.ingresarOtroDni();
    }

    /**
     * Solicita la confirmación al usuario para llevar a cabo la eliminación del cliente de la base de datos
     *
     * @param cliente Cliente que va a ser eliminado
     */
    public static void confirmarEliminacion(Cliente cliente){
        Mensajes.confirmarEliminacion();
        if (Repetir.deseaRepetirAccion()){
            baseClientes.remove(cliente);
            Mensajes.clienteEliminado();
        }
    }

    /**
     * Permite modificar la información de un cliente
     */
    public static void modificarCliente(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        if (cliente != null){
            Mensajes.menuModificarValores();
            int opc = SelectorOpciones.elegir_opcion(5);
            switch (opc){
                case 1 -> confirmarCambio("NOMBRE", cliente, ingresarNombre(), cliente.getNombre());
                case 2 -> {
                    String nuevoDni = ingresarDni();
                    if (comprobarDuplicidadDni(nuevoDni)) {
                        confirmarCambio("DNI", cliente, nuevoDni, cliente.getDni());
                    }
                }
                case 3 -> confirmarCambio("TELEFONO", cliente, ingresarTelefono(), cliente.getTelefono());
                case 4 -> confirmarCambio("EMAIL", cliente, ingresarEmail(), cliente.getEmail());
                case 5 -> { return; }
            }
        }else{
            Mensajes.clienteNoExiste();
        }
        continuarModificando();
    }

    /**
     * Solicita la confirmación al usuario para llevar a cabo la modificación de la información del cliente
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
     * Pregunta al usuario si quiere volver a elegir la opción que acaba de hacer, evitando volver al menu.
     */
    public static void continuarModificando(){
        Mensajes.modificarAlgoMas();
        if (Repetir.deseaRepetirAccion()){
            modificarCliente();
        }
    }

    /**
     * Permite ver el listado con todos los clientes de la base de datos.
     */
    public static void listarClientes(){
        baseClientes.sort(Comparator.comparing(Cliente::getNombre)); // Ordena el ArrayList por el nombre de los clientes

        for (Cliente cliente: baseClientes){
            Mensajes.mostarCliente(cliente);
        }
    }

    /**
     * Pregunta al usuario si desea volver al menu clientes.
     * Si ingresa "S" vuelve al menu clientes, si no vuelve al menu principal.
     * Si el usuario selecciono la opción 6 no se ejecuta.
     *
     * @param opc Valor numérico ingresado por el usuario cuando se le solicitó en el menu clientes
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