
package obtenerDatos;

public class plantillasFinal {
    private String actividad;
    private int requisito;

    public plantillasFinal() {
        //Es obligatorio incluir constructor por defecto
    }

    public plantillasFinal(String actividad, int requisito) {
        this.actividad = actividad;
        this.requisito = requisito;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getRequisito() {
        return requisito;
    }

    public void setRequisito(int requisito) {
        this.requisito = requisito;
    }
}
