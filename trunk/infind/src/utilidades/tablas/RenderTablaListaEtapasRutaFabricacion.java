/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.tablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author diego
 */
public class RenderTablaListaEtapasRutaFabricacion implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel etiqueta = new JLabel();
        etiqueta.setOpaque(true);

        if (table.getGridColor() == Color.CYAN) {

            etiqueta.setBackground(new Color(255, 51, 51));//celeste (es el que viene por defecto en look and feel
            etiqueta.setForeground(Color.BLACK);
        } else {
            etiqueta.setBackground(Color.YELLOW);//celeste (es el que viene por defecto en look and feel
            etiqueta.setForeground(Color.BLACK);
        }

        if (value instanceof String && value != null) {
            etiqueta.setText((String) value);
        } else {
            etiqueta.setText(String.valueOf(value));
        }




        return etiqueta;
    }
}

/*class render extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        setEnabled(table == null || table.isEnabled());
    }
} // see question above 
{if(String.valueOf(table.getValueAt(row,3)).equals("0"))//aca indico que me pinte de color verde todas 
//las filas en la cual la ultima columna tiene valor 0. 
setBackground(Color.green); 
else setBackground(null); } 
super.getTableCellRendererComponent(table, value, selected, focused, row, column); 
return this; 

} } ***********de esta manera debes llamara a la clase desde tu formulario por ejemplo 
public class form extends javax.swing.JFrame {

    public form() {
        initComponents();
        tabla.setDefaultRenderer(Object.class, new render());// tabla, es la que tienes en tu formulario } .*/