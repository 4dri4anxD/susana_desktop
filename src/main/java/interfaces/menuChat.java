package interfaces;

import com.google.firebase.database.DatabaseReference;
import configuracion.xmlManagment;
import configuracion.info;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import helpers.windowClosing;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import obtenerDatos.keys;
import obtenerDatos.users;

public class menuChat extends JFrame {//no esta en uso

    private DatabaseReference con;
    private int priv;
    private String user, idioma;
    DefaultListModel modelo;
    int y = 0;
    JPanel panel;
    GridBagConstraints gbc;

    public menuChat(DatabaseReference con, String user, int priv, String idioma) {
        initComponents();
        new configuracionVentana(this);
        this.con = con;
        this.user = user;
        this.priv = priv;
        this.idioma = idioma;

        iniciarDiseno();
        

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 0);
        modelo = new DefaultListModel();

        panel.setMaximumSize(new Dimension(600, 1500));
        panelS.setMaximumSize(new Dimension(600, 1500));

        users u = new users("Hola", 1, "User");
        modelo.addElement(u);
        keys k = new keys("           aaaaa", "usuario");
        modelo.addElement(k);

        idioma = new xmlManagment().leerId();//se lee el idioma de la aplicacion, si es la primer vez que se ejecuta el codigo, crea el documento config.xml y le asigna espanol por defecto
        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }

        leerChats();

    }

    public void iniciarDiseno() {
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        txtBuscar.setHorizontalAlignment(JLabel.CENTER);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);
        new disenos().botones(btnAdj, 3);
        new disenos().botones(btnSend, 5);

        new disenos().botones(btnBuscar, 1);

        new disenos().textoL(txtBuscar);
        new disenos().texto1(txtMsj);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCuerpo1, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlMsj, 1);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);
        txtMsj.setLineWrap(true);

        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnAdd, "img/addUser1.png");
        ponerImg(btnAtras, "img/atras2.png");
        ponerImg(btnBuscar, "img/buscar1.png");
        ponerImg(btnAdj, "img/adj1.png");
        ponerImg(btnSend, "img/send1.png");

        tablaUsers.requestFocus();
        txtBuscar.setForeground(Color.LIGHT_GRAY);

        new disenoTabla().cabecera(tablaUsers);

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
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsers = new javax.swing.JTable();
        pnlCuerpo1 = new javax.swing.JPanel();
        btnSend = new javax.swing.JButton();
        pnlMsj = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        txtMsj = new javax.swing.JTextArea();
        btnAdj = new javax.swing.JButton();
        panelS = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
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

        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
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

        tablaUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chats"
            }
        ));
        tablaUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tablaUsers);

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );

        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtMsj.setColumns(20);
        txtMsj.setRows(5);
        scroll.setViewportView(txtMsj);

        javax.swing.GroupLayout pnlMsjLayout = new javax.swing.GroupLayout(pnlMsj);
        pnlMsj.setLayout(pnlMsjLayout);
        pnlMsjLayout.setHorizontalGroup(
            pnlMsjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMsjLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdj, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlMsjLayout.setVerticalGroup(
            pnlMsjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMsjLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMsjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAdj, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCuerpo1Layout = new javax.swing.GroupLayout(pnlCuerpo1);
        pnlCuerpo1.setLayout(pnlCuerpo1Layout);
        pnlCuerpo1Layout.setHorizontalGroup(
            pnlCuerpo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuerpo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCuerpo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelS)
                    .addGroup(pnlCuerpo1Layout.createSequentialGroup()
                        .addComponent(pnlMsj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCuerpo1Layout.setVerticalGroup(
            pnlCuerpo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCuerpo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCuerpo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlMsj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCuerpo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCuerpo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPrincipal(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        panel.setBackground(Color.white);
        String s;
        if (y % 3 == 0) {
            s = "Un supertext";
        } else {
            s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean eu nulla urna. Donec sit amet risus nisl, a porta enim. Quisque luctus, ligula eu scelerisque gravida, tellus quam vestibulum urna, ut aliquet sapien purus sed erat. Pellentesque consequat vehicula magna, eu aliquam magna interdum porttitor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed sollicitudin sapien non leo tempus lobortis. Morbi semper auctor ipsum, a semper quam elementum a. Aliquam eget sem metus.";
        }
        String html = "<html><body style='width: %1spx'>%1s";
        JLabel f = new JLabel(String.format(html, 380, s));
        if (y % 2 == 0) {
            String texto = "<html>You: <br>" + s;
            f.setHorizontalAlignment(JLabel.LEFT);
            f.setBackground(Color.lightGray);
            f.setText(String.format(html, 380, texto));

        } else {
            String texto = "<html>Each other: <br>" + s;
            f.setHorizontalAlignment(JLabel.LEFT);
            f.setBackground(Color.white);
            f.setText(String.format(html, 380, texto));
        }
        new disenos().titulo(f, 3);
        f.setForeground(Color.black);
        f.setOpaque(true);
        gbc.gridy = y;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        if (s.length() > 40) {
            gbc.ipadx = 90;
        } else if (s.length() > 20 && s.length() < 41) {
            gbc.ipadx = 150;
        } else {
            gbc.ipadx = 370;
        }
        panel.add(f, gbc);
        y++;
        panelS.setViewportView(panel);
    }//GEN-LAST:event_btnSendActionPerformed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new windowClosing(idioma,this);
    }//GEN-LAST:event_formWindowClosing

    private void leerChats() {

    }

    private void ingles() {
        txtBuscar.setText("Search");
        lblTitulo.setText("Chats menu");
    }

    private void esp() {
        txtBuscar.setText("Buscar");
        lblTitulo.setText("Menu de chats");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdj;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane panelS;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlCuerpo1;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JPanel pnlMsj;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaUsers;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtMsj;
    // End of variables declaration//GEN-END:variables
}
