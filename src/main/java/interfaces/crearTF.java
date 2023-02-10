/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import datos.temporalStorage;
import disenos.centerTextInTable;
import disenos.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import obtenerDatos.plantillasFinal;
import obtenerDatos.users;

public class crearTF extends configuracionVentana {

    private String idioma, responsableTF, plantillaTF, user;
    private DefaultTableModel modelo;
    private temporalStorage storage;
    private boolean hasPrevious;
    private ArrayList<String> actividades, listaUsuarios, seleccion;
    private ArrayList<Integer> requisitos;
    private DatabaseReference con;
    private int serie, priv;
    private JFrame context;
    private boolean valido, cargado;

    public crearTF(DatabaseReference con, String user, int priv, String idioma, int serie, boolean valido) {
        initComponents();
        cargado = false;

        ImageIcon imagen = new ImageIcon(info.RUTA_IMAGEN);
        Image icono = imagen.getImage();
        this.setIconImage(icono);
        storage = new temporalStorage();
        hasPrevious = false;

        context = this;
        this.valido = valido;
        this.serie = serie;
        this.con = con;
        this.user = user;
        this.idioma = idioma;
        this.setTitle(info.VERSION);
        modelo = (DefaultTableModel) tblActividades.getModel();
        this.responsableTF = storage.getResponsableTF();
        this.plantillaTF = storage.getPlantillaTF();

        actividades = new ArrayList<>();
        requisitos = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        seleccion = new ArrayList();

        try {

            actividades.addAll(storage.getActividadesTF());
            requisitos.addAll(storage.getRequisitosTF());
            seleccion.addAll(storage.getUsuariosTF());
            cmbPlantilla.setEnabled(valido);
            if (actividades.size() > 0) {
                rellenar();
                ponerRv();
                hasPrevious = true;
            } else {
                System.out.println("Aca xd");
                if (storage.getListaUsuarios().size() > 0) {
                    rellenar();
                    getActividades(cmbPlantilla.getSelectedItem().toString());
                }else{
                    leerUsuarios();
                }
            }

        } catch (Exception e) {
            System.out.println("erorrrrrrrrrrrrrr: " + e);
        }

        if (idioma.equals("English")) {
            ingles();
        } else {
            esp();
        }
        //se le pone el cell renderer solo a las columnas 1 y 2
        tblActividades.setDefaultRenderer(Object.class, new centerTextInTable());

        iniciarDiseno();
    }

    public void iniciarDiseno() {//decorar los componentes del frame
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlArriba, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().fondoLabel(lblSerie, 1);
        new disenos().titulo(lblSerie, 5);
        new disenos().titulo(lblResponsable, 6);
        new disenos().titulo(lblPlantilla, 6);
        new disenos().selector(cmbResponsable);
        new disenos().selector(cmbPlantilla);
        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnAdd, "img/check1.png");
        ponerImg(btnAtras, "img/atras2.png");

        new disenoTabla().cabecera(tblActividades);
    }

    public void ponerImg(JButton b, String ruta) {//poner imagenes a los botones
        ImageIcon imagen = new ImageIcon(ruta);
        Image imgEscalada = imagen.getImage().getScaledInstance(b.getWidth(),
                b.getHeight(), Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);
    }

    private void rellenar() {
        cmbPlantilla.setEnabled(valido);
        hasPrevious = true;

        listaUsuarios.addAll(storage.getListaUsuarios());
        for (String plan : storage.getPlantillas()) {
            cmbPlantilla.addItem(plan);
        }
        for (String us : listaUsuarios) {
            cmbResponsable.addItem(us);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlFondo = new javax.swing.JPanel();
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActividades = new javax.swing.JTable();
        pnlArriba = new javax.swing.JPanel();
        lblSerie = new javax.swing.JLabel();
        lblResponsable = new javax.swing.JLabel();
        lblPlantilla = new javax.swing.JLabel();
        cmbResponsable = new javax.swing.JComboBox<>();
        cmbPlantilla = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("CheckList Final");

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

        pnlDer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        tblActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Actividad", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblActividadesMouseClicked(evt);
            }
        });
        tblActividades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblActividadesKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblActividades);

        pnlArriba.setLayout(new java.awt.GridBagLayout());

        lblSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSerie.setText("No establecido aun");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        pnlArriba.add(lblSerie, gridBagConstraints);

        lblResponsable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResponsable.setText("Responsable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblResponsable, gridBagConstraints);

        lblPlantilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlantilla.setText("Plantilla");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblPlantilla, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbResponsable, gridBagConstraints);

        cmbPlantilla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPlantillaItemStateChanged(evt);
            }
        });
        cmbPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPlantillaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbPlantilla, gridBagConstraints);

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
            .addComponent(pnlArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCuerpoLayout.createSequentialGroup()
                .addComponent(pnlArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        try {
            this.setCursor(new Cursor(WAIT_CURSOR));
            for (int i = 0; i < modelo.getRowCount(); i++) {
                seleccion.set(i, modelo.getValueAt(i, 1).toString());
            }
            storage.setPlantillaTF(cmbPlantilla.getSelectedItem().toString());
            storage.setResponsableTF(cmbResponsable.getSelectedItem().toString());
            storage.setUsuariosTF(seleccion);
            storage.setActividadesTF(actividades);
            storage.setRequisitosTF(requisitos);
            //volver
            new info().setXY(this.getX(), this.getY());
            new vistaActividades(con, user, priv, idioma, serie, 2).setVisible(true);
            this.dispose();
        } catch (Exception e) {
            this.setCursor(new Cursor(DEFAULT_CURSOR));
        }
        //actualizar seleccion
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        //   new info().setXY(this.getX(), this.getY());
        // this.setCursor(new Cursor(WAIT_CURSOR));
        //  this.dispose();
        new info().setXY(this.getX(), this.getY());
        new vistaActividades(con, user, priv, idioma, serie, 2).setVisible(true);
        this.setCursor(new Cursor(WAIT_CURSOR));
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tblActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblActividadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblActividadesMouseClicked

    private void tblActividadesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblActividadesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblActividadesKeyTyped

    private void cmbPlantillaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPlantillaItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbPlantillaItemStateChanged

    private void cmbPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPlantillaActionPerformed
        // TODO add your handling code here:
        if (cargado) {
            // System.out.println("Aquasai");
            if (!hasPrevious) {
                limpiarTabla();
                getActividades(cmbPlantilla.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_cmbPlantillaActionPerformed

    private void limpiarTabla() {
        int tam = modelo.getRowCount();
        for (int i = 0; i < tam; i++) {
            modelo.removeRow(0);
        }
        actividades.clear();
        requisitos.clear();
        // listaUsuarios.clear();
        seleccion.clear();
    }

    private void getPlantillas() {

        try {
              ArrayList<String> plantillas = new ArrayList<>();
            Query query = null;
            query = con.child("plantillasFinal");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                             plantillas.add(user.getKey());
                            cmbPlantilla.addItem(user.getKey());
                        }
                        storage.setPlantillas(plantillas);
                        cmbPlantilla.setSelectedIndex(0);
                        //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.light_background_item, plantillas);
                        //  spnPlantillaTF.setAdapter(adapter);
                        // adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.light_background_item, listaUsuarios);
                        // spnResponsableF.setAdapter(adapter);
                        if (hasPrevious) {
                            cmbResponsable.setSelectedItem(storage.getResponsableTF());
                            cmbPlantilla.setSelectedItem(storage.getPlantillaTF());
                            //ver como arreglar listaUsuarios
                            //Si se agrega un nuevo usuario, se va a desmadrar todo no?
                            //O al menos no lo va a reconocer

                            hasPrevious = false;
                        }
                        getActividades(cmbPlantilla.getSelectedItem().toString());

                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }

            });
        } catch (Exception e) {

        }
    }

    private void ponerRv() {
        plantillaTF = cmbPlantilla.getSelectedItem().toString();
        responsableTF = cmbResponsable.getSelectedItem().toString();

        if (storage.getUsuariosTF().size() > 0) {
            seleccion = storage.getUsuariosTF();
        } else {
            //  System.out.println("ACA NOOOOOOOOOOOOOOOOOOOOOOOO");
            llenarBaraja();
        }
        JComboBox cmb = new JComboBox();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            cmb.addItem(listaUsuarios.get(i));
        }
        new disenos().selector(cmb);
        //agrega el como box a las filas

        tblActividades.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cmb));
        for (int i = 0; i < seleccion.size(); i++) {
            modelo.addRow(new Object[]{actividades.get(i), seleccion.get(i)});
        }
        cargado = true;
    }

    private void llenarBaraja() {
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
    }

    private void getActividades(String plantilla) {
        try {
            actividades.clear();
            requisitos.clear();
            Query query = null;
            query = con.child("plantillasFinal").child(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot info : snapshot.getChildren()) {
                            plantillasFinal datos = info.getValue(plantillasFinal.class);
                            actividades.add(datos.getActividad());
                            requisitos.add(datos.getRequisito());
                        }
                        ponerRv();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        } catch (Exception e) {

        }
    }

    private void leerUsuarios() {
       // if (listaUsuarios.size() == 0) {//!hasPrevious && !loadFlag
            Query query = con.child("usuarios").orderByChild("user");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            users log = user.getValue(users.class);
                            try {
                                int pr = log.getPriv();
                                if (pr >= 2 && pr <= 4) {
                                    listaUsuarios.add(log.getUser());
                                    cmbResponsable.addItem(log.getUser());
                                }
                            } catch (Exception e) {

                            }
                        }
                        storage.setListaUsuarios(listaUsuarios);
                        cmbResponsable.setSelectedIndex(0);
                        getPlantillas();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
       // } else {
        //    getPlantillas();
       // }
    }

    private void ingles() {//poner la interfaz en ingles
        lblTitulo.setText("CheckList");
        JTableHeader tableHeader = tblActividades.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Activities");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("User");
        tableHeader.repaint();
        lblResponsable.setText("Responsible");
        lblPlantilla.setText("Template");
       // leerUsuarios();
    }

    private void esp() {//poner la interfaz en espanol
        lblTitulo.setText("CheckList");
        JTableHeader tableHeader = tblActividades.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Actividades");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("Usuario");
        tableHeader.repaint();
        lblResponsable.setText("Responsable");
        lblPlantilla.setText("Plantilla");
      //  leerUsuarios();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JComboBox<String> cmbPlantilla;
    private javax.swing.JComboBox<String> cmbResponsable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPlantilla;
    private javax.swing.JLabel lblResponsable;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlArriba;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tblActividades;
    // End of variables declaration//GEN-END:variables
}
