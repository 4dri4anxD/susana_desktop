package disenos;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class disenoTabla { //establece el diseno para las tablas de la aplicacion

    public void cabecera(JTable tabla) {
        JTableHeader cabecera = tabla.getTableHeader();//obtiene la cabecera de la tabla
        DefaultTableCellRenderer render = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();//obtiene el render
        render.setHorizontalAlignment(JLabel.CENTER);//los textos de la cabecera estaran centrados en su celda
        cabecera.setDefaultRenderer(render);//se le asigna el render a la tabla el cual le dice que centre los textos de la tabla
        cabecera.setReorderingAllowed(false);
        cabecera.setOpaque(false);
        cabecera.setBackground(colores.getGris());//pone el color azul de fondo
        cabecera.setForeground(colores.getBlanco());//pone las letras con color blanco
        cabecera.setFont(new Font("Lato", Font.BOLD, 36));//fuente del texto de la cabecera
        tabla.setRowHeight(48); //establece la altura de la tabla
        tabla.setFont(new Font("Lato", Font.PLAIN, 24));//fuente del cuerpo de la tabla
    }

}
