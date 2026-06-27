package GUI;

import main.java.Carrito;
import main.java.ControlCompra;
import main.java.Inventario;
import main.java.Producto;
import main.java.ProductoDescuento;
import main.java.StockInsuficienteException;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;

public class Inicio extends JFrame {
    private JPanel PanelInicio;
    //Eber
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnTotal;
    private JButton btnGuardar;
    private JLabel lblResultado;
    private JLabel lblTotal;
    private ControlCompra control;
    //

    private JComboBox<Producto> cmbProductos;
    private JTextField txtCantidad;
    private JButton btnAgregar;
    private Inventario inventario;
    private Carrito carrito;
    private JTable tablaCarrito;
    private DefaultTableModel modeloTabla;
    //

    //Constructor
    public Inicio() {
        setContentPane(PanelInicio);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cobro Caja - Minisuper");
        setMinimumSize(new java.awt.Dimension(480, 360));
        pack();
        setLocationRelativeTo(null);

        inventario = new Inventario();
        inventario.cargarDesdeArchivo();
        carrito = new Carrito();
        cargarComboProductos();
        crearTablaCarrito();

        // Parte Eber: Botones Buscar, Guardar y Calcular total
        control = new ControlCompra();
        control.cargar();

        // BUSCAR
        btnBuscar.addActionListener(e -> buscarProducto());

        // Persona 2: al elegir del combo mostrar producto
        cmbProductos.addActionListener(e -> seleccionarDelCombo());

        // Persona 2: agregar al carrito con validacion de stock
        btnAgregar.addActionListener(e -> agregarAlCarrito());

        // TOTAL
        btnTotal.addActionListener(e -> calcularTotalTabla());

        // GUARDAR
        btnGuardar.addActionListener(e -> {
            control.guardarBinario();
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
        });

        setVisible(true);
    }

    private void cargarComboProductos() {
        cmbProductos.removeAllItems();
        for (Producto producto : inventario.getProductos().values()) {
            cmbProductos.addItem(producto);
        }
    }

    private void seleccionarDelCombo() {
        Producto producto = (Producto) cmbProductos.getSelectedItem();
        if (producto != null) {
            txtBuscar.setText(String.valueOf(producto.getCodigo()));
            lblResultado.setText(
                    producto.getNombre()
                            + " | Precio: " + producto.getPrecioFinal()
                            + " | Stock: " + producto.getStock());
        }
    }

    // BUSCAR PRODUCTO
    private void buscarProducto() {

        try {

            int id = Integer.parseInt(txtBuscar.getText());

            Producto producto = inventario.buscarPorCodigo(id);

            if (producto != null) {
                lblResultado.setText(
                        producto.getNombre()
                                + " | Precio: " + producto.getPrecioFinal()
                                + " | Stock: " + producto.getStock());
                cmbProductos.setSelectedItem(producto);
            } else {
                ProductoDescuento p = control.buscarProducto(id);
                if (p != null) {
                    lblResultado.setText(
                            p.getNombre() +
                                    " | Precio final: " + p.getPrecioFinal()
                    );
                } else {
                    lblResultado.setText("Producto no encontrado");
                }
            }

        } catch (Exception e) {
            lblResultado.setText("ID inválido");
        }
    }

    private void agregarAlCarrito() {
        Producto producto = (Producto) cmbProductos.getSelectedItem();
        if (producto == null) {
            lblResultado.setText("Seleccione un producto del combo");
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            carrito.agregarProducto(producto, cantidad);
            ProductoDescuento p = new ProductoDescuento(
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    0
            );
            control.agregarProducto(p);
            double precio = producto.getPrecioFinal();
            double subtotal = precio * cantidad;

            modeloTabla.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    cantidad,
                    precio,
                    subtotal
            });

            lblResultado.setText(
                    "Agregado: " + producto.getNombre()
                            + " x" + cantidad
                            + " | Stock restante: " + producto.getStock()
                            + " | Items en carrito: " + carrito.getProductos().size());

        } catch (StockInsuficienteException e) {
            lblResultado.setText(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Stock insuficiente", JOptionPane.WARNING_MESSAGE);

        } catch (NumberFormatException e) {
            lblResultado.setText("Cantidad invalida");

        } catch (IllegalArgumentException e) {
            lblResultado.setText(e.getMessage());
        }
    }

    private void crearTablaCarrito() {

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Producto");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Subtotal");

        tablaCarrito.setModel(modeloTabla);
    }
    private void calcularTotalTabla() {

        double total = 0;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {

            total += Double.parseDouble(
                    modeloTabla.getValueAt(i, 4).toString());

        }

        lblTotal.setText("Total: $" + total);

    }

}
