package interfaces;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import configuracion.info;
import disenos.centerTextInTable;
import disenos.ventanas.configuracionVentana;
import disenos.disenoTabla;
import disenos.disenos;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class menuPlantillas extends JFrame {//clase que muestra todas las plantillas

    //declaracion de variables globales
    private DatabaseReference con;
    private int priv, check;
    private String user, idioma;
    private DefaultTableModel modelo;//modelo de la tabla
    private JFrame context;

    public menuPlantillas(DatabaseReference con, String user, int priv, String idioma, int check) {//constructor
        initComponents();
        new configuracionVentana(this);
        //poner icono
        //inicializacion de variables
        context = this;
        modelo = (DefaultTableModel) tablaUsers.getModel();
        this.con = con;
        this.check = check;
        this.priv = priv;
        this.user = user;
        this.idioma = idioma;
        mostrar();
        iniciarDiseno();
        leer();
    }

    public void iniciarDiseno() {//decorar los componentes del frame
        //centrar el texto de las celdas de la tabla
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        modelo = (DefaultTableModel) tablaUsers.getModel();
       // tcr.setHorizontalAlignment(SwingConstants.CENTER);
      //  for (int i = 0; i < modelo.getColumnCount(); i++) {
       //     tablaUsers.getColumnModel().getColumn(i).setCellRenderer(tcr);
      //  }
        tablaUsers.setDefaultRenderer(Object.class, new centerTextInTable());
        lblTitulo.setHorizontalAlignment(JLabel.LEFT);
        txtBuscar.setHorizontalAlignment(JLabel.CENTER);

        pnlIzq.setFocusable(true);
        pnlFondo.setFocusable(true);
        pnlCuerpo.setFocusable(true);
        pnlDer.setFocusable(true);
        pnlCabecera.setFocusable(true);

        new disenos().botones(btnEliminar, 3);
        new disenos().botones(btnAdd, 3);
        new disenos().botones(btnAtras, 3);
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
        tablaUsers.requestFocus();
        txtBuscar.setForeground(Color.LIGHT_GRAY);

        new disenoTabla().cabecera(tablaUsers);
    }

    public void ponerImg(JButton b, String ruta) {//le pone una imagen a los botones
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
        pnlIzq = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
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
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout pnlDerLayout = new javax.swing.GroupLayout(pnlDer);
        pnlDer.setLayout(pnlDerLayout);
        pnlDerLayout.setHorizontalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        pnlDerLayout.setVerticalGroup(
            pnlDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlIzq.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pnlIzqFocusGained(evt);
            }
        });
        pnlIzq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlIzqMousePressed(evt);
            }
        });
        pnlIzq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlIzqKeyPressed(evt);
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

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
        });

        tablaUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plantilla"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaUsers.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaUsersFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsers);
        if (tablaUsers.getColumnModel().getColumnCount() > 0) {
            tablaUsers.getColumnModel().getColumn(0).setResizable(false);
            tablaUsers.getColumnModel().getColumn(0).setPreferredWidth(500);
        }

        javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(pnlCuerpo);
        pnlCuerpo.setLayout(pnlCuerpoLayout);
        pnlCuerpoLayout.setHorizontalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );
        pnlCuerpoLayout.setVerticalGroup(
            pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
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
                    .addComponent(pnlDer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            int sel = tablaUsers.getSelectedRow();
            if (sel == -1) {
                new info().setXY(this.getX(), this.getY());
                if(check==2){
                    new vistaPlantillasTT(con, user, priv, idioma, null, check).setVisible(true);
                }else{
                    new vistaPlantillas(con, user, priv, idioma, null, check).setVisible(true);
                }
                this.dispose();
            } else {
                new info().setXY(this.getX(), this.getY());
                if(check==2){
                    new vistaPlantillasTT(con, user, priv, idioma, modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString(),
                        check).setVisible(true);
                }else{
                    new vistaPlantillas(con, user, priv, idioma, modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString(),
                        check).setVisible(true);
                }
                
                this.dispose();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        new info().setXY(this.getX(), this.getY());
        this.setCursor(new Cursor(WAIT_CURSOR));
        new menuCheckList(con, user, priv, idioma).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        //pedir confirmacion 
        String texto1, texto2, o1, o2;
        if (idioma.equals("English")) {
            texto1 = "Are you sure you want to delete the selected template?";
            texto2 = "Confirm Action";
            o1 = "Yes";
            o2 = "No";
        } else {
            texto1 = "¿Seguro que quiere eliminar la plantilla seleccionada?";
            texto2 = "Confirmar Acción";
            o1 = "Si";
            o2 = "No";
        }
        Object[] options = {o1, o2};
        if (JOptionPane.showOptionDialog(this, texto1, texto2,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]) == 0) {
            try {
                String id = modelo.getValueAt(tablaUsers.getSelectedRow(), 0).toString();
               // String plantilla = modelo.getValueAt(tablaUsers.getSelectedRow(), 1).toString();
                eliminar(id, tablaUsers.getSelectedRow());
            } catch (NullPointerException e) {
                if (idioma.equals("English")) {
                    JOptionPane.showMessageDialog(context, "Select a template to delete");
                } else {
                    JOptionPane.showMessageDialog(context, "Seleccione una plantilla para eliminar");
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscar(txtBuscar.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {//si se presiona el enter con el focus en el campo de usuario
            buscar(txtBuscar.getText());
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void tablaUsersFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaUsersFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaUsersFocusLost

    private void pnlIzqFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pnlIzqFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_pnlIzqFocusGained

    private void pnlIzqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlIzqKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlIzqKeyPressed

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

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed
        // TODO add your handling code here:
        tablaUsers.clearSelection();
    }//GEN-LAST:event_jScrollPane1MousePressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        if (txtBuscar.getText().length() == 25) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private String getTableName() {
        String plantilla = "";
        switch (check) {
            case 1:
                plantilla = "plantillasCalidad";
                break;
            case 2:
                plantilla = "plantillasTesting";
                break;
            case 3:
                plantilla = "plantillasShipment";
                break;
            case 4:
                plantilla = "plantillasFinal";
                break;
        }
        return plantilla;
    }

    private void eliminar(String id, int position) {//eliminar la plantilla seleccionada
        try {
            con.child(getTableName()).child(id).removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                    modelo.removeRow(position);
                    if (idioma.equals("English")) {
                        JOptionPane.showMessageDialog(context, "Template deleted");
                    } else {
                        JOptionPane.showMessageDialog(context, "Plantilla eliminada");
                    }
                }
            });

            // localDataSet1.remove(position);
        } catch (Exception e) {

        }
       
    }

    private void buscar(String bus) {//busca alguna plantilla si su nombre coincide en parte con la busqueda
        try {
            Query query = con.child(getTableName()).child(bus);//orderByChild("nombre").startAt(bus).endAt(bus + "\uf8ff");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        limpiarTabla();
                        ArrayList keys = new ArrayList();
                        try {
                            keys.add(bus.toLowerCase());
                            llenarTabla(keys);
                        } catch (Exception e) {
                            // Toast.makeText(getApplicationContext(), "Notifica el siguiente error: " + e, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if(modelo.getRowCount()<2){
                            limpiarTabla();
                            leer();
                        }
                        if (idioma.equals("english")) {
                            // Toast.makeText(getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                        } else {
                            // Toast.makeText(getApplicationContext(), "No results found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Toast.makeText(getApplicationContext(), "De " + error, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            //Context context = getApplicationContext();
            // Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            //  toast.show();
        }

    }

    private void leer() {//se leen todas las plantillas 
        try {

            Query query = null;
            query = con.child(getTableName());//.orderByChild("nombre");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        limpiarTabla();
                        ArrayList keys = new ArrayList();
                        try {
                            for (DataSnapshot user : snapshot.getChildren()) {
                                keys.add(user.getKey());

                            }
                            llenarTabla(keys);
                        } catch (Exception e) {
                            // Toast.makeText(getApplicationContext(), "Notifica el siguiente error: " + e, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (idioma.equals("english")) {
                            // Toast.makeText(getApplicationContext(), "There are no templates", Toast.LENGTH_LONG).show();
                        } else {
                            //Toast.makeText(getApplicationContext(), "No existen plantillas", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Toast.makeText(getApplicationContext(), "De " + error, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            // Context context = getApplicationContext();
            //  Toast toast = Toast.makeText(context, "Notifica el siguiente error:" + e, Toast.LENGTH_SHORT);
            //  toast.show();
        }
    }

    private void limpiarTabla() {
        int size = modelo.getRowCount();
        for (int i = 0; i < size; i++) {
            modelo.removeRow(0);
        }

    }

    private void llenarTabla(ArrayList<String> nombre) {
        for (int i = 0; i < nombre.size(); i++) {
            modelo.addRow(new Object[]{nombre.get(i)});
        }

    }

    private void mostrar() {
        if (idioma.equals("english")) {
            txtBuscar.setText("Search");
            lblTitulo.setText("Templates menu");
            JTableHeader tableHeader = tablaUsers.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Code");
            tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Template");
            tableHeader.repaint();
            btnEliminar.setToolTipText("<html><b style='font-size: 12px;'>Delete selected template</b></html>");
            btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Add/Edit templates</b></html>");
        } else {
            txtBuscar.setText("Buscar");
            lblTitulo.setText("Menu de plantillas");
            btnEliminar.setToolTipText("<html><b style='font-size: 12px;'>Eliminar la plantilla seleccionada</b></html>");
            btnAdd.setToolTipText("<html><b style='font-size: 12px;'>Agregar/Editar plantillas</b></html>");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDer;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlIzq;
    private javax.swing.JTable tablaUsers;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
