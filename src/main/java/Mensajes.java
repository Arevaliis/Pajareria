public class Mensajes {
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

    public static void menuPajaros(){
        System.out.println("""
                
                ======== MENÚ PÁJAROS ========
                    1. Alta de pájaro
                    2. Listado
                    3. Búsqueda por especie
                    4. Volver
                ==============================
                """);
    }

    public static void menuModificarValores(){
        System.out.println("""
                
                ======== CAMPOS CLIENTE ========
                        1. Nombre
                        2. Dni
                        3. Teléfono
                        4. Email
                        5. Volver
                ==============================
                """);
    }

    public static void saliendo(){
        System.out.println("\nSaliendo...");
    }

    public static void clienteNoExiste(){
        System.out.println("No se ha encontrado ningún cliente con ese DNI.");
    }

    public static void yaExisteDni(){
        System.out.println("Error: Ya existe un cliente registrado con ese DNI.");
    }

    public static void clienteAgregado(){
        System.out.println("Cliente registrado correctamente.");
    }

    public static void clienteEliminado(){
        System.out.println("Cliente eliminado exitosamente.");
    }

    public static void agregarOtroCliente(){
        System.out.print("\n¿Desea agregar otro cliente? (S/N): ");
    }

    public static void ingresarOtroDni(){
        System.out.print("\n¿Desea ingresar otro DNI? (S/N): ");
    }

    public static void baseDatosVacia(){
        System.out.println("Base de datos clientes vacía.");
    }

    public static void mensajeNombre(){
        System.out.print("Ingrese el nombre del cliente: ");
    }

    public static void mensajeDni(){
        System.out.print("Ingrese el DNI del cliente: ");
    }

    public static void mensajeTelefono(){
        System.out.print("Ingrese el número de teléfono del cliente: ");
    }

    public static void mensajeEmail(){
        System.out.print("Ingrese el correo electrónico del cliente: ");
    }

    public static void  emailRepetido(){
        System.out.println("Error: Ya existe un cliente con ese correo electrónico.\n");
    }

    public static void telefonoRepetido(){
        System.out.println("Error: Ya existe un cliente con ese número de teléfono.\n");
    }

    public static void probarNuevamente(){
        System.out.print("\n¿Desea intentarlo de nuevo? (S/N): ");
    }

    public static void mostarCliente(Cliente cliente){
        System.out.println(cliente);
    }

    public static void buscarDeNuevo(){
        System.out.print("\n¿Desea buscar otro cliente por DNI? (S/N): ");
    }

    public static void confirmarEliminacion(){
        System.out.print("\n¿Está seguro de que desea eliminar este cliente? (S/N): ");
    }

    public static void modifcarAlgoMas(){
        System.out.print("\n¿Desea modificar otro dato de este cliente? (S/N): ");
    }

    public static void mensajeVolverMenu(){
        System.out.print("\n¿Desea volver al menú principal? (S/N): ");
    }

    public static void mensajeVolverMenuClientes(){
        System.out.print("\n¿Desea volver al menú de gestión de clientes? (S/N): ");
    }

    public static void mensajeVolverMenuPajaros(){
        System.out.print("\n¿Desea volver al menú de gestión de pájaros? (S/N): ");
    }

    public static void mensajeEspecie(){
        System.out.print("Ingrese la especie del pájaro: ");
    }

    public static void mensajeColor(){
        System.out.print("Ingrese el color del pájaro: ");
    }

    public static void mensajePrecio(){
        System.out.print("Ingrese el precio del pájaro (use punto para los decimales): ");
    }

    public static void agregadoPajaroConExito(){
        System.out.println("El pájaro ha sido registrado correctamente.");
    }

    public static void agregarOtroPajaro(){
        System.out.print("\n¿Desea registrar otro pájaro? (S/N): ");
    }

    public static void tipoEspecie(String especie){
        System.out.println(especie);
    }

    public static void volverBuscarPajaro(){
        System.out.print("\n¿Desea realizar otra búsqueda por especie? (S/N): ");
    }

    public static void mostrarPajaro(Pajaro pajaro){
        System.out.println(pajaro);
    }

    public static void noExistePajaro(){
        System.out.println("No se ha encontrado ningún pájaro de esa especie.");
    }
}