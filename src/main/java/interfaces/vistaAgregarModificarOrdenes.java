package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import datos.leerJSON;
import obtenerDatos.ordenes;
import datos.temporalStorage;
import disenos.cmbTabla;
import disenos.colores;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import notificacion.notificar;
import obtenerDatos.checkAC;
import obtenerDatos.checkAF;
import obtenerDatos.checkAS;
import obtenerDatos.checkAT;
import obtenerDatos.checkCommentsC;
import obtenerDatos.checkTC;
import obtenerDatos.checkTF;
import obtenerDatos.checkTS;
import obtenerDatos.checkTT;
import obtenerDatos.keys;
import obtenerDatos.users;
import org.json.simple.JSONObject;

public class vistaAgregarModificarOrdenes extends JFrame {

    private DatabaseReference con;//conexion con la base de datos
    private String user, idioma;//usuario que esta utilizando la app e idioma de esta
    private int priv, inter;//, contadorcito, contadorsote;//privilegios
    private int serie;//numero de serie del dron
    private String tSerie, tNombre;//hint
    private JFrame context;
    private boolean listo;//si ya se inicializo la interfaz
    private temporalStorage storage;
    private leerJSON json;

    public vistaAgregarModificarOrdenes(DatabaseReference con, String user, int priv, String idioma, int serie, int inter) {
        initComponents();
        new configuracionVentana(this);

        storage = new temporalStorage();
        json = new leerJSON();
        this.con = con;
        this.inter = inter;
        this.user = user;
        context = this;
        listo = false;
        this.priv = priv;
        this.idioma = idioma;
        this.serie = serie;
        
        if(serie!=0)txtSerie.setEnabled(false);

        iniciarDiseno();

        Date f = new Date();
        txtPlazo.setDate(f);

        if (inter == 1) {//inter 1 significa insert
            storage.inicializarTodo();
            if (idioma.equals("English")) {
                ingles();//cambia la interfaz a ingles
            } else {
                esp();//cambia la interfaz a espanol
            }
        } else if (inter == 2) {//cargar info que ya se tenia, creo
            if (idioma.equals("English")) {
                ingles();//cambia la interfaz a ingles
            } else {
                esp();//cambia la interfaz a espanol
            }
            try {
                if (storage.getSerie() != 0) {
                    txtSerie.setForeground(Color.black);
                    txtSerie.setText("" + storage.getSerie());
                }
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(storage.getFecha());
                txtPlazo.setDate(date1);

                if (!storage.getNombre().equals("")) {
                    txtNombre.setForeground(Color.black);
                    txtNombre.setText(storage.getNombre());
                }

            } catch (Exception e) {

            }

        } else {//3
            storage.inicializarTodo();
            //leer Toda la info de un trabajo en especifico
            leerExistente();
        }
    }

    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: " + spinner.getEditor().getClass() + " isn't a descendant of DefaultEditor");
            return null;
        }
    }

    public void iniciarDiseno() {

        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        lblSerie.setHorizontalAlignment(JLabel.CENTER);
        lblPlazo.setHorizontalAlignment(JLabel.CENTER);
        lblNombre.setHorizontalAlignment(JLabel.CENTER);

        //  JFormattedTextField ftf = getTextField(txtHora);
        // if (ftf != null) {
        // ftf.setHorizontalAlignment(JTextField.CENTER);
        // }
        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);

        new disenos().textoL1(txtSerie);
        new disenos().fechas(txtPlazo);
        new disenos().textoL1(txtNombre);
        // new disenos().spiner(txtHora);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlChecks, 2);
        new disenos().fondo(pnlCue, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().fondoLabel(lblCheckLists, 1);
        new disenos().fondoLabel(lblPlantillas, 1);
        new disenos().fondoLabel(lblResponsable, 1);

        new disenos().fondoLabel(lblCC, 2);
        new disenos().fondoLabel(lblTC, 2);
        new disenos().fondoLabel(lblRC, 2);

        new disenos().fondoLabel(lblCT, 3);
        new disenos().fondoLabel(lblTT, 3);
        new disenos().fondoLabel(lblRT, 3);

        new disenos().fondoLabel(lblCS, 2);
        new disenos().fondoLabel(lblTS, 2);
        new disenos().fondoLabel(lblRS, 2);

        new disenos().fondoLabel(lblCF, 3);
        new disenos().fondoLabel(lblTF, 3);
        new disenos().fondoLabel(lblRF, 3);

        new disenos().titulo(lblTitulo, 2);
        new disenos().titulo(lblSerie, 6);
        new disenos().titulo(lblNombre, 6);
        new disenos().titulo(lblPlazo, 6);
        new disenos().titulo(lblPlazo, 6);
        new disenos().titulo(lblPlazo, 6);
        new disenos().titulo(lblCheckLists, 5);
        new disenos().titulo(lblPlantillas, 5);
        new disenos().titulo(lblResponsable, 5);
        new disenos().titulo(lblCC, 6);
        new disenos().titulo(lblTC, 6);
        new disenos().titulo(lblRC, 6);
        new disenos().titulo(lblCT, 6);
        new disenos().titulo(lblTT, 6);
        new disenos().titulo(lblRT, 6);
        new disenos().titulo(lblCS, 6);
        new disenos().titulo(lblTS, 6);
        new disenos().titulo(lblRS, 6);
        new disenos().titulo(lblCF, 6);
        new disenos().titulo(lblTF, 6);
        new disenos().titulo(lblRF, 6);

        ponerImg(btnAdd, "img/guardar1.png");
        ponerImg(btnAtras, "img/atras2.png");

        txtSerie.requestFocus();

        //  if (serie != 0) {
        //   txtHora.setForeground(Color.black);
        //  txtSerie.setForeground(Color.black);
        //  txtNombre.setForeground(Color.black);
        //  txtSerie.setEnabled(false);
        //  }
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
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        pnlCue = new javax.swing.JPanel();
        lblSerie = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        lblPlazo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtPlazo = new com.toedter.calendar.JDateChooser();
        txtNombre = new javax.swing.JTextField();
        pnlChecks = new javax.swing.JPanel();
        lblCheckLists = new javax.swing.JLabel();
        lblPlantillas = new javax.swing.JLabel();
        lblResponsable = new javax.swing.JLabel();
        lblCC = new javax.swing.JLabel();
        lblTC = new javax.swing.JLabel();
        lblRC = new javax.swing.JLabel();
        lblCT = new javax.swing.JLabel();
        lblTT = new javax.swing.JLabel();
        lblRT = new javax.swing.JLabel();
        lblCS = new javax.swing.JLabel();
        lblTS = new javax.swing.JLabel();
        lblRS = new javax.swing.JLabel();
        lblCF = new javax.swing.JLabel();
        lblTF = new javax.swing.JLabel();
        lblRF = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addContainerGap(64, Short.MAX_VALUE))
        );
        pnlIzqLayout.setVerticalGroup(
            pnlIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIzqLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlCuerpo.setLayout(new java.awt.GridBagLayout());

        pnlCue.setLayout(new java.awt.GridBagLayout());

        lblSerie.setText("No. de Serie");
        lblSerie.setMinimumSize(new java.awt.Dimension(20, 14));
        lblSerie.setPreferredSize(new java.awt.Dimension(30, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        pnlCue.add(lblSerie, gridBagConstraints);

        txtSerie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSerieFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSerieFocusLost(evt);
            }
        });
        txtSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSerieKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        pnlCue.add(txtSerie, gridBagConstraints);

        lblPlazo.setText("Plazo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        pnlCue.add(lblPlazo, gridBagConstraints);

        lblNombre.setText("Modelo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        pnlCue.add(lblNombre, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        pnlCue.add(txtPlazo, gridBagConstraints);

        txtNombre.setMinimumSize(new java.awt.Dimension(6, 14));
        txtNombre.setPreferredSize(new java.awt.Dimension(6, 16));
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 10, 0);
        pnlCue.add(txtNombre, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlCuerpo.add(pnlCue, gridBagConstraints);

        pnlChecks.setLayout(new java.awt.GridBagLayout());

        lblCheckLists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCheckLists.setText("CheckLists");
        lblCheckLists.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCheckLists.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblCheckLists, gridBagConstraints);

        lblPlantillas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlantillas.setText("Plantillas");
        lblPlantillas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPlantillas.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblPlantillas, gridBagConstraints);

        lblResponsable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResponsable.setText("Responsable");
        lblResponsable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblResponsable.setPreferredSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblResponsable, gridBagConstraints);

        lblCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCC.setText("Calidad");
        lblCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCC.setPreferredSize(new java.awt.Dimension(0, 0));
        lblCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCCMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblCC, gridBagConstraints);

        lblTC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTC.setText("-");
        lblTC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTC.setPreferredSize(new java.awt.Dimension(0, 0));
        lblTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTCMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblTC, gridBagConstraints);

        lblRC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRC.setText("-");
        lblRC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRC.setPreferredSize(new java.awt.Dimension(0, 0));
        lblRC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRCMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblRC, gridBagConstraints);

        lblCT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCT.setText("Pruebas");
        lblCT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCT.setPreferredSize(new java.awt.Dimension(0, 0));
        lblCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCTMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblCT, gridBagConstraints);

        lblTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTT.setText("-");
        lblTT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTT.setPreferredSize(new java.awt.Dimension(0, 0));
        lblTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTTMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblTT, gridBagConstraints);

        lblRT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRT.setText("-");
        lblRT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRT.setPreferredSize(new java.awt.Dimension(0, 0));
        lblRT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRTMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblRT, gridBagConstraints);

        lblCS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCS.setText("Envios");
        lblCS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCS.setPreferredSize(new java.awt.Dimension(0, 0));
        lblCS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCSMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblCS, gridBagConstraints);

        lblTS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTS.setText("-");
        lblTS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTS.setPreferredSize(new java.awt.Dimension(0, 0));
        lblTS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTSMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblTS, gridBagConstraints);

        lblRS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRS.setText("-");
        lblRS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRS.setPreferredSize(new java.awt.Dimension(0, 0));
        lblRS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRSMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblRS, gridBagConstraints);

        lblCF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCF.setText("Final");
        lblCF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCF.setPreferredSize(new java.awt.Dimension(0, 0));
        lblCF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCFMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblCF, gridBagConstraints);

        lblTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTF.setText("-");
        lblTF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTF.setPreferredSize(new java.awt.Dimension(0, 0));
        lblTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTFMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblTF, gridBagConstraints);

        lblRF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRF.setText("-");
        lblRF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRF.setPreferredSize(new java.awt.Dimension(0, 0));
        lblRF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRFMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(lblRF, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 682;
        gridBagConstraints.ipady = 175;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlCuerpo.add(pnlChecks, gridBagConstraints);

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
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
      cambio1();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        //si se guarda el trabajo
        //se actualiza la lista de userSel
        System.out.println("--------------------------------------------------------------------------" + serie);
        if (serie == 0) {
            System.out.println("Insert");
            //insert
            if (!txtSerie.getText().toString().equals(tSerie)) {
                if (txtPlazo.getDate() != null) {
                    if (!txtNombre.getText().toString().equals(tNombre)) {
                        if (!storage.getResponsableTC().equals("")) {
                            if (!storage.getResponsableTT().equals("")) {
                                if (!storage.getResponsableTS().equals("")) {
                                    if (!storage.getResponsableTF().equals("")) {
                                        System.out.println("Seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerie: " + serie);
                                        //comrpobar que no exista ya la orden
                                        leerT();
                                    } else {
                                        System.out.println("Final");
                                        //Seleccione una plantilla para el checklist de final
                                        //btnGuardar.setEnabled(true);
                                        // new showToast(getString(R.string.lblSelectFinalCheckListVistaTrabajosSpn), getString(R.string.lblSelectFinalCheckListVistaTrabajosEng), idioma, this);
                                    }
                                } else {
                                    System.out.println("Shipment");
                                    //Seleccione una plantilla para el checklist de shipment
                                    // btnGuardar.setEnabled(true);
                                    // new showToast(getString(R.string.lblSelectShipmentCheckListVistaTrabajosSpn), getString(R.string.lblSelectShipmentCheckListVistaTrabajosEng), idioma, this);
                                }
                            } else {
                                System.out.println("Testing");
                                //Seleccione una plantilla para el checklist de testing
                                //  btnGuardar.setEnabled(true);
                                //  new showToast(getString(R.string.lblSelectTestingCheckListVistaTrabajosSpn), getString(R.string.lblSelectTestingCheckListVistaTrabajosEng), idioma, this);
                            }
                        } else {
                            System.out.println("Calidad");
                            //Seleccione una plantilla para el checklist de calidad
                            // btnGuardar.setEnabled(true);
                            //  new showToast(getString(R.string.lblSelectQualityCheckListVistaTrabajosSpn), getString(R.string.lblSelectQualityCheckListVistaTrabajosEng), idioma, this);
                        }
                    } else {
                        System.out.println("Campo modelo");
                        //llene el campo de modelo
                        //  btnGuardar.setEnabled(true);
                        //  new showToast(getString(R.string.lblModelBlankVistaTrabajosSpn), getString(R.string.lblModelBlankVistaTrabajosEng), idioma, this);
                    }
                } else {
                    System.out.println("Campo fecha");
                    //llene el campo de fecha
                    // btnGuardar.setEnabled(true);
                    //  new showToast(getString(R.string.lblDateBlankVistaTrabajosSpn), getString(R.string.lblDateBlankVistaTrabajosEng), idioma, this);
                }
            } else {
                System.out.println("Campos erie");
                //llene el campo de serie
                // btnGuardar.setEnabled(true);
                // new showToast(getString(R.string.lblSerialBlankVistaTrabajosSpn), getString(R.string.lblSerialBlankVistaTrabajosEng), idioma, this);
            }
        } else {
            //update
            update();
            System.out.println("Uptodate");
            //   new info().setXY(this.getX(), this.getY());
            //  this.setCursor(new Cursor(WAIT_CURSOR));
        }
        System.out.println("Salio");


    }//GEN-LAST:event_btnAddActionPerformed

    private void txtSerieFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSerieFocusGained
        // TODO add your handling code here:
        if (txtSerie.getText().equals(tSerie)) {
            txtSerie.setText("");
            txtSerie.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtSerieFocusGained

    private void txtSerieFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSerieFocusLost
        // TODO add your handling code here:
        //validacion y estilizacion del txtSerie
        JTextField t = txtSerie;
        if (!t.getText().equals("")) {
            try {
                if (t.getText().length() > 5) {
                    t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                } else {
                    t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
            } catch (Exception e) {
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
        } else {
            t.setText(tSerie);
            t.setForeground(Color.lightGray);
            t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
    }//GEN-LAST:event_txtSerieFocusLost

    private void txtSerieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerieKeyTyped
        // TODO add your handling code here:

        if (txtSerie.getText().length() == 5) {
            evt.consume();
        }
        char caracter = evt.getKeyChar();
        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0')
                || (caracter > '9'))) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_txtSerieKeyTyped

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        // TODO add your handling code here:
        if (txtNombre.getText().equals(tNombre)) {
            txtNombre.setText("");
            txtNombre.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
        JTextField t = txtNombre;
        if (!t.getText().equals("")) {
            try {
                //int h = Integer.parseInt(t.getText());
                if (t.getText().length() > 50) {
                    t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                } else {
                    t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
            } catch (Exception e) {
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
        } else {
            t.setText(tNombre);
            t.setForeground(Color.lightGray);
            t.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        }
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if (txtNombre.getText().length() == 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void lblCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCCMouseClicked
        // TODO add your handling code here:
        cambio(1);
    }//GEN-LAST:event_lblCCMouseClicked

    private void lblTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTCMouseClicked
        // TODO add your handling code here:
        cambio(1);
    }//GEN-LAST:event_lblTCMouseClicked

    private void lblRCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRCMouseClicked
        // TODO add your handling code here:
        cambio(1);
    }//GEN-LAST:event_lblRCMouseClicked

    private void lblCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCTMouseClicked
        // TODO add your handling code here:
        cambio(2);
    }//GEN-LAST:event_lblCTMouseClicked

    private void lblTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTTMouseClicked
        // TODO add your handling code here:
        cambio(2);
    }//GEN-LAST:event_lblTTMouseClicked

    private void lblRTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRTMouseClicked
        // TODO add your handling code here:
        cambio(2);
    }//GEN-LAST:event_lblRTMouseClicked

    private void lblCSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCSMouseClicked
        // TODO add your handling code here:
        cambio(3);
    }//GEN-LAST:event_lblCSMouseClicked

    private void lblTSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTSMouseClicked
        // TODO add your handling code here:
        cambio(3);
    }//GEN-LAST:event_lblTSMouseClicked

    private void lblRSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRSMouseClicked
        // TODO add your handling code here:
        cambio(3);
    }//GEN-LAST:event_lblRSMouseClicked

    private void lblCFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCFMouseClicked
        // TODO add your handling code here:
        cambio(4);
    }//GEN-LAST:event_lblCFMouseClicked

    private void lblTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTFMouseClicked
        // TODO add your handling code here:
        cambio(4);
    }//GEN-LAST:event_lblTFMouseClicked

    private void lblRFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRFMouseClicked
        // TODO add your handling code here:
        cambio(4);
    }//GEN-LAST:event_lblRFMouseClicked

    private void cambio1() {
        new info().setXY(this.getX(), this.getY());
        storage.inicializarTodo();
        this.setCursor(new Cursor(WAIT_CURSOR));
        new MenuAgregarModificarOrdenes(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }

    private void update() {
        //primero se elimina todo lo relacionado al trabajo y luego se hace insert
        //eliminar
        con.child("trabajos").child("" + serie).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
                insert(false);
            }
        });

    }

    private void leerT() {
        try {
            Query query = null;
            query = con.child("trabajos").child("" + txtSerie.getText());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        storage.setSerie(Integer.parseInt(txtSerie.getText()));
                        insert(true);
                    } else {
                        System.out.println("Ya existexd");
                        //  btnGuardar.setEnabled(true);
                        // new showToast(getString(R.string.lblVistaTrabajosExistsSpn), getString(R.string.lblVistaTrabajosExistsEng), idioma, vistaAgregarModificarOrdenes.this);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.out.println("Cancelado: " + error);
                    // btnGuardar.setEnabled(true);
                    //  new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + " :" + error, getString(R.string.lblErrorWhileReadingDBEng) + " :" + error, idioma, vistaAgregarModificarOrdenes.this);
                }
            });
        } catch (Exception e) {
            System.out.println("Exc: " + e);
            // btnGuardar.setEnabled(true);
            // new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + " :" + e, getString(R.string.lblErrorWhileReadingDBEng) + " :" + e, idioma, vistaAgregarModificarOrdenes.this);

        }

    }

    private void insertTC(DatabaseReference db) {
        checkTC checkTC = new checkTC(storage.getResponsableTC(), storage.getPlantillaTC(), storage.getPorcentajeTC());
        db.setValue(checkTC, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
            }
        });
        db = db.child("actividades");
        checkAC checkAC = null;
        checkCommentsC com = null;
        int cont = 0;
        ArrayList<String> usu = storage.getUsuariosTC();
        ArrayList<String> mensaje = storage.getMensajeTC();
        ArrayList<Integer> req = storage.getRequisitosTC();
        ArrayList<Boolean> aprobado = storage.getAprobadoTC();
        ArrayList<Boolean> revsol = storage.getRevsolTC();
        for (String actividad : storage.getUbicacionTC()) {
            String mensaj = "";
            boolean aprob = false, revs = false;
            try {
                aprob = aprobado.get(cont);
            } catch (Exception e) {
            }
            try {
                revs = revsol.get(cont);
            } catch (Exception e) {
            }
            try {
                mensaj = mensaje.get(cont);
            } catch (Exception e) {
            }
            checkAC = new checkAC(actividad, usu.get(cont), revs, aprob, req.get(cont), mensaj);
            db.child("" + cont).setValue(checkAC, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                }
            });
            if (storage.getComentariosTC().get(actividad) != null) {
                int i = 0;
                for (String comentario : storage.getComentariosTC().get(actividad)) {
                    com = new checkCommentsC(comentario);
                    db.child("" + cont).child("comentario").child("" + i).setValue(com, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError de, DatabaseReference dr) {
                        }
                    });
                    i++;
                }
            }

            cont++;
        }
    }

    private void insertTT(DatabaseReference db) {
        checkTT checkTT = new checkTT(storage.getResponsableTT(), storage.getPlantillaTT(), storage.getPilotoTT(), storage.getPorcentajeTT());
        db.setValue(checkTT, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
            }
        });//.child(serie)
        db = db.child("actividades");
        checkAT checkAT = null;
        ArrayList<String> usu = storage.getUsuariosTT();
        ArrayList<String> usu1 = storage.getUsuarios1TT();
        ArrayList<String> rend = storage.getRendimientoTT();
        ArrayList<String> act = storage.getActividadesTT();
        ArrayList<Integer> req = storage.getRequisitosTT();
        ArrayList<Integer> req1 = storage.getRequisitosTT1();
        ArrayList<String> pondera = storage.getPonderacionTT();
        ArrayList<Boolean> real = storage.getRealizadoTT();
        ArrayList<Boolean> aproba = storage.getAprobacionTT();
        ArrayList<String> comenta = storage.getComentarioTT();
        ArrayList<String> comenta1 = storage.getComentarioTT1();
        int sizeAct = act.size(), sizeRend = rend.size();
        int max = (sizeAct > sizeRend) ? sizeAct : sizeRend;

        for (int cont = 0; cont < max; cont++) {
            String actividad = "", usuario = "", rendimiento = "", usuario1 = "", ponderacion = "", comentario = "", comentario1 = "";
            int requisito = 2, requisito1 = 2;
            boolean realizado = false, aprobacion = false;

            if (cont < sizeAct) {
                actividad = act.get(cont);
                usuario = usu.get(cont);
                requisito = req.get(cont);
                try {
                    comentario = comenta.get(cont);
                } catch (Exception e) {
                }
                try {
                    realizado = real.get(cont);
                } catch (Exception e) {
                }
                try {
                    aprobacion = aproba.get(cont);
                } catch (Exception e) {
                }
            }
            if (cont < sizeRend) {
                rendimiento = rend.get(cont);
                usuario1 = usu1.get(cont);
                requisito1 = req1.get(cont);
                try {
                    comentario1 = comenta1.get(cont);
                } catch (Exception e) {
                }
                try {
                    ponderacion = pondera.get(cont);
                } catch (Exception e) {
                    ponderacion = "N/A";
                }
            }
            checkAT = new checkAT(actividad, usuario, realizado, aprobacion, requisito, comentario, rendimiento, usuario1, ponderacion, requisito1, comentario1);
            db.child("" + cont).setValue(checkAT, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                }
            });
        }
    }

    private void insertTS(DatabaseReference db) {
        checkTS checkTS = new checkTS(storage.getResponsableTS(), storage.getPlantillaTS(), storage.getPaqueteTS(), storage.getCaseTS(), storage.getProductoTS(), storage.getMensajeTS(), storage.isAirtagTS(), storage.getPorcentajeTS());
        db.setValue(checkTS, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
            }
        });
        db = db.child("actividades");
        checkAS checkAS = null;
        int cont = 0;
        ArrayList<String> usu = storage.getUsuariosTS();
        ArrayList<Integer> req = storage.getRequisitosTS();
        ArrayList<Integer> completo = storage.getCompletadoTS();
        ArrayList<String> comentarios = storage.getComentarioTS();
        for (String actividad : storage.getAccesoriosTS()) {
            int comple = 0;
            String comentario = "";
            try {
                comple = completo.get(cont);
            } catch (Exception e) {
            }
            try {
                comentario = comentarios.get(cont);
            } catch (Exception e) {
            }
            checkAS = new checkAS(actividad, usu.get(cont), comple, req.get(cont), comentario);//.child(serie)
            db.child("" + cont).setValue(checkAS, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                }
            });
            cont++;
        }
    }

    private void insertTF(DatabaseReference db) {
        checkTF checkTF = new checkTF(storage.getResponsableTF(), storage.getPlantillaTF(), storage.getPorcentajeTF());//.child(serie)
        db.setValue(checkTF, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
            }
        });
        db = db.child("actividades");
        checkAF checkAF = null;
        int cont = 0;
        ArrayList<String> usu = storage.getUsuariosTF();
        ArrayList<Integer> req = storage.getRequisitosTF();
        ArrayList<Integer> comp = storage.getCompletadoTF();
        ArrayList<String> com = storage.getComentarioTF();
        for (String actividad : storage.getActividadesTF()) {
            int comple = 0;
            String comentario = "";
            try {
                comple = comp.get(cont);
            } catch (Exception e) {
            }
            try {
                comentario = com.get(cont);
            } catch (Exception e) {
            }
            checkAF = new checkAF(actividad, usu.get(cont), comple, req.get(cont), comentario);//.child(serie)
            db.child("" + cont).setValue(checkAF, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                }
            });
            cont++;
        }
    }

    private void insertTrabajo(DatabaseReference db) {
        //convertir fecha a texto
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(txtPlazo.getDate());
        ordenes trabajo = new ordenes(strDate, txtNombre.getText().toString(), storage.getProgreso());//.child(serie)
        db.setValue(trabajo, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
            }
        });
    }

    private void insert(boolean nuevo) {
        String serie = txtSerie.getText().toString();
        //poner callback para que primero ingrese trabajos y cuando termine suba el resto
        insertTrabajo(con.child("Trabajos").child(serie));
        insertTC(con.child("Trabajos").child(serie).child("checkTC"));
        insertTT(con.child("Trabajos").child(serie).child("checkTT"));
        insertTS(con.child("Trabajos").child(serie).child("checkTS"));
        insertTF(con.child("Trabajos").child(serie).child("checkTF"));
        sendNot(getInvolvedUsers(), nuevo);
        cambio1();
    }

    private ArrayList<String> getInvolvedUsers() {
        ArrayList<String> inUsers = new ArrayList<>();
        for (String user : storage.getUsuariosTS()) {
            if (!inUsers.contains(user)) {
                inUsers.add(user);
            }
        }
        for (String user : storage.getUsuariosTT()) {
            if (!inUsers.contains(user)) {
                inUsers.add(user);
            }
        }
        for (String user : storage.getUsuarios1TT()) {
            if (!inUsers.contains(user)) {
                inUsers.add(user);
            }
        }
        for (String user : storage.getUsuariosTC()) {
            if (!inUsers.contains(user)) {
                inUsers.add(user);
            }
        }
        for (String user : storage.getUsuariosTF()) {
            if (!inUsers.contains(user)) {
                inUsers.add(user);
            }
        }
        return inUsers;
    }

    private void sendNot(ArrayList<String> InUsers, boolean nuevo) {
        try {
            String titulo, contenido;//topic
            if (nuevo) {
                titulo = "Se ha generado una nueva orden: " + storage.getSerie();
            } else {
                titulo = "Se ha modificado la orden: " + storage.getSerie();
            }
            contenido = "Haga click para abrir la aplicacion";
            //topic = "supervisor";
            for (String user : InUsers) {
                if (!user.equals(this.user)) {
                    Query query = con.child("keys").orderByChild("usuario").equalTo(user);
                    //.child(bus);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                keys dato = null;
                                for (DataSnapshot user : snapshot.getChildren()) {
                                    dato = user.getValue(keys.class);
                                    new notificar().notificaT(titulo, contenido, dato.getKey());
                                    //  new notify().notificaT(vistaAgregarModificarOrdenes.this, titulo, contenido, dato.getKey());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + error, getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, getApplicationContext());
                        }
                    });
                }
            }
        } catch (Exception e) {
            //  new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + " :" + e, getString(R.string.lblErrorWhileReadingDBEng) + " :" + e, idioma, vistaAgregarModificarOrdenes.this);
        }

    }

    private void cambio(int check) {
        if (!txtNombre.getText().equals(tNombre)) {
            storage.setNombre(txtNombre.getText());
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(txtPlazo.getDate());
        storage.setFecha(strDate);
        try {
            storage.setSerie(Integer.parseInt(txtSerie.getText()));
        } catch (NumberFormatException s) {
        }

        this.setCursor(new Cursor(WAIT_CURSOR));
        new info().setXY(this.getX(), this.getY());
        boolean val = (serie==0) ? true : false;
        
        switch (check) {
            case 1:
                new crearTC(con, user, priv, idioma, serie, val).setVisible(true);
                this.dispose();
                break;
            case 2:
                new crearTT(con, user, priv, idioma, serie, val).setVisible(true);
                this.dispose();
                break;
            case 3:
                new crearTS(con, user, priv, idioma, serie, val).setVisible(true);//con, user, priv, idioma, serie, val
                this.dispose();
                break;
            case 4:
                new crearTF(con, user, priv, idioma, serie, val).setVisible(true);
                this.dispose();
                break;
        }
    }

    private void leerExistente() {
        try {
            //  HashMap<String, ArrayList<String>> hm = new HashMap<>();
            Query query = con.child(json.getString("trabajosTable")).child("" + serie);
            //.child(bus);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    try {
                        if (snapshot.exists()) {
                            //   storage.inicializarTC();
                            //   storage.inicializarBasico();
                            //   storage.inicializarTS();
                            // //   storage.inicializarTT();
                            //   storage.inicializarTF();
                            ordenes log;
                            log = snapshot.getValue(ordenes.class);
                            //Basico
                            storage.setSerie(serie);
                            storage.setFecha(log.getFecha());
                            storage.setNombre(log.getNombre());
                            storage.setProgreso(log.getProgreso());
                            Map<String, Object> value = (Map<String, Object>) snapshot.getValue();
                            JSONObject allData = new JSONObject(value);

                            //TC
                            fillTC(allData);
                            //TT
                            fillTT(allData);
                            //TS
                            fillTS(allData);
                            //TF
                            fillTF(allData);
                        } else {
                            //No hay trabajos agregados aun
                            // new showToast(getString(R.string.noTrabajosEncontradosSpn), getString(R.string.noTrabajosEncontradosEng), idioma, getApplicationContext());
                        }
                        if (idioma.equals("English")) {
                            ingles();//cambia la interfaz a ingles
                        } else {
                            esp();//cambia la interfaz a espanol
                        }
                        txtSerie.setForeground(Color.black);
                        txtNombre.setForeground(Color.black);
                        txtSerie.setText("" + serie);
                        //2023-01-30
                        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(storage.getFecha());
                        txtPlazo.setDate(date1);
                        txtNombre.setText(storage.getNombre());
                        // mostrar(idioma);

                    } catch (Exception ex) {
                        System.out.println("Excepction: " + ex);
                        //  Logger.getLogger(vistaActividades.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + error, getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, getApplicationContext());
                }
            });
        } catch (Exception e) {

            //  new showToast(getString(R.string.lblErrorWhileReadingDBSpn) + " :" + e, getString(R.string.lblErrorWhileReadingDBEng) + " :" + e, idioma, vistaAgregarModificarOrdenes.this);
        }
    }

    private void fillTC(JSONObject allData) {//read TC info
        try {
            //
            HashMap checkTC = (HashMap) allData.get(json.getString("trabajosTableCheckTC"));
            ArrayList actividades = (ArrayList) checkTC.get(json.getString("trabajosTableCheckTCActividades"));
            ArrayList<String> usuarios = new ArrayList<>();
            ArrayList<String> actividad = new ArrayList<>();
            ArrayList<String> mensaje = new ArrayList<>();
            ArrayList<Boolean> revsol = new ArrayList<>();
            ArrayList<Boolean> aprobado = new ArrayList<>();
            ArrayList<Integer> requisito = new ArrayList<>();
            HashMap<String, ArrayList<String>> comenta = new HashMap<>();
            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);

                usuarios.add(jsonTC.get(json.getString("trabajosTableCheckTCActividadesUsu")).toString());
                actividad.add(jsonTC.get(json.getString("trabajosTableCheckTCActividadesAct")).toString());
                revsol.add(Boolean.parseBoolean(jsonTC.get(json.getString("trabajosTableCheckTCActividadesRevSol")).toString()));
                aprobado.add(Boolean.parseBoolean(jsonTC.get(json.getString("trabajosTableCheckTCActividadesApr")).toString()));
                mensaje.add(jsonTC.get(json.getString("trabajosTableCheckTCActividadesMen")).toString());
                requisito.add(Integer.parseInt(jsonTC.get(json.getString("trabajosTableCheckTCActividadesReq")).toString()));
                try {
                    ArrayList<String> come = new ArrayList<>();
                    ArrayList coments = (ArrayList) jsonTC.get(jsonTC.get(json.getString("trabajosTableCheckTCActividadesComentarios")).toString());
                    for (int j = 0; j < coments.size(); j++) {
                        HashMap jsonCom = (HashMap) coments.get(j);
                        come.add(jsonCom.get(jsonTC.get(json.getString("trabajosTableCheckTCActividadesComentariosCom")).toString()).toString());
                    }
                    comenta.put(jsonTC.get(json.getString("trabajosTableCheckTCActividadesAct")).toString(), come);
                } catch (Exception e) {

                }
            }
            storage.setUbicacionTC(actividad);
            storage.setUsuariosTC(usuarios);
            storage.setPlantillaTC(checkTC.get(json.getString("trabajosTableCheckTCPlantilla")).toString());
            storage.setResponsableTC(checkTC.get(json.getString("trabajosTableCheckTCResponsable")).toString());
            storage.setPorcentajeTC(Double.parseDouble(checkTC.get(json.getString("trabajosTableCheckTCPorcentaje")).toString()));
            // storage.setCompletadoTC(completado);
            storage.setAprobadoTC(aprobado);
            storage.setRevsolTC(revsol);
            storage.setMensajeTC(mensaje);
            storage.setRequisitosTC(requisito);
            storage.setComentariosTC(comenta);

            /*   System.out.println("Plantilla: " + storage.getPlantillaTC());
            System.out.println("Responsable: " + storage.getResponsableTC());
            System.out.println("Ubicacion: " + storage.getUbicacionTC());
            System.out.println("Usuarios: " + storage.getUsuariosTC());
            //   System.out.println("Completado: "+storage.getCompletadoTC());
            System.out.println("Revsol: " + storage.getRevsolTC());
            System.out.println("Aprobado: " + storage.getAprobadoTC());
            System.out.println("mensaje: " + mensaje);
            System.out.println("Requisitos: " + storage.getRequisitosTC());
            System.out.println("Comentarios: " + storage.getComentariosTC()); */
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooor12: " + e);
        }
    }

    private void fillTT(JSONObject allData) {//read TT info
        try {

            HashMap checkTT = (HashMap) allData.get(json.getString("trabajosTableCheckTT"));

            ArrayList actividades = (ArrayList) checkTT.get(json.getString("trabajosTableCheckTTActividades"));
            // JSONArray rendimiento = checkTT.getJSONArray(getString(R.string.trabajosTableCheckTTRendimiento));

            ArrayList<String> usuarios = new ArrayList<>();
            ArrayList<String> actividad = new ArrayList<>();
            ArrayList<Integer> requisito = new ArrayList<>();
            ArrayList<Boolean> realizado = new ArrayList<>();
            ArrayList<Boolean> aprobacion = new ArrayList<>();
            ArrayList<String> usuarios1 = new ArrayList<>();
            ArrayList<String> rend = new ArrayList<>();
            ArrayList<String> ponderacion = new ArrayList<>();
            ArrayList<Integer> requisito1 = new ArrayList<>();
            ArrayList<String> comenta = new ArrayList();
            ArrayList<String> comenta1 = new ArrayList();
            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);

                if (!jsonTC.get(json.getString("trabajosTableCheckTTActividadesAct")).toString().equals("")) {
                    usuarios.add(jsonTC.get(json.getString("trabajosTableCheckTTActividadesUsu")).toString());
                    actividad.add(jsonTC.get(json.getString("trabajosTableCheckTTActividadesAct")).toString());
                    aprobacion.add(Boolean.parseBoolean(jsonTC.get(json.getString("trabajosTableCheckTTActividadesApr")).toString()));
                    realizado.add(Boolean.parseBoolean(jsonTC.get(json.getString("trabajosTableCheckTTActividadesRea")).toString()));
                    requisito.add(Integer.parseInt(jsonTC.get(json.getString("trabajosTableCheckTTActividadesReq")).toString()));
                    comenta.add(jsonTC.get(json.getString("trabajosTableCheckTTActividadesCom")).toString());
                }
                if (!jsonTC.get(json.getString("trabajosTableCheckTTActividadesRen")).toString().equals("")) {
                    usuarios1.add(jsonTC.get(json.getString("trabajosTableCheckTTActividadesUsu1")).toString());
                    rend.add(jsonTC.get(json.getString("trabajosTableCheckTTActividadesRen")).toString());
                    ponderacion.add(jsonTC.get(json.getString("trabajosTableCheckTTActividadesPon")).toString());
                    requisito1.add(Integer.parseInt(jsonTC.get(json.getString("trabajosTableCheckTTActividadesReq1")).toString()));
                    comenta1.add(jsonTC.get(json.getString("trabajosTableCheckTTActividadesCom1")).toString());
                }
            }
            System.out.println("Aqio");
            storage.setActividadesTT(actividad);
            storage.setUsuariosTT(usuarios);
            storage.setPonderacionTT(ponderacion);
            storage.setAprobacionTT(aprobacion);
            storage.setRealizadoTT(realizado);
            storage.setComentarioTT(comenta);
            storage.setComentarioTT1(comenta1);
            //  storage.setCompletadoTT(completado);
            storage.setRequisitosTT(requisito);
            storage.setRendimientoTT(rend);
            System.out.println("Rendimiento: " + storage.getRendimientoTT());
            System.out.println("Actividades: " + storage.getActividadesTT());
            storage.setUsuarios1TT(usuarios1);
            //    storage.setCompletado1TT(completado1);
            storage.setRequisitosTT1(requisito1);

            storage.setPlantillaTT(checkTT.get(json.getString("trabajosTableCheckTTPlantilla")).toString());
            storage.setResponsableTT(checkTT.get(json.getString("trabajosTableCheckTTResponsable")).toString());
            storage.setPilotoTT(checkTT.get(json.getString("trabajosTableCheckTTPiloto")).toString());
            storage.setPorcentajeTT(Double.parseDouble(checkTT.get(json.getString("trabajosTableCheckTTPorcentaje")).toString()));


            /*  System.out.println("Plantilla: " + storage.getPlantillaTT());
            System.out.println("Responsable: " + storage.getResponsableTT());
            System.out.println("Piloto: " + storage.getPilotoTT());
            System.out.println("Actividades: " + storage.getActividadesTT());
            System.out.println("Usuarios: " + storage.getUsuariosTT());
            //  System.out.println("Completado: "+storage.getCompletadoTT());
            System.out.println("Requisitos: " + storage.getRequisitosTT());
            System.out.println("Rendimiento: " + storage.getRendimientoTT());
            System.out.println("Usuarios1: " + storage.getUsuarios1TT());
            //   System.out.println("Completado1: "+storage.getCompletado1TT());
            System.out.println("Requisitos1: " + storage.getRequisitosTT1()); */
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooor32: " + e);
        }
    }

    private void fillTS(JSONObject allData) {//read TT info
        try {

            HashMap checkTS = (HashMap) allData.get(json.getString("trabajosTableCheckTS"));
            ArrayList actividades = (ArrayList) checkTS.get(json.getString("trabajosTableCheckTSActividades"));
            // JSONArray rendimiento = checkTT.getJSONArray(getString(R.string.trabajosTableCheckTTRendimiento));
            ArrayList<String> usuarios = new ArrayList<>();
            ArrayList<String> actividad = new ArrayList<>();
            ArrayList<Integer> completado = new ArrayList<>();
            ArrayList<Integer> requisito = new ArrayList<>();
            ArrayList<String> comentario = new ArrayList<>();
            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);

                usuarios.add(jsonTC.get(json.getString("trabajosTableCheckTSActividadesUsu")).toString());
                actividad.add(jsonTC.get(json.getString("trabajosTableCheckTSActividadesAcc")).toString());
                completado.add(Integer.parseInt(jsonTC.get(json.getString("trabajosTableCheckTSActividadesCom")).toString()));
                requisito.add(Integer.parseInt(jsonTC.get(json.getString("trabajosTableCheckTSActividadesReq")).toString()));
                comentario.add(jsonTC.get(json.getString("trabajosTableCheckTSActividadesCome")).toString());
            }
            storage.setAccesoriosTS(actividad);
            storage.setUsuariosTS(usuarios);
            storage.setCompletadoTS(completado);
            storage.setRequisitosTS(requisito);
            storage.setComentarioTS(comentario);

            storage.setPlantillaTS(checkTS.get(json.getString("trabajosTableCheckTSPlantilla")).toString());
            storage.setResponsableTS(checkTS.get(json.getString("trabajosTableCheckTSResponsable")).toString());
            storage.setPorcentajeTS(Double.parseDouble(checkTS.get(json.getString("trabajosTableCheckTSPorcentaje")).toString()));
            storage.setCaseTS(checkTS.get(json.getString("trabajosTableCheckTSCaja")).toString());
            storage.setAirtagTS(Boolean.parseBoolean(checkTS.get(json.getString("trabajosTableCheckTSAirTag")).toString()));
            storage.setProductoTS(checkTS.get(json.getString("trabajosTableCheckTSProducto")).toString());
            storage.setPaqueteTS(checkTS.get(json.getString("trabajosTableCheckTSPaquete")).toString());
            storage.setMensajeTS(checkTS.get(json.getString("trabajosTableCheckTSMensaje")).toString());

            /*   System.out.println("Plantilla: " + storage.getPlantillaTS());
            System.out.println("Responsable: " + storage.getResponsableTS());
            System.out.println("Caja: " + storage.getCaseTS());
            System.out.println("Mensaje: " + storage.getMensajeTS());
            System.out.println("paquete: " + storage.getPaqueteTS());
            System.out.println("Producto: " + storage.getProductoTS());
            System.out.println("AirTag: " + storage.isAirtagTS());

            System.out.println("Actividades: " + storage.getAccesoriosTS());
            System.out.println("Usuarios: " + storage.getUsuariosTS());
            System.out.println("Completado: " + storage.getCompletadoTS());
            System.out.println("Requisitos: " + storage.getRequisitosTS());*/
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooor43: " + e);
        }
    }

    private void fillTF(JSONObject allData) {//read TT info
        try {
            HashMap checkTF = (HashMap) allData.get(json.getString("trabajosTableCheckTF"));
            ArrayList actividades = (ArrayList) checkTF.get(json.getString("trabajosTableCheckTFActividades"));
            // JSONArray rendimiento = checkTT.getJSONArray(getString(R.string.trabajosTableCheckTTRendimiento));
            ArrayList<String> usuarios = new ArrayList<>();
            ArrayList<String> actividad = new ArrayList<>();
            ArrayList<Integer> completado = new ArrayList<>();
            ArrayList<Integer> requisito = new ArrayList<>();
            ArrayList<String> comentario = new ArrayList<>();

            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                usuarios.add(jsonTC.get(json.getString("trabajosTableCheckTFActividadesUsu")).toString());
                actividad.add(jsonTC.get(json.getString("trabajosTableCheckTFActividadesAct")).toString());
                comentario.add(jsonTC.get(json.getString("trabajosTableCheckTFActividadesCome")).toString());
                completado.add(Integer.parseInt(jsonTC.get(json.getString("trabajosTableCheckTFActividadesCom")).toString()));
                requisito.add(Integer.parseInt(jsonTC.get(json.getString("trabajosTableCheckTFActividadesReq")).toString()));
            }
            storage.setActividadesTF(actividad);
            storage.setUsuariosTF(usuarios);
            storage.setCompletadoTF(completado);
            storage.setRequisitosTF(requisito);
            storage.setComentarioTF(comentario);
            storage.setPlantillaTF(checkTF.get(json.getString("trabajosTableCheckTFPlantilla")).toString());
            storage.setResponsableTF(checkTF.get(json.getString("trabajosTableCheckTFResponsable")).toString());
            storage.setPorcentajeTF(Double.parseDouble(checkTF.get(json.getString("trabajosTableCheckTFPorcentaje")).toString()));

            /*   System.out.println("Plantilla: " + storage.getPlantillaTF());
            System.out.println("Responsable: " + storage.getResponsableTF());

            System.out.println("Actividades: " + storage.getActividadesTF());
            System.out.println("Usuarios: " + storage.getUsuariosTF());
            System.out.println("Completado: " + storage.getCompletadoTF());
            System.out.println("Requisitos: " + storage.getRequisitosTF());*/
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooor42: " + e);
        }
    }

    private void ingles() {
        lblTitulo.setText("Activities view");

        lblSerie.setText("Serial N.");
        lblPlazo.setText("Deadline");
        lblNombre.setText("Time");

        tSerie = "Ex. 12345";
        tNombre = "Ex. Aurelia X6";
        txtSerie.setText(tSerie);
        txtNombre.setText(tNombre);

        lblSerie.setText("Serial Number");

        lblPlazo.setText("Date");
        lblNombre.setText("Model");
        lblCheckLists.setText("Checklists");
        lblPlantillas.setText("Templates");
        lblResponsable.setText("Responsible");
        lblCC.setText("Quality");
        lblCS.setText("Shipment");
        lblCT.setText("Testing");
        lblCF.setText("Final");
        String interf = "";
        try {
            interf = "shipment";
            if (!storage.getResponsableTS().equals("")) {
                lblRS.setText(storage.getResponsableTS());
                lblTS.setText(storage.getPlantillaTS());
            }
            interf = "testing";

            if (!storage.getResponsableTT().equals("")) {
                lblRT.setText(storage.getResponsableTT());
                lblTT.setText(storage.getPlantillaTT());
            }
            interf = "final";
            if (!storage.getResponsableTF().equals("")) {
                lblRF.setText(storage.getResponsableTF());
                lblTF.setText(storage.getPlantillaTF());
            }
            interf = "quality";
            if (!storage.getResponsableTC().equals("")) {
                lblRC.setText(storage.getResponsableTC());
                lblTC.setText(storage.getPlantillaTC());
            }
        } catch (Exception e) {
            //  Toast.makeText(this, "There was an error while reading data in " + interf + " interface", Toast.LENGTH_SHORT).show();

        }
    }

    private void esp() {
        lblTitulo.setText("Vista de actividades");
        tSerie = "Ej. 12345";
        tNombre = "Ej. Aurelia X6";
        txtSerie.setText(tSerie);
        txtNombre.setText(tNombre);

        lblSerie.setText("No. serie");
        // txtSerie.setHint("Ej. 12345");
        lblPlazo.setText("Fecha");
        // txtPlazo.setHint("aaaa-mm-dd");
        lblNombre.setText("Modelo");
        // txtNombre.setHint("Ej. X8");
        lblCheckLists.setText("Checklists");
        lblPlantillas.setText("Plantillas");
        lblResponsable.setText("Responsable");
        lblCC.setText("Calidad");
        lblCS.setText("Envios");
        lblCT.setText("Pruebas");
        lblCF.setText("Final");
        String interf = "";

        try {
            interf = "envios";
            if (!storage.getResponsableTS().equals("")) {
                lblRS.setText(storage.getResponsableTS());
                lblTS.setText(storage.getPlantillaTS());
            }
            interf = "pruebas";

            if (!storage.getResponsableTT().equals("")) {
                lblRT.setText(storage.getResponsableTT());
                lblTT.setText(storage.getPlantillaTT());
            }
            interf = "final";
            if (!storage.getResponsableTF().equals("")) {
                lblRF.setText(storage.getResponsableTF());
                lblTF.setText(storage.getPlantillaTF());
            }
            interf = "calidad";
            if (!storage.getResponsableTC().equals("")) {
                lblRC.setText(storage.getResponsableTC());
                lblTC.setText(storage.getPlantillaTC());
            }
        } catch (Exception e) {
            //  Toast.makeText(this, "There was an error while reading data in " + interf + " interface", Toast.LENGTH_SHORT).show();

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblCF;
    private javax.swing.JLabel lblCS;
    private javax.swing.JLabel lblCT;
    private javax.swing.JLabel lblCheckLists;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPlantillas;
    private javax.swing.JLabel lblPlazo;
    private javax.swing.JLabel lblRC;
    private javax.swing.JLabel lblRF;
    private javax.swing.JLabel lblRS;
    private javax.swing.JLabel lblRT;
    private javax.swing.JLabel lblResponsable;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblTC;
    private javax.swing.JLabel lblTF;
    private javax.swing.JLabel lblTS;
    private javax.swing.JLabel lblTT;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlChecks;
    private javax.swing.JPanel pnlCue;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTextField txtNombre;
    private com.toedter.calendar.JDateChooser txtPlazo;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
