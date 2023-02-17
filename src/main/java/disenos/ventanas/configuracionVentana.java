package disenos.ventanas;

import configuracion.info;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class configuracionVentana {//clase para definir ciertas propiedades para algunos frame de la aplicacion
    //declaracion de variables globales
    JFrame v;
    private final String RUTA_IMAGEN = "img/image.png";

    public configuracionVentana(JFrame v) {
        this.v=v;
        setLocation();
        maxV();//esto esta presente en cada JFrame tambien, porque sabra porque no se hereda el alto y ancho de la ventana, entonces, se tiene que llamar en el constructor de cada JFrame
        ponerDimensionMinima();
        ponerIcono(RUTA_IMAGEN);
        ponerDecoracionDeWindows();
        colorearFoco(new Color(50, 50, 96));
        v.setTitle(info.VERSION);
    }

    private void setLocation() {//establece las coordenadas en las que se mostrara la ventana, esto por si se estan usando 2 monitores
        v.setLocation(new info().getX(), new info().getY());

    }

    public final void maxV() {// maximiza la ventana
       // GraphicsEnvironment env= GraphicsEnvironment.getLocalGraphicsEnvironment();
      //  v.setMaximizedBounds(env.getMaximumWindowBounds());
     //   v.setExtendedState(v.getExtendedState() | v.MAXIMIZED_BOTH);
       // v.setExtendedState(MAXIMIZED_BOTH);
       GraphicsEnvironment env= GraphicsEnvironment.getLocalGraphicsEnvironment();
       Rectangle bounds=env.getMaximumWindowBounds();
       v.setBounds(bounds);
       
       
    }

    private void ponerDimensionMinima() {//establece una dimension minima para la ventana
        v.setMinimumSize(new Dimension(900, (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height) - ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().height) * 10 / 100)));//975/*JFrame.MAXIMIZED_VERT*/
    }

    /**
     * metodo que busca la imagen Se recomiendan imagenes de 128px X 128px
     *
     * @param ruta la direccion del package donde esta la imagen
     * @return la url
     */
    /* private URL buscarImagen(String ruta) {
        URL rutaDeLaImagen = getClass().getResource(ruta);
        return rutaDeLaImagen;
    } */
    /**
     * método que valida si se encontro la ruta
     *
     * @param url es la ruta absoluta donde esta la imagen
     * @return true si encontro la imagen, false en caso de que no
     */
    private boolean seEcontroLaRuta(String url) {
        boolean rutaEncontrada = url != null;
        return rutaEncontrada;
    }

    /**
     * método que pone el icono en la esquina superior izquierda
     *
     * @param url ruta absoluta de la imagen
     */
    private void ponerIcono(String url) {
        if (seEcontroLaRuta(url)) {
            ImageIcon imagen = new ImageIcon(url);
            Image icono = imagen.getImage();
            v.setIconImage(icono);
        }
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
            v.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.err.println(ex + "No se pudo cambiar el estilo de la ventana");
        }
    }

    /**
     * método que cambia el color de la selecion de los componentes (foco)
     */
    private void colorearFoco(Color color) {
        UIManager.put("Button.focus", color);
    }

}
