/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import com.google.firebase.database.DatabaseReference;
import configuracion.info;
import disenos.StretchIcon;
import disenos.ventanas.configEXTRAS;
import disenos.disenoTabla;
import disenos.disenos;
import disenos.putIcon;
import helpers.back;
import helpers.checkUsers;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class addComentarios extends configEXTRAS {

    //declaracion de variables globales
    private String user, idioma, proceso;
    private int priv, interfaz;
    private DefaultTableModel modelo;
    private crearTC creartc;
    private LinkedHashMap<String, ArrayList<String>> comentarios;
    private disenos disenos;
    private info info;
    private putIcon icon;

    public addComentarios(String user, int priv, String idioma, int interfaz, String proceso, LinkedHashMap<String, ArrayList<String>> comentarios,
            crearTC creartc) {
        initComponents();
        this.creartc = creartc;
        modelo = (DefaultTableModel) tablaPermisos.getModel();
        this.comentarios = comentarios;
        info=new info();
        icon=new putIcon();
        disenos=new disenos();
        this.user = user;
        this.priv = priv;
        this.idioma = idioma;
        this.interfaz = interfaz;
        this.proceso = proceso;

        iniciarDiseno();
        fill();

        if (interfaz == 5) {
            btnAdd.setVisible(false);
            btnAdd1.setVisible(false);
            tablaPermisos.setEnabled(false);
        }

        //poner la interfaz en el idioma seleccionado
        if (idioma.equals("english")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }
    }

    public void iniciarDiseno() {//estilizar componentes

        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        disenos.botones(btnAdd, 3);
        disenos.botones(btnAdd1, 3);
        disenos.botones(btnAtras, 3);

        disenos.fondo(pnlFondo, 2);
        disenos.fondo(pnlCuerpo, 2);
        disenos.fondo(pnlCabecera, 3);
        disenos.fondo(pnlDer, 1);
        disenos.fondo(pnlIzq, 1);

        disenos.titulo(lblTitulo, 2);

        icon.setIcon(btnAdd, "img/check1.png");
        icon.setIcon(btnAdd1, "img/agregarProceso.png");
        icon.setIcon(btnAtras, "img/atras2.png");
        


        new disenoTabla().cabecera(tablaPermisos);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnAdd1 = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPermisos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnAdd1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        tablaPermisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Actividad"
            }
        ));
        tablaPermisos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPermisosMouseClicked(evt);
            }
        });
        tablaPermisos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaPermisosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPermisos);

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        //Asociar los extras, actividades o versiones a la plantilla o proceso.

        //comprobar que actividad no se repite
        if (tablaPermisos.isEditing()) {
            tablaPermisos.getCellEditor().stopCellEditing();//detenga la edicion para almacenar el valor
        }
        int a = 0;
        int b = modelo.getRowCount();
        ArrayList<String> comments = new ArrayList();
        for (int i = 0; i < b; i++) {
            if (modelo.getValueAt(a, 0).toString().equals("")) {
                modelo.removeRow(a);//se eliminan filas vacias
                a--;
            } else {
                if (!comments.contains(modelo.getValueAt(a, 0).toString())) {
                    comments.add(modelo.getValueAt(a, 0).toString());
                }
            }
            a++;
        }
        comentarios.put(proceso, comments);
        creartc.setComentarios(comentarios);
        info.setXY(-1,-1, 0,0);
        this.setCursor(new Cursor(WAIT_CURSOR));
        this.dispose();//cerrar esta ventanas para volver a vistaPlantillas 
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        // TODO add your handling code here:
        //agregar una fila a la tabla
        modelo.addRow(new Object[]{""});
        tablaPermisos.editCellAt(modelo.getRowCount() - 1, 0);
        Component aComp = tablaPermisos.getEditorComponent();
        aComp.requestFocus();
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        //boton atras
        if (new back().backConf(idioma, this)) {
            info.setXY(-1,-1, 0,0);
            this.setCursor(new Cursor(WAIT_CURSOR));
            this.dispose();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tablaPermisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPermisosMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == 3) {//si se da click derecho sobre un fila, quiere decir que se pretende eliminar dicha fila
            String texto1, texto2, o1, o2;
            if (idioma.equals("english")) {
                texto1 = "Are you sure you want to delete the selected item?";
                texto2 = "Confirm Action";
                o1 = "Yes";
                o2 = "No";
            } else {
                texto1 = "¿Seguro que quiere eliminar el objeto seleccionado?";
                texto2 = "Confirmar Acción";
                o1 = "Si";
                o2 = "No";
            }

            Object[] options = {o1, o2};
            if (JOptionPane.showOptionDialog(this, texto1, texto2,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]) == 0) {
                try {
                    // eliminados.add(modelo.getValueAt(tablaPermisos.getSelectedRow(), 0).toString());//agregar el contenido de la fila a los eliminados
                    modelo.removeRow(tablaPermisos.getSelectedRow());//eliminar fila
                } catch (NullPointerException e) {
                    if (idioma.equals("english")) {
                        JOptionPane.showMessageDialog(this, "Select an item to delete");
                    } else {
                        JOptionPane.showMessageDialog(this, "Seleccione un objeto para eliminar");
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaPermisosMouseClicked

    private void tablaPermisosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPermisosKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tablaPermisosKeyTyped

    private void fill() {
        if (comentarios.get(proceso) != null) {
            for (String comentario : comentarios.get(proceso)) {
                modelo.addRow(new Object[]{comentario});
            }
        }
    }

    private void ingles() {//se pone la interfaz en ingles

        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        lblTitulo.setText(proceso);
        tableColumn.setHeaderValue("Comments");
        btnAdd1.setToolTipText("<html><b style='font-size: 12px;'>Add a comment</b></html>");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Save comments</b></html>");

        tableHeader.repaint();
    }

    private void esp() {//se pone la interfaz en espanol
        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        lblTitulo.setText(proceso);
        tableColumn.setHeaderValue("Comentarios");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Guardar comentarios</b></html>");
        btnAdd1.setToolTipText("<html><b style='font-size: 12px;'>Agrega un comentario</b></html>");

        tableHeader.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnAtras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tablaPermisos;
    // End of variables declaration//GEN-END:variables
}
