package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import datos.datos;
import disenos.colores;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import obtenerDatos.versiones;

public class vistaPlantillas extends configuracionVentana {

    private DatabaseReference con;//Conexion a la base de datos
    private String user, idioma, codigo, plantilla;//nombre de usuario que esta utlizando la app, idioma de la app, codigo de plantilla y nombre de plantilla
    private int priv;//privilegio de usuario (0,1,2,3,4)
    private DefaultTableModel modelo;//modelo de la tabla
    private JFrame context;//para los JOptionPane
    private String tC, tN;//variables que guardan un hint para las cajas de texto
    private ArrayList<String> idProceso, aux, eliminados, az, rc;//rc contiene el numero de fila y columna de datos no validos en la tabla
    private HashMap<String, String> ids;//ids de cada fila de la tabla
    private int listo;//para indicar cuando ya se inicializo la interfaz
    private boolean valido;//para ver si es valida la info para guardarla
    private HashMap<String, String> requisitos;//requisitos de cada proceso
    private TableCellRenderer render;//para pintar el borde de celdas de rojo si algun dato es incorrecto

    public vistaPlantillas(DatabaseReference con, String user, int priv, String idioma, String codigo, String plantilla) {
        initComponents();

        listo = 0;
        new datos().inicializar();
        valido = true;
        this.con = con;
        ids = new HashMap();
        requisitos = new HashMap();
        az = new ArrayList();
        this.user = user;
        this.priv = priv;
        this.plantilla = plantilla;
        this.idioma = idioma;
        this.codigo = codigo;
        eliminados = new ArrayList();
        aux = new ArrayList();
        rc = new ArrayList();

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        modelo = (DefaultTableModel) tablaPermisos.getModel();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            tablaPermisos.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }

        iniciarDiseno();
        context = this;
        idProceso = new ArrayList();
        if (idioma.equals("English")) {
            ingles();//cambia la interfaz a ingles
        } else {
            esp();//cambia la interfaz a espanol
        }
        if (!codigo.equals("")) {
            btnAdd.setEnabled(false);
            btnAddProceso.setEnabled(false);
            btnAddActividad.setEnabled(false);
            btnExtras.setEnabled(false);
            btnVersiones.setEnabled(false);
            txtCodigo.setEnabled(false);
            txtNombre.setText(plantilla);
            txtCodigo.setText(codigo);
            leer();
            txtNombre.setForeground(Color.BLACK);
            txtCodigo.setForeground(Color.BLACK);
        }

        btnAddActividad.setEnabled(false);
        btnAsignarPre.setEnabled(false);
        render = new TableCellRenderer() {//pintar de rojo el borde de una celda si el dato es incorrecto
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JLabel lbl = new JLabel(value == null ? "" : value.toString()); //si el valor es nulo pone vacio, si tiene algo, pone ese algo
                lbl.setHorizontalAlignment(SwingConstants.CENTER);//centra el texto en las celdas
                lbl.setFont(new Font("Lato", Font.PLAIN, 24));//fuente del cuerpo de la tabla
                lbl.setOpaque(true);
                if (rc.contains(row + "-" + column)) {//si la fila y columna (con un dato invalido) coinciden con la que se esta renderizando
                    lbl.setBorder(BorderFactory.createLineBorder(Color.RED, 3));//se pinta el borde de rojo
                } else {
                    lbl.setBorder(BorderFactory.createCompoundBorder());//sino se pone el borde por defecto
                }
                if (isSelected) {
                    lbl.setBackground(colores.azul);
                    lbl.setForeground(Color.WHITE);
                } else {
                    lbl.setBackground(Color.white);
                    lbl.setForeground(colores.getNegro());
                }
                return lbl;
            }
        };
        tablaPermisos.getColumnModel().getColumn(0).setCellRenderer(render);
        tablaPermisos.getColumnModel().getColumn(1).setCellRenderer(render);
        tablaPermisos.getColumnModel().getColumn(2).setCellRenderer(render);
    }

    public void iniciarDiseno() {
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);

        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAsignarPre, 3);
        new disenos().botones(btnAddProceso, 3);
        new disenos().botones(btnAddActividad, 3);
        new disenos().botones(btnAtras, 3);

        new disenos().textoL1(txtCodigo);
        new disenos().textoL1(txtNombre);

        new disenos().fondo(pnlFondo, 2);
        new disenos().fondo(pnlCuerpo, 2);
        new disenos().fondo(pnlCabecera, 3);
        new disenos().fondo(pnlDer, 1);
        new disenos().fondo(pnlIzq, 1);

        new disenos().titulo(lblTitulo, 2);
        new disenos().titulo(lbCodigo, 6);
        new disenos().titulo(lblNombre, 6);

        new disenos().botones(btnVersiones, 3);
        new disenos().botones(btnExtras, 3);

        ponerImg(btnAdd, "img/guardar1.png");
        ponerImg(btnAddProceso, "img/agregarProceso.png");
        ponerImg(btnAddActividad, "img/agregarActividad.png");
        ponerImg(btnAsignarPre, "img/checklist.png");
        ponerImg(btnAtras, "img/atras2.png");

        txtCodigo.requestFocus();

        new disenoTabla().cabecera(tablaPermisos);

        //las siguientes 3 lineas nomas son pa que el celleditor no sea null
        final JTextField text = new JTextField();
        TableCellEditor s = new DefaultCellEditor(text);
        tablaPermisos.setCellEditor(s);

        try {
            tablaPermisos.getCellEditor().addCellEditorListener(new CellEditorListener() {
                public void editingCanceled(ChangeEvent e) {
                    if (listo == 1) {
                        System.out.println("nooooooooooooooooooo");
                    }
                }

                @Override
                public void editingStopped(ChangeEvent e) {//si se para de editar la tabla ejecuta esto
                    if (listo == 1) {
                        try {
                            if (!modelo.getValueAt(tablaPermisos.getSelectedRow(), 0).toString().equals("")
                                    && !modelo.getValueAt(tablaPermisos.getSelectedRow(), 1).toString().equals("")
                                    && !modelo.getValueAt(tablaPermisos.getSelectedRow(), 2).toString().equals("")
                                    && !modelo.getValueAt(tablaPermisos.getSelectedRow(), 3).toString().equals("")) {//si no estan vacios los campos de la tabla
                                String proceso = modelo.getValueAt(tablaPermisos.getSelectedRow(), 0).toString() + "-"
                                        + modelo.getValueAt(tablaPermisos.getSelectedRow(), 1).toString() + "-"
                                        + modelo.getValueAt(tablaPermisos.getSelectedRow(), 2).toString() + "-"
                                        + modelo.getValueAt(tablaPermisos.getSelectedRow(), 3).toString();
                                List<String> a = datos.getProcesosPlantilla().get(ids.get("" + tablaPermisos.getSelectedRow()));
                                if (a == null) {
                                    a = new ArrayList();
                                }
                                datos.getProcesosPlantilla().remove(ids.get("" + tablaPermisos.getSelectedRow()));//se borra el proceso de la fila seleccionada
                                datos.setProcesosPlantilla(proceso, a);//se actualiza el proceso
                                ids.put("" + tablaPermisos.getSelectedRow(), proceso);//le asigna un id a cada proceso en la tabka
                            }

                        } catch (Exception es) {
                            //  System.out.println("Es: " + es);
                        }
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("errrororrr: " + e);
        }
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

        pnlFondo = new javax.swing.JPanel();
        pnlCabecera = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDer = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnAddProceso = new javax.swing.JButton();
        btnAddActividad = new javax.swing.JButton();
        btnAsignarPre = new javax.swing.JButton();
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        scrool = new javax.swing.JScrollPane();
        tablaPermisos = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        btnVersiones = new javax.swing.JButton();
        btnExtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setText("Menu usuarios");

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
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

        btnAddProceso.setToolTipText("Agregar proceso");
        btnAddProceso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProcesoActionPerformed(evt);
            }
        });

        btnAddActividad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActividadActionPerformed(evt);
            }
        });

        btnAsignarPre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsignarPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarPreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAsignarPre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAsignarPre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        lbCodigo.setText("Codigo de plantilla:");

        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        lblNombre.setText("Nombre de plantilla:");

        scrool.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                scroolKeyPressed(evt);
            }
        });

        tablaPermisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proceso", "P. de foto", "Peso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaPermisos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tablaPermisosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaPermisosFocusLost(evt);
            }
        });
        tablaPermisos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPermisosMouseClicked(evt);
            }
        });
        tablaPermisos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tablaPermisosInputMethodTextChanged(evt);
            }
        });
        tablaPermisos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaPermisosKeyPressed(evt);
            }
        });
        scrool.setViewportView(tablaPermisos);
        if (tablaPermisos.getColumnModel().getColumnCount() > 0) {
            tablaPermisos.getColumnModel().getColumn(0).setResizable(false);
            tablaPermisos.getColumnModel().getColumn(0).setPreferredWidth(300);
            tablaPermisos.getColumnModel().getColumn(1).setResizable(false);
            tablaPermisos.getColumnModel().getColumn(1).setPreferredWidth(2);
            tablaPermisos.getColumnModel().getColumn(2).setResizable(false);
            tablaPermisos.getColumnModel().getColumn(2).setPreferredWidth(2);
        }

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

        btnVersiones.setText("Versiones");
        btnVersiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVersionesActionPerformed(evt);
            }
        });

        btnExtras.setText("Extras");
        btnExtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrool, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
            .addGroup(pnlCuerpoLayout.createSequentialGroup()
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCuerpoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCuerpoLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnVersiones, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVersiones, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrool, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
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

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:

        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPlantillas(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnVersionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVersionesActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        new extrasVersiones(con, user, priv, idioma, 2, "").setVisible(true);
    }//GEN-LAST:event_btnVersionesActionPerformed

    private void btnExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        new extrasVersiones(con, user, priv, idioma, 1, "").setVisible(true);
    }//GEN-LAST:event_btnExtrasActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (tablaPermisos.isEditing()) {
            tablaPermisos.getCellEditor().stopCellEditing();//detenga la edicion para almacenar el valor
        }
        for (int i = 0; i < idProceso.size(); i++) {
            if (!requisitos.containsKey(idProceso.get(i))) {
                requisitos.put(idProceso.get(i), "0");//se rellena el array de requisitos con 0s
            }
        }
        if (datos.getProcesosPlantilla().size() > 0) {//si existe algun proceso
            new info().setXY(this.getX(), this.getY());
            if (comprobarTabla(true)) {
                boolean val = true;
                actualizarIndices();//actualizar datos.procesosPlantilla
                for (int i = 0; i < datos.getProcesosPlantilla().size(); i++) {
                    String proceso = idProceso.get(i) + "-"
                            + modelo.getValueAt(i, 0).toString() + "-"
                            + modelo.getValueAt(i, 1).toString() + "-"
                            + modelo.getValueAt(i, 2).toString();
                    try {
                        if (datos.getProcesosPlantilla().get(proceso).size() == 0) {
                            val = false;
                            //si algun proceso no tiene ninguna actividad relacionada
                        }
                    } catch (NullPointerException e) {
                        val = false;
                    }
                }
                if (val) {
                    if (!txtNombre.getText().equals(tN) && !txtCodigo.getText().equals(tC)) {//si hay algo en los campos de texto diferente al hint
                        if (valido) {
                            if (validar()) {
                                if (codigo.equals("")) {
                                    //insert
                                    leerC();
                                } else {
                                    //update
                                    update();

                                }
                            }
                        } else {
                            if (txtNombre.getText().equals("") || txtNombre.getText().equals(tN)) {
                                txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                            }
                            if (txtCodigo.getText().equals("") || txtCodigo.getText().equals(tC)) {
                                txtCodigo.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                            }
                            if (idioma.equals("English")) {
                                JOptionPane.showMessageDialog(context, "There are some invalid information or unfilled fields");
                            } else {
                                JOptionPane.showMessageDialog(context, "Hay informacion invalida o campos sin llenar");
                            }

                        }
                    } else {
                        if (txtNombre.getText().equals("") || txtNombre.getText().equals(tN)) {
                            txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                        }
                        if (txtCodigo.getText().equals("") || txtCodigo.getText().equals(tC)) {
                            txtCodigo.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                        }

                        if (idioma.equals("English")) {
                            JOptionPane.showMessageDialog(context, "Fill out all fields");
                        } else {
                            JOptionPane.showMessageDialog(context, "Llene todos los campos");
                        }
                    }
                } else {
                    tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(context, "Each proccess must have at least one activity");
                    } else {
                        JOptionPane.showMessageDialog(context, "Cada proceso debe tener al menos una actividad");
                    }
                }
            }
        } else {
            if (txtNombre.getText().equals("") || txtNombre.getText().equals(tN)) {
                txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
            if (txtCodigo.getText().equals("") || txtCodigo.getText().equals(tC)) {
                txtCodigo.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
            tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Template must have at least one proccess and\nat least an activity assigned to each proccess");
            } else {
                JOptionPane.showMessageDialog(context, "Debe haber al menos un proceso y\nal menos una actividad asignada por proceso");
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProcesoActionPerformed
        // TODO add your handling code here:
        modelo.addRow(new Object[]{"", "", ""});
        ids.put("" + (modelo.getRowCount() - 1), "");
        tablaPermisos.editCellAt(modelo.getRowCount() - 1, 0);
        Component aComp = tablaPermisos.getEditorComponent();
        aComp.requestFocus();
        idProceso.add("" + modelo.getRowCount());
        btnAddActividad.setEnabled(true);
        if (modelo.getRowCount() > 1) {
            btnAsignarPre.setEnabled(true);
        }
    }//GEN-LAST:event_btnAddProcesoActionPerformed

    private void btnAddActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActividadActionPerformed
        // TODO add your handling code here:
        try {
            new info().setXY(context.getX(), context.getY());
            if (procesosLlenadosE(tablaPermisos.getSelectedRow())) {
                String proceso = idProceso.get(tablaPermisos.getSelectedRow()) + "-"
                        + modelo.getValueAt(tablaPermisos.getSelectedRow(), 0).toString() + "-"
                        + modelo.getValueAt(tablaPermisos.getSelectedRow(), 1).toString() + "-"
                        + modelo.getValueAt(tablaPermisos.getSelectedRow(), 2).toString();
                List<String> a = datos.getProcesosPlantilla().get(ids.get("" + tablaPermisos.getSelectedRow()));
                if (a == null) {
                    a = new ArrayList();
                }
                datos.getProcesosPlantilla().remove(ids.get("" + tablaPermisos.getSelectedRow()));
                datos.setProcesosPlantilla(proceso, a);
                ids.put("" + tablaPermisos.getSelectedRow(), proceso);
                new extrasVersiones(con, user, priv, idioma, 0, proceso).setVisible(true);
            } else {
                if (idioma.equals("English")) {
                    JOptionPane.showMessageDialog(context, "Fill out all row fields");
                } else {
                    JOptionPane.showMessageDialog(context, "Llene todos los campos de la fila");
                }
            }
        } catch (Exception e) {
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Select a process to see the related activities");
            } else {
                JOptionPane.showMessageDialog(context, "Selecciona un proceso para ver las actividades relacionadas");
            }
        }

    }//GEN-LAST:event_btnAddActividadActionPerformed

    private void tablaPermisosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPermisosMouseClicked
        // TODO add your handling code here:
        if (modelo.getRowCount() > 0) {
            tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
        try {
            if (comprobarTabla(false)) {
                rc.clear();
            }
        } catch (Exception ess) {
            System.out.println("Erroreeeees: " + ess);
        }
        if (evt.getButton() == 3) {
            Point p = evt.getPoint();
            int rowNumber = tablaPermisos.rowAtPoint(p);
            ListSelectionModel modelo1 = tablaPermisos.getSelectionModel();
            modelo1.setSelectionInterval(rowNumber, rowNumber);
            String texto1, texto2, o1, o2;
            if (idioma.equals("English")) {
                texto1 = "Are you sure you want to delete the selected proccess?";
                texto2 = "Confirm Action";
                o1 = "Yes";
                o2 = "No";
            } else {
                texto1 = "¿Seguro que quiere eliminar el proceso seleccionado?";
                texto2 = "Confirmar Acción";
                o1 = "Si";
                o2 = "No";
            }

            Object[] options = {o1, o2};
            if (JOptionPane.showOptionDialog(this, texto1, texto2,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]) == 0) {
                try {
                    eliminados.add(modelo.getValueAt(rowNumber, 0).toString() + "-"
                            + modelo.getValueAt(rowNumber, 1).toString() + "-"
                            + modelo.getValueAt(rowNumber, 2).toString());
                    datos.getProcesosPlantilla().remove((rowNumber + 1) + "-" + modelo.getValueAt(rowNumber, 0).toString() + "-"
                            + modelo.getValueAt(rowNumber, 1).toString() + "-"
                            + modelo.getValueAt(rowNumber, 2).toString());
                    ids.remove("" + rowNumber);
                    for (int i = (rowNumber + 1); i < modelo.getRowCount(); i++) {
                        String a = ids.get("" + i);
                        ids.put("" + (i - 1), a);
                    }
                    ids.remove("" + modelo.getRowCount());
                    modelo.removeRow(rowNumber);
                } catch (NullPointerException e) {
                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(context, "Select a proccess to delete");
                    } else {
                        JOptionPane.showMessageDialog(context, "Seleccione un proceso para eliminar");
                    }
                }

            }
        }
    }//GEN-LAST:event_tablaPermisosMouseClicked

    private void tablaPermisosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaPermisosFocusLost
        // TODO add your handling code here:
        try {
            if (comprobarTabla(false)) {
                rc.clear();
            }
        } catch (Exception ess) {
            System.out.println("Erroreeeees: " + ess);
        }
    }//GEN-LAST:event_tablaPermisosFocusLost

    private void tablaPermisosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaPermisosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaPermisosFocusGained

    private void tablaPermisosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPermisosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            try {
                if (comprobarTabla(false)) {
                    rc.clear();
                }
            } catch (Exception ess) {
                System.out.println("Erroreeeees: " + ess);
            }
        }
    }//GEN-LAST:event_tablaPermisosKeyPressed

    private void tablaPermisosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tablaPermisosInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_tablaPermisosInputMethodTextChanged

    private void txtCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusGained
        // TODO add your handling code here:
        JTextField t = txtCodigo;
        if (t.getText().equals(tC)) {
            t.setText("");
            t.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtCodigoFocusGained

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        // TODO add your handling code here:
        JTextField t = txtCodigo;
        if (!t.getText().equals("")) {
            try {
                if (t.getText().length() > 10) {
                    t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    valido = false;
                } else {

                    t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    valido = true;
                }

            } catch (Exception e) {
                valido = false;
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
        } else {
            valido = false;
            t.setText(tC);
            t.setForeground(Color.lightGray);
            t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        if (txtCodigo.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        // TODO add your handling code here:
        JTextField t = txtNombre;
        if (t.getText().equals(tN)) {
            t.setText("");
            t.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
        JTextField t = txtNombre;
        if (!t.getText().equals("")) {
            try {
                if (t.getText().length() > 20) {
                    t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    valido = false;
                } else {
                    t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    valido = true;
                }

            } catch (Exception e) {
                valido = false;
                t.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
        } else {
            valido = false;
            t.setText(tN);
            t.setForeground(Color.lightGray);
            t.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if (txtCodigo.getText().length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnAsignarPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarPreActionPerformed
        // TODO add your handling code here:
        try {
            new info().setXY(this.getX(), this.getY());
            ArrayList<String> xd = new ArrayList();
            String procesoSel = modelo.getValueAt(tablaPermisos.getSelectedRow(), 0).toString();
            String codigo = idProceso.get(tablaPermisos.getSelectedRow());
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (!modelo.getValueAt(i, 0).toString().equals(procesoSel)) {
                    xd.add(modelo.getValueAt(i, 0).toString());
                }
            }
            String a = requisitos.get(codigo);
            int num = 0;
            try {
                num = Integer.parseInt(a);
            } catch (Exception e) {
                // System.out.println("ex: "+e);
            }
            new requisitos(con, user, priv, idioma, xd, num, this, codigo, tablaPermisos.getSelectedRow()).setVisible(true);
        } catch (Exception e) {
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Select a process to see its required processes");
            } else {
                JOptionPane.showMessageDialog(context, "Selecciona un proceso para ver sus procesos requeridos");
            }
        }
    }//GEN-LAST:event_btnAsignarPreActionPerformed

    private void pnlDerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDerMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlDerMousePressed

    private void scroolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scroolKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            try {
                if (comprobarTabla(false)) {
                    rc.clear();
                }
            } catch (Exception ess) {
                System.out.println("Erroreeeees: " + ess);
            }
        }
    }//GEN-LAST:event_scroolKeyPressed

    public void setRequisito(String proceso, String requisito) {
        requisitos.put(proceso, requisito);
    }

    public boolean validar() {
        boolean valido = true;
        int codigoError = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                switch (j) {
                    case 0:
                        break;
                    case 1:
                        if (Integer.parseInt(modelo.getValueAt(i, j).toString()) < 0 || Integer.parseInt(modelo.getValueAt(i, j).toString()) > 100) {
                            codigoError = 2;
                            valido = false;
                        }
                        break;

                    case 2:
                        break;
                }
            }
        }
        if (!valido) {
            switch (codigoError) {
                case 1:
                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(context, "Proccesses code must have almost 8 characters");
                    } else {
                        JOptionPane.showMessageDialog(context, "El codigo de los procesos debe tener al menos 8 caracteres");
                    }
                    break;
                case 2:
                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(context, "Pictures probability value must be between 0 and 100");
                    } else {
                        JOptionPane.showMessageDialog(context, "El valor de probabilidad de foto debe estar entre 0 y 10");
                    }
                    break;
            }
        }
        return valido;

    }

    private void actualizarIndices() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            List<String> a = datos.getProcesosPlantilla().get(ids.get("" + i));
            datos.getProcesosPlantilla().remove(ids.get("" + i));
            String proceso = idProceso.get(i) + "-"
                    + modelo.getValueAt(i, 0).toString() + "-"
                    + modelo.getValueAt(i, 1).toString() + "-"
                    + modelo.getValueAt(i, 2).toString();
            datos.setProcesosPlantilla(proceso, a);
        }
    }

    private boolean procesosLlenadosE(int row) {
        int a = 0;
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            if (modelo.getValueAt(row, i).toString().equals("")) {
                a++;
            }
        }
        if (a == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void update() {
        try {
           // plantillas user = new plantillas(txtNombre.getText().toString().toLowerCase().trim());
            con.child("plantillas").child(txtCodigo.getText().toString().toLowerCase().trim()).setValue(user, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                }
            });
            updatePro();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }

    }

    private void updatePro() {
        con.child("procesos").child(codigo).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
                try {
                    for (int i = 0; i < datos.getProcesosPlantilla().size(); i++) {
                        Object[] keys = datos.getProcesosPlantilla().keySet().toArray();
                        StringTokenizer st = new StringTokenizer(keys[i].toString(), "-");
                        String codigo = st.nextToken().trim();
                       // procesos pro = new procesos(st.nextToken().trim(), Integer.parseInt(st.nextToken().trim()), Float.parseFloat(st.nextToken().trim()), Integer.parseInt(requisitos.get(codigo)));
                       // con.child("procesos").child(txtCodigo.getText().toString().toLowerCase()).child(codigo).setValue(pro, new DatabaseReference.CompletionListener() {
                         //   @Override
                         //   public void onComplete(DatabaseError de, DatabaseReference dr) {
                         //   }
                        //});
                    }
                    updateAct();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(context, "Error: " + e);
                }
            }
        });

    }

    private void updateAct() {
        try {
            for (int i = 0; i < aux.size(); i++) {
                StringTokenizer st = new StringTokenizer(aux.get(i).toString(), "-");
                String y = st.nextToken().trim();
                con.child("actividades").child(codigo).child(y).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError de, DatabaseReference dr) {
                    }
                });
            }
            leerExtras();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);

        }

        try {
            Object[] keys = datos.getProcesosPlantilla().keySet().toArray();
            for (int i = 0; i < datos.getProcesosPlantilla().size(); i++) {
                ArrayList lista = (ArrayList) datos.getProcesosPlantilla().get(keys[i]);
                for (int j = 0; j < lista.size(); j++) {
                    StringTokenizer st = new StringTokenizer(keys[i].toString(), "-");
                    String plantilla = st.nextToken().trim();
                  //  actividades pro = new actividades("1");
                //    con.child("actividades").child(codigo).child(plantilla).child(lista.get(j).toString()).setValue(pro, new DatabaseReference.CompletionListener() {
                      //  @Override
                      //  public void onComplete(DatabaseError de, DatabaseReference dr) {
                      //  }
                  //  });
                }
            }
            upEx();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }

    }

    private void upEx() {
        try {
            updateExtra(new RolCallback1() {
                @Override
                public void rolRecibido(ArrayList rol) {
                    for (int i = 0; i < az.size(); i++) {
                        rol.remove(az.get(i).toString());
                    }
                    for (int i = 0; i < rol.size(); i++) {
                        con.child("extra").child(rol.get(i).toString()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                            }
                        });
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < datos.getExtras().size(); i++) {
              //  extras1 pro = new extras1(datos.getExtras().get(i).toString(), txtNombre.getText().toLowerCase().trim());
                az.add(con.child("extra").push().getKey());
              //  con.child("extra").child(az.get(i)).setValue(pro, new DatabaseReference.CompletionListener() {
               //     @Override
                //    public void onComplete(DatabaseError de, DatabaseReference dr) {
               //     }
              //  });
            }
            upVer();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }

    }

    private void updateExtra(RolCallback1 callback) throws InterruptedException {
        try {
            Query query = con.child("extra").orderByChild("plantilla").equalTo(plantilla.toLowerCase());

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                    } else {
                        ArrayList a = new ArrayList();
                        for (DataSnapshot user : snapshot.getChildren()) {
                            a.add(user.getKey());
                        }
                        if (callback != null) {
                            callback.rolRecibido(a);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private interface RolCallback {

        void rolRecibido(final ArrayList rol);
    }

    private void recuperaRol(RolCallback callback) throws InterruptedException {
        try {
            Query query = con.child("version").orderByChild("plantilla").equalTo(plantilla.toLowerCase());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                    } else {
                        ArrayList a = new ArrayList();
                        for (DataSnapshot user : snapshot.getChildren()) {
                            a.add(user.getKey());
                        }
                        if (callback != null) {
                            callback.rolRecibido(a);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);

        }

    }

    private void upVer() {
        try {
            for (int i = 0; i < datos.getVersiones().size(); i++) {
                versiones pro = new versiones(txtNombre.getText().toLowerCase().trim(), datos.getVersiones().get(i).toString());
                az.add(con.child("version").push().getKey());
                con.child("version").child(az.get(i)).setValue(pro, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError de, DatabaseReference dr) {
                    }
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
        try {
            recuperaRol(new RolCallback() {
                @Override
                public void rolRecibido(ArrayList rol) {
                    for (int i = 0; i < az.size(); i++) {
                        rol.remove(az.get(i).toString());
                    }
                    for (int i = 0; i < rol.size(); i++) {
                        con.child("version").child(rol.get(i).toString()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError de, DatabaseReference dr) {
                            }
                        });
                    }

                }
            });
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
        if (idioma.equals("english")) {
            JOptionPane.showMessageDialog(context, "The template has been modified");

        } else {
            JOptionPane.showMessageDialog(context, "Plantilla modificada");

        }
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuPlantillas(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }

    private interface RolCallback1 {

        void rolRecibido(final ArrayList rol);
    }

    private void leerC() {//comprueba que el codigo de plantilla ingresado no exista ya
        try {
            Query query = con.child("plantillas").child(txtCodigo.getText().toString().toLowerCase());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        insert();
                    } else {
                        if (idioma.equals("english")) {
                            JOptionPane.showMessageDialog(context, "Template's code already exists");
                        } else {
                            JOptionPane.showMessageDialog(context, "El codigo de plantilla ya existe");
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }

            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private void insert() {//inserta la plantilla
        this.setCursor(new Cursor(WAIT_CURSOR));
        try {
           // plantillas user = new plantillas(txtNombre.getText().toString().toLowerCase().trim());
           // con.child("plantillas").child(txtCodigo.getText().toString().toLowerCase().trim()).setValue(user, new DatabaseReference.CompletionListener() {
            //    @Override
            //    public void onComplete(DatabaseError de, DatabaseReference dr) {
             //   }
           // });
            insertPro();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private void insertPro() {//inserta la lista de procesos
        try {
            Object[] keys = datos.getProcesosPlantilla().keySet().toArray();
            for (int i = 0; i < datos.getProcesosPlantilla().size(); i++) {//recorre los procesos
                
                StringTokenizer st = new StringTokenizer(keys[i].toString(), "-");
                String codigo = st.nextToken().trim();
             //   procesos pro = new procesos(st.nextToken().trim(), Integer.parseInt(st.nextToken().trim()), Float.parseFloat(st.nextToken().trim()), Integer.parseInt(requisitos.get(codigo)));
              //  con.child("procesos").child(txtCodigo.getText().toString().toLowerCase()).child(codigo).setValue(pro, new DatabaseReference.CompletionListener() {
               //     @Override
              //      public void onComplete(DatabaseError de, DatabaseReference dr) {
              //      }
              //  });
            }
            insertAct();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }

    }

    private void insertAct() {//inserta la lista de actividades
        try {
            Object[] keys = datos.getProcesosPlantilla().keySet().toArray();
            for (int i = 0; i < datos.getProcesosPlantilla().size(); i++) {//se recorre el array (de procesos)
                ArrayList lista = (ArrayList) datos.getProcesosPlantilla().get(keys[i]);//obtiene las actividades de un proceso
                for (int j = 0; j < lista.size(); j++) {//recorre las actividades
                    String plantilla = txtCodigo.getText().toString().toLowerCase();
                 //   actividades pro = new actividades("1");
                  //  con.child("actividades").child(plantilla).child("" + (i + 1)).child(lista.get(j).toString()).setValue(pro, new DatabaseReference.CompletionListener() {
                   //     @Override
                    //    public void onComplete(DatabaseError de, DatabaseReference dr) {
                   //     }
                   // });
                }
            }
            insertExtra();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private void insertExtra() {//inserta los extras
        try {
            for (int i = 0; i < datos.getExtras().size(); i++) {
               // extras1 pro = new extras1(datos.getExtras().get(i).toString(), txtNombre.getText().toString().toLowerCase().trim());
             //   con.child("extra").push().setValue(pro, new DatabaseReference.CompletionListener() {
              //      @Override
               //     public void onComplete(DatabaseError de, DatabaseReference dr) {
               //     }
              //  });
            }
            insertVersion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private void insertVersion() {//inserta las versiones
        try {
            for (int i = 0; i < datos.getVersiones().size(); i++) {
                versiones pro = new versiones(txtNombre.getText().toString().toLowerCase().trim(), datos.getVersiones().get(i).toString());
                con.child("version").push().setValue(pro, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError de, DatabaseReference dr) {
                    }
                });
            }
            if (idioma.equals("English")) {
                JOptionPane.showMessageDialog(context, "Template added");
            } else {
                JOptionPane.showMessageDialog(context, "Plantilla agregada");
            }
            new info().setXY(this.getX(), this.getY());
            new menuPlantillas(con, user, priv, idioma).setVisible(true);//regresa al menu
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private boolean comprobarTabla(boolean save) {
       //flad determina si los datos son validos o no
        boolean flag = true;
        int b = 0, c = 0, d = 0, e = 0, f = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int a = 0;
            if (modelo.getValueAt(i, 0).toString().equals("")) {//si algun campo de nombre de proceso se encuentra vacio
                a++;
                if (!rc.contains(i)) {
                    rc.add(i + "-" + 0);
                }
            }
            if (modelo.getValueAt(i, 1).toString().equals("")) {//si algun campo de probabilidad se encuentra vacio
                a++;
                if (!rc.contains(i)) {
                    rc.add(i + "-" + 1);
                }
            }
            if (Integer.parseInt(modelo.getValueAt(i, 1).toString()) > 100
                    || Integer.parseInt(modelo.getValueAt(i, 1).toString()) < 0) {//si algun campo de probabilidad tiene un valor invalido
                d++;
                if (!rc.contains(i)) {
                    rc.add(i + "-" + 1);
                }
            }
            if (modelo.getValueAt(i, 2).toString().equals("")) {//si algun campo de peso se encuentra vacio
                a++;
                if (!rc.contains(i)) {
                    rc.add(i + "-" + 2);
                }
            }
            if (Float.parseFloat(modelo.getValueAt(i, 2).toString()) > 50
                    || Float.parseFloat(modelo.getValueAt(i, 2).toString()) < 0) {//si algun campo de peso tiene un  valor invalido
                e++;
                if (!rc.contains(i)) {
                    rc.add(i + "-" + 2);
                }
            }
            if (a == 3) {//si las 3 columnas de encuentran vacias, se borra la fila
                modelo.removeRow(i);
                rc.remove(i + "-" + 0);
                rc.remove(i + "-" + 1);
                rc.remove(i + "-" + 2);

            } else if (a != 0) {//si tiene algun dato vacio o invalido
                flag = false;
                if (b == 0) {//para que solo entre una vez
                    b++;
                    tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    if (save) {
                        if (idioma.equals("English")) {
                            JOptionPane.showMessageDialog(context, "Fill out all blanks on the table");
                        } else {
                            JOptionPane.showMessageDialog(context, "Llene todos los campos de la tabla");
                        }
                    }
                }
            }
            if (modelo.getValueAt(i, 0).toString().charAt(modelo.getValueAt(i, 0).toString().length() - 1) >= '0'
                    && modelo.getValueAt(i, 0).toString().charAt(modelo.getValueAt(i, 0).toString().length() - 1) <= '9') {
                f++;
                //si el ultimo caracter del nombre de un proceso es un numero
                if (!rc.contains(i)) {
                    rc.add(i + "-" + 0);
                }
            }
        }
        if (c > 0) {
            
            flag = false;
            tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            if (save) {
                if (idioma.equals("English")) {
                    JOptionPane.showMessageDialog(context, "Code cannot be longer than 8 characters");
                } else {
                    JOptionPane.showMessageDialog(context, "El codigo no puede ser mas largo que 8 caracteres");
                }
            }
        }
        if (d > 0) {
            flag = false;
            tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            if (save) {
                if (idioma.equals("English")) {
                    JOptionPane.showMessageDialog(context, "Photo probability must be between 0 and 100");
                } else {
                    JOptionPane.showMessageDialog(context, "La probabilidad de foto debe estar entre 0 y 100");
                }
            }
        }
        if (e > 0) {
            flag = false;
            tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            if (save) {
                if (idioma.equals("English")) {
                    JOptionPane.showMessageDialog(context, "Weight must be between 0 and 50");
                } else {
                    JOptionPane.showMessageDialog(context, "El peso debe estar entre 0 y 50");
                }
            }
        }
        if (f > 0) {
            flag = false;
            tablaPermisos.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            if (save) {
                if (idioma.equals("English")) {
                    JOptionPane.showMessageDialog(context, "Last character in the name of a process cannot be a number");
                } else {
                    JOptionPane.showMessageDialog(context, "El ultimo caracter en el nombre de un proceso no puede ser un numero");
                }
            }
        }
        return flag;
    }

    private void leer() {
        try {//lee los procesos
            Query query = con.child("procesos").child(codigo);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                    //    procesos log = null;
                        for (DataSnapshot user : snapshot.getChildren()) {
                            try {
                             //   log = user.getValue(procesos.class);
                                //aux contiene codigo de proceso-proceso-probabilidad-peso
                             //   aux.add(user.getKey() + "-" + log.getProceso()
                             //           + "-" + log.getProba() + "-" + log.getPeso());
                                //requisitos contiene codigo de proceso-requisito
                            //    requisitos.put(user.getKey(), "" + log.getRequisito());
                            } catch (Exception e) {
                            }

                        }
                        leerAct();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);

        }
    }

    private void leerExtras() {
        try {
            Query query = con.child("extra").orderByChild("plantilla").equalTo(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                       // extras1 log = null;
                        for (DataSnapshot user : snapshot.getChildren()) {
                          //  log = user.getValue(extras1.class);
                            try {
                          //      datos.setExtras(log.getExtra());
                            } catch (Exception e) {
                                System.out.println("" + e);
                            }
                        }
                    }
                    leerVer();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private void leerVer() {
        try {
            Query query = con.child("version").orderByChild("plantilla").equalTo(plantilla);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        versiones log = null;
                        for (DataSnapshot user : snapshot.getChildren()) {
                            log = user.getValue(versiones.class);
                            try {
                                datos.setVersiones(log.getTipo());
                            } catch (Exception e) {
                                System.out.println("" + e);
                            }
                        }
                    }
                    btnAdd.setEnabled(true);
                    btnAddProceso.setEnabled(true);
                    btnAddActividad.setEnabled(true);
                    btnExtras.setEnabled(true);
                    btnVersiones.setEnabled(true);
                    for (int i = 0; i < aux.size(); i++) {
                        StringTokenizer st = new StringTokenizer(aux.get(i), "-");
                        idProceso.add(st.nextToken());
                        modelo.addRow(new Object[]{st.nextToken(), st.nextToken(), st.nextToken()});
                        ids.put("" + i, aux.get(i));
                    }
                    listo = 1;
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    JOptionPane.showMessageDialog(context, "Error: " + error);
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);
        }
    }

    private void leerAct() {//lee actividades
        try {
            for (int i = 0; i < aux.size(); i++) {//recorre proceso por proceso
                final int x = i;
                ArrayList ac = new ArrayList();
                StringTokenizer st = new StringTokenizer(aux.get(i), "-");
                String y = st.nextToken().trim();//separa el codigo del proceso del resto
                Query query = con.child("actividades").child(codigo).child(y);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot user : snapshot.getChildren()) {
                                try {
                                    ac.add(user.getKey());//obtiene las actividades relacionadas a un proceso
                                } catch (Exception e) {
                                    System.out.println("" + e);
                                }

                            }
                            datos.setProcesosPlantilla(aux.get(x), ac);//agrega el proceso con su info y sus actividades relacionadas
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        JOptionPane.showMessageDialog(context, "Error: " + error);
                    }
                });
            }
            System.out.println("AS: " + datos.getProcesosPlantilla());
            leerExtras();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(context, "Error: " + e);

        }
    }

    private void ingles() {
        lblTitulo.setText("Template view");
        JTableHeader tableHeader = tablaPermisos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(0);
        tableColumn.setHeaderValue("Code");
        tableColumn = tableColumnModel.getColumn(1);
        tableColumn.setHeaderValue("Proccess");
        tableColumn = tableColumnModel.getColumn(2);
        tableColumn.setHeaderValue("Ps. probability");
        tableColumn = tableColumnModel.getColumn(3);
        tableColumn.setHeaderValue("Weight");
        tableHeader.repaint();
        btnExtras.setToolTipText("<html><b style='font-size: 12px;'>Manage template's extras</b></html>");
        btnVersiones.setToolTipText("<html><b style='font-size: 12px;'>Manage template's versions</b></html>");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Save template</b></html>");
        btnAddProceso.setToolTipText("<html><b style='font-size: 12px;'>Add a proccess</b></html>");
        btnAsignarPre.setToolTipText("<html><b style='font-size: 12px;'>Manage required processes</b></html>");
        btnAddActividad.setToolTipText("<html><b style='font-size: 12px;'>Manage activities assigned to the selected proccess</b></html>");
        tN = "Ex. Skywalker";
        tC = "Ex. SKY";
        txtCodigo.setText(tC);
        txtNombre.setText(tN);
        lbCodigo.setText("Template's code: ");
        lblNombre.setText("Template's name:");
        btnVersiones.setText("Versions");
    }

    private void esp() {
        lblTitulo.setText("Vista de plantilla");
        lbCodigo.setText("Codigo de plantilla: ");
        lblNombre.setText("Nombre de plantilla: ");
        tN = "Ej. Skywalker";
        tC = "Ej. SKY";
        txtCodigo.setText(tC);
        txtNombre.setText(tN);
        btnAsignarPre.setToolTipText("<html><b style='font-size: 12px;'>Administrar procesos requeridos</b></html>");
        btnExtras.setToolTipText("<html><b style='font-size: 12px;'>Administrar extras de la plantilla</b></html>");
        btnVersiones.setToolTipText("<html><b style='font-size: 12px;'>Administrar versiones de la plantilla</b></html>");
        btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Guardar plantilla</b></html>");
        btnAddProceso.setToolTipText("<html><b style='font-size: 12px;'>Agregar un proceso</b></html>");
        btnAddActividad.setToolTipText("<html><b style='font-size: 12px;'>Administrar actividades asignadas al proceso seleccionado</b></html>");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddActividad;
    private javax.swing.JButton btnAddProceso;
    private javax.swing.JButton btnAsignarPre;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnExtras;
    private javax.swing.JButton btnVersiones;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JScrollPane scrool;
    private javax.swing.JTable tablaPermisos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
