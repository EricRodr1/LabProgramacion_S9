/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labprogramacion_s9;

/**
 *
 * @author Eric Rodriguez
 */
public class JTunes {
    private Song[] canciones;

    public JTunes(int capacidad) {
        canciones = new Song[capacidad];
    }

    public boolean addSong(int codigo, String nombre, double precio, ImageIcon imagenDisco) {
        for (Song s : canciones) {
            if (s != null && s.getCodigo() == codigo) return false; // código duplicado
        }
        for (int i = 0; i < canciones.length; i++) {
            if (canciones[i] == null) {
                canciones[i] = new Song(codigo, nombre, precio, imagenDisco);
                return true;
            }
        }
        return false; // no hay espacio
    }

    public Song searchSong(int codigo) {
        for (Song s : canciones) {
            if (s != null && s.getCodigo() == codigo) return s;
        }
        return null;
    }

    public void rateSong(int codigo, int stars) {
        Song s = searchSong(codigo);
        if (s != null) {
            s.addStars(stars);
        }
    }

    public Song[] getAllSongs() {
        int count = 0;
        for (Song s : canciones) if (s != null) count++;

        Song[] result = new Song[count];
        int index = 0;
        for (Song s : canciones) {
            if (s != null) result[index++] = s;
        }
        return result;
    }
}
