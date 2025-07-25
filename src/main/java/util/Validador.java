package util;

import excepciones.*;
import modelos.Cliente;
import modelos.Pajaro;
import modelos.Venta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase para validar los valores ingresados por el usuario mediante la consola
 *
 * @author Jose Iglesias
 * @version 4.0
 */
public class Validador {

    /**
     * Comprueba que el valor ingresado por el usuario está dentro del rango permitido
     *
     * @param opc Valor numérico ingresado
     * @param valorTop Valor máximo permitido
     * @throws ValorFueraRangoExcepcion si opc está fuera del rango permitido
     */
    public static void validarRango(int opc, int valorTop) throws ValorFueraRangoExcepcion {
        if (0 >= opc || opc > valorTop ){
            throw new ValorFueraRangoExcepcion("\nError -> El valor ingresado debe estar entre 1 y " + valorTop + ".");
        }
    }

    /**
     * Comprueba que el nombre no contenga ni números ni símbolos y tenga al menos 3 letras.
     *
     * @param nombre Nombre ingresado
     * @throws ErrorIngresoNombreException si el nombre contiene un número o un símbolo
     */
    public static void validarNombre(String nombre) throws ErrorIngresoNombreException {
        if (nombre.length() < 3){
            throw new ErrorIngresoNombreException("\nError -> El nombre debe contener al menos 3 letras.");
        }

        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+$")) {
            throw new ErrorIngresoNombreException("\nError -> El nombre solo puede contener letras.");
        }
    }

    /**
     * Valida que el DNI este en el formato correcto
     *
     * @param dni DNI ingresado
     * @throws ErrorIngresoDniException si el dni no contiene 8 números y 1 letra.
     */
    public static void validarDni(String dni) throws ErrorIngresoDniException {
        if (!dni.matches("^\\d{8}.*")) {
            throw new ErrorIngresoDniException("\nError -> El DNI debe comenzar con 8 dígitos.");
        }

        if (!dni.matches(".*[A-Za-z]$")) {
            throw new ErrorIngresoDniException("\nError -> El DNI debe terminar con una letra.");
        }
    }

    /**
     * Comprueba que el dni no pertenezca a otro cliente
     *
     * @param dni Dni ingresado
     * @param baseClientes Arraylist de {@code Cliente}
     * @throws ErrorDniDuplicado si el dni ya existe
     */
    public static void validarDniDuplicado(String dni, ArrayList<Cliente> baseClientes) throws ErrorDniDuplicado {
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
     * @throws ErrorIngresoTelefonoException si no comienza por 6, 7, 8 o 9 y no tiene exactamente 9 cifras.
     */
    public static void validarTelefono(String telefono) throws ErrorIngresoTelefonoException {
        if (!telefono.matches("^[6789].*")) {
            throw new ErrorIngresoTelefonoException("\nError -> El teléfono debe comenzar por 6, 7, 8 o 9.");
        }

        if (!telefono.matches("^\\d{9}$")) {
            throw new ErrorIngresoTelefonoException("\nError -> El teléfono debe tener exactamente 9 dígitos.");
        }
    }

    /**
     * Comprueba que el teléfono ingresado no pertenezca a un cliente
     *
     * @param telefono Teléfono ingresado
     * @param baseClientes Arraylist de {@code Clientes}
     * @throws ErrorTelefonoDuplicado si el teléfono ingresado ya existe
     */
    public static void validarTelefonoDuplicado(String telefono, ArrayList<Cliente> baseClientes) throws ErrorTelefonoDuplicado{
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
     * @throws ErrorIngresoEmailException si el formato del email no es válido o el dominio no está permitido
     */
    public static void validarEmail(String email) throws ErrorIngresoEmailException {
        int indiceArroba = email.indexOf("@");

        if (indiceArroba == -1){
            throw new ErrorIngresoEmailException("\nError -> El email debe tener @");
        }

        ArrayList<String> dominiosComunes = new ArrayList<>(
                List.of(
                        "@gmail.com",
                        "@hotmail.com",
                        "@outlook.com",
                        "@yahoo.com",
                        "@yahoo.es",
                        "@icloud.com",
                        "@live.com",
                        "@live.es",
                        "@protonmail.com",
                        "@aol.com",
                        "@msn.com",
                        "@gmx.com",
                        "@gmx.es",
                        "@zoho.com",
                        "@mail.com"
                )
        );
        String usuario = email.substring(0, indiceArroba);

        if (!usuario.matches("^[\\w.-]+$")) {
            throw new ErrorIngresoEmailException("\nError -> Usuario mal ingresado. Debe contener al menos 1 carácter válido.");
        }

        String dominio = email.substring(indiceArroba);

        if (!dominiosComunes.contains(dominio)){
            String mensaje = String.join(", ", dominiosComunes);
            throw new ErrorIngresoEmailException("\nError -> Dominio no permitido. Debe usar uno de estos dominios: " + mensaje);
        }
    }

    /**
     * Comprueba que el email ingresado no pertenezca ya un cliente
     *
     * @param email Email ingresado
     * @param baseClientes Arraylist de {@code Clientes}
     * @throws ErrorEmailDuplicado si el email ya existe
     */
    public static void validarEmailDuplicado(String email, ArrayList<Cliente> baseClientes) throws ErrorEmailDuplicado{
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
    public static void validarColor(String color) throws ErrorIngresoColor{
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
    public static void validarBaseClientesVacia(ArrayList<Cliente> base) throws ErrorBaseDatosClientesVacia{
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
    public static void validarBasePajarosVacia(ArrayList<Pajaro> base) throws ErrorBaseDatosPajarosVacia{
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
    public static void validarExistenciaCliente(Cliente cliente) throws ErrorClienteNoExiste{
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
    public static void validarClienteNoExiste(Cliente cliente) throws ErrorYaExisteCliente {
        if (cliente != null){
            throw new ErrorYaExisteCliente("\nError -> Ya existe un cliente registrado con ese DNI.");
        }
    }

    /**
     * Comprueba que exista el pájaro dentro de la base de datos
     *
     * @param pajaro {@code Pajaro} ingresado
     * @throws ErrorNoExistePajaro si no existe el pájaro
     */
    public static void validarExistenciaPajaro(Pajaro pajaro) throws ErrorNoExistePajaro{
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
    public static void validarYaExisteEspecie(String especie, ArrayList<Pajaro> basePajaros) throws ErrorYaExisteEspecie{
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
    public static void validarVentasClientes(ArrayList<Venta> ventasCliente) throws ErrorNoVentasCliente{
        if (ventasCliente.isEmpty()) {
            throw new ErrorNoVentasCliente("\nError -> El cliente todavía no ha realizado ninguna compra.");
        }
    }

    /**
     * Comprueba que la tienda tenga suficiente stock para el pedido del cliente
     *
     * @param cantidad Cantidad ingresada
     * @param pajaro {@code Pajaro} ingresado
     * @throws ErrorNoHayStock si el stock menos la cantidad es inferior a 0
     */
    public static void validarNoHayStock(int cantidad, Pajaro pajaro) throws ErrorNoHayStock{
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
    public static void validarCestaCompraVacia(Venta venta) throws ErrorCestaVacia {
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
    public static void validarBaseVentasVacia(ArrayList<Venta> baseVentas) throws ErrorBaseVentasVacia{
        if (baseVentas.isEmpty()){
            throw new ErrorBaseVentasVacia("\nError -> Historial de ventas vacío. Haga una venta para acceder.");
        }
    }

    /**
     * Comprueba la cantidad a ingresar
     *
     * @param cantidad Cantidad ingresada
     * @throws ErrorValorInferiorCero Error si la cantidad ingresada es menor a 1.
     */
    public static void validarCantidadStock(int cantidad) throws ErrorValorInferiorCero{
        if (cantidad  < 1 ){
            throw new ErrorValorInferiorCero("\nError -> La cantidad a ingresar no puede ser menor de 1.");
        }
    }

}