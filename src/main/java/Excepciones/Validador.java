package Excepciones;

import java.util.ArrayList;
import java.util.Arrays;

public class Validador {
    public static void validandoRangoScanner(int opc, int valorTop) throws ValorFueraRangoExcepcion{
        if (0 >= opc || opc > valorTop ){
            throw new ValorFueraRangoExcepcion("\nError -> El valor ingresado debe estar entre 1 y " + valorTop + ".");
        }
    }

    public static void validandoNombre(String nombre) throws ErrorIngresoNombreException{
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+")){
            throw new ErrorIngresoNombreException("\nError -> Valor mal ingresado. El nombre solo puede contener letras.");
        }
    }

    public static void valindandoDni(String dni) throws ErrorIngresoDniException{
        if (!dni.matches("^\\d{8}[A-Za-z]$")){
            throw new ErrorIngresoDniException("\nError -> Valor mal ingresado. El dni debe tener 8 números y 1 letra al final.");
        }
    }

    public static void validandoTelefono(String telefono) throws ErrorIngresoTelefonoException{
        if (!telefono.matches("^[6789]\\d{8}$")){
            throw new ErrorIngresoTelefonoException("\nError -> Valor mal ingresado. Deben empezar por (6,7,8,9) y luego 8 números mas.");
        }
    }

    public static void validandoEmail(String email) throws ErrorIngresoEmailException{
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

        // TODO HACERLO MEJOR
        String mensaje = "Error -> Debe ingresar uno de estos colores: Rojo, Azul, Verde, Amarillo, Blanco, Negro, Naranja, Gris, Marrón, Violeta.";

        if(!colores.contains(color)){
            throw new ErrorIngresoColor(mensaje);
        }
    }

}
