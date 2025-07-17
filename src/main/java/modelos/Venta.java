package modelos;

import java.util.ArrayList;

/**
 * Clase que representa una venta de una tienda de pájaros
 */
public class Venta {
    private Cliente cliente;
    private ArrayList<Pajaro> lineasDeVenta;
    private String fecha;
    private double total;

    /**
     * Parámetros para crear una venta
     *
     * @param cliente Cliente que realiza la compra
     * @param lineasDeVenta Arraylist con todos los pájaros comprados
     * @param fecha Fecha del dia de la venta
     */
    public Venta(Cliente cliente, ArrayList<Pajaro> lineasDeVenta, String fecha){
        this.cliente = cliente;
        this.lineasDeVenta = lineasDeVenta;
        this.fecha = fecha;
        this.total = 0.00;
    }

    /** @return Cliente que hace la compra */
    public Cliente getCliente() {
        return cliente;
    }

    /** @return  Arraylist con los pájaros comprados */
    public ArrayList<Pajaro> getLineasDeVenta() {
        return lineasDeVenta;
    }

    /** @return  Dia de la compra */
    public String getFecha() {
        return fecha;
    }

    /** @param cliente Nuevo cliente */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /** @param lineasDeVenta Nueva lista de pájaros */
    public void setLineasDeVenta(ArrayList<Pajaro> lineasDeVenta) {
        this.lineasDeVenta = lineasDeVenta;
    }

    /** @param fecha Nueva fecha de la compra */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /** @param total Nuevo importe total de la compra */
    public void setTotal(double total) {
        this.total = total;
    }

    /** @return String con la información de la venta */
    @Override
    public String toString() {
        String pajarosComprados = "";

        for (Pajaro pajaro: this.lineasDeVenta){
            pajarosComprados += pajaro.getEspecie() + ", ";
        }
        pajarosComprados = pajarosComprados.substring(0, pajarosComprados.length()- 2);

        return """
                
                -----------------------------
                Venta:
                    Cliente: %s
                    Pájaros: %s
                    Total: %.2f€
                ------------------------------
                Fecha: %s
                -----------------------------""".formatted(this.cliente.getNombre(), pajarosComprados, this.total, this.fecha);
    }
}