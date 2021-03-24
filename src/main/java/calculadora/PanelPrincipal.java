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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Juan Carlos Fernández Vico
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;
    private static ArrayList<String> operandos = new ArrayList<>();
    private static String op1 = "";
    private static String simbo = "";
    private static String op2 = "";

    // Constructor
    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1; // No hay operaciones en la calculadora
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);
        for (JButton boton : this.botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        // Se obtiene el objeto que desencadena el evento
        Object o = ae.getSource();
        // Si es un botón
        if (o instanceof JButton) {
            System.out.println(((JButton) o).getText());

            // Si los botones son operadores
            if (((JButton) o).getText().equals("+")
                    || ((JButton) o).getText().equals("-")
                    || ((JButton) o).getText().equals("/")
                    || ((JButton) o).getText().equals("*")
                    || ((JButton) o).getText().equals("C")
                    || ((JButton) o).getText().equals("=")) {

                if (((JButton) o).getText().equals("C")) {
                    op1 = "";
                    simbo = "";
                    op2 = "";
                    areaTexto.setText(op1);
//                    }else if(((JButton) o).getText().equals("=") && !op2.isEmpty()){

                } else {
                    simbo = ((JButton) o).getText();
                    areaTexto.setText(op1 + simbo);
                    operandos.add(1, op1);
                    System.out.println("OP: " + simbo);
                }
                // Si los botones son números
            } else if (!((JButton) o).getText().equals("+")
                    || !((JButton) o).getText().equals("-")
                    || !((JButton) o).getText().equals("/")
                    || !((JButton) o).getText().equals("*")
                    || !((JButton) o).getText().equals("C")
                    || !((JButton) o).getText().equals("=")) {
                if (simbo.isEmpty()) {
                    op1 += ((JButton) o).getText();
                    areaTexto.setText(op1);
                    operandos.add(0, op1);
                    System.out.println("TT 1: " + op1);
                } else {
                    op2 += ((JButton) o).getText();
                    areaTexto.setText(op2);
                    System.out.println("TT 2: " + op2);
                }

            } else {

            }
            if (!op2.isEmpty()) {
                switch (simbo) {
                    case "+":
                        tipoOperacion = Integer.parseInt(op1) + Integer.parseInt(op2);
                        System.out.println(tipoOperacion);
                    case "-":
                        tipoOperacion = Integer.parseInt(op1) - Integer.parseInt(op2);
                        System.out.println(tipoOperacion);
                    case "/":
                        tipoOperacion = Integer.parseInt(op1) % Integer.parseInt(op2);
                        System.out.println(tipoOperacion);
                    case "*":
                        tipoOperacion = Integer.parseInt(op1) * Integer.parseInt(op2);
                        System.out.println(tipoOperacion);
                }
            }
        }

    }
}
