/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author angel
 */
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel {

    // Números del 0 al 9 y seis botones más para
    // suma, resta, multiplicacion, división, resultado y limpiar
    JButton[] grupoBotones = new JButton[16];

    public PanelBotones() {
        initComponents();
    }

    private void initComponents() {

        // Creación de los botones
        for (int i = 0; i < 10; i++) {
            grupoBotones[i] = new JButton(Integer.toString(i));
        }
        grupoBotones[10] = new JButton("+");
        grupoBotones[11] = new JButton("-");
        grupoBotones[12] = new JButton("*");
        grupoBotones[13] = new JButton("/");
        grupoBotones[14] = new JButton("=");
        grupoBotones[15] = new JButton("C");

        // Establecemos el layout
        this.setLayout(new GridLayout(4, 4));

        for (JButton boton : grupoBotones) {
            this.add(boton);
        }
    }

    public JButton[] getgrupoBotones() {
        return grupoBotones;
    }

    // Deshabilitará los botones "+, *, / y ="
    public void deshabilitarBotones() {
        grupoBotones[10].setEnabled(false);
        grupoBotones[12].setEnabled(false);
        grupoBotones[13].setEnabled(false);
        grupoBotones[14].setEnabled(false);
    }

    // Habilitará los botones "+, *, / y ="
    public void habilitarBotones() {
        grupoBotones[10].setEnabled(true);
        grupoBotones[12].setEnabled(true);
        grupoBotones[13].setEnabled(true);
        grupoBotones[14].setEnabled(true);
    }
}
