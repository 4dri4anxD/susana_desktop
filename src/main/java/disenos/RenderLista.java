package disenos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

public class RenderLista extends JLabel implements ListCellRenderer {//esta clase es pal chat, pero no esta en uso pq el chat no esta en servicio

    public RenderLista() {//constructor sin datos

    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        setText(value.toString());
        // cuando este seleccionado se pinta de azul
        if (isSelected) {
            setFont(new Font("Verdana", Font.BOLD, 16));
            setForeground(Color.white);
            setBackground(Color.BLUE);
            setOpaque(true);
        } else {
            setFont(null);
            setForeground(Color.black);
            setOpaque(false);
        }

        return this;
    }
}
