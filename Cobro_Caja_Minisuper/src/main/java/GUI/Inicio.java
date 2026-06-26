package GUI;

import javax.swing.*;

public class Inicio extends JFrame {
    private JPanel PanelInicio;


    //Constructor
    public Inicio() {
        setContentPane(PanelInicio);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setEnabled(true);
        setTitle("Notas");
        setSize(1000, 1000);
        setVisible(true);


    }
}
