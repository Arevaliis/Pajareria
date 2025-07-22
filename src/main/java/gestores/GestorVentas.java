package gestores;

import excepciones.*;

import modelos.*;
import util.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase que gestiona el proceso de venta.
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
            Validador.validarBaseClientesVacia(baseClientes);
            Validador.validarBasePajarosVacia(basePajaros);
            String dni = Consola.ingresarDni(scanner);
            Cliente cliente = GestorClientes.buscarPorDni(dni, baseClientes);
            Validador.validarExistenciaCliente(cliente);

            crearVenta(new Venta(cliente, new ArrayList<>(), obtenerFecha()), basePajaros, baseVentas, scanner);
        }catch (ErrorBaseDatosClientesVacia | ErrorBaseDatosPajarosVacia | ErrorClienteNoExiste e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Continúa el proceso de venta.
     * Agrega las especies y la cantidad que le pida el cliente.
     *
     * @param venta Instancia de venta
     * @param basePajaros ArrayList de Pájaros
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     */
    public static void crearVenta(Venta venta, ArrayList<Pajaro> basePajaros, ArrayList<Venta> baseVentas, Scanner scanner){
        boolean seguirAgregando = true;
        double total = 0.00;
        HashMap<Pajaro, Integer> cantidadEspecie = new HashMap<>();

        while (seguirAgregando){
            Mensajes.tituloEspecies();
            GestorPajaros.listarPajaros(basePajaros);
            Mensajes.comprarPajaro();
            Pajaro pajaro = GestorPajaros.busquedaPorEspecie(basePajaros, scanner);

            try{
                Validador.validarExistenciaPajaro(pajaro);

                int cantidad = Consola.ingresarStock(scanner);
                Validador.validarNoHayStock(cantidad, pajaro);

                venta.getLineasDeVenta().add(pajaro);

                total += calcularPrecioFinal(pajaro.getPrecio(), cantidad);
                pajaro.setStock(pajaro.getStock() - cantidad);

                cantidadEspecie.put(pajaro, cantidad); // Guarda la cantidad de cada pájaro comprado

            } catch (ErrorNoExistePajaro | ErrorNoHayStock e) {
                System.out.println(e.getMessage());
            }

            Mensajes.volverComprarPajaro();
            seguirAgregando = Repetir.deseaRepetirAccion(scanner);
        }

        confirmarVenta(venta, baseVentas, scanner, cantidadEspecie, total);
    }

    /**
     * Solicita la confirmación del cliente para la compra.
     * Si dice N se cancela la venta y devuelve las cantidades agregadas a las especies
     * Si dice S se ejecuta la venta
     *
     * @param venta Instancia de venta
     * @param baseVentas ArrayList de Ventas
     * @param scanner Scanner para leer los valores ingresados por el usuario
     * @param cantidadEspecie HashMap con las especies y cantidades agregadas
     * @param total Importe total de la venta
     */
    public static void confirmarVenta(Venta venta, ArrayList<Venta> baseVentas, Scanner scanner, HashMap<Pajaro, Integer> cantidadEspecie, double total){
        Mensajes.confirmarCompra();
        if (Repetir.deseaRepetirAccion(scanner)){
            venta.setTotal(total);
            baseVentas.add(venta);
            imprimirTicket(venta);
        }else{
            Mensajes.compraCancelada();
            // Proceso para devolver el stock original de las especies agregadas
            for (Map.Entry<Pajaro, Integer> entry : cantidadEspecie.entrySet()) {
                Pajaro pajaro = entry.getKey();
                Integer cantidad = entry.getValue();
                pajaro.setStock(pajaro.getStock() + cantidad);
            }
        }
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
            Validador.validarCestaCompraVacia(venta);
            Mensajes.ticketCompra(venta);
        } catch (ErrorCestaVacia e) {
            System.out.println(e.getMessage());
        }
    }
}