package datos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class datos {//clase para contener variables estaticas con info utilizada por la interfaz de plantillas para almacenar la info temporalmente para mostrarla

    private static LinkedHashMap<String, List<String>> procesosPlantilla;//procesos vinculados a una plantilla
    private static ArrayList<String> versiones, extras;//versiones y extras relacionados a una plantilla

    public static void setVersiones(String value) {
        versiones.add(value);
    }

    public static ArrayList<String> getVersiones() {
        return versiones;
    }

    public static ArrayList<String> getExtras() {
        return extras;
    }

    public static void setExtras(String value) {
        extras.add(value);
    }

    public static LinkedHashMap<String, List<String>> getProcesosPlantilla() {
        return procesosPlantilla;
    }

    public static void setProcesosPlantilla(String key, List<String> value) {
        procesosPlantilla.put(key, value);
    }

    public void inicializar() {
        procesosPlantilla = new LinkedHashMap();
        extras = new ArrayList();
        versiones = new ArrayList();
    }

    public datos() {//constructor

    }

}
