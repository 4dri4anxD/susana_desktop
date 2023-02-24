package disenos;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class disenos {//clase que sirve para establecer el diseno de algunos componentes de todas las interfaces

    //se pasa como parametro el componente a la funcion cuando se requiere
    public void texto(JTextField texto) {//pone una fuente y tamanio especificos a campos de texto
        texto.setFont(new Font("Lato", Font.PLAIN, 14));
        texto.setForeground(colores.getNegro());
        texto.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        texto.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                texto.setBorder(BorderFactory.createLineBorder(colores.azul, 2));
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }

    //spinner, creo que era para el chat, entonces estara en desuso
    public void spiner(JSpinner texto) {
        texto.setFont(new Font("Lato", Font.PLAIN, 20));
        texto.setForeground(colores.getNegro());
        texto.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        texto.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                texto.setBorder(BorderFactory.createLineBorder(colores.azul, 2));
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }

    public void fechas(JDateChooser label) {//pone una fuente y tamanio especificos a un campo de seleccion de fecha
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) label.getComponent(1);
        dateEditor.setHorizontalAlignment(JTextField.CENTER);
        label.setFont(new Font("Lato", Font.PLAIN, 20));
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        label.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                label.setBorder(BorderFactory.createLineBorder(colores.azul, 2));
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }

    public void texto1(JTextArea texto) {//pone una fuente y tamanio especificos a campos de textArea
        texto.setFont(new Font("Lato", Font.PLAIN, 22));
        texto.setForeground(Color.lightGray);
        texto.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        texto.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                texto.setBorder(BorderFactory.createLineBorder(colores.azul, 2));
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }

    public void textoL(JTextField texto) {//pone una fuente y tamanio especificos a campos de texto
        texto.setFont(new Font("Lato", Font.PLAIN, 20));
        texto.setForeground(colores.getNegro());
        texto.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    public void textoL1(JTextField texto) {//pone una fuente y tamanio especificos a campos de texto
        texto.setFont(new Font("Lato", Font.PLAIN, 20));
        texto.setForeground(Color.LIGHT_GRAY);
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        texto.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                texto.setBorder(BorderFactory.createLineBorder(colores.azul, 2));
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }

    public void textoL2(JTextField texto) {//pone una fuente y tamanio especificos a campos de texto
        texto.setFont(new Font("Lato", Font.PLAIN, 20));
        texto.setForeground(Color.black);
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        texto.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                texto.setBorder(BorderFactory.createLineBorder(colores.azul, 2));
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }

    public void textoL1(JLabel label) {//pone una fuente y tamanio especificos a un label
        label.setFont(new Font("Lato", Font.PLAIN, 24));
        label.setForeground(colores.getBlanco());
    }

    public void titulo(JLabel label, int tipo) {//pone una fuente y tamanio especificos a un label
        if (tipo == 1) {
            label.setFont(new Font("Lato", Font.BOLD, 48));
            label.setForeground(colores.getBlanco());
        } else if (tipo == 2) {
            label.setFont(new Font("Lato", Font.BOLD, 36));
            label.setForeground(colores.getBlanco());
        } else if (tipo == 3) {
            label.setFont(new Font("Lato", Font.BOLD, 24));
            label.setForeground(colores.getBlanco());
        } else if (tipo == 4) {
            label.setFont(new Font("Lato", Font.BOLD, 80));
            label.setForeground(colores.getBlanco());
        } else if (tipo == 5) {
            label.setFont(new Font("Lato", Font.BOLD, 20));
            label.setForeground(colores.getBlanco());
        } else if (tipo == 6) {
            label.setFont(new Font("Lato", Font.BOLD, 20));
            label.setForeground(colores.getNegro());
        }else{
            label.setFont(new Font("Lato", Font.BOLD, 14));
            label.setForeground(colores.getNegro());
        }

    }

    public void fondo(JPanel panel, int tipo) {//pone un color de fondo a un panel
        if (tipo == 1) {
            panel.setBackground(colores.getGris());
        } else if (tipo == 2) {
            panel.setBackground(colores.getBlanco());
        } else if(tipo==3){
            panel.setBackground(colores.getAzul());
        }else{
            panel.setBackground(colores.getGrisBlanco());
        }
    }

    public void fondoLabel(JLabel panel, int tipo) {//pone un color de fondo a un panel
        if (tipo == 1) {
            panel.setBackground(colores.getGris());
            // panel.setForeground(colores.getBlanco());
        } else if (tipo == 2) {
            panel.setBackground(colores.getBlanco());
        } else if (tipo == 3) {
            panel.setBackground(colores.getGrisBlanco());
        }
        panel.setOpaque(true);
    }

    public void botones(JButton boton, int tipo) {//pone color de fondo y fuente a botones
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        if (tipo == 1) {
            boton.setFont(new Font("Lato", Font.PLAIN, 18));
            boton.setBackground(colores.getAzul());
        } else if (tipo == 2) {
            boton.setFont(new Font("Lato", Font.BOLD, 20));
            boton.setBackground(colores.getGris());
        } else if (tipo == 3) {
            boton.setFont(new Font("Lato", Font.BOLD, 16));
            boton.setBackground(colores.getGris());
        } else if (tipo == 4) {
            boton.setFont(new Font("Lato", Font.BOLD, 16));
            boton.setBackground(colores.getGris2());
        } else {
            boton.setFont(new Font("Lato", Font.BOLD, 16));
            boton.setBackground(colores.blanco);
        }
        boton.setForeground(colores.getBlanco());

        boton.setOpaque(true);
    }

    public void selector(JComboBox combo) {//pone un formato especifico a los combobox
        combo.setForeground(Color.black);
        combo.setBackground(colores.getAzul());
        combo.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        combo.setOpaque(true);
        combo.setFont(new Font("Lato", Font.PLAIN, 20));
    }
    
    public void progressBar(JProgressBar pb){
      //  pb.setValue(50);
        //pbCalidad.setBorderPainted(true);
        pb.setForeground(colores.getAzul());
        pb.setStringPainted(true);
        pb.setFont(new Font("Lato", Font.BOLD, 20));
        
    }

}
