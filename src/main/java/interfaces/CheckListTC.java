package interfaces;

import alerts.alert;
import com.google.firebase.database.DatabaseReference;
import configuracion.info;
import datos.temporalStorage;
import disenos.StretchIcon;
import disenos.colores;
import disenos.ventanas.configEXTRAS;
import disenos.disenoTabla;
import disenos.disenos;
import disenos.enableActivityTable;
import disenos.ventanas.configuracionVentana;
import helpers.back;
import helpers.checkUsers;
import helpers.windowClosing;
import java.awt.Cursor;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CheckListTC extends JFrame {

    private String idioma, plantilla, user;
    private DefaultTableModel modelo;
    private DatabaseReference con;
    private int priv, modo, serie;
    private disenos disenos;
    private temporalStorage storage;
    private ArrayList<String> actividades, mensajes;
    private LinkedHashMap<String, ArrayList<String>> comentarios;
    private ArrayList<Boolean> aprobado, revsol, habilitar;
    private CheckListTC context;
    private boolean val;
    private info info;

    // private alert alerta;
    public CheckListTC(DatabaseReference con, String user, int priv, String idioma, int serie, String plantilla, int modo) {//LinkedHashMap<String, List<String>> hm, ArrayList<String> codProceso,ArrayList<String> procesos
        initComponents();

        //inicializacion de variables
        new configuracionVentana(this);
        //   modelo = (DefaultTableModel) tblActividades.getModel();
        // alerta=new alert();
        info=new info();
        context = this;
        this.idioma = idioma;
        this.modo = modo;
        this.plantilla = plantilla;
        this.serie = serie;
        this.user = user;
        this.con = con;
        this.priv = priv;
        comentarios = new LinkedHashMap();
        aprobado = new ArrayList();
        revsol = new ArrayList();
        actividades = new ArrayList();
        mensajes = new ArrayList();
        val = true;
        habilitar = new ArrayList();

        storage = new temporalStorage();
        disenos = new disenos();

        aprobado.addAll(storage.getAprobadoTC());
        revsol.addAll(storage.getRevsolTC());
        comentarios.putAll(storage.getComentariosTC());
        actividades.addAll(storage.getUbicacionTC());
        mensajes.addAll(storage.getMensajeTC());

        getPermitidos();
        procesosTabla();

        iniciarDiseno();
        ponerTabla();
        mostrar();

        if (modo == 1) {
            btnAdd.setVisible(false);

        }

    }

    private void procesosTabla() {
        String t[] = {"Actividades", "Solucionado", "Aprobado"};
        modelo = new DefaultTableModel(null, t) {//se crea un modelo para inhabilitar la edicion  de filas con procesos ya completados
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (columnIndex == 1 || columnIndex == 2) {
                    if (modo == 1) {
                        return false;
                    } else {
                        if (habilitar.get(rowIndex)) {
                            return true;
                        }
                        return false;
                    }
                } else {
                    return false;
                }
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Object.class;
                }
                return Boolean.class;
            }
        };
        tblActividades.setModel(modelo);
        tblActividades.setDefaultRenderer(Object.class, new enableActivityTable(habilitar));

        tblActividades.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    if (habilitar.get(table.getSelectedRow())) {
                        String titulo, cuerpo;
                        if (idioma.equals("english")) {
                            titulo = "Message";
                            cuerpo = "Write a comment";
                        } else {
                            titulo = "Mensaje";
                            cuerpo = "Escriba un comentario";
                        }
                        String mensaje = mensajes.get(row);
                        if (modo == 1) {
                            if (mensaje.equals("")) {
                                if (idioma.equals("english")) {
                                    JOptionPane.showMessageDialog(context, "There's no comments written by workers");
                                } else {
                                    JOptionPane.showMessageDialog(context, "No hay comentarios hechos por el trabajador");
                                }

                            } else {
                                JOptionPane.showMessageDialog(context, mensaje);
                            }

                        } else {
                            String resp = JOptionPane.showInputDialog(context, cuerpo, mensaje);
                            if (resp != null) {
                                if (!resp.equals("")) {
                                    mensajes.set(row, resp);
                                }
                            }
                        }

                    }

                }
            }
        });
    }

    private void getPermitidos() {
        int i = 0;
        LinkedHashMap<String, ArrayList<String>> procesosRequeridos = new LinkedHashMap();
        ArrayList<Integer> requisitos = new ArrayList();
        requisitos.addAll(storage.getRequisitosTC());
        for (Integer req : requisitos) {
            ArrayList<String> g = new ArrayList();
            String b = Integer.toBinaryString(req);//se convierte el numero de "requisito" en binario
            for (int k = 0; k < b.length(); k++) {//se recorren los bits de requisito
                if (b.charAt(k) == '1') {//si bit leido es un 1
                    g.add(storage.getUbicacionTC().get(((b.length() - 1) - k)));
                }
            }
            procesosRequeridos.put(actividades.get(i), g);//se coloca el nombre del proceso actual, y la lista de nombres de los procesos requeridos para poder empezar a hacer este
            i++;
        }
        for (String key : procesosRequeridos.keySet()) {
            if (procesosRequeridos.get(key).size() > 0) {
                boolean permit = true;
                for (String value : procesosRequeridos.get(key)) {
                    int index = storage.getUbicacionTC().indexOf(value);
                    if (!storage.getRevsolTC().get(index) || !storage.getAprobadoTC().get(index)) {
                        permit = false;
                    }
                }
                habilitar.add(permit);
                // permitido.put(key, permit);
            } else {
                //  permitido.put(key, true);
                habilitar.add(true);
            }
        }

    }

    public void iniciarDiseno() {//decorar los componentes del frame

        tblActividades.setBackground(colores.grisTenue);
        tblActividades.setSelectionBackground(colores.grisTenue);

        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        disenos.botones(btnAdd, 3);
        disenos.botones(btnAtras, 3);
        new disenos().botones(btnCheckComment, 3);
        // disenos.botones(btnCom, 3);

        disenos.fondo(pnlFondo, 2);
        disenos.fondo(pnlCuerpo, 2);
        disenos.fondo(pnlCabecera, 3);
        disenos.fondo(pnlDer, 1);
        disenos.fondo(pnlIzq, 1);

        disenos.titulo(lblTitulo, 2);

        ponerImg(btnAdd, "img/check1.png");
        ponerImg(btnAtras, "img/atras2.png");
        ponerImg(btnCheckComment, "img/adj1.png");

        new disenoTabla().cabecera(tblActividades);
    }

    public void ponerImg(JButton b, String ruta) {//poner imagenes a los botones
        b.setIcon(new StretchIcon(ruta));
       /* ImageIcon imagen = new ImageIcon(ruta);
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
        btnCheckComment = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActividades = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitulo.setText("Check calidad");

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

        btnCheckComment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCheckComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckCommentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCheckComment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCheckComment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        pnlCuerpo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlCuerpoComponentResized(evt);
            }
        });

        tblActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Procesos", "Solucionado", "Aprobado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblActividadesMouseClicked(evt);
            }
        });
        tblActividades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblActividadesKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblActividades);
        if (tblActividades.getColumnModel().getColumnCount() > 0) {
            tblActividades.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblActividades.getColumnModel().getColumn(1).setPreferredWidth(20);
            tblActividades.getColumnModel().getColumn(2).setPreferredWidth(20);
        }

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
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
        for (int i = 0; i < modelo.getRowCount(); i++) {
            revsol.set(i, Boolean.parseBoolean(modelo.getValueAt(i, 1).toString()));
            aprobado.set(i, Boolean.parseBoolean(modelo.getValueAt(i, 2).toString()));
        }
        storage.setRevsolTC(revsol);
        storage.setAprobadoTC(aprobado);
        storage.setMensajeTC(mensajes);
        // new info().setXY(this.getX(), this.getY());
        info.setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new vistaCompletarOrden(con, user, priv, idioma, serie, plantilla, modo).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (new back().backConf(idioma, this)) {
            //  new info().setXY(this.getX(), this.getY());
            info.setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.setCursor(new Cursor(WAIT_CURSOR));
            new vistaCompletarOrden(con, user, priv, idioma, serie, plantilla, modo).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tblActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblActividadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblActividadesMouseClicked

    private void tblActividadesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblActividadesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblActividadesKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new windowClosing(idioma, this);
    }//GEN-LAST:event_formWindowClosing

    private void btnCheckCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckCommentActionPerformed
        // TODO add your handling code here:
        //Abre nueva ventana
        try {
           // new info().setXY(this.getX(), this.getY());
           info.setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            if (tblActividades.getSelectedRow() == -1) {
                if (idioma.equals("english")) {
                    JOptionPane.showMessageDialog(context, "Select an activity");
                } else {
                    JOptionPane.showMessageDialog(context, "Seleccione una actividad");
                }
            } else {
                if (comentarios.get(actividades.get(tblActividades.getSelectedRow())).size() == 0) {
                    //no hay
                } else {
                    new addComentarios(user, priv, idioma, 5, actividades.get(tblActividades.getSelectedRow()), comentarios, null).setVisible(true);
                }
            }

        } catch (Exception e) {
            if (idioma.equals("english")) {
                JOptionPane.showMessageDialog(context, "There's no comments about this activity");
                // alerta.show("Warning", "Select an activity", idioma, false);
            } else {
                JOptionPane.showMessageDialog(context, "No hay comentarios acerca de esta actividad");
                //  alerta.show("Advertencia", "Seleccione una actividad", idioma, false);
            }
        }
    }//GEN-LAST:event_btnCheckCommentActionPerformed

    private void pnlCuerpoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCuerpoComponentResized
        // TODO add your handling code here:
        if (val) {//De una sola ejecucion
            int anchoPanel = (int) (pnlCuerpo.getWidth() / 10);
            TableColumnModel tableColumnModel = tblActividades.getColumnModel();
            tableColumnModel.getColumn(0).setMaxWidth((anchoPanel * 6));
            tableColumnModel.getColumn(1).setMaxWidth((anchoPanel * 2));
            tableColumnModel.getColumn(2).setMaxWidth((anchoPanel * 2));
            val = false;
        }
    }//GEN-LAST:event_pnlCuerpoComponentResized

    private void ponerTabla() {//poner las actividades del proceso seleccionado en la tabla
        for (int i = 0; i < actividades.size(); i++) {
            modelo.addRow(new Object[]{actividades.get(i), revsol.get(i), aprobado.get(i)});
        }
    }

    private void mostrar() {
        if (idioma.equals("english")) {
            lblTitulo.setText("Quality check");
            JTableHeader tableHeader = tblActividades.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Activities");
            tableColumn = tableColumnModel.getColumn(1);
            tableColumn.setHeaderValue("Solved");
            tableColumn = tableColumnModel.getColumn(2);
            tableColumn.setHeaderValue("Aproved");
            tableHeader.repaint();
        } else {
            lblTitulo.setText("Revision de calidad");
            JTableHeader tableHeader = tblActividades.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Actividades");
            tableHeader.repaint();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCheckComment;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tblActividades;
    // End of variables declaration//GEN-END:variables
}
