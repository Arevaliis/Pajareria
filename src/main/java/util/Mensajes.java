package util;

import modelos.*;

/** Clase que contiene todos los mensajes del programa; menús, mensajes informativos y errores. */
public class Mensajes {

    /** Muestra el menu principal del programa */
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

    /** Muestra el menu clientes del programa */
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

    /** Muestra el menu pájaros del programa */
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

    /** Muestra el menú de campos modificables del cliente */
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

    /** Muestra el menu ventas del programa */
    public static void menuMostrarVentas(){
        System.out.println("""
                
                =========== MENÚ HISTORIAL VENTAS ===========
                    1. Todas las ventas
                    2. Ventas por cliente
                    3. Importe total de cada venta
                    4. Gasto total de cliente
                    5. Volver
                ======================================
                """);
    }

    /** Muestra un mensaje de salida del programa */
    public static void saliendo(){
        System.out.println("\nSaliendo...");
    }

                            /* ======================= Mensajes de Clientes ======================= */

    /** Muestra un mensaje cuando el cliente no existe */
    public static void clienteNoExiste(){
        System.out.println("No se ha encontrado ningún cliente con ese DNI.");
    }

    /** Pregunta al usuario si desea buscar otro cliente */
    public static void buscarDeNuevoCliente(){
        System.out.print("\n¿Desea buscar otro cliente por DNI? (S/N): ");
    }

    /** Solicita el nombre del cliente */
    public static void mensajeNombre(){
        System.out.print("Ingrese el nombre del cliente: ");
    }

    /** Pide el DNI del cliente */
    public static void mensajeDni(){
        System.out.print("Ingrese el DNI del cliente: ");
    }

    /** Informa de que el DNI ingresado ya corresponde a un cliente */
    public static void yaExisteDni(){
        System.out.println("Error: Ya existe un cliente registrado con ese DNI.");
    }

    /** Solicita el ingreso del teléfono del cliente */
    public static void mensajeTelefono(){
        System.out.print("Ingrese el número de teléfono del cliente: ");
    }

    /** Informa que el teléfono ingresado ya corresponde a un cliente */
    public static void telefonoRepetido(){
        System.out.println("Error: Ya existe un cliente con ese número de teléfono.\n");
    }

    /** Pide el email del cliente */
    public static void mensajeEmail(){
        System.out.print("Ingrese el correo electrónico del cliente: ");
    }

    /** Informa de que el email ingresado ya corresponde a un cliente */
    public static void  emailRepetido(){
        System.out.println("Error: Ya existe un cliente con ese correo electrónico.\n");
    }

    /** Confirmación del ingreso de un cliente a la base de datos clientes */
    public static void clienteAgregado(){
        System.out.println("Cliente registrado correctamente.");
    }

    /** Pregunta al usuario si desea agregar un nuevo cliente */
    public static void ingresarNuevoCliente(){
        System.out.print("\n¿Desea ingresar un nuevo cliente? (S/N): ");
    }

    /** Solicita la confirmación para eliminar al cliente */
    public static void confirmarEliminacion(){
        System.out.print("\n¿Está seguro de que desea eliminar este cliente? (S/N): ");
    }

    /** Confirmación de eliminación de cliente */
    public static void clienteEliminado(){
        System.out.println("Cliente eliminado exitosamente.");
    }

    /** Pregunta al usuario si quiere ingresar otro DNI. */
    public static void ingresarOtroDni(){
        System.out.print("\n¿Desea ingresar otro DNI? (S/N): ");
    }

    /** Informa de que la base clientes esta vacía */
    public static void baseClientesVacia(){
        System.out.println("Base de datos clientes vacía.");
    }

    /** Pide la confirmación para modificar la información del cliente */
    public static void modificarCampo(String campo){
        System.out.print("\n¿Está seguro de que desea modificar el campo " + campo + " de este cliente? (S/N): ");
    }

    /** Muestra el cambio de información del cliente */
    public static void campoModificado(String campo, String valorNuevo, String valorAntiguo){
        System.out.println("El " + campo + " ha sido modificado de " + valorAntiguo + " a " + valorNuevo);
    }

    /** Pregunta para modificar información de otro cliente */
    public static void modificarAlgoMas(){
        System.out.print("\n¿Desea modificar otro dato de algún cliente? (S/N): ");
    }

    /** Muestra un mensaje con la información del cliente */
    public static void mostarCliente(Cliente cliente){
        System.out.println(cliente);
    }

    /** Muestra un mensaje para volver al menu principal */
    public static void mensajeVolverMenu(){
        System.out.print("\n¿Desea volver al menú principal? (S para Sí / N para Salir): ");
    }

    /** Muestra un mensaje volver al menu clientes */
    public static void mensajeVolverMenuClientes(){
        System.out.print("\n¿Desea volver al menú de gestión de clientes? (S/N): ");
    }

                        /* ======================= Mensajes de Pájaros ======================= */

    /** Informa que la base de datos de pájaros esta vacía */
    public static void basePajarosVacia(){
        System.out.println("La base de datos de pájaros esta vacía.");
    }

    /** Informa que la especie de pájaro no existe en la base de datos */
    public static void noExistePajaro(){
        System.out.println("No se ha encontrado ningún pájaro de esa especie.");
    }

    /** Pregunta si quiere buscar otra especie de pájaro */
    public static void volverBuscarPajaro(){
        System.out.print("\n¿Desea realizar otra búsqueda por especie? (S/N): ");
    }

    /** Pregunta si quiere volver al menu de pájaros */
    public static void mensajeVolverMenuPajaros(){
        System.out.print("\n¿Desea volver al menú de gestión de pájaros? (S/N): ");
    }

    /** Pide la especie del pájaro */
    public static void mensajeEspecie(){
        System.out.print("Ingrese la especie del pájaro: ");
    }

    /** Pide el color del pájaro */
    public static void mensajeColor(){
        System.out.print("Ingrese el color del pájaro: ");
    }

    /** Pide el precio del pájaro */
    public static void mensajePrecio(){
        System.out.print("Ingrese el precio del pájaro (use punto para los decimales): ");
    }

    /** Pide la cantidad de unidades del pájaro */
    public static void mensajeStock(){
        System.out.print("Ingrese la cantidad de unidades: ");
    }

    /** Confirma el ingreso de un nuevo pájaro */
    public static void agregadoPajaroConExito(){
        System.out.println("El pájaro ha sido registrado correctamente.");
    }

    /** Pregunta si desea agregar un nuevo pájaro */
    public static void agregarOtroPajaro(){
        System.out.print("\n¿Desea registrar otro pájaro? (S/N): ");
    }

    /** Pide el tipo de especie del pájaro */
    public static void tipoEspecie(String especie){
        System.out.println(especie);
    }

    /** Muestra la información del pájaro */
    public static void mostrarPajaro(Pajaro pajaro){
        System.out.println(pajaro);
    }

    /** Muestra la confirmación de que el stock fue modificado */
    public static void modificadoStock(){
        System.out.println("El stock ha sido actualizado");
    }

    /** Pregunta si desea modificar el stock de otro pájaro */
    public static void modificarOtraVezStock(){
        System.out.print("\n¿Desea modificar el stock de otra especie? (S/N): ");
    }

                            /* ======================= Mensajes de Venta ======================= */

    /** Título para el inventario */
    public static void tituloEspecies(){
        System.out.println("\n----- Inventario -----");
    }

    /** Pide al usuario que ingrese el pájaro que quiere comprar */
    public static void comprarPajaro(){
        System.out.print("\n¿Que pájaro quiere comprar?. ");
    }

    /** Pregunta si desea agregar mas pájaros a la cesta de compra */
    public static void volverComprarPajaro(){
        System.out.print("\n¿Desea agregar otro pájaro? (S/N): ");
    }

    /** Muestra el ticket de compra */
    public static void compraTotal(Venta venta){
        System.out.println(venta);
    }

    /** Informa de que la cesta esta vacía */
    public static void cestaVacia(){
        System.out.println("No ha añadido ningún producto a la cesta. La operación se cancelará.");

    }

    /** Informa de que no hay stock suficiente del producto seleccionado */
    public static void noHayStockSuficiente(int cantidad){
        System.out.println("No hay stock suficiente. Stock disponible: " + cantidad);
    }

                        /* ======================= Mensajes de Mostrar Ventas ======================= */

    /** Pregunta si desea volver al menu de ventas totales */
    public static void mensajeVolverMenuVentasTotales(){
        System.out.print("\n¿Desea volver al menú de ventas totales? (S/N): ");
    }

    /** Muestra el ticket de la venta */
    public static void mostrarVentasTotales(Venta venta){
        System.out.println(venta);
    }

    /** Informa de que no hay ventas realizadas */
    public static void noHayVentas(){
        System.out.println("No es posible acceder a la sección de ventas porque aún no se ha realizado ninguna.");
    }

    /** Muestra el importe total gastado por el cliente */
    public static void importeTotalVentasCliente(String nombre, double gasto){
        System.out.println("El cliente " + nombre + " ha gastado un total de: " + gasto + "€");
    }

    /** Muestra el importe total de cada venta */
    public static void mensajeTotalVenta(int contador, double total){
        System.out.print(contador + ". Venta: " + total + "€");
    }
}