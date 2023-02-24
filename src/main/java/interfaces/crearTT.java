/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import com.google.firebase.database.DatabaseReference;
import datos.temporalStorage;
import disenos.centerTextInTable;
import disenos.disenoTabla;
import disenos.disenos;
import disenos.ventanas.configuracionVentana;
import helpers.crearOrdenes;
import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import configuracion.info;
import helpers.back;
import helpers.windowClosing;
import javax.swing.JOptionPane;

public class crearTT extends JFrame {

    private String idioma, user;
    private DefaultTableModel modelo, modelo1;
    private temporalStorage storage;
    private ArrayList<String> actividades, listaUsuarios, seleccion, seleccion1, rendimiento;
    private ArrayList<Integer> requisitos, requisitos1;
    private DatabaseReference con;
    private int serie, priv;
    private JFrame context;
    private boolean valido, cargado;
    private crearOrdenes co;

    public crearTT(DatabaseReference con, String user, int priv, String idioma, int serie, boolean valido) {
        initComponents();
        new configuracionVentana(this);
        this.valido = valido;
        this.serie = serie;
        this.con = con;
        this.user = user;
        this.idioma = idioma;

        iniciarVariables();
        mostrar();
        iniciarDiseno();
    }

    private void iniciarVariables() {
        cargado = false;
        storage = new temporalStorage();
        context = this;
        modelo = (DefaultTableModel) tblActividades.getModel();
        modelo1 = (DefaultTableModel) tblRendimiento.getModel();
        actividades = new ArrayList<>();
        requisitos = new ArrayList<>();
        rendimiento = new ArrayList<>();
        requisitos1 = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        seleccion = new ArrayList();
        seleccion1 = new ArrayList();
        co = new crearOrdenes();
        //    System.out.println("rend: " + storage.getRendimientoTT());
        //    System.out.println("ac: " + storage.getActividadesTT());

        if (storage.getActividadesTT().size() > 0 || storage.getRendimientoTT().size() > 0) {
            try {
                rendimiento.addAll(storage.getRendimientoTT());
                requisitos1.addAll(storage.getRequisitosTT1());
                seleccion1.addAll(storage.getUsuarios1TT());
            } catch (Exception e) {
            }
            try {
                actividades.addAll(storage.getActividadesTT());
                requisitos.addAll(storage.getRequisitosTT());
                seleccion.addAll(storage.getUsuariosTT());
            } catch (Exception e) {
            }
            cmbPlantilla.setEnabled(valido);
            fill(true);
            cmbPlantilla.setSelectedItem(storage.getPlantillaTT());
            cmbResponsable.setSelectedItem(storage.getResponsableTT());
            cmbPiloto.setSelectedItem(storage.getPilotoTT());
        } else {
            fill(false);
        }

    }

    private void fill(boolean pr) {
        if (storage.getPlantillasTT().size() > 0) {
            rellenar(1);
            if (!pr) {
                getActividades(cmbPlantilla.getSelectedItem().toString());
            } else {
                ponerRv();
            }
        } else {
            if (storage.getListaUsuarios().size() == 0) {
                leerUsuarios();
            } else {
                rellenar(1);
            }
            getPlantillas(pr);
        }

    }

    private void iniciarDiseno() {//decorar los componentes del frame
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlArriba, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().fondoLabel(lblSerie, 1);
        new disenos().titulo(lblSerie, 5);
        new disenos().titulo(lblResponsable, 6);
        new disenos().titulo(lblPiloto, 6);
        new disenos().titulo(lblPlantilla, 6);
        new disenos().selector(cmbResponsable);
        new disenos().selector(cmbPlantilla);
        new disenos().selector(cmbPiloto);
        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnAdd, "img/check1.png");
        ponerImg(btnAtras, "img/atras2.png");

        new disenoTabla().cabecera(tblActividades);
        tblActividades.setDefaultRenderer(Object.class, new centerTextInTable());
        new disenoTabla().cabecera(tblRendimiento);
        tblRendimiento.setDefaultRenderer(Object.class, new centerTextInTable());
    }

    public void ponerImg(JButton b, String ruta) {//poner imagenes a los botones
        ImageIcon imagen = new ImageIcon(ruta);
        Image imgEscalada = imagen.getImage().getScaledInstance(b.getWidth(),
                b.getHeight(), Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblActividades = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRendimiento = new javax.swing.JTable();
        pnlArriba = new javax.swing.JPanel();
        lblSerie = new javax.swing.JLabel();
        lblResponsable = new javax.swing.JLabel();
        lblPlantilla = new javax.swing.JLabel();
        cmbResponsable = new javax.swing.JComboBox<>();
        cmbPlantilla = new javax.swing.JComboBox<>();
        lblPiloto = new javax.swing.JLabel();
        cmbPiloto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitulo.setText("CheckList Final");

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

        pnlDer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        pnlCuerpo.setLayout(new java.awt.GridBagLayout());

        tblActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Actividad", "Usuario"
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
        jScrollPane2.setViewportView(tblActividades);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 438;
        gridBagConstraints.ipady = 171;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 0);
        pnlCuerpo.add(jScrollPane2, gridBagConstraints);

        tblRendimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Actividad", "Usuario"
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
        jScrollPane1.setViewportView(tblRendimiento);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 438;
        gridBagConstraints.ipady = 171;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 11, 0);
        pnlCuerpo.add(jScrollPane1, gridBagConstraints);

        pnlArriba.setLayout(new java.awt.GridBagLayout());

        lblSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSerie.setText("No establecido aun");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        pnlArriba.add(lblSerie, gridBagConstraints);

        lblResponsable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResponsable.setText("Responsable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblResponsable, gridBagConstraints);

        lblPlantilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlantilla.setText("Plantilla");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblPlantilla, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbResponsable, gridBagConstraints);

        cmbPlantilla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPlantillaItemStateChanged(evt);
            }
        });
        cmbPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPlantillaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbPlantilla, gridBagConstraints);

        lblPiloto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPiloto.setText("Piloto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblPiloto, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbPiloto, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 390;
        gridBagConstraints.ipady = 119;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCuerpo.add(pnlArriba, gridBagConstraints);

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
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

        try {
            this.setCursor(new Cursor(WAIT_CURSOR));
            for (int i = 0; i < modelo.getRowCount(); i++) {
                seleccion.set(i, modelo.getValueAt(i, 1).toString());
            }
            for (int i = 0; i < modelo1.getRowCount(); i++) {
                seleccion1.set(i, modelo1.getValueAt(i, 1).toString());
            }
            storage.setPlantillaTT(cmbPlantilla.getSelectedItem().toString());
            storage.setPilotoTT(cmbPiloto.getSelectedItem().toString());
            storage.setResponsableTT(cmbResponsable.getSelectedItem().toString());
            storage.setUsuariosTT(seleccion);
            storage.setUsuarios1TT(seleccion1);
            storage.setActividadesTT(actividades);
            storage.setRendimientoTT(rendimiento);
            storage.setRequisitosTT(requisitos);
            storage.setRequisitosTT1(requisitos1);

            new info().setXY(this.getX(), this.getY());
            new vistaAgregarModificarOrdenes(con, user, priv, idioma, serie, 2).setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
            this.setCursor(new Cursor(DEFAULT_CURSOR));
        }
        //actualizar seleccion
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        //   new info().setXY(this.getX(), this.getY());
        // this.setCursor(new Cursor(WAIT_CURSOR));
        //  this.dispose();
        if (new back().backConf(idioma, this)) {
            new info().setXY(this.getX(), this.getY());
            new vistaAgregarModificarOrdenes(con, user, priv, idioma, serie, 2).setVisible(true);
            this.setCursor(new Cursor(WAIT_CURSOR));
            this.dispose();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tblRendimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRendimientoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblRendimientoMouseClicked

    private void tblRendimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRendimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblRendimientoKeyTyped

    private void cmbPlantillaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPlantillaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPlantillaItemStateChanged

    private void cmbPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPlantillaActionPerformed
        // TODO add your handling code here:
        if (cargado) {
            // System.out.println("Aquasai");
            limpiarTabla();
            getActividades(cmbPlantilla.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbPlantillaActionPerformed

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

    private void rellenar(int a) {
        listaUsuarios.addAll(co.rellenar(cmbPlantilla, cmbResponsable, storage.getPlantillasTT(), valido, storage.getListaUsuarios(), a));
        for (String paq : listaUsuarios) {
            cmbPiloto.addItem(paq);
        }

    }

    private void limpiarTabla() {
        co.limpiarTabla(modelo);
        co.limpiarTabla(modelo1);
        actividades.clear();
        requisitos.clear();
        seleccion.clear();
        rendimiento.clear();
        requisitos1.clear();
        seleccion1.clear();
    }

    private void ponerRv() {
        System.out.println("Poner rv");
        seleccion.clear();
        seleccion.addAll(co.ponerRv(storage.getUsuariosTT(), listaUsuarios, tblActividades, modelo, actividades));
        seleccion1.clear();
        seleccion1.addAll(co.ponerRv(storage.getUsuarios1TT(), listaUsuarios, tblRendimiento, modelo1, rendimiento));
        cargado = true;
    }

    private void getPlantillas(boolean pr) {
        co.readPlantillas(new crearOrdenes.CallBackPlantillas() {
            @Override
            public void onCallback(ArrayList<String> plantillas) {
                storage.setPlantillasTT(plantillas);
                if (!pr) {
                    getActividades(cmbPlantilla.getSelectedItem().toString());
                } else {
                    ponerRv();
                }
            }
        }, con, "plantillasTesting", cmbResponsable, cmbPlantilla, storage.getResponsableTT(), storage.getPlantillaTT());
    }

    private void getActividades(String plantilla) {
        co.readActividades(new crearOrdenes.CallBackActividades() {
            @Override
            public void onCallback(crearOrdenes.Actividades c) {
                System.out.println("BACK: " + c.getActividades1());
                actividades.addAll(c.getActividades());
                requisitos.addAll(c.getRequisitos());
                rendimiento.addAll(c.getActividades1());
                requisitos1.addAll(c.getRequisitos1());
                //  System.out.println("Aquie como no xd: "+rendimiento);
                ponerRv();
            }
        }, con, "plantillasTesting", plantilla, "actividad", "requisito", "accion", "requisito1");
    }

    private void leerUsuarios() {
        co.readUsuarios(new crearOrdenes.CallBackUsuarios() {
            @Override
            public void onCallback(ArrayList<String> usuarios) {
                for (String usuario : usuarios) {
                    listaUsuarios.add(usuario);
                    cmbPiloto.addItem(usuario);
                }

            }
        }, con, storage, cmbResponsable);
    }

    private void mostrar() {
        if (idioma.equals("english")) {
            lblTitulo.setText("Final CheckList");
            JTableHeader tableHeader = tblActividades.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Activities");
            tableColumn = tableColumnModel.getColumn(1);
            tableColumn.setHeaderValue("User");
            tableHeader.repaint();
            tableHeader = tblRendimiento.getTableHeader();
            tableColumnModel = tableHeader.getColumnModel();
            tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Actions");
            tableColumn = tableColumnModel.getColumn(1);
            tableColumn.setHeaderValue("User");
            tableHeader.repaint();
            lblResponsable.setText("Responsible");
            lblPlantilla.setText("Template");
            lblPiloto.setText("Pilot");
            if (storage.getSerie() == 0) {
                lblSerie.setText("Not stablished yet");
            } else {
                lblSerie.setText("" + storage.getSerie());
            }
        } else {
            lblTitulo.setText("CheckList Final");
            JTableHeader tableHeader = tblActividades.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Actividades");
            tableColumn = tableColumnModel.getColumn(1);
            tableColumn.setHeaderValue("Usuario");
            tableHeader.repaint();
            tableHeader = tblRendimiento.getTableHeader();
            tableColumnModel = tableHeader.getColumnModel();
            tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Acciones");
            tableColumn = tableColumnModel.getColumn(1);
            tableColumn.setHeaderValue("Usuario");
            tableHeader.repaint();
            lblResponsable.setText("Responsable");
            lblPlantilla.setText("Plantilla");
            lblPiloto.setText("Piloto");
            if (storage.getSerie() == 0) {
                lblSerie.setText("No establecido aun");
            } else {
                lblSerie.setText("" + storage.getSerie());
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JComboBox<String> cmbPiloto;
    private javax.swing.JComboBox<String> cmbPlantilla;
    private javax.swing.JComboBox<String> cmbResponsable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPiloto;
    private javax.swing.JLabel lblPlantilla;
    private javax.swing.JLabel lblResponsable;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlArriba;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tblActividades;
    private javax.swing.JTable tblRendimiento;
    // End of variables declaration//GEN-END:variables
}
