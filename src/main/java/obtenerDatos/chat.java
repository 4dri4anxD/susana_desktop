package obtenerDatos;

public class chat {//clase para CRUD sobre el nodo chat en la base de datos

    private String fecha, hora, mensaje, remitente;
    private int tipo, grupo;

    public chat() {
        //Es obligatorio incluir constructor por defecto
    }

    public chat(String fecha, int grupo, String hora, String mensaje, String remitente, int tipo) {
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.hora = hora;
        this.remitente = remitente;
        this.tipo = tipo;
        this.grupo = grupo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String valor) {
        this.fecha = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int valor) {
        this.tipo = valor;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int valor) {
        this.grupo = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String valor) {
        this.hora = valor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String msj) {
        this.mensaje = msj;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String rem) {
        this.remitente = rem;
    }

}
