import Excepciones.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestionPajareria {
    static Scanner scanner = new Scanner(System.in);
    static boolean estaFuncionando = true;
    static ArrayList<Cliente> baseClientes = new ArrayList<>();
    static ArrayList<Pajaro> basePajaros = new ArrayList<>();
    static ArrayList<Venta> baseVentas = new ArrayList<>();

    public static void main(String[] args) {
       // baseClientes.add(new Cliente("JUAN","45454545F", "654454545", "juan@g.com"));

        // basePajaros.add(new Pajaro("Loro", "Verde", 56.32));
        // basePajaros.add(new Pajaro("Canario", "Amarillo", 24.50));

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
            case 4 -> ejecutarMenuVentasTotales();
            case 5 -> {
                Mensajes.saliendo();
                return false;
            }
        }
        return seguir();
    }

    /* ============== Clientes ============== */
    public static void ejecutarMenuCliente(){
        if (!baseClientes.isEmpty()){
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
        }else{
            Mensajes.noHayClientes();
            agregarCliente();
            ejecutarMenuCliente();
        }
    }

    public static Cliente buscarPorDni(String dni){
        Cliente c = null;

        for (Cliente cliente : baseClientes) {
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                Mensajes.mostarCliente(cliente);
                c = cliente;
            }
        }
        return c;
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

    public static String ingresarDni(){
        while (true){
            try {
                Mensajes.mensajeDni();
                String dni = scanner.nextLine().trim().toUpperCase();
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
        if (!basePajaros.isEmpty()){
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
        }else{
            Mensajes.noHayPajaros();
            agregarPajaro();
            ejecutarMenuPajaros();
        }
    }

    public static String ingresarEspecie(){
        while (true){
            try {
                Mensajes.mensajeEspecie();
                String especie = scanner.nextLine().trim().toUpperCase();
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

    public static Pajaro busquedaPorEspecie(){
        boolean seguirBuscando = true;

        while (seguirBuscando){
            String especie = ingresarEspecie();

            Mensajes.tipoEspecie(especie);
            for (Pajaro pajaro: basePajaros){
                if(pajaro.getEspecie().equalsIgnoreCase(especie)){
                    Mensajes.mostrarPajaro(pajaro);
                    return pajaro;
                }
            }

            Mensajes.noExistePajaro();
            Mensajes.volverBuscarPajaro();
            seguirBuscando = seguirModificandoProbando();
        }
        return null;
    }

    public static void listarPajaros(){
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

    /* ============== Venta ============== */
    public static void crearVenta(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        if (cliente != null){
            LocalDate date = LocalDate.now();
            String hoy = String.valueOf(date);
            Venta venta = new Venta(cliente, new ArrayList<>(), hoy);
            boolean seguirAgregando = true;

            while (seguirAgregando){
                Mensajes.saltoLinea();
                listarPajaros();
                Mensajes.comprarPajaro();
                Pajaro pajaro = busquedaPorEspecie();
                venta.getLineasDeVenta().add(pajaro);

                Mensajes.volverComprarPajaro();
                seguirAgregando = seguirModificandoProbando();
            }
            Mensajes.compraTotal(venta);
            baseVentas.add(venta);
        }

        if(cliente == null){
            Mensajes.clienteNoExiste();
            Mensajes.agregarClienteDuranteCompra();
            boolean resp = seguirModificandoProbando();
            if (resp){
               agregarCliente();
            }
        }
    }
    /* ============== Mostrar Ventas ============== */
    public static void ejecutarMenuVentasTotales(){
        if (!baseVentas.isEmpty()){
            Mensajes.menuMostarVentas();
            int opc = elegir_opcion(4);

            while (opc == -1) {
                Mensajes.menuClientes();
                opc = elegir_opcion(4);
            }

            switch (opc) {
                case 1 -> mostrarVentasTotales();
                case 2 -> mostrarVentasTotalesPorCliente();
                case 3 -> mostrarImporteTotalPorVenta();
                case 4 -> {
                    return;
                }
            }
            seguirMenuMostrarVentas();
        }else{
            Mensajes.noHayVentas();
        }
    }

    public static void mostrarVentasTotales(){
        for (Venta venta: baseVentas){
            Mensajes.mostrarVentasTotales(venta);
        }
    }

    public static void mostrarVentasTotalesPorCliente(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);
        
        if(cliente != null){
            for (Venta venta: baseVentas){
                if (venta.getCliente().getDni().equals(cliente.getDni())){
                    Mensajes.mostrarVentasTotales(venta);
                }
            }   
        }else{
            Mensajes.clienteNoExiste();
        }
        
    }

    public static void mostrarImporteTotalPorVenta(){
        int contador = 1;
        for (Venta venta: baseVentas){
            Mensajes.mensajeContador(contador++);
            double total = 0.00;
            for (Pajaro pajaro: venta.getLineasDeVenta()) {
                total += pajaro.getPrecio();
            }
            Mensajes.mensajeTotalVenta(total);
        }
    }

    public static void seguirMenuMostrarVentas(){
        Mensajes.mensajeVolverMenuVentasTotales();
        if (seguirModificandoProbando()){
            ejecutarMenuVentasTotales();
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
