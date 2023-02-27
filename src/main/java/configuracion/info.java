package configuracion;

public class info {//clase que solo contieen variables con informacion que muchas partes de la aplicacion requieren

    public static final String RUTA_IMAGEN = "img/image.png";//ruta de la imagen del icono
    public static final String VERSION = "SUSANA DESKTOP V-1.0.1";//version de la aplicacion
    public static int x, width;//valor en x donde se encuentra el frame
    public static int y, height;//valor en y donde se encuentra el frame
    public static String pass = "";

    public info() {//constructor
    }

    public void setXY(int x, int y, int w, int h) {//establece los valores de x,y donde aparecera el frame

        if (x != -1 && y != -1) {
            this.x = x;
            this.y = y;
        }
        if (x != 0 && y != 0) {
            this.height = h;
            this.width = w;
        }

    }

    public int getX() {//retorna el valor de x
        return x;
    }

    public int getY() {//retorna el valor de y
        return y;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
