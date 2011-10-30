/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaEditarMateriaPrima.java
 *
 * Created on 26/10/2011, 09:17:18
 */
package interfacesGraficas;

import interfacesGraficas.Controladores.ControladorListarMateriasPrimas;
import interfacesGraficas.Controladores.ControladorListarProductosComponentes;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

/**
 *
 * @author eduardo
 */
public class PantallaListarProductosComponentes extends javax.swing.JDialog {

    ControladorListarProductosComponentes controlador;

    /** Creates new form PantallaEditarMateriaPrima*/
    public PantallaListarProductosComponentes(java.awt.Frame parent, boolean modal, ControladorListarProductosComponentes controladorPC) {
        super(parent, modal);
        initComponents();
        controlador = controladorPC;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        botonSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductosComponentesEncontrados = new javax.swing.JTable();
        mostrarProductosComponentesEliminadosCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);

        botonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/Log-out-30.png"))); // NOI18N
        botonSalir.setToolTipText("Salir");
        botonSalir.setFocusable(false);
        botonSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSalir.setMargin(new java.awt.Insets(2, 0, 2, 14));
        botonSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(botonSalir);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos Componentes encontrados"));

        tablaProductosComponentesEncontrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaProductosComponentesEncontrados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosComponentesEncontradosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductosComponentesEncontrados);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        );

        mostrarProductosComponentesEliminadosCheckBox.setText("Mostrar productos componentes eliminados");
        mostrarProductosComponentesEliminadosCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarProductosComponentesEliminadosCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(385, Short.MAX_VALUE)
                .addComponent(mostrarProductosComponentesEliminadosCheckBox)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(mostrarProductosComponentesEliminadosCheckBox)
                .addContainerGap())
        );

        jPanel3.getAccessibleContext().setAccessibleName("Productos Componentes Encontrados");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

private void tablaProductosComponentesEncontradosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosComponentesEncontradosMouseClicked
    if(evt.getClickCount()==2){
        int pcSeleccionado = getTablaProductosComponentesEncontrados().getSelectedRow();
        controlador.editarProductosComponentes(pcSeleccionado);
    }
}//GEN-LAST:event_tablaProductosComponentesEncontradosMouseClicked

private void mostrarProductosComponentesEliminadosCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarProductosComponentesEliminadosCheckBoxActionPerformed
    controlador.recargarTabla();
}//GEN-LAST:event_mostrarProductosComponentesEliminadosCheckBoxActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonSalir;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JCheckBox mostrarProductosComponentesEliminadosCheckBox;
    private javax.swing.JTable tablaProductosComponentesEncontrados;
    // End of variables declaration//GEN-END:variables

  

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public void setBotonSalir(JButton botonSalir) {
        this.botonSalir = botonSalir;
    }


    public ControladorListarProductosComponentes getControlador() {
        return controlador;
    }

    public void setControlador(ControladorListarProductosComponentes controlador) {
        this.controlador = controlador;
    }



    public JPanel getjPanel3() {
        return jPanel3;
    }

    public void setjPanel3(JPanel jPanel3) {
        this.jPanel3 = jPanel3;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JToolBar getjToolBar1() {
        return jToolBar1;
    }

    public void setjToolBar1(JToolBar jToolBar1) {
        this.jToolBar1 = jToolBar1;
    }


    public JTable getTablaProductosComponentesEncontrados() {
        return tablaProductosComponentesEncontrados;
    }

    public void setTablaProductosComponentesEncontrados(JTable tablaProductosComponentesEncontrados) {
        this.tablaProductosComponentesEncontrados = tablaProductosComponentesEncontrados;
    }



    public JCheckBox getMostrarProductosComponentesEliminadosCheckBox() {
        return mostrarProductosComponentesEliminadosCheckBox;
    }

    public void setMostrarProductosComponentesEliminadosCheckBox(JCheckBox mostrarProductosComponentesEliminadosCheckBox) {
        this.mostrarProductosComponentesEliminadosCheckBox = mostrarProductosComponentesEliminadosCheckBox;
    }


}
