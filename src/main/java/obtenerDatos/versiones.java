package obtenerDatos;

public class versiones {//clase para CRUD sobre el nodo versiones en la base de datos

    private String tipo, plantilla;

    public versiones() {
        //Es obligatorio incluir constructor por defecto
    }

    public versiones(String plantilla, String tipo) {
        this.plantilla = plantilla;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

}
