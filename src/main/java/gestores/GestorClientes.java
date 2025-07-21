package gestores;

import excepciones.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import modelos.Cliente;
import util.*;

/**
 * Clase que gestiona la funcionalidad de las opciones del menu cliente,
 * incluyendo el alta, modificación, listado, búsqueda y baja de clientes.
 *
 *  @author Jose Iglesias
 *  @version 4.0
 */
public class GestorClientes {

    /**
     * Muestra el menu clientes hasta que el usuario elija una opción válida
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void ejecutarMenuCliente(ArrayList<Cliente> baseClientes, Scanner scanner) {
        int opc;
        do {
            Mensajes.menuClientes();
            opc = SelectorOpciones.elegir_opcion(6, scanner);
            comprobarBaseDatosVacia(opc, baseClientes, scanner);
            seguirMenuClientes(opc, baseClientes, scanner);
        } while (opc == -1);
    }

    /**
     * Comprueba que la base de datos clientes no se encuentre vacía.
     * Sí es el caso, solo se podrán ejecutar las opciones alta cliente y volver.
     * Si ingresa otro valor, saltará un mensaje de advertencia.
     *
     * @param opc Número ingresado por el usuario
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void comprobarBaseDatosVacia(int opc, ArrayList<Cliente> baseClientes, Scanner scanner){
        if (baseClientes.isEmpty() && opc != 1 && opc != 6 ){
            Mensajes.baseClientesVacia();
        } else {
            ejecutarOpcionClientes(opc, baseClientes, scanner);
        }
    }

    /**
     * Ejecuta la opción elegida por el usuario.
     *
     * @param opc  Número ingresado por el usuario.
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void ejecutarOpcionClientes(int opc, ArrayList<Cliente> baseClientes, Scanner scanner){
        do {
            switch (opc) {
                case 1 -> agregarCliente(baseClientes, scanner);
                case 2 -> eliminarCliente(baseClientes, scanner);
                case 3 -> modificarCliente(baseClientes, scanner);
                case 4 -> {
                    try{
                        String dni = ingresarDni(scanner);
                        Cliente cliente = buscarPorDni(dni, baseClientes);
                        Validador.validandoExistenciaCliente(cliente);
                    } catch (ErrorClienteNoExiste e) {
                        System.out.println(e.getMessage());
                    }
                    Mensajes.buscarDeNuevoCliente();
                }
                case 5 -> {
                    listarClientes(baseClientes);
                    return;
                }
                case 6 -> {
                    return;
                }
            }
        } while (Repetir.deseaRepetirAccion(scanner));
    }

    /**
     * Busca al cliente mediante su DNI
     *
     * @param dni DNI ingresado por el usuario
     * @param baseClientes ArrayList con los Clientes
     *
     * @return Si existe el cliente devuelve una instancia de {@code Cliente}, si no devolverá {@code null}
     */
    public static Cliente buscarPorDni(String dni, ArrayList<Cliente> baseClientes){
        // Agregado baseClientes como parametro para poder testearlo
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
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return String con nombre del cliente
     */
    public static String ingresarNombre(Scanner scanner){
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
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return String con el DNI del cliente
     */
    public static String ingresarDni(Scanner scanner) {
        String dni;
        while (true) {
            try {
                Mensajes.mensajeDni();
                dni = scanner.nextLine().trim().toUpperCase();
                Validador.validandoDni(dni);
                return dni;
            } catch (ErrorIngresoDniException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el teléfono del cliente y comprueba que sea válido
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return String Teléfono del cliente
     */
    public static String ingresarTelefono(ArrayList<Cliente> baseClientes, Scanner scanner) {
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
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return String Email del cliente
     */
    public static String ingresarEmail(ArrayList<Cliente> baseClientes, Scanner scanner){
        String email;
        while (true){
            try {
                Mensajes.mensajeEmail();
                email = scanner.nextLine().trim().toLowerCase();
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
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void agregarCliente(ArrayList<Cliente> baseClientes, Scanner scanner){
        String dni = ingresarDni(scanner);
        Cliente cliente = buscarPorDni(dni, baseClientes);

        try {
            Validador.existeCliente(cliente);
            Cliente clienteNuevo = new Cliente(ingresarNombre(scanner), dni, ingresarTelefono(baseClientes, scanner), ingresarEmail(baseClientes, scanner));
            baseClientes.add(clienteNuevo);
            Mensajes.clienteAgregado();
        } catch (ErrorYaExisteCliente e) {
            System.out.println(e.getMessage());
        }

        Mensajes.ingresarNuevoCliente();
    }

    /**
     * Eliminar a un cliente
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void  eliminarCliente(ArrayList<Cliente> baseClientes, Scanner scanner){
        String dni = ingresarDni(scanner);
        Cliente cliente = buscarPorDni(dni, baseClientes);

        try{
            Validador.validandoExistenciaCliente(cliente);
            confirmarEliminacion(cliente, baseClientes, scanner);
        } catch (ErrorClienteNoExiste e) {
            System.out.println(e.getMessage());
        }
        Mensajes.ingresarOtroDni();
    }

    /**
     * Solicita confirmación al usuario para llevar a cabo la eliminación del cliente de la base de datos
     *
     * @param cliente {@code Cliente} que va a ser eliminado
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void confirmarEliminacion(Cliente cliente, ArrayList<Cliente> baseClientes, Scanner scanner){
        Mensajes.confirmarEliminacion();
        if (Repetir.deseaRepetirAccion(scanner)){
            baseClientes.remove(cliente);
            Mensajes.clienteEliminado();
        }
    }

    /**
     * Modifica la información de un cliente
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void modificarCliente(ArrayList<Cliente> baseClientes, Scanner scanner){
        String dni = ingresarDni(scanner);
        Cliente cliente = buscarPorDni(dni, baseClientes);

        try {
            Validador.validandoExistenciaCliente(cliente);
            Mensajes.menuModificarValores();
            int opc = SelectorOpciones.elegir_opcion(5, scanner);
            switch (opc){
                case 1 -> confirmarCambio("NOMBRE", cliente, ingresarNombre(scanner), cliente.getNombre(), scanner);
                case 2 -> {
                    String nuevoDni = ingresarDni(scanner);
                    Validador.validandoDniDuplicado(dni, baseClientes);
                    confirmarCambio("DNI", cliente, nuevoDni, cliente.getDni(), scanner);
                }
                case 3 -> confirmarCambio("TELEFONO", cliente, ingresarTelefono(baseClientes, scanner), cliente.getTelefono(), scanner);
                case 4 -> confirmarCambio("EMAIL", cliente, ingresarEmail(baseClientes, scanner), cliente.getEmail(), scanner);
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
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void confirmarCambio(String campo, Cliente cliente, String valorNuevo, String valorAntiguo, Scanner scanner){
        Mensajes.modificarCampo(campo);
        if (Repetir.deseaRepetirAccion(scanner)){ // Se reutiliza el método para obtener la confirmación del usuario
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
     *
     * @param baseClientes ArrayList con la base de datos clientes
     */
    public static void listarClientes(ArrayList<Cliente> baseClientes){
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
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void seguirMenuClientes(int opc, ArrayList<Cliente> baseClientes, Scanner scanner){
        if (opc != 6){
            Mensajes.mensajeVolverMenuClientes();
            if (Repetir.deseaRepetirAccion(scanner)){
                ejecutarMenuCliente(baseClientes, scanner);
            }
        }
    }
}