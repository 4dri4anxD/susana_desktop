package disenos;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class colorTabla extends DefaultTableCellRenderer {//clase para pintar de colores la tabla de la interfaz vistaActividades1

    //declaracion de variables globales
    private int col = -1;//no es requerido
    private ArrayList<String> nombres;//filas con procesos ya terminados
    private Component cellComponent;

    public colorTabla(int col, ArrayList<String> nombres) {
        this.nombres = nombres;
        this.col = col;
    }

    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
        cellComponent = super.getTableCellRendererComponent(table, color, isSelected, hasFocus, row, column);

        if (nombres.contains("" + row)) {//si el proceso de la fila ya se realizo
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
        return cellComponent;
    }
}
