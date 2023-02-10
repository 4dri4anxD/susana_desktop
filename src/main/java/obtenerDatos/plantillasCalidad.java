/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class plantillasCalidad {
    private String ubicacion;
    private int requisito;

    public plantillasCalidad() {
        //Es obligatorio incluir constructor por defecto
    }

    public plantillasCalidad(String ubicacion, int requisito) {
        this.ubicacion= ubicacion;
        this.requisito=requisito;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion= ubicacion;
    }

    public int getRequisito() {
        return requisito;
    }

    public void setRequisito(int requisito) {
        this.requisito= requisito;
    }


}
