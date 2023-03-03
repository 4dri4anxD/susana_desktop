/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disenos;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author dell
 */

/*
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int width = gd.getDisplayMode().getWidth();
    int height = gd.getDisplayMode().getHeight();

 */
public class putIcon {

    public putIcon() {

    }

    public void setIcon(JButton b, String ruta) {
      /*  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        System.out.println("W: "+width);
        System.out.println("H: "+height); */
        //   b.setIcon(new StretchIcon(ruta));
        ImageIcon imagen = new ImageIcon(ruta);
        int ancho = (int) (b.getWidth() / 1.5);
        int alto = (int) (b.getHeight() / 1.5);
        Image imgEscalada = imagen.getImage().getScaledInstance(ancho,
                alto, Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);
    }

    public void setIcon1(JButton b, String ruta) {
        //   b.setIcon(new StretchIcon(ruta));
        ImageIcon imagen = new ImageIcon(ruta);
        int ancho = b.getWidth();
        int alto = b.getHeight();
        Image imgEscalada = imagen.getImage().getScaledInstance(ancho,
                alto, Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);
    }

}
