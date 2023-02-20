package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import datos.leerJSON;
import datos.temporalStorage;
import disenos.colores;
import disenos.disenos;
import disenos.ventanas.configuracionVentana;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.WAIT_CURSOR;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.json.simple.JSONObject;

public class vistaCompletarOrden extends JFrame {

    private final DatabaseReference con;//conexion con la base de datos
    private final String user, idioma, plantilla;//usuario que esta utilizando la app e idioma de esta
    private final int priv, modo;//, contadorcito, contadorsote;//privilegios
    private final int serie;//numero de serie del dron
    private final JFrame context;
    //private boolean listo;//si ya se inicializo la interfaz
    private final temporalStorage storage;
    private final leerJSON json;
    private final disenos disenos;

    public vistaCompletarOrden(DatabaseReference con, String user, int priv, String idioma, int serie, String plantilla, int modo) {
        initComponents();
        new configuracionVentana(this);
        this.disenos = new disenos();
        storage = new temporalStorage();
        json = new leerJSON();
        this.con = con;
        this.modo = modo;
        this.user = user;
        context = this;
        // listo = false;
        this.priv = priv;
        this.idioma = idioma;
        this.plantilla = plantilla;
        this.serie = serie;

        if (storage.getUbicacionTC() == null) {
            storage.inicializarTodo();
            System.out.println("Inicialice");
        }
        if (storage.getUbicacionTC().size() == 0) {
            System.out.println("lEI");
            readFromSerial();
        }

        iniciarDiseno();
        mostrar();
    }

    public void iniciarDiseno() {
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        lblSerie.setHorizontalAlignment(JLabel.CENTER);
        disenos.botones(btnAdd, 3);
        disenos.botones(btnAtras, 3);

        disenos.fondo(pnlFondo, 2);
        disenos.fondo(pnlCuerpo, 2);
        disenos.fondo(pnlChecks, 2);
        disenos.fondo(pnlCue, 2);
        disenos.fondo(pnlCabecera, 3);
        disenos.fondo(pnlDer, 1);
        disenos.fondo(pnlIzq, 1);

        //UIManager.put("pbCalidad.background", Color.RED);
        disenos.fondo(pnlTC, 2);
        disenos.fondo(pnlTS, 2);

        disenos.fondo(pnlTT, 4);
        disenos.fondo(pnlTF, 4);
        //ver como el cambiar el color del progressBar

        disenos.progressBar(pbCalidad);
        disenos.progressBar(pbFinal);
        disenos.progressBar(pbTesting);
        disenos.progressBar(pbShipment);
        //  pbCalidad.setBackground(Color.white);
        // pbCalidad.setForeground(Color.white);
        disenos.fondoLabel(lblCheckLists, 1);
        disenos.fondoLabel(lblPlantillas, 1);

        disenos.fondoLabel(lblCC, 2);
        disenos.fondoLabel(lblTC, 2);

        disenos.fondoLabel(lblCT, 3);
        disenos.fondoLabel(lblTT, 3);

        disenos.fondoLabel(lblCS, 2);
        disenos.fondoLabel(lblTS, 2);

        disenos.fondoLabel(lblCF, 3);
        disenos.fondoLabel(lblTF, 3);

        disenos.titulo(lblTitulo, 2);
        disenos.titulo(lblSerie, 6);
        disenos.titulo(lblCheckLists, 5);
        disenos.titulo(lblPlantillas, 5);
        disenos.titulo(lblCC, 6);
        disenos.titulo(lblTC, 6);
        disenos.titulo(lblCT, 6);
        disenos.titulo(lblTT, 6);
        disenos.titulo(lblCS, 6);
        disenos.titulo(lblTS, 6);
        disenos.titulo(lblCF, 6);
        disenos.titulo(lblTF, 6);

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
                .addContainerGap(22, Short.MAX_VALUE))
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
        lblPlantillas.setText("Progreso");
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

        pnlCalidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCalidadMouseClicked(evt);
            }
        });
        pnlCalidad.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        pnlCalidad.add(lblCC, gridBagConstraints);

        pnlTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTCMouseClicked(evt);
            }
        });
        pnlTC.setLayout(new java.awt.GridBagLayout());

        lblTC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlTC.add(lblTC, gridBagConstraints);

        pbCalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pbCalidadMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
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

        pblTesting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pblTesting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pblTestingMouseClicked(evt);
            }
        });
        pblTesting.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        pblTesting.add(lblCT, gridBagConstraints);

        pnlTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTTMouseClicked(evt);
            }
        });
        pnlTT.setLayout(new java.awt.GridBagLayout());

        lblTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        pbTesting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pbTestingMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
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

        pnlShipment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlShipment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlShipmentMouseClicked(evt);
            }
        });
        pnlShipment.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        pnlShipment.add(lblCS, gridBagConstraints);

        pnlTS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTSMouseClicked(evt);
            }
        });
        pnlTS.setLayout(new java.awt.GridBagLayout());

        lblTS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        pbShipment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pbShipmentMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
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

        pnlFinal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlFinalMouseClicked(evt);
            }
        });
        pnlFinal.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.6;
        gridBagConstraints.weighty = 1.0;
        pnlFinal.add(lblCF, gridBagConstraints);

        pnlTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTFMouseClicked(evt);
            }
        });
        pnlTF.setLayout(new java.awt.GridBagLayout());

        lblTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        pbFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pbFinalMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
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
                .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
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
        atras();
        //  cambio1();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void lblCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCCMouseClicked
        // TODO add your handling code here:
        cambio(1);
    }//GEN-LAST:event_lblCCMouseClicked

    private void lblTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTCMouseClicked
        // TODO add your handling code here:
        cambio(1);
    }//GEN-LAST:event_lblTCMouseClicked

    private void lblCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCTMouseClicked
        // TODO add your handling code here:
        cambio(2);
    }//GEN-LAST:event_lblCTMouseClicked

    private void lblTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTTMouseClicked
        // TODO add your handling code here:
        cambio(2);
    }//GEN-LAST:event_lblTTMouseClicked

    private void lblCSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCSMouseClicked
        // TODO add your handling code here:
        cambio(3);
    }//GEN-LAST:event_lblCSMouseClicked

    private void lblTSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTSMouseClicked
        // TODO add your handling code here:
        cambio(3);
    }//GEN-LAST:event_lblTSMouseClicked

    private void lblCFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCFMouseClicked
        // TODO add your handling code here:
        cambio(4);
    }//GEN-LAST:event_lblCFMouseClicked

    private void lblTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTFMouseClicked
        // TODO add your handling code here:
        cambio(4);
    }//GEN-LAST:event_lblTFMouseClicked

    private void pnlCalidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCalidadMouseClicked
        // TODO add your handling code here:
        cambio(1);
    }//GEN-LAST:event_pnlCalidadMouseClicked

    private void pnlTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTCMouseClicked
        // TODO add your handling code here:
        cambio(1);
    }//GEN-LAST:event_pnlTCMouseClicked

    private void pbCalidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pbCalidadMouseClicked
        // TODO add your handling code here:
        cambio(1);
    }//GEN-LAST:event_pbCalidadMouseClicked

    private void pblTestingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pblTestingMouseClicked
        // TODO add your handling code here:
        cambio(2);
    }//GEN-LAST:event_pblTestingMouseClicked

    private void pnlTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTTMouseClicked
        // TODO add your handling code here:
        cambio(2);
    }//GEN-LAST:event_pnlTTMouseClicked

    private void pbTestingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pbTestingMouseClicked
        // TODO add your handling code here:
        cambio(2);
    }//GEN-LAST:event_pbTestingMouseClicked

    private void pnlShipmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlShipmentMouseClicked
        // TODO add your handling code here:
        cambio(3);
    }//GEN-LAST:event_pnlShipmentMouseClicked

    private void pnlTSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTSMouseClicked
        // TODO add your handling code here:
        cambio(3);
    }//GEN-LAST:event_pnlTSMouseClicked

    private void pbShipmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pbShipmentMouseClicked
        // TODO add your handling code here:
        cambio(3);
    }//GEN-LAST:event_pbShipmentMouseClicked

    private void pnlFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFinalMouseClicked
        // TODO add your handling code here:
        cambio(4);
    }//GEN-LAST:event_pnlFinalMouseClicked

    private void pnlTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTFMouseClicked
        // TODO add your handling code here:
        cambio(4);
    }//GEN-LAST:event_pnlTFMouseClicked

    private void pbFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pbFinalMouseClicked
        // TODO add your handling code here:
        cambio(4);
    }//GEN-LAST:event_pbFinalMouseClicked

    private void atras() {
        new info().setXY(this.getX(), this.getY());
        storage.inicializarTodo();
        this.setCursor(new Cursor(WAIT_CURSOR));
        new MenuCompletarOrden(con, user, priv, idioma, modo).setVisible(true);
        this.dispose();
    }

    private void cambio(int interfaz) {
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        switch (interfaz) {
            case 1:
                new CheckListTC(con, user, priv, idioma, serie, plantilla, modo).setVisible(true);
                break;
            case 2:
                new CheckListTT(con, user, priv, idioma, serie, plantilla, modo).setVisible(true);
                break;
            case 3:
                new CheckListTS(con, user, priv, idioma, serie, plantilla, modo).setVisible(true);
                break;
            case 4:
                new CheckListTF(con, user, priv, idioma, serie, plantilla, modo).setVisible(true);
                break;
        }
        this.dispose();
    }

    private void fillTT(JSONObject allData, String primera, String segunda, String tercera, String cuarta, String act, String realizado, String aprobacion, String requisito, String comentario, String rendimiento, String ponderacion, String requisito1, String comentario1) {
        try {
            HashMap check = (HashMap) allData.get(primera);
            ArrayList<String> actividad = new ArrayList<>();
            ArrayList<Boolean> real = new ArrayList<>();
            ArrayList<Boolean> aproba = new ArrayList<>();
            ArrayList<Integer> requi = new ArrayList<>();
            ArrayList<String> rend = new ArrayList<>();
            ArrayList<String> pondera = new ArrayList<>();
            ArrayList<Integer> requi1 = new ArrayList<>();
            ArrayList<String> usuarios = new ArrayList<>();
            ArrayList<String> usuarios1 = new ArrayList<>();
            ArrayList<String> comenta = new ArrayList<>();
            ArrayList<String> comenta1 = new ArrayList<>();
            ArrayList actividades = (ArrayList) check.get(segunda);
            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                if (!jsonTC.get(act).toString().equals("")) {
                    String us = jsonTC.get(tercera).toString();
                    usuarios.add(us);
                    actividad.add(jsonTC.get(act).toString());
                    real.add(Boolean.parseBoolean(jsonTC.get(realizado).toString()));
                    aproba.add(Boolean.parseBoolean(jsonTC.get(aprobacion).toString()));
                    requi.add(Integer.parseInt(jsonTC.get(requisito).toString()));
                    comenta.add(jsonTC.get(comentario).toString());
                }
                if (!jsonTC.get(rendimiento).toString().equals("")) {
                    usuarios1.add(jsonTC.get(cuarta).toString());
                    rend.add(jsonTC.get(rendimiento).toString());
                    pondera.add(jsonTC.get(ponderacion).toString());
                    requi1.add(Integer.parseInt(jsonTC.get(requisito1).toString()));
                    comenta1.add(jsonTC.get(comentario1).toString());
                }
                //  if (us.toLowerCase().equals(nomU.toLowerCase())) {
                // }
            }
            storage.setPorcentajeTT(Double.parseDouble(check.get("porcentaje").toString()));
            storage.setActividadesTT(actividad);
            storage.setPonderacionTT(pondera);
            storage.setAprobacionTT(aproba);
            storage.setRealizadoTT(real);
            // System.out.println("Comentarioss: " + comenta);
            storage.setComentarioTT(comenta);
            storage.setComentarioTT1(comenta1);
            // System.out.println("Despues: " + storage.getComentarioTT());
            //  storage.setCompletadoTT(complet);
            storage.setRequisitosTT(requi);
            storage.setRendimientoTT(rend);
            //  storage.setCompletado1TT(complet1);
            storage.setUsuariosTT(usuarios);
            storage.setUsuarios1TT(usuarios1);
            storage.setRequisitosTT1(requi1);
            pbTesting.setValue(Integer.parseInt(check.get("porcentaje").toString()));
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooo2r: " + e);
        }
    }

    private void fillTC(JSONObject allData, String primera, String segunda, String tercera, String act, String rev, String apro, String requisito, String mensaj) {
        try {
            HashMap check = (HashMap) allData.get(primera);
            ArrayList actividades = (ArrayList) check.get(segunda);
            HashMap<String, ArrayList<String>> comenta = new HashMap<>();
            ArrayList<String> activid = new ArrayList<>();
            ArrayList<String> usuarios = new ArrayList<>();
            ArrayList<String> mensaje = new ArrayList<>();

            ArrayList<Boolean> revsol = new ArrayList<>();
            ArrayList<Boolean> aprobado = new ArrayList<>();
            ArrayList<Integer> requi = new ArrayList<>();

            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                String us = jsonTC.get(tercera).toString();
                usuarios.add(us);
                //   if (us.toLowerCase().equals(nomU.toLowerCase())) {
                //solo las del usuario
                System.out.println("Actividad: "+jsonTC.get(act).toString());
                System.out.println("act: "+act);
                activid.add(jsonTC.get(act).toString());
                mensaje.add(jsonTC.get(mensaj).toString());
                revsol.add(Boolean.parseBoolean(jsonTC.get(rev).toString()));
                aprobado.add(Boolean.parseBoolean(jsonTC.get(apro).toString()));
                requi.add(Integer.parseInt(jsonTC.get(requisito).toString()));
                try {
                    ArrayList<String> come = new ArrayList<>();
                    ArrayList coments = (ArrayList) jsonTC.get("comentario");
                    for (int j = 0; j < coments.size(); j++) {
                        HashMap jsonCom = (HashMap) coments.get(j);
                        come.add(jsonCom.get("comentario").toString());
                    }
                    comenta.put(jsonTC.get("ubicacion").toString(), come);
                } catch (Exception e) {
                    System.out.println("No haaaay");
                }

                //  }
            }
            System.out.println("Activid: "+activid);
            storage.setUsuariosTC(usuarios);
            storage.setUbicacionTC(activid);
            storage.setPorcentajeTC(Double.parseDouble(check.get("porcentaje").toString()));
            storage.setRevsolTC(revsol);
            storage.setAprobadoTC(aprobado);
            storage.setRequisitosTC(requi);
            storage.setMensajeTC(mensaje);
            // System.out.println("activi: " + activid);
            storage.setComentariosTC(comenta);
            pbCalidad.setValue(Integer.parseInt(check.get("porcentaje").toString()));
            // System.out.println("Aprobado: "+storage.getAprobadoTC());
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooo21r: " + e);
        }
    }

    private void fillTF(JSONObject allData, String primera, String segunda, String tercera, String act, String completado, String requisito, String comen) {
        try {
            HashMap check = (HashMap) allData.get(primera);
            ArrayList actividades = (ArrayList) check.get(segunda);
            ArrayList<String> actividad = new ArrayList<>();
            ArrayList<Integer> complet = new ArrayList<>();
            ArrayList<Integer> requi = new ArrayList<>();
            ArrayList<String> coment = new ArrayList<>();
            ArrayList<String> usuarios = new ArrayList<>();

            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                String us = jsonTC.get(tercera).toString();
                usuarios.add(us);
                //  if (us.toLowerCase().equals(nomU.toLowerCase())) {
                actividad.add(jsonTC.get(act).toString());
                coment.add(jsonTC.get(comen).toString());
                complet.add(Integer.parseInt(jsonTC.get(completado).toString()));
                requi.add(Integer.parseInt(jsonTC.get(requisito).toString()));
                // }
            }
            storage.setPorcentajeTF(Double.parseDouble(check.get("porcentaje").toString()));
            storage.setActividadesTF(actividad);
            storage.setCompletadoTF(complet);
            storage.setRequisitosTF(requi);
            storage.setUsuariosTF(usuarios);
            storage.setComentarioTF(coment);
            pbFinal.setValue(Integer.parseInt(check.get("porcentaje").toString()));
            //  return false;
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooo1r: " + e);
            //  return false;
        }
    }

    private void fillTS(JSONObject allData, String primera, String segunda, String tercera, String act, String completado, String requisito, String comentario) {
        try {
            HashMap check = (HashMap) allData.get(primera);
            ArrayList actividades = (ArrayList) check.get(segunda);
            ArrayList<String> actividad = new ArrayList<>();
            ArrayList<Integer> complet = new ArrayList<>();
            ArrayList<Integer> requi = new ArrayList<>();
            ArrayList<String> usuarios = new ArrayList<>();
            ArrayList<String> comentar = new ArrayList<>();

            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                String us = jsonTC.get(tercera).toString();
                usuarios.add(us);
                //  if (us.toLowerCase().equals(nomU.toLowerCase())) {
                actividad.add(jsonTC.get(act).toString());
                 System.out.println("Completado: "+jsonTC.get(completado));
                complet.add(Integer.parseInt(jsonTC.get(completado).toString()));
                System.out.println("Requisito: "+jsonTC.get(requisito));
                requi.add(Integer.parseInt(jsonTC.get(requisito).toString()));
                comentar.add(jsonTC.get(comentario).toString());
                //  }
            }
            storage.setPorcentajeTS(Double.parseDouble(check.get("porcentaje").toString()));
            System.out.println("Porcentaje: "+check.get("porcentaje").toString());
            storage.setAccesoriosTS(actividad);
            storage.setUsuariosTS(usuarios);
            storage.setCompletadoTS(complet);
            storage.setRequisitosTS(requi);
            storage.setComentarioTS(comentar);
            storage.setMensajeTS(check.get("mensaje").toString());

            pbShipment.setValue(Integer.parseInt(check.get("porcentaje").toString()));
            //  return false;
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooor: " + e.getLocalizedMessage());
            //  return false;
        }
    }

    private void readFromSerial() {
        try {
            Query query = con.child("Trabajos").child("" + serie);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Map<String, Object> value = (Map<String, Object>) snapshot.getValue();
                        JSONObject allData = new JSONObject(value);
                        fillTC(allData, "checkTC", "actividades", "usuario", "ubicacion", "revsol", "aprobado", "requisito", "mensaje");
                        fillTS(allData, "checkTS", "actividades", "usuario", "accesorio", "completado", "requisito", "comentario");
                        fillTF(allData, "checkTF", "actividades", "usuario", "actividad", "completado", "requisito", "comentario");
                        fillTT(allData, "checkTT", "actividades", "usuario", "usuario1", "actividad", "realizado", "aprobacion", "requisito", "comentario", "rendimiento", "ponderacion", "requisito1", "comentario1");
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //  Toast.makeText(getApplicationContext(), "De15 " + error, Toast.LENGTH_SHORT).show();
                }

            });
        } catch (Exception t) {
            //  Context context = getApplicationContext();
            //   Toast toast = Toast.makeText(context, "Notifica el siguiente error8:" + t, Toast.LENGTH_SHORT);
            //   toast.show();
        }
    }

    private void mostrar() {
        lblSerie.setText(serie + " - " + plantilla);
        lblTS.setText(storage.getPlantillaTS());
        lblTT.setText(storage.getPlantillaTT());
        lblTF.setText(storage.getPlantillaTF());
        lblTC.setText(storage.getPlantillaTC());
        if (idioma.equals("english")) {
            lblTitulo.setText("Check activities");

            lblCheckLists.setText("Checklists");
            lblPlantillas.setText("Progress");
            lblCC.setText("Quality");
            lblCS.setText("Shipment");
            lblCT.setText("Testing");
            lblCF.setText("Final");

        } else {
            lblTitulo.setText("Check actividades");

            lblCheckLists.setText("Checklists");
            lblPlantillas.setText("Progreso");
            lblCC.setText("Calidad");
            lblCS.setText("Envio");
            lblCT.setText("Pruebas");
            lblCF.setText("Final");
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
