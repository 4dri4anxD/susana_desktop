package alerts;

import disenos.disenos;
import disenos.ventanas.configAlert;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class alert extends JFrame {

    private disenos disenos;
    private boolean resultado;

    public alert() {
        initComponents();

    }

    public void show(String title, String text, String language, boolean showCancel) {
        resultado = false;
        new configAlert(this);
        disenos = new disenos();
        setTitleText(title);
        setInfo(text);
        iniciarDiseno();
        mostrar(language);
        btnCancel.setVisible(showCancel);
        this.setVisible(true);
        
       // return false;
    }

    public void iniciarDiseno() {
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblInfo.setHorizontalAlignment(JLabel.CENTER);

        disenos.fondo(pnlTitulo, 3);
        disenos.fondo(pnlFondo, 2);
        disenos.fondo(pnlCuerpo, 2);
        disenos.fondo(pnlInfo, 2);
        disenos.fondo(pnlBotones, 2);

        disenos.titulo(lblTitulo, 3);
        disenos.titulo(lblInfo, 7);

        disenos.botones(btnOk, 3);
        disenos.botones(btnCancel, 3);

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
        java.awt.GridBagConstraints gridBagConstraints;

        pnlFondo = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlCuerpo = new javax.swing.JPanel();
        pnlInfo = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        pnlBotones = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setType(java.awt.Window.Type.POPUP);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        pnlFondo.setLayout(new java.awt.GridBagLayout());

        pnlTitulo.setPreferredSize(new java.awt.Dimension(0, 0));
        pnlTitulo.setLayout(new java.awt.GridBagLayout());

        lblTitulo.setText("Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        pnlTitulo.add(lblTitulo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        pnlFondo.add(pnlTitulo, gridBagConstraints);

        pnlCuerpo.setLayout(new java.awt.GridBagLayout());

        pnlInfo.setPreferredSize(new java.awt.Dimension(0, 0));

        lblInfo.setText("Info");

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCuerpo.add(pnlInfo, gridBagConstraints);

        pnlBotones.setPreferredSize(new java.awt.Dimension(0, 0));

        btnOk.setText("Ok");
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotonesLayout.createSequentialGroup()
                .addContainerGap(354, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        pnlCuerpo.add(pnlBotones, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.5;
        pnlFondo.add(pnlCuerpo, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        resultado = true;
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        resultado = false;
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formFocusLost

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TODO add your handling code here:
        this.requestFocus();
    }//GEN-LAST:event_formWindowLostFocus

    public boolean getResultado() {
        return resultado;
    }

    private void setTitleText(String title) {
        lblTitulo.setText(formattedText(title));
    }

    private void setInfo(String info) {
        lblInfo.setText(formattedText(info));
    }

    private String formattedText(String texto) {
        texto = "<html> <center>" + texto + "</center> </html>";
        return texto;
    }

    private void mostrar(String idioma) {
        if (idioma.equals("english")) {
            btnOk.setText("Continue");
            btnCancel.setText("Cancel");
        } else {
            btnOk.setText("Continuar");
            btnCancel.setText("Cancelar");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlTitulo;
    // End of variables declaration//GEN-END:variables
}
