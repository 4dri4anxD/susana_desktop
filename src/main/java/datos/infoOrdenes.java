package datos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class infoOrdenes {//clase para almacenar temporalmente informacion para mostrarla en las clases de ordenes

    //datos: contiene key del 1 al 24 con un value contenido en la bd como direccion de envio, fecha en la que se ordeno, etc
    //users contiene todos los usuarios tipo vendedores
    //versiones contiene key con el codigo(o nombre) de una plantilla y el value es una lista delas distintas versiones de esa plantilla
    //extras contiene key con el codigo(o nombre) de una plantilla y el value es una lista de los distintos extras de esa plantilla
    //extra sel contiene los extras seleccionados para una determinada orden
    
    private static LinkedHashMap<String, String> datos, users;
    private static LinkedHashMap<String, List<String>> versiones, extras;
    private static ArrayList<String> extraSel, plantillas;

    public infoOrdenes() {

    }

    public void inicializarListas() {
        datos = new LinkedHashMap();
        extraSel = new ArrayList();
        plantillas = new ArrayList();
        versiones = new LinkedHashMap();
        extras = new LinkedHashMap();
        users = new LinkedHashMap();
    }

    public void setDatos(String key, String value) {
        datos.put(key, value);
    }

    public LinkedHashMap<String, String> getDatos() {
        return datos;
    }

    public void setUsers(String key, String value) {
        users.put(key, value);
    }

    public LinkedHashMap<String, String> getUsers() {
        return users;
    }

    public void setextraSel(String info) {
        extraSel.add(info);
    }

    public ArrayList getextraSel() {
        return extraSel;
    }

    public void setPlantillas(String info) {

        plantillas.add(info);
        System.out.println("siiiuuu");
    }

    public ArrayList<String> getPlantillas() {
        return plantillas;
    }

    public void setVersiones(String key, ArrayList value) {
        versiones.put(key, value);
    }

    public LinkedHashMap<String, List<String>> getVersiones() {
        return versiones;
    }

    public void setExtras(String key, ArrayList value) {
        extras.put(key, value);
    }

    public LinkedHashMap<String, List<String>> getExtras() {
        return extras;
    }

}
