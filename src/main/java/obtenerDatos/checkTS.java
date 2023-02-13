/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class checkTS {//Child de shipment en la tabla trabajos
    private  String responsable, plantilla, paquete, caja, producto, mensaje;
    private boolean airtag;
    private double porcentaje;

    public checkTS() {
        //Es obligatorio incluir constructor por defecto
    }

    public checkTS(String responsable, String plantilla, String paquete, String caja, String producto, String mensaje, boolean airtag, double porcentaje)
    {
        this.responsable=responsable;
        this.plantilla=plantilla;
        this.paquete=paquete;
        this.caja=caja;
        this.producto=producto;
        this.mensaje=mensaje;
        this.airtag=airtag;
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

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isAirtag() {
        return airtag;
    }

    public void setAirtag(boolean airtag) {
        this.airtag = airtag;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}

