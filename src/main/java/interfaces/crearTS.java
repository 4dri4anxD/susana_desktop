package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import datos.temporalStorage;
import disenos.centerTextInTable;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import helpers.back;
import helpers.crearOrdenes;
import helpers.windowClosing;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import obtenerDatos.plantillasShipment;
import obtenerDatos.tipoPaquete;
import obtenerDatos.users;

public class crearTS extends JFrame {

    private final String idioma, user;
    private String mensajeAdj, hintProducto;
    private DefaultTableModel modelo;
    private temporalStorage storage;
    private ArrayList<String> actividades, listaUsuarios, seleccion;
    private ArrayList<Integer> requisitos;
    private final DatabaseReference con;
    private final int serie, priv;
    private JFrame context;
    private final boolean valido;
    private boolean cargado;
    private crearOrdenes co;

    public crearTS(DatabaseReference con, String user, int priv, String idioma, int serie, boolean valido) {
        initComponents();
        new configuracionVentana(this);
        //Igualar parametros
        this.con = con;
        this.valido = valido;
        this.user = user;
        this.priv = priv;
        this.idioma = idioma;
        this.serie = serie;

        iniciarVariables();
        iniciarDiseno();
        mostrar();

    }

    private void iniciarVariables() {
        hintProducto = "Aurelia X6 Std";
        mensajeAdj = "";
        context = this;
        seleccion = new ArrayList();
        storage = new temporalStorage();
        modelo = (DefaultTableModel) tblActividades.getModel();
        actividades = new ArrayList<>();
        requisitos = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        co = new crearOrdenes();
        // chkAirTag.setSelected(false);
        if (storage.getAccesoriosTS().size() > 0) {
            try {
                actividades.addAll(storage.getAccesoriosTS());
                requisitos.addAll(storage.getRequisitosTS());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(context, "Error: " + e);
            }

            fill(true);
            cmbPlantilla.setEnabled(valido);
            mensajeAdj = storage.getMensajeTS();
            txtProducto.setText(storage.getProductoTS());
            cmbPlantilla.setSelectedItem(storage.getPlantillaTS());
            cmbResponsable.setSelectedItem(storage.getResponsableTS());
            chkAirTag.setSelected(storage.isAirtagTS());
            cmbCase.setSelectedItem(storage.getCaseTS());
            cmbPaquete.setSelectedItem(storage.getPaqueteTS());
        } else {
            fill(false);
        }

    }

    private void fill(boolean pr) {
        if (storage.getPlantillasTS().size() > 0) {
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
                rellenar(0);
            }
            if (storage.getTipoPaquete().size() == 0) {
                leerTipoPaquetes();
            }
            getPlantillas(pr);

        }

    }

    private void iniciarDiseno() {//decorar los componentes del frame
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnMsj, 3);
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
        new disenos().titulo(lblPaquete, 6);
        new disenos().titulo(lblProducto, 6);
        new disenos().titulo(lblCase, 6);
        new disenos().selector(cmbResponsable);
        new disenos().selector(cmbPaquete);
        new disenos().selector(cmbCase);
        new disenos().textoL1(txtProducto);
        new disenos().selector(cmbPlantilla);
        new disenos().titulo(lblTitulo, 2);

        chkAirTag.setFont(new Font("Lato", Font.BOLD, 20));

        ponerImg(btnAdd, "img/check1.png");
        ponerImg(btnAtras, "img/atras2.png");
        ponerImg(btnMsj, "img/adj1.png");
        ponerImgChk(chkAirTag, "img/unchecked.png", "img/checked.jpg");

        new disenoTabla().cabecera(tblActividades);
        tblActividades.setDefaultRenderer(Object.class, new centerTextInTable());
        // System.out.println("Antres: "+chkAirTag.isSelected());

        // System.out.println("Chk desues: "+chkAirTag.isSelected());
    }

    private void ponerImg(JButton b, String ruta) {//poner imagenes a los botones
        ImageIcon imagen = new ImageIcon(ruta);
        Image imgEscalada = imagen.getImage().getScaledInstance(b.getWidth(),
                b.getHeight(), Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);
    }

    private void ponerImgChk(JCheckBox b, String ruta, String ruta2) {//poner imagenes a los botones
        int w = 7, h = 2;
        ImageIcon imagen = new ImageIcon(ruta);
        Image imgEscalada = imagen.getImage().getScaledInstance(b.getWidth() / w,
                b.getHeight() / h, Image.SCALE_SMOOTH);
        Icon icono = new ImageIcon(imgEscalada);
        b.setIcon(icono);
        imagen = new ImageIcon(ruta2);
        imgEscalada = imagen.getImage().getScaledInstance(b.getWidth() / w,
                b.getHeight() / h, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imgEscalada);
        b.setSelectedIcon(icono);
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
        btnMsj = new javax.swing.JButton();
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
        lblProducto = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        lblPaquete = new javax.swing.JLabel();
        cmbPaquete = new javax.swing.JComboBox<>();
        lblCase = new javax.swing.JLabel();
        cmbCase = new javax.swing.JComboBox<>();
        chkAirTag = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitulo.setText("CheckList de Envios");

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

        btnMsj.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMsj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMsjActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMsj, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMsj, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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

        pnlArriba.setLayout(new java.awt.GridBagLayout());

        lblSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSerie.setText("No establecido aun");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.5;
        pnlArriba.add(lblSerie, gridBagConstraints);

        lblResponsable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResponsable.setText("Responsable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblResponsable, gridBagConstraints);

        lblPlantilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlantilla.setText("Plantilla");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblPlantilla, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
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
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbPlantilla, gridBagConstraints);

        lblProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProducto.setText("Producto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblProducto, gridBagConstraints);

        txtProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProductoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProductoFocusLost(evt);
            }
        });
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductoKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(txtProducto, gridBagConstraints);

        lblPaquete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaquete.setText("Paquete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblPaquete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbPaquete, gridBagConstraints);

        lblCase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCase.setText("Case");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(lblCase, gridBagConstraints);

        cmbCase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCaseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(cmbCase, gridBagConstraints);

        chkAirTag.setBackground(new java.awt.Color(255, 255, 255));
        chkAirTag.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        chkAirTag.setText("AirTag");
        chkAirTag.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkAirTag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAirTag.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkAirTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAirTagActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlArriba.add(chkAirTag, gridBagConstraints);

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
            .addComponent(pnlArriba, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCuerpoLayout.createSequentialGroup()
                .addComponent(pnlArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
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
        try {//Guardar
            if (!txtProducto.getText().equals(hintProducto)) {
                this.setCursor(new Cursor(WAIT_CURSOR));
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    seleccion.set(i, modelo.getValueAt(i, 1).toString());
                }
                storage.setPlantillaTS(cmbPlantilla.getSelectedItem().toString());
                storage.setResponsableTS(cmbResponsable.getSelectedItem().toString());
                storage.setUsuariosTS(seleccion);
                storage.setAccesoriosTS(actividades);
                storage.setRequisitosTS(requisitos);
                storage.setPaqueteTS(cmbPaquete.getSelectedItem().toString());
                storage.setAirtagTS(chkAirTag.isSelected());
                storage.setCaseTS(cmbCase.getSelectedItem().toString());
                storage.setMensajeTS(mensajeAdj);
                storage.setProductoTS(txtProducto.getText());
                //volver
                new info().setXY(this.getX(), this.getY());
                new vistaAgregarModificarOrdenes(con, user, priv, idioma, serie, 2).setVisible(true);
                this.dispose();
            } else {
                if (idioma.equals("english")) {
                    JOptionPane.showMessageDialog(context, "Fill product's blank");
                } else {
                    JOptionPane.showMessageDialog(context, "Llene el campo de producto");
                }
                //escriba el nomrbe del producto
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
            //  this.setCursor(new Cursor(DEFAULT_CURSOR));
        }
        //actualizar seleccion
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (new back().backConf(idioma, this)) {
            new info().setXY(this.getX(), this.getY());
            new vistaAgregarModificarOrdenes(con, user, priv, idioma, serie, 2).setVisible(true);
            this.setCursor(new Cursor(WAIT_CURSOR));
            this.dispose();
        }
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

    private void cmbPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPlantillaActionPerformed
        // TODO add your handling code here:
        if (cargado) {
            limpiarTabla();
            getActividades(cmbPlantilla.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbPlantillaActionPerformed

    private void btnMsjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMsjActionPerformed
        // TODO add your handling code here:
        //abrir recuadro de texto
        String titulo, cuerpo;
        if (idioma.equals("english")) {
            titulo = "Message";
            cuerpo = "Write a comment";
        } else {
            titulo = "Mensaje";
            cuerpo = "Escriba un comentario";
        }
        String resp = JOptionPane.showInputDialog(context, cuerpo, mensajeAdj);
        if (resp != null) {
            if (!resp.equals("")) {
                mensajeAdj = resp;
            }
        }
    }//GEN-LAST:event_btnMsjActionPerformed

    private void chkAirTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAirTagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkAirTagActionPerformed

    private void cmbCaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCaseActionPerformed

    private void txtProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductoFocusGained
        // TODO add your handling code here:
        if (txtProducto.getText().equals(hintProducto)) {
            txtProducto.setText("");
            txtProducto.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtProductoFocusGained

    private void txtProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductoFocusLost
        // TODO add your handling code here:
        JTextField t = txtProducto;
        if (!t.getText().equals("")) {
            try {
                if (t.getText().length() > 50) {
                    t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                } else {
                    t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
            } catch (Exception e) {
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
        } else {
            t.setText(hintProducto);
            t.setForeground(Color.lightGray);
            t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
    }//GEN-LAST:event_txtProductoFocusLost

    private void txtProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyTyped
        // TODO add your handling code here:
        if (txtProducto.getText().length() >= 50) {
            evt.consume();
        }

    }//GEN-LAST:event_txtProductoKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new windowClosing(idioma, this);
    }//GEN-LAST:event_formWindowClosing

    private void limpiarTabla() {
        co.limpiarTabla(modelo);
        actividades.clear();
        requisitos.clear();
        seleccion.clear();
    }

    private void ponerRv() {
        seleccion.clear();
        seleccion.addAll(co.ponerRv(storage.getUsuariosTS(), listaUsuarios, tblActividades, modelo, actividades));
        cargado = true;
    }

    private void getPlantillas(boolean pr) {
        co.readPlantillas(new crearOrdenes.CallBackPlantillas() {
            @Override
            public void onCallback(ArrayList<String> plantillas) {
                cmbPaquete.setSelectedItem(storage.getTipoPaquete());
                storage.setPlantillasTF(plantillas);
                if (!pr) {
                    getActividades(cmbPlantilla.getSelectedItem().toString());
                } else {
                    ponerRv();
                }
            }
        }, con, "plantillasShipment", cmbResponsable, cmbPlantilla, storage.getResponsableTS(), storage.getPlantillaTS());

    }

    private void getActividades(String plantilla) {
        co.readActividades(new crearOrdenes.CallBackActividades() {
            @Override
            public void onCallback(crearOrdenes.Actividades c) {
                actividades.addAll(c.getActividades());
                requisitos.addAll(c.getRequisitos());
                ponerRv();
            }
        }, con, "plantillasShipment", plantilla, "accesorio", "requisito", null, null);
    }

    private void leerUsuarios() {
        co.readUsuarios(new crearOrdenes.CallBackUsuarios() {
            @Override
            public void onCallback(ArrayList<String> usuarios) {
                for (String usuario : usuarios) {
                    listaUsuarios.add(usuario);
                }

            }
        }, con, storage, cmbResponsable);
    }

    private void rellenar(int a) {
        listaUsuarios.addAll(co.rellenar(cmbPlantilla, cmbResponsable, storage.getPlantillasTS(), valido, storage.getListaUsuarios(), a));
        for (String paq : storage.getTipoPaquete()) {
            cmbPaquete.addItem(paq);
        }

    }

    private void leerTipoPaquetes() {
        ArrayList<String> pa = new ArrayList<>();
        Query query = con.child("tipoPaquete");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot user : snapshot.getChildren()) {
                        tipoPaquete twopaq = user.getValue(tipoPaquete.class);
                        try {
                            pa.add(twopaq.getPaquete());

                            cmbPaquete.addItem(twopaq.getPaquete());
                        } catch (Exception e) {

                        }
                    }
                    storage.setTipoPaquete(pa);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void mostrar() {
        cmbCase.addItem("Travel Case");
        cmbCase.addItem("Caja de madera");
        if (idioma.equals("english")) {
            lblProducto.setText("Product");
            lblPaquete.setText("Package");
            lblResponsable.setText("Responsible");
            lblPlantilla.setText("Template");
            lblTitulo.setText("Shipment Checklist");
            if (storage.getSerie() == 0) {
                lblSerie.setText("Not stablished yet");
            } else {
                lblSerie.setText("" + storage.getSerie());
            }
        } else {
            lblProducto.setText("Producto");
            lblPaquete.setText("Paquete");
            lblResponsable.setText("Responsable");
            lblPlantilla.setText("Plantilla");
            lblTitulo.setText("Checklist de envios");
            if (storage.getSerie() == 0) {
                lblSerie.setText("No establecido aun");
            } else {
                lblSerie.setText("" + storage.getSerie());
            }
        }
        if (txtProducto.getText().equals("")) {
            txtProducto.setText(hintProducto);
        } else {
            txtProducto.setForeground(Color.black);
        }
        txtProducto.requestFocus();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnMsj;
    private javax.swing.JCheckBox chkAirTag;
    private javax.swing.JComboBox<String> cmbCase;
    private javax.swing.JComboBox<String> cmbPaquete;
    private javax.swing.JComboBox<String> cmbPlantilla;
    private javax.swing.JComboBox<String> cmbResponsable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCase;
    private javax.swing.JLabel lblPaquete;
    private javax.swing.JLabel lblPlantilla;
    private javax.swing.JLabel lblProducto;
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
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
