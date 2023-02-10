package disenos;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class cmbEditor extends BasicComboBoxEditor {//esta clase era para estilizar combo boxes, pero no funciono, esta en desuso

    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private Object selectedItem;

    public cmbEditor() {

        label.setOpaque(false);
        label.setFont(new Font("Lato", Font.BOLD, 20));
        label.setForeground(colores.blanco);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panel.add(label);
        panel.setBackground(colores.azul);
    }

    public Component getEditorComponent() {
        return this.panel;
    }

    public Object getItem() {
        return "[" + this.selectedItem.toString() + "]";
    }

    public void setItem(Object item) {
        this.selectedItem = item;
        label.setText(item.toString());
    }

}
