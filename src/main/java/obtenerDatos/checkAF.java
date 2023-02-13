/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class checkAF {//Actividades del child checkTF
    private  String actividad, usuario, comentario;
    private int completado, requisito;


    public checkAF() {
        //Es obligatorio incluir constructor por defecto
    }

    public checkAF(String actividad, String usuario, int completado, int requisito, String comentario)
    {
        this.actividad =actividad;
        this.usuario=usuario;
        this.completado=completado;
        this.requisito=requisito;
        this.comentario=comentario;
        //  this.com=com;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCompletado() {
        return completado;
    }

    public void setCompletado(int completado) {
        this.completado = completado;
    }

    public int getRequisito() {
        return requisito;
    }

    public void setRequisito(int requisito) {
        this.requisito = requisito;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
