/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disenos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author dell
 */
public class centerTextInTable extends DefaultTableCellRenderer {

    public centerTextInTable() {

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        JLabel lbl = new JLabel(value == null ? "" : value.toString());//si value es null, se pone dato vacio, si no, se pone lo que tenga esta
        lbl.setHorizontalAlignment(SwingConstants.CENTER);//centra la info en la celda
        lbl.setFont(new Font("Lato", Font.PLAIN, 24));//fuente del cuerpo de la tabla
        lbl.setOpaque(true);

        if (isSelected) {
            lbl.setBackground(colores.azul);
            lbl.setForeground(Color.WHITE);
        } else {
            lbl.setBackground(Color.white);
            lbl.setForeground(colores.getNegro());
        }

        return lbl;
    }

}
