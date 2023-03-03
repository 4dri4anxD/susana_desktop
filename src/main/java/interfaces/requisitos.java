package interfaces;

import com.google.firebase.database.DatabaseReference;
import configuracion.info;
import disenos.StretchIcon;
import disenos.ventanas.configEXTRAS;
import disenos.disenoTabla;
import disenos.disenos;
import helpers.back;
import helpers.checkUsers;
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

public class requisitos extends configEXTRAS {//clase para establecer los requisitos de cada proceso (se manda a llamar desde vistaPlantillas)

    //declaracion de variables globales
    private final DefaultTableModel modelo;
    private final String idioma, actividad;
    private final int pos, requisito, val;
    private final vistaPlantillas vista;
    private final LinkedHashMap<String, Integer> req, actireq;
    private final vistaPlantillasTT testing;
    private disenos disenos;
    private info info;

    public requisitos(String idioma, int pos, String actividad, int requisito, LinkedHashMap<String, Integer> actireq,
            vistaPlantillas vista, vistaPlantillasTT testing, int val) {//constructor
        initComponents();
        info=new info();
        disenos=new disenos();
       
        //poner icono
        //inicializacion de variables
        this.vista = vista;
        this.idioma = idioma;
        this.actividad = actividad;
        this.requisito = requisito;
        this.pos = pos;
        this.val = val;
        this.actireq = actireq;
        this.testing = testing;
        // this.lista = lista;
        req = new LinkedHashMap();
        modelo = (DefaultTableModel) tablaPermisos.getModel();

        iniciarDiseno();
        llenarArray();
        ponerInfo();
        mostrar();

    }

    private void llenarArray() {
        try {
            Set<String> lista = actireq.keySet();
            int x = 0;
            if (requisito > 0) {
                String numero = Integer.toBinaryString(requisito);
                x = numero.length() - 1;
                for (String key : lista) {
                    if (!key.equals(actividad)) {
                        try {
                            if (x < 0) {
                                req.put(key, 0);
                            } else {
                                if (numero.charAt(x) == '1') {
                                    if (!key.equals(actividad)) {
                                        req.put(key, 1);
                                    }
                                } else {
                                    req.put(key, 0);
                                }
                            }
                        } catch (Exception e) {

                        }
                    }
                    x--;
                }
            } else {
                for (String key : lista) {
                    if (!key.equals(actividad)) {
                        try {
                            req.put(key, 0);
                        } catch (Exception e) {

                        }
                    }
                    x--;
                }

            }

        } catch (Exception e) {
            System.out.println("Excepcion e: " + e);
        }
    }

    private void iniciarDiseno() {//decoracion de los componentes del frame
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        disenos.botones(btnAdd, 3);
        disenos.botones(btnAtras, 3);

        disenos.fondo(pnlFondo, 2);
        disenos.fondo(pnlCuerpo, 2);
        disenos.fondo(pnlCabecera, 3);
        disenos.fondo(pnlDer, 1);
        disenos.fondo(pnlIzq, 1);

        disenos.titulo(lblTitulo, 2);

        ponerImg(btnAdd, "img/check1.png");
        ponerImg(btnAtras, "img/atras2.png");

        new disenoTabla().cabecera(tablaPermisos);
    }

    public void ponerImg(JButton b, String ruta) {//poner imagenes a los botones
        b.setIcon(new StretchIcon(ruta));
      /*  ImageIcon imagen = new ImageIcon(ruta);
        Image imgEscalada = imagen.getImage().getScaledInstance(b.getWidth(),
                b.getHeight(), Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);*/
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

        lblTitulo.setText("Requisitos");

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                "Procesos", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
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
            tablaPermisos.getColumnModel().getColumn(0).setPreferredWidth(300);
            tablaPermisos.getColumnModel().getColumn(1).setPreferredWidth(10);
        }

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
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

        //todo esto se hace con una mascara de bits, es decir, a cada proceso se le asigna un numero binario
        if (tablaPermisos.isEditing()) {
            tablaPermisos.getCellEditor().stopCellEditing();//detenga la edicion para almacenar el valor
        }
        int req = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Boolean.parseBoolean(modelo.getValueAt(i, 1).toString())) {//obtiene si se selecciono un proceso o no
                //pos contiene la posicion de la fila la cual se selecciono para acceder a esta interfaz
                if (i < pos) {//si la fila que se esta analizando es menor a pos
                    //se convierte el numero de binario a decimal
                    req = (int) (req + Math.pow(2, i));
                } else {//si i es mayor a pos
                    //se convierte i+1 en decimal, porque van en orden, 1,01,11, etc, entonces como no aparece la que se selecciono pa entrar aqui
                    //se tiene que sumar 1, como si estuviera pa seguir el orden
                    int c = i + 1;
                    req = (int) (req + Math.pow(2, c));
                }

            }
        }
        //   String valor = "" + req; //se convierte el valor a string
        if (testing == null) {
            vista.setRequisito(actividad, req);//se guarda el valor del requisito
        } else {
            testing.setRequisito(actividad, req, val);//se guarda el valor del requisito
        }

        info.setXY(-1,-1, 0,0);
        this.setCursor(new Cursor(WAIT_CURSOR));
        this.dispose();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (new back().backConf(idioma, this)) {
            info.setXY(-1,-1,0,0);
            this.setCursor(new Cursor(WAIT_CURSOR));
            this.dispose();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tablaPermisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPermisosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tablaPermisosMouseClicked

    private void tablaPermisosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPermisosKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tablaPermisosKeyTyped

    private void ponerInfo() {//pone como seleccionados los procesos requeridos por el proceso que se selecciono para entrar aqui
        Set<String> actividades = req.keySet();
        for (String actividad : actividades) {
            if (req.get(actividad) == 1) {//si se tiene un 1 en la posicion de un proceso, quiere decir que ese proceso es un requisito por lo que se selecciona
                modelo.addRow(new Object[]{actividad, true});
            } else {
                modelo.addRow(new Object[]{actividad, false});//si tiene un 0, entonces no es requisito
            }
        }

    }

    private void mostrar() {
        lblTitulo.setText(actividad);
        if (idioma.equals("english")) {
            
            JTableHeader tableHeader = tablaPermisos.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Required");
            tableHeader.repaint();
        } else {
            
            JTableHeader tableHeader = tablaPermisos.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Requerido");
            tableHeader.repaint();
        }
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
