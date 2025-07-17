package util;

import excepciones.*;
import modelos.Cliente;
import modelos.Pajaro;
import modelos.Venta;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase para validar los valores ingresados por el usuario mediante la consola
 */
public class Validador {

    /**
     * Comprueba que el valor dado por el usuario está dentro del rango permitido
     *
     * @param opc Valor numérico ingresado por el usuario
     * @param valorTop Valor máximo del rango
     * @throws ValorFueraRangoExcepcion Error si opc esta fuera del rango permitido
     */
    public static void validandoRango(int opc, int valorTop) throws ValorFueraRangoExcepcion {
        if (0 >= opc || opc > valorTop ){
            throw new ValorFueraRangoExcepcion("\nError -> El valor ingresado debe estar entre 1 y " + valorTop + ".");
        }
    }

    /**
     * Comprueba que el nombre no contenga ni números ni símbolos.
     *
     * @param nombre Nombre ingresado por el usuario
     * @throws ErrorIngresoNombreException Error si el nombre contiene un número o un símbolo
     */
    public static void validandoNombre(String nombre) throws ErrorIngresoNombreException {
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+")){
            throw new ErrorIngresoNombreException("\nError -> Valor mal ingresado. El nombre solo puede contener letras.");
        }
    }

    /**
     * Valida que el DNI este en el formato correcto
     *
     * @param dni DNI ingresado por el usuario
     * @throws ErrorIngresoDniException Error si el usuario no cumple con el formato del DNI. 8 números y 1 letra.
     */
    public static void valindandoDni(String dni) throws ErrorIngresoDniException {
        if (!dni.matches("^\\d{8}[A-Za-z]$")){
            throw new ErrorIngresoDniException("\nError -> Valor mal ingresado. El dni debe tener 8 números y 1 letra al final.");
        }
    }

    /**
     * Valida que el teléfono ingresado sea válido
     *
     * @param telefono Teléfono ingresado por el usuario
     * @throws ErrorIngresoTelefonoException Error si el teléfono ingresado no es válido
     */
    public static void validandoTelefono(String telefono) throws ErrorIngresoTelefonoException {
        if (!telefono.matches("^[6789]\\d{8}$")){
            throw new ErrorIngresoTelefonoException("\nError -> Valor mal ingresado. Deben empezar por (6,7,8,9) y luego 8 números mas.");
        }
    }

    /**
     * Comprueba que el email ingresado sea válido
     *
     * @param email Email ingresado por el usuario
     * @throws ErrorIngresoEmailException Error si el email ingresado no es válido
     */
    public static void validandoEmail(String email) throws ErrorIngresoEmailException {
        if (!email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$")){
            throw new ErrorIngresoEmailException("\nError -> Valor mal ingresado. El email debe tener formato válido: usuario@dominio.extensión (mínimo 2 letras)");
        }
    }

    /**
     * Comprueba que el color que se ingresa sea un color
     *
     * @param color Color ingresado por el usuario
     * @throws ErrorIngresoColor Error si el color ingresado no está en el Array
     */
    public static void validandoColor(String color) throws ErrorIngresoColor{
        ArrayList<String> colores = new ArrayList<>(Arrays.asList(
                "ROJO",
                "AZUL",
                "VERDE",
                "AMARILLO",
                "BLANCO",
                "NEGRO",
                "NARANJA",
                "GRIS",
                "MARRÓN",
                "VIOLETA"
        ));

        String mensaje = "\nError -> Debe ingresar uno de estos colores: " + String.join(", ", colores);

        if(!colores.contains(color)){
            throw new ErrorIngresoColor(mensaje);
        }
    }

    /**
     * Valida que la base de datos de clientes no este vacía
     *
     * @param base ArrayList con la base de datos de clientes
     * @throws ErrorBaseDatosClientesVacia Error si la base de datos de clientes esta vacía
     */
    public static void validandoBaseClientes(ArrayList<Cliente> base) throws ErrorBaseDatosClientesVacia{
        if (base.isEmpty()){
            throw new ErrorBaseDatosClientesVacia("\nError -> Hay 0 clientes agregados. Debe ingresar algún cliente para poder hacer una venta.");
        }
    }

    /**
     * Valida que la base de datos de pájaros no este vacía
     *
     * @param base ArrayList con la base de datos de pájaros
     * @throws ErrorBaseDatosPajarosVacia Error si la base de datos de pájaros esta vacía
     */
    public static void validandoBasPajaros(ArrayList<Pajaro> base) throws ErrorBaseDatosPajarosVacia{
        if (base.isEmpty()){
            throw new ErrorBaseDatosPajarosVacia("\nError -> Hay 0 especies agregadas. Debe ingresar alguna especie para poder hacer una venta.");
        }
    }

    /**
     * Comprueba que exista el cliente en la base de datos de clientes
     *
     * @param cliente Cliente a verificar
     * @throws ErrorClienteNoExiste Error si el cliente no existe
     */
    public static void validandoExistenciaCliente(Cliente cliente) throws ErrorClienteNoExiste{
        if (cliente == null){
            throw new ErrorClienteNoExiste("\nError -> No existe el cliente");
        }
    }

    /**
     * Comprueba si el cliente ha realizado alguna venta
     *
     * @param ventasCliente ArrayList con el historial de ventas del cliente
     * @throws ErrorNoVentasCliente Error si el cliente todavía no ha hecho ninguna compra
     */
    public static void validandoVentasClientes(ArrayList<Venta> ventasCliente) throws ErrorNoVentasCliente{
        if (ventasCliente.isEmpty()) {
            throw new ErrorNoVentasCliente("\n El cliente todavía no ha realizado ninguna compra.");
        }
    }

    /**
     * Valida la cantidad a ingresar
     *
     * @param cantidad Cantidad ingresada
     * @throws ErrorValorInferiorCero Error si la cantidad ingresada es menor a 1.
     */
    public static void validandoCantidadStock(int cantidad) throws ErrorValorInferiorCero{
        if (cantidad < 1){
            throw new ErrorValorInferiorCero("\nError la cantidad a ingresar no puede ser menor de 1.");
        }
    }
}