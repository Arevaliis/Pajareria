import Excepciones.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestionPajareria {
    static Scanner scanner = new Scanner(System.in);
    static boolean estaFuncionando = true;
    static ArrayList<Cliente> baseClientes = new ArrayList<>();
    static ArrayList<Pajaro> basePajaros = new ArrayList<>();

    public static void main(String[] args) {
        baseClientes.add(new Cliente("Jose", "45454545f",  "654455445", "jose@g.com"));
        baseClientes.add(new Cliente("Juan", "45454545s",  "655555555", "juan@g.com"));

        basePajaros.add(new Pajaro("Loro", "Verde", 56.32));
        basePajaros.add(new Pajaro("Canario", "Amarillo", 24.50));
        basePajaros.add(new Pajaro("Periquito", "Azul", 18.75));
        basePajaros.add(new Pajaro("Cotorra", "Rojo", 30.00));
        basePajaros.add(new Pajaro("Jilguero", "Naranja", 22.40));
        basePajaros.add(new Pajaro("Agapornis", "Verde", 35.60));

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
            System.out.println(e.getMessage());
            return -1;
        }
        return opc;
    }

    public static boolean ejecutarOpcion(int opc){
        switch (opc){
            case 1 -> ejecutarMenuCliente();
            case 2 -> ejecutarMenuPajaros();
            case 3 -> crearVenta();
            case 4 -> System.out.println("4");
            case 5 -> {
                Mensajes.saliendo();
                return false;
            }
        }
        return seguir();
    }

    /* ============== Clientes ============== */
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
                break;
                }

                do {
                    String dni = ingresarDni();
                    Cliente cliente = buscarPorDni(dni);
                    if (cliente == null) {
                        Mensajes.clienteNoExiste();
                    }

                    Mensajes.buscarDeNuevo();
                } while (seguirModificandoProbando());

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
                    Mensajes.mostarCliente(cliente);
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
                System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
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
            Mensajes.mostarCliente(cliente);
        }
    }

    public static void seguirMenuClientes(){
        Mensajes.mensajeVolverMenuClientes();
        if (seguirModificandoProbando()){
            ejecutarMenuCliente();
        }
    }

    /* ============== Pájaros ============== */
    public static void ejecutarMenuPajaros(){
        Mensajes.menuPajaros();
        int opc = elegir_opcion(4);

        while (opc == -1) {
            Mensajes.menuPajaros();
            opc = elegir_opcion(4);
        }

        switch (opc) {
            case 1 -> agregarPajaro();
            case 2 -> listarPajaros();
            case 3 -> busquedaPorEspecie();
            case 4 -> {
                return;
            }
        }

        seguirMenuPajaros();
    }

    public static String ingresarEspecie(){
        while (true){
            try {
                Mensajes.mensajeEspecie();
                String especie = scanner.nextLine().trim();
                Validador.validandoNombre(especie);
                return especie;
            }catch (ErrorIngresoNombreException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static String ingresarColor(){
        while (true){
            try {
                Mensajes.mensajeColor();
                String color = scanner.nextLine().trim().toUpperCase();
                Validador.validandoColor(color);
                return color;
            } catch (ErrorIngresoColor e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static double ingresarPrecio(){
        while (true){
            try {
                Mensajes.mensajePrecio();
                return (double) Math.round(Double.parseDouble(scanner.nextLine().trim()) * 100) /100;
            } catch (NumberFormatException e) {
                System.out.println("Error -> Debe ingresar un número. Use punto para los decimales.");
            }
        }
    }

    public static void agregarPajaro(){
        boolean seguirAgregando = true;

        while (seguirAgregando){
            basePajaros.add(new Pajaro(ingresarEspecie(), ingresarColor(), ingresarPrecio()));
            Mensajes.agregadoPajaroConExito();
            Mensajes.agregarOtroPajaro();
            seguirAgregando = seguirModificandoProbando();
        }

    }

    public static void busquedaPorEspecie(){
        if(basePajaros.isEmpty()){
            Mensajes.baseDatosVacia();
            return;
        }

        boolean seguirBuscando = true;

        while (seguirBuscando){
            String especie = ingresarEspecie();
            boolean pajaroEncontrado = false;

            Mensajes.tipoEspecie(especie);
            for (Pajaro pajaro: basePajaros){
                if(pajaro.getEspecie().equalsIgnoreCase(especie)){
                    Mensajes.mostrarPajaro(pajaro);
                    pajaroEncontrado = true;
                }
            }

            if (!pajaroEncontrado){
                Mensajes.noExistePajaro();
            }

            Mensajes.volverBuscarPajaro();
            seguirBuscando = seguirModificandoProbando();
        }
    }

    public static void listarPajaros(){
        if(basePajaros.isEmpty()){
            Mensajes.baseDatosVacia();
            return;
        }

        for (Pajaro pajaro: basePajaros){
            Mensajes.mostrarPajaro(pajaro);
        }
    }

    public static void seguirMenuPajaros(){
        Mensajes.mensajeVolverMenuPajaros();
        if (seguirModificandoProbando()){
            ejecutarMenuPajaros();
        }
    }

    /* ============== Ventas ============== */
    public static void crearVenta(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        if (cliente != null){
            LocalDate date = LocalDate.now();
            String hoy = String.valueOf(date);
            Venta venta = new Venta(cliente, new ArrayList<>(), hoy);
            boolean seguirAgregando = true;

            while (seguirAgregando){
                listarPajaros();
                Mensajes.comprarPajaro(); // Cambiarlo por menu especies
                String especie = ingresarEspecie();
                Pajaro pajaro = busquedaPorEspecie();

                Mensajes.volverComprarPajaro();
                seguirAgregando = seguirModificandoProbando();
            }
        }
    }

    /* ============== Funciones Generales ============== */

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
