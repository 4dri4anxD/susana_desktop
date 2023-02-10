package disenos;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class cmbRender extends JLabel implements ListCellRenderer {//clase para estilizar combobox, no funciono, esta en desuso

    public cmbRender() {
        setOpaque(true);
        setFont(new Font("Lato", Font.BOLD, 20));
        setBackground(colores.azul);
        setForeground(colores.blanco);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            this.setText("" + value);
        }
        return this;
    }

}
