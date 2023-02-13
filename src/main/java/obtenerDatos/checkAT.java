/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class checkAT {//Actividades del child checkTT
    private String actividad, usuario, rendimiento, usuario1, ponderacion, comentario, comentario1;
    private int requisito, requisito1;
    private boolean realizado, aprobacion;

    public checkAT() {
        //Es obligatorio incluir constructor por defecto
    }
    public checkAT(String actividad, String usuario, boolean realizado, boolean aprobacion, int requisito, String comentario, String rendimiento, String usuario1, String ponderacion, int requisito1, String comentario1) {
        this.actividad= actividad;
        this.requisito=requisito;
        this.rendimiento=rendimiento;
        this.requisito1=requisito1;
        this.usuario=usuario;
        this.usuario1=usuario1;
        this.aprobacion=aprobacion;
        this.realizado=realizado;
        this.ponderacion=ponderacion;
        this.comentario=comentario;
        this.comentario1=comentario1;
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

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    public int getRequisito1() {
        return requisito1;
    }

    public void setRequisito1(int requisito1) {
        this.requisito1 = requisito1;
    }

    public int getRequisito() {
        return requisito;
    }

    public void setRequisito(int requisito) {
        this.requisito = requisito;
    }

    public boolean isAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(boolean aprobacion) {
        this.aprobacion = aprobacion;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public String getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(String ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario1() {
        return comentario1;
    }

    public void setComentario1(String comentario1) {
        this.comentario1 = comentario1;
    }
}
