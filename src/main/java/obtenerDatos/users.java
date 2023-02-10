package obtenerDatos;

public class users {//clase para CRUD sobre el nodo users en la base de datos

    private String pass;
    private int priv;
    private String user;

    public users() {
        //Es obligatorio incluir constructor por defecto
    }

    public users(String pass, int priv, String user) {
        this.pass = pass;
        this.priv = priv;
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPriv() {
        return priv;
    }

    public void setPriv(int priv) {
        this.priv = priv;
    }

    @Override
    public String toString() {
        return pass + " " + priv + " " + user;
    }

}
