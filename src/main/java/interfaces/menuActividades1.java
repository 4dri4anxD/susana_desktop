package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import disenos.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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

public class menuActividades1 extends configuracionVentana {//clase para los trabajadores para que vean todas sus actividades pendientes
    //o para que algun admin o supervisor vea como va el progreso de algun dron

    private DatabaseReference con;
    private int priv;
    private String user, idioma;
    private int tipo;//0 es que un supervisor o admin va a ver el progreso del trabajo de algun dron
    private JFrame context;
    private DefaultTableModel modelo;
    private ArrayList<String> codPlantillas;

    public menuActividades1(DatabaseReference con, String user, int priv, String idioma, int tipo) {//constructor
        initComponents();

        //poner icono
        ImageIcon imagen = new ImageIcon(info.RUTA_IMAGEN);
        Image icono = imagen.getImage();
        this.setIconImage(icono);
        //poner titulo
        this.setTitle(info.VERSION);
        //inicializacion de variables
        this.con = con;
        this.priv = priv;
        this.user = user;
        this.idioma = idioma;
        this.tipo = tipo;
        codPlantillas = new ArrayList();
        modelo = (DefaultTableModel) tablaUsers.getModel();
        context = this;

        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }
        if (tipo == 0) {//se esta supervisando el trabajo de los drones
            leerTodo();
        } else {//un trabajador esta visualizando sus actividades
            leer();
        }
        iniciarDiseno();
    }

    public void iniciarDiseno() {//decorar los componentes del frame
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        //centrar el texto en las celdas de la tabla
        modelo = (DefaultTableModel) tablaUsers.getModel();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            tablaUsers.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }

        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        txtBuscar.setHorizontalAlignment(JLabel.CENTER);

        new disenos().botones(btnAtras, 3);
        new disenos().botones(btnBuscar, 1);
        new disenos().botones(btnAdd, 3);

        new disenos().textoL(txtBuscar);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnAtras, "img/atras2.png");
        ponerImg(btnBuscar, "img/buscar1.png");
        ponerImg(btnAdd, "img/edit1.png");

        tablaUsers.requestFocus();
        txtBuscar.setForeground(Color.LIGHT_GRAY);

        new disenoTabla().cabecera(tablaUsers);

    }

    public void ponerImg(JButton b, String ruta) {//ponerle imagenes a los botones
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
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("Menu usuarios");

        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

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

        tablaUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N. Serie", "Dron", "Progreso", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaUsers);
        if (tablaUsers.getColumnModel().getColumnCount() > 0) {
            tablaUsers.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaUsers.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaUsers.getColumnModel().getColumn(2).setPreferredWidth(30);
            tablaUsers.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
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
                    .addComponent(pnlDer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        // TODO add your handling code here:
        if (txtBuscar.getText().equals("Buscar") || txtBuscar.getText().equals("Search")) {
            txtBuscar.setText("");
            txtBuscar.setForeground(Color.BLACK);
            txtBuscar.setHorizontalAlignment(JLabel.LEFT);
        }
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        // TODO add your handling code here:
        if (txtBuscar.getText().equals("")) {
            if (idioma.equals("English")) {
                txtBuscar.setText("Search");
            } else {
                txtBuscar.setText("Buscar");
            }
            txtBuscar.setHorizontalAlignment(JLabel.CENTER);
            txtBuscar.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtBuscarFocusLost

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPrincipal(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            new info().setXY(this.getX(), this.getY());
            //leer serie de la tabla
            int serie = Integer.parseInt(modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString());
            String plantilla = modelo.getValueAt(tablaUsers.getSelectedRow(), 1).toString();
            new vistaActividades1(con, user, priv, idioma, tipo, serie, plantilla).setVisible(true);
            this.dispose();
        } catch (NullPointerException e) {
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Select a drone to see its details");
            } else {
                JOptionPane.showMessageDialog(context, "Selecciona un dron para ver sus detalles");
            }

        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            if (!txtBuscar.getText().equals("")) {
                buscar();
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    //se utilizan callbacks porque firebase retorna la info asincronamente
    private interface RolCallback1 {

        void rolRecibido(final ArrayList rol);
    }

    private void recuperaRol1(RolCallback1 callback) throws InterruptedException {//leer info de proUS
        try {
            //obtiene todos los drones en los que esta trabajndo un usuario que tengan un progreso de 99 o menos
            Query query = con.child("proUS").orderByChild("usuario").equalTo(user);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ArrayList a = new ArrayList();
                    //    proUS log;
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                           //     log = user.getValue(proUS.class);
                              //  if (log.getProgreso() != 100) {
                             //       a.add(log.getSerie());
                             //   }
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error: " + es);
                            }
                        }
                        if (callback != null) {
                            //retorna los numeros de serie
                            callback.rolRecibido(a);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private void buscar() {//buscar algun trabajo especifico por su numero de serie
        try {
            recuperaRol1(new RolCallback1() {
                @Override
                public void rolRecibido(ArrayList rol) {
                    for (int i = 0; i < rol.size(); i++) {
                        try {
                            //lee trabajos, segun los numeros de serie
                            Query query = con.child("trabajos").orderByChild("serie").equalTo(Integer.parseInt(rol.get(i).toString()));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                      //  trabajos trab;
                                        for (DataSnapshot user : snapshot.getChildren()) {
                                            try {
                                             //   trab = user.getValue(trabajos.class);
                                              //  if (trab.getProgreso() != 100) {//si el progreso es menor a 100
                                               //     if (trab.getSerie() == Integer.parseInt(txtBuscar.getText())) {//si la serie del dron obtenido es igual al de la busqueda
                                                        //se agrega el resultado a la tabla
                                                 //       modelo.addRow(new Object[]{trab.getSerie(), trab.getPlantilla(), (int)trab.getProgreso() + " %", trab.getFechaE()});
                                                // //   }
                                             //   }
                                            } catch (Exception es) {
                                                JOptionPane.showMessageDialog(context, "Error: " + es);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    JOptionPane.showMessageDialog(context, "Error: " + error);
                                }
                            });
                        } catch (Exception t) {
                            JOptionPane.showMessageDialog(context, "Error: " + t);
                        }
                    }
                }
            });
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private void leer() {//lee todos los trabajos en los que el trabajador que ingreso a esta interfaz esta involucrado
        try {
            recuperaRol(new RolCallback() {
                @Override
                public void rolRecibido(ArrayList rol) {
                    for (int i = 0; i < rol.size(); i++) {
                        try {
                            //lee el nodo de trabajos, cuya serie sea igual a la serie de los drones en los que el trabajador esta involucrado
                            Query query = con.child("trabajos").orderByChild("serie").equalTo(Integer.parseInt(rol.get(i).toString()));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                      //  trabajos trab;
                                        for (DataSnapshot user : snapshot.getChildren()) {
                                            try {
                                               // trab = user.getValue(trabajos.class);
                                             //   if (trab.getProgreso() != 100) {//si el progreso en menor a 100
                                                    //poner en tabla
                                                 //   modelo.addRow(new Object[]{trab.getSerie(), trab.getPlantilla(), (int)trab.getProgreso() + " %", trab.getFechaE()});
                                             //   }
                                            } catch (Exception es) {
                                                JOptionPane.showMessageDialog(context, "Error: " + es);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    JOptionPane.showMessageDialog(context, "Error: " + error);
                                }
                            });
                        } catch (Exception t) {
                            JOptionPane.showMessageDialog(context, "Error: " + t);
                        }
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void recuperaRol(RolCallback callback) throws InterruptedException {//lee proUS para obtener los drones relacionados a un usuario
        try {
            Query query = con.child("proUS").orderByChild("usuario").equalTo(user);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        ArrayList a = new ArrayList();
                      //  proUS log;
                        for (DataSnapshot user : snapshot.getChildren()) {

                            try {

                               // log = user.getValue(proUS.class);
                              //  if (!a.contains(log.getSerie())) {
                                //    if (log.getProgreso() != 100) {//si el progreso es menor a 100
                                 //       a.add(log.getSerie());
                                  //  }
                              //  }

                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error: " + es);
                            }

                        }
                        if (callback != null) {
                            callback.rolRecibido(a);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }

            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private interface RolCallback {
        void rolRecibido(final ArrayList rol);
    }

    private void leerTodo() {//leer todos los trabajos con un progreso menor a 100, no importa el usuario/usuarios involucrados
        try {
            //esto se hace para cuando un admin o supervisor quiere ver el desarrollo del dron
            Query query = con.child("trabajos").orderByChild("progreso").startAt(0).endAt(99);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                      //  trabajos trab;
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                             //   trab = user.getValue(trabajos.class);
                                //pone la info obtenida en la tabla
                             //   modelo.addRow(new Object[]{trab.getSerie(), trab.getPlantilla(), (int)trab.getProgreso() + " %", trab.getFechaE()});
                            } catch (Exception es) {
                                JOptionPane.showMessageDialog(context, "Error: " + es);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }
            });
        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error: " + t);
        }
    }

    private void ingles() {//pone la interfaz en ingles
        txtBuscar.setText("Search");
        lblTitulo.setText("Activities list menu");
        JTableHeader tableHeader = tablaUsers.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Serial N.");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("Drone");
        tableColumn = tableColumnModel.getColumn(2);
        tableColumn.setHeaderValue("Progress");
        tableColumn = tableColumnModel.getColumn(3);
        tableColumn.setHeaderValue("Date");
        tableHeader.repaint();
    }

    private void esp(){ //pone la interfaz en espanol
        txtBuscar.setText("Buscar");
        lblTitulo.setText("Menu de lista de ordenes");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tablaUsers;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
