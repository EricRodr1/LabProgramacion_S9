/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labprogramacion_s9;

/**
 *
 * @author Eric Rodriguez
 */
public class MainJTunes {
    public static void main(String[] args) {
        // Inicializar la instancia de JTunes
        JTunesManager.getInstance();
        
        // Crear y mostrar la interfaz gráfica principal
        Menuprincipal visualizar = new Menuprincipal();
        visualizar.setVisible(true);
        visualizar.setLocationRelativeTo(null);
    }
}
