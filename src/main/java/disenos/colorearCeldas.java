package disenos;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class colorearCeldas extends DefaultTableCellRenderer {//clase para colorear filas de la tabla de la interfaz menuOrdenesN
    //esta en desuso

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (row >= 1) {
            setBackground(Color.lightGray);//pinta el fondo de la fila de un color gris claro
            setForeground(Color.BLACK);//y el color de la letra negro
        } else {
            setBackground(Color.white);//pinta el fondo de la fila de blanco
            setForeground(Color.BLACK);//y el color de la letra en nergo
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);//retorna las modificaciones hechas a la estetica de la tabla para que se muestre
    }

}
