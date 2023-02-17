
package interfaces;

import com.google.firebase.database.DatabaseReference;
import configuracion.xmlManagment;
import configuracion.info;
import disenos.colores;
import disenos.ventanas.configuracionVentana;
import disenos.disenos;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class menuPrincipal extends JFrame {//clase que contiene el menu principal

    //declaracion de variable globales
    private int alto, ancho;
    private Color colorSel, color1, color2;
    private DatabaseReference con;
    private String user, idioma;
    private int priv;

    public menuPrincipal(DatabaseReference con, String user, int priv, String idioma) {//constructor
        initComponents();
        new configuracionVentana(this);
       
        //inicializacion de variables
        this.con = con;
        this.user = user;
        this.priv = priv;
        this.idioma = idioma;
        
        iniciarDiseno();
        idioma = new xmlManagment().leerId();//se lee el idioma de la aplicacion, si es la primer vez que se ejecuta el codigo, crea el documento config.xml y le asigna espanol por defecto
        
        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }
        
        permisos();
        lblBienvenida.setText(lblBienvenida.getText() + " " + user);
        
        
    }

    public void iniciarDiseno() {//decoracion de los componentes del frame
        colorSel = colores.getGrisSel();
        color1 = colores.getGris();
        color2 = colores.getGris2();
        
        lblBienvenida.setHorizontalAlignment(JLabel.LEFT);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 1);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlTitulo, 1);

        new disenos().titulo(lblBienvenida, 5);
        new disenos().titulo(lblTitulo, 2);

        new disenos().botones(btnUsers, 3);
        new disenos().botones(btnAct, 3);
        new disenos().botones(btnPlantillas, 4);
        new disenos().botones(btnChat, 4);
        new disenos().botones(btnOpciones, 1);
        new disenos().botones(btnAtras, 1);
        
        alto = btnAct.getHeight();
        ancho = btnAct.getWidth();

        ponerImg(btnAct, "img/mantenimiento1.png");
        ponerImg(btnChat, "img/dialogo1.png");
        ponerImg(btnPlantillas, "img/portapapeles1.png");
        ponerImg(btnUsers, "img/grupo2.png");
        ponerImg1(btnOpciones, "img/conf2.png");
        ponerImg1(btnAtras, "img/atras2.png");
        
        btnOpciones.setOpaque(true);
        btnAtras.setOpaque(true);
    }

    public void ponerImg(JButton b, String ruta) {//poner imagenes a los botones, modificando su tamano
        ImageIcon imagen = new ImageIcon(ruta);
        int an = (int) (ancho / 2);
        Image imgEscalada = imagen.getImage().getScaledInstance(an,
                alto / 2, Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);
        b.setIconTextGap(20);

    }

    public void ponerImg1(JButton b, String ruta) {//poner imagenes a los botones
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
        lblBienvenida = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnOpciones = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlCuerpo = new javax.swing.JPanel();
        btnAct = new javax.swing.JButton();
        btnChat = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnPlantillas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frame1"); // NOI18N
        setPreferredSize(new java.awt.Dimension(965, 752));

        pnlFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlFondoMousePressed(evt);
            }
        });

        lblBienvenida.setText("Hola de nuevo,");

        btnAtras.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnOpciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnOpciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
                .addComponent(btnOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btnOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblTitulo.setText("Menu principal");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
        );

        pnlCuerpo.setLayout(new java.awt.GridLayout(2, 0, 2, 1));

        btnAct.setText("Actividades");
        btnAct.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct.setContentAreaFilled(false);
        btnAct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAct.setIconTextGap(10);
        btnAct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActMouseExited(evt);
            }
        });
        btnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActActionPerformed(evt);
            }
        });
        pnlCuerpo.add(btnAct);

        btnChat.setText("Chat");
        btnChat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnChat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnChat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChat.setIconTextGap(10);
        btnChat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChatMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnChatMouseReleased(evt);
            }
        });
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });
        pnlCuerpo.add(btnChat);

        btnUsers.setText("Usuarios");
        btnUsers.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUsers.setIconTextGap(10);
        btnUsers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUsersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUsersMouseExited(evt);
            }
        });
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });
        pnlCuerpo.add(btnUsers);

        btnPlantillas.setText("Plantillas");
        btnPlantillas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPlantillas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPlantillas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlantillas.setIconTextGap(10);
        btnPlantillas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPlantillas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPlantillasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPlantillasMouseExited(evt);
            }
        });
        btnPlantillas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlantillasActionPerformed(evt);
            }
        });
        pnlCuerpo.add(btnPlantillas);

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
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

    private void btnOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcionesActionPerformed
        // TODO add your handling code here:
        new opciones(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnOpcionesActionPerformed

    private void btnChatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMouseEntered
        // TODO add your handling code here:
        btnChat.setBackground(colorSel);
        ponerImg(btnChat, "img/dialogo2.png");
    }//GEN-LAST:event_btnChatMouseEntered

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        new login(con).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnChatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChatMouseReleased

    private void btnChatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChatMouseExited
        // TODO add your handling code here:
        btnChat.setBackground(color2);
        ponerImg(btnChat, "img/dialogo1.png");
    }//GEN-LAST:event_btnChatMouseExited

    private void btnActMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActMouseEntered
        // TODO add your handling code here:
        btnAct.setBackground(colorSel);
        ponerImg(btnAct, "img/mantenimiento2.png");
    }//GEN-LAST:event_btnActMouseEntered

    private void btnActMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActMouseExited
        // TODO add your handling code here:
        btnAct.setBackground(color1);
        ponerImg(btnAct, "img/mantenimiento1.png");
    }//GEN-LAST:event_btnActMouseExited

    private void btnUsersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsersMouseEntered
        // TODO add your handling code here:
        btnUsers.setBackground(colorSel);
        ponerImg(btnUsers, "img/grupo3.png");
    }//GEN-LAST:event_btnUsersMouseEntered

    private void btnUsersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsersMouseExited
        // TODO add your handling code here:
        btnUsers.setBackground(color2);
        ponerImg(btnUsers, "img/grupo2.png");
    }//GEN-LAST:event_btnUsersMouseExited

    private void btnPlantillasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlantillasMouseEntered
        // TODO add your handling code here:
        btnPlantillas.setBackground(colorSel);
        ponerImg(btnPlantillas, "img/portapapeles2.png");
    }//GEN-LAST:event_btnPlantillasMouseEntered

    private void btnPlantillasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlantillasMouseExited
        // TODO add your handling code here:
        btnPlantillas.setBackground(color1);
        ponerImg(btnPlantillas, "img/portapapeles1.png");
    }//GEN-LAST:event_btnPlantillasMouseExited

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuUsuarios(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnPlantillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlantillasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        new menuPlantillas(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPlantillasActionPerformed

    private void btnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        String texto1, texto2, o1, o2, o3;
        if (idioma.equals("English")) {
            texto1 = "What do you want to do?";
            texto2 = "Select Action";
            o1 = "Add/Edit work";
            o2 = "See works";
            o3 = "Work";
        } else {
            texto1 = "¿Qué quiere hacer?";
            texto2 = "Seleccionar accion Acción";
            o1 = "Agregar/Editar trabajo";
            o2 = "Ver trabajos";
            o3 = "Trabajar";
        }
        switch (priv) {
            case 1://se se es el superUsuario, se le da la opcion de ver, agregar o editar trabajos
                Object[] options = {o1, o2};
                int opc = JOptionPane.showOptionDialog(this, texto1, texto2,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                if (opc == 0) {//agregar o editar trabajos
                    new MenuAgregarModificarOrdenes(con, user, priv, idioma).setVisible(true);
                    this.dispose();

                } else if (opc == 1) {//ver progreso de algun dron
                    new menuActividades1(con, user, priv, idioma, 0).setVisible(true);
                    this.dispose();
                }
                break;
            case 2://si se es un administrador, se le da la opcion de ver, agregar o editar trabajos
                Object[] options3 = {o1, o2};
                opc = JOptionPane.showOptionDialog(this, texto1, texto2,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options3, options3[0]);
                if (opc == 0) {//agregar o editar trabajos
                    new MenuAgregarModificarOrdenes(con, user, priv, idioma).setVisible(true);
                    this.dispose();

                } else if (opc == 1) {//ver progreso de algun dron
                    new menuActividades1(con, user, priv, idioma, 0).setVisible(true);
                    this.dispose();
                }
                break;
            case 3://si se es un supervisor, se le da la opcion de ver, agregar, editar trabajos o trabajar
                Object[] options2 = {o1, o2, o3};
                switch (JOptionPane.showOptionDialog(this, texto1, texto2,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options2, options2[0])) {
                    case 0://agregar o editar trabajos
                        new MenuAgregarModificarOrdenes(con, user, priv, idioma).setVisible(true);
                        this.dispose();
                        break;
                    case 1://ver progreso de algun dron
                        new menuActividades1(con, user, priv, idioma, 0).setVisible(true);
                        this.dispose();
                        break;
                    case 2://trabajar
                        new menuActividades1(con, user, priv, idioma, 1).setVisible(true);
                        this.dispose();
                        break;
                }
                break;
            case 4://si se es trabajador
                new menuActividades1(con, user, priv, idioma, 1).setVisible(true); //ver trabajos
                this.dispose();
                break;
        }
    }//GEN-LAST:event_btnActActionPerformed

    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatActionPerformed
        // TODO add your handling code here:
        if (idioma.equals("English")) {
            JOptionPane.showMessageDialog(this, "Not available");
        } else {
            JOptionPane.showMessageDialog(this, "No disponible");
        }
    }//GEN-LAST:event_btnChatActionPerformed

    private void pnlFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFondoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlFondoMousePressed

    private void permisos() {
        switch (priv) {
            case 1://superusuario
                break;
            case 2://admin
                break;
            case 3://supervisor
                btnPlantillas.setEnabled(false);
                btnUsers.setEnabled(false);
                break;
            case 4: //trabajador
                btnPlantillas.setEnabled(false);
                btnUsers.setEnabled(false);
                break;
            case 5: //vendedor
                btnPlantillas.setEnabled(false);
                btnUsers.setEnabled(false);
                btnAct.setEnabled(false);
                break;
        }
    }

    private void ingles() {//pone la interfaz en ingles
        lblBienvenida.setText("Welcome back,");
        lblTitulo.setText("Principal menu");
        btnAct.setText("Assigments");
        btnUsers.setText("Users");
        btnPlantillas.setText("Templates");
        btnChat.setText("Chat");
        btnPlantillas.setToolTipText("<html><b style='font-size: 12px;'>Press to view/edit/delete/add a list of activities related to the manufacture of a drone</b></html>");
        btnUsers.setToolTipText("<html><b style='font-size: 12px;'>Press to view/edit/delete/add users</b></html>");
        if (priv == 4) {
            btnAct.setToolTipText("<html><b style='font-size: 12px;'>Press to view activities to do</b></html>");
        } else {
            btnAct.setToolTipText("<html><b style='font-size: 12px;'>Press to asign activities to workers or view activies asigned </b></html>");
        }
    }

    private void esp() {//pone la interfaz en espanol
        lblBienvenida.setText("Hola de nuevo,");
        lblTitulo.setText("Menu principal");
        btnAct.setText("Asignaciones");
        btnUsers.setText("Usuarios");
        btnPlantillas.setText("Plantillas");
        btnChat.setText("Chat");
        btnPlantillas.setToolTipText("<html><b style='font-size: 12px;'>Presiona para ver/editar/eliminar/agregar una lista de actividades relacionadas a la fabricacion de un dron</b></html>");
        btnUsers.setToolTipText("<html><b style='font-size: 12px;'>Presiona para ver/editar/eliminar/agregar usuarios</b></html>");
        if (priv == 4) {
            btnAct.setToolTipText("<html><b style='font-size: 12px;'>Presiona para ver actividades por hacer </b></html>");
        } else {
            btnAct.setToolTipText("<html><b style='font-size: 12px;'>Presiona para asignar actividades a los trabajadores o ver actividades asignadas </b></html>");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAct;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnOpciones;
    private javax.swing.JButton btnPlantillas;
    private javax.swing.JButton btnUsers;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlTitulo;
    // End of variables declaration//GEN-END:variables
}
