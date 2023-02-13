/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class checkTT {//Child de testing en la tabla trabajos
    private  String responsable, plantilla, piloto;
    private double porcentaje;

    public checkTT() {
        //Es obligatorio incluir constructor por defecto
    }

    public checkTT(String responsable, String plantilla, String piloto, double porcentaje)
    {
        this.responsable=responsable;
        this.plantilla=plantilla;
        this.piloto=piloto;
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

    public String getPiloto() {
        return piloto;
    }
    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
