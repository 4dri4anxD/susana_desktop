/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import datos.temporalStorage;
import disenos.disenos;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import obtenerDatos.users;

public class crearOrdenes {

    //constructor
    public crearOrdenes() {

    }

    //Funciones random
    public void limpiarTabla(DefaultTableModel modelo) {
        int tam = modelo.getRowCount();
        for (int i = 0; i < tam; i++) {
            modelo.removeRow(0);
        }
    }

    public ArrayList<String> llenarBaraja(ArrayList<String> listaUsuarios, ArrayList<String> actividades) {
        ArrayList<String> seleccion = new ArrayList();
        ArrayList cartas1 = new ArrayList();
        int pos;
        int nCartas = listaUsuarios.size();
        int comp = (listaUsuarios.contains("dummy")) ? (nCartas - 1) : nCartas;
        for (int i = 0; i < actividades.size(); i++) {//nCartas
            pos = (int) Math.floor(Math.random() * nCartas);
            if (cartas1.size() == comp) {
                cartas1.clear();
            }
            boolean val = cartas1.contains(pos) || listaUsuarios.get(pos).equals("dummy");
            while (val) {
                pos = (int) Math.floor(Math.random() * nCartas);
                val = cartas1.contains(pos) || listaUsuarios.get(pos).equals("dummy");
            }

            seleccion.add(listaUsuarios.get(pos));
            // pCartas.add(pos);
            cartas1.add(pos);
        }
        return seleccion;
    }

    public ArrayList<String> ponerRv(ArrayList<String> usuariosSeleccionados, ArrayList<String> listaUsuarios, JTable tblActividades, DefaultTableModel modelo, ArrayList<String> actividades) {
        ArrayList seleccion = new ArrayList();
        if (usuariosSeleccionados.size() > 0) {
            seleccion.addAll(usuariosSeleccionados);
        } else {
            seleccion.addAll(llenarBaraja(listaUsuarios, actividades));
        }
        JComboBox cmb = new JComboBox();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            cmb.addItem(listaUsuarios.get(i));
        }
        new disenos().selector(cmb);
        System.out.println("Seleccion: " + seleccion);
        System.out.println("Actividades: " + actividades);
        tblActividades.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cmb));
        for (int i = 0; i < seleccion.size(); i++) {
            modelo.addRow(new Object[]{actividades.get(i), seleccion.get(i)});
        }
        return seleccion;
    }

    public ArrayList<String> rellenar(JComboBox cmb1, JComboBox cmb2, ArrayList<String> plantillas, boolean valido, ArrayList<String> listaUsuarios, int caso) {
        if (caso == 1) {
            cmb1.setEnabled(valido);
            for (String plan : plantillas) {
                cmb1.addItem(plan);//plantilla
            }
        }
        for (String us : listaUsuarios) {
            cmb2.addItem(us);//reposnable
        }
        return listaUsuarios;
    }

    //CallBacks
    public interface CallBackPlantillas {

        void onCallback(ArrayList<String> plantillas);
    }

    public interface CallBackActividades {

        void onCallback(Actividades c);
    }

    public interface CallBackUsuarios {

        void onCallback(ArrayList<String> users);
    }

    //Funciones de lectura de la base de datos
    public void readPlantillas(CallBackPlantillas call, DatabaseReference con, String plantilla, JComboBox cmb1, JComboBox cmb2, String responsable, String planSel) {
        try {
            System.out.println("Plantilla: " + plantilla);
            ArrayList<String> plantillas = new ArrayList<>();
            Query query = null;
            query = con.child(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            String plant = user.getKey();
                            plantillas.add(plant);
                            cmb2.addItem(plant);
                        }

                        cmb2.setSelectedIndex(0);
                        cmb1.setSelectedItem(responsable);
                        cmb2.setSelectedItem(planSel);
                        // hasPrevious = false;
                        call.onCallback(plantillas);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        } catch (Exception t) {
            // JOptionPane.showMessageDialog(context, "Error34: " + t);
        }
    }

    Actividades getActividades(ArrayList<String> actividades, ArrayList<Integer> requisitos) {
        return new Actividades(actividades, requisitos);
    }

    public void readActividades(CallBackActividades call, DatabaseReference con, String tabla, String plantilla, String campo1, String campo2) {
        try {
            ArrayList<String> actividades = new ArrayList();
            ArrayList<Integer> requisitos = new ArrayList();
            Query query = null;
            query = con.child(tabla).child(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot info : snapshot.getChildren()) {
                            HashMap json = (HashMap) info.getValue();
                            actividades.add(json.get(campo1).toString());
                            requisitos.add(Integer.parseInt(json.get(campo2).toString()));
                        }
                        call.onCallback(getActividades(actividades, requisitos));

                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        } catch (Exception e) {

        }
    }

    public void readUsuarios(CallBackUsuarios call, DatabaseReference con, temporalStorage storage, JComboBox cmb) {
        try {
            ArrayList<String> usuarios = new ArrayList();

            Query query = con.child("usuarios").orderByChild("user");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            users log = user.getValue(users.class);
                            int pr = log.getPriv();
                            if (pr >= 2 && pr <= 4) {
                                String u = log.getUser();
                                usuarios.add(u);
                                cmb.addItem(u);
                            }
                        }
                        storage.setListaUsuarios(usuarios);
                        cmb.setSelectedIndex(0);
                        call.onCallback(usuarios);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        } catch (Exception e) {
        }
    }

    //Clases
    public class Actividades {

        private ArrayList<String> actividades;
        private ArrayList<Integer> requisitos;

        public Actividades(ArrayList<String> actividades, ArrayList<Integer> requisitos) {

            this.actividades = actividades;
            this.requisitos = requisitos;
        }

        public ArrayList<String> getActividades() {
            return actividades;
        }

        public ArrayList<Integer> getRequisitos() {
            return requisitos;
        }
    }
}
