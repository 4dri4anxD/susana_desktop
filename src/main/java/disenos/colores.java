package disenos;

import java.awt.Color;

public class colores {//clase con variables con distintos colores utilizados por la aplicacion

    public final static Color gris = new Color(38, 38, 38),
            azul = new Color(18, 82, 175),
            blanco = new Color(255, 255, 255),
            gris2 = new Color(25, 25, 25),
            negro = new Color(0, 0, 0),
            grisSel = new Color(80, 80, 80),
            grisSel1 = new Color(120, 120, 120),
            focus = new Color(146, 189, 252),
            grisBlanco = new Color(223, 223, 223);


    public static Color getGris() {
        return gris;
    }

    public static Color getGris2() {
        return gris2;
    }

    public static Color getGrisSel() {
        return grisSel;
    }

    public static Color getAzul() {
        return azul;
    }

    public static Color getBlanco() {
        return blanco;
    }

    public static Color getNegro() {
        return negro;
    }
    
    public static Color getGrisBlanco(){
        return grisBlanco;
    }

}
