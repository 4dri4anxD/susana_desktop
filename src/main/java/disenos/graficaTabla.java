package disenos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class graficaTabla extends JPanel implements TableCellRenderer {//esta clase era pa ponerle una grafica a algunas tablas como las de menuActividades
    //pero no funciono, esta en desuso

    //declaracion de variables globales
    boolean isBordered = true;
    public String idioma = "";
    private static int i = 0;

    public graficaTabla(boolean isBordered) {//constructor
        this.isBordered = isBordered;
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {

        JPanel button;
        button = new JPanel(new GridBagLayout());
        button.setFont(new Font("Lato", Font.PLAIN, 20));
        button.setForeground(Color.white);
        button.setBackground(colores.azul);
        button.setOpaque(true);
        JLabel f = new JLabel("65%");
        f.setForeground(Color.white);
        f.setHorizontalAlignment(JLabel.CENTER);
        f.setFont(new Font("Lato", Font.PLAIN, 20));
        f.setBackground(colores.negro);
        f.setOpaque(true);
        button.add(f);
        return button;
    }
}
