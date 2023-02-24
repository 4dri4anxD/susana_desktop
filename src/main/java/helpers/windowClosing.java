
package helpers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class windowClosing {

    public windowClosing(String idioma, JFrame jf) {
        String texto1, texto2, o1, o2;
        if (idioma.equals("English")) {
            texto1 = "Are you sure you want to exit?";
            texto2 = "Confirm action";
            o2 = "Yes";
        } else {
            texto1 = "¿Esta seguro de que quiere salir?";
            texto2 = "Confirmar Accion";
            o2 = "Si";
        }
        o1 = "No";
        Object[] options = {o1, o2};
        if (JOptionPane.showOptionDialog(jf, texto1, texto2,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]) == 1) {
            System.exit(0);
        }
    }

}
