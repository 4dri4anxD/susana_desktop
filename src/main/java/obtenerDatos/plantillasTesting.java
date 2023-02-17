
package obtenerDatos;

public class plantillasTesting {
    private String actividad, accion;
    private int requisito, requisito1;

    public plantillasTesting() {
        //Es obligatorio incluir constructor por defecto
    }

    public plantillasTesting(String ubicacion, int requisito, String accion, int requisito1) {
        this.actividad= ubicacion;
        this.requisito=requisito;
        this.accion=accion;
        this.requisito1=requisito1;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String ubicacion) {
        this.actividad= ubicacion;
    }

    public int getRequisito() {
        return requisito;
    }

    public void setRequisito(int requisito) {
        this.requisito= requisito;
    }

    public int getRequisito1() {
        return requisito1;
    }

    public void setRequisito1(int requisito) {
        this.requisito1= requisito;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String ubicacion) {
        this.accion= ubicacion;
    }


}

