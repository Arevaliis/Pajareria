package modelos;

import java.util.ArrayList;

public class Venta {
    private Cliente cliente;
    private ArrayList<Pajaro> lineasDeVenta;
    private String fecha;
    private double total;

    public Venta(Cliente cliente, ArrayList<Pajaro> lineasDeVenta, String fecha){
        this.cliente = cliente;
        this.lineasDeVenta = lineasDeVenta;
        this.fecha = fecha;
        this.total = 0.00;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Pajaro> getLineasDeVenta() {
        return lineasDeVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setLineasDeVenta(ArrayList<Pajaro> lineasDeVenta) {
        this.lineasDeVenta = lineasDeVenta;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTotal(double total) {
        this.total = total;
    }

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