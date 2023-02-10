package interfaces;

import com.google.firebase.database.DatabaseReference;
import configuracion.info;
import datos.datos;
import disenos.configEXTRAS;
import disenos.configuracionExtras;
import disenos.disenoTabla;
import disenos.disenos;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class extrasVersiones extends configEXTRAS {//frame utilizado para mostrar y modificar: actividades relacionadas a un proceso,
    //versiones o extras relacionados a una plantilla
    //esta clase se llama desde vistaPlantillas

    //declaracion de variables globales
    private DatabaseReference con;
    private String user, idioma, proceso;
    private int priv, interfaz;
    private DefaultTableModel modelo;
    private ArrayList<String> eliminados;

    public extrasVersiones(DatabaseReference con, String user, int priv, String idioma, int interfaz, String proceso) {//constructor
        initComponents();

        modelo = (DefaultTableModel) tablaPermisos.getModel();

        //se pone el icono de la aplicacion en la taskBar y en el titulo de la ventana
        ImageIcon imagen = new ImageIcon(info.RUTA_IMAGEN);
        Image icono = imagen.getImage();
        this.setIconImage(icono);
        //se pone el titulo de la ventana con la version
        this.setTitle(info.VERSION);

        //inicializacion de variables
        eliminados = new ArrayList();
        this.con = con;
        this.user = user;
        this.priv = priv;
        this.idioma = idioma;
        this.interfaz = interfaz;
        this.proceso = proceso;

        //estilizar el frame
        new configuracionExtras(this);
        //estilizar componentes del frame
        iniciarDiseno();

        //poner la interfaz en el idioma seleccionado
        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }
        leer();//lee los extras/versiones/actividades

    }

    public void iniciarDiseno() {//estilizar componentes

        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAdd1, 3);
        new disenos().botones(btnAtras, 3);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnAdd, "img/check1.png");
        ponerImg(btnAdd1, "img/agregarProceso.png");
        ponerImg(btnAtras, "img/atras2.png");

        new disenoTabla().cabecera(tablaPermisos);
    }

    public void ponerImg(JButton b, String ruta) {//les pone una imagen a los botones
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

        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnAdd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (tablaPermisos.getColumnModel().getColumnCount() > 0) {
            tablaPermisos.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
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
        //boton atras
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        //Asociar los extras, actividades o versiones a la plantilla o proceso.

        //comprobar que actividad no se repite
        if (tablaPermisos.isEditing()) {
            tablaPermisos.getCellEditor().stopCellEditing();//detenga la edicion para almacenar el valor
        }
        int a = 0;
        int b = modelo.getRowCount();
        for (int i = 0; i < b; i++) {
            if (modelo.getValueAt(a, 0).toString().equals("")) {
                modelo.removeRow(a);//se eliminan filas vacias
                a--;
            }
            a++;
        }
        eliminarArray();
        if (llenarArray()) {

            new info().setXY(this.getX(), this.getY());
            this.setCursor(new Cursor(WAIT_CURSOR));
            this.dispose();//cerrar esta ventanas para volver a vistaPlantillas
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        // TODO add your handling code here:
        //agregar una fila a la tabla
        modelo.addRow(new Object[]{""});
        tablaPermisos.editCellAt(modelo.getRowCount() - 1, 0);
        Component aComp = tablaPermisos.getEditorComponent();
        aComp.requestFocus();

    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void tablaPermisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPermisosMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == 3) {//si se da click derecho sobre un fila, quiere decir que se pretende eliminar dicha fila
            String texto1, texto2, o1, o2;
            if (idioma.equals("English")) {
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
                    eliminados.add(modelo.getValueAt(tablaPermisos.getSelectedRow(), 0).toString());//agregar el contenido de la fila a los eliminados
                    modelo.removeRow(tablaPermisos.getSelectedRow());//eliminar fila
                } catch (NullPointerException e) {
                    if (idioma.equals("English")) {
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

    private void eliminarArray() {//eliminar extras, versiones o actividades de las variables estaticas las cuales se alimentaron de la bd
        switch (interfaz) {
            case 0://si se estan eliminando actividades relacionadas a un proceso
                for (int i = 0; i < eliminados.size(); i++) {
                    datos.getProcesosPlantilla().get(proceso).remove(eliminados.get(i));
                }
                break;
            case 1://si se estan eliminando los extras de una plantilla
                for (int i = 0; i < eliminados.size(); i++) {
                    datos.getExtras().remove(eliminados.get(i));
                }
                break;
            case 2://si se estan eliminando las versiones de una plantilla
                for (int i = 0; i < eliminados.size(); i++) {
                    datos.getVersiones().remove(eliminados.get(i));
                }
                break;
        }
    }

    private boolean llenarArray() {//se actualizan las variables que contienen los extras, versiones y actividades, las cuales contienen info de la bd
        boolean valido = true;
        String actividad = "";
        switch (interfaz) {
            case 0://si se agregaron actividades
                //comprobar que la actividad no existe en otro proceso
                ArrayList<String> x = new ArrayList();
                Set<String> keys = datos.getProcesosPlantilla().keySet();
                for (int i = 0; i < modelo.getRowCount(); i++) {//se leen todas las actividades y se guardan en un ArrayList
                    for (String key : keys) {
                        if (!key.equals(proceso)) {
                            for (int j = 0; j < datos.getProcesosPlantilla().get(key).size(); j++) {
                                if (datos.getProcesosPlantilla().get(key).get(j).toLowerCase().equals(modelo.getValueAt(i, 0).toString().toLowerCase())) {
                                    valido = false;
                                    actividad = modelo.getValueAt(i, 0).toString();
                                }
                            }
                        }

                    }
                    x.add(modelo.getValueAt(i, 0).toString());
                }
                if (valido) {
                    datos.setProcesosPlantilla(proceso, x);//se le asigna ese arrayList a un proceso
                } else {
                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(this, "Activities were not added because the activity:\n" + actividad + "\nis part of another process already");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se han agregado las actividades debido a que la actividad:\n" + actividad + "\nya se encuentra en otro proceso, cambiela e intente nuevamente");
                    }
                }
                break;
            case 1://si se agregaron extras
                datos.getExtras().clear();//se borra lo que contenga extras
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    datos.setExtras(modelo.getValueAt(i, 0).toString());//y se llena con lo que tenga la tabla
                }

                break;
            case 2://si se agregaron versiones
                datos.getVersiones().clear();//se borra lo que contenga versiones
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    datos.setVersiones(modelo.getValueAt(i, 0).toString());//y se llena con lo que tenga la tabla
                }
                break;
        }
        return valido;
    }

    private void leer() {//poner en la tabla la info de las variables que contienen la info de la bd
        switch (interfaz) {
            case 0://actividades
                for (int i = 0; i < datos.getProcesosPlantilla().get(proceso).size(); i++) {
                    modelo.addRow(new Object[]{datos.getProcesosPlantilla().get(proceso).get(i)});
                }
                break;
            case 1://extras
                for (int i = 0; i < datos.getExtras().size(); i++) {
                    modelo.addRow(new Object[]{datos.getExtras().get(i)});
                }
                break;
            case 2://versiones
                for (int i = 0; i < datos.getVersiones().size(); i++) {
                    modelo.addRow(new Object[]{datos.getVersiones().get(i)});
                }
                break;
        }

    }

    private void ingles() {//se pone la interfaz en ingles

        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        switch (interfaz) {
            case 0:
                lblTitulo.setText("Activities view");
                tableColumn.setHeaderValue("Activities");
                btnAdd1.setToolTipText("<html><b style='font-size: 12px;'>Add an activity</b></html>");
                btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Save activities assigned</b></html>");
                break;
            case 1:
                lblTitulo.setText("Extras view");
                tableColumn.setHeaderValue("Extras");
                btnAdd1.setToolTipText("<html><b style='font-size: 12px;'>Add an extra for this template</b></html>");
                btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Save extras</b></html>");
                break;
            case 2:
                lblTitulo.setText("Versions view");
                tableColumn.setHeaderValue("Versions");
                btnAdd1.setToolTipText("<html><b style='font-size: 12px;'>Add a version for this template</b></html>");
                btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Save versions</b></html>");
                break;
        }

        tableHeader.repaint();
    }

    private void esp() {//se pone la interfaz en espanol
        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        switch (interfaz) {
            case 0:
                lblTitulo.setText("Vista de actividades");
                tableColumn.setHeaderValue("Actividades");
                btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Guardar las actividades asignadas</b></html>");
                btnAdd1.setToolTipText("<html><b style='font-size: 12px;'>Agrega una actividad</b></html>");
                break;
            case 1:
                lblTitulo.setText("Vista de extras");
                tableColumn.setHeaderValue("Extras");
                btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Guardar los extras</b></html>");
                btnAdd1.setToolTipText("<html><b style='font-size: 12px;'>Agrega un extra para esta plantilla</b></html>");
                break;
            case 2:
                lblTitulo.setText("Vista de versiones");
                tableColumn.setHeaderValue("Versiones");
                btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Guardar las versiones</b></html>");
                btnAdd1.setToolTipText("<html><b style='font-size: 12px;'>Agrega una version para esta plantilla</b></html>");
                break;
        }
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
