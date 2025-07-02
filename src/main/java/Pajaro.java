public class Pajaro {
    private String especie;
    private String color;
    private double precio;

    public Pajaro(String especie, String color, double precio){
        this.especie = especie;
        this.color = color;
        this.precio = precio;
    }

    public String getEspecie() {
        return especie;
    }

    public String getColor() {
        return color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Especie pájaro: " + this.getEspecie() + ", color: " + this.getColor() + ", precio: " + this.getPrecio();
    }
}