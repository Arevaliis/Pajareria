package util;

import excepciones.*;
import modelos.Cliente;
import modelos.Pajaro;
import modelos.Venta;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase para validar los valores ingresados por el usuario mediante la consola
 *
 * @author Jose Iglesias
 * @version 3.0
 */
public class Validador {

    /**
     * Comprueba que el valor ingresado por el usuario está dentro del rango permitido
     *
     * @param opc Valor numérico ingresado
     * @param valorTop Valor máximo permitido
     * @throws ValorFueraRangoExcepcion si opc está fuera del rango permitido
     */
    public static void validandoRango(int opc, int valorTop) throws ValorFueraRangoExcepcion {
        if (0 >= opc || opc > valorTop ){
            throw new ValorFueraRangoExcepcion("\nError -> El valor ingresado debe estar entre 1 y " + valorTop + ".");
        }
    }

    /**
     * Comprueba que el nombre no contenga ni números ni símbolos.
     *
     * @param nombre Nombre ingresado
     * @throws ErrorIngresoNombreException si el nombre contiene un número o un símbolo
     */
    public static void validandoNombre(String nombre) throws ErrorIngresoNombreException {
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+")){
            throw new ErrorIngresoNombreException("\nError -> Valor mal ingresado. El nombre solo puede contener letras.");
        }
    }

    /**
     * Valida que el DNI este en el formato correcto
     *
     * @param dni DNI ingresado
     * @throws ErrorIngresoDniException si el usuario no cumple con el formato del DNI. 8 números y 1 letra.
     */
    public static void valindandoDni(String dni) throws ErrorIngresoDniException {
        if (!dni.matches("^\\d{8}[A-Za-z]$")){
            throw new ErrorIngresoDniException("\nError -> Valor mal ingresado. El dni debe tener 8 números y 1 letra al final.");
        }
    }

    /**
     * Comprueba que el dni no pertenezca a otro cliente
     *
     * @param dni Dni ingresado
     * @param baseClientes Arraylist de {@code Clientes}
     * @throws ErrorDniDuplicado si el dni ya existe
     */
    public static void dniDuplicado(String dni, ArrayList<Cliente> baseClientes) throws ErrorDniDuplicado {
        for (Cliente cliente : baseClientes) {
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                throw new ErrorDniDuplicado("\nError: Ya existe un cliente registrado con ese DNI.");
            }
        }
    }

    /**
     * Valida que el teléfono ingresado sea válido
     *
     * @param telefono Teléfono del cliente ingresado
     * @throws ErrorIngresoTelefonoException si el teléfono ingresado no empieza por 6,7,8 o 9 y 8 números más.
     */
    public static void validandoTelefono(String telefono) throws ErrorIngresoTelefonoException {
        if (!telefono.matches("^[6789]\\d{8}$")){
            throw new ErrorIngresoTelefonoException("\nError -> Valor mal ingresado. Deben empezar por (6,7,8,9) y luego 8 números mas.");
        }
    }

    /**
     * Comprueba que el teléfono ingresado no pertenezca a un cliente
     *
     * @param telefono Teléfono ingresado
     * @param baseClientes Arraylist de {@code Clientes}
     * @throws ErrorTelefonoDuplicado si el teléfono ingresado ya existe
     */
    public static void telefonoDuplicado(String telefono, ArrayList<Cliente> baseClientes) throws ErrorTelefonoDuplicado{
        for (Cliente cliente : baseClientes) {
            if (cliente.getTelefono().equalsIgnoreCase(telefono)) {
                throw new ErrorTelefonoDuplicado("\nError: Ya existe un cliente con ese número de teléfono.");
            }
        }
    }

    /**
     * Comprueba que el email ingresado tenga un formato válido: usuario@dominio.extensión
     *
     * @param email Email del cliente ingresado
     * @throws ErrorIngresoEmailException si el email ingresado no es válido
     */
    public static void validandoEmail(String email) throws ErrorIngresoEmailException {
        if (!email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$")){
            throw new ErrorIngresoEmailException("\nError -> Valor mal ingresado. El email debe tener formato válido: usuario@dominio.extensión (mínimo 2 letras)");
        }
    }

    /**
     * Comprueba que el email ingresado no pertenezca ya un cliente
     *
     * @param email Email ingresado
     * @param baseClientes Arraylist de {@code Clientes}
     * @throws ErrorEmailDuplicado si el email ya existe
     */
    public static void emailDuplicado(String email, ArrayList<Cliente> baseClientes) throws ErrorEmailDuplicado{
        for (Cliente cliente: baseClientes){
            if (cliente.getEmail().equalsIgnoreCase(email)){
                throw new ErrorEmailDuplicado("\nError: Ya existe un cliente con ese email.");
            }
        }
    }

    /**
     * Comprueba que el color que se ingresa sea un color real
     *
     * @param color Color ingresado
     * @throws ErrorIngresoColor si el color ingresado no está en el ArrayList
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
     * @throws ErrorBaseDatosClientesVacia si la base de datos de clientes está vacía
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
     * @throws ErrorBaseDatosPajarosVacia si la base de datos de pájaros está vacía
     */
    public static void validandoBasePajaros(ArrayList<Pajaro> base) throws ErrorBaseDatosPajarosVacia{
        if (base.isEmpty()){
            throw new ErrorBaseDatosPajarosVacia("\nError -> Hay 0 especies agregadas. Debe ingresar alguna especie para poder hacer una venta.");
        }
    }

    /**
     * Comprueba que exista el cliente seleccionado en la base de datos de clientes
     *
     * @param cliente Cliente a verificar
     * @throws ErrorClienteNoExiste si el cliente no existe
     */
    public static void validandoExistenciaCliente(Cliente cliente) throws ErrorClienteNoExiste{
        if (cliente == null){
            throw new ErrorClienteNoExiste("\nError -> No existe el cliente");
        }
    }

    /**
     * Comprueba que el cliente no exista en la base de datos
     *
     * @param cliente {@code Cliente} ha ingresar
     * @throws ErrorYaExisteCliente si el cliente ya existe
     */
    public static void existeCliente(Cliente cliente) throws ErrorYaExisteCliente {
        if (cliente != null){
            throw new ErrorYaExisteCliente("\nError ->Ya existe un cliente registrado con ese DNI.");
        }
    }

    /**
     * Comprueba que exista el pájaro dentro de la base de datos
     *
     * @param pajaro {@code Pajaro} ingresado
     * @throws ErrorNoExistePajaro si no existe el pájaro
     */
    public static void noExistePajaro(Pajaro pajaro) throws ErrorNoExistePajaro{
        if (pajaro == null){
            throw new ErrorNoExistePajaro("\nError -> No se ha encontrado ningún pájaro de esa especie.");
        }
    }

    /**
     * Comprueba si ya existe esa instancia de pájaro en la base de datos
     *
     * @param especie Especie ingresada
     * @param basePajaros ArrayList con la base de datos de pájaros
     * @throws ErrorYaExisteEspecie si ya existe
     */
    public static void yaExisteEspecie(String especie, ArrayList<Pajaro> basePajaros) throws ErrorYaExisteEspecie{
        for(Pajaro pajaro: basePajaros) {
            if (pajaro.getEspecie().equalsIgnoreCase(especie)) {
                throw new ErrorYaExisteEspecie("\nError -> Ya existe esa especie de pájaro.");
            }
        }
    }

    /**
     * Comprueba si el cliente ha realizado alguna compra
     *
     * @param ventasCliente ArrayList con el historial de compra del cliente
     * @throws ErrorNoVentasCliente si el cliente todavía no ha hecho ninguna compra
     */
    public static void validandoVentasClientes(ArrayList<Venta> ventasCliente) throws ErrorNoVentasCliente{
        if (ventasCliente.isEmpty()) {
            throw new ErrorNoVentasCliente("\nError -> El cliente todavía no ha realizado ninguna compra.");
        }
    }

    /**
     * Comprueba la cantidad a ingresar
     *
     * @param cantidad Cantidad ingresada
     * @throws ErrorValorInferiorCero Error si la cantidad ingresada es menor a 1.
     */
    public static void validandoCantidadStock(int cantidad) throws ErrorValorInferiorCero{
        if (cantidad < 1){
            throw new ErrorValorInferiorCero("\nError -> La cantidad a ingresar no puede ser menor de 1.");
        }
    }

    /**
     * Comprueba que la tienda tenga suficiente stock para el pedido del cliente
     *
     * @param cantidad Cantidad ingresada
     * @param pajaro {@code Pajaro} ingresado
     * @throws ErrorNoHayStock si el stock menos la cantidad es inferior a 0
     */
    public static void noHayStock(int cantidad, Pajaro pajaro) throws ErrorNoHayStock{
        if (pajaro.getStock() - cantidad < 0){
            throw new ErrorNoHayStock("\nError -> No hay stock suficiente. Stock disponible:" + pajaro.getStock());
        }
    }

    /**
     * Comprueba la cesta del cliente una vez terminado el proceso de compra
     *
     * @param venta {@code Venta} realizada
     * @throws ErrorCestaVacia si la cesta de compra esta vacía
     */
    public static void cestaVacia(Venta venta) throws ErrorCestaVacia {
        if (venta.getLineasDeVenta().isEmpty()) {
            throw new ErrorCestaVacia("\nError -> No ha añadido ningún producto a la cesta. La operación se cancelará.");
        }
    }

    /**
     * Comprueba que exista alguna venta en la base de datos ventas
     *
     * @param baseVentas ArraList con la base de datos ventas
     * @throws ErrorBaseVentasVacia si no hay ninguna venta
     */
    public static void baseVentasVacia(ArrayList<Venta> baseVentas) throws ErrorBaseVentasVacia{
        if (baseVentas.isEmpty()){
            throw new ErrorBaseVentasVacia("\nError -> Historial de ventas vacío. Haga una venta para acceder.");
        }
    }
}