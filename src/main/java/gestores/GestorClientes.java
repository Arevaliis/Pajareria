package gestores;

import excepciones.ErrorIngresoDniException;
import excepciones.ErrorIngresoEmailException;
import excepciones.ErrorIngresoNombreException;
import excepciones.ErrorIngresoTelefonoException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import modelos.Cliente;
import util.*;

public class GestorClientes {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Cliente> baseClientes = new ArrayList<>(
            List.of(
                    new Cliente("Juan", "45454545F", "654545454", "jj@j.com"),
                    new Cliente("María", "12345678A", "611223344", "maria@example.com"),
                    new Cliente("Pedro", "87654321B", "622334455", "pedro@example.com"),
                    new Cliente("Lucía", "11223344C", "633445566", "lucia@example.com"),
                    new Cliente("Carlos", "99887766D", "644556677", "carlos@example.com")
            )
    );

    public static void ejecutarMenuCliente() {
        int opc;
        do {
            Mensajes.menuClientes();
            opc = SelectorOpciones.elegir_opcion(6);
            comprobarBaseDatosVacia(opc);
            seguirMenuClientes(opc);
        } while (opc == -1);
    }

    public static void comprobarBaseDatosVacia(int opc){
        if (baseClientes.isEmpty() && opc != 1 && opc != 6 ){
            Mensajes.baseClientesVacia();
        } else {
            ejecutarOpcionClientes(opc);
        }
    }

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

    public static boolean comprobarDuplicidadDni(String dni) {
        for (Cliente cliente : baseClientes) {
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                Mensajes.yaExisteDni();
                return false;
            }
        }
        return true;
    }

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

    public static boolean comprobarDuplicidadTelefono(String telefono) {
        for (Cliente cliente : baseClientes) {
            if (cliente.getTelefono().equalsIgnoreCase(telefono)) {
                Mensajes.telefonoRepetido();
                return false;
            }
        }
        return true;
    }

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

    public static boolean comprobarDuplicidadEmail(String email){
        for (Cliente cliente: baseClientes){
            if (cliente.getEmail().equalsIgnoreCase(email)){
                Mensajes.emailRepetido();
                return false;
            }
        }
        return true;
    }

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

    public static void confirmarEliminacion(Cliente cliente){
        Mensajes.confirmarEliminacion();
        if (Repetir.deseaRepetirAccion()){
            baseClientes.remove(cliente);
            Mensajes.clienteEliminado();
        }
    }

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

    public static void confirmarCambio(String campo, Cliente cliente, String valorNuevo, String valorAntiguo){
        Mensajes.modificarCampo(campo);
        if (Repetir.deseaRepetirAccion()){
            switch (campo){
                case "NOMBRE" -> cliente.setNombre(valorNuevo);
                case "DNI" -> cliente.setDni(valorNuevo);
                case "TELEFONO" -> cliente.setTelefono(valorNuevo);
                case "EMAIL" -> cliente.setEmail(valorNuevo);
            }
            Mensajes.campoModificado(campo, valorNuevo, valorAntiguo);
        }
    }

    public static void continuarModificando(){
        Mensajes.modifcarAlgoMas();
        if (Repetir.deseaRepetirAccion()){
            modificarCliente();
        }
    }

    public static void listarClientes(){
        baseClientes.sort(Comparator.comparing(Cliente::getNombre));

        for (Cliente cliente: baseClientes){
            Mensajes.mostarCliente(cliente);
        }
    }

    public static void seguirMenuClientes(int opc){
        if (opc != 6){
            Mensajes.mensajeVolverMenuClientes();
            if (Repetir.deseaRepetirAccion()){
                ejecutarMenuCliente();
            }
        }
    }
}
