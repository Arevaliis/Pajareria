package util;

import excepciones.*;
import modelos.Cliente;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase para ingresar valores por el usuario mediante la consola
 *
 * @author Jose Iglesias
 * @version 4.0
 */
public class Consola {
    /**
     * Pide al usuario que ingrese el nombre del cliente y comprueba que sea válido
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return String con nombre del cliente
     */
    public static String ingresarNombre(Scanner scanner){
        while (true){
            try {
                Mensajes.mensajeNombre();
                String nombre = scanner.nextLine().trim().toUpperCase();
                Validador.validarNombre(nombre);
                return nombre;
            } catch (ErrorIngresoNombreException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el DNI del cliente y comprueba que sea válido
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return String con el DNI del cliente
     */
    public static String ingresarDni(Scanner scanner) {
        String dni;
        while (true) {
            try {
                Mensajes.mensajeDni();
                dni = scanner.nextLine().trim().toUpperCase();
                Validador.validarDni(dni);
                return dni;
            } catch (ErrorIngresoDniException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el teléfono del cliente y comprueba que sea válido
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return String Teléfono del cliente
     */
    public static String ingresarTelefono(ArrayList<Cliente> baseClientes, Scanner scanner) {
        String telefono;
        while (true) {
            try {
                Mensajes.mensajeTelefono();
                telefono = scanner.nextLine().trim();
                Validador.validarTelefono(telefono);
                Validador.validarTelefonoDuplicado(telefono, baseClientes);
                return telefono;
            } catch (ErrorIngresoTelefonoException | ErrorTelefonoDuplicado e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el email del cliente y comprueba que sea válido
     *
     * @param baseClientes ArrayList con la base de datos clientes
     * @param scanner Scanner para leer los valores ingresados por el usuario
     *
     * @return String Email del cliente
     */
    public static String ingresarEmail(ArrayList<Cliente> baseClientes, Scanner scanner){
        String email;
        while (true){
            try {
                Mensajes.mensajeEmail();
                email = scanner.nextLine().trim().toLowerCase();
                Validador.validarEmail(email);
                Validador.validarEmailDuplicado(email, baseClientes);
                return email;
            } catch (ErrorIngresoEmailException | ErrorEmailDuplicado e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese la especie del pájaro
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario
     * @return String con la especie del pájaro
     */
    public static String ingresarEspecie(Scanner scanner){
        while (true){
            try {
                Mensajes.mensajeEspecie();
                String especie = scanner.nextLine().trim().toUpperCase();
                Validador.validarNombre(especie);
                return especie;
            }catch (ErrorIngresoNombreException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el color del pájaro
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario
     * @return String con el color del pájaro
     */
    public static String ingresarColor(Scanner scanner){
        while (true){
            try {
                Mensajes.mensajeColor();
                String color = scanner.nextLine().trim().toUpperCase();
                Validador.validarColor(color);
                return color;
            } catch (ErrorIngresoColor e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Pide al usuario que ingrese el precio del pájaro
     *
     * @param scanner Scanner para leer los valores ingresados por el usuario
     * @return double con el precio del pájaro
     */
    public static double ingresarPrecio(Scanner scanner){
        while (true){
            try {
                Mensajes.mensajePrecio();
                return (double) Math.round(Double.parseDouble(scanner.nextLine().trim()) * 100) /100;
            } catch (NumberFormatException e) {
                System.out.println("\nError -> Debe ingresar un número. Use punto para los decimales.");
            }
        }
    }

    /**
     * Pide al usuario que ingrese la cantidad disponible
     *
     * @return  int con la cantidad disponible del pájaro
     */
    public static int ingresarStock(Scanner scanner){
        while (true){
            try {
                Mensajes.mensajeAgregarStock();
                int cantidad = Integer.parseInt(scanner.nextLine());
                Validador.validarCantidadStock(cantidad);
                return cantidad;
            }catch (ErrorValorInferiorCero e){
                System.out.println(e.getMessage());
            }catch (NumberFormatException e) {
                System.out.println("\nError -> Debe ingresar un número.");
            }
        }
    }
}
