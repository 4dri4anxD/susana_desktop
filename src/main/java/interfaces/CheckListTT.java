package interfaces;

import com.google.firebase.database.DatabaseReference;
import configuracion.info;
import datos.temporalStorage;
import disenos.colores;
import disenos.disenoTabla;
import disenos.disenos;
import disenos.enableActivityTable;
import disenos.ventanas.configuracionVentana;
import helpers.back;
import helpers.checkUsers;
import helpers.windowClosing;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CheckListTT extends JFrame {

    private String idioma, plantilla, user;
    private DefaultTableModel modelo, modelo1;
    private DatabaseReference con;
    private int priv, modo, serie;
    private disenos disenos;
    private temporalStorage storage;
    private ArrayList<String> actividades, mensajes, mensajes1, rendimiento, ponderacion;
    private ArrayList<Boolean> realizado, aprobacion, habilitar, habilitar1;
    private boolean val;

    private CheckListTT context;

    public CheckListTT(DatabaseReference con, String user, int priv, String idioma, int serie, String plantilla, int modo) {
        initComponents();
        new configuracionVentana(this);
        // modelo1 = (DefaultTableModel) tblRendimiento.getModel();
        context = this;
        this.idioma = idioma;
        this.modo = modo;
        this.plantilla = plantilla;
        this.serie = serie;
        this.user = user;
        this.con = con;
        this.priv = priv;

        val = true;
        habilitar1 = new ArrayList();
        habilitar = new ArrayList();
        actividades = new ArrayList();
        mensajes = new ArrayList();
        realizado = new ArrayList();
        aprobacion = new ArrayList();
        mensajes1 = new ArrayList();
        rendimiento = new ArrayList();
        ponderacion = new ArrayList();

        storage = new temporalStorage();
        disenos = new disenos();

        actividades.addAll(storage.getActividadesTT());
        mensajes.addAll(storage.getComentarioTT());
        realizado.addAll(storage.getRealizadoTT());
        aprobacion.addAll(storage.getAprobacionTT());
        rendimiento.addAll(storage.getRendimientoTT());
        mensajes1.addAll(storage.getComentarioTT1());
        ponderacion.addAll(storage.getPonderacionTT());

        getPermitidos();
        procesosTabla();
        getPermitidos1();
        procesosTabla1();

        // actividades.addAll(storage.getActividadesTT());
        //  mensajes.addAll(storage.getComentarioTT());
        iniciarDiseno();
        ponerTabla();
        mostrar();

        if (modo == 1) {
            btnAdd.setVisible(false);
        }

    }

    private void procesosTabla() {
        String t[] = {"Actividades", "Realizado", "Aprobacion"};
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
        requisitos.addAll(storage.getRequisitosTT());
        for (Integer req : requisitos) {
            ArrayList<String> g = new ArrayList();
            String b = Integer.toBinaryString(req);//se convierte el numero de "requisito" en binario
            for (int k = 0; k < b.length(); k++) {//se recorren los bits de requisito
                if (b.charAt(k) == '1') {//si bit leido es un 1
                    g.add(storage.getActividadesTT().get(((b.length() - 1) - k)));
                }
            }
            procesosRequeridos.put(actividades.get(i), g);//se coloca el nombre del proceso actual, y la lista de nombres de los procesos requeridos para poder empezar a hacer este
            i++;
        }
        for (String key : procesosRequeridos.keySet()) {
            if (procesosRequeridos.get(key).size() > 0) {
                boolean permit = true;
                for (String value : procesosRequeridos.get(key)) {
                    int index = storage.getActividadesTT().indexOf(value);
                    if (!storage.getRealizadoTT().get(index) || !storage.getAprobacionTT().get(index)) {
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

    private void procesosTabla1() {
        String t[] = {"Rendimiento en vuelo", "Ponderacion"};
        modelo1 = new DefaultTableModel(null, t) {//se crea un modelo para inhabilitar la edicion  de filas con procesos ya completados
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (columnIndex == 1) {
                    if (habilitar1.get(rowIndex)) {
                        return true;
                    }
                    return false;
                } else {
                    return false;
                }
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return Object.class;
            }
        };
        tblRendimiento.setModel(modelo1);
        tblRendimiento.setDefaultRenderer(Object.class, new enableActivityTable(habilitar1));

        tblRendimiento.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    if (habilitar1.get(table.getSelectedRow())) {
                        String titulo, cuerpo;
                        if (idioma.equals("english")) {
                            titulo = "Message";
                            cuerpo = "Write a comment";
                        } else {
                            titulo = "Mensaje";
                            cuerpo = "Escriba un comentario";
                        }
                        String mensaje = mensajes1.get(row);
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
                                    mensajes1.set(row, resp);
                                }
                            }
                        }

                    }

                }
            }
        });
    }

    private void getPermitidos1() {
        int i = 0;
        LinkedHashMap<String, ArrayList<String>> procesosRequeridos = new LinkedHashMap();
        ArrayList<Integer> requisitos = new ArrayList();
        requisitos.addAll(storage.getRequisitosTT1());
        for (Integer req : requisitos) {
            ArrayList<String> g = new ArrayList();
            String b = Integer.toBinaryString(req);//se convierte el numero de "requisito" en binario
            for (int k = 0; k < b.length(); k++) {//se recorren los bits de requisito
                if (b.charAt(k) == '1') {//si bit leido es un 1
                    g.add(storage.getRendimientoTT().get(((b.length() - 1) - k)));
                }
            }
            procesosRequeridos.put(actividades.get(i), g);//se coloca el nombre del proceso actual, y la lista de nombres de los procesos requeridos para poder empezar a hacer este
            i++;
        }
        for (String key : procesosRequeridos.keySet()) {
            if (procesosRequeridos.get(key).size() > 0) {
                boolean permit = true;
                for (String value : procesosRequeridos.get(key)) {
                    int index = storage.getRendimientoTT().indexOf(value);
                    if (storage.getPonderacionTT().get(index).equals("N/A")) {
                        permit = false;
                    }
                }
                habilitar1.add(permit);
                // permitido.put(key, permit);
            } else {
                //  permitido.put(key, true);
                habilitar1.add(true);
            }
        }

    }

    public void iniciarDiseno() {//decorar los componentes del frame
        tblActividades.setBackground(colores.grisTenue);
        tblActividades.setSelectionBackground(colores.grisTenue);

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

        new disenoTabla().cabecera(tblActividades);
        new disenoTabla().cabecera(tblRendimiento);
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
        java.awt.GridBagConstraints gridBagConstraints;

        pnlFondo = new javax.swing.JPanel();
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActividades = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRendimiento = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitulo.setText("Check shipment");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        pnlCuerpo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlCuerpoComponentResized(evt);
            }
        });
        pnlCuerpo.setLayout(new java.awt.GridBagLayout());

        tblActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Procesos", "Realizado", "Aprobacion"
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
            tblActividades.getColumnModel().getColumn(2).setHeaderValue("Aprobacion");
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 397;
        gridBagConstraints.ipady = 231;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCuerpo.add(jScrollPane1, gridBagConstraints);

        tblRendimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rendimiento en vuelo", "Ponderacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
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
        tblRendimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRendimientoMouseClicked(evt);
            }
        });
        tblRendimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblRendimientoKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tblRendimiento);
        if (tblRendimiento.getColumnModel().getColumnCount() > 0) {
            tblRendimiento.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblRendimiento.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 397;
        gridBagConstraints.ipady = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlCuerpo.add(jScrollPane2, gridBagConstraints);

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
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
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
            realizado.set(i, Boolean.parseBoolean(modelo.getValueAt(i, 1).toString()));
            aprobacion.set(i, Boolean.parseBoolean(modelo.getValueAt(i, 2).toString()));
        }
        for (int i = 0; i < modelo1.getRowCount(); i++) {
            ponderacion.set(i, modelo1.getValueAt(i, 1).toString());
        }
        storage.setComentarioTT(mensajes);
        storage.setRealizadoTT(realizado);
        storage.setAprobacionTT(aprobacion);

        storage.setPonderacionTT(ponderacion);
        storage.setComentarioTT1(mensajes1);

        //  new info().setXY(this.getX(), this.getY());
        new info().setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new vistaCompletarOrden(con, user, priv, idioma, serie, plantilla, modo).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (new back().backConf(idioma, this)) {
            new info().setXY(this.getX(), this.getY(), this.getWidth(), this.getHeight());
//            new info().setXY(this.getX(), this.getY());
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

    private void tblRendimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRendimientoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblRendimientoMouseClicked

    private void tblRendimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRendimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblRendimientoKeyTyped

    private void pnlCuerpoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCuerpoComponentResized
        // TODO add your handling code here:
        if (val) {//De una sola ejecucion
            int anchoPanel = (int) (pnlCuerpo.getWidth() / 10);
            TableColumnModel tableColumnModel = tblActividades.getColumnModel();
            tableColumnModel.getColumn(0).setMaxWidth((anchoPanel * 6));
            tableColumnModel.getColumn(1).setMaxWidth((anchoPanel * 2));
            tableColumnModel.getColumn(2).setMaxWidth((anchoPanel * 2));
            tableColumnModel = tblRendimiento.getColumnModel();
            tableColumnModel.getColumn(0).setMaxWidth((anchoPanel * 7));
            tableColumnModel.getColumn(1).setMaxWidth((anchoPanel * 3));
            val = false;
        }
    }//GEN-LAST:event_pnlCuerpoComponentResized

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new windowClosing(idioma, this);
    }//GEN-LAST:event_formWindowClosing

    private void ponerTabla() {//poner las actividades del proceso seleccionado en la tabla
        JComboBox cmb = new JComboBox();
        cmb.addItem("N/A");
        cmb.addItem("Buena");
        cmb.addItem("Regular");
        cmb.addItem("Aceptable");
        new disenos().selector(cmb);

        for (int i = 0; i < actividades.size(); i++) {
            if (!actividades.get(i).equals("")) {
                modelo.addRow(new Object[]{actividades.get(i), realizado.get(i), aprobacion.get(i)});
            }

        }
        tblRendimiento.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cmb));
        for (int i = 0; i < rendimiento.size(); i++) {
            if (!rendimiento.get(i).equals("")) {
                modelo1.addRow(new Object[]{rendimiento.get(i), ponderacion.get(i)});
            }
        }

    }

    private void mostrar() {
        if (idioma.equals("english")) {
            lblTitulo.setText("Testing check");
            JTableHeader tableHeader = tblActividades.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Activities");
            tableColumn = tableColumnModel.getColumn(1);
            tableColumn.setHeaderValue("Done");
            tableColumn = tableColumnModel.getColumn(2);
            tableColumn.setHeaderValue("Aprovement");
            tableHeader.repaint();

            tableHeader = tblRendimiento.getTableHeader();
            tableColumnModel = tableHeader.getColumnModel();
            tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Flight performance");
            tableColumn = tableColumnModel.getColumn(1);
            tableColumn.setHeaderValue("Consideration");
            tableHeader.repaint();
        } else {
            lblTitulo.setText("Revision de pruebas");
            JTableHeader tableHeader = tblActividades.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Actividades");
            tableHeader.repaint();

            tableHeader = tblRendimiento.getTableHeader();
            tableColumnModel = tableHeader.getColumnModel();
            tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Rendimiento en vuelo");
            tableColumn = tableColumnModel.getColumn(1);
            tableColumn.setHeaderValue("Ponderacion");
            tableHeader.repaint();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tblActividades;
    private javax.swing.JTable tblRendimiento;
    // End of variables declaration//GEN-END:variables
}
