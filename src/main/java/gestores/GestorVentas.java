package gestores;

import excepciones.*;

import modelos.*;
import util.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static gestores.GestorPajaros.*;
import static gestores.GestorClientes.baseClientes;
import static util.SelectorOpciones.elegir_opcion;

/**
 * Clase que controla las ventas
 */
public class GestorVentas {
    static ArrayList<Venta> baseVentas = new ArrayList<>();

    /**
     * Inicia el proceso de venta.
     * Se encarga de las comprobaciones, si existe el cliente y si las bases de datos están vacías.
     */
    public static void iniciarVenta(){
        try {
            Validador.validandoBaseClientes(baseClientes);
            Validador.validandoBasPajaros(basePajaros);
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

            if(pajaro != null){
                int cantidad = ingresarStock();

                if (pajaro.getStock() - cantidad >= 0){
                    venta.getLineasDeVenta().add(pajaro);
                    total += calcularPrecioFinal(pajaro.getPrecio(), cantidad);
                    pajaro.setStock(pajaro.getStock() - cantidad);

                } else{
                    Mensajes.noHayStockSuficiente(pajaro.getStock());
                }
            }
             else{
                Mensajes.noExistePajaro();
            }

            Mensajes.volverComprarPajaro();
            seguirAgregando = Repetir.deseaRepetirAccion();
        }
        venta.setTotal(total);
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
        if (!venta.getLineasDeVenta().isEmpty()){
            Mensajes.compraTotal(venta);
            baseVentas.add(venta);
        }else{
            Mensajes.cestaVacia();
        }
    }

    /**
     * Muestra el menu de ventas totales para ver el historial de ventas.
     */
    public static void ejecutarMenuVentasTotales(){
        if (!baseVentas.isEmpty()){
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
        }else{
            Mensajes.noHayVentas();
        }
    }

    /**
     * Muestra todas las ventas realizadas
     */
    public static void mostrarVentasTotales(){
        for (Venta venta: baseVentas){
            Mensajes.mostrarVentasTotales(venta);
        }
    }

    /**
     * Obtiene todas las ventas realizadas por un cliente
     *
     * @return ArrayList con las ventas de un cliente si existe, si no Null.
     */
    public static ArrayList<Venta> obtenerVenta(){
        String dni = GestorClientes.ingresarDni();
        Cliente cliente = GestorClientes.buscarPorDni(dni);
        ArrayList<Venta> ventasCliente = new ArrayList<>();

        try{
            Validador.validandoExistenciaCliente(cliente);
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

        if (ventasClientes != null){
            for (Venta venta : ventasClientes) {
                Mensajes.mostrarVentasTotales(venta);
            }
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

        if(ventasClientes != null) {
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