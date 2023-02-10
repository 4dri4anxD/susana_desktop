/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class temporalStorage {
    private static String productoTS, cajaTS, paqueteTS, responsableTS, plantillaTS, fecha, nombre, plantillaTC, responsableTC, responsableTT, plantillaTT, pilotoTT, responsableTF, plantillaTF, mensajeTS;
    private static ArrayList<String> accesoriosTS, usuariosTS, ubicacionTC, usuariosTC, actividadesTT, usuariosTT, rendimientoTT, usuarios1TT, actividadesTF, usuariosTF, mensajeTC, comentarioTS, comentarioTF, ponderacionTT, comentarioTT, comentarioTT1, plantillas,listaUsuarios;
    private static ArrayList<Integer>  requisitosTC, requisitosTS, requisitosTT, requisitosTT1, requisitosTF, completadoTS, completadoTF;
    private static HashMap<String, ArrayList<String>> comentariosTC;
    private static ArrayList<Boolean> aprobadoTC, revsolTC, aprobacionTT, realizadoTT;
    private static int serie;
    private static boolean  airtagTS;
    private static double progreso, porcentajeTT, porcentajeTF, porcentajeTS, porcentajeTC;

    public temporalStorage() {

    }
    public void inicializarTodo(){
        inicializarBasico();
        inicializarTS();
        inicializarTT();
        inicializarTC();
        inicializarTF();
        plantillas=new ArrayList();
        listaUsuarios=new ArrayList();
    }

    public void inicializarBasico(){
        serie=0;
        fecha="";
        nombre="";
        progreso=0;
    }
    public void inicializarTS() {
        productoTS = "";
        cajaTS = "";
        paqueteTS = "";
        responsableTS = "";
        airtagTS = false;
        plantillaTS = "";
        accesoriosTS = new ArrayList<>();
        usuariosTS = new ArrayList<>();
        mensajeTS="";
        requisitosTS=new ArrayList<>();
        completadoTS=new ArrayList<>();
        comentarioTS=new ArrayList<>();
        porcentajeTS=0;
    }
    public void inicializarTC() {
        responsableTC = "";
        plantillaTC = "";
        ubicacionTC = new ArrayList<>();
        usuariosTC = new ArrayList<>();
        comentariosTC=new LinkedHashMap<>();
        requisitosTC=new ArrayList<>();
        revsolTC=new ArrayList<>();
        aprobadoTC=new ArrayList<>();
        mensajeTC=new ArrayList<>();
        porcentajeTC=0;
    }

    public void inicializarTT(){
       responsableTT="";
        plantillaTT="";
        pilotoTT="";
        actividadesTT=new ArrayList<>();
        usuariosTT=new ArrayList<>();
        usuarios1TT=new ArrayList<>();
        requisitosTT=new ArrayList<>();
        requisitosTT1=new ArrayList<>();
        rendimientoTT=new ArrayList<>();
        aprobacionTT=new ArrayList<>();
        realizadoTT=new ArrayList<>();
        ponderacionTT=new ArrayList<>();
        comentarioTT=new ArrayList<>();
        comentarioTT1=new ArrayList<>();
        porcentajeTT=0;
    }
    public void inicializarTF(){
        responsableTF="";
        plantillaTF="";
        actividadesTF=new ArrayList<>();
        usuariosTF=new ArrayList<>();
        requisitosTF=new ArrayList<>();
        completadoTF=new ArrayList<>();
        comentarioTF=new ArrayList<>();
        porcentajeTF=0;
    }
    
    public static ArrayList<String> getPlantillas() {
        return plantillas;
    }

    public static void setPlantillas(ArrayList<String> plantillas) {
        temporalStorage.plantillas = plantillas;
    }

    public static ArrayList<String> getListaUsuarios() {
        return listaUsuarios;
    }

    public static void setListaUsuarios(ArrayList<String> listaUsuarios) {
        temporalStorage.listaUsuarios= listaUsuarios;
    }
    
    public static ArrayList<String> getComentarioTT() {
        return comentarioTT;
    }

    public static void setComentarioTT(ArrayList<String> comentarioTT) {
        temporalStorage.comentarioTT = comentarioTT;
    }

    public static ArrayList<String> getComentarioTT1() {
        return comentarioTT1;
    }

    public static void setComentarioTT1(ArrayList<String> comentarioTT1) {
        temporalStorage.comentarioTT1 = comentarioTT1;
    }

    public static ArrayList<String> getPonderacionTT() {
        return ponderacionTT;
    }

    public static void setPonderacionTT(ArrayList<String> ponderacionTT) {
        temporalStorage.ponderacionTT = ponderacionTT;
    }

    public static ArrayList<Boolean> getAprobacionTT() {
        return aprobacionTT;
    }

    public static void setAprobacionTT(ArrayList<Boolean> aprobacionTT) {
        temporalStorage.aprobacionTT = aprobacionTT;
    }

    public static ArrayList<Boolean> getRealizadoTT() {
        return realizadoTT;
    }

    public static void setRealizadoTT(ArrayList<Boolean> realizadoTT) {
        temporalStorage.realizadoTT = realizadoTT;
    }

    public static ArrayList<String> getComentarioTF() {
        return comentarioTF;
    }

    public static void setComentarioTF(ArrayList<String> comentarioTF) {
        temporalStorage.comentarioTF = comentarioTF;
    }
    public static ArrayList<String> getComentarioTS() {
        return comentarioTS;
    }

    public static void setComentarioTS(ArrayList<String> comentarioTS) {
        temporalStorage.comentarioTS = comentarioTS;
    }

    public static ArrayList<String> getMensajeTC() {
        return mensajeTC;
    }

    public static void setMensajeTC(ArrayList<String> mensajeTC) {
        temporalStorage.mensajeTC = mensajeTC;
    }

    public static ArrayList<Boolean> getAprobadoTC() {
        return aprobadoTC;
    }

    public static void setAprobadoTC(ArrayList<Boolean> aprobadoTC) {
        temporalStorage.aprobadoTC = aprobadoTC;
    }

    public static ArrayList<Boolean> getRevsolTC() {
        return revsolTC;
    }

    public static void setRevsolTC(ArrayList<Boolean> revsolTC) {
        temporalStorage.revsolTC = revsolTC;
    }

    public static double getPorcentajeTT() {
        return porcentajeTT;
    }

    public static void setPorcentajeTT(double porcentajeTT) {
        temporalStorage.porcentajeTT = porcentajeTT;
    }

    public static double getPorcentajeTF() {
        return porcentajeTF;
    }

    public static void setPorcentajeTF(double porcentajeTF) {
        temporalStorage.porcentajeTF = porcentajeTF;
    }

    public static double getPorcentajeTS() {
        return porcentajeTS;
    }

    public static void setPorcentajeTS(double porcentajeTS) {
        temporalStorage.porcentajeTS = porcentajeTS;
    }

    public static double getPorcentajeTC() {
        return porcentajeTC;
    }

    public static void setPorcentajeTC(double porcentajeTC) {
        temporalStorage.porcentajeTC = porcentajeTC;
    }

    public static double getProgreso() {
        return progreso;
    }

    public static void setProgreso(double progreso) {
        temporalStorage.progreso = progreso;
    }

    public static ArrayList<Integer> getCompletadoTF() {
        return completadoTF;
    }

    public static void setCompletadoTF(ArrayList<Integer> completadoTF) {
        temporalStorage.completadoTF = completadoTF;
    }

    public static ArrayList<Integer> getCompletadoTS() {
        return completadoTS;
    }

    public static void setCompletadoTS(ArrayList<Integer> completadoTS) {
        temporalStorage.completadoTS = completadoTS;
    }


    public static ArrayList<Integer> getRequisitosTC() {
        return requisitosTC;
    }

    public static void setRequisitosTC(ArrayList<Integer> requisitosTC) {
        temporalStorage.requisitosTC = requisitosTC;
    }

    public static ArrayList<Integer> getRequisitosTS() {
        return requisitosTS;
    }

    public static void setRequisitosTS(ArrayList<Integer> requisitosTS) {
        temporalStorage.requisitosTS = requisitosTS;
    }

    public static ArrayList<Integer> getRequisitosTT() {
        return requisitosTT;
    }

    public static void setRequisitosTT(ArrayList<Integer> requisitosTT) {
        temporalStorage.requisitosTT = requisitosTT;
    }

    public static ArrayList<Integer> getRequisitosTT1() {
        return requisitosTT1;
    }

    public static void setRequisitosTT1(ArrayList<Integer> requisitosTT1) {
        temporalStorage.requisitosTT1 = requisitosTT1;
    }

    public static ArrayList<Integer> getRequisitosTF() {
        return requisitosTF;
    }

    public static void setRequisitosTF(ArrayList<Integer> requisitosTF) {
        temporalStorage.requisitosTF = requisitosTF;
    }

    public static String getMensajeTS() {
        return mensajeTS;
    }

    public static void setMensajeTS(String mensajeTS) {
        temporalStorage.mensajeTS = mensajeTS;
    }

    public static String getResponsableTF() {
        return responsableTF;
    }

    public static void setResponsableTF(String responsableTF) {
        temporalStorage.responsableTF = responsableTF;
    }

    public static String getPlantillaTF() {
        return plantillaTF;
    }

    public static void setPlantillaTF(String plantillaTF) {
        temporalStorage.plantillaTF = plantillaTF;
    }

    public static ArrayList<String> getActividadesTF() {
        return actividadesTF;
    }

    public static void setActividadesTF(ArrayList<String> actividadesTF) {
        temporalStorage.actividadesTF = actividadesTF;
    }

    public static ArrayList<String> getUsuariosTF() {
        return usuariosTF;
    }

    public static void setUsuariosTF(ArrayList<String> usuariosTF) {
        temporalStorage.usuariosTF = usuariosTF;
    }


    public static String getResponsableTT() {
        return responsableTT;
    }

    public static void setResponsableTT(String responsableTT) {
        temporalStorage.responsableTT = responsableTT;
    }

    public static String getPlantillaTT() {
        return plantillaTT;
    }

    public static void setPlantillaTT(String plantillaTT) {
        temporalStorage.plantillaTT = plantillaTT;
    }

    public static String getPilotoTT() {
        return pilotoTT;
    }

    public static void setPilotoTT(String pilotoTT) {
        temporalStorage.pilotoTT = pilotoTT;
    }

    public static ArrayList<String> getUsuariosTT() {
        return usuariosTT;
    }

    public static void setUsuariosTT(ArrayList<String> usuariosTT) {
        temporalStorage.usuariosTT = usuariosTT;
    }

    public static ArrayList<String> getActividadesTT() {
        return actividadesTT;
    }

    public static void setActividadesTT(ArrayList<String> actividadesTT) {
        temporalStorage.actividadesTT = actividadesTT;
    }

    public static ArrayList<String> getRendimientoTT() {
        return rendimientoTT;
    }

    public static void setRendimientoTT(ArrayList<String> rendimientoTT) {
        temporalStorage.rendimientoTT = rendimientoTT;
    }

    public static ArrayList<String> getUsuarios1TT() {
        return usuarios1TT;
    }

    public static void setUsuarios1TT(ArrayList<String> usuarios1TT) {
        temporalStorage.usuarios1TT = usuarios1TT;
    }

    public static HashMap<String, ArrayList<String>> getComentariosTC() {
        return comentariosTC;
    }

    public static void setComentariosTC(HashMap<String, ArrayList<String>> comentariosTC) {
        temporalStorage.comentariosTC = comentariosTC;
    }


    public static ArrayList<String> getUbicacionTC() {
        return ubicacionTC;
    }

    public static void setUbicacionTC(ArrayList<String> ubicacionTC) {
        temporalStorage.ubicacionTC = ubicacionTC;
    }


    public static ArrayList<String> getUsuariosTC() {
        return usuariosTC;
    }

    public static void setUsuariosTC(ArrayList<String> usuariosTC) {
        temporalStorage.usuariosTC = usuariosTC;
    }

    public static String getPlantillaTC() {
        return plantillaTC;
    }

    public static void setPlantillaTC(String plantillaTC) {
        temporalStorage.plantillaTC = plantillaTC;
    }

    public static String getResponsableTC() {
        return responsableTC;
    }

    public static void setResponsableTC(String responsableTC) {
        temporalStorage.responsableTC = responsableTC;
    }


    public String getProductoTS() {
        return productoTS;
    }

    public void setProductoTS(String productoTS) {
        this.productoTS = productoTS;
    }

    public String getCaseTS() {
        return cajaTS;
    }

    public void setCaseTS(String caseTS) {
        this.cajaTS = caseTS;
    }

    public String getPaqueteTS() {
        return paqueteTS;
    }

    public void setPaqueteTS(String paqueteTS) {
        this.paqueteTS = paqueteTS;
    }

    public String getResponsableTS() {
        return responsableTS;
    }

    public void setResponsableTS(String responsableTS) {
        this.responsableTS = responsableTS;
    }

    public String getPlantillaTS() {
        return plantillaTS;
    }

    public void setPlantillaTS(String plantillaTS) {
        this.plantillaTS = plantillaTS;
    }

    public ArrayList<String> getAccesoriosTS() {
        return accesoriosTS;
    }

    public void setAccesoriosTS(ArrayList<String> accesoriosTS) {
        this.accesoriosTS = accesoriosTS;
    }

    public ArrayList<String> getUsuariosTS() {
        return usuariosTS;
    }

    public void setUsuariosTS(ArrayList<String> usuariosTS) {
        this.usuariosTS = usuariosTS;
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

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public boolean isAirtagTS() {
        return airtagTS;
    }

    public void setAirtagTS(boolean airtagTS) {
        this.airtagTS = airtagTS;
    }
}
