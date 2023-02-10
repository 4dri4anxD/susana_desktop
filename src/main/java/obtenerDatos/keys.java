package obtenerDatos;

public class keys {//clase para CRUD sobre el nodo keys en la base de datos

    private String key, usuario;

    public keys() {
        //Es obligatorio incluir constructor por defecto
    }

    public keys(String key, String usuario) {
        this.key = key;
        this.usuario = usuario;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String valor) {
        this.key = valor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String user) {
        this.usuario = user;
    }

    @Override
    public String toString() {
        return usuario;
    }

}
