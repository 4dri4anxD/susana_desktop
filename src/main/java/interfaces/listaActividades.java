
package interfaces;

import configuracion.info;
import disenos.ventanas.configEXTRAS;
import disenos.disenoTabla;
import disenos.disenos;
import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class listaActividades extends configEXTRAS {

    private String idioma;
    private DefaultTableModel modelo;
    private LinkedHashMap<String, String> actividades;
    private LinkedHashMap<String, Integer> completados;
    private vistaActividades1 va;
    private int tipo, seleccion;
    
    public listaActividades(String idioma, LinkedHashMap<String, String> act, LinkedHashMap<String, Integer> co, vistaActividades1 va, int tipo,int seleccion) {//LinkedHashMap<String, List<String>> hm, ArrayList<String> codProceso,ArrayList<String> procesos
        initComponents();
        //inicializacion de variables
        modelo = (DefaultTableModel) tablaPermisos.getModel();
        this.idioma = idioma;
        this.tipo = tipo;
        this.seleccion=seleccion;
        actividades = act;
        completados = co;
        this.va = va;

        if (idioma.equals("English")) {
            ingles();
        } else {
            esp();
        }
        iniciarDiseno();
        ponerTabla();
    }

    public void iniciarDiseno() {//decorar los componentes del frame
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);
        
        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);
        
        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnAdd, "img/check1.png");
        ponerImg(btnAtras, "img/atras2.png");

        new disenoTabla().cabecera(tablaPermisos);
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

        pnlFondo = new javax.swing.JPanel();
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPermisos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitulo.setText("Requisitos");

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
                "Procesos", "I", "P", "L"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
            tablaPermisos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tablaPermisos.getColumnModel().getColumn(1).setPreferredWidth(20);
            tablaPermisos.getColumnModel().getColumn(2).setPreferredWidth(20);
            tablaPermisos.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
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
        if (tablaPermisos.isEditing()) {
            tablaPermisos.getCellEditor().stopCellEditing();//detenga la edicion para almacenar el valor
        }
        //actualizar valores de terminados y calcular porcentaje(importante)
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Boolean.parseBoolean(modelo.getValueAt(i, 1).toString())) {
                completados.put(modelo.getValueAt(i, 0).toString(), 1);//actividades
            } else if (Boolean.parseBoolean(modelo.getValueAt(i, 2).toString())) {
                completados.put(modelo.getValueAt(i, 0).toString(), 2);
            } else if (Boolean.parseBoolean(modelo.getValueAt(i, 3).toString())) {
                completados.put(modelo.getValueAt(i, 0).toString(), 3);
            } else {
                completados.put(modelo.getValueAt(i, 0).toString(), 0);
            }
        }
        va.actualizarComp(completados,seleccion);//actualizar completados ,,, actividades
        this.dispose();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tablaPermisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPermisosMouseClicked
        // TODO add your handling code here:

        if (tablaPermisos.isEditing()) {
            tablaPermisos.getCellEditor().stopCellEditing();//detenga la edicion para almacenar el valor
        }
        
        int row = tablaPermisos.getSelectedRow();
        int column = tablaPermisos.getSelectedColumn();
        Set<String> keys = completados.keySet();//actividades
        ArrayList<String> cla = new ArrayList();
        cla.addAll(keys);
        int j = 0;
        int completo = 0;
        //actividades contiene la actividad y un  1 o 0, depende de si esta completada la actividad o no
        for (String key : keys) {//recorre las actividades
            if (j == row) {
                completo = completados.get(key);
            }
            j++;
        }
        boolean valido = true;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (row != i) {
                for (int k = 1; k < modelo.getColumnCount(); k++) {
                    if (Boolean.parseBoolean(modelo.getValueAt(i, k).toString())) {
                        if (completados.get(cla.get(i)) != 3) {
                            valido = false;
                        }

                    }

                }
            }
        }
        
        if (valido) {
            if (completo == 3) {
                modelo.setValueAt(false, row, 1);
                modelo.setValueAt(false, row, 2);
                modelo.setValueAt(true, row, 3);
            } else {
                switch (column) {
                    case 1:
                        modelo.setValueAt(false, row, 3);
                        modelo.setValueAt(false, row, 2);
                        break;
                    case 2:
                        modelo.setValueAt(false, row, 1);
                        modelo.setValueAt(false, row, 3);
                        break;
                    case 3:
                        modelo.setValueAt(false, row, 1);
                        modelo.setValueAt(false, row, 2);
                        break;
                }

            }
        } else {
            switch (completo) {
                case 1:
                    modelo.setValueAt(false, row, 3);
                    modelo.setValueAt(false, row, 2);
                    break;
                case 2:
                    modelo.setValueAt(false, row, 1);
                    modelo.setValueAt(false, row, 3);
                    break;
                case 3:
                    modelo.setValueAt(false, row, 1);
                    modelo.setValueAt(false, row, 2);
                    break;
                case 0:
                    modelo.setValueAt(false, row, 1);
                    modelo.setValueAt(false, row, 2);
                    modelo.setValueAt(false, row, 3);
                    break;
            }
        }
    }//GEN-LAST:event_tablaPermisosMouseClicked

    private void tablaPermisosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPermisosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaPermisosKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void ponerTabla() {//poner las actividades del proceso seleccionado en la tabla
        Set<String> keys = completados.keySet();//actividades
        int i = 0;
        for (String key : keys) {//recorre las actividades
            if (completados.get(key) == 1) {//si equivale 1, quiere decir que esta completado
                modelo.addRow(new Object[]{key, true, false, false});
            } else if (completados.get(key) == 2) {//0 es que no esta completada
                // i++;
                modelo.addRow(new Object[]{key, false, true, false});
            } else if (completados.get(key) == 3) {
                i++;
                modelo.addRow(new Object[]{key, false, false, true});
            } else {
                modelo.addRow(new Object[]{key, false, false, false});
            }

        }
        if (i == keys.size()) {//si ya se completaron todas las actividades, inhabilitar hacer algo
            tablaPermisos.setEnabled(false);
        }
        if (tipo == 0) {//tipo=0 quiere decir que esta en modo visualizacion por parte de un admin o sueprvisor
            tablaPermisos.setEnabled(false);
            btnAdd.setVisible(false);
        }
    }

    private void ingles() {//poner la interfaz en ingles
        lblTitulo.setText("Activities list");
        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Activities");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("S");
        tableColumn = tableColumnModel.getColumn(2);
        tableColumn.setHeaderValue("P");
        tableColumn = tableColumnModel.getColumn(3);
        tableColumn.setHeaderValue("D");
        tableHeader.repaint();
    }

    private void esp() {//poner la interfaz en espanol
        lblTitulo.setText("Lista de actividades");
        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Actividades");
        tableHeader.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
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
