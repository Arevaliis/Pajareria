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
                        String dni = Consola.ingresarDni(scanner);
                        Cliente cliente = buscarPorDni(dni, baseClientes);
                        Validador.validarExistenciaCliente(cliente);
                    } catch (ErrorClienteNoExiste e) {
                        System.out.println(e.getMessage());
                    }
                    Mensajes.buscarDeNuevoCliente();
                }
                case 5 -> {
                    listarClientes(baseClientes);
                    return; // Para evitar el mensaje de volver a ejecutar esta acción, va directo al menú clientes
                }
                case 6 -> {return;}
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
     * Agrega un nuevo cliente
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void agregarCliente(ArrayList<Cliente> baseClientes, Scanner scanner){
        String dni = Consola.ingresarDni(scanner);
        Cliente cliente = buscarPorDni(dni, baseClientes);

        try {
            Validador.validarClienteNoExiste(cliente);
            Cliente clienteNuevo = new Cliente(Consola.ingresarNombre(scanner), dni, Consola.ingresarTelefono(baseClientes, scanner), Consola.ingresarEmail(baseClientes, scanner));
            confirmarAgregarCliente(clienteNuevo, baseClientes, scanner);
        } catch (ErrorYaExisteCliente e) {
            System.out.println(e.getMessage());
        }

        Mensajes.ingresarNuevoCliente();
    }

    /**
     * Solicita confirmación al usuario para llevar a cabo la ingreso del cliente a la base de datos
     *
     * @param cliente {@code Cliente} que va a ser agregado
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void confirmarAgregarCliente(Cliente cliente, ArrayList<Cliente> baseClientes, Scanner scanner){
        Mensajes.confirmarAgregarCliente(cliente);
        if (Repetir.deseaRepetirAccion(scanner)){
            baseClientes.add(cliente);
            Mensajes.clienteAgregado();
        }else{
            Mensajes.procesoAgregarClienteCancelado();
        }
    }

    /**
     * Eliminar a un cliente
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void  eliminarCliente(ArrayList<Cliente> baseClientes, Scanner scanner){
        String dni = Consola.ingresarDni(scanner);
        Cliente cliente = buscarPorDni(dni, baseClientes);

        try{
            Validador.validarExistenciaCliente(cliente);
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
        }else{
            Mensajes.eliminacionAbortada();
        }
    }

    /**
     * Modifica la información de un cliente
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void modificarCliente(ArrayList<Cliente> baseClientes, Scanner scanner){
        String dni = Consola.ingresarDni(scanner);
        Cliente cliente = buscarPorDni(dni, baseClientes);

        try {
            Validador.validarExistenciaCliente(cliente);
            Mensajes.menuModificarValores();
            int opc = SelectorOpciones.elegir_opcion(5, scanner);
            switch (opc){
                case 1 -> confirmarCambio("NOMBRE", cliente, Consola.ingresarNombre(scanner), cliente.getNombre(), scanner);
                case 2 -> {
                    String nuevoDni = Consola.ingresarDni(scanner);
                    Validador.validarDniDuplicado(dni, baseClientes);
                    confirmarCambio("DNI", cliente, nuevoDni, cliente.getDni(), scanner);
                }
                case 3 -> confirmarCambio("TELEFONO", cliente, Consola.ingresarTelefono(baseClientes, scanner), cliente.getTelefono(), scanner);
                case 4 -> confirmarCambio("EMAIL", cliente, Consola.ingresarEmail(baseClientes, scanner), cliente.getEmail(), scanner);
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
        }else{
            Mensajes.modificacionAbortada();
        }
    }

    /**
     * Muestra un listado ordenado por nombre con todos los clientes de la base de datos.
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