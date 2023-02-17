package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import datos.leerJSON;
import disenos.ventanas.configuracionVentana;
import disenos.customTabbedUI;
import disenos.disenoTabla;
import disenos.disenos;
import disenos.readRecordTableBackground;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import obtenerDatos.ordenes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MenuAgregarModificarOrdenes extends JFrame {//insert/update creo

    //declaracion de variables globales
    private DatabaseReference con;
    private int priv;
    private String user, idioma;
    private DefaultTableModel modelo;
    private JFrame context;
    private HashMap<String, ArrayList<String>> drone_user;
    private ArrayList<String> serie, dron;
    private ArrayList<Integer> progreso;
    private ArrayList<Date> fecha;
    private int inicioLog;
    private tblUsersActividades usAct;
    private boolean verLog;
    private leerJSON json;

    public MenuAgregarModificarOrdenes(DatabaseReference con, String user, int priv, String idioma) {
        initComponents();
        new configuracionVentana(this);
        //inicializacion de variables
        json = new leerJSON();
        drone_user = new HashMap();
        this.con = con;
        this.priv = priv;
        verLog = false;
        inicioLog = 0;
        usAct = new tblUsersActividades(con, idioma);
        pnlOpc.setUI(new customTabbedUI());
        limpiar();
        this.user = user;
        context = this;
        this.idioma = idioma;
       // modelo = (DefaultTableModel) tablaUsers.getModel();

        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }

        attach();
        iniciarDiseno();
        leer();

        //se deshabilitan estos botones porque si se elimina algo, no se eliminan las fotos relacionadas a los procesos
        //  btnElReg.setEnabled(false);
        //  btnEliminar.setEnabled(false);
    }

    public void attach() {//se agrega el otro tab
        if (idioma.equals("English")) {
            pnlOpc.setTitleAt(0, "Drones");
            pnlOpc.addTab("Users", usAct.returnPanel());
        } else {
            pnlOpc.setTitleAt(0, "Drones");
            pnlOpc.addTab("Usuarios", usAct.returnPanel());
        }
    }

    public void inhabilitarBotones() {
        btnAdd.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnElReg.setEnabled(false);
        btnVerReg.setEnabled(false);
    }

    public void habilitarBotones() {
        btnAdd.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnElReg.setEnabled(true);
        btnVerReg.setEnabled(true);
    }

    public void iniciarDiseno() {//decoracion de los componentes del frame
        //   lblAct.setVisible(false);

        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        txtBuscar.setHorizontalAlignment(JLabel.CENTER);
        modelo = (DefaultTableModel) tablaUsers.getModel();
        new disenos().botones(btnEliminar, 3);
        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnVerUsers, 3);
        new disenos().botones(btnAtras, 3);
        new disenos().botones(btnElReg, 3);
        new disenos().botones(btnVerReg, 3);
        new disenos().botones(btnBuscar, 1);

        new disenos().textoL(txtBuscar);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().titulo(lblTitulo, 2);

        ponerImg(btnEliminar, "img/trash2.png");
        ponerImg(btnAdd, "img/edit1.png");
        ponerImg(btnAtras, "img/atras2.png");
        ponerImg(btnBuscar, "img/buscar1.png");
        ponerImg(btnElReg, "img/delLog.png");
        ponerImg(btnVerReg, "img/verLog.png");
        ponerImg(btnVerUsers, "img/ojo.png");

        tablaUsers.requestFocus();
        txtBuscar.setForeground(Color.LIGHT_GRAY);

        new disenoTabla().cabecera(tablaUsers);

        float wid = (float) (pnlOpc.getWidth() / 2);//8.3
        for (int i = 0; i < 2; i++) {
            String name = pnlOpc.getTitleAt(i);
            pnlOpc.setTitleAt(i, "<html><center><div style=\"width: " + wid + "px\"><font color=\"#FFFFFF\" size=\"6\" face=\"Lato\">" + new String(name) + "</font></div>"
                    + "</center></html>");
        }
    }

    public void ponerImg(JButton b, String ruta) {//les pone una imagen a los botones
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
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVerReg = new javax.swing.JButton();
        btnElReg = new javax.swing.JButton();
        btnVerUsers = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlOpc = new javax.swing.JTabbedPane();
        pnlCuerpo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlFondoMousePressed(evt);
            }
        });

        pnlCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlCabeceraMousePressed(evt);
            }
        });

        lblTitulo.setText("Menu usuarios");

        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlDer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlDerMousePressed(evt);
            }
        });

        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnVerReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerRegActionPerformed(evt);
            }
        });

        btnElReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnElReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElRegActionPerformed(evt);
            }
        });

        btnVerUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerReg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnElReg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerUsers, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerReg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnElReg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlIzq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlIzqMousePressed(evt);
            }
        });

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

        pnlOpc.setToolTipText("");
        pnlOpc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pnlOpcStateChanged(evt);
            }
        });
        pnlOpc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlOpcMousePressed(evt);
            }
        });

        pnlCuerpo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCuerpoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlCuerpoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlCuerpoMouseReleased(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
        });

        tablaUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N. Serie", "Dron", "Progreso", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsersMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaUsersMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsers);
        if (tablaUsers.getColumnModel().getColumnCount() > 0) {
            tablaUsers.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaUsers.getColumnModel().getColumn(1).setPreferredWidth(200);
            tablaUsers.getColumnModel().getColumn(2).setPreferredWidth(30);
            tablaUsers.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
        );

        pnlOpc.addTab("tab1", pnlCuerpo);

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlOpc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlOpc, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)))
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

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        // TODO add your handling code here:
        if (txtBuscar.getText().equals("Buscar") || txtBuscar.getText().equals("Search")) {
            txtBuscar.setText("");
            txtBuscar.setForeground(Color.BLACK);
            txtBuscar.setHorizontalAlignment(JLabel.LEFT);
        }
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        // TODO add your handling code here:
        if (txtBuscar.getText().equals("")) {
            if (idioma.equals("English")) {
                txtBuscar.setText("Search");
            } else {
                txtBuscar.setText("Buscar");
            }
            txtBuscar.setHorizontalAlignment(JLabel.CENTER);
            txtBuscar.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtBuscarFocusLost

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            int cod = Integer.parseInt(modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString());
            new info().setXY(this.getX(), this.getY());
            //DatabaseReference con, String user, int priv, String idioma, int serie, int inter
            new vistaAgregarModificarOrdenes(con, user, priv, idioma, cod, 3).setVisible(true);
            this.dispose();
        } catch (Exception e) {
            new info().setXY(this.getX(), this.getY());
            new vistaAgregarModificarOrdenes(con, user, priv, idioma, 0, 1).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPrincipal(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (!txtBuscar.getText().equals("")) {
            try {
                String ser = txtBuscar.getText();
                if (ser.length() == 5) {
                    int serieBus = Integer.parseInt(ser);
                    buscar(serieBus);
                } else {
                    //POnga el codigo completo
                }
            } catch (Exception e) {

            }

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtBuscar.getText().equals("")) {
                //   buscar();
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        if (txtBuscar.getText().length() == 5) {
            evt.consume();
        }
        char caracter = evt.getKeyChar();
        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0')
                || (caracter > '9'))) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnVerRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerRegActionPerformed
        // TODO add your handling code here:
        if (!verLog) {
            leerTodo();
        }
        verLog = true;
        btnVerReg.setEnabled(false);
    }//GEN-LAST:event_btnVerRegActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        //si se busca eliminar una actividad
        try {
            modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString();
            String texto1, texto2, o1, o2;
            if (idioma.equals("English")) {
                texto1 = "Are you sure you want to delete the selected work?";
                texto2 = "Confirm Action";
                o1 = "Yes";
                o2 = "No";
            } else {
                texto1 = "¿Seguro que quiere eliminar el trabajo seleccionado?";
                texto2 = "Confirmar Acción";
                o1 = "Si";
                o2 = "No";
            }

            Object[] options = {o1, o2};
            if (JOptionPane.showOptionDialog(this, texto1, texto2,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]) == 0) {
                try {

                    eliminar(Integer.parseInt(modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString()), tablaUsers.getSelectedRow());
                } catch (Exception e) {

                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(context, "Select a work to delete");
                    } else {
                        JOptionPane.showMessageDialog(context, "Seleccione un trabajo a eliminar");
                    }
                }
            }
        } catch (Exception e) {
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Select a work to delete");
            } else {
                JOptionPane.showMessageDialog(context, "Seleccione un trabajo a eliminar");
            }
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnElRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElRegActionPerformed
        // TODO add your handling code here:
        //eliminar registro
        String texto1, texto2, o1, o2;
        if (idioma.equals("English")) {
            texto1 = "Are you sure youn want to delete the record?\nWorks older than 90 days will be deleted";
            texto2 = "Confirm Action";
            o1 = "Yes";
            o2 = "No";
        } else {
            texto1 = "¿Esta seguro de querer eliminar el registro?\n" + "Se eliminaran trabajos mayores a 90 dias";
            texto2 = "Confirmar Acción";
            o1 = "Si";
            o2 = "No";
        }
        Object[] options = {o1, o2};
        if (JOptionPane.showOptionDialog(this, texto1, texto2,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]) == 0) {
            try {
                eliminarReg();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(context, "Error: " + e);
            }
        }
    }//GEN-LAST:event_btnElRegActionPerformed

    private void btnVerUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerUsersActionPerformed
        // TODO add your handling code here:
        //ver usuarios asociados a un trabajo
        //mostrar JFrame con tabla como el de extrasVersiones en donde aparexcan los usuarios
        try {
            if (pnlOpc.getSelectedIndex() == 0) {
                int cod = Integer.parseInt(modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString());
                //pasar lista de usuarios almacenados en du
                ArrayList<String> a = drone_user.get("" + cod);
                new verUsuarios(idioma, a, "" + cod, pnlOpc.getSelectedIndex()).setVisible(true);
            } else {
                String cod = usAct.returnModelo().getValueAt(usAct.returnTabla().getSelectedRow(), 0).toString();
                //pasar lista de usuarios almacenados en du
                List<String> a = (List) usAct.returnLista().get(cod);
                new verUsuarios(idioma, a, cod, pnlOpc.getSelectedIndex()).setVisible(true);
            }
        } catch (Exception e) {
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Select an order");
            } else {
                JOptionPane.showMessageDialog(context, "Seleccione una orden");
            }
        }

    }//GEN-LAST:event_btnVerUsersActionPerformed

    private void pnlIzqMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlIzqMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlIzqMousePressed

    private void pnlDerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDerMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlDerMousePressed

    private void pnlCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCabeceraMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlCabeceraMousePressed

    private void pnlFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFondoMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlFondoMousePressed

    private void pnlOpcMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpcMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlOpcMousePressed

    private void pnlCuerpoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCuerpoMousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_pnlCuerpoMousePressed

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_jScrollPane1MousePressed

    private void tablaUsersMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsersMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tablaUsersMouseReleased

    private void pnlCuerpoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCuerpoMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_pnlCuerpoMouseReleased

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void pnlCuerpoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCuerpoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlCuerpoMouseClicked

    private void tablaUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsersMouseClicked
        // TODO add your handling code here:
        System.out.println("evt.get tabla: " + evt.getButton());
        if (evt.getButton() == MouseEvent.BUTTON3) {

        }
    }//GEN-LAST:event_tablaUsersMouseClicked

    private void pnlOpcStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pnlOpcStateChanged
        // TODO add your handling code here:
        JTabbedPane tabbedPane = (JTabbedPane) evt.getSource();
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex == 0) {
            habilitarBotones();
        } else {
            inhabilitarBotones();
        }
    }//GEN-LAST:event_pnlOpcStateChanged

    private void eliminarReg() {//elimina el registro de trabajos
        try {
            Query query = con.child(json.getString("trabajosTable")).orderByChild(json.getString("trabajosTableFecha"));
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaactual = new Date(System.currentTimeMillis());
                        int cantidad = 0;
                        // ArrayList a = new ArrayList();
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                                ordenes log = user.getValue(ordenes.class);
                                Date fechaInicioDate = null;  //String a date
                                try {
                                    fechaInicioDate = df.parse(log.getFecha());
                                } catch (ParseException es) {
                                }
                                long diffTime = fechaactual.getTime() - fechaInicioDate.getTime();
                                long diffDays = diffTime / (1000 * 60 * 60 * 24);
                                if (diffDays >= 90) {
                                    if (log.getProgreso() == 100) {
                                        //  a.add(log.getSerie());
                                        cantidad++;
                                        con.child(json.getString("trabajosTable")).child(user.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                                            @Override
                                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                                                //mensaje de confirmacion
                                                //eliminar del array el log
                                                if (serie.contains(user.getKey())) {
                                                    int index = serie.indexOf(user.getKey());
                                                    serie.remove(index);
                                                    fecha.remove(index);
                                                    progreso.remove(index);
                                                    dron.remove(index);
                                                }
                                            }
                                        });
                                    }
                                }
                            } catch (Exception es) {
                                // Toast.makeText(getApplicationContext(), "Notifica el siguiente error4: " + es, Toast.LENGTH_LONG).show();
                            }
                        }
                        if (cantidad > 0) {
                            borrarTabla();
                            llenarTabla();
                            if (idioma.equals("english")) {
                                //Toast toast = Toast.makeText(getApplicationContext(), "Deleted succesfully", Toast.LENGTH_SHORT);
                                // toast.show();
                            } else {
                                // Toast toast = Toast.makeText(getApplicationContext(), "Eliminado satisfactoriamente", Toast.LENGTH_SHORT);
                                // toast.show();
                            }
                        } else {
                            if (idioma.equals("english")) {
                                // Toast toast = Toast.makeText(getApplicationContext(), "There's nothing in record", Toast.LENGTH_SHORT);
                                // toast.show();
                            } else {
                                // Toast toast = Toast.makeText(getApplicationContext(), "No existe registro alguno", Toast.LENGTH_SHORT);
                                // toast.show();
                            }
                        }
                        // eliminarResto(a);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Toast.makeText(getApplicationContext(), "De " + error, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            // Context context = getApplicationContext();
            // Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            // toast.show();
        }
    }

    private void eliminar(int serie1, int position) {//elimina el trabajo seleccionado
        //en desuso pq esta deshabilitado el boton de eliminar
        try {
            con.child(json.getString("trabajosTable")).child("" + serie1).removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                    //mensaje de confirmacion
                    // du.remove("" + serie);
                    modelo.removeRow(position);
                    serie.remove(position);
                    fecha.remove(position);
                    progreso.remove(position);
                    dron.remove(position);
                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(context, "Work deleted");
                    } else {
                        JOptionPane.showMessageDialog(context, "Trabajo eliminado");
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    private void leerTodo() {//lee el registro de la bd
        try {
            Query query = null;
            query = con.child(json.getString("trabajosTable")).orderByChild(json.getString("trabajosTableProgreso")).equalTo(100);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ordenes log;
                        for (DataSnapshot info : snapshot.getChildren()) {
                            log = info.getValue(ordenes.class);
                            serie.add("" + info.getKey());
                            dron.add(log.getNombre());
                            progreso.add((int) log.getProgreso());
                            Map<String, Object> value = (Map<String, Object>) info.getValue();
                            JSONObject allData = new JSONObject(value);
                            ArrayList<String> usuarios = new ArrayList<>();
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTC"), json.getString("trabajosTableCheckTCActividades"), json.getString("trabajosTableCheckTCActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTT"), json.getString("trabajosTableCheckTTActividades"), json.getString("trabajosTableCheckTTActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTT"), json.getString("trabajosTableCheckTTActividades"), json.getString("trabajosTableCheckTTActividadesUsu1"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTS"), json.getString("trabajosTableCheckTSActividades"), json.getString("trabajosTableCheckTSActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTF"), json.getString("trabajosTableCheckTFActividades"), json.getString("trabajosTableCheckTFActividadesUsu"), usuarios);
                            drone_user.put(info.getKey(), usuarios);
                            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                            Date fecha1;
                            try {
                                fecha1 = formato.parse(log.getFecha());
                                fecha.add(fecha1);
                            } catch (ParseException ex) {
                            }
                        }
                        //ordenarPorFecha(0, fecha.size() - 1);
                        borrarTabla();
                        llenarTabla();

                    } else {
                        //No hay trabajos agregados aun
                        //  new showToast(context.getString(R.string.noTrabajosCompletosSpn), context.getString(R.string.noTrabajosCompletosEng), idioma, context);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //new showToast(context.getString(R.string.lblErrorWhileReadingDBSpn) + error, context.getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, context);
                }
            });
        } catch (Exception e) {
            // new showToast(context.getString(R.string.lblErrorWhileReadingDBSpn) + e, context.getString(R.string.lblErrorWhileReadingDBEng) + e, idioma, context);
        }
    }

    private void buscar(int serieBus) {//buscar algun trabaho especifico por numero de serie
        try {

            Query query = con.child(json.getString("trabajosTable")).child("" + serieBus);
            //.child(bus);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        limpiar();
                        ordenes log;
                        // for (DataSnapshot info : snapshot.getChildren()) {
                        //  log = info.getValue(trabajos1.class);
                        log = snapshot.getValue(ordenes.class);
                        serie.add("" + serieBus);
                        dron.add(log.getNombre());
                        progreso.add((int) log.getProgreso());
                        Map<String, Object> value = (Map<String, Object>) snapshot.getValue();//info.getValue()
                        JSONObject allData = new JSONObject(value);
                        ArrayList<String> usuarios = new ArrayList<>();
                        usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTC"), json.getString("trabajosTableCheckTCActividades"), json.getString("trabajosTableCheckTCActividadesUsu"), usuarios);
                        usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTT"), json.getString("trabajosTableCheckTTActividades"), json.getString("trabajosTableCheckTTActividadesUsu"), usuarios);
                        usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTT"), json.getString("trabajosTableCheckTTActividades"), json.getString("trabajosTableCheckTTActividadesUsu1"), usuarios);
                        usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTS"), json.getString("trabajosTableCheckTSActividades"), json.getString("trabajosTableCheckTSActividadesUsu"), usuarios);
                        usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTF"), json.getString("trabajosTableCheckTFActividades"), json.getString("trabajosTableCheckTFActividadesUsu"), usuarios);
                        drone_user.put("" + serieBus, usuarios);
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        Date fecha1;
                        try {
                            fecha1 = formato.parse(log.getFecha());
                            fecha.add(fecha1);
                        } catch (ParseException ex) {
                        }

                        //  }
                        ordenarPorFecha(0, fecha.size() - 1);
                        borrarTabla();
                        llenarTabla();
                    } else {
                        if (modelo.getRowCount() == 0) {
                            //actualizar con click derecho
                            //leer o leer todo
                            // leer();
                        }
                        //No hay trabajos agregados aun
                        //   new showToast(context.getString(R.string.noTrabajosEncontradosSpn), context.getString(R.string.noTrabajosEncontradosEng), idioma, context);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //  new showToast(context.getString(R.string.lblErrorWhileReadingDBSpn) + error, context.getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, context);
                }
            });
        } catch (Exception e) {
            // new showToast(context.getString(R.string.lblErrorWhileReadingDBSpn) + e, context.getString(R.string.lblErrorWhileReadingDBEng) + e, idioma, context);
        }
    }

    private ArrayList<String> fillUsuario(JSONObject allData, String primera, String segunda, String tercera, ArrayList<String> usuarios) {
        try {
            // System.out.println("All data: "+allData.get(primera));
            HashMap check = (HashMap) allData.get(primera);
            ArrayList actividades = (ArrayList) check.get(segunda);
            for (int i = 0; i < actividades.size(); i++) {
                HashMap jsonTC = (HashMap) actividades.get(i);
                String us = (String) jsonTC.get(tercera);
                if (!usuarios.contains(us) && !us.equals("")) {
                    usuarios.add(us);
                }
            }
            return usuarios;
        } catch (Exception e) {
            System.out.println("erroooooooooooooooooooooooooooor: " + e);
            return null;
        }
    }

    private void limpiar() {
        progreso = new ArrayList<>();
        dron = new ArrayList<>();
        fecha = new ArrayList<>();
        serie = new ArrayList<>();
    }

    private void leer() {//lee solo procesos que tengan un procentaje de completado menor a 100%
        try {
            Query query = null;
            query = con.child(json.getString("trabajosTable")).orderByChild(json.getString("trabajosTableProgreso")).endAt(99);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ordenes log;

                        for (DataSnapshot info : snapshot.getChildren()) {
                            log = info.getValue(ordenes.class);
                            ArrayList<String> usuarios = new ArrayList<>();
                            serie.add("" + info.getKey());
                            dron.add(log.getNombre());
                            progreso.add((int) log.getProgreso());
                            // infor.add(a);
                            Map<String, Object> value = (Map<String, Object>) info.getValue();
                            JSONObject allData = new JSONObject(value);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTC"), json.getString("trabajosTableCheckTCActividades"), json.getString("trabajosTableCheckTCActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTT"), json.getString("trabajosTableCheckTTActividades"), json.getString("trabajosTableCheckTTActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTT"), json.getString("trabajosTableCheckTTActividades"), json.getString("trabajosTableCheckTTActividadesUsu1"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTS"), json.getString("trabajosTableCheckTSActividades"), json.getString("trabajosTableCheckTSActividadesUsu"), usuarios);
                            usuarios = fillUsuario(allData, json.getString("trabajosTableCheckTF"), json.getString("trabajosTableCheckTFActividades"), json.getString("trabajosTableCheckTFActividadesUsu"), usuarios);
                            drone_user.put(info.getKey(), usuarios);
                            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                            Date fecha1;
                            try {
                                fecha1 = formato.parse(log.getFecha());
                                fecha.add(fecha1);
                            } catch (ParseException ex) {
                            }
                        }
                        ordenarPorFecha(0, fecha.size() - 1);
                        borrarTabla();
                        llenarTabla();
                    } else {
                        //No hay trabajos agregados aun
                        //  new showToast(context.getString(R.string.noTrabajosSpn), context.getString(R.string.noTrabajosEng), idioma, context);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //  new showToast(context.getString(R.string.lblErrorWhileReadingDBSpn) + error, context.getString(R.string.lblErrorWhileReadingDBEng) + error, idioma, context);

                }
            });
        } catch (Exception e) {
            //   new showToast(context.getString(R.string.lblErrorWhileReadingDBSpn) + e, context.getString(R.string.lblErrorWhileReadingDBEng) + e, idioma, context);
        }
    }

    private void llenarTabla() {
        tablaUsers.setDefaultRenderer(Object.class, new readRecordTableBackground(progreso));
        for (int i = 0; i < serie.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaC = sdf.format(fecha.get(i));
            modelo.addRow(new Object[]{serie.get(i), dron.get(i), progreso.get(i) + "%", fechaC});
        }
    }

    private void borrarTabla() {//borra lo que contenga la tabla
        int x = modelo.getRowCount();
        for (int i = 0; i < x; i++) {
            modelo.removeRow(0);
        }
    }

    private void ordenarPorFecha(int izq, int der) {//ordenar el contenido de la tabla por fechas
        try {
            Date pivote = fecha.get(izq);
            String piv1 = serie.get(izq);
            String piv2 = dron.get(izq);
            int piv3 = progreso.get(izq);

            int i = izq;         // i realiza la bÃºsqueda de izquierda a derecha
            int j = der;         // j realiza la bÃºsqueda de derecha a izquierda

            Date aux;
            String aux1, aux2;
            int aux3;
            while (i < j) {// mientras no se crucen las bÃºsquedas
                while ((fecha.get(i).before(pivote) || fecha.get(i).toString().equals(pivote.toString())) && i < j) {
                    i++;//menor o igual es before o equal
                }
                while (fecha.get(j).after(pivote)) {
                    j--;
                }
                if (i < j) {// si no se han cruzado
                    aux = fecha.get(i);// los intercambia
                    aux1 = serie.get(i);
                    aux2 = dron.get(i);
                    aux3 = progreso.get(i);

                    fecha.set(i, fecha.get(j));
                    serie.set(i, serie.get(j));
                    dron.set(i, dron.get(j));
                    progreso.set(i, progreso.get(j));

                    fecha.set(j, aux);
                    serie.set(j, aux1);
                    dron.set(j, aux2);
                    progreso.set(j, aux3);

                }
            }

            fecha.set(izq, fecha.get(j));    // se coloca el pivote en su lugar de forma que tendremos
            serie.set(izq, serie.get(j));
            dron.set(izq, dron.get(j));
            progreso.set(izq, progreso.get(j));

            fecha.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha
            serie.set(j, piv1);
            dron.set(j, piv2);
            progreso.set(j, piv3);

            if (izq < j - 1) {
                ordenarPorFecha(izq, j - 1);          // ordenamos subarray izquierdo
            }
            if (j + 1 < der) {
                ordenarPorFecha(j + 1, der);
            }

        } catch (Exception e) {
            System.out.println("Error4: " + e);
        }
    }

    private void ingles() {//poner componentes de la interfaz en ingles
        txtBuscar.setText("Search");
        lblTitulo.setText("Activities menu");
        JTableHeader tableHeader = tablaUsers.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Serial N.");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("Drone");
        tableColumn = tableColumnModel.getColumn(2);
        tableColumn.setHeaderValue("Progress");
        tableColumn = tableColumnModel.getColumn(3);
        tableColumn.setHeaderValue("Date");
        tableHeader.repaint();
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Add a new work</b></html>");
        btnVerReg.setToolTipText("<html><b style='font-size: 12px;'>See log</b></html>");
        btnVerUsers.setToolTipText("<html><b style='font-size: 12px;'>See the users that are working in the selected drone</b></html>");
        btnElReg.setToolTipText("<html><b style='font-size: 12px;'>Delete log</b></html>");
        btnEliminar.setToolTipText("<html><b style='font-size: 12px;'>Delete the selected work</b></html>");
    }

    private void esp() {//poner componentes de la interfaz en espanol
        txtBuscar.setText("Buscar");
        lblTitulo.setText("Menu de actividades");
        btnVerUsers.setToolTipText("<html><b style='font-size: 12px;'>Ver usuarios que estan trabajando en el dron seleccionado</b></html>");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Agrega un nuevo trabajo</b></html>");
        btnVerReg.setToolTipText("<html><b style='font-size: 12px;'>Ver registro</b></html>");
        btnElReg.setToolTipText("<html><b style='font-size: 12px;'>Eliminar registro</b></html>");
        btnEliminar.setToolTipText("<html><b style='font-size: 12px;'>Elimina el trabajo seleccionado</b></html>");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnElReg;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVerReg;
    private javax.swing.JButton btnVerUsers;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTabbedPane pnlOpc;
    private javax.swing.JTable tablaUsers;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
