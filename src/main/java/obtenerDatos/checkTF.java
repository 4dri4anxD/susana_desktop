/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class checkTF {//Child de final en la tabla trabajos
    private  String responsable, plantilla;
    private double porcentaje;

    public checkTF() {
        //Es obligatorio incluir constructor por defecto
    }

    public checkTF(String responsable, String plantilla, double porcentaje)
    {
        this.responsable=responsable;
        this.plantilla=plantilla;
        this.porcentaje=porcentaje;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
