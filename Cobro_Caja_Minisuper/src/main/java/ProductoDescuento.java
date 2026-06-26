package main.java;

public class ProductoDescuento extends ProductoBase{
    private double descuento;


    public ProductoDescuento(int id, String nombre, double precio, double descuento) {

        super(id, nombre, precio);

        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }


    @Override
    public double getPrecioFinal() {
        return super.getPrecio() -
                (super.getPrecio() * descuento / 100);
    }
}
