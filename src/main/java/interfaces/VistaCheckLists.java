package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import disenos.colorTabla;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class VistaCheckLists extends JFrame {

    private DatabaseReference con;
    private String user, idioma;
    private int priv;
    private int tipo, serie;
    private String plantilla;
    private final int SECONDS_IN_ONE_DAY = 86_400;
    private JFrame context;
    private ArrayList<String> codProceso, procesos, progresos, pro, claves, actividades;
    private ArrayList<Integer> division;
    private LinkedHashMap<String, List<String>> hm;
    private DefaultTableModel modelo;
    private String codPlantilla;
    private int seleccion;
    private LinkedHashMap<String, String> permisos, bitProceso, permitido;
    private LinkedHashMap<String, Integer> estados;
    private LinkedHashMap<Integer, String> codigoProceso;
    private LinkedHashMap<String, Long> fin, inicio, segundos;
    private LinkedHashMap<String, List<String>> procesosRequeridos;
    private List<String> act;
    private HashMap<String, String> completados;
    
    //completados es lo que se lee de la bd

    public VistaCheckLists(DatabaseReference con, String user, int priv, String idioma, int tipo, int serie, String plantilla) {
        initComponents();
        new configuracionVentana(this);


        //inicializacion de variables
        this.con = con;
        this.user = user;
        this.priv = priv;
        this.idioma = idioma;
        this.tipo = tipo;
        completados = new HashMap();
        fin = new LinkedHashMap();
        codigoProceso = new LinkedHashMap();
        claves = new ArrayList();
        inicio = new LinkedHashMap();
        actividades = new ArrayList();
        segundos = new LinkedHashMap();
        seleccion = -1;
        estados = new LinkedHashMap();
        division = new ArrayList();
        act = new ArrayList();
        this.serie = serie;
        this.plantilla = plantilla;
        codProceso = new ArrayList();
        procesos = new ArrayList();
        pro = new ArrayList();
        modelo = (DefaultTableModel) tablaPermisos.getModel();
        hm = new LinkedHashMap();
        progresos = new ArrayList();
        bitProceso = new LinkedHashMap();
        //codPro = new LinkedHashMap();
        permitido = new LinkedHashMap();
        permisos = new LinkedHashMap();
        procesosRequeridos = new LinkedHashMap();
        context = this;
        iniciarDiseno();

        
        if (tipo == 0) {//si se esta revisando el trabajo de todos
            String t[] = {"Proceso", "Progreso"};
            modelo = new DefaultTableModel(null, t) {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;//no debe poderse editar la tabla
                }
            };
            tablaPermisos.setModel(modelo);
            btnAdd.setVisible(false);//ni modificar nada
            TableColumnModel columnModel = tablaPermisos.getColumnModel();

            columnModel.getColumn(0).setPreferredWidth(400);
            columnModel.getColumn(1).setPreferredWidth(10);
            leerTodo();

            //se centra el texto en la tabla
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            modelo = (DefaultTableModel) tablaPermisos.getModel();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < modelo.getColumnCount(); i++) {
                tablaPermisos.getColumnModel().getColumn(i).setCellRenderer(tcr);
            }
        } else {//si se esta accediendo apra trabajar
            codPlantilla();
        }
        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }
    }

    public void iniciarDiseno() {
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        lblPlantilla.setHorizontalAlignment(JLabel.LEFT);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnVerAct, 3);
        new disenos().botones(btnAtras, 3);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);
        new disenos().fondo(panelArriba, 1);

        new disenos().titulo(lblTitulo, 2);

        new disenos().titulo(lblPlantilla, 5);

        ponerImg(btnAdd, "img/guardar1.png");
        ponerImg(btnVerAct, "img/ojo.png");
        ponerImg(btnAtras, "img/atras2.png");

        new disenoTabla().cabecera(tablaPermisos);
    }

    public void ponerImg(JButton b, String ruta) {
        ImageIcon imagen = new ImageIcon(ruta);
        Image imgEscalada = imagen.getImage().getScaledInstance(b.getWidth(),
                b.getHeight(), Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnVerAct = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPermisos = new javax.swing.JTable();
        panelArriba = new javax.swing.JPanel();
        lblPlantilla = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("Completar checklist calidad");

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnVerAct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnVerAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerAct, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlIzqLayout = new javax.swing.GroupLayout(pnlIzq);
        pnlIzq.setLayout(pnlIzqLayout);
        pnlIzqLayout.setHorizontalGroup(
            pnlIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIzqLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnlIzqLayout.setVerticalGroup(
            pnlIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIzqLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tablaPermisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Procesos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPermisos);

        lblPlantilla.setText("93496-Skywalker");

        javax.swing.GroupLayout panelArribaLayout = new javax.swing.GroupLayout(panelArriba);
        panelArriba.setLayout(panelArribaLayout);
        panelArribaLayout.setHorizontalGroup(
            panelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArribaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPlantilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelArribaLayout.setVerticalGroup(
            panelArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelArribaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addComponent(panelArriba, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuerpoLayout.createSequentialGroup()
                .addComponent(panelArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlIzq, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new MenuCompletarOrden(con, user, priv, idioma, tipo).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        update();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnVerActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActActionPerformed
        // TODO add your handling code here:
        if (tipo == 1) {//si se esta trabajando
            if (permitido.get(modelo.getValueAt(tablaPermisos.getSelectedRow(), 0)).equals("1")) {//si permite la modificacion del estado de las actividades (pq aun no esta completado el proceso)
                List<String> a = hm.get(modelo.getValueAt(tablaPermisos.getSelectedRow(), 0));//hm contiene procesos y actividades
                LinkedHashMap<String, String> b = new LinkedHashMap();
                LinkedHashMap<String, Integer> c = new LinkedHashMap();
                for (int i = 0; i < a.size(); i++) {//se recorren las actividades del proceso seleccionado
                    if (completados.containsKey(a.get(i))) {//si las actividades estan completadas
                        b.put(a.get(i), completados.get(a.get(i)));
                        c.put(a.get(i), estados.get(a.get(i)));
                    }
                }
              //  new listaActividades(idioma, b, c, this, tipo, tablaPermisos.getSelectedRow()).setVisible(true);// hm, codProceso, procesos
            }
        } else {//si se esta visualizando el progreso de todo
            List<String> a = hm.get(modelo.getValueAt(tablaPermisos.getSelectedRow(), 0));
            LinkedHashMap<String, String> b = new LinkedHashMap();
            LinkedHashMap<String, Integer> c = new LinkedHashMap();
            for (int i = 0; i < a.size(); i++) {
                if (completados.containsKey(a.get(i))) {
                    b.put(a.get(i), completados.get(a.get(i)));
                    c.put(a.get(i), estados.get(a.get(i)));
                }
            }
          //  new listaActividades(idioma, b, c, this, tipo, tablaPermisos.getSelectedRow()).setVisible(true);
        }
    }//GEN-LAST:event_btnVerActActionPerformed

    private interface RolCallback6 {
        void rolRecibido();
    }

    private void recuperaRol6(RolCallback6 callback) throws InterruptedException { //lee variables de proUS
        try {
            Query query = con.child("proUS").orderByChild("serie").equalTo(serie);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                      //  proUS trab;
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                             //   trab = user.getValue(proUS.class);
                            //    codProceso.add(trab.getProceso());
                            //    procesos.add(trab.getNombre());
                            //    division.add(trab.getDivision());
                             //   progresos.add("" + trab.getProgreso());
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error34: " + es);
                                break;
                            }
                        }
                        if (callback != null) {
                            callback.rolRecibido();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error34: " + error);
                }
            });

        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error34: " + t);
        }
    }

    private void leerTodo() {//lee todo (cuando se esta supervisando el progreso de todo)
        try {
            recuperaRol6(new RolCallback6() {//lee variables de proUS
                @Override
                public void rolRecibido() {
                    try {
                        recuperaRol1(new RolCallback1() {//recupera el codigo de plantilla
                            @Override
                            public void rolRecibido(String codP) {
                                try {
                                    recuperaRol3(new RolCallback3() {//lee las actividades relacionadas a la plantilla
                                        @Override
                                        public void rolRecibido(ArrayList l) {
                                            try {
                                                Query query = con.child("actUS").orderByChild("serie").equalTo(serie);
                                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot snapshot) {
                                                        if (snapshot.exists()) {
                                                         //   actUS log;
                                                            for (DataSnapshot user : snapshot.getChildren()) {
                                                                try {
                                                                  //  log = user.getValue(actUS.class);
                                                                    for (int i = 0; i < codProceso.size(); i++) {//recorre los procesos
                                                                      //  estados.put(log.getActividad(), log.getEstado());
                                                                      //  fin.put(log.getActividad(), log.getFin());
                                                                      //  inicio.put(log.getActividad(), log.getInicio());
                                                                      //  segundos.put(log.getActividad(), log.getSegundos());
                                                                       // if (codProceso.get(i).equals(log.getProceso())) {//si el proceso coincide con el de la actividad
                                                                        //    completados.put(log.getActividad(), "" + log.getCompleto());
                                                                            if (hm.get(procesos.get(i)) != null) {
                                                                                act = hm.get(procesos.get(i));
                                                                             //   act.add(log.getActividad());
                                                                                hm.put("" + procesos.get(i), act);
                                                                            } else {
                                                                                act = new ArrayList();
                                                                             //   act.add(log.getActividad());
                                                                                hm.put("" + procesos.get(i), act);
                                                                            }
                                                                      //  }
                                                                    }
                                                                } catch (Exception es) {
                                                                    System.out.println("EX1: " + es);
                                                                }
                                                            }
                                                            for (int i = 0; i < procesos.size(); i++) {
                                                                modelo.addRow(new Object[]{procesos.get(i), (int)Double.parseDouble(progresos.get(i)) + "%"});
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError error) {
                                                        JOptionPane.showMessageDialog(context, "Error34: " + error);
                                                    }
                                                });

                                            } catch (Exception t) {
                                                JOptionPane.showMessageDialog(context, "Error34: " + t);
                                            }
                                        }
                                    }, codProceso);

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(context, "Error34: " + e);
                                }
                            }
                        });
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(context, "Error34: " + e);
                    }
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error34: " + e);
        }
    }

    private void update() {//actualizar (cuando se completa algo)
        try {
            int i = 0;
            int k = 0;
            for (Map.Entry<String, List<String>> entry : hm.entrySet()) {//se recorren los procesos
                ArrayList a = new ArrayList();
                a.addAll(entry.getValue());
                for (int j = 0; j < a.size(); j++) {//se recorren las actividades
                    int est = 0;
                    try {
                        est = Integer.parseInt(completados.get(a.get(j)));
                    } catch (NumberFormatException e) {

                    }
                    Map<String, Object> userss = new HashMap<>();
                    userss.put("actividad", actividades.get(k));
                    userss.put("completo", est);
                    userss.put("estado", estados.get(actividades.get(k)));
                    userss.put("fin", fin.get(actividades.get(k)));
                    userss.put("inicio", inicio.get(actividades.get(k)));
                    userss.put("proceso", codProceso.get(i));
                    userss.put("segundos", segundos.get(actividades.get(k)));
                    userss.put("serie", serie);
                    userss.put("usuario", user);
                    con.child("actUS").child(claves.get(k)).updateChildren(userss, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError de, DatabaseReference dr) {
                        }
                    });
                    k++;
                }
                i++;
            }
            obtenCompletos();
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error34: " + t);
        }
    }

    private void obtenCompletos() {//obtiene procesos ya completados
        try {
            Query query = con.child("proUS").orderByChild("serie").equalTo(serie);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ArrayList completos = new ArrayList();
                   //     proUS trab;
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                             //   trab = user.getValue(proUS.class);
                             //   if (trab.getUsuario().equals(user)) {
                              //      if (trab.getProgreso() == 100) {
                               //         completos.add(trab.getProceso());
                                //    }
                               // }
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error35: " + es);
                                break;
                            }
                        }
                        updateProgreso(completos);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error36: " + error);
                }
            });
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error37: " + t);
        }
    }

    private void updateProgreso(ArrayList completos) {//actualiza proUS
        try {
            Query query = con.child("proUS").orderByChild("serie").equalTo(serie);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                    //    proUS trab;
                        String co = "";
                        for (DataSnapshot user1 : snapshot.getChildren()) {
                            try {
                             //   trab = user1.getValue(proUS.class);
                                for (int i = 0; i < codProceso.size(); i++) {//recorre la lista de procesos
                                //    if (trab.getProceso().toLowerCase().equals(codProceso.get(i).toLowerCase()) && trab.getUsuario().equals(user)) {//si el proceso y el usuario coinciden con el de la db
                                        double tam = 0;
                                        try {
                                            tam = hm.get(procesos.get(i).toString()).size();//cantidad de actividades por proceso
                                        } catch (Exception e) {
                                            System.out.println("EXKDK");
                                        }
                                        double porc = 0;
                                        double porcentaje = 0;
                                        ArrayList a = new ArrayList();
                                        porc = 100.0 / tam; //porcentaje equivalente de cada actividad
                                        try {
                                            a.addAll(hm.get(procesos.get(i).toString()));//se agregan las actividades a a
                                        } catch (Exception e) {
                                            System.out.println("Errororr");
                                        }
                                        for (int j = 0; j < a.size(); j++) {//se recorren las actividades
                                            try {
                                                if (completados.get(a.get(j)).equals("1")) {//si la actividad ya estaba completada
                                                    porcentaje = porcentaje + porc;//se le suma el porcentaje de la act, al porcentaje total del proceso
                                                }
                                            } catch (NullPointerException e) {
                                            }
                                        }
                                        if (porcentaje >= 99.7) {//proceso completado
                                            porcentaje = 100;
                                        }
                                        //actualizar proUS
                                        Map<String, Object> userss = new HashMap<>();
                                        if (!co.equals("")) {//nunca va a entrar aca, esto era pa las imagenes, pero desde la app desktop no se pueden subir
                                            String path = "fotosProcesos/" + serie + "_" + co + ".jpg";
                                            userss.put("division", division.get(i));
                                            userss.put("proceso", codProceso.get(i).toString());
                                            userss.put("progreso", porcentaje);
                                            userss.put("nombre", procesos.get(i).toString());
                                            userss.put("serie", serie);
                                            userss.put("usuario", user);
                                            userss.put("path", path);
                                        } else {
                                            userss.put("division", division.get(i));
                                            userss.put("proceso", codProceso.get(i).toString());
                                            userss.put("progreso", porcentaje);
                                            userss.put("nombre", procesos.get(i).toString());
                                            userss.put("serie", serie);
                                            userss.put("usuario", user);
                                            userss.put("path", "");
                                        }
                                        con.child("proUS").child(user1.getKey()).setValue(userss, new DatabaseReference.CompletionListener() {
                                            @Override
                                            public void onComplete(DatabaseError de, DatabaseReference dr) {

                                            }
                                        });
                                  //  }
                                }
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error38: " + es);
                                break;
                            }
                        }
                        getCodigoDron(completos);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error
                ) {
                    JOptionPane.showMessageDialog(context, "Error40: " + error);
                }
            }
            );
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error41: " + t);
        }
    }

    private void getCodigoDron(ArrayList completos) {//obtiene el codigo de la plantilla
        try {
            Query query = con.child("plantillas").orderByChild("nombre").equalTo(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String codi = "";
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                                codi = user.getKey();
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error42: " + es);
                                break;
                            }
                        }
                        obtenerPesoTotal(codi, completos);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error43: " + error);
                }
            });

        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error44: " + t);
        }
    }

    private void obtenerPesoTotal(String codi, ArrayList completos) {//pbtiene el peso de cada proceso
        try {
            Query query = con.child("procesos").child(codi);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        HashMap<String, String> proPe = new HashMap<String, String>();
                        float total = 0;
                       // procesos trab;
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                              //  trab = user.getValue(procesos.class);
                               // total = trab.getPeso() + total;
                                for (int i = 0; i < codProceso.size(); i++) {
                                    if (user.getKey().toLowerCase().equals(codProceso.get(i).toString().toLowerCase())) {
                                 //       proPe.put(codProceso.get(i).toString(), "" + trab.getPeso());
                                    }
                                }
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error45: " + es);
                                break;
                            }
                        }
                        obtenerProcesosCompletos(total, proPe, completos);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error46: " + error);
                }
            });

        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error47: " + t);

        }

    }

    private void obtenerProcesosCompletos(float total, HashMap<String, String> proPe, ArrayList compeltos) {//actualiza el progreso del trabajo y actualiza trabajos
        double porcentaje = 0.0;//se inicia en 0
        int valor = -1;//para que sea menor que 0
        int t = 0;
        int tot = hm.get(procesos.get(seleccion).toString()).size();//total de actividades por proceso
        for (int i = 0; i < tot; i++) {
            if (completados.get(hm.get(procesos.get(seleccion).toString()).get(i)).equals("1")) {
                t++;
            }
        }
        if (t == tot) {//si todas las actividades de un mismo proceso han sido completadas
            valor = procesos.indexOf(procesos.get(seleccion).toString());//se obtiene el index del proceso que acaba de ser completado
        }
        if (valor >= 0) {//si se completo el proceso que se modifico
            double prob = Double.parseDouble(proPe.get(codProceso.get(valor).toString())) / total;//se calcula el porcentaje que modifica el proceso sobre el total del trabajo
            double proba = prob;
            if (division.get(valor) > 1) {//si el proceso esta dividido
                proba = prob / division.get(valor);//se divide el porcentaje entre la cantidad de divisiones
            }
            double p = proba * 100;//se multiplica por 100 pa que sea porcentaje
            porcentaje = p;
        }
        final double por = porcentaje;
        try {
            Query query = con.child("trabajos").orderByChild("serie").equalTo(serie);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                      //  trabajos trab;
                        String key = "";
                        String fechaE = "";
                        String hora = "";
                        double porcentaje1 = 0;
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                              //  trab = user.getValue(trabajos.class);
                                key = user.getKey();
                             //   porcentaje1 = trab.getProgreso();
                             //   fechaE = trab.getFechaE();
                              //  hora = trab.getHora();
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error48: " + es);
                                break;
                            }
                        }

                        double progreso = por + porcentaje1;//se suma el porcentaje que tenia el trabajo al del proceso que se aab de completar
                        if (progreso >= 99.8) {
                            progreso = 100;
                        }
                        //se actualiza trabajos
                      //  trabajos act = new trabajos(fechaE, hora, plantilla, progreso, serie);//plan
                        con.child("trabajos").child(key).setValue(act, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError de, DatabaseReference dr) {

                            }
                        });
                        if (idioma.equals("English")) {
                            JOptionPane.showMessageDialog(context, "Information updated");
                        } else {
                            JOptionPane.showMessageDialog(context, "Informacion actualizada");
                        }
                        new MenuCompletarOrden(con, user, priv, idioma, tipo).setVisible(true);
                        context.dispose();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error30: " + error);
                }
            });
        } catch (Exception d) {
            JOptionPane.showMessageDialog(context, "Error31: " + d);
        }
    }

    private interface RolCallback {
        void rolRecibido();
    }

    private interface RolCallback1 {
        void rolRecibido(String rol);
    }

    private interface RolCallback2 {
        void rolRecibido(ArrayList rol, ArrayList proba);
    }

    private interface RolCallback3 {
        void rolRecibido(ArrayList rol);
    }

    private void recuperaRol(RolCallback callback) throws InterruptedException {//lee variables de proUS   
        try {
            Query query = con.child("proUS").orderByChild("serie").equalTo(serie);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                      //  proUS trab;
                        int i = 0;
                        for (DataSnapshot user1 : snapshot.getChildren()) {
                            try {
                            //    trab = user1.getValue(proUS.class);
                              /*  if (trab.getUsuario().equals(user)) {
                                    codProceso.add(trab.getProceso());
                                    procesos.add(trab.getNombre());
                                    division.add(trab.getDivision());
                                    progresos.add("" + trab.getProgreso());
                                    permitido.put(trab.getNombre(), "1");
                                }
                                codigoProceso.put(Integer.parseInt(trab.getProceso()), trab.getNombre());
                                if (!bitProceso.containsValue(trab.getProceso())) {
                                    bitProceso.put("" + Math.pow(2, i), trab.getProceso());//se le asigna un valor binario en decimal a cada proceso (mascara de bits),es un identificador unico
                                    i++;
                                }*/
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error1: " + es);
                                break;
                            }

                        }
                        comprobarSecuencia(callback);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error2: " + error);
                }
            });

        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error3: " + t);
        }

    }

    private void codPlantilla() {//lee el codigo de la plantilla
        try {
            Query query = con.child("plantillas").orderByChild("nombre").equalTo(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                                codPlantilla = user.getKey();
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error4: " + es);
                                break;
                            }
                        }
                        leer1();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error5: " + error);
                }
            });
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error6: " + t);
        }
    }

    private void comprobarSecuencia(RolCallback callback) {//comprueba que procesos pueden ser completados y cuales no, dependiendo de los requisitos de cada uno
        for (int i = 0; i < codProceso.size(); i++) {//se recorren los procesos
            final int x = i;
            try {
                Query query = con.child("procesos").child(codPlantilla).orderByChild("requisito");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                          //  procesos trab;
                            for (DataSnapshot user : snapshot.getChildren()) {
                                try {
                                   // trab = user.getValue(procesos.class);
                                    if (user.getKey().equals(codProceso.get(x))) {//si el codigo de los procesos coinciden
                                        ArrayList<String> g = new ArrayList();
                                     //   String b = Integer.toBinaryString(trab.getRequisito());//el requisito se convierte a binario
                                     //   permisos.put(user.getKey(), "" + trab.getRequisito());//se relaciona el codigo de un proceso con su requisito
                                       // for (int k = 0; k < b.length(); k++) {//se recorre el numero binario
                                           // if (b.charAt(k) == '1') {//si es un 1 el bit que se esta recorriendo
                                              //  double a = Math.pow(2, (b.length() - 1) - k);//se convierte de binario a decimal, pero a cada numero se le resta un bit, creo
                                             //   g.add(bitProceso.get("" + a));//agrega el proceso que tiene bitProceso en a
                                          //  }
                                       // }
                                       // procesosRequeridos.put(trab.getProceso(), g);//se relacionan los procesos requeridos con los demas procesos
                                    }

                                } catch (Exception es) {
                                    JOptionPane.showMessageDialog(context, "Error7: " + es);
                                    break;
                                }
                            }
                            if (x == codProceso.size() - 1) {
                                verificarProgresos(callback);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        JOptionPane.showMessageDialog(context, "Error8: " + error);
                    }
                });
            } catch (Exception t) {
                JOptionPane.showMessageDialog(context, "Error9: " + t);
            }
        }
    }

    private void verificarProgresos(RolCallback callback) {//se invalida la modificacion de procesos que ya esten completados al 100
        try {
            Query query = con.child("proUS").orderByChild("serie").equalTo(serie);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                      //  proUS trab;
                        Set<String> keys = procesosRequeridos.keySet();
                        ArrayList<String> d = new ArrayList();//d es para registrar las filas que no se pueden editar, para pintarlas de gris
                        int k = 0;
                        for (DataSnapshot user1 : snapshot.getChildren()) {
                            try {
                              //  trab = user1.getValue(proUS.class);
                             //   if (trab.getProgreso() < 100) {//si el proceso no ha sido completado
                                    int i = 0;
                                    for (String key : keys) {//se recorren los procesos requeridos
                                      //  if (procesosRequeridos.get(key).contains(trab.getProceso())) {//si se tiene el proceso como requerido de algun otro
                                            permitido.put(key, "2");//se inhabilita su edicion
                                            d.add("" + i);
                                     //   }
                                        i++;
                                    }
                                 //   if (trab.getUsuario().equals(user)) {
                                        k++;
                                  //  }
                                //} else if (trab.getProgreso() == 100 && trab.getUsuario().equals(user)) {//si el proceso ya esta completado
                                //    permitido.put(trab.getNombre(), "2");//si ya ha sido completado un proceso, se inhabilita su edicion
                                    d.add("" + k);
                                    k++;

                               // }
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error10: " + es);
                                break;
                            }
                        }
                        //se pinta de color gris las filas que no se puedan modificar
                        tablaPermisos.setDefaultRenderer(Object.class, new colorTabla(1, d));
                        for (int i = 0; i < procesos.size(); i++) {
                            modelo.addRow(new Object[]{procesos.get(i)});

                        }
                        if (callback != null) {
                            callback.rolRecibido();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error11: " + error);
                }
            });
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error12: " + t);
        }
    }

    private void recuperaRol3(RolCallback3 callback, ArrayList codigos) throws InterruptedException {//lee las actividades relacionadas a la plantilla
        try {
            Query query = con.child("plantillas").orderByChild("nombre").equalTo(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                                codPlantilla = user.getKey();
                            } catch (Exception es) {

                            }
                        }
                        for (int i = 0; i < codigos.size(); i++) {
                            final int x = i;
                            try {
                                Query query = con.child("actividades").child(codPlantilla).child(codigos.get(i).toString());
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            ArrayList a = new ArrayList();
                                            for (DataSnapshot user : snapshot.getChildren()) {
                                                try {
                                                    a.add(user.getKey());
                                                } catch (Exception es) {
                                                    JOptionPane.showMessageDialog(context, "Error19: " + es);
                                                    break;
                                                }
                                            }
                                            if (x == codigos.size() - 1) {
                                                if (callback != null) {
                                                    callback.rolRecibido(a);
                                                }
                                            }
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError error) {
                                        JOptionPane.showMessageDialog(context, "Error20: " + error);
                                    }
                                });
                            } catch (Exception t) {
                                JOptionPane.showMessageDialog(context, "Error21: " + t);
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error43: " + error);
                }
            });
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error44: " + t);
        }
    }

    private void recuperaRol2(RolCallback2 callback, String codP, ArrayList cod) throws InterruptedException {//lee procesos
        try {
            Query query = con.child("procesos").child(codP);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                     //   procesos trab;
                        ArrayList a = new ArrayList();
                        ArrayList b = new ArrayList();
                        for (DataSnapshot user : snapshot.getChildren()) {
                            boolean x = false;
                            try {
                             //   trab = user.getValue(procesos.class);
                                for (int j = 0; j < cod.size(); j++) {
                                    if (user.getKey().equals(cod.get(j).toString())) {
                                        x = true;
                                    }
                                }
                                if (x) {
                                 //   a.add(trab.getProceso());
                                 //   pro.add(trab.getProceso());
                                  //  b.add(trab.getProba());
                                }

                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error22: " + es);
                                break;
                            }
                        }
                        if (callback != null) {
                            callback.rolRecibido(a, b);
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error23: " + error);
                }
            });
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error24: " + t);
        }
    }

    private void recuperaRol1(RolCallback1 callback) throws InterruptedException {//recupera el codigo de plantilla
        try {
            Query query = con.child("plantillas").orderByChild("nombre").equalTo(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String a = "";
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                                a = user.getKey();
                            } catch (Exception es) {
                                break;
                            }
                        }
                        if (callback != null) {
                            callback.rolRecibido(a);
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error25: " + error);
                }
            });
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error26: " + t);
        }
    }

    private void leer1() {//leer cuando se entra a trabajar
        try {
            recuperaRol1(new RolCallback1() {//recupera el codigo de plantilla
                @Override
                public void rolRecibido(String codP) {
                    try {
                        recuperaRol(new RolCallback() {//lee variables de proUS 
                            @Override
                            public void rolRecibido() {
                                try {
                                    recuperaRol2(new RolCallback2() {//lee procesos
                                        @Override
                                        public void rolRecibido(ArrayList procesos1, ArrayList probabilidadF) {
                                            try {
                                                recuperaRol3(new RolCallback3() {//lee las actividades relacionadas a la plantilla
                                                    @Override
                                                    public void rolRecibido(ArrayList act1) {
                                                        try {
                                                            Query query = con.child("actUS").orderByChild("serie").equalTo(serie);
                                                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                     //   actUS log;
                                                                        for (DataSnapshot user1 : snapshot.getChildren()) {
                                                                            try {
                                                                               // log = user1.getValue(actUS.class);
                                                                               /*
                                                                                if (log.getUsuario().equals(user)) {
                                                                                    for (int i = 0; i < codProceso.size(); i++) {//recorre los procesos
                                                                                        if (codProceso.get(i).equals(log.getProceso())) {//si coinciden
                                                                                            //llena todas estas listas
                                                                                            actividades.add(log.getActividad());
                                                                                            estados.put(log.getActividad(), log.getEstado());
                                                                                            claves.add(user1.getKey());
                                                                                            fin.put(log.getActividad(), log.getFin());
                                                                                            inicio.put(log.getActividad(), log.getInicio());
                                                                                            segundos.put(log.getActividad(), log.getSegundos());
                                                                                            completados.put(log.getActividad(), "" + log.getCompleto());
                                                                                            if (hm.get(procesos.get(i)) != null) {
                                                                                                act = hm.get(procesos.get(i));
                                                                                                act.add(log.getActividad());
                                                                                                hm.put("" + procesos.get(i), act);
                                                                                            } else {
                                                                                                act = new ArrayList();
                                                                                                act.add(log.getActividad());
                                                                                                hm.put("" + procesos.get(i), act);
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }*/
                                                                            } catch (Exception es) {
                                                                                System.out.println("EX: " + es);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                @Override
                                                                public void onCancelled(DatabaseError error) {
                                                                    JOptionPane.showMessageDialog(context, "Error1: " + error);
                                                                }
                                                            });
                                                        } catch (Exception t) {
                                                            JOptionPane.showMessageDialog(context, "Error2: " + t);
                                                        }
                                                    }
                                                }, codProceso);
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(context, "Error3: " + e);
                                            }
                                        }
                                    }, codP, codProceso);
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(context, "Error4: " + e);
                                }
                            }
                        });
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(context, "Error5: " + e);
                    }
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error6: " + e);
        }
    }

    public void actualizarComp(LinkedHashMap<String, Integer> a, int seleccion) {
        this.seleccion = seleccion;
        Long stamp = System.currentTimeMillis();
        for (Map.Entry<String, Integer> entry : a.entrySet()) {
            if (entry.getValue() == 1 && estados.get(entry.getKey()) == 0) {//si cambia de nada a inicio
                inicio.put(entry.getKey(), stamp);
            }
            if (entry.getValue() == 3 && estados.get(entry.getKey()) == 0) {//si cambia de nada a inicio
                inicio.put(entry.getKey(), stamp);
                fin.put(entry.getKey(), stamp);
            }
            if (entry.getValue() == 2 && estados.get(entry.getKey()) == 1) {//si cambia de inicio a pausa
                java.util.Date now = null;
                java.util.Date date = null;
                try {
                    now = new Date(stamp);
                    date = new Date(inicio.get(entry.getKey()));
                    long start = date.getTime() / 1000;
                    long end = now.getTime() / 1000;
                    if (end < start) {
                        end += SECONDS_IN_ONE_DAY;
                    }
                    long difference = (long) (end - start);
                    long s = difference + segundos.get(entry.getKey());
                    inicio.put(entry.getKey(), Long.parseLong("0"));
                    segundos.put(entry.getKey(), s);
                } catch (Exception e) {
                    System.out.println("ERRROR: " + e);
                }
            }
            if (entry.getValue() == 3 && estados.get(entry.getKey()) == 1) {//si cambia de inicio a fin
                java.util.Date now = null;
                java.util.Date date = null;
                try {
                    now = new Date(stamp);
                    date = new Date(inicio.get(entry.getKey()));
                    long start = date.getTime() / 1000;
                    long end = now.getTime() / 1000;
                    if (end < start) {
                        end += SECONDS_IN_ONE_DAY;
                    }
                    long difference = (long) (end - start);
                    long s = difference + segundos.get(entry.getKey());
                    fin.put(entry.getKey(), stamp);
                    segundos.put(entry.getKey(), s);
                } catch (Exception e) {
                    System.out.println("ERROR1: " + e);
                }
            }
            if (entry.getValue() == 3 && estados.get(entry.getKey()) == 2) {//si cambia de inicio a fin
                java.util.Date now = null;
                java.util.Date date = null;
                try {
                    now = new Date(stamp);
                    date = new Date(inicio.get(entry.getKey()));
                    long start = date.getTime() / 1000;
                    long end = now.getTime() / 1000;
                    if (end < start) {
                        end += SECONDS_IN_ONE_DAY;
                    }
                    long difference = (long) (end - start);
                    long s = difference + segundos.get(entry.getKey());
                    fin.put(entry.getKey(), stamp);
                    segundos.put(entry.getKey(), s);
                } catch (Exception e) {
                    System.out.println("Error2: " + e);
                }
            }
            if (entry.getValue() == 1 && estados.get(entry.getKey()) == 2) {//si cambia de pausa a inicio
                try {
                    inicio.put(entry.getKey(), stamp);
                } catch (Exception e) {
                    System.out.println("Error4: " + e);
                }
            }
            estados.put(entry.getKey(), entry.getValue());
            if (entry.getValue() == 3) {
                completados.put(entry.getKey(), "1");
            } else {
                completados.put(entry.getKey(), "0");
            }
        }
    }

    private void ingles() {
        lblTitulo.setText("Activities view");
        lblPlantilla.setText("" + serie + " - " + plantilla);
        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Process");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("Progress");

        tableHeader.repaint();
    }

    private void esp() {
        lblPlantilla.setText("" + serie + " - " + plantilla);
        lblTitulo.setText("Vista de Actividades");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnVerAct;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPlantilla;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelArriba;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tablaPermisos;
    // End of variables declaration//GEN-END:variables
}
