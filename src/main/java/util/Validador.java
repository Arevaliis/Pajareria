package util;

import excepciones.*;
import modelos.Cliente;
import modelos.Pajaro;
import modelos.Venta;

import java.util.ArrayList;
import java.util.Arrays;

public class Validador {
    public static void validandoRangoScanner(int opc, int valorTop) throws ValorFueraRangoExcepcion {
        if (0 >= opc || opc > valorTop ){
            throw new ValorFueraRangoExcepcion("\nError -> El valor ingresado debe estar entre 1 y " + valorTop + ".");
        }
    }

    public static void validandoNombre(String nombre) throws ErrorIngresoNombreException {
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+")){
            throw new ErrorIngresoNombreException("\nError -> Valor mal ingresado. El nombre solo puede contener letras.");
        }
    }

    public static void valindandoDni(String dni) throws ErrorIngresoDniException {
        if (!dni.matches("^\\d{8}[A-Za-z]$")){
            throw new ErrorIngresoDniException("\nError -> Valor mal ingresado. El dni debe tener 8 números y 1 letra al final.");
        }
    }

    public static void validandoTelefono(String telefono) throws ErrorIngresoTelefonoException {
        if (!telefono.matches("^[6789]\\d{8}$")){
            throw new ErrorIngresoTelefonoException("\nError -> Valor mal ingresado. Deben empezar por (6,7,8,9) y luego 8 números mas.");
        }
    }

    public static void validandoEmail(String email) throws ErrorIngresoEmailException {
        if (!email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$")){
            throw new ErrorIngresoEmailException("\nError -> Valor mal ingresado. El email debe tener formato válido: usuario@dominio.extensión (mínimo 2 letras)");
        }
    }

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

    public static void validandoBaseClientes(ArrayList<Cliente> base) throws ErrorBaseDatosClientesVacia{
        if (base.isEmpty()){
            throw new ErrorBaseDatosClientesVacia("\nError -> Hay 0 clientes agregados. Debe ingresar algún cliente para poder hacer una venta.");
        }
    }

    public static void validandoBasPajaros(ArrayList<Pajaro> base) throws ErrorBaseDatosPajarosVacia{
        if (base.isEmpty()){
            throw new ErrorBaseDatosPajarosVacia("\nError -> Hay 0 especies agregadas. Debe ingresar alguna especie para poder hacer una venta.");
        }
    }

    public static void validandoExistenciaCliente(Cliente cliente) throws ErrorClienteNoExiste{
        if (cliente == null){
            throw new ErrorClienteNoExiste("\nError -> No existe el cliente");
        }
    }

    public static void validandoVentasClientes(ArrayList<Venta> ventasCliente) throws ErrorNoVentasCliente{
        if (ventasCliente.isEmpty()) {
            throw new ErrorNoVentasCliente("\n El cliente todavía no ha realizado ninguna compra.");
        }
    }

    public static void validandoCantidadStock(int cantidad) throws ErrorValorInferiorCero{
        if (cantidad < 1){
            throw new ErrorValorInferiorCero("\nError la cantidad a ingresar no puede ser menor de 1.");
        }
    }
}
