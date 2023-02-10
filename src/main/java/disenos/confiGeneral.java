package disenos;

import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class confiGeneral {//clase para definir ciertas propiedades para los frame de la aplicacion

    private final String RUTA_IMAGEN = "img/image.png";

    public confiGeneral(JFrame frame) {
        // frame.setSize(660, 620);
        frame.setExtendedState(MAXIMIZED_BOTH);
        //pone que no se pueda ajustar el tamanio de esta
        frame.setResizable(false);
        //que se muestre en el centro
        frame.setLocationRelativeTo(frame);
        //se pone el icono
        ponerIcono(buscarImagen(RUTA_IMAGEN), frame);
        //se pone decoracion de windows
        ponerDecoracionDeWindows();
        ponerDimensionMinima(frame);

    }

    private void ponerDimensionMinima(JFrame frame) {//establece una dimension minima para la ventana
        frame.setMinimumSize(new Dimension(900, (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height) - ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().height) * 10 / 100)));//975/*JFrame.MAXIMIZED_VERT*/
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
