package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {
    // Campos para mostrar los números y resultados
    private JTextField pantalla;
    private double numero1 = 0, numero2 = 0, resultado = 0;
    private String operador = "";

    public CalculadoraGUI() {
        // Configuración principal de la ventana
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configura el fondo de la ventana
        getContentPane().setBackground(Color.DARK_GRAY);

        // Campo de texto para la pantalla de la calculadora
        pantalla = new JTextField();
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);
        pantalla.setFont(new Font("Arial", Font.BOLD, 30));
        pantalla.setBackground(Color.BLACK);
        pantalla.setForeground(Color.GREEN); // Color del texto de la pantalla
        add(pantalla, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4, 10, 10));
        panelBotones.setBackground(Color.DARK_GRAY); // Fondo del panel de botones

        // Crear botones numéricos y de operaciones con colores personalizados
        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String texto : botones) {
            JButton boton = new JButton(texto);

            // Agregar diferentes colores según el tipo de botón
            if ("0123456789".contains(texto)) {
                boton.setBackground(Color.LIGHT_GRAY); // Botones numéricos
            } else if ("/*-+=C".contains(texto)) {
                boton.setBackground(Color.ORANGE); // Botones de operaciones o limpiar
            }

            // Color del texto en los botones
            boton.setForeground(Color.BLACK);

            // Fuente del texto en los botones
            boton.setFont(new Font("Arial", Font.BOLD, 20));

            // Agregar acción al botón
            boton.addActionListener(this);
            panelBotones.add(boton);
        }

        // Añadir el panel de botones al centro de la calculadora
        add(panelBotones, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ("0123456789".contains(comando)) {
            // Si se presiona un número
            pantalla.setText(pantalla.getText() + comando);
        } else if ("/*-+".contains(comando)) {
            // Si se presiona un operador
            operador = comando;
            numero1 = Double.parseDouble(pantalla.getText());
            pantalla.setText("");
        } else if ("=".equals(comando)) {
            // Si se presiona el botón "="
            numero2 = Double.parseDouble(pantalla.getText());

            switch (operador) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        pantalla.setText("Error");
                        return;
                    }
                    break;
            }

            pantalla.setText(String.valueOf(resultado));
        } else if ("C".equals(comando)) {
            // Si se presiona el botón "C" para limpiar
            pantalla.setText("");
            numero1 = 0;
            numero2 = 0;
            operador = "";
        }
    }

    public static void main(String[] args) {
        // Crear y mostrar la calculadora
        SwingUtilities.invokeLater(() -> {
            CalculadoraGUI calculadora = new CalculadoraGUI();
            calculadora.setVisible(true);
        });
    }
}