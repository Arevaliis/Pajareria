import Excepciones.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// TODO DOBLE ENTER
// TODO Mostrar importe total de ventas por cliente
// TODO Gestión de stock (disminuir cantidad de pájaros disponibles al vender)
// TODO Ordenar clientes o pájaros por campos (por nombre, especie, etc.)

public class SistemaGestionPajareria {
    static Scanner scanner = new Scanner(System.in);
    static boolean estaFuncionando = true;
    static ArrayList<Cliente> baseClientes = new ArrayList<>();
    static ArrayList<Pajaro> basePajaros = new ArrayList<>();
    static ArrayList<Venta> baseVentas = new ArrayList<>();

    public static void main(String[] args) {

        basePajaros.add(new Pajaro("Loro", "Verde", 56.32));
        basePajaros.add(new Pajaro("Canario", "Amarillo", 24.50));

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

    /* ============== Clientes ============ */
    public static void ejecutarMenuCliente() {
        int opc;
        do {
            Mensajes.menuClientes();
            opc = elegir_opcion(6);
            opcionElegidaClientes(opc);
            seguirMenuClientes(opc);
        } while (opc == -1);
    }

    public static void opcionElegidaClientes(int opc){
        if (baseClientes.isEmpty() && opc != 1 && opc != 6 ){
            Mensajes.baseDatosVacia();
        } else {
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
                        Mensajes.buscarDeNuevo();
                    }
                    case 5 -> {
                        listarClientes();
                        return;
                    }
                    case 6 -> {
                        return;
                    }
                }
            } while (seguirModificandoProbando());
        }
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
        if (seguirModificandoProbando()){
            baseClientes.remove(cliente);
            Mensajes.clienteEliminado();
        }
    }

    public static void modificarCliente(){
        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        if (cliente != null){
            Mensajes.menuModificarValores();
            int opc = elegir_opcion(5);
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
        if (seguirModificandoProbando()){
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
        if (seguirModificandoProbando()){
            modificarCliente();
        }
    }

    public static void listarClientes(){
        for (Cliente cliente: baseClientes){
            Mensajes.mostarCliente(cliente);
        }
    }

    public static void seguirMenuClientes(int opc){
        if (opc != 6){
            Mensajes.mensajeVolverMenuClientes();
            if (seguirModificandoProbando()){
                ejecutarMenuCliente();
            }
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
    public static boolean comprobarBasesVacias(){
        if (baseClientes.isEmpty()) {
            Mensajes.vacioBaseClienteDuranteCompra();
            return true;
        }

        if (basePajaros.isEmpty()){
            Mensajes.vacioBasePajarosDuranteCompra();
            return true;
        }
        return false;
    }
    public static void crearVenta(){
        if (comprobarBasesVacias()){
            return;
        }

        String dni = ingresarDni();
        Cliente cliente = buscarPorDni(dni);

        if(cliente == null){
            Mensajes.clienteNoExiste();
        } else{
            LocalDate date = LocalDate.now();
            String hoy = String.valueOf(date);
            Venta venta = new Venta(cliente, new ArrayList<>(), hoy);
            boolean seguirAgregando = true;

            while (seguirAgregando){
                Mensajes.saltoLinea();
                listarPajaros();
                Mensajes.comprarPajaro();
                Pajaro pajaro = busquedaPorEspecie();

                if (pajaro != null){
                    venta.getLineasDeVenta().add(pajaro);
                    Mensajes.volverComprarPajaro();
                    seguirAgregando = seguirModificandoProbando();
                }
            }
            Mensajes.compraTotal(venta);
            baseVentas.add(venta);
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