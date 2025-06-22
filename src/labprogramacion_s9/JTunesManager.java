/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labprogramacion_s9;

/**
 *
 * @author Eric Rodriguez
 */
public class JTunesManager {
    private static JTunes instance = null;
    
    public static JTunes getInstance() {
        if (instance == null) {
            instance = new JTunes(10);
        }
        return instance;
    }
    
    public static void resetInstance() {
        instance = null;
    }
} 