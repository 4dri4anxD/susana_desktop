package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import disenos.StretchIcon;
import disenos.centerTextInTable;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import disenos.putIcon;
import helpers.back;
import helpers.checkUsers;
import helpers.windowClosing;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import obtenerDatos.plantillasFinal;
import obtenerDatos.plantillasShipment;
import obtenerDatos.plantillasCalidad;

public class vistaPlantillas extends JFrame {

    private DatabaseReference con;//Conexion a la base de datos
    private String user, idioma, plantilla;//nombre de usuario que esta utlizando la app, idioma de la app, codigo de plantilla y nombre de plantilla
    private int priv, check;//privilegio de usuario (0,1,2,3,4)
    private DefaultTableModel modelo;//modelo de la tabla
    private JFrame context;//para los JOptionPane
    private String tN;//variables que guardan un hint para las cajas de texto
    private LinkedHashMap<String, Integer> actireq;
    private disenos disenos;
    private info info;
    private putIcon icon;

    public vistaPlantillas(DatabaseReference con, String user, int priv, String idioma, String plantilla, int check) {
        initComponents();
        new configuracionVentana(this);
        disenos=new disenos();
        info=new info();
        this.check = check;
        this.con = con;
        this.user = user;
        this.priv = priv;
        this.plantilla = plantilla;
        this.idioma = idioma;
        context = this;
        icon=new putIcon();

        iniciarVariables();
        iniciarDiseno();
        mostrar();

        if (plantilla != null) {
            leer();
            txtNombre.setText(plantilla);
            txtNombre.setEnabled(false);
            if (idioma.equals("english")) {
                lblTitulo.setText("Edit template");
            } else {
                lblTitulo.setText("Editar plantilla");
            }
            //leer Base de datos
        } else {
            if (idioma.equals("english")) {
                lblTitulo.setText("Add new template");
            } else {
                lblTitulo.setText("Agregar nueva plantilla");
            }

        }

    }

    private void iniciarVariables() {
        modelo = (DefaultTableModel) tablaPermisos.getModel();
        actireq = new LinkedHashMap();
        //   actividades = new ArrayList();
        //   requisitos = new ArrayList();

    }

    private void iniciarDiseno() {
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        tablaPermisos.setDefaultRenderer(Object.class, new centerTextInTable());

        disenos.botones(btnAdd, 3);
        disenos.botones(btnAsignarPre, 3);
        disenos.botones(btnAddProceso, 3);
        disenos.botones(btnAtras, 3);

        disenos.textoL1(txtNombre);

        disenos.fondo(pnlFondo, 2);
        disenos.fondo(pnlCuerpo, 2);
        disenos.fondo(pnlCabecera, 3);
        disenos.fondo(pnlDer, 1);
        disenos.fondo(pnlIzq, 1);

        disenos.titulo(lblTitulo, 2);
        disenos.titulo(lblNombre, 6);
        
        icon.setIcon(btnAdd, "img/guardar1.png");
        icon.setIcon(btnAddProceso, "img/agregarProceso.png");
        icon.setIcon(btnAsignarPre, "img/checklist.png");
        icon.setIcon(btnAtras, "img/atras2.png");
        

        new disenoTabla().cabecera(tablaPermisos);

        //las siguientes 3 lineas nomas son pa que el celleditor no sea null
        final JTextField text = new JTextField();
        TableCellEditor s = new DefaultCellEditor(text);
        tablaPermisos.setCellEditor(s);

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlFondo = new javax.swing.JPanel();
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnAddProceso = new javax.swing.JButton();
        btnAsignarPre = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        scrool = new javax.swing.JScrollPane();
        tablaPermisos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitulo.setText("Menu usuarios");

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

        pnlDer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlDerMousePressed(evt);
            }
        });

        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnAddProceso.setToolTipText("Agregar proceso");
        btnAddProceso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAddProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProcesoActionPerformed(evt);
            }
        });

        btnAsignarPre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAsignarPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarPreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAsignarPre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAsignarPre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(20, 20, 20)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlIzqLayout.setVerticalGroup(
            pnlIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIzqLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlCuerpo.setLayout(new java.awt.GridBagLayout());

        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("Nombre de plantilla:");
        lblNombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 199;
        gridBagConstraints.ipady = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCuerpo.add(lblNombre, gridBagConstraints);

        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCuerpo.add(txtNombre, gridBagConstraints);

        scrool.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                scroolKeyPressed(evt);
            }
        });

        tablaPermisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Actividad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaPermisos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tablaPermisosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaPermisosFocusLost(evt);
            }
        });
        tablaPermisos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPermisosMouseClicked(evt);
            }
        });
        tablaPermisos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tablaPermisosInputMethodTextChanged(evt);
            }
        });
        tablaPermisos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaPermisosKeyPressed(evt);
            }
        });
        scrool.setViewportView(tablaPermisos);
        if (tablaPermisos.getColumnModel().getColumnCount() > 0) {
            tablaPermisos.getColumnModel().getColumn(0).setResizable(false);
            tablaPermisos.getColumnModel().getColumn(0).setPreferredWidth(300);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 477;
        gridBagConstraints.ipady = 405;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 12.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 11, 0);
        pnlCuerpo.add(scrool, gridBagConstraints);

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, 505, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 494, Short.MAX_VALUE)
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
        if (new back().backConf(idioma, this)) {
            cambio();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        comprobarTabla();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (!actireq.containsKey(modelo.getValueAt(i, 0).toString())) {
                actireq.put(modelo.getValueAt(i, 0).toString(), 0);
            }
        }
        Set<String> actividades = actireq.keySet();
        for (String actividad : actividades) {
            ArrayList<Integer> comprobar = buscarTabla(actividad);
            if (comprobar.get(0) == 0) {
                actireq.remove(actividad);
            }
        }
        if (actireq.size() > 0) {//si existe algun proceso
            info.setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            //  boolean val = true;
            //   actualizarIndices();//actualizar datos.procesosPlantilla
            if (!txtNombre.getText().equals(tN)) {//si hay algo en los campos de texto diferente al hint
                if (!txtNombre.getText().contains("{") || !txtNombre.getText().contains("}") || !txtNombre.getText().contains(";")) {
                    String tableName = getTableName();
                    if (plantilla != null) {//update
                        //Eliminar e insertar
                        try {
                            con.child(tableName).child(plantilla).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError de, DatabaseReference dr) {
                                }
                            });
                            insert(true);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(context, "Error: " + e);
                            //  Context context = getApplicationContext();
                            // Toast toast = Toast.makeText(context, "Notifica el siguiente error20: " + e, Toast.LENGTH_SHORT);
                            // toast.show();
                        }
                    } else {//insert
                        leerC(tableName);
                    }
                } else {
                    //caracteres invalidos
                }
            } else {
                if (txtNombre.getText().equals("") || txtNombre.getText().equals(tN)) {
                    txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                }

                if (idioma.equals("english")) {
                    JOptionPane.showMessageDialog(context, "Fill out all fields");
                } else {
                    JOptionPane.showMessageDialog(context, "Llene todos los campos");
                }
            }
        } else {
            if (txtNombre.getText().equals("") || txtNombre.getText().equals(tN)) {
                txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
            tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            if (idioma.equals("english")) {
                JOptionPane.showMessageDialog(context, "Template must have at least one proccess and\nat least an activity assigned to each proccess");
            } else {
                JOptionPane.showMessageDialog(context, "Debe haber al menos un proceso y\nal menos una actividad asignada por proceso");
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProcesoActionPerformed
        // TODO add your handling code here:
        // verificarRepetidos();

        //   if (comprobarTabla() || modelo.getRowCount() < 2) {
        comprobarTabla();
        modelo.addRow(new Object[]{"", "", ""});
        tablaPermisos.editCellAt(modelo.getRowCount() - 1, 0);
        Component aComp = tablaPermisos.getEditorComponent();
        aComp.requestFocus();
        //  }

        if (modelo.getRowCount() > 1) {
            btnAsignarPre.setEnabled(true);
        }
    }//GEN-LAST:event_btnAddProcesoActionPerformed

    private void tablaPermisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPermisosMouseClicked
        // TODO add your handling code here:
        comprobarTabla();
        if (evt.getButton() == 3) {
            Point p = evt.getPoint();
            int rowNumber = tablaPermisos.rowAtPoint(p);
            ListSelectionModel modelo1 = tablaPermisos.getSelectionModel();
            modelo1.setSelectionInterval(rowNumber, rowNumber);
            String texto1, texto2, o1, o2;
            if (idioma.equals("english")) {
                texto1 = "Are you sure you want to delete the selected proccess?";
                texto2 = "Confirm Action";
                o1 = "Yes";
                o2 = "No";
            } else {
                texto1 = "¿Seguro que quiere eliminar el proceso seleccionado?";
                texto2 = "Confirmar Acción";
                o1 = "Si";
                o2 = "No";
            }
            Object[] options = {o1, o2};
            if (JOptionPane.showOptionDialog(this, texto1, texto2,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]) == 0) {
                try {
                    actireq.remove(modelo.getValueAt(rowNumber, 0).toString());
                    modelo.removeRow(rowNumber);
                } catch (NullPointerException e) {
                    if (idioma.equals("english")) {
                        JOptionPane.showMessageDialog(context, "Select a proccess to delete");
                    } else {
                        JOptionPane.showMessageDialog(context, "Seleccione un proceso para eliminar");
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaPermisosMouseClicked

    private void tablaPermisosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaPermisosFocusLost
        // TODO add your handling code here:
        comprobarTabla();
    }//GEN-LAST:event_tablaPermisosFocusLost

    private void tablaPermisosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaPermisosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaPermisosFocusGained

    private void tablaPermisosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPermisosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            comprobarTabla();
        }
    }//GEN-LAST:event_tablaPermisosKeyPressed

    private void tablaPermisosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tablaPermisosInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_tablaPermisosInputMethodTextChanged

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        // TODO add your handling code here:
        JTextField t = txtNombre;
        if (t.getText().equals(tN)) {
            t.setText("");
            t.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
        JTextField t = txtNombre;
        if (!t.getText().equals("")) {
            try {
                if (t.getText().length() > 20) {
                    t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    //  valido = false;
                } else {
                    t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    //    valido = true;
                }

            } catch (Exception e) {
                //    valido = false;
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
        } else {
            // valido = false;
            t.setText(tN);
            t.setForeground(Color.lightGray);
            t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if (txtNombre.getText().length() == 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnAsignarPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarPreActionPerformed
        // TODO add your handling code here:
        try {
            //  if (comprobarTabla()) {
            comprobarTabla();
            info.setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            String procesoSel = modelo.getValueAt(tablaPermisos.getSelectedRow(), 0).toString();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (!actireq.containsKey(modelo.getValueAt(i, 0).toString())) {
                    actireq.put(modelo.getValueAt(i, 0).toString(), 0);
                }

            }
            int req = 0;
            try {
                req = actireq.get(procesoSel);
            } catch (Exception e) {
                // System.out.println("ex: "+e);
            }
            new requisitos(idioma, tablaPermisos.getSelectedRow(), procesoSel, req, actireq, this, null, 0).setVisible(true);
            //   }
        } catch (Exception e) {
            if (idioma.equals("english")) {
                JOptionPane.showMessageDialog(context, "Select a process to see its required processes");
            } else {
                JOptionPane.showMessageDialog(context, "Selecciona un proceso para ver sus procesos requeridos");
            }
        }
    }//GEN-LAST:event_btnAsignarPreActionPerformed

    private void pnlDerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDerMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlDerMousePressed

    private void scroolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scroolKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            comprobarTabla();
        }
    }//GEN-LAST:event_scroolKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new windowClosing(idioma, this);
    }//GEN-LAST:event_formWindowClosing

    private String getTableName() {
        String plantilla = "";
        switch (check) {
            case 1:
                plantilla = "plantillasCalidad";
                break;
            case 2:
                plantilla = "plantillasTesting";
                break;
            case 3:
                plantilla = "plantillasShipment";
                break;
            case 4:
                plantilla = "plantillasFinal";
                break;
        }
        return plantilla;
    }

    private void comprobarTabla() {
        if (tablaPermisos.isEditing()) {
            tablaPermisos.getCellEditor().stopCellEditing();//detenga la edicion para almacenar el valor
        }
        //  tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).toString().equals("")) {//si algun campo de nombre de proceso se encuentra vacio
                modelo.removeRow(i);
                //  return false;
                return;
            } else {
                ArrayList<Integer> b = buscarTabla(modelo.getValueAt(i, 0).toString());
                if (b.get(0) > 1) {
                    modelo.removeRow(b.get(1));
                    //  return false;
                    return;
                }
            }
        }
        //  return true;
    }

    private ArrayList<Integer> buscarTabla(String plantilla) {
        ArrayList<Integer> regreso = new ArrayList();
        int cont = 0;
        int removePos = 0;
        if (plantilla != null && modelo != null) {
            for (int renglon = 0; renglon < modelo.getRowCount(); renglon++) {
                String codigoEnRenglon = (String) modelo.getValueAt(renglon, 0);
                if (plantilla.equals(codigoEnRenglon)) {
                    cont++;
                    removePos = renglon;
                }
            }
        }
        regreso.add(cont);
        regreso.add(removePos);
        return regreso;
    }

    public void setRequisito(String proceso, int requisito) {
        actireq.put(proceso, requisito);
    }

    private void leer() {
        try {
            Query query = null;
            switch (check) {
                case 1://child("actividades")
                    query = con.child("plantillasCalidad").child(plantilla);//child("ubicacion");//.orderByChild("nombre");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                try {
                                    plantillasCalidad dato = null;
                                    for (DataSnapshot user : snapshot.getChildren()) {
                                        dato = user.getValue(plantillasCalidad.class);
                                        //  actividades.add(dato.getUbicacion());
                                        //requisitos.add(dato.getRequisito());
                                        actireq.put(dato.getUbicacion(), dato.getRequisito());
                                        modelo.addRow(new Object[]{dato.getUbicacion()});
                                    }
                                    //llenarTabla

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(context, "Error: " + e);
                                    // Toast.makeText(getApplicationContext(), "Notifica el siguiente error1: " + e, Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (idioma.equals("english")) {
                                    JOptionPane.showMessageDialog(context, "An error has ocurred while reading data base");
                                } else {
                                    JOptionPane.showMessageDialog(context, "Ha ocurrido un error al leer la base de datos");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            JOptionPane.showMessageDialog(context, "Error: " + error);
                            //Toast.makeText(getApplicationContext(), "De " + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 3:
                    query = con.child("plantillasShipment").child(plantilla);//child("ubicacion");//.orderByChild("nombre");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                try {
                                    plantillasShipment dato = null;
                                    for (DataSnapshot user : snapshot.getChildren()) {
                                        dato = user.getValue(plantillasShipment.class);
                                        // actividades.add(dato.getAccesorio());
                                        // requisitos.add(dato.getRequisito());
                                        actireq.put(dato.getAccesorio(), dato.getRequisito());
                                        modelo.addRow(new Object[]{dato.getAccesorio()});
                                    }

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(context, "Error: " + e);
                                    // Toast.makeText(getApplicationContext(), "Notifica el siguiente error1: " + e, Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (idioma.equals("english")) {
                                    JOptionPane.showMessageDialog(context, "An error has ocurred while reading data base");
                                } else {
                                    JOptionPane.showMessageDialog(context, "Ha ocurrido un error al leer la base de datos");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            JOptionPane.showMessageDialog(context, "Error: " + error);
                            // Toast.makeText(getApplicationContext(), "De " + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 4:
                    query = con.child("plantillasFinal").child(plantilla);//child("ubicacion");//.orderByChild("nombre");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                try {
                                    plantillasFinal dato = null;
                                    for (DataSnapshot user : snapshot.getChildren()) {
                                        dato = user.getValue(plantillasFinal.class);
                                        //  actividades.add(dato.getActividad());
                                        // requisitos.add(dato.getRequisito());
                                        actireq.put(dato.getActividad(), dato.getRequisito());
                                        modelo.addRow(new Object[]{dato.getActividad()});
                                    }
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(context, "Error: " + e);
                                    // Toast.makeText(getApplicationContext(), "Notifica el siguiente error1: " + e, Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (idioma.equals("english")) {
                                    JOptionPane.showMessageDialog(context, "An error has ocurred while reading data base");
                                } else {
                                    JOptionPane.showMessageDialog(context, "Ha ocurrido un error al leer la base de datos");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            JOptionPane.showMessageDialog(context, "Error: " + error);
                            // Toast.makeText(getApplicationContext(), "De " + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);

        }
    }

    private void leerC(String texto) {
        try {
            Query query = con.child(texto).child(txtNombre.getText().toString().toLowerCase());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        insert(false);
                    } else {
                        if (idioma.equals("english")) {
                            JOptionPane.showMessageDialog(context, "Template's ID already exists");
                        } else {
                            JOptionPane.showMessageDialog(context, "Ya existe el ID de la plantilla");
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

    private void insert(boolean update) {//Inserta la info en la base de datos
        try {
            Set<String> act = actireq.keySet();
            int i = 0;
            switch (check) {
                case 1://Calidad
                    plantillasCalidad datoCalidad;
                    for (String actividad : act) {
                        datoCalidad = new plantillasCalidad(actividad, actireq.get(actividad));
                        con.child("plantillasCalidad").child(txtNombre.getText().toLowerCase()).child("" + i).setValue(datoCalidad, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                            }
                        });
                        i++;
                    }
                    break;
                case 3://Shipment
                    for (String actividad : act) {
                        plantillasShipment datoShipment = new plantillasShipment(actividad, actireq.get(actividad));
                        con.child("plantillasShipment").child(txtNombre.getText().toLowerCase()).child("" + i).setValue(datoShipment, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                            }
                        });
                        i++;
                    }
                    break;
                case 4:
                    for (String actividad : act) {
                        plantillasFinal datoFinal = new plantillasFinal(actividad, actireq.get(actividad));
                        con.child("plantillasFinal").child(txtNombre.getText().toLowerCase()).child("" + i).setValue(datoFinal, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                            }
                        });
                        i++;
                    }
                    break;
            }
            if (idioma.equals("english")) {
                JOptionPane.showMessageDialog(context, "Template saved succesfully");
            } else {
                JOptionPane.showMessageDialog(context, "Plantilla almacenada existosamente");
            }
            cambio();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }

    }

    private void cambio() {
        info.setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPlantillas(con, user, priv, idioma, check).setVisible(true);
        this.dispose();
    }

    private void mostrar() {
        if (idioma.equals("english")) {
            lblTitulo.setText("Template view");
            JTableHeader tableHeader = tablaPermisos.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Activity");
            tableHeader.repaint();
            btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Save template</b></html>");
            btnAddProceso.setToolTipText("<html><b style='font-size: 12px;'>Add an activity</b></html>");
            btnAsignarPre.setToolTipText("<html><b style='font-size: 12px;'>Manage required activities</b></html>");
            tN = "Ex. Skywalker";
            txtNombre.setText(tN);
            lblNombre.setText("Template's name:");
        } else {
            lblTitulo.setText("Vista de plantilla");
            lblNombre.setText("Nombre de plantilla: ");
            tN = "Ej. Skywalker";
            txtNombre.setText(tN);
            btnAsignarPre.setToolTipText("<html><b style='font-size: 12px;'>Administrar actividades requeridas</b></html>");
            btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Guardar plantilla</b></html>");
            btnAddProceso.setToolTipText("<html><b style='font-size: 12px;'>Agregar una actividad</b></html>");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddProceso;
    private javax.swing.JButton btnAsignarPre;
    private javax.swing.JButton btnAtras;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JScrollPane scrool;
    private javax.swing.JTable tablaPermisos;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
