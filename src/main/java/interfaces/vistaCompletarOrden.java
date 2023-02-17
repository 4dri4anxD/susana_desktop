package interfaces;

import com.google.firebase.database.DatabaseReference;
import datos.leerJSON;
import datos.temporalStorage;
import disenos.disenos;
import disenos.ventanas.configuracionVentana;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class vistaCompletarOrden extends JFrame {

    private DatabaseReference con;//conexion con la base de datos
    private String user, idioma, plantilla;//usuario que esta utilizando la app e idioma de esta
    private int priv, modo;//, contadorcito, contadorsote;//privilegios
    private int serie;//numero de serie del dron
    private String tSerie, tNombre;//hint
    private JFrame context;
    private boolean listo;//si ya se inicializo la interfaz
    private temporalStorage storage;
    private leerJSON json;
    

    public vistaCompletarOrden(DatabaseReference con, String user, int priv, String idioma, int serie, String plantilla, int modo) {
        initComponents();
        new configuracionVentana(this);
        
        
        lblSerie.setText(serie+" - "+plantilla);
        storage = new temporalStorage();
        json = new leerJSON();
        this.con = con;
        this.modo = modo;
        this.user = user;
        context = this;
        listo = false;
        this.priv = priv;
        this.idioma = idioma;
        this.plantilla=plantilla;
        this.serie = serie;

        iniciarDiseno();
    }

    public void iniciarDiseno() {
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        lblSerie.setHorizontalAlignment(JLabel.CENTER);
        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);


        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlChecks, 2);
        new disenos().fondo(pnlCue, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().fondoLabel(lblCheckLists, 1);
        new disenos().fondoLabel(lblPlantillas, 1);

        new disenos().fondoLabel(lblCC, 2);
        new disenos().fondoLabel(lblTC, 2);

        new disenos().fondoLabel(lblCT, 3);
        new disenos().fondoLabel(lblTT, 3);

        new disenos().fondoLabel(lblCS, 2);
        new disenos().fondoLabel(lblTS, 2);

        new disenos().fondoLabel(lblCF, 3);
        new disenos().fondoLabel(lblTF, 3);

        new disenos().titulo(lblTitulo, 2);
        new disenos().titulo(lblSerie, 6);
        new disenos().titulo(lblCheckLists, 5);
        new disenos().titulo(lblPlantillas, 5);
        new disenos().titulo(lblCC, 6);
        new disenos().titulo(lblTC, 6);
        new disenos().titulo(lblCT, 6);
        new disenos().titulo(lblTT, 6);
        new disenos().titulo(lblCS, 6);
        new disenos().titulo(lblTS, 6);
        new disenos().titulo(lblCF, 6);
        new disenos().titulo(lblTF, 6);

        ponerImg(btnAdd, "img/guardar1.png");
        ponerImg(btnAtras, "img/atras2.png");
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
        pnlChecks = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        lblCheckLists = new javax.swing.JLabel();
        lblPlantillas = new javax.swing.JLabel();
        pnlCalidad = new javax.swing.JPanel();
        lblCC = new javax.swing.JLabel();
        pnlTC = new javax.swing.JPanel();
        lblTC = new javax.swing.JLabel();
        pbCalidad = new javax.swing.JProgressBar();
        pblTesting = new javax.swing.JPanel();
        lblCT = new javax.swing.JLabel();
        pnlTT = new javax.swing.JPanel();
        lblTT = new javax.swing.JLabel();
        pbTesting = new javax.swing.JProgressBar();
        pnlShipment = new javax.swing.JPanel();
        lblCS = new javax.swing.JLabel();
        pnlTS = new javax.swing.JPanel();
        lblTS = new javax.swing.JLabel();
        pbShipment = new javax.swing.JProgressBar();
        pnlFinal = new javax.swing.JPanel();
        lblCF = new javax.swing.JLabel();
        pnlTF = new javax.swing.JPanel();
        lblTF = new javax.swing.JLabel();
        pbFinal = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2000, 1000));
        setPreferredSize(new java.awt.Dimension(1000, 700));

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCuerpo.add(pnlCue, gridBagConstraints);

        pnlChecks.setLayout(new java.awt.GridBagLayout());

        pnlTitulo.setLayout(new java.awt.GridBagLayout());

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
        pnlTitulo.add(lblCheckLists, gridBagConstraints);

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
        pnlTitulo.add(lblPlantillas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(pnlTitulo, gridBagConstraints);

        pnlCalidad.setLayout(new java.awt.GridBagLayout());

        lblCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCC.setText("Calidad");
        lblCC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCC.setPreferredSize(new java.awt.Dimension(0, 0));
        lblCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCCMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        pnlCalidad.add(lblCC, gridBagConstraints);

        pnlTC.setLayout(new java.awt.GridBagLayout());

        lblTC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTC.setText("-");
        lblTC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblTC.setPreferredSize(new java.awt.Dimension(0, 0));
        lblTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTCMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTC.add(lblTC, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTC.add(pbCalidad, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCalidad.add(pnlTC, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(pnlCalidad, gridBagConstraints);

        pblTesting.setLayout(new java.awt.GridBagLayout());

        lblCT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCT.setText("Pruebas");
        lblCT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCT.setPreferredSize(new java.awt.Dimension(0, 0));
        lblCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCTMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        pblTesting.add(lblCT, gridBagConstraints);

        pnlTT.setLayout(new java.awt.GridBagLayout());

        lblTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTT.setText("-");
        lblTT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblTT.setPreferredSize(new java.awt.Dimension(0, 0));
        lblTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTTMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTT.add(lblTT, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTT.add(pbTesting, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pblTesting.add(pnlTT, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(pblTesting, gridBagConstraints);

        pnlShipment.setLayout(new java.awt.GridBagLayout());

        lblCS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCS.setText("Envios");
        lblCS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCS.setPreferredSize(new java.awt.Dimension(0, 0));
        lblCS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCSMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        pnlShipment.add(lblCS, gridBagConstraints);

        pnlTS.setLayout(new java.awt.GridBagLayout());

        lblTS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTS.setText("-");
        lblTS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblTS.setPreferredSize(new java.awt.Dimension(0, 0));
        lblTS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTSMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTS.add(lblTS, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTS.add(pbShipment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlShipment.add(pnlTS, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(pnlShipment, gridBagConstraints);

        pnlFinal.setLayout(new java.awt.GridBagLayout());

        lblCF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCF.setText("Final");
        lblCF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCF.setPreferredSize(new java.awt.Dimension(0, 0));
        lblCF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCFMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        pnlFinal.add(lblCF, gridBagConstraints);

        pnlTF.setLayout(new java.awt.GridBagLayout());

        lblTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTF.setText("-");
        lblTF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblTF.setPreferredSize(new java.awt.Dimension(0, 0));
        lblTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTFMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTF.add(lblTF, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTF.add(pbFinal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlFinal.add(pnlTF, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlChecks.add(pnlFinal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 682;
        gridBagConstraints.ipady = 175;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
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
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
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
        //si se guarda el trabajo
        //se actualiza la lista de userSel
        /*
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
        System.out.println("Salio"); */

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
      //  cambio1();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void lblCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCCMouseClicked
        // TODO add your handling code here:
       // cambio(1);
    }//GEN-LAST:event_lblCCMouseClicked

    private void lblTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTCMouseClicked
        // TODO add your handling code here:
      //  cambio(1);
    }//GEN-LAST:event_lblTCMouseClicked

    private void lblCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCTMouseClicked
        // TODO add your handling code here:
       // cambio(2);
    }//GEN-LAST:event_lblCTMouseClicked

    private void lblTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTTMouseClicked
        // TODO add your handling code here:
        //cambio(2);
    }//GEN-LAST:event_lblTTMouseClicked

    private void lblCSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCSMouseClicked
        // TODO add your handling code here:
       // cambio(3);
    }//GEN-LAST:event_lblCSMouseClicked

    private void lblTSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTSMouseClicked
        // TODO add your handling code here:
      //  cambio(3);
    }//GEN-LAST:event_lblTSMouseClicked

    private void lblCFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCFMouseClicked
        // TODO add your handling code here:
        //cambio(4);
    }//GEN-LAST:event_lblCFMouseClicked

    private void lblTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTFMouseClicked
        // TODO add your handling code here:
       // cambio(4);
    }//GEN-LAST:event_lblTFMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblCF;
    private javax.swing.JLabel lblCS;
    private javax.swing.JLabel lblCT;
    private javax.swing.JLabel lblCheckLists;
    private javax.swing.JLabel lblPlantillas;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblTC;
    private javax.swing.JLabel lblTF;
    private javax.swing.JLabel lblTS;
    private javax.swing.JLabel lblTT;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JProgressBar pbCalidad;
    private javax.swing.JProgressBar pbFinal;
    private javax.swing.JProgressBar pbShipment;
    private javax.swing.JProgressBar pbTesting;
    private javax.swing.JPanel pblTesting;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCalidad;
    private javax.swing.JPanel pnlChecks;
    private javax.swing.JPanel pnlCue;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFinal;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JPanel pnlShipment;
    private javax.swing.JPanel pnlTC;
    private javax.swing.JPanel pnlTF;
    private javax.swing.JPanel pnlTS;
    private javax.swing.JPanel pnlTT;
    private javax.swing.JPanel pnlTitulo;
    // End of variables declaration//GEN-END:variables
}
