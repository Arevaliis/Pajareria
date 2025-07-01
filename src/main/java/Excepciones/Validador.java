package Excepciones;

public class Validador {
    public static void validandoRangoScanner(int opc, int valorTop) throws ValorFueraRangoExcepcion{
        if (0 >= opc || opc > valorTop ){
            throw new ValorFueraRangoExcepcion("El valor ingresado debe estar entre 1 y " + valorTop + ".");
        }
    }

    public static void validandoNombre(String nombre) throws ErrorIngresoNombreException{
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+")){
            throw new ErrorIngresoNombreException("Valor mal ingresado. El nombre solo puede contener letras.");
        }
    }

    public static void valindandoDni(String dni) throws ErrorIngresoDniException{
        if (!dni.matches("^\\d{8}[A-Za-z]$")){
            throw new ErrorIngresoDniException("Valor mal ingresado. El dni debe tener 8 números y 1 letra al final.");
        }
    }

    public static void validandoTelefono(String telefono) throws ErrorIngresoTelefonoException{
        if (!telefono.matches("^[6789]\\d{8}$")){
            throw new ErrorIngresoTelefonoException("Valor mal ingresado. Deben empezar por (6,7,8,9) y luego 8 números mas.");
        }
    }

    public static void validandoEmail(String email) throws ErrorIngresoEmailException{
        if (!email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$")){
            throw new ErrorIngresoEmailException("Valor mal ingresado. El email debe tener formato válido: usuario@dominio.extensión (mínimo 2 letras)");
        }
    }
}
