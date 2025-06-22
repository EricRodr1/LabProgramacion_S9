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
public class MostrarCanciones extends javax.swing.JFrame {
    private JTunes jTunes;
    private JPanel panelCanciones;
    private JScrollPane scrollPane;
    private JButton btnVolver;

    public MostrarCanciones() {
        this.jTunes = JTunesManager.getInstance();
        initComponents();
        mostrarCanciones();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mostrar Canciones");
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Título
        JLabel lblTitulo = new JLabel("Canciones Disponibles");
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Panel de canciones con scroll
        panelCanciones = new JPanel();
        panelCanciones.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelCanciones.setBackground(Color.BLACK);

        scrollPane = new JScrollPane(panelCanciones);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Botón volver
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setFont(new Font("Courier New", Font.BOLD, 12));
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAlMenu();
            }
        });

        // Agregar componentes
        mainPanel.add(lblTitulo, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(btnVolver, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public void mostrarCanciones() {
        panelCanciones.removeAll();
        Song[] canciones = jTunes.getAllSongs();
        
        if (canciones.length == 0) {
            JLabel lblNoCanciones = new JLabel("No hay canciones disponibles");
            lblNoCanciones.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
            lblNoCanciones.setForeground(Color.WHITE);
            panelCanciones.add(lblNoCanciones);
        } else {
            for (Song s : canciones) {
                JPanel tarjeta = crearTarjetaCancion(s);
                panelCanciones.add(tarjeta);
            }
        }
        
        panelCanciones.revalidate();
        panelCanciones.repaint();
    }

    private JPanel crearTarjetaCancion(Song cancion) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        tarjeta.setPreferredSize(new Dimension(200, 300));
        tarjeta.setMaximumSize(new Dimension(200, 300));

        // Imagen del disco
        JLabel imgLabel;
        if (cancion.getImagenDisco() != null) {
            Image img = cancion.getImagenDisco().getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imgLabel = new JLabel(new ImageIcon(img));
        } else {
            imgLabel = new JLabel("Sin imagen");
            imgLabel.setPreferredSize(new Dimension(150, 150));
            imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Información de la canción
        JLabel lblCodigo = new JLabel("Código: " + cancion.getCodigo());
        JLabel lblNombre = new JLabel("Nombre: " + cancion.getNombre());
        JLabel lblPrecio = new JLabel("Precio: Lps. " + cancion.getPrecio());
        JLabel lblRating = new JLabel("Rating: " + String.format("%.2f", cancion.songRating()));

        // Configurar etiquetas
        Font font = new Font("Trebuchet MS", Font.PLAIN, 12);
        lblCodigo.setFont(font);
        lblNombre.setFont(font);
        lblPrecio.setFont(font);
        lblRating.setFont(font);

        lblCodigo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRating.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar componentes a la tarjeta
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(imgLabel);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(lblCodigo);
        tarjeta.add(lblNombre);
        tarjeta.add(lblPrecio);
        tarjeta.add(lblRating);
        tarjeta.add(Box.createVerticalStrut(10));

        return tarjeta;
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
                new MostrarCanciones().setVisible(true);
            }
        });
    }
} 