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
public class RenderTablaListaMaterialesProveedor implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel etiqueta = new JLabel();
        etiqueta.setOpaque(true);

        if (isSelected) {
            etiqueta.setBackground(new Color(204, 240, 255));//celeste (es el que viene por defecto en look and feel
            etiqueta.setForeground(Color.BLACK);
        } else {
            if (Double.valueOf(table.getValueAt(row, 2).toString()) == 0) {//comportamiento si el campo es igual a pendiente
                etiqueta.setForeground(Color.RED);
                etiqueta.setBackground(Color.WHITE);
            } else {
                etiqueta.setBackground(Color.WHITE);
                etiqueta.setForeground(Color.BLACK);
            }
        }

        if (value instanceof String && value != null) {
            etiqueta.setText((String) value);
        } else {
            etiqueta.setText(String.valueOf(value));
        }

        


        return etiqueta;
    }
}
