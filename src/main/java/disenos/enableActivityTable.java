/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disenos;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class enableActivityTable extends DefaultTableCellRenderer {//clase para pintar de colores la tabla de la interfaz vistaActividades1

    //declaracion de variables globales
    private ArrayList<Boolean> habilitar;//filas con procesos ya terminados
    private Component cellComponent;

    public enableActivityTable(ArrayList<Boolean> habilitar) {
        this.habilitar = habilitar;
    }

    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
        cellComponent = super.getTableCellRendererComponent(table, color, isSelected, hasFocus, row, column);

        if (!habilitar.get(row)) {//si el proceso de la fila ya se realizo
            if (isSelected) {//se pinta de gris asi este seleccionado o no
                cellComponent.setBackground(Color.LIGHT_GRAY);
                cellComponent.setForeground(Color.WHITE);
            } else {
                cellComponent.setBackground(Color.LIGHT_GRAY);
                cellComponent.setForeground(Color.BLACK);
            }
            
        } else {//si el proceso aun no se completa
            if (isSelected) {//se pinta de azul la fila si se selecciona
                cellComponent.setBackground(colores.azul);
                cellComponent.setForeground(Color.WHITE);
            } else {//o de blanco si se deselecciona
                cellComponent.setBackground(Color.WHITE);
                cellComponent.setForeground(Color.BLACK);
            }
        }
        int align = DefaultTableCellRenderer.CENTER;
        ((DefaultTableCellRenderer) cellComponent).setHorizontalAlignment(align);
        return cellComponent;
    }
}
