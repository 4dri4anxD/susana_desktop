/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class checkAC {//Actividades del child checkTC
    private  String ubicacion, usuario, mensaje;
    private int requisito;
    private boolean revsol, aprobado;


    public checkAC() {
        //Es obligatorio incluir constructor por defecto
    }

    public checkAC(String actividad, String usuario, boolean revsol, boolean aprobado, int requisito, String mensaje)
    {
        this.ubicacion=actividad;
        this.usuario=usuario;
        this.requisito=requisito;
        this.revsol=revsol;
        this.aprobado=aprobado;
        this.mensaje=mensaje;
      //  this.com=com;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String actividad) {
        this.ubicacion = actividad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public int getRequisito() {
        return requisito;
    }

    public void setRequisito(int requisito) {
        this.requisito = requisito;
    }

    public boolean isRevsol() {
        return revsol;
    }

    public void setRevsol(boolean revsol) {
        this.revsol = revsol;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

