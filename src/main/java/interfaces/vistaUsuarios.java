package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import disenos.colores;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import helpers.back;
import helpers.windowClosing;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import obtenerDatos.users;
import seguridad.encriptado;

public class vistaUsuarios extends JFrame {

    private DatabaseReference con;
    private String user, idioma, nombre;
    private int priv;
    private char passwordChar;
    private JFrame context;
    private DefaultTableModel modelo;//modelo de la tabla
    private int carga;// cont;
    private boolean valido;

    public vistaUsuarios(DatabaseReference con, String user, int priv, String idioma, String nombre) {
        initComponents();
        new configuracionVentana(this);

        carga = 0;
        this.con = con;
        valido = true;
        modelo = (DefaultTableModel) tablaPermisos.getModel();
        this.user = user;
        this.priv = priv;
        this.idioma = idioma;
        this.nombre = nombre;
        iniciarDiseno();
        context = this;
        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }
        cmbTipos.setSelectedIndex(3);
        if (!nombre.equals("")) {
            leer();
        } else {
            llenarArray(3);
            carga = 1;
        }

    }

    public void iniciarDiseno() {
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        txtPass.setEchoChar('*');
        passwordChar = txtPass.getEchoChar();//obtiene el valor del echochar
        new disenos().botones(btnVer, 3);
        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);

        new disenos().textoL1(txtUser);
        new disenos().textoL1(txtPass);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().titulo(lblTitulo, 2);
        new disenos().titulo(lblUser, 6);
        new disenos().titulo(lblPass, 6);
        new disenos().titulo(lblPermisos, 6);

        new disenos().selector(cmbTipos);

        ponerImg(btnAdd, "img/guardar1.png");
        ponerImg(btnAtras, "img/atras2.png");
        ponerImg(btnVer, "img/ojo.png");
        txtUser.requestFocus();

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
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        lblPermisos = new javax.swing.JLabel();
        cmbTipos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPermisos = new javax.swing.JTable();
        btnVer = new javax.swing.JButton();
        txtPass = new javax.swing.JPasswordField();

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(617, Short.MAX_VALUE))
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

        lblUser.setText("Nombre:");

        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserFocusLost(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserKeyTyped(evt);
            }
        });

        lblPass.setText("Password:");

        lblPermisos.setText("Permisos de usuario:");

        cmbTipos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTiposItemStateChanged(evt);
            }
        });

        tablaPermisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Un trabajador puede"
            }
        ));
        jScrollPane1.setViewportView(tablaPermisos);

        btnVer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnVerMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVerMouseReleased(evt);
            }
        });
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        txtPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPassFocusLost(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(pnlCuerpoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPermisos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbTipos, 0, 310, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPass, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
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
        if (new back().backConf(idioma, this)) {
            new info().setXY(this.getX(), this.getY());
            this.setCursor(new Cursor(WAIT_CURSOR));
            new menuUsuarios(con, user, priv, idioma).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void txtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusGained
        // TODO add your handling code here:
        txtPass.setForeground(colores.negro);
    }//GEN-LAST:event_txtPassFocusGained

    private void txtPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusLost
        // TODO add your handling code here:      
        JTextField t = txtPass;
        try {
            if (t.getText().length() == 0) {
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                valido = true;
            } else if (t.getText().length() > 25) {
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                valido = false;
            } else {
                t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                valido = true;
            }
        } catch (Exception e) {
            t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            valido = false;
        }
    }//GEN-LAST:event_txtPassFocusLost

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerMouseClicked

    private void btnVerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerMouseReleased
        // TODO add your handling code here:
        txtPass.setEchoChar(passwordChar);
    }//GEN-LAST:event_btnVerMouseReleased

    private void btnVerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerMouseExited

    private void btnVerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerMousePressed
        // TODO add your handling code here:
        //  txtPass.setEchoChar(passwordChar);
        txtPass.setEchoChar((char) 0);
    }//GEN-LAST:event_btnVerMousePressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (nombre.equals("")) {
            insert();
        } else {
            update();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cmbTiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTiposItemStateChanged
        // TODO add your handling code here:
        if (carga != 0) {
            llenarArray(cmbTipos.getSelectedIndex());
        }
    }//GEN-LAST:event_cmbTiposItemStateChanged

    private void txtUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusGained
        // TODO add your handling code here:
        txtUser.setForeground(colores.negro);
    }//GEN-LAST:event_txtUserFocusGained

    private void txtUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusLost
        // TODO add your handling code here:
        JTextField t = txtUser;
        try {
            if (t.getText().length() == 0) {
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                valido = true;
            } else if (t.getText().length() > 25) {
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                valido = false;
            } else {
                t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                valido = true;
            }
        } catch (Exception e) {
            t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            valido = false;
        }
    }//GEN-LAST:event_txtUserFocusLost

    private void txtUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyTyped
        // TODO add your handling code here:
        if (txtUser.getText().length() == 25) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUserKeyTyped

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        // TODO add your handling code here:
        if (txtPass.getText().length() == 25) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPassKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new windowClosing(idioma, this);
    }//GEN-LAST:event_formWindowClosing

    private void update() {
        Query query = con.child("usuarios").orderByChild("user").equalTo(nombre).limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String id = childSnapshot.getKey();
                        try {
                            String pass = new encriptado().encrypt(txtPass.getText().toString());
                            int priv1;
                            String permiso = cmbTipos.getSelectedItem().toString();
                            switch (permiso) {
                                case "Administrador":
                                    priv1 = 2;
                                    break;
                                case "Trabajador":
                                    priv1 = 4;
                                    break;
                                case "Supervisor":
                                    priv1 = 3;
                                    break;
                                case "Vendedor":
                                    priv1 = 5;
                                    break;
                                case "Administrator":
                                    priv1 = 2;
                                    break;
                                case "Worker":
                                    priv1 = 4;
                                    break;
                                case "Seller":
                                    priv1 = 5;
                                    break;
                                default:
                                    priv1 = 0;
                            }
                            users user = new users(pass, priv1, nombre);

                            con.child("usuarios").child(id).setValue(user, new CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError de, DatabaseReference dr) {
                                    if (idioma.equals("English")) {
                                        JOptionPane.showMessageDialog(context, "User edited");
                                    } else {
                                        JOptionPane.showMessageDialog(context, "Usuario modificado");
                                    }
                                    cambiar();
                                }
                            });

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(context, "Error: " + e);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                JOptionPane.showMessageDialog(context, "Error: " + error);
            }
        });
    }

    private void cambiar() {
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuUsuarios(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }

    private void insert() {
        if (!txtUser.getText().equals("") && !txtPass.getText().equals("")) {
            try {
                String pass = new encriptado().encrypt(txtPass.getText().toString());
                int priv1;
                String permiso = cmbTipos.getSelectedItem().toString();
                switch (permiso) {
                    case "Administrador":
                        priv1 = 2;
                        break;
                    case "Trabajador":
                        priv1 = 4;
                        break;
                    case "Supervisor":
                        priv1 = 3;
                        break;
                    case "Vendedor":
                        priv1 = 5;
                        break;
                    case "Administrator":
                        priv1 = 2;
                        break;
                    case "Worker":
                        priv1 = 4;
                        break;
                    case "Seller":
                        priv1 = 5;
                        break;
                    default:
                        priv1 = 0;
                }
                users user = new users(pass, priv1, txtUser.getText().toString().toLowerCase());
                con.child("usuarios").push().setValue(user, new CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError de, DatabaseReference dr) {
                        if (idioma.equals("English")) {
                            JOptionPane.showMessageDialog(context, "User added");
                        } else {
                            JOptionPane.showMessageDialog(context, "Usuario agregado");
                        }
                        cambiar();
                    }
                });
            } catch (Exception e) {
                JOptionPane.showMessageDialog(context, "Error: " + e);
            }

        } else {
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Fill out all fields");
            } else {
                JOptionPane.showMessageDialog(context, "Llene todos los campos");
            }
        }
    }

    private void llenarArray(int sel) {
        int cant = modelo.getRowCount();
        try {

            for (int i = 0; i < cant; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {

        }
        ArrayList<String> info = new ArrayList();
        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        if (idioma.equals("English")) {
            switch (sel) {
                case 0:
                    info.add("Assign activities to workers");
                    info.add("Delete, modify and view all activities");
                    info.add("Create, modify, delete and view drone templates");
                    info.add("View, delete, modify and add users");
                    info.add("View and reply messages");
                    tableColumn.setHeaderValue("An administator can");
                    break;
                case 1:
                    info.add("Assign activities to workers");
                    info.add("Delete, modify and view all activities");
                    info.add("Create, modify, delete and view drone templates");
                    info.add("View and reply messages");
                    tableColumn.setHeaderValue("A supervisor can");
                    break;
                case 2:
                    info.add("Generate orders");
                    info.add("View and reply messages");
                    tableColumn.setHeaderValue("A seller can");
                    break;
                case 3:
                    info.add("View and mark activities assigned to him/her as completed");
                    info.add("View and reply messages");
                    tableColumn.setHeaderValue("A worker can");
                    break;
                case 4:
                    info.add("Assign activities to workers");
                    info.add("Delete, modify and view all activities");
                    info.add("Create, modify, delete and view drone templates");
                    info.add("View, delete, modify and add users");
                    info.add("View and reply messages");
                    info.add("Super user cannot be deleted");
                    tableColumn.setHeaderValue("Super user can");
                    break;
            }

        } else {
            switch (sel) {
                case 0:
                    info.add("Asignar actividades a los trabajadores");
                    info.add("Eliminar, modificar y ver todas las actividades");
                    info.add("Crear, modificar, eliminar y ver plantillas de drones");
                    info.add("Ver, eliminar, modificar y agregar usuarios");
                    info.add("Ver y responder mensajes");
                    tableColumn.setHeaderValue("Un administrador puede");
                    break;
                case 1:
                    info.add("Asignar actividades a los trabajadores");
                    info.add("Eliminar, modificar y ver todas las actividades");
                    info.add("Crear, modificar, eliminar y ver plantillas de drones");
                    info.add("Ver y responder mensajes");
                    tableColumn.setHeaderValue("Un supervisor puede puede");
                    break;
                case 2:
                    info.add("Generar ordenes");
                    info.add("Ver y responder mensajes");
                    tableColumn.setHeaderValue("Un vendedor puede");
                    break;
                case 3:
                    info.add("Ver y marcar actividades asignadas a el como terminadas");
                    info.add("Ver y responder mensajes");
                    tableColumn.setHeaderValue("Un trabajador puede");
                    break;
                case 4:
                    info.add("Asignar actividades a los trabajadores");
                    info.add("Eliminar, modificar y ver todas las actividades");
                    info.add("Crear, modificar, eliminar y ver plantillas de drones");
                    info.add("Ver, eliminar, modificar y agregar usuarios");
                    info.add("Ver y responder mensajes");
                    info.add("El superusuario no puede ser eliminado");
                    tableColumn.setHeaderValue("El superusuario puede");
                    break;
            }

        }
        for (int i = 0; i < info.size(); i++) {
            modelo.addRow(new Object[]{info.get(i)});//y la pone en la tabla
        }
        tableHeader.repaint();
    }

    private void leer() {
        try {
            Query query = con.child("usuarios").orderByChild("user").equalTo(nombre);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            int tipo = 0;
                            try {
                                users log = null;
                                log = user.getValue(users.class);
                                String pass = new encriptado().decrypt(log.getPass());
                                int priv1 = log.getPriv();
                                txtUser.setText(nombre);
                                txtPass.setText(pass);
                                JTableHeader tableHeader = tablaPermisos.getTableHeader();
                                TableColumnModel tableColumnModel = tableHeader.getColumnModel();
                                TableColumn tableColumn = tableColumnModel.getColumn(0);
                                switch (priv1) {
                                    case 1://superuser
                                        if (idioma.equals("English")) {
                                            cmbTipos.addItem("Superuser");
                                        } else {
                                            cmbTipos.addItem("Superusuario");
                                        }
                                        cmbTipos.setSelectedIndex(4);
                                        tipo = 4;
                                        if (idioma.equals("English")) {
                                            tableColumn.setHeaderValue("Super user can");
                                        } else {
                                            tableColumn.setHeaderValue("El superusuario puede");
                                        }
                                        break;
                                    case 2://administrador
                                        cmbTipos.setSelectedIndex(0);
                                        tipo = 0;
                                        if (idioma.equals("English")) {
                                            tableColumn.setHeaderValue("An administator can");
                                        } else {
                                            tableColumn.setHeaderValue("Un administrador puede");
                                        }
                                        break;
                                    case 3://supervisor
                                        cmbTipos.setSelectedIndex(1);
                                        tipo = 1;
                                        if (idioma.equals("English")) {
                                            tableColumn.setHeaderValue("A supervisor can");
                                        } else {
                                            tableColumn.setHeaderValue("Un supervisor puede puede");
                                        }
                                        break;
                                    case 4://trabajador
                                        cmbTipos.setSelectedIndex(3);
                                        tipo = 3;
                                        if (idioma.equals("English")) {
                                            tableColumn.setHeaderValue("A worker can");
                                        } else {
                                            tableColumn.setHeaderValue("Un trabajador puede");
                                        }
                                        break;
                                    case 5://vendedor
                                        cmbTipos.setSelectedIndex(2);
                                        tipo = 2;
                                        if (idioma.equals("English")) {
                                            tableColumn.setHeaderValue("A seller can");
                                        } else {
                                            tableColumn.setHeaderValue("Un vendedor puede");
                                        }
                                        break;
                                }
                                tableHeader.repaint();
                                txtUser.setEnabled(false);
                                if (nombre.equals("admin")) {
                                    cmbTipos.setEnabled(false);
                                }

                            } catch (Exception e) {
                            }
                            llenarArray(tipo);
                        }
                        carga = 1;
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        } catch (Exception e) {
        }
    }

    private void ingles() {
        lblTitulo.setText("User view");
        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("A worker can");
        tableHeader.repaint();
        lblUser.setText("Name: ");
        lblPass.setText("Password: ");
        lblPermisos.setText("User permissions: ");
        cmbTipos.addItem("Administrator");
        cmbTipos.addItem("Supervisor");
        cmbTipos.addItem("Seller");
        cmbTipos.addItem("Worker");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Save user</b></html>");
        btnVer.setToolTipText("<html><b style='font-size: 12px;'>Show password</b></html>");
    }

    private void esp() {
        lblTitulo.setText("Vista de usuario");
        lblUser.setText("Nombre: ");
        lblPass.setText("Contraseña: ");
        lblPermisos.setText("Permisos de usuario: ");
        cmbTipos.addItem("Administrador");
        cmbTipos.addItem("Supervisor");
        cmbTipos.addItem("Vendedor");
        cmbTipos.addItem("Trabajador");
        btnVer.setToolTipText("<html><b style='font-size: 12px;'>Mostrar contraseña</b></html>");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Guardar usuario</b></html>");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbTipos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPermisos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tablaPermisos;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
