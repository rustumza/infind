/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaEditarEstadoALaOrdenDeProduccion.java
 *
 * Created on 03/12/2011, 05:03:53
 */
package interfacesGraficas;

import interfacesGraficas.Controladores.ControladorListarOrdenesDeFabricacion;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author rustu
 */
public class PantallaEditarEstadoALaOrdenDeProduccion extends javax.swing.JDialog {

    ControladorListarOrdenesDeFabricacion controlador;
    
    /** Creates new form PantallaEditarEstadoALaOrdenDeProduccion */
    public PantallaEditarEstadoALaOrdenDeProduccion(java.awt.Frame parent, boolean modal, ControladorListarOrdenesDeFabricacion control) {
        super(parent, modal);
        initComponents();
        controlador = control;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cambiarAEstadoLabel = new javax.swing.JLabel();
        cambiarAEstadoValorReal = new javax.swing.JLabel();
        si = new javax.swing.JButton();
        no = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaOrdenesDeProduccion = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaOrdenesDeCompraYProduccion = new javax.swing.JTable();
        productoAFabricarLabel = new javax.swing.JLabel();
        productoAFabricarValorReal = new javax.swing.JLabel();
        eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cambiarAEstadoLabel.setText("Cambiar estado de la orden a: ");

        si.setText("Sí");

        no.setText("No");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ordenes de produccion"));

        tablaOrdenesDeProduccion.setBorder(null);
        tablaOrdenesDeProduccion.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tablaOrdenesDeProduccion);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ordenes de compra"));

        tablaOrdenesDeCompraYProduccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(tablaOrdenesDeCompraYProduccion);

        productoAFabricarLabel.setText("Producto a fabricar: ");

        eliminar.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cambiarAEstadoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cambiarAEstadoValorReal, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(productoAFabricarLabel)
                        .addGap(6, 6, 6)
                        .addComponent(productoAFabricarValorReal, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(si, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cambiarAEstadoLabel)
                            .addComponent(cambiarAEstadoValorReal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(si)
                            .addComponent(no)
                            .addComponent(eliminar)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(productoAFabricarLabel)
                        .addComponent(productoAFabricarValorReal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cambiarAEstadoLabel;
    private javax.swing.JLabel cambiarAEstadoValorReal;
    private javax.swing.JButton eliminar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton no;
    private javax.swing.JLabel productoAFabricarLabel;
    private javax.swing.JLabel productoAFabricarValorReal;
    private javax.swing.JButton si;
    private javax.swing.JTable tablaOrdenesDeCompraYProduccion;
    private javax.swing.JTable tablaOrdenesDeProduccion;
    // End of variables declaration//GEN-END:variables

    public JLabel getCambiarAEstadoLabel() {
        return cambiarAEstadoLabel;
    }

    public void setCambiarAEstadoLabel(JLabel cambiarAEstadoLabel) {
        this.cambiarAEstadoLabel = cambiarAEstadoLabel;
    }

    public JLabel getCambiarAEstadoValorReal() {
        return cambiarAEstadoValorReal;
    }

    public void setCambiarAEstadoValorReal(JLabel cambiarAEstadoValorReal) {
        this.cambiarAEstadoValorReal = cambiarAEstadoValorReal;
    }

    public ControladorListarOrdenesDeFabricacion getControlador() {
        return controlador;
    }

    public void setControlador(ControladorListarOrdenesDeFabricacion controlador) {
        this.controlador = controlador;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public JScrollPane getjScrollPane3() {
        return jScrollPane3;
    }

    public void setjScrollPane3(JScrollPane jScrollPane3) {
        this.jScrollPane3 = jScrollPane3;
    }

    public JButton getNo() {
        return no;
    }

    public void setNo(JButton no) {
        this.no = no;
    }

    public JLabel getProductoAFabricarLabel() {
        return productoAFabricarLabel;
    }

    public void setProductoAFabricarLabel(JLabel productoAFabricarLabel) {
        this.productoAFabricarLabel = productoAFabricarLabel;
    }

    public JLabel getProductoAFabricarValorReal() {
        return productoAFabricarValorReal;
    }

    public void setProductoAFabricarValorReal(JLabel productoAFabricarValorReal) {
        this.productoAFabricarValorReal = productoAFabricarValorReal;
    }

    public JButton getSi() {
        return si;
    }

    public void setSi(JButton si) {
        this.si = si;
    }

    public JTable getTablaOrdenesDeCompraYProduccion() {
        return tablaOrdenesDeCompraYProduccion;
    }

    public void setTablaOrdenesDeCompraYProduccion(JTable tablaOrdenesDeCompraYProduccion) {
        this.tablaOrdenesDeCompraYProduccion = tablaOrdenesDeCompraYProduccion;
    }

    public JTable getTablaOrdenesDeProduccion() {
        return tablaOrdenesDeProduccion;
    }

    public void setTablaOrdenesDeProduccion(JTable tablaOrdenesDeProduccion) {
        this.tablaOrdenesDeProduccion = tablaOrdenesDeProduccion;
    }

    
    
}
