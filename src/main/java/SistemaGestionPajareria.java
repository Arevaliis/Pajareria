import Excepciones.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestionPajareria {
    static Scanner scanner = new Scanner(System.in);
    static boolean estaFuncionando = true;
    static ArrayList<Cliente> baseClientes = new ArrayList<>();

    public static void main(String[] args) {
        baseClientes.add(new Cliente("Jose", "45454545f",  "654455445", "jj@g.com"));
        baseClientes.add(new Cliente("Juan", "45454545s",  "655555555", "ju@g.com"));

        while (estaFuncionando){
            Mensajes.menuInicial();
            int opc = elegir_opcion(5);
            if (opc != -1){
                estaFuncionando = ejecutarOpcion(opc);
            }
        }
    }

    public static int elegir_opcion(int limite) {
        int opc;
        try {
            System.out.print("Elija una opción: ");
            opc = Integer.parseInt(scanner.nextLine().trim());
            Validador.validandoRangoScanner(opc, limite);
        } catch (NumberFormatException e) {
            System.out.println("\nError -> No puede ingresar valores no numéricos.");
            return -1;
        } catch (ValorFueraRangoExcepcion e) {
            System.out.println("\nError -> " + e.getMessage());
            return -1;
        }
        return opc;
    }

    public static boolean ejecutarOpcion(int opc){
        switch (opc){
            case 1 -> ejecutarMenuCliente();
            case 2 -> ejecutarMenuPajaros();
            case 3 -> System.out.println("3");
            case 4 -> System.out.println("4");
            case 5 -> {
                Mensajes.saliendo();
                return false;
            }
        }
        return seguir();
    }

    public static void ejecutarMenuCliente(){
        Mensajes.menuClientes();
        int opc = elegir_opcion(6);

        while (opc == -1) {
            Mensajes.menuClientes();
            opc = elegir_opcion(6);
        }

        switch (opc) {
            case 1 -> agregarCliente();
            case 2 -> eliminarCliente();
            case 3 -> modificarCliente();
            case 4 -> {
                if (baseClientes.isEmpty()) {
                Mensajes.baseDatosVacia();
                return;
                }

                while (true){
                    String dni = ingresarDni();
                    Cliente cliente = buscarPorDni(dni);
                    if (cliente == null) {
                        Mensajes.clienteNoExiste();
                    }

                    Mensajes.buscarDeNuevo();
                    if (!seguirModificandoProbando()){
                        return;
                    }
                }

            }
            case 5 -> listarClientes();
            case 6 -> {
                return;
            }
        }

        seguirMenuClientes();
    }

    public static Cliente buscarPorDni(String dni){
        if (!baseClientes.isEmpty()) {
            for (Cliente cliente : baseClientes) {
                if (cliente.getDni().equalsIgnoreCase(dni)) {
                    System.out.println(cliente + "\n");
                    return cliente;
                }
            }
        }
        return null;
    }

    public static String ingresarNombre(){
        while (true){
            try {
                Mensajes.mensajeNombre();
                String nombre = scanner.nextLine().trim();
                Validador.validandoNombre(nombre);
                return nombre;
            } catch (ErrorIngresoNombreException e) {
                System.out.println("\nError -> " + e.getMessage());
            }
        }
    }

    public static String ingresarDni(){
        while (true){
            try {
                Mensajes.mensajeDni();
                String dni = scanner.nextLine().trim();
                Validador.valindandoDni(dni);
                return dni;
            } catch (ErrorIngresoDniException e) {
                System.out.println("\nError -> " + e.getMessage());
            }
        }
    }

    public static String ingresarTelefono(){
        while (true){
            boolean estaRepetido = false;
            try {
                Mensajes.mensajeTelefono();
                String telefono = scanner.nextLine().trim();
                Validador.validandoTelefono(telefono);
                for (Cliente cliente: baseClientes){
                    if (cliente.getTelefono().equalsIgnoreCase(telefono)){
                        Mensajes.telefonoRepetido();
                        estaRepetido = true;
                        break;
                    }
                }

                 if (!estaRepetido){
                     return telefono;
                 }

            } catch (ErrorIngresoTelefonoException e) {
                System.out.println("\nError -> " + e.getMessage());
            }
        }
    }

    public static String ingresarEmail(){
        while (true){
            boolean estaRepetido = false;
            try {
                Mensajes.mensajeEmail();
                String email = scanner.nextLine().trim();
                Validador.validandoEmail(email);
                for (Cliente cliente: baseClientes){
                    if (cliente.getEmail().equalsIgnoreCase(email)){
                        Mensajes.emailRepetido();
                        estaRepetido = true;
                        break;
                    }
                }

                if (!estaRepetido){
                    return email;
                }

            } catch (ErrorIngresoEmailException e) {
                System.out.println("\nError -> " + e.getMessage());
            }
        }
    }

    public static void agregarCliente(){
        boolean seguirProbando = true;

        while(seguirProbando){
            String dni = ingresarDni();
            if (buscarPorDni(dni) == null){
                Cliente cliente = new Cliente(ingresarNombre(), dni, ingresarTelefono(), ingresarEmail());
                baseClientes.add(cliente);
                Mensajes.clienteAgregado();
                Mensajes.agregarOtroCliente();
            }else{
                Mensajes.yaExisteDni();
                Mensajes.probarNuevamente();
            }
            seguirProbando = seguirModificandoProbando();
        }
    }

    public static void  eliminarCliente(){
        boolean seguirProbando = true;

        if(baseClientes.isEmpty()){
            Mensajes.baseDatosVacia();
            return;
        }

        while(seguirProbando){
            String dni = ingresarDni();
            Cliente cliente = buscarPorDni(dni);

            if (cliente != null){
                Mensajes.confirmarEliminacion();
                if (seguirModificandoProbando()){
                    baseClientes.remove(cliente);
                    Mensajes.clienteEliminado();
                }
            }else{
                    Mensajes.clienteNoExiste();
            }
            Mensajes.ingresarOtroDni();
            seguirProbando = seguirModificandoProbando();
        }
    }

    public static void modificarCliente(){
        boolean seguirModificando = true;

        if(baseClientes.isEmpty()){
            Mensajes.baseDatosVacia();
            return;
        }

        String dni = ingresarDni();

        while (seguirModificando){
            Cliente cliente = buscarPorDni(dni);
            if (cliente != null){
                Mensajes.menuModificarValores();
                int opc = elegir_opcion(5);
                switch (opc){
                    case 1 -> {
                        String nombre = ingresarNombre();
                        cliente.setNombre(nombre);
                    }
                    case 2 ->{
                        String dniNuevo = ingresarDni();
                        if (buscarPorDni(dniNuevo) == null){
                            cliente.setDni(dniNuevo);
                        }else{
                            Mensajes.yaExisteDni();
                        }
                    }
                    case 3 ->{
                        String telefono = ingresarTelefono();
                        cliente.setTelefono(telefono);
                    }
                    case 4 -> {
                        String email = ingresarEmail();
                        cliente.setEmail(email);
                    }
                    case 5 -> {
                        return;
                    }
                }
                Mensajes.modifcarAlgoMas();
                seguirModificando = seguirModificandoProbando();
            }else{
                Mensajes.clienteNoExiste();
                Mensajes.probarNuevamente();
                if (!seguirModificandoProbando()){
                    return;
                }
                dni = ingresarDni();
            }
        }
    }

    public static void listarClientes(){
        if(baseClientes.isEmpty()){
            Mensajes.baseDatosVacia();
            return;
        }

        for (Cliente cliente: baseClientes){
            System.out.println(cliente);
        }
    }

    public static void seguirMenuClientes(){
        Mensajes.mensajeVolverMenuClientes();
        if (seguirModificandoProbando()){
            ejecutarMenuCliente();
        }
    }

    /* Pájaros */
    public static void ejecutarMenuPajaros(){
        Mensajes.menuPajaros();
        int opc = elegir_opcion(4);

        while (opc == -1) {
            Mensajes.menuPajaros();
            opc = elegir_opcion(4);
        }

        switch (opc) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
            case 3 -> System.out.println("3");
            case 4 -> {}
        }
    }

    public static boolean seguir(){
        Mensajes.mensajeVolverMenu();
        if (!seguirModificandoProbando()){
            Mensajes.saliendo();
            return false;
        }
        return true;
    }

    public static boolean seguirModificandoProbando(){
        return scanner.nextLine().trim().equalsIgnoreCase("S");
    }
}
