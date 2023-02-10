package obtenerDatos;

public class ultimomsj {//clase para CRUD sobre el nodo ultimomsj en la base de datos

    private String fecha, mensaje, hora, remitente, destinatario, chat;
    private int visto, tipo, grupo;
    private long timest;

    public ultimomsj() {
        //Es obligatorio incluir constructor por defecto
    }

    public ultimomsj(String chat, String destinatario, String fecha, int grupo, String hora, String mensaje, String remitente, long timest, int tipo, int visto) {
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.hora = hora;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.visto = visto;
        this.chat = chat;
        this.tipo = tipo;
        this.grupo = grupo;
        this.timest = timest;
    }

    public long getTimest() {
        return timest;
    }

    public void setTimest(long val) {
        this.timest = val;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String valor) {
        this.fecha = valor;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String valor) {
        this.chat = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String valor) {
        this.hora = valor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String valor) {
        this.destinatario = valor;
    }

    public int getVisto() {
        return visto;
    }

    public void setVisto(int valor) {
        this.visto = valor;
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
