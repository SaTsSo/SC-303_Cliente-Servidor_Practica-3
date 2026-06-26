package main.java;

import java.io.*;
import java.util.ArrayList;

public class ControlCompra {

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
            total += p.calcularPrecio();
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

        try {

            DataOutputStream out  = new DataOutputStream(
                    new FileOutputStream("productos.txt")
            );

            for (ProductoDescuento p : productos) {

                out.writeInt(p.getId());
                out.writeUTF(p.getNombre());
                out.writeDouble(p.getPrecio());
                out.writeDouble(p.getDescuento());
            }
            out.close();

        }
        catch(Exception e){
            System.out.println("Error guardando archivo: " + e.getMessage());

        }
        finally {
            System.out.println(
                    "Proceso terminado"
            );


        }

    }
    public void cargar() {

        productos.clear();

        try {

            DataInputStream in = new DataInputStream(
                    new FileInputStream("productos.txt")
            );

            while (true) {

                int id = in.readInt();
                String nombre = in.readUTF();
                double precio = in.readDouble();
                double descuento = in.readDouble();

                ProductoDescuento p =
                        new ProductoDescuento(id, nombre, precio, descuento);

                productos.add(p);
            }

        } catch (EOFException e) {

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
