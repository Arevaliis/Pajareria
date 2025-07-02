import java.util.ArrayList;

public class Venta {
    private Cliente cliente;
    private ArrayList<Pajaro> lineasDeVenta; //Cada venta contiene varios Paj
    private String fecha;

    public Venta(Cliente cliente, ArrayList<Pajaro> lineasDeVenta, String fecha){
        this.cliente = cliente;
        this.lineasDeVenta = lineasDeVenta;
        this.fecha = fecha;
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

    @Override
    public String toString() {
        String pajarosComprados = "";
        double total = 0.00;

        for (Pajaro pajaro: this.lineasDeVenta){
            pajarosComprados += pajaro.getEspecie() + ", ";
            total += pajaro.getPrecio();
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
                -----------------------------""".formatted(this.cliente.getNombre(), pajarosComprados, total, this.fecha);
    }
}