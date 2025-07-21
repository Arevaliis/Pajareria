package gestores;

import excepciones.*;

import modelos.*;
import util.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static gestores.GestorPajaros.*;
import static util.SelectorOpciones.elegir_opcion;

/**
 * Clase que gestiona el proceso de venta y su historial.
 *
 * @author Jose Iglesias
 * @version 4.0
 */
public class GestorVentas {

    /**
     * Inicia el proceso de venta.
     * Se encarga de las comprobaciones, si existe el cliente y si las bases de datos están vacías.
     *
     * @param baseClientes ArrayList de clientes
     * @param basePajaros ArrayList de Pájaros
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void iniciarVenta(ArrayList<Cliente> baseClientes, ArrayList<Pajaro> basePajaros, ArrayList<Venta> baseVentas, Scanner scanner){
        try {
            Validador.validandoBaseClientes(baseClientes);
            Validador.validandoBasePajaros(basePajaros);
            String dni = GestorClientes.ingresarDni(scanner);
            Cliente cliente = GestorClientes.buscarPorDni(dni, baseClientes);
            Validador.validandoExistenciaCliente(cliente);

            crearVenta(new Venta(cliente, new ArrayList<>(), obtenerFecha()), basePajaros, baseVentas, scanner); // Pasa como parametro lka instancia de Venta creada
        }catch (ErrorBaseDatosClientesVacia | ErrorBaseDatosPajarosVacia | ErrorClienteNoExiste e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Continúa el proceso de venta.
     * Agrega las especies y la cantidad que le pida el cliente.
     *
     * @param venta Instancia de venta creada anteriormente
     * @param basePajaros ArrayList de Pájaros
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void crearVenta(Venta venta, ArrayList<Pajaro> basePajaros, ArrayList<Venta> baseVentas, Scanner scanner){
        boolean seguirAgregando = true;
        double total = 0.00;

        while (seguirAgregando){
            Mensajes.tituloEspecies();
            listarPajaros(basePajaros);
            Mensajes.comprarPajaro();
            Pajaro pajaro = busquedaPorEspecie(basePajaros, scanner);

            try{
                Validador.noExistePajaro(pajaro);
                int cantidad = ingresarStock(scanner);
                Validador.noHayStock(cantidad, pajaro);

                venta.getLineasDeVenta().add(pajaro);
                total += calcularPrecioFinal(pajaro.getPrecio(), cantidad);
                pajaro.setStock(pajaro.getStock() - cantidad);
            } catch (ErrorNoExistePajaro | ErrorNoHayStock e) {
                System.out.println(e.getMessage());
            }

            Mensajes.volverComprarPajaro();
            seguirAgregando = Repetir.deseaRepetirAccion(scanner);
        }
        venta.setTotal(total); // Modifica el valor predefinido en la clase Ventas
        baseVentas.add(venta);
        imprimirTicket(venta);
    }

    /**
     * Calcula el importe de compra según el precio del pájaro y la cantidad deseada.
     *
     * @param precio Precio del pájaro
     * @param cantidad  Cantidad de unidades compradas.
     *
     * @return double con el precio total
     */
    public static double calcularPrecioFinal(double precio, int cantidad){
        return precio * cantidad;
    }

    /**
     * Obtiene el dia actual
     *
     * @return String con el día actual
     */
    public static String obtenerFecha(){
        LocalDate date = LocalDate.now();
        return String.valueOf(date);
    }

    /**
     * Muestra por pantalla el ticket de compra
     *
     * @param venta Venta con toda la información de la venta
     */
    public static void imprimirTicket(Venta venta){
        try {
            Validador.cestaVacia(venta);
            Mensajes.ticketCompra(venta);
        } catch (ErrorCestaVacia e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra el menu de ventas totales para ver el historial de ventas.
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void ejecutarMenuVentasTotales(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        try {
            Validador.baseVentasVacia(baseVentas);

            int opc;
            do {
                Mensajes.menuMostrarVentas();
                opc = elegir_opcion(5, scanner);
            } while (opc == -1);

            switch (opc) {
                case 1 -> mostrarVentasTotales(baseVentas);
                case 2 -> mostrarVentasTotalesPorCliente(baseClientes, baseVentas, scanner);
                case 3 -> mostrarImporteTotalPorVenta(baseVentas);
                case 4 -> mostrarImporteTotalVentasPorCliente(baseClientes, baseVentas, scanner);
                case 5 -> {return;}
            }
            seguirMenuMostrarVentas(baseClientes, baseVentas, scanner);
        } catch (ErrorBaseVentasVacia e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra todas las ventas realizadas
     *
     * @param baseVentas ArrayList de Ventas
     */
    public static void mostrarVentasTotales(ArrayList<Venta> baseVentas){
        for (Venta venta: baseVentas){
            Mensajes.ticketCompra(venta);
        }
    }

    /**
     * Obtiene todas las ventas realizadas por un cliente
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return ArrayList con las ventas de un cliente si existe, si no {@code null}.
     */
    public static ArrayList<Venta> obtenerVenta(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        String dni = GestorClientes.ingresarDni(scanner);
        Cliente cliente = GestorClientes.buscarPorDni(dni, baseClientes);

        try{
            Validador.validandoExistenciaCliente(cliente);

            ArrayList<Venta> ventasCliente = new ArrayList<>();
            for (Venta venta : baseVentas) {
                if (venta.getCliente().getDni().equals(cliente.getDni())) {
                    ventasCliente.add(venta);
                }
            }
            Validador.validandoVentasClientes(ventasCliente);
            return ventasCliente;
        }catch (ErrorClienteNoExiste | ErrorNoVentasCliente e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Muestra todas las ventas del cliente ingresado
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void mostrarVentasTotalesPorCliente(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        ArrayList<Venta> ventasClientes = obtenerVenta(baseClientes, baseVentas, scanner);

        if (ventasClientes == null){ return;}

        for (Venta venta : ventasClientes) {
            Mensajes.ticketCompra(venta);
        }

    }

    /**
     * Muestra el importe total de cada venta
     *
     * @param baseVentas ArrayList de Ventas
     */
    public static void mostrarImporteTotalPorVenta(ArrayList<Venta> baseVentas){
        int contador = 1;
        for (Venta venta: baseVentas){
            Mensajes.mensajeTotalVenta(contador++, venta.getTotal());
        }
    }

    /**
     * Muestra el importe total gastado por el cliente ingresado
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void mostrarImporteTotalVentasPorCliente(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        ArrayList<Venta> ventasClientes = obtenerVenta(baseClientes, baseVentas, scanner);

        if (ventasClientes == null){ return;}

        double totalVentas = 0.00;
        String cliente = "";

        for (Venta venta : ventasClientes) {
            cliente = venta.getCliente().getNombre();
            for (Pajaro pajaro : venta.getLineasDeVenta()) {
                totalVentas += pajaro.getPrecio();
            }
        }
        Mensajes.importeTotalVentasCliente(cliente, totalVentas);
    }

    /**
     * Pregunta al usuario si desea volver al menu ventas totales.
     * Si ingresa "S" vuelve al menu ventas totales, si no vuelve al menu principal.
     *
     * @param baseClientes ArrayList de clientes
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void seguirMenuMostrarVentas(ArrayList<Cliente> baseClientes, ArrayList<Venta> baseVentas, Scanner scanner){
        Mensajes.mensajeVolverMenuVentasTotales();
        if (Repetir.deseaRepetirAccion(scanner)){
            ejecutarMenuVentasTotales(baseClientes, baseVentas, scanner);
        }
    }
}