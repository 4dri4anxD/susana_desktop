/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obtenerDatos;

public class plantillasShipment {
    private String accesorio;
    private int requisito;

    public plantillasShipment() {
        //Es obligatorio incluir constructor por defecto
    }

    public plantillasShipment(String ubicacion, int requisito) {
        this.accesorio= ubicacion;
        this.requisito=requisito;
    }

    public String getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(String ubicacion) {
        this.accesorio= ubicacion;
    }

    public int getRequisito() {
        return requisito;
    }

    public void setRequisito(int requisito) {
        this.requisito= requisito;
    }


}