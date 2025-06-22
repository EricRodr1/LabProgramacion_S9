/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package labprogramacion_s9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Eric Rodriguez
 */
public class CalificarCanciones extends javax.swing.JFrame {
    private JTunes jTunes;
    private JTextField txtCodigo;
    private JSpinner spinnerEstrellas;
    private JButton btnBuscar;
    private JButton btnCalificar;
    private JButton btnVolver;
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JLabel lblEstrellas;
    private JLabel lblInfoCancion;
    private Song cancionEncontrada;

    public CalificarCanciones() {
        this.jTunes = JTunesManager.getInstance();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calificar Canciones");
        setSize(400, 350);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);

        // Título
        lblTitulo = new JLabel("Calificar Canciones");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Código de la canción
        lblCodigo = new JLabel("Ingrese el código de la canción:");
        lblCodigo.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblCodigo.setForeground(Color.WHITE);
        lblCodigo.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtCodigo = new JTextField(10);
        txtCodigo.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtCodigo.setMaximumSize(new Dimension(200, 30));

        // Botón buscar
        btnBuscar = new JButton("Buscar Canción");
        btnBuscar.setBackground(Color.WHITE);
        btnBuscar.setFont(new Font("Courier New", Font.BOLD, 12));
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCancion();
            }
        });

        // Información de la canción encontrada
        lblInfoCancion = new JLabel(" ");
        lblInfoCancion.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
        lblInfoCancion.setForeground(Color.WHITE);
        lblInfoCancion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Estrellas
        lblEstrellas = new JLabel("Número de estrellas (0-5):");
        lblEstrellas.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblEstrellas.setForeground(Color.WHITE);
        lblEstrellas.setAlignmentX(Component.CENTER_ALIGNMENT);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 5, 1);
        spinnerEstrellas = new JSpinner(spinnerModel);
        spinnerEstrellas.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón calificar
        btnCalificar = new JButton("Calificar");
        btnCalificar.setBackground(Color.WHITE);
        btnCalificar.setFont(new Font("Courier New", Font.BOLD, 12));
        btnCalificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCalificar.setEnabled(false); // Inicialmente deshabilitado
        btnCalificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calificarCancion();
            }
        });

        // Botón volver
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setFont(new Font("Courier New", Font.BOLD, 12));
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAlMenu();
            }
        });

        // Agregar componentes al panel
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(lblTitulo);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(lblCodigo);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(txtCodigo);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnBuscar);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(lblInfoCancion);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(lblEstrellas);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(spinnerEstrellas);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(btnCalificar);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnVolver);
        mainPanel.add(Box.createVerticalStrut(20));

        setContentPane(mainPanel);
    }

    private void buscarCancion() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            cancionEncontrada = jTunes.searchSong(codigo);
            
            if (cancionEncontrada != null) {
                lblInfoCancion.setText("Canción encontrada: " + cancionEncontrada.getNombre() + 
                                     " - Rating actual: " + String.format("%.2f", cancionEncontrada.songRating()));
                lblInfoCancion.setForeground(Color.GREEN);
                btnCalificar.setEnabled(true);
                spinnerEstrellas.setValue(0);
            } else {
                lblInfoCancion.setText("No se encontró una canción con el código " + codigo);
                lblInfoCancion.setForeground(Color.RED);
                btnCalificar.setEnabled(false);
                cancionEncontrada = null;
            }
        } catch (NumberFormatException e) {
            lblInfoCancion.setText("Por favor ingrese un código válido (número entero)");
            lblInfoCancion.setForeground(Color.RED);
            btnCalificar.setEnabled(false);
            cancionEncontrada = null;
        }
    }

    private void calificarCancion() {
        if (cancionEncontrada == null) {
            JOptionPane.showMessageDialog(this, "Primero debe buscar una canción válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int estrellas = (Integer) spinnerEstrellas.getValue();
        jTunes.rateSong(cancionEncontrada.getCodigo(), estrellas);
        
        JOptionPane.showMessageDialog(this, 
            "Canción '" + cancionEncontrada.getNombre() + "' calificada exitosamente con " + estrellas + " estrellas", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Actualizar información de la canción
        buscarCancion();
    }

    private void volverAlMenu() {
        Menuprincipal menu = new Menuprincipal();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalificarCanciones().setVisible(true);
            }
        });
    }
} 