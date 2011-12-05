/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rustu
 */
public class Render extends DefaultTableCellRenderer { 
    @Override 
    public Component getTableCellRendererComponent (JTable tabla, Object value, boolean selected, boolean focused, int row, int column ) { 
        setEnabled(tabla == null || tabla.isEnabled());
         // see question above 
        if(String.valueOf(tabla.getValueAt(row,4)).equals("-1"))
            setBackground(Color.red);
        else if(String.valueOf(tabla.getValueAt(row,4)).equals("1"))
            setBackground(Color.green);
        else 
            setBackground(null); 

        super.getTableCellRendererComponent(tabla, value, selected, focused, row, column); 
        return this; 
} 
} //***********de esta manera debes llamara a la clase desde tu formulario por ejemplo public class form extends javax.swing.JFrame { public form() { initComponents(); tabla.setDefaultRenderer (Object.class, new render());/// tabla, es la que tienes en tu formulario 
               
    
    

