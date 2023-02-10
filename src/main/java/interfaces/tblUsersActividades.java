package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import datos.infoOrdenes;
import datos.leerJSON;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import obtenerDatos.ordenes;
import org.json.simple.JSONObject;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class tblUsersActividades extends configuracionVentana {//clase para poner la lista de usuarios trabajando en diferentes drones en la clase de menuActividades

    //declaracion de variables globales
    private String idioma;
    private infoOrdenes info;
    private DefaultTableModel modelo;
    private DatabaseReference con;
    private JFrame context;
    private leerJSON json;
    private LinkedHashMap<String, ArrayList<String>> user_drone;//contiene como key el nombre de usuario y de value la lista de drones en los que esta trabajando

    public tblUsersActividades(DatabaseReference con, String idioma) {//constructor
        initComponents();
        //poner icono
        //declaracion de variables globales
        this.idioma = idioma;
        this.con = con;
        user_drone = new LinkedHashMap();
        this.context = this;
        new disenoTabla().cabecera(tablaExtras);
        modelo = (DefaultTableModel) tablaExtras.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaExtras.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
       // tablaExtras.setDefaultRenderer(String.class, centerRenderer);
        leer();
    }

    private ArrayList<String> fillUsuario(JSONObject allData, String primera, String segunda, String tercera, ArrayList<String> usuarios) {
        try {
            HashMap check = (HashMap) allData.get(primera);
            ArrayList actividades = (ArrayList) check.get(segunda);
            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                String us = (String) jsonTC.get(tercera);
                if (!usuarios.contains(us) && !us.equals("")) {
                    usuarios.add(us);
                }
            }
            return usuarios;
        } catch (Exception e) {
            //   System.out.println("erroooooooooooooooooooooooooooor: "+e);
            return null;
        }
    }

    public void leer() {//lee la lista de usuarios y en que drones esta trabajando cada uno

        try {
            // HashMap<String, ArrayList<String>> hm = new HashMap<>();
            Query query = null;
            query = con.child(json.getString("trabajosTable")).orderByChild(json.getString("trabajosTableProgreso")).endAt(99);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ordenes log;

                        for (DataSnapshot info : snapshot.getChildren()) {
                            log = info.getValue(ordenes.class);
                            ArrayList<String> usuarios = new ArrayList<>();
                            String a = info.getKey() + " - " + log.getNombre();
                            Map<String, Object> value = (Map<String, Object>) info.getValue();
                            JSONObject allData = new JSONObject(value);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTC"), json.getString("trabajosTableCheckTCActividades"), json.getString("trabajosTableCheckTCActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTT"), json.getString("trabajosTableCheckTTActividades"), json.getString("trabajosTableCheckTTActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTT"), json.getString("trabajosTableCheckTTActividades"), json.getString("trabajosTableCheckTTActividadesUsu1"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTS"), json.getString("trabajosTableCheckTSActividades"), json.getString("trabajosTableCheckTSActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTF"), json.getString("trabajosTableCheckTFActividades"), json.getString("trabajosTableCheckTFActividadesUsu"), usuarios);
                            for (String usuario : usuarios) {
                                ArrayList<String> ordenes1 = new ArrayList<>();

                                if (!user_drone.containsKey(usuario)) {
                                    ordenes1.add(a);
                                    user_drone.put(usuario, ordenes1);
                                } else {
                                    ordenes1 = user_drone.get(usuario);
                                    ordenes1.add(a);
                                    user_drone.put(usuario, ordenes1);
                                }
                            }
                           
                        }

                        ArrayList<String> a = new ArrayList<String>(user_drone.keySet());
                        for (int i = 0; i < user_drone.size(); i++) {
                            //pone la info (nombre de los usuarios) en la tabla
                            modelo.addRow(new Object[]{a.get(i)});
                        }
                    } else {
                        //No hay trabajos agregados aun
                        //  new showToast(getString(R.string.noTrabajosSpn), getString(R.string.noTrabajosEng), idioma, getContext());
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //   new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + error, getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, getContext());

                }
            });
        } catch (Exception e) {
            // new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + e, getString(R.string.lblErrorWhileReadingDBEng) + e, idioma, getContext());
        }

     
    }

   
    public JPanel returnPanel() {
        return pnlCuerpo;
    }

    public LinkedHashMap returnLista() {
        return user_drone;
    }

    public DefaultTableModel returnModelo() {
        return modelo;
    }

    public JTable returnTabla() {
        return tablaExtras;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaExtras = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaExtras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombres"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaExtras);

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JTable tablaExtras;
    // End of variables declaration//GEN-END:variables
}
