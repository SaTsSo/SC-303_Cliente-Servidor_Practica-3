package main.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario {

    private Map<Integer, Producto> productos;

    public Inventario() {
        this.productos = new HashMap<>();
    }

    public void cargarDesdeArchivo() {
        this.productos.clear();
        List<Producto> lista = ControlArchivo.leerProductos();
        for (Producto producto : lista) {
            this.productos.put(producto.getCodigo(), producto);
        }
    }

    public Producto buscarPorCodigo(int codigo) {
        return this.productos.get(codigo);
    }

    public void reducirStock(int codigo, int cantidad) throws StockInsuficienteException {
        Producto producto = buscarPorCodigo(codigo);
        if (producto == null) {
            throw new StockInsuficienteException("Producto no encontrado con codigo: " + codigo);
        }
        producto.reducirStock(cantidad);
    }

    public Map<Integer, Producto> getProductos() {
        return this.productos;
    }
}
