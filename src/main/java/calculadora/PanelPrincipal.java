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
    private static double tipoOperacionDecimales = 0;
    // Suponiendo que tenemos 5 + 1 veremos donde se guarda cada uno
    // Aquí guardará el primer operador antes del símbolo (5)
    private static String op1 = "";
    // Aquí guardará el símbolo y el simboAux guardará todo menos el = para 
    // poder saber que operaciones tengo que realizar (+)
    private static String simbo = "", simboAux = "";
    // Aquí guardará el segundo operador después del símbolo (1)
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
        // Llamara al método para deshabilitar los botones al principio
        botonera.deshabilitarBotones();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        // Se obtiene el objeto que desencadena el evento
        Object o = ae.getSource();
        // Si es un botón
        if (o instanceof JButton) {
            System.out.println(((JButton) o).getText());
            
            // Si los botones son simbolos
            if (((JButton) o).getText().equals("+")
                    || ((JButton) o).getText().equals("-")
                    || ((JButton) o).getText().equals("/")
                    || ((JButton) o).getText().equals("*")
                    || ((JButton) o).getText().equals("C")
                    || ((JButton) o).getText().equals("=")) {

                // Si es una C
                if (((JButton) o).getText().equals("C")) {
                    // Borrará todo lo que haya guardado la calculadora
                    op1 = "";
                    simbo = "";
                    op2 = "";
                    tipoOperacionDecimales = 0;
                    tipoOperacion = 0;
                    areaTexto.setText(op1);
                } else if (((JButton) o).getText().equals("-") && op1.isEmpty()) {
                    // Guardará el primer operador
                    op1 += ((JButton) o).getText();
                    areaTexto.setText(op1);
                    System.out.println("Operador 1: " + op1);

                } else {
                    // Si no, guardará la información del simbolos
                    simbo = ((JButton) o).getText();
                    areaTexto.setText(op1 + simbo);
                    System.out.println("Simbolo: " + simbo);
                    // Si el simbolo no es = ...
                    if (!simbo.equals("=")) {
                        // simboAux guardará el simbolo
                        simboAux = simbo;
                    }
                }
                // Si los botones son números
            } else {
                // Si el símbolo está vacío...
                if (simbo.isEmpty()) {
                    // Guardará el primer operador
                    op1 += ((JButton) o).getText();
                    areaTexto.setText(op1);
                    System.out.println("Operador 1: " + op1);
                    // Cuando llegue al operador 1, ya habilitaremos los demás 
                    // operadores
                    botonera.habilitarBotones();
                    // Si el simbolo contiene un simbolo, significará que es el 
                    // operador 2
                } else {
                    op2 += ((JButton) o).getText();
                    areaTexto.setText(op1 + simboAux + op2);
                    System.out.println("Operador 2: " + op2);
                }

            }
            // Si op2 no está vacío y el simbolo es = ...
            if (!op2.isEmpty() && simbo.equals("=")) {
                // Haremos un switch del simboAux
                switch (simboAux) {
                    // SUMA
                    case "+":
                        tipoOperacion = Integer.parseInt(op1) + Integer.parseInt(op2);
                        break;
                    //RESTA
                    case "-":
                        tipoOperacion = Integer.parseInt(op1) - Integer.parseInt(op2);
                        break;
                    // DIVISIÓN
                    case "/":
                        tipoOperacionDecimales = Double.parseDouble(op1) / Double.parseDouble(op2);
                        break;
                    // MULTIPLICACIÓN
                    case "*":
                        tipoOperacion = Integer.parseInt(op1) * Integer.parseInt(op2);
                        break;
                    // IGUAL
                    default:
                        tipoOperacion = Integer.parseInt(op1);
                        break;
                }
                // Una vez terminado el switch hago que me lo imprima todo
                // Ahora aquí divido entre ver el resultado con decimales o sin 
                // decimales

                // Si tipoOperacionDecimales es mayor a 0 que es su valor por defecto
                if (tipoOperacionDecimales > 0 ) {
                    // Imprimirá su número con decimales
                    System.out.println("Resultado: " + tipoOperacionDecimales);
                    areaTexto.setText(tipoOperacionDecimales + "");
                // Si la división sale un número menor a 0 será porque algún 
                // número es negativo y lo tendremos que avisar con el SYNTAX ERROR
                }else if((tipoOperacion >= 0 || tipoOperacion <= 0) && tipoOperacionDecimales == 0){
                    System.out.println("Resultado: " + tipoOperacion);
                    areaTexto.setText(tipoOperacion + "");
                    
                // Si no, mostrará el resultado sin decimales para que sea 
                // más estético
                } else {
                    areaTexto.setText("SYNTAX ERROR");
                    System.out.println("SYNTAX ERROR");
                }
                // Y luego limpiará todo para volver a hacer otra operacion
                op1 = "";
                simbo = "";
                op2 = "";
                tipoOperacionDecimales = 0;
                tipoOperacion = 0;
                // Y deshabilito los botones otra vez para que no vuelvan a 
                // aparecer al principio
                botonera.deshabilitarBotones();
            }
        }   
    }
}
