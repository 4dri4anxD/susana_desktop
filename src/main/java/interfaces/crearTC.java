package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import datos.temporalStorage;
import disenos.centerTextInTable;
import disenos.colores;
import disenos.configEXTRAS;
import disenos.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import disenos.readRecordTableBackground;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import obtenerDatos.plantillasCalidad;
import obtenerDatos.users;

public class crearTC extends configuracionVentana {

    private String idioma, responsableTC, plantillaTC, user;
    private DefaultTableModel modelo;
    private temporalStorage storage;
    private boolean hasPrevious, loadFlag;
    private ArrayList<String> actividades, listaUsuarios, seleccion;
    private ArrayList<Integer> requisitos;
    private LinkedHashMap<String, ArrayList<String>> comentarios;
    private DatabaseReference con;
    private int serie, priv;
    private boolean valido, cargado;
    private JFrame context;

    /*
    actividades= getIntent().getExtras().getStringArrayList("actividades");
        requisitos=getIntent().getExtras().getIntegerArrayList("requisitos");
        listaUsuarios= getIntent().getExtras().getStringArrayList("listaUsuarios");
     */
    public crearTC(DatabaseReference con, String user, int priv, String idioma, int serie, boolean valido) {
        initComponents();
        cargado = false;

        ImageIcon imagen = new ImageIcon(info.RUTA_IMAGEN);
        Image icono = imagen.getImage();
        this.setIconImage(icono);
        storage = new temporalStorage();
        //poner titulo
        hasPrevious = false;
        seleccion = new ArrayList();
        context = this;
        this.valido = valido;

        this.serie = serie;
        this.con = con;
        this.user = user;
        this.idioma = idioma;

        loadFlag = false;
        this.setTitle(info.VERSION);
        //inicializacion de variables
        modelo = (DefaultTableModel) tblActividades.getModel();
        this.responsableTC = storage.getResponsableTC();
        this.plantillaTC = storage.getPlantillaTC();
        if (storage.getUbicacionTC().size() > 0) {
            this.actividades = new ArrayList<>();
            this.requisitos = new ArrayList<>();
            this.listaUsuarios = new ArrayList<>();
            this.comentarios = new LinkedHashMap<>();
            try {

                this.actividades.addAll(storage.getUbicacionTC());
                this.requisitos.addAll(storage.getRequisitosTC());
                for (HashMap.Entry<String, ArrayList<String>> entry : storage.getComentariosTC().entrySet()) {
                    this.comentarios.put(entry.getKey(),
                            entry.getValue());
                }
                rellenar();
                ponerRv();

            } catch (Exception e) {
                System.out.println("erorrrrrrrrrrrrrr: " + e);
            }

        } else {

            this.actividades = new ArrayList<>();
            this.requisitos = new ArrayList<>();
            this.listaUsuarios = new ArrayList<>();
            this.comentarios = new LinkedHashMap<>();
            loadFlag = false;
            if (storage.getListaUsuarios().size() > 0) {
                rellenar();
                getActividades(cmbPlantilla.getSelectedItem().toString());
            } else {
                leerUsuarios();
            }

        }
        if (idioma.equals("English")) {
            ingles();
        } else {
            esp();
        }
        tblActividades.setDefaultRenderer(Object.class, new centerTextInTable());
        iniciarDiseno();
    }

    private void rellenar() {
        cmbPlantilla.setEnabled(valido);
        hasPrevious = true;

        listaUsuarios.addAll(storage.getListaUsuarios());
        for (String plan : storage.getPlantillas()) {
            cmbPlantilla.addItem(plan);
        }
        for (String us : listaUsuarios) {
            cmbResponsable.addItem(us);
        }
    }

    public void iniciarDiseno() {//decorar los componentes del frame
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAddComment, 3);
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
        new disenos().titulo(lblPlantilla, 6);
        new disenos().selector(cmbResponsable);
        new disenos().selector(cmbPlantilla);
        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnAdd, "img/check1.png");
        ponerImg(btnAddComment, "img/agregarProceso.png");
        ponerImg(btnAtras, "img/atras2.png");

        new disenoTabla().cabecera(tblActividades);
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
        btnAddComment = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActividades = new javax.swing.JTable();
        pnlArriba = new javax.swing.JPanel();
        lblSerie = new javax.swing.JLabel();
        lblResponsable = new javax.swing.JLabel();
        lblPlantilla = new javax.swing.JLabel();
        cmbResponsable = new javax.swing.JComboBox<>();
        cmbPlantilla = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("CheckList de Calidad");

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

        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnAddComment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCommentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddComment, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddComment, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jScrollPane1.setViewportView(tblActividades);
        if (tblActividades.getColumnModel().getColumnCount() > 0) {
            tblActividades.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblActividades.getColumnModel().getColumn(1).setPreferredWidth(60);
        }

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
        gridBagConstraints.gridy = 2;
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbPlantilla, gridBagConstraints);

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
            .addComponent(pnlArriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCuerpoLayout.createSequentialGroup()
                .addComponent(pnlArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
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

        try {
            this.setCursor(new Cursor(WAIT_CURSOR));
            for (int i = 0; i < modelo.getRowCount(); i++) {
                seleccion.set(i, modelo.getValueAt(i, 1).toString());
            }
            storage.setPlantillaTC(cmbPlantilla.getSelectedItem().toString());
            storage.setResponsableTC(cmbResponsable.getSelectedItem().toString());
            storage.setUsuariosTC(seleccion);
            storage.setUbicacionTC(actividades);
            storage.setComentariosTC(comentarios);
            storage.setRequisitosTC(requisitos);
            //volver
            new info().setXY(this.getX(), this.getY());
            new vistaActividades(con, user, priv, idioma, serie, 2).setVisible(true);
            this.dispose();
        } catch (Exception e) {
            this.setCursor(new Cursor(DEFAULT_CURSOR));
        }
        //actualizar seleccion
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        //   new info().setXY(this.getX(), this.getY());
        // this.setCursor(new Cursor(WAIT_CURSOR));
        //  this.dispose();
        new info().setXY(this.getX(), this.getY());
        new vistaActividades(con, user, priv, idioma, serie, 2).setVisible(true);
        this.setCursor(new Cursor(WAIT_CURSOR));
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tblActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblActividadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblActividadesMouseClicked

    private void tblActividadesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblActividadesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblActividadesKeyTyped

    private void cmbPlantillaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPlantillaItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbPlantillaItemStateChanged

    private void btnAddCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCommentActionPerformed
        // TODO add your handling code here:
        //Abre nueva ventana
        try {
            String activi = modelo.getValueAt(tblActividades.getSelectedRow(), 0).toString();
            new info().setXY(this.getX(), this.getY());
            //guardar listaUsuarios
            for (int i = 0; i < modelo.getRowCount(); i++) {
                seleccion.set(i, modelo.getValueAt(i, 1).toString());
            }
            responsableTC = cmbResponsable.getSelectedItem().toString();
            plantillaTC = cmbPlantilla.getSelectedItem().toString();
            new addComentarios(user, priv, idioma, 1, activi, comentarios).setVisible(true);
        } catch (Exception e) {
            System.out.println("E: " + e);
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Select an activity");
            } else {
                JOptionPane.showMessageDialog(context, "Seleccione una actividad");
            }
        }
    }//GEN-LAST:event_btnAddCommentActionPerformed

    private void cmbPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPlantillaActionPerformed
        // TODO add your handling code here:
        if (cargado) {
            // System.out.println("Aquasai");
            if (!hasPrevious && !loadFlag) {
                limpiarTabla();
                getActividades(cmbPlantilla.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_cmbPlantillaActionPerformed

    private void limpiarTabla() {
        int tam = modelo.getRowCount();
        for (int i = 0; i < tam; i++) {
            modelo.removeRow(0);

        }
        actividades.clear();
        requisitos.clear();
        // listaUsuarios.clear();
        seleccion.clear();
        comentarios.clear();
    }

    private void ponerRv() {
        //tabla
        plantillaTC = cmbPlantilla.getSelectedItem().toString();
        responsableTC = cmbResponsable.getSelectedItem().toString();

        if (storage.getUsuariosTC().size() > 0) {
            seleccion = storage.getUsuariosTC();
        } else {
            llenarBaraja();
        }
        JComboBox cmb = new JComboBox();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            cmb.addItem(listaUsuarios.get(i));
        }
        new disenos().selector(cmb);
        //agrega el como box a las filas
        tblActividades.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cmb));
        for (int i = 0; i < seleccion.size(); i++) {
            modelo.addRow(new Object[]{actividades.get(i), seleccion.get(i)});
        }
        cargado = true;
    }

    private void llenarBaraja() {
        ArrayList cartas1 = new ArrayList();
        int pos;
        int nCartas = listaUsuarios.size();
        int comp = (listaUsuarios.contains("dummy")) ? (nCartas - 1) : nCartas;
        for (int i = 0; i < actividades.size(); i++) {//nCartas
            pos = (int) Math.floor(Math.random() * nCartas);
            if (cartas1.size() == comp) {
                cartas1.clear();
            }
            boolean val = cartas1.contains(pos) || listaUsuarios.get(pos).equals("dummy");
            while (val) {
                pos = (int) Math.floor(Math.random() * nCartas);
                val = cartas1.contains(pos) || listaUsuarios.get(pos).equals("dummy");
            }

            seleccion.add(listaUsuarios.get(pos));
            // pCartas.add(pos);
            cartas1.add(pos);
        }
    }

    private void getPlantillas() {

        try {
            ArrayList<String> plantillas = new ArrayList<>();

            Query query = null;
            query = con.child("plantillasCalidad");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot user : snapshot.getChildren()) {
                            cmbPlantilla.addItem(user.getKey());
                            plantillas.add(user.getKey());
                        }
                        storage.setPlantillas(plantillas);
                        cmbPlantilla.setSelectedIndex(0);
                        System.out.println("Index: " + cmbPlantilla.getItemCount());
                        //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.light_background_item, plantillas);
                        //   spnPlantillaTC.setAdapter(adapter);
                        //   adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.light_background_item, listaUsuarios);
                        //   spnResponsable.setAdapter(adapter);
                        if (hasPrevious) {
                            System.out.println("Aquiii");
                            cmbResponsable.setSelectedItem(storage.getResponsableTC());
                            cmbPlantilla.setSelectedItem(storage.getPlantillaTC());
                            //ver como arreglar listaUsuarios
                            //Si se agrega un nuevo usuario, se va a desmadrar todo no?
                            //O al menos no lo va a reconocer

                            hasPrevious = false;
                        } else if (loadFlag) {
                            System.out.println("Acaa");
                            //cargar desde memoria

                            cmbResponsable.setSelectedItem(responsableTC);
                            cmbPlantilla.setSelectedItem(plantillaTC);
                            loadFlag = false;
                        }
                        getActividades(cmbPlantilla.getSelectedItem().toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
        } catch (Exception e) {

        }
    }

    private void getActividades(String plantilla) {
        try {
            Query query = null;
            query = con.child("plantillasCalidad").child(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot info : snapshot.getChildren()) {
                            plantillasCalidad datos = info.getValue(plantillasCalidad.class);
                            actividades.add(datos.getUbicacion());
                            requisitos.add(datos.getRequisito());
                        }
                        ponerRv();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        } catch (Exception e) {

        }
    }

    private void leerUsuarios() {
        System.out.println("Aquiiisera");
        // if (listaUsuarios.size() == 0) {//!hasPrevious && !loadFlag
        //  System.out.println("leyo");
        Query query = con.child("usuarios").orderByChild("user");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot user : snapshot.getChildren()) {
                        users log = user.getValue(users.class);
                        try {
                            int pr = log.getPriv();
                            if (pr >= 2 && pr <= 4) {
                                listaUsuarios.add(log.getUser());
                                cmbResponsable.addItem(log.getUser());

                            }
                        } catch (Exception e) {

                        }
                    }
                    storage.setListaUsuarios(listaUsuarios);
                    //  cmbResponsable.setSelectedItem(listaUsuarios.get(0));
                    cmbResponsable.setSelectedIndex(0);
                    System.out.println("Aqui: " + cmbResponsable.getItemCount());
                    getPlantillas();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        //  } else {
        //   getPlantillas();
        //  }
    }

    private void ingles() {//poner la interfaz en ingles
        lblTitulo.setText("CheckList");
        JTableHeader tableHeader = tblActividades.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Activities");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("User");
        tableHeader.repaint();
        lblResponsable.setText("Responsible");
        lblPlantilla.setText("Template");
        //   leerUsuarios();
    }

    private void esp() {//poner la interfaz en espanol
        lblTitulo.setText("CheckList");
        JTableHeader tableHeader = tblActividades.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Actividades");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("Usuario");
        tableHeader.repaint();
        lblResponsable.setText("Responsable");
        lblPlantilla.setText("Plantilla");
        //  leerUsuarios();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddComment;
    private javax.swing.JButton btnAtras;
    private javax.swing.JComboBox<String> cmbPlantilla;
    private javax.swing.JComboBox<String> cmbResponsable;
    private javax.swing.JScrollPane jScrollPane1;
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
    // End of variables declaration//GEN-END:variables
}
