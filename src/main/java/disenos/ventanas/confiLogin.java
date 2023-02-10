package disenos.ventanas;

import configuracion.info;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class confiLogin extends JFrame {//clase para definir ciertas propiedades para el frame del login de la aplicacion

    private final String RUTA_IMAGEN = "img/image.png";

    public confiLogin() {
        //asigna un tamanio a la ventana
        setSize(540, 566);
        //pone que no se pueda ajustar el tamanio de esta
        setResizable(false);
        //que se muestre en el centro
        setLocationRelativeTo(this);
        //se pone el icono
        //  ponerIcono(buscarImagen(RUTA_IMAGEN));
        //se pone decoracion de windows
        ponerDecoracionDeWindows();
        ponerIcono(RUTA_IMAGEN);
        //se pone la version de la aplicacion contenida en la variable version en la clase info
        this.setTitle(info.VERSION);
    }

    private void ponerIcono(String url) {

        if (seEcontroLaRuta(url)) {
            ImageIcon imagen = new ImageIcon(url);
            Image icono = imagen.getImage();
            setIconImage(icono);
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
