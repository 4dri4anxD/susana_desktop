
package disenos;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class render extends DefaultTableCellRenderer {//es para decorar el chat, pero no funciono, esta en desuso
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
 
        String texto = table.getValueAt(row,3).toString();
        if (texto.equals("skywalker")) {//esto nomas era pa probar
            setBackground(Color.lightGray);//pinta el fondo de la fila de un color gris claro
            setForeground(Color.BLACK);//y el color de la letra negro
        } else{
            setBackground(Color.white);//pinta el fondo de la fila de blanco
            setForeground(Color.BLACK);//y el color de la letra en nergo
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);//retorna las modificaciones hechas a la estetica de la tabla para que se muestre
    }
 
}
