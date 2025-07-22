package util;

import modelos.*;

/**
 * Clase que contiene todos los mensajes del programa: menús, mensajes informativos y de error.
 */
public class Mensajes {

    /** Muestra el menu principal del programa. */
    public static void menuInicial(){
        System.out.println("""
                
                ======= MENÚ PRINCIPAL =======
                    1. Gestión de clientes
                    2. Gestión de pájaros
                    3. Realizar venta
                    4. Mostrar ventas
                    5. Salir
                ==============================
                """);
    }

    /** Muestra el menu clientes. */
    public static void menuClientes(){
        System.out.println("""
                
                ======= MENÚ CLIENTES ========
                    1. Alta de clientes
                    2. Baja de clientes
                    3. Modificación cliente
                    4. Búsqueda por DNI
                    5. Listado
                    6. Volver
                ==============================
                """);
    }

    /** Muestra el menu pájaros. */
    public static void menuPajaros(){
        System.out.println("""
                
                ======== MENÚ PÁJAROS ========
                    1. Alta de pájaro
                    2. Listado
                    3. Búsqueda por especie
                    4. Agregar Stock
                    5. Volver
                ==============================
                """);
    }

    /** Muestra el menú de campos modificables del cliente. */
    public static void menuModificarValores(){
        System.out.println("""
                
                ======== CAMPOS CLIENTE ========
                        1. Nombre
                        2. Dni
                        3. Teléfono
                        4. Email
                        5. Volver
                ================================
                """);
    }

    /** Muestra el menu del historial de ventas. */
    public static void menuMostrarVentas(){
        System.out.println("""
                
                =========== MENÚ HISTORIAL VENTAS ===========
                    1. Todas las ventas
                    2. Ventas por cliente
                    3. Importe total de cada venta
                    4. Gasto total de cliente
                    5. Volver
                =============================================
                """);
    }

    /** Muestra un mensaje de salida del programa. */
    public static void saliendo(){
        System.out.println("\nSaliendo...");
    }

                            /* ======================= Mensajes de Clientes ======================= */

    /** Pregunta al usuario si desea buscar otro cliente. */
    public static void buscarDeNuevoCliente(){
        System.out.print("\n¿Desea buscar otro cliente por DNI? (S/N): ");
    }

    /** Solicita el nombre del cliente. */
    public static void mensajeNombre(){
        System.out.print("Ingrese el nombre del cliente: ");
    }

    /** Pide el DNI del cliente. */
    public static void mensajeDni(){
        System.out.print("\nIngrese el DNI del cliente: ");
    }

    /** Solicita el ingreso del teléfono del cliente. */
    public static void mensajeTelefono(){
        System.out.print("Ingrese el número de teléfono del cliente: ");
    }

    /** Pide el email del cliente. */
    public static void mensajeEmail(){
        System.out.print("Ingrese el correo electrónico del cliente: ");
    }

    /** Confirmación del ingreso de un cliente a la base de datos clientes. */
    public static void clienteAgregado(){
        System.out.println("\nCliente registrado correctamente.");
    }

    /** Pregunta al usuario si desea agregar el nuevo cliente. */
    public static void confirmarAgregarCliente(Cliente cliente){
        System.out.print("\n¿Desea agregar el " + cliente + " ? (S/N): ");
    }

    /** Informa al usuario que ha cancelado el proceso de agregar un nuevo cliente. */
    public static void procesoAgregarClienteCancelado(){
        System.out.println("\nEl proceso de agregar un nuevo cliente se ha cancelado");
    }

    /** Pregunta al usuario si desea agregar otro nuevo cliente. */
    public static void ingresarNuevoCliente(){
        System.out.print("\n¿Desea ingresar un nuevo cliente? (S/N): ");
    }

    /** Solicita la confirmación para eliminar al cliente. */
    public static void confirmarEliminacion(){
        System.out.print("\n¿Está seguro de que desea eliminar este cliente? (S/N): ");
    }

    /** Confirmación de eliminación de cliente. */
    public static void clienteEliminado(){
        System.out.println("\nCliente eliminado exitosamente.");
    }

    /** Informa al usuario de que ha cancelado el proceso de eliminación del cliente. */
    public static void eliminacionAbortada(){
        System.out.println("\nProceso de eliminación cancelado.");
    }

    /** Pregunta al usuario si quiere ingresar otro DNI. */
    public static void ingresarOtroDni(){
        System.out.print("\n¿Desea ingresar otro DNI? (S/N): ");
    }

    /** Informa de que la base clientes esta vacía. */
    public static void baseClientesVacia(){
        System.out.println("\nBase de datos clientes vacía.");
    }

    /** Pide la confirmación para modificar la información del cliente. */
    public static void modificarCampo(String campo){
        System.out.print("\n¿Está seguro de que desea modificar el campo " + campo + " de este cliente? (S/N): ");
    }

    /** Muestra el cambio de información del cliente. */
    public static void campoModificado(String campo, String valorNuevo, String valorAntiguo){
        System.out.println("\nEl " + campo + " ha sido modificado de " + valorAntiguo + " a " + valorNuevo);
    }

    /** Informa al cliente que el proceso de modificacion ha sido cancelado. */
    public static void modificacionAbortada(){
        System.out.println("\nModificación abortada.");
    }

    /** Muestra un mensaje con la información del cliente. */
    public static void mostrarCliente(Cliente cliente){
        System.out.println(cliente);
    }

    /** Pregunta al usuario si desea volver al menu principal. */
    public static void mensajeVolverMenu(){
        System.out.print("\n¿Desea volver al menú principal? (S para Sí / N para Salir): ");
    }

    /** Pregunta al usuario si desea volver al menu clientes. */
    public static void mensajeVolverMenuClientes(){
        System.out.print("\n¿Desea volver al menú de gestión de clientes? (S/N): ");
    }

                        /* ======================= Mensajes de Pájaros ======================= */

    /** Informa que la base de datos de pájaros esta vacía. */
    public static void basePajarosVacia(){
        System.out.println("\nLa base de datos de pájaros esta vacía.");
    }

    /** Pregunta si quiere buscar otra especie de pájaro. */
    public static void volverBuscarPajaro(){
        System.out.print("\n¿Desea realizar otra búsqueda por especie? (S/N): ");
    }

    /** Pregunta si quiere volver al menu de pájaros. */
    public static void mensajeVolverMenuPajaros(){
        System.out.print("\n¿Desea volver al menú de gestión de pájaros? (S/N): ");
    }

    /** Pide la especie del pájaro. */
    public static void mensajeEspecie(){
        System.out.print("\nIngrese la especie del pájaro: ");
    }

    /** Pide el color del pájaro. */
    public static void mensajeColor(){
        System.out.print("Ingrese el color del pájaro: ");
    }

    /** Pide el precio del pájaro. */
    public static void mensajePrecio(){
        System.out.print("Ingrese el precio del pájaro (use punto para los decimales): ");
    }

    /** Pide la cantidad de unidades para el inventario del pájaro. */
    public static void mensajeAgregarStock(){
        System.out.print("Ingrese la cantidad de unidades: ");
    }

    /** Solicita confirmación del usuario para agregar stock a la especie */
    public static void conirmarAgregarStock(int cantidad, String especie){
        System.out.print("\n¿Desea agregar "+ cantidad + " al inventario de " + especie + " ? (S/N): ");
    }

    /** Mensaje informativo de la cantidad agregada al stock. */
    public static void cantidadAgregadaStock(int cantidad, String especie){
        System.out.println("\nSe ha agregado " + cantidad + " al stock de " + especie + ".");
    }

    /** Informa al usuario que ha cancelado el proceso de agregar stock al producto. */
    public static void procesoStockAbortado(){
        System.out.println("\nEl proceso de agregar stock al inventario se ha cancelado");
    }

    /** Pregunta al usuario si desea agregar el nuevo pájaro. */
    public static void confirmarAgregarPajaro(Pajaro pajaro){
        System.out.print("\n¿Desea agregar el " + pajaro + " ? (S/N): ");

    }

    /** Confirma el ingreso de un nuevo pájaro. */
    public static void agregadoPajaroConExito(){
        System.out.println("\nEl pájaro ha sido registrado correctamente.");
    }

    /** Informa de que no ha sido agregado el pájaro. */
    public static void pajaroNoAgregado(){
        System.out.println("\nEl pájaro no ha sido registrado.");
    }

    /** Pregunta si desea agregar un nuevo pájaro. */
    public static void agregarOtroPajaro(){
        System.out.print("\n¿Desea registrar otro pájaro? (S/N): ");
    }

    /** Muestra la información del pájaro. */
    public static void mostrarPajaro(Pajaro pajaro){
        System.out.println(pajaro);
    }

    /** Pregunta si desea modificar el stock de otro pájaro. */
    public static void modificarOtraVezStock(){
        System.out.print("\n¿Desea modificar el stock de otra especie? (S/N): ");
    }

                            /* ======================= Mensajes de Venta ======================= */

    /** Título para el inventario. */
    public static void tituloEspecies(){
        System.out.println("\n--------- Inventario ---------");
    }

    /** Pide al usuario que ingrese el pájaro que quiere comprar. */
    public static void comprarPajaro(){
        System.out.print("\n¿Que pájaro quiere comprar?. ");
    }

    /** Pregunta si desea agregar mas pájaros a la cesta de compra. */
    public static void volverComprarPajaro(){
        System.out.print("\n¿Desea agregar otro pájaro? (S/N): ");
    }

    public static void confirmarCompra(){
        System.out.print("\n¿Desea realizar la compra? (S/N): ");
    }

    public static void compraCancelada(){
        System.out.println("\nSe ha cancelado el proceso de compra.");
    }

    /** Muestra el ticket de compra. */
    public static void ticketCompra(Venta venta){
        System.out.println(venta);
    }

                        /* ======================= Mensajes de Mostrar Ventas ======================= */

    /** Pregunta si desea volver al menu de ventas totales. */
    public static void mensajeVolverMenuVentasTotales(){
        System.out.print("\n¿Desea volver al menú de ventas totales? (S/N): ");
    }

    /** Muestra el importe total gastado por el cliente especificado. */
    public static void importeTotalVentasCliente(String nombre, double gasto){
        System.out.printf("\nEl cliente %s ha gastado un total de: %.2f€", nombre, gasto);

    }

    /** Muestra el importe total de cada venta. */
    public static void mensajeTotalVenta(int contador, double total){
        System.out.printf("\n%d. Venta: %.2f€\n", contador, total);
    }
}