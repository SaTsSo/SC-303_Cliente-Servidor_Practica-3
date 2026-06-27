package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ControlCompra {

    private static final String ARCHIVO = "productos.txt";

    private ArrayList<ProductoDescuento> productos;

    public ControlCompra(){
        productos = new ArrayList<>();
    }

    public void agregarProducto(ProductoDescuento p){
        productos.add(p);
    }

    public double calcularTotal(){
        double total = 0;

        for(ProductoDescuento p : productos){
            total += p.getPrecioFinal();
        }

        return total;
    }

    public ProductoDescuento buscarProducto(int id) {

        for (ProductoDescuento p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    public void guardarBinario(){

        PrintWriter out = null;

        try {

            out = new PrintWriter(new FileWriter(RutaArchivos.resolver(ARCHIVO)));

            for (ProductoDescuento p : productos) {
                out.println(
                        p.getId() + ","
                                + p.getNombre() + ","
                                + p.getPrecio() + ","
                                + p.getDescuento()
                );
            }

        } catch (Exception e) {
            System.out.println("Error guardando archivo: " + e.getMessage());

        } finally {
            if (out != null) {
                out.close();
            }
            System.out.println("Proceso terminado");
        }

    }

    public void cargar() {

        productos.clear();
        BufferedReader in = null;

        try {

            in = new BufferedReader(new FileReader(RutaArchivos.resolver(ARCHIVO)));
            String linea;

            while ((linea = in.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0].trim());
                String nombre = datos[1].trim();
                double precio = Double.parseDouble(datos[2].trim());
                double descuento = Double.parseDouble(datos[3].trim());

                ProductoDescuento p =
                        new ProductoDescuento(id, nombre, precio, descuento);

                productos.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar archivo: " + e.getMessage());
                }
            }
        }
    }
}
