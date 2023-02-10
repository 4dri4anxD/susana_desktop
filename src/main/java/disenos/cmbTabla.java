package disenos;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class cmbTabla extends DefaultTableCellRenderer {//clase para poner un combobox dentro de una celda de tabla en la interfaz de vistaActividades

    //declaracion de variables globales
    private int col = -1;//columna
    private ArrayList<String> nombres;//filas con procesos ya realizados
    private Component cellComponent;

    public cmbTabla(int col, ArrayList<String> nombres) {//constructor
        this.nombres = nombres;
        this.col = col;
    }

    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
        cellComponent = super.getTableCellRendererComponent(table, color, isSelected, hasFocus, row, column);

        //retorna un combo con el valor seleccionado
        
        if (col == column) {//la columna que tiene el JComboBox
            //le agrega los nombres de los trabajadores al combo box
            JComboBox comboBox = new JComboBox();
            comboBox.addItem(color);
            comboBox.setSelectedIndex(0);
            return comboBox;
        }
        
        if (nombres.contains("" + row)) {
            //si el proceso ya se realizo, siempre sera gris la columna pq no se debe poder modificar
            if (isSelected) {
                cellComponent.setBackground(Color.LIGHT_GRAY);
                cellComponent.setForeground(Color.WHITE);
            } else {
                cellComponent.setBackground(Color.LIGHT_GRAY);
                cellComponent.setForeground(Color.BLACK);
            }
        } else {//si aun no se realiza el proceso y se selecciona la fila, se pinta de azul, si no se selecciona, se pinta de blanco
            if (isSelected) {
                cellComponent.setBackground(colores.azul);
                cellComponent.setForeground(Color.WHITE);
            } else {
                cellComponent.setBackground(Color.WHITE);
                cellComponent.setForeground(Color.BLACK);
            }
        }

        return cellComponent;
    }
}
