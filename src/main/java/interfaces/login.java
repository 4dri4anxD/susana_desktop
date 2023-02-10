package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import conexion.Connect;
import configuracion.xmlManagment;
import configuracion.info;
import disenos.ventanas.confiLogin;
import disenos.disenos;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.DEFAULT_CURSOR;
import static java.awt.Frame.TEXT_CURSOR;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import obtenerDatos.users;
import seguridad.encriptado;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/*
1-llamar al proceso como ps su nombre y un numero, ej. proceso1
2-Agregar variable que diga si esta duplicado y cuantas veces
3-dependiendo de la variable de arriba, calcular el porcentaje de completado de un proceso en base a sus actividades y a la cantidad de duplicaciones
4-estas duplicaciones se veran en actUS y proUS
debe haber opcion para borrar un proceso dividido a la hora de asignar trabajos

Para los check de las actividades que sea en procesos o actividades? yo diria que en procesos
1-Agregar variable en proUS que sea el estado del check
2-Agregar variable en proUS que sea la hora y fecha de inicio
3-Agregar variable en proUS que sea la hora y la fecha de termino
4-Agregar variable en proUS que sea el tiempo transcurrido
cuando este en pausa, se actualiza tiempo transcurrido, y cuando se vuelva a reanudar, la fecha de inicio cambia a esa hora
asi cuando finalice, se suma el tiempo transcurrido + la fecha de termino-fecha de inicio

reworks debe reiniciar todo esto


*/

public class login extends confiLogin {//frame con el login de la aplicacion

    //declaracion de variables globales
    char passwordChar;
    private String idioma;//idioma de la interfaz
    private DatabaseReference con;
    private boolean ing;

    public login(DatabaseReference con) {
        initComponents();

        iniciarDiseno();//decorar componentes de este frame

        ing = true;

  
        //estilizacion de la ventana
     //   new configuracionLogin(this);

        idioma = new xmlManagment().leerId();//se lee el idioma de la aplicacion, si es la primer vez que se ejecuta el codigo, crea el documento config.xml y le asigna espanol por defecto
        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            Esp();//cambia la interfaz a espanol
        }
        if (con == null) {//si no hay una conexion activa la crea y obtiene
            try {
                this.con = new Connect().getCon();
            } catch (IOException ex) {
                System.out.println("Ex: "+ex);
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.con = con;
        }

        txtPass.setEchoChar('*');
        passwordChar = txtPass.getEchoChar();//obtiene el valor del echochar
        txtPass.setEchoChar((char) 0);//le dice al campo de contrasena que en vez de puntos ponga las letras
        txtPass.setForeground(Color.LIGHT_GRAY);//pone como un 'hint' la palabra 'contrasena'
    }

    public void iniciarDiseno() {//estilizacion de los componentes del frame
        lblLogin.setHorizontalAlignment(JLabel.CENTER);
        lbl1.setHorizontalAlignment(JLabel.CENTER);
        lbl2.setHorizontalAlignment(JLabel.CENTER);
        lbl3.setHorizontalAlignment(JLabel.CENTER);
        txtUser.setHorizontalAlignment(JLabel.CENTER);
        txtPass.setHorizontalAlignment(JLabel.CENTER);

        new disenos().fondo(panelFondo, 1);
        new disenos().fondo(pnlCuerpo, 3);

        new disenos().titulo(lblLogin, 2);
        new disenos().titulo(lbl1, 1);
        new disenos().titulo(lbl2, 3);
        new disenos().titulo(lbl3, 3);

        new disenos().botones(btnGo, 2);

        new disenos().textoL(txtUser);
        new disenos().textoL(txtPass);

        txtUser.requestFocus();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        pnlCuerpo = new javax.swing.JPanel();
        txtPass = new javax.swing.JPasswordField();
        lblLogin = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        btnGo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelFondo.setLayout(null);

        lbl1.setText("SUSANA");
        panelFondo.add(lbl1);
        lbl1.setBounds(0, 4, 650, 60);

        lbl2.setText("System for UAV Systems");
        panelFondo.add(lbl2);
        lbl2.setBounds(-2, 64, 650, 40);

        lbl3.setText("and Aurelia Network App");
        panelFondo.add(lbl3);
        lbl3.setBounds(0, 104, 650, 40);

        txtPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPassFocusLost(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        lblLogin.setText("LOG IN");

        txtUser.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserFocusLost(evt);
            }
        });
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });

        btnGo.setText("Go");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCuerpoLayout.createSequentialGroup()
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCuerpoLayout.createSequentialGroup()
                            .addGap(112, 112, 112)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlCuerpoLayout.createSequentialGroup()
                            .addGap(228, 228, 228)
                            .addComponent(btnGo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        panelFondo.add(pnlCuerpo);
        pnlCuerpo.setBounds(20, 160, 610, 350);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void txtUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusGained
        // TODO add your handling code here:
        if (txtUser.getText().equals("Usuario") || txtUser.getText().equals("User")) {//si esta el 'hint' lo quita y pone el color de letra en negro
            txtUser.setText("");
            txtUser.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtUserFocusGained

    private void txtUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusLost
        // TODO add your handling code here:

        if (txtUser.getText().equals("")) {//si no se ha escrito nada vuelve a poner el hint
            txtUser.setText("Usuario");
            txtUser.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtUserFocusLost

    private void txtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusGained
        // TODO add your handling code here:
        if (txtPass.getText().equals("Contraseña") || txtPass.getText().equals("Password")) {//si esta el hint, lo quita y pone puntos en lugar de las letras
            txtPass.setText("");
            txtPass.setEchoChar(passwordChar);
            txtPass.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtPassFocusGained

    private void txtPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusLost
        // TODO add your handling code here:
        if (txtPass.getText().equals("")) {//si no se ha escrito nada, se ponen letras en lugar de puntos y se pone el hint
            txtPass.setEchoChar((char) 0);
            txtPass.setText("Contraseña");
            txtPass.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtPassFocusLost

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            txtPass.requestFocus();
        }
    }//GEN-LAST:event_txtUserKeyPressed


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        //cerrar aplicacion
        System.exit(0);

    }//GEN-LAST:event_formWindowClosing

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        // TODO add your handling code here:
        if (ing) {//ing es utilizado para asrgurar de que no se haga spam presionando el boton de ingresar
            new info().setXY(this.getX(), this.getY());
            ingresar();
        }
        ing = false;
    }//GEN-LAST:event_btnGoActionPerformed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            if (ing) {
                new info().setXY(this.getX(), this.getY());
                ingresar();
            }
            ing = false;
        }

    }//GEN-LAST:event_txtPassKeyPressed

    public void ingresar() {
        if (!txtUser.getText().equals("") && !txtPass.getText().equals("") && !txtUser.getText().equals("Usuario")
                && !txtUser.getText().equals("User") && !txtPass.getText().equals("Password")
                && !txtPass.getText().equals("Contraseña")) { //si se escribio algo en ambos campos

            //poner cursor de espera
            this.setCursor(new Cursor(WAIT_CURSOR));
            txtUser.setCursor(new Cursor(WAIT_CURSOR));
            txtPass.setCursor(new Cursor(WAIT_CURSOR));
            JFrame a = this;
            try {
                Query query = con.child("usuarios").orderByChild("user").equalTo(txtUser.getText().toLowerCase());//leer nodo usuarios, con el usuario dado
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot user : snapshot.getChildren()) {
                                try {
                                    users log = user.getValue(users.class);
                                    String pass = "";
                                    pass = new encriptado().decrypt(log.getPass());//desencripta la contrasena obtenida
                                    int priv = log.getPriv();
                                    if (txtPass.getText().toString().equals(pass)) {//si las contrasenas coinciden inicia sesion
                                        adelante(priv);
                                    } else {//si no
                                        ing = true;//habilita que se pueda presionar de nuevo el boton de ingresar
                                        a.setCursor(new Cursor(DEFAULT_CURSOR));
                                        txtUser.setCursor(new Cursor(TEXT_CURSOR));
                                        txtPass.setCursor(new Cursor(TEXT_CURSOR));
                                        if (idioma.equals("English")) {
                                            JOptionPane.showMessageDialog(a, "Wrong data");
                                        } else {

                                            JOptionPane.showMessageDialog(a, "Datos incorrectos");
                                        }
                                    }
                                } catch (Exception e) {
                                    //si hay algun error
                                    ing = true;
                                    a.setCursor(new Cursor(DEFAULT_CURSOR));
                                    txtUser.setCursor(new Cursor(TEXT_CURSOR));
                                    txtPass.setCursor(new Cursor(TEXT_CURSOR));
                                    System.out.println("Error: " + e);
                                }
                            }
                        } else {//si no existe el usuario en la bd
                            ing = true;//se habilita el boton de ingresar
                            a.setCursor(new Cursor(DEFAULT_CURSOR));
                            txtUser.setCursor(new Cursor(TEXT_CURSOR));
                            txtPass.setCursor(new Cursor(TEXT_CURSOR));
                            if (idioma.equals("English")) {
                                JOptionPane.showMessageDialog(a, "Wrong data");
                            } else {
                                JOptionPane.showMessageDialog(a, "Datos incorrectos");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        //si hay algun otro error por parte de la conexion
                        ing = true;
                        a.setCursor(new Cursor(DEFAULT_CURSOR));
                        txtUser.setCursor(new Cursor(TEXT_CURSOR));
                        txtPass.setCursor(new Cursor(TEXT_CURSOR));
                        System.out.println("Error: " + error);
                    }
                });
            } catch (Exception e) {
                ing = true;
                a.setCursor(new Cursor(DEFAULT_CURSOR));
                txtUser.setCursor(new Cursor(TEXT_CURSOR));
                txtPass.setCursor(new Cursor(TEXT_CURSOR));
                System.out.println("Error: " + e);

            }
        } else {//si no se llenen todos los campos
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(this, "Fill out all fields");
            } else {

                JOptionPane.showMessageDialog(this, "Llene todos los campos");
            }
        }
    }

    private void adelante(int priv) {//cierra este frame y abre el del menu principal
        new menuPrincipal(con, txtUser.getText().toLowerCase(), priv, idioma).setVisible(true);
        this.dispose();
    }

    private void ingles() {//componentes de la interfaz en ingles
        lblLogin.setText("SIGN IN");
        btnGo.setText("Login");
        txtUser.setText("User");
        txtPass.setText("Password");
        txtPass.setForeground(Color.LIGHT_GRAY);
        txtUser.setForeground(Color.LIGHT_GRAY);

    }

    private void Esp() {//componentes de la interfaz en espanol
        lblLogin.setText("INICIAR SESION");
        btnGo.setText("INGRESAR");
        txtUser.setText("Usuario");
        txtPass.setText("Contraseña");
        txtPass.setForeground(Color.LIGHT_GRAY);
        txtUser.setForeground(Color.LIGHT_GRAY);
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGo;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
