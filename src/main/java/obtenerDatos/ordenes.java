
package obtenerDatos;


public class ordenes {
    private String fecha, nombre;
    private double progreso;

    public ordenes() {
        //Es obligatorio incluir constructor por defecto
    }

    public ordenes(String fecha, String nombre, double progreso)
    {
        this.nombre= nombre;
        this.fecha=fecha;
        this.progreso=progreso;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getProgreso() {
        return progreso;
    }

    public void setProgreso(double progreso) {
        this.progreso = progreso;
    }

}
