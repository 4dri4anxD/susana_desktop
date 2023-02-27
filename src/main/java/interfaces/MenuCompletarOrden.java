package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import disenos.readRecordTableBackground;
import helpers.checkUsers;
import helpers.windowClosing;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import obtenerDatos.ordenes;
import org.json.simple.JSONObject;

public class MenuCompletarOrden extends JFrame {//clase para los trabajadores para que vean todas sus actividades pendientes
    //o para que algun admin o supervisor vea como va el progreso de algun dron

    private DatabaseReference con;
    private int priv;
    private String user, idioma;
    private int modo;//0 es que un supervisor o admin va a ver el progreso del trabajo de algun dron
    private JFrame context;
    private DefaultTableModel modelo;
    private ArrayList<String> serie, plantilla, fecha;
    private ArrayList<Integer> progreso;
    private boolean reg;

    public MenuCompletarOrden(DatabaseReference con, String user, int priv, String idioma, int modo) {//constructor
        initComponents();
        new configuracionVentana(this);

        //inicializacion de variables
        this.con = con;
        this.priv = priv;
        this.user = user;
        this.idioma = idioma;
        this.modo = modo;
        plantilla = new ArrayList();
        fecha = new ArrayList();
        serie = new ArrayList();
        progreso = new ArrayList();
        modelo = (DefaultTableModel) tablaUsers.getModel();
        context = this;
        reg = false;

        mostrar();
        if (modo == 1) {
            leerTodo(99);
        } else {
            leer(99);
        }
        iniciarDiseno();
    }

    public void iniciarDiseno() {//decorar los componentes del frame
        //centrar el texto en las celdas de la tabla
        modelo = (DefaultTableModel) tablaUsers.getModel();

        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        txtBuscar.setHorizontalAlignment(JLabel.CENTER);

        new disenos().botones(btnAtras, 3);
        new disenos().botones(btnBuscar, 1);
        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnReg, 3);

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
        ponerImg(btnReg, "img/verLog.png");

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
        btnReg = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
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

        btnReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        tablaUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
            if (idioma.equals("english")) {
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
        new info().setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPrincipal(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            new info().setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            //leer serie de la tabla
            int serie = Integer.parseInt(modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString());
            String plantilla = modelo.getValueAt(tablaUsers.getSelectedRow(), 1).toString();
            //new VistaCheckLists(con, user, priv, idioma, modo, serie, plantilla).setVisible(true);

            //DatabaseReference con, String user, int priv, String idioma, int serie, int modo
            new vistaCompletarOrden(con, user, priv, idioma, serie, plantilla, modo).setVisible(true);
            this.dispose();
        } catch (Exception e) {
            if (idioma.equals("english")) {
                JOptionPane.showMessageDialog(context, "Select a drone to see its details");
            } else {
                JOptionPane.showMessageDialog(context, "Selecciona un dron para ver sus detalles");
            }

        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscar(txtBuscar.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            if (!txtBuscar.getText().equals("")) {
                buscar(txtBuscar.getText());
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
        // TODO add your handling code here:
        if (modo == 1) {
            leerTodo(100);
        } else {
            leer(100);
        }
    }//GEN-LAST:event_btnRegActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        if (txtBuscar.getText().length() == 5) {
            evt.consume();
        }
        char caracter = evt.getKeyChar();
        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0')
                || (caracter > '9'))) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new windowClosing(idioma, this);
    }//GEN-LAST:event_formWindowClosing

    private void llenarTabla() {
        tablaUsers.setDefaultRenderer(Object.class, new readRecordTableBackground(progreso));
        for (int i = 0; i < serie.size(); i++) {
            modelo.addRow(new Object[]{serie.get(i), plantilla.get(i), progreso.get(i) + "%", fecha.get(i)});
        }
    }

    private boolean fillUsuario(JSONObject allData, String primera, String segunda, String tercera, boolean bus) {
        try {
            boolean bandera = false;
            HashMap check = (HashMap) allData.get(primera);
            ArrayList actividades = (ArrayList) check.get(segunda);
            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                String us = jsonTC.get(tercera).toString();
                if (us.toLowerCase().equals(user.toLowerCase())) {
                    //no retornar, solo marcar como true o false una bandera
                    if (bus) {
                        return true;
                    } else {
                        if (reg) {
                            bandera = true;
                        } else {
                            if (actividadCompletada(jsonTC, primera)) {
                                bandera = true;
                            }
                        }
                    }
                }
            }
            return bandera;
        } catch (Exception e) {
            //   System.out.println("erroooooooooooooooooooooooooooor: "+e);
            return false;
        }
    }

    private boolean actividadCompletada(HashMap jsonTC, String primera) {
        try {
            switch (primera) {
                case "checkTC":
                    if (Boolean.parseBoolean(jsonTC.get("revsol").toString()) && Boolean.parseBoolean(jsonTC.get("aprobado").toString())) {
                        return false;
                    } else {
                        return true;
                    }

                case "checkTT":
                    if (Boolean.parseBoolean(jsonTC.get("realizado").toString()) && Boolean.parseBoolean(jsonTC.get("aprobacion").toString())
                            && !jsonTC.get("ponderacion").toString().equals("N/A")) {
                        return false;
                    } else {
                        return true;
                    }

                case "checkTS":
                    if (Integer.parseInt(jsonTC.get("completado").toString()) != 0) {
                        return false;
                    } else {
                        return true;
                    }

                case "checkTF":
                    if (Integer.parseInt(jsonTC.get("completado").toString()) == 1) {
                        return false;
                    } else {
                        return true;
                    }

            }
        } catch (Exception e) {
            //   System.out.println("erroooooooooooooooooooooooooooor: "+e);
            return false;
        }
        return false;
    }

    private void leer(int progresoFinal) {
        try {
            Query query = null;
            if (progresoFinal == 100) {
                reg = true;
                query = con.child("Trabajos").orderByChild("progreso").equalTo(progresoFinal);
            } else {
                reg = false;
                query = con.child("Trabajos").orderByChild("progreso").endAt(progresoFinal);
            }
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ordenes log;
                        for (DataSnapshot info : snapshot.getChildren()) {
                            log = info.getValue(ordenes.class);
                            Map<String, Object> value = (Map<String, Object>) info.getValue();
                            JSONObject allData = new JSONObject(value);
                            if (!serie.contains(info.getKey())) {
                                if (fillUsuario(allData, "checkTC", "actividades", "usuario", false)
                                        || fillUsuario(allData, "checkTT", "actividades", "usuario", false)
                                        || fillUsuario(allData, "checkTT", "actividades", "usuario1", false)
                                        || fillUsuario(allData, "checkTS", "actividades", "usuario", false)
                                        || fillUsuario(allData, "checkTF", "actividades", "usuario", false)) {
                                    //el usuario existe en la orden
                                    serie.add(info.getKey());
                                    //  modelo.addRow(new Object[]{info.getKey(), log.getNombre(), log.getProgreso() + "%", log.getFecha()});
                                    plantilla.add(log.getNombre());
                                    progreso.add((int) log.getProgreso());
                                    fecha.add(log.getFecha());
                                    //  modelo.
                                }
                            }
                            //  hm.put(a, usuarios);
                        }
                        borrarTabla();
                        llenarTabla();

                    } else {
                        if (idioma.equals("english")) {
                            JOptionPane.showMessageDialog(context, "There are no orders involving you");
                        } else {
                            JOptionPane.showMessageDialog(context, "No existen ordenes en las que participes");
                        }

                        //No hay trabajos agregados aun
                        // new showToast(getString(R.string.noTrabajosSpn), getString(R.string.noTrabajosEng), idioma, getApplicationContext());
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                    // new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + error, getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, getApplicationContext());

                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
            //  e.printStackTrace();
        }
    }

    private void leerTodo(int progresoFinal) {
        try {
            Query query = null;
            if (progresoFinal == 100) {
                query = con.child("Trabajos").orderByChild("progreso").equalTo(progresoFinal);
            } else {
                query = con.child("Trabajos").orderByChild("progreso").endAt(progresoFinal);
            }
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        ordenes log;
                        for (DataSnapshot info : snapshot.getChildren()) {
                            log = info.getValue(ordenes.class);
                            if (!serie.contains(info.getKey())) {
                                serie.add(info.getKey());
                                //  modelo.addRow(new Object[]{info.getKey(), log.getNombre(), log.getProgreso() + "%", log.getFecha()});
                                plantilla.add(log.getNombre());
                                progreso.add((int) log.getProgreso());
                                fecha.add(log.getFecha());
                            }
                        }
                        borrarTabla();
                        llenarTabla();

                    } else {
                        if (idioma.equals("english")) {
                            JOptionPane.showMessageDialog(context, "There are no active orders");
                        } else {
                            JOptionPane.showMessageDialog(context, "No hay ordenes activas");
                        }

                        //No hay trabajos agregados aun
                        // new showToast(getString(R.string.noTrabajosSpn), getString(R.string.noTrabajosEng), idioma, getApplicationContext());
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                    //  new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + error, getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, getApplicationContext());

                }
            });

        } catch (Exception t) {
            JOptionPane.showMessageDialog(context, "Error: " + t);
            //  Context context = getApplicationContext();
            //////  Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + t, Toast.LENGTH_SHORT);
            // toast.show();
        }
    }

    private void limpiar() {
        progreso = new ArrayList<>();
        plantilla = new ArrayList<>();
        fecha = new ArrayList<>();
        serie = new ArrayList<>();
    }

    private void buscar(String bus) {
        try {
            Query query = con.child("Trabajos").child(bus);
            //.child(bus);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        ArrayList<String> seri = new ArrayList();
                        ArrayList<String> plantill = new ArrayList();
                        ArrayList<Integer> progres = new ArrayList();
                        ArrayList<String> fech = new ArrayList();
                        boolean res = false;
                        ordenes log;
                        log = snapshot.getValue(ordenes.class);
                        //   String a = bus + "_" + log.getNombre() + "_" + log.getProgreso() + "_" + log.getFecha();
                        Map<String, Object> value = (Map<String, Object>) snapshot.getValue();//info.getValue()
                        JSONObject allData = new JSONObject(value);
                        if (modo == 1) {//todo
                            //  modelo.addRow(new Object[]{bus, log.getNombre(), log.getProgreso() + "%", log.getFecha()});
                            seri.add(bus);
                            plantill.add(log.getNombre());
                            fech.add(log.getFecha());
                            progres.add((int) log.getProgreso());
                            res = true;
                        } else {
                            if (fillUsuario(allData, "checkTC", "actividades", "usuario", true)
                                    || fillUsuario(allData, "checkTT", "actividades", "usuario", true)
                                    || fillUsuario(allData, "checkTT", "actividades", "usuario1", true)
                                    || fillUsuario(allData, "checkTS", "actividades", "usuario", true)
                                    || fillUsuario(allData, "checkTF", "actividades", "usuario", true)) {
                                //   modelo.addRow(new Object[]{bus, log.getNombre(), log.getProgreso() + "%", log.getFecha()});
                                seri.add(bus);
                                plantill.add(log.getNombre());
                                fech.add(log.getFecha());
                                progres.add((int) log.getProgreso());
                                res = true;
                            }
                        }
                        if (res) {
                            limpiar();
                            serie.addAll(seri);
                            plantilla.addAll(plantill);
                            fecha.addAll(fech);
                            progreso.addAll(progres);
                            borrarTabla();
                            llenarTabla();
                        } else {
                            if (idioma.equals("english")) {
                                JOptionPane.showMessageDialog(context, "No results were found");
                            } else {
                                JOptionPane.showMessageDialog(context, "No se encontraron resultados");
                            }
                            //no se encontro
                        }

                    } else {
                        if (idioma.equals("english")) {
                            JOptionPane.showMessageDialog(context, "No results were found");
                        } else {
                            JOptionPane.showMessageDialog(context, "No se encontraron resultados");
                        }
                        //No hay trabajos agregados aun
                        // new showToast(getString(R.string.noTrabajosEncontradosSpn), getString(R.string.noTrabajosEncontradosEng), idioma, getApplicationContext());
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                    // new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + error, getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, getApplicationContext());
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
            //  e.printStackTrace();
        }
    }

    private void borrarTabla() {
        int total = modelo.getRowCount();
        for (int i = 0; i < total; i++) {
            modelo.removeRow(0);
        }
    }

    private void mostrar() {
        if (idioma.equals("english")) {
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
        } else {
            txtBuscar.setText("Buscar");
            lblTitulo.setText("Menu de lista de ordenes");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnReg;
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
