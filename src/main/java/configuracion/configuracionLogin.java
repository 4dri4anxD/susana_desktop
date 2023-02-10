package configuracion;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class configuracionLogin {

    private final String RUTA_IMAGEN = "img/logo1susana.png";

    public configuracionLogin(JFrame frame) {
        frame.setSize(660, 620);
        //pone que no se pueda ajustar el tamanio de esta
        frame.setResizable(false);
        //que se muestre en el centro
        frame.setLocationRelativeTo(frame);
        //se pone el icono
        ponerIcono(buscarImagen(RUTA_IMAGEN), frame);
        //se pone decoracion de windows
        ponerDecoracionDeWindows();

    }

    private void ponerDecoracionDeWindows() {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.err.println(ex + "No se pudo cambiar el estilo de la ventana");
        }
    }

    private void ponerIcono(URL url, JFrame frame) {
        if (seEcontroLaRuta(url)) {
            ImageIcon imagen = new ImageIcon(url);
            Image icono = imagen.getImage();
            frame.setIconImage(icono);
        }
    }

    private URL buscarImagen(String ruta) {
        URL rutaDeLaImagen = getClass().getResource(ruta);
        return rutaDeLaImagen;
    }

    private boolean seEcontroLaRuta(URL url) {
        boolean rutaEncontrada = url != null;
        return rutaEncontrada;
    }

}
