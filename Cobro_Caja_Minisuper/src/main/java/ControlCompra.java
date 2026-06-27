package main.java;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.EOFException;

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

    public void guardarBinario() {

        DataOutputStream salida = null;

        try {

            salida = new DataOutputStream(
                    new FileOutputStream(RutaArchivos.resolver(ARCHIVO)));

            for (ProductoDescuento p : productos) {

                salida.writeInt(p.getId());
                salida.writeUTF(p.getNombre());
                salida.writeDouble(p.getPrecio());
                salida.writeDouble(p.getDescuento());

            }

            System.out.println("Archivo guardado correctamente");

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            if (salida != null) {
                try {
                    salida.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    public void cargar() {

        productos.clear();
        DataInputStream entrada = null;

        try {

            entrada = new DataInputStream(
                    new FileInputStream(RutaArchivos.resolver(ARCHIVO)));

            while (true) {

                int id = entrada.readInt();
                String nombre = entrada.readUTF();
                double precio = entrada.readDouble();
                double descuento = entrada.readDouble();

                ProductoDescuento p =
                        new ProductoDescuento(id, nombre, precio, descuento);

                productos.add(p);
            }

        } catch (EOFException e) {

            // Fin del archivo

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }
}
