
package disenos.ventanas;

import configuracion.info;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class configEXTRAS extends JFrame {//clase para definir ciertas propiedades para algunos frame de la aplicacion
    //declaracion de variables globales
    private final String RUTA_IMAGEN = "img/image.png";

    public configEXTRAS() {
        
        
        setLocation();
        maxV();//esto esta presente en cada JFrame tambien, porque sabra porque no se hereda el alto y ancho de la ventana, entonces, se tiene que llamar en el constructor de cada JFrame
        //ponerDimensionMinima();
        this.setResizable(false);
        //que se muestre en el centro
        this.setLocationRelativeTo(null);
        ponerIcono(RUTA_IMAGEN);
        ponerDecoracionDeWindows();
        colorearFoco(new Color(50, 50, 96));
        
        
        this.setTitle(info.VERSION);
        
    }
    
    private void setLocation(){//establece las coordenadas en las que se mostrara la ventana, esto por si se estan usando 2 monitores
      //  this.setLocation(new info().getX(), new info().getY());
    }

    public void maxV() {// maximiza la ventana
        this.setSize(800, 700);
    }

  
    

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
            setIconImage(icono);
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
