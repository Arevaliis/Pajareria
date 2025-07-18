package gestores;

import excepciones.*;

import modelos.*;
import util.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static gestores.GestorPajaros.*;
import static gestores.GestorClientes.baseClientes;
import static util.SelectorOpciones.elegir_opcion;

/**
 * Clase que gestiona el proceso de venta y su historial.
 *
 * @author Jose Iglesias
 * @version 3.0
 */
public class GestorVentas {
    static ArrayList<Venta> baseVentas = new ArrayList<>(
            List.of(new Venta(
                    new Cliente("JUAN", "45454545F", "654545454", "jj@jj.com"),
                    new ArrayList<>(
                    List.of(new Pajaro("LORO", "VERDE", 5.23, 10))),
                    "2025-09-20"
            ))
    );

    /**
     * Inicia el proceso de venta.
     * Se encarga de las comprobaciones, si existe el cliente y si las bases de datos están vacías.
     */
    public static void iniciarVenta(){
        try {
            Validador.validandoBaseClientes(baseClientes);
            Validador.validandoBasePajaros(basePajaros);
            String dni = GestorClientes.ingresarDni();
            Cliente cliente = GestorClientes.buscarPorDni(dni);
            Validador.validandoExistenciaCliente(cliente);

            crearVenta(new Venta(cliente, new ArrayList<>(), obtenerFecha())); // Crea una instancia de Venta
        }catch (ErrorBaseDatosClientesVacia | ErrorBaseDatosPajarosVacia | ErrorClienteNoExiste e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Continúa el proceso de venta.
     * Agrega las especies y la cantidad que le pida el cliente.
     *
     * @param venta Instancia de venta creada anteriormente
     */
    public static void crearVenta(Venta venta){
        boolean seguirAgregando = true;
        double total = 0.00;

        while (seguirAgregando){
            Mensajes.tituloEspecies();
            listarPajaros();
            Mensajes.comprarPajaro();
            Pajaro pajaro = busquedaPorEspecie();

            try{
                Validador.noExistePajaro(pajaro);
                int cantidad = ingresarStock();
                Validador.noHayStock(cantidad, pajaro);

                venta.getLineasDeVenta().add(pajaro);
                total += calcularPrecioFinal(pajaro.getPrecio(), cantidad);
                pajaro.setStock(pajaro.getStock() - cantidad);
            } catch (ErrorNoExistePajaro | ErrorNoHayStock e) {
                System.out.println(e.getMessage());
            }

            Mensajes.volverComprarPajaro();
            seguirAgregando = Repetir.deseaRepetirAccion();
        }
        venta.setTotal(total); // Modifica el valor predefinido en la clase Ventas
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
            baseVentas.add(venta);
        } catch (ErrorCestaVacia e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra el menu de ventas totales para ver el historial de ventas.
     */
    public static void ejecutarMenuVentasTotales(){
        try {
            Validador.baseVentasVacia(baseVentas);
            Mensajes.menuMostrarVentas();
            int opc = elegir_opcion(5);

            while (opc == -1) {
                Mensajes.menuMostrarVentas();
                opc = elegir_opcion(5);
            }

            switch (opc) {
                case 1 -> mostrarVentasTotales();
                case 2 -> mostrarVentasTotalesPorCliente();
                case 3 -> mostrarImporteTotalPorVenta();
                case 4 -> mostrarImporteTotalVentasPorCliente();
                case 5 -> {
                    return;
                }
            }
            seguirMenuMostrarVentas();
        } catch (ErrorBaseVentasVacia e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra todas las ventas realizadas
     */
    public static void mostrarVentasTotales(){
        for (Venta venta: baseVentas){
            Mensajes.ticketCompra(venta);
        }
    }

    /**
     * Obtiene todas las ventas realizadas por un cliente
     *
     * @return ArrayList con las ventas de un cliente si existe, si no {@code null}.
     */
    public static ArrayList<Venta> obtenerVenta(){
        String dni = GestorClientes.ingresarDni();
        Cliente cliente = GestorClientes.buscarPorDni(dni);

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
     */
    public static void mostrarVentasTotalesPorCliente(){
        ArrayList<Venta> ventasClientes = obtenerVenta();

        if (ventasClientes == null){ return;}

        for (Venta venta : ventasClientes) {
            Mensajes.ticketCompra(venta);
        }

    }

    /**
     * Muestra el importe total de cada venta
     */
    public static void mostrarImporteTotalPorVenta(){
        int contador = 1;
        for (Venta venta: baseVentas){
            double total = 0.00;
            for (Pajaro pajaro: venta.getLineasDeVenta()) {
                total += pajaro.getPrecio();
            }
            Mensajes.mensajeTotalVenta(contador++, total);
        }
    }

    /**
     * Muestra el importe total gastado por el cliente ingresado
     */
    public static void mostrarImporteTotalVentasPorCliente(){
        ArrayList<Venta> ventasClientes = obtenerVenta();

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
     */
    public static void seguirMenuMostrarVentas(){
        Mensajes.mensajeVolverMenuVentasTotales();
        if (Repetir.deseaRepetirAccion()){
            ejecutarMenuVentasTotales();
        }
    }
}