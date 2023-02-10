package obtenerDatos;

public class grupos {//clase para CRUD sobre el nodo grupos en la base de datos

    private String nombre;

    public grupos() {
        //Es obligatorio incluir constructor por defecto
    }

    public grupos(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String valor) {
        this.nombre = valor;
    }

}
