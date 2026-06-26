package GUI;

import main.java.ControlCompra;
import main.java.ProductoDescuento;

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

    //Constructor
    public Inicio() {
        setContentPane(PanelInicio);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setEnabled(true);
        setTitle("Notas");
        setSize(1000, 1000);
        setVisible(true);

        // Parte Eber: Botones Buscar, Guardar y Calcular total
        control = new ControlCompra();
        control.cargar();

        // BUSCAR
        btnBuscar.addActionListener(e -> buscarProducto());

        // TOTAL
        btnTotal.addActionListener(e -> {
            lblTotal.setText("Total: " + control.calcularTotal());
        });

        // GUARDAR
        btnGuardar.addActionListener(e -> {
            control.guardarBinario();
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
        });
    }

    // BUSCAR PRODUCTO
    private void buscarProducto() {

        try {

            int id = Integer.parseInt(txtBuscar.getText());

            ProductoDescuento p = control.buscarProducto(id);

            if (p != null) {
                lblResultado.setText(
                        p.getNombre() +
                                " | Precio final: " + p.calcularPrecio()
                );
            } else {
                lblResultado.setText("Producto no encontrado");
            }

        } catch (Exception e) {
            lblResultado.setText("ID inválido");
        }
    }
}

