package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import helpers.windowClosing;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.WAIT_CURSOR;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import obtenerDatos.ultimomsj;
import obtenerDatos.users;
import org.json.simple.JSONObject;

public class menuUsuarios extends JFrame {//clase que muestra a los usuarios en la base de datos

    //declaracion de variables globales
    private DatabaseReference con;
    private int priv, co;
    private String user, idioma;
    private JFrame context;
    private DefaultTableModel modelo;//modelo de la tabla

    public menuUsuarios(DatabaseReference con, String user, int priv, String idioma) {//constructor
        initComponents();
        new configuracionVentana(this);
        //poner icono

        //inicializacion de variables
        context = this;
        modelo = (DefaultTableModel) tablaUsers.getModel();
        co = 0;
        this.con = con;
        this.priv = priv;
        this.user = user;
        this.idioma = idioma;

        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }
        iniciarDiseno();
        leer();
        //se deshabilita el boton de eliminar pq no se eliminan las fotos
        btnEliminar.setEnabled(false);
    }

    public void iniciarDiseno() {//decora los componentes del frame
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        txtBuscar.setHorizontalAlignment(JLabel.CENTER);

        new disenos().botones(btnEliminar, 3);
        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);
        new disenos().botones(btnBuscar, 1);

        new disenos().textoL(txtBuscar);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnEliminar, "img/trash2.png");
        ponerImg(btnAdd, "img/edit1.png");
        ponerImg(btnAtras, "img/atras2.png");
        ponerImg(btnBuscar, "img/buscar1.png");

        tablaUsers.requestFocus();
        txtBuscar.setForeground(Color.LIGHT_GRAY);

        new disenoTabla().cabecera(tablaUsers);
    }

    public void ponerImg(JButton b, String ruta) {//poner imagenes a los botones
        ImageIcon imagen = new ImageIcon(ruta);
        Image imgEscalada = imagen.getImage().getScaledInstance(b.getWidth(),
                b.getHeight(), Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pnlFondo = new javax.swing.JPanel();
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsers = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlFondoMousePressed(evt);
            }
        });

        pnlCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlCabeceraMousePressed(evt);
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

        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlIzq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlIzqMousePressed(evt);
            }
        });

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

        pnlCuerpo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlCuerpoMousePressed(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
        });

        tablaUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuarios"
            }
        ));
        tablaUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tablaUsers);

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
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
            int sel = tablaUsers.getSelectedRow();
            if (sel == -1) {//si no se tiene seleccionado ninguna fila, se entiende que se va a agregar un nuevo usuario
                new info().setXY(this.getX(), this.getY());
                new vistaUsuarios(con, user, priv, idioma, "").setVisible(true);
                this.dispose();
            } else {//si se selecciono alguna fila, se va a entrar a modificar
                new info().setXY(this.getX(), this.getY());
                new vistaUsuarios(con, user, priv, idioma, tablaUsers.getValueAt(sel, 0).toString()).setVisible(true);
                this.dispose();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        String texto1, texto2, o1, o2;
        if (idioma.equals("English")) {
            texto1 = "Are you sure you want to delete the selected user?";
            texto2 = "Confirm Action";
            o1 = "Yes";
            o2 = "No";
        } else {
            texto1 = "¿Seguro que quiere eliminar al usuario seleccionado?";
            texto2 = "Confirmar Acción";
            o1 = "Si";
            o2 = "No";
        }
        Object[] options = {o1, o2};
        if (JOptionPane.showOptionDialog(this, texto1, texto2,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]) == 0) {
            try {
                if (!modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString().equals("admin")) {
                    delete(modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString(), tablaUsers.getSelectedRow());
                } else {
                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(context, "SuperUser cannot be deleted");
                    } else {
                        JOptionPane.showMessageDialog(context, "El superusuario no puede ser eliminado");
                    }
                }
            } catch (NullPointerException e) {
                if (idioma.equals("English")) {
                    JOptionPane.showMessageDialog(context, "Select a user to delete");
                } else {
                    JOptionPane.showMessageDialog(context, "Seleccione un usuario para eliminar");
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            buscar();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void pnlDerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDerMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlDerMousePressed

    private void pnlIzqMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlIzqMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlIzqMousePressed

    private void pnlCuerpoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCuerpoMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlCuerpoMousePressed

    private void pnlCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCabeceraMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlCabeceraMousePressed

    private void pnlFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFondoMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlFondoMousePressed

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_jScrollPane1MousePressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        if (txtBuscar.getText().length() == 25) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new windowClosing(idioma,this);
    }//GEN-LAST:event_formWindowClosing

    private void buscar() {//buscar por nombre de usuario
        int tabla = modelo.getRowCount();
        for (int i = 0; i < tabla; i++) {//se elimina todo lo que tenga la tabla
            modelo.removeRow(0);
        }
        String bus = txtBuscar.getText().toLowerCase();
        try {
            //se leen todos los usuarios que coincidan en parte con la busqueda
            Query query = con.child("usuarios").orderByChild("user").startAt(bus).endAt(bus + "\uf8ff");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                                users log = user.getValue(users.class);
                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                    //se agrega lo que encuentra a la tabla
                                    modelo.addRow(new Object[]{log.getUser()});
                                }
                            } catch (Exception e) {
                                System.out.println("Es: " + e);
                            }
                        }
                    } else {
                        if (idioma.equals("English")) {
                            JOptionPane.showMessageDialog(context, "No results were found");
                        } else {
                            JOptionPane.showMessageDialog(context, "No se encontraron resultados");
                        }
                        leer();
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

    private void ingles() {//pone la interfaz en ingles
        txtBuscar.setText("Search");
        lblTitulo.setText("Users menu");
        JTableHeader tableHeader = tablaUsers.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Users");
        tableHeader.repaint();
        btnEliminar.setToolTipText("<html><b style='font-size: 12px;'>Delete selected user</b></html>");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Add/Edit users</b></html>");
    }

    private void esp() {//pone la interfaz en espanol
        txtBuscar.setText("Buscar");
        lblTitulo.setText("Menu de usuarios");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Agregar/Eliminar usuarios</b></html>");
        btnEliminar.setToolTipText("<html><b style='font-size: 12px;'>Eliminar al usuario seleccionado</b></html>");
    }

    private void leer() {//lee todos los usuarios
        try {
            Query query = con.child("usuarios").orderByChild("user");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                                users log = user.getValue(users.class);
                                //pone lo encontrado en la tabla
                                modelo.addRow(new Object[]{log.getUser()});
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(context, "Error: " + e);
                            }
                        }

                        //centra el texto en las celdas de la tabla
                        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
                        render.setHorizontalAlignment(SwingConstants.CENTER);
                        for (int i = 0; i < modelo.getRowCount(); i++) {
                            tablaUsers.getColumnModel().getColumn(i).setCellRenderer(render);
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
            // System.out.println("Ex: " + e);
        }
    }

    private void changeUser(JSONObject allData, String primera, String responsable, String segunda, String tercera, String usuario, String serie) {
        try {
            HashMap check = (HashMap) allData.get(primera);
            String respo = check.get(responsable).toString();
            if (respo.equals(usuario)) {
                con.child("Trabajos")
                        .child(serie).child(primera).child(responsable).setValue("dummy", new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError de, DatabaseReference dr) {
                    }
                });
            }
            ArrayList actividades = (ArrayList) check.get(segunda);
            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                String us = jsonTC.get(tercera).toString();
                if (us.equals(usuario)) {
                    //cambiar este usuario por otro, ya sea superuser o dummy
                    con.child("Trabajos")
                            .child(serie).child(primera).child(segunda)
                            .child("" + i).child(tercera).setValue("dummy", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError de, DatabaseReference dr) {
                        }
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
            // System.out.println("Error: " + e);
        }
    }

    private void delete(String usuario, int pos) {
        try {
            Query query = con.child("Trabajos");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            try {

                                String check1 = "checkTC";
                                Map<String, Object> value = (Map<String, Object>) childSnapshot.getValue();
                                JSONObject allData = new JSONObject(value);
                                //TC
                                changeUser(allData, "checkTC",
                                        "responsable",
                                        "actividades",
                                        "usuario", usuario, childSnapshot.getKey());
                                //TT actividades
                                changeUser(allData, "checkTT",
                                        "responsable",
                                        "actividades",
                                        "usuario", usuario, childSnapshot.getKey());
                                //TT Rendimiento
                                changeUser(allData, "checkTT",
                                        "responsable",
                                        "rendimiento",
                                        "usuario", usuario, childSnapshot.getKey());
                                //TS
                                changeUser(allData, "checkTS",
                                        "responsable",
                                        "actividades",
                                        "usuario", usuario, childSnapshot.getKey());
                                //TF
                                changeUser(allData, "checkTF",
                                        "responsable",
                                        "actividades",
                                        "usuario", usuario, childSnapshot.getKey());

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(context, "Error: " + e);
                            }
                        }
                        try {
                            Query query = con.child("usuarios").orderByChild("user").equalTo(usuario).limitToLast(1);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                            String id = childSnapshot.getKey();
                                            try {
                                                con.child("usuarios").child(id).removeValue(new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(DatabaseError de, DatabaseReference dr) {
                                                    }
                                                });
                                                //remover posicion de tabla
                                                modelo.removeRow(pos);
                                                //   localDataSet2.remove(usuario);
                                                //  notifyDataSetChanged();
                                                //  val = false;
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(context, "Error: " + e);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    JOptionPane.showMessageDialog(context, "Error: " + error);
                                    // Toast.makeText(context, "Notifica el siguiente error: " + error, Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(context, "Error: " + e);
                            // Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
                            //  toast.show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: "+error);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: "+e);
            ////  Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            // toast.show();
        }
        try {
            Query query = con.child("keys").orderByChild("usuario").equalTo(usuario);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String id = childSnapshot.getKey();
                            try {
                                con.child("keys").child(id).removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError de, DatabaseReference dr) {
                                    }
                                });
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(context, "Error: "+e);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: "+error);
                    // Toast.makeText(context, "Notifica el siguiente error: " + error, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: "+e);
            ////  Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            //  toast.show();
        }
        try {
            Query query = con.child("ultimomsj");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ultimomsj msj;
                        String chat = "";
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            msj = childSnapshot.getValue(ultimomsj.class);
                            if (msj.getRemitente().equals(usuario)) {
                                try {
                                    if (msj.getGrupo() == 0) {
                                        chat = msj.getChat();
                                        con.child("ultimomsj").child(childSnapshot.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                                            @Override
                                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                                            }
                                        });
                                        con.child("chat").child(chat).removeValue(new DatabaseReference.CompletionListener() {
                                            @Override
                                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                                            }
                                        });
                                    } else {
                                        chat = msj.getChat();
                                        removerMiembroGrupo(chat, usuario);
                                    }
                                } catch (Exception e) {
                                }
                            } else if (msj.getDestinatario().equals(usuario)) {
                                try {
                                    chat = msj.getChat();
                                    con.child("ultimomsj").child(childSnapshot.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(DatabaseError de, DatabaseReference dr) {
                                        }
                                    });
                                    if (msj.getGrupo() == 0) {
                                        con.child("chat").child(chat).removeValue(new DatabaseReference.CompletionListener() {
                                            @Override
                                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                            if (msj.getGrupo() == 0) {
                                // borrarDoc(chat);
                            } else {
                                chat = msj.getChat();
                                removerMiembroGrupo(chat, usuario);
                            }
                        }
                        // borrarFoto(usuario);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: "+error);
                    //  Toast.makeText(context, "Notifica el siguiente error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: "+e);
            // Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            //  toast.show();
        }
    }

    private void contarMiembrosGrupo(String chat) {
        ArrayList miembrps = new ArrayList();
        try {
            Query query = con.child("grupos").child(chat).child("admin");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            miembrps.add("a");
                        }
                        co++;
                        if (co == 2) {
                            if (miembrps.size() <= 1) {
                                eliminarGrupo(chat);
                            }
                        }
                    } else {
                        co++;
                        if (co == 2) {
                            if (miembrps.size() <= 1) {
                                eliminarGrupo(chat);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: "+error);
                    // Toast.makeText(context, "Notifica el siguiente error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: "+e);
            //Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            // toast.show();
        }
        try {
            Query query = con.child("grupos").child(chat).child("miembros");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            miembrps.add("a");
                        }
                        co++;
                        if (co == 2) {
                            if (miembrps.size() <= 1) {
                                eliminarGrupo(chat);
                            }
                        }
                    } else {
                        co++;
                        if (co == 2) {
                            if (miembrps.size() <= 1) {
                                eliminarGrupo(chat);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: "+error);
                    // Toast.makeText(context, "Notifica el siguiente error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: "+e);
            //  //Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            //  toast.show();
        }
    }

    private void eliminarGrupo(String chat) {
        try {
            con.child("grupos").child(chat).removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                }
            });
            con.child("chat").child(chat).removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                }
            });
            //eliminar ultimos msj
            try {
                Query query = con.child("ultimomsj").orderByChild("chat").equalTo(chat);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                con.child("ultimomsj").child(childSnapshot.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError de, DatabaseReference dr) {
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        JOptionPane.showMessageDialog(context, "Error: "+error);
                        //   Toast.makeText(context, "Notifica el siguiente error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                JOptionPane.showMessageDialog(context, "Error: "+e);

                // Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
                //  toast.show();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: "+e);
        }
        //  EliminarFotos(chat);
    }

    /* private void EliminarFotos(String chat) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference islandRef = storageRef.child("chats/" + chat);
        islandRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference item : listResult.getItems()) {
                            StorageReference isl = storageRef.child(item.getPath());
                            isl.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // File deleted successfully
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Uh-oh, an error occurred!
                    }
                });
    } */
    private void removerMiembroGrupo(String chat, String usuario) {
        try {
            Query query = con.child("grupos").child(chat).child("admin").orderByChild("nombre").equalTo(usuario);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // grupos msj;
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            con.child("grupos").child(chat).child("admin").child(childSnapshot.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError de, DatabaseReference dr) {
                                }
                            });
                            contarMiembrosGrupo(chat);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: "+error);
                    // Toast.makeText(context, "Notifica el siguiente error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: "+e);
            // Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            // toast.show();
        }
        try {
            Query query = con.child("grupos").child(chat).child("miembros").orderByChild("nombre").equalTo(usuario);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // grupos msj;
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            con.child("grupos").child(chat).child("miembros").child(childSnapshot.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError de, DatabaseReference dr) {
                                }
                            });
                            contarMiembrosGrupo(chat);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: "+error);
                    // Toast.makeText(context, "Notifica el siguiente error: " + error, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: "+e);
            //  Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            //  toast.show();
        }

    }

    /*private void borrarFoto(String user) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference isla = storageRef.child("fotoUser/" + user + ".jpg");
        isla.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // File deleted successfully
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
            }
        });

    }

    private void borrarDoc(String ruta) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference islandRef = storageRef.child("chats/" + ruta);
        islandRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference item : listResult.getItems()) {
                            StorageReference isl = storageRef.child(item.getPath());
                            isl.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // File deleted successfully
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Uh-oh, an error occurred!
                    }
                });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
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
