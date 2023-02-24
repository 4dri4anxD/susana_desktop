
package disenos.ventanas;

import configuracion.info;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class configAlert {//clase para definir ciertas propiedades para el frame del login de la aplicacion

    private final String RUTA_IMAGEN = "img/image.png";
    JFrame v;

    public configAlert(JFrame v) {
        //asigna un tamanio a la ventana
        this.v=v;
        v.setSize(520, 220);
       // v.setBounds(new info().getX(),new info().getY(),500,100);
        //pone que no se pueda ajustar el tamanio de esta
        v.setResizable(false);
        //que se muestre en el centro
        v.setLocationRelativeTo(v);
        //se pone el icono
        //  ponerIcono(buscarImagen(RUTA_IMAGEN));
        //se pone decoracion de windows
        ponerDecoracionDeWindows();
        ponerIcono(RUTA_IMAGEN);
        //se pone la version de la aplicacion contenida en la variable version en la clase info
      //  v.setTitle(info.VERSION);
    }

    private void ponerIcono(String url) {

        if (seEcontroLaRuta(url)) {
            ImageIcon imagen = new ImageIcon(url);
            Image icono = imagen.getImage();
            v.setIconImage(icono);
        }

    }

    private boolean seEcontroLaRuta(String url) {
        boolean rutaEncontrada = url != null;
        return rutaEncontrada;
    }

    /**
     * método que cambia la decoracion de los elementos swing al diseño de
     * windows.
     */
    private void ponerDecoracionDeWindows() {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.err.println(ex + "No se pudo cambiar el estilo de la ventana");
        }
    }
}
