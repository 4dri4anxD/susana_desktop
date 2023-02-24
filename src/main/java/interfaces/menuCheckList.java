package interfaces;

import com.google.firebase.database.DatabaseReference;
import configuracion.info;
import disenos.colores;
import disenos.disenos;
import disenos.ventanas.configuracionVentana;
import helpers.windowClosing;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class menuCheckList extends JFrame {

    //declaracion de variables globales
    private DatabaseReference con;
    private int priv, alto, ancho;
    private Color colorSel, color1, color2;
    private String user, idioma;
    private JFrame context;

    public menuCheckList(DatabaseReference con, String user, int priv, String idioma) {
        initComponents();
        new configuracionVentana(this);
        this.con = con;
        this.priv = priv;
        this.user = user;
        this.idioma = idioma;
        context = this;
        iniciarDiseno();

        mostrar();
    }

    public void iniciarDiseno() {//decoracion de los componentes del frame
        colorSel = colores.getGrisSel();
        color1 = colores.getGris();
        color2 = colores.getGris2();

        lblTitulo.setHorizontalAlignment(JLabel.CENTER);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 1);
        new disenos().fondo(pnlCabecera, 3);

        new disenos().titulo(lblTitulo, 5);

        new disenos().botones(btnFinal, 3);
        new disenos().botones(btnCalidad, 3);
        new disenos().botones(btnEnvio, 4);
        new disenos().botones(btnPruebas, 4);
        new disenos().botones(btnAtras, 1);

        ancho = (int) btnCalidad.getWidth() / 2;
        alto = (int) btnCalidad.getHeight() / 2;

        ponerImg(btnCalidad, "img/quality.png");
        ponerImg(btnEnvio, "img/shipment.png");
        ponerImg(btnPruebas, "img/testing.png");
        ponerImg(btnFinal, "img/fin.png");
        ponerImg1(btnAtras, "img/atras2.png");

        btnAtras.setOpaque(true);
    }

    public void ponerImg(JButton b, String ruta) {//poner imagenes a los botones, modificando su tamano

        ImageIcon imagen = new ImageIcon(ruta);

        Image imgEscalada = imagen.getImage().getScaledInstance(ancho,
                alto, Image.SCALE_SMOOTH);
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
        btnAtras = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        pnlCuerpo = new javax.swing.JPanel();
        btnCalidad = new javax.swing.JButton();
        btnEnvio = new javax.swing.JButton();
        btnPruebas = new javax.swing.JButton();
        btnFinal = new javax.swing.JButton();

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

        btnAtras.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        lblTitulo.setText("Menu de checklists");

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCabeceraLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCabeceraLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlCuerpo.setLayout(new java.awt.GridLayout(2, 0, 2, 1));

        btnCalidad.setText("Calidad");
        btnCalidad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCalidad.setContentAreaFilled(false);
        btnCalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCalidad.setIconTextGap(10);
        btnCalidad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCalidadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCalidadMouseExited(evt);
            }
        });
        btnCalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalidadActionPerformed(evt);
            }
        });
        pnlCuerpo.add(btnCalidad);

        btnEnvio.setText("Envio");
        btnEnvio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEnvio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnvio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEnvio.setIconTextGap(10);
        btnEnvio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEnvio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEnvioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEnvioMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEnvioMouseReleased(evt);
            }
        });
        btnEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvioActionPerformed(evt);
            }
        });
        pnlCuerpo.add(btnEnvio);

        btnPruebas.setText("Pruebas");
        btnPruebas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPruebas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPruebas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPruebas.setIconTextGap(10);
        btnPruebas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPruebas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPruebasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPruebasMouseExited(evt);
            }
        });
        btnPruebas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPruebasActionPerformed(evt);
            }
        });
        pnlCuerpo.add(btnPruebas);

        btnFinal.setText("Final");
        btnFinal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFinal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinal.setIconTextGap(10);
        btnFinal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalMouseExited(evt);
            }
        });
        btnFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalActionPerformed(evt);
            }
        });
        pnlCuerpo.add(btnFinal);

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
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

    private void btnCalidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalidadMouseEntered
        // TODO add your handling code here:
        btnCalidad.setBackground(colorSel);
        ponerImg(btnCalidad, "img/quality1.png");
    }//GEN-LAST:event_btnCalidadMouseEntered

    private void btnCalidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalidadMouseExited
        // TODO add your handling code here:
        btnCalidad.setBackground(color1);
        ponerImg(btnCalidad, "img/quality.png");
    }//GEN-LAST:event_btnCalidadMouseExited

    private void btnCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalidadActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPlantillas(con, user, priv, idioma, 1).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCalidadActionPerformed

    private void btnEnvioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnvioMouseEntered
        // TODO add your handling code here:
        btnEnvio.setBackground(colorSel);
        ponerImg(btnEnvio, "img/shipment1.png");
    }//GEN-LAST:event_btnEnvioMouseEntered

    private void btnEnvioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnvioMouseExited
        // TODO add your handling code here:
        btnEnvio.setBackground(color2);
        ponerImg(btnEnvio, "img/shipment.png");
    }//GEN-LAST:event_btnEnvioMouseExited

    private void btnEnvioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnvioMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnvioMouseReleased

    private void btnEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvioActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPlantillas(con, user, priv, idioma, 3).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEnvioActionPerformed

    private void btnPruebasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPruebasMouseEntered
        // TODO add your handling code here:
        btnPruebas.setBackground(colorSel);
        ponerImg(btnPruebas, "img/testing1.png");
    }//GEN-LAST:event_btnPruebasMouseEntered

    private void btnPruebasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPruebasMouseExited
        // TODO add your handling code here:
        btnPruebas.setBackground(color2);
        ponerImg(btnPruebas, "img/testing.png");
    }//GEN-LAST:event_btnPruebasMouseExited

    private void btnPruebasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPruebasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPlantillas(con, user, priv, idioma, 2).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPruebasActionPerformed

    private void btnFinalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalMouseEntered
        // TODO add your handling code here:
        btnFinal.setBackground(colorSel);
        ponerImg(btnFinal, "img/fin1.png");
    }//GEN-LAST:event_btnFinalMouseEntered

    private void btnFinalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalMouseExited
        // TODO add your handling code here:
        btnFinal.setBackground(color1);
        ponerImg(btnFinal, "img/fin.png");
    }//GEN-LAST:event_btnFinalMouseExited

    private void btnFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPlantillas(con, user, priv, idioma, 4).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFinalActionPerformed

    private void pnlFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFondoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlFondoMousePressed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        new menuPrincipal(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       // TODO add your handling code here:
        new windowClosing(idioma,this);
    }//GEN-LAST:event_formWindowClosing

    private void mostrar() {
        btnFinal.setText("Final");
        if (idioma.equals("english")) {
            lblTitulo.setText("Choose a checklist");
            btnCalidad.setText("Quality");
            btnEnvio.setText("Shipment");
            btnPruebas.setText("Testing");

        } else {
            lblTitulo.setText("Seleccione una checklist");
            btnCalidad.setText("Calidad");
            btnEnvio.setText("Envio");
            btnPruebas.setText("Pruebas");

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCalidad;
    private javax.swing.JButton btnEnvio;
    private javax.swing.JButton btnFinal;
    private javax.swing.JButton btnPruebas;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlFondo;
    // End of variables declaration//GEN-END:variables
}
