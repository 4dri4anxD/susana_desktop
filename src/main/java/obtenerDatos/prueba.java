package obtenerDatos;

import java.util.List;

public class prueba {//clase para CRUD sobre el nodo prueba en la base de datos

    private String usuario;
    private List foto;

    public prueba() {
        //Es obligatorio incluir constructor por defecto
    }

    public prueba(List foto, String usuario) {
        this.foto = foto;
        this.usuario = usuario;
    }

    public List getKey() {
        return foto;
    }

    public void setKey(List valor) {
        this.foto = valor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String user) {
        this.usuario = user;
    }

}
