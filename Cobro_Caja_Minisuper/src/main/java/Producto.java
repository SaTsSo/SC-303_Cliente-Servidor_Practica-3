package main.java;

public class Producto extends ProductoBase {

    private int stock;

    public Producto(int codigo, String nombre, double precio, int stock) {
        super(codigo, nombre, precio);
        this.stock = stock;
    }

    public int getCodigo() {
        return getId();
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void reducirStock(int cantidad) throws StockInsuficienteException {
        if (cantidad > this.stock) {
            throw new StockInsuficienteException(
                    "No hay stock suficiente para " + getNombre() + ". Disponible: " + this.stock);
        }
        this.stock = this.stock - cantidad;
    }

    @Override
    public double getPrecioFinal() {
        return getPrecio();
    }

    @Override
    public String toString() {
        return getCodigo() + " - " + getNombre();
    }
}
