/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaCrearOrdenDeFabricacion.java
 *
 * Created on 25/11/2011, 17:20:11
 */
package interfacesGraficas;

import com.toedter.calendar.JDateChooser;
import interfacesGraficas.Controladores.ControladorCrearOrdenDeFabricacion;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author rustu
 */
public class PantallaCrearOrdenDeFabricacion extends javax.swing.JDialog {

    ControladorCrearOrdenDeFabricacion controlador;
    
    /** Creates new form PantallaCrearOrdenDeFabricacion */
    public PantallaCrearOrdenDeFabricacion(java.awt.Frame parent, boolean modal, ControladorCrearOrdenDeFabricacion aThis) {
        super(parent, modal);
        initComponents();
        controlador = aThis;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        buscar = new javax.swing.JButton();
        unidadDeMedida = new javax.swing.JLabel();
        loteEstandarTextBox = new javax.swing.JTextField();
        loteEstandarLabel = new javax.swing.JLabel();
        productoSeleccionadoTextBox = new javax.swing.JTextField();
        codigoLabel = new javax.swing.JLabel();
        tipoProducto = new javax.swing.JComboBox();
        productoSeleccionadoLabel = new javax.swing.JLabel();
        codigoTextBox = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cantidadDeLotesLabel = new javax.swing.JLabel();
        cantidadDeLotesTextBox = new javax.swing.JTextField();
        fechaDeInicioDateChooser = new com.toedter.calendar.JDateChooser();
        fechaDeInicioLabel = new javax.swing.JLabel();
        probar = new javax.swing.JButton();
        generar = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        salir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaOrdenesDeCompraYProduccion = new javax.swing.JTable();
        fechaPosibleRealizacionLabel = new javax.swing.JLabel();
        fechaPosibleRealizacionTextBox = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Producto"));

        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        loteEstandarTextBox.setEnabled(false);

        loteEstandarLabel.setText("Lote estandar");

        productoSeleccionadoTextBox.setEnabled(false);

        codigoLabel.setText("codigo");

        tipoProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Producto final", "Producto intermedio" }));

        productoSeleccionadoLabel.setText("Producto seleccionado: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(tipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(productoSeleccionadoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productoSeleccionadoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(loteEstandarLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loteEstandarTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unidadDeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoLabel)
                    .addComponent(codigoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productoSeleccionadoLabel)
                    .addComponent(productoSeleccionadoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loteEstandarLabel)
                    .addComponent(loteEstandarTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unidadDeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Orden de produccion"));

        cantidadDeLotesLabel.setText("Cantidad de lotes");

        fechaDeInicioLabel.setText("Fecha de inicio deseada");

        probar.setText("Probar");
        probar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probarActionPerformed(evt);
            }
        });

        generar.setText("Generar");
        generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(probar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cantidadDeLotesLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cantidadDeLotesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(fechaDeInicioLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaDeInicioDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaDeInicioDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cantidadDeLotesLabel)
                        .addComponent(cantidadDeLotesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fechaDeInicioLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(probar)
                    .addComponent(generar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.setRollover(true);

        salir.setText("Salir");
        salir.setFocusable(false);
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jToolBar1.add(salir);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Información generada"));

        tablaOrdenesDeCompraYProduccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Fecha estimada de ralizacion de pedido"
            }
        ));
        jScrollPane2.setViewportView(tablaOrdenesDeCompraYProduccion);

        fechaPosibleRealizacionLabel.setText("Fecha de realizacion posible");

        fechaPosibleRealizacionTextBox.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fechaPosibleRealizacionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaPosibleRealizacionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaPosibleRealizacionLabel)
                    .addComponent(fechaPosibleRealizacionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
    controlador.buscarProducto();
}//GEN-LAST:event_buscarActionPerformed

private void probarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probarActionPerformed
    controlador.probarOrden();
}//GEN-LAST:event_probarActionPerformed

private void generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarActionPerformed
    controlador.generarOrden();
}//GEN-LAST:event_generarActionPerformed

private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
    dispose();
}//GEN-LAST:event_salirActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JLabel cantidadDeLotesLabel;
    private javax.swing.JTextField cantidadDeLotesTextBox;
    private javax.swing.JLabel codigoLabel;
    private javax.swing.JTextField codigoTextBox;
    private com.toedter.calendar.JDateChooser fechaDeInicioDateChooser;
    private javax.swing.JLabel fechaDeInicioLabel;
    private javax.swing.JLabel fechaPosibleRealizacionLabel;
    private javax.swing.JTextField fechaPosibleRealizacionTextBox;
    private javax.swing.JButton generar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel loteEstandarLabel;
    private javax.swing.JTextField loteEstandarTextBox;
    private javax.swing.JButton probar;
    private javax.swing.JLabel productoSeleccionadoLabel;
    private javax.swing.JTextField productoSeleccionadoTextBox;
    private javax.swing.JButton salir;
    private javax.swing.JTable tablaOrdenesDeCompraYProduccion;
    private javax.swing.JComboBox tipoProducto;
    private javax.swing.JLabel unidadDeMedida;
    // End of variables declaration//GEN-END:variables

    public JButton getBuscar() {
        return buscar;
    }

    public void setBuscar(JButton buscar) {
        this.buscar = buscar;
    }

    public JLabel getCantidadDeLotesLabel() {
        return cantidadDeLotesLabel;
    }

    public void setCantidadDeLotesLabel(JLabel cantidadDeLotesLabel) {
        this.cantidadDeLotesLabel = cantidadDeLotesLabel;
    }

    public JTextField getCantidadDeLotesTextBox() {
        return cantidadDeLotesTextBox;
    }

    public void setCantidadDeLotesTextBox(JTextField cantidadDeLotesTextBox) {
        this.cantidadDeLotesTextBox = cantidadDeLotesTextBox;
    }

    public JLabel getCodigoLabel() {
        return codigoLabel;
    }

    public void setCodigoLabel(JLabel codigoLabel) {
        this.codigoLabel = codigoLabel;
    }

    public JTextField getCodigoTextBox() {
        return codigoTextBox;
    }

    public void setCodigoTextBox(JTextField codigoTextBox) {
        this.codigoTextBox = codigoTextBox;
    }

    public ControladorCrearOrdenDeFabricacion getControlador() {
        return controlador;
    }

    public void setControlador(ControladorCrearOrdenDeFabricacion controlador) {
        this.controlador = controlador;
    }

    public JDateChooser getFechaDeInicioDateChooser() {
        return fechaDeInicioDateChooser;
    }

    public void setFechaDeInicioDateChooser(JDateChooser fechaDeInicioDateChooser) {
        this.fechaDeInicioDateChooser = fechaDeInicioDateChooser;
    }

    public JLabel getFechaDeInicioLabel() {
        return fechaDeInicioLabel;
    }

    public void setFechaDeInicioLabel(JLabel fechaDeInicioLabel) {
        this.fechaDeInicioLabel = fechaDeInicioLabel;
    }

    public JLabel getFechaPosibleRealizacionLabel() {
        return fechaPosibleRealizacionLabel;
    }

    public void setFechaPosibleRealizacionLabel(JLabel fechaPosibleRealizacionLabel) {
        this.fechaPosibleRealizacionLabel = fechaPosibleRealizacionLabel;
    }

    public JTextField getFechaPosibleRealizacionTextBox() {
        return fechaPosibleRealizacionTextBox;
    }

    public void setFechaPosibleRealizacionTextBox(JTextField fechaPosibleRealizacionTextBox) {
        this.fechaPosibleRealizacionTextBox = fechaPosibleRealizacionTextBox;
    }

    public JButton getGenerar() {
        return generar;
    }

    public void setGenerar(JButton generar) {
        this.generar = generar;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public void setjPanel2(JPanel jPanel2) {
        this.jPanel2 = jPanel2;
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

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public void setjTable1(JTable jTable1) {
        this.jTable1 = jTable1;
    }

    public JToolBar getjToolBar1() {
        return jToolBar1;
    }

    public void setjToolBar1(JToolBar jToolBar1) {
        this.jToolBar1 = jToolBar1;
    }

    public JLabel getLoteEstandarLabel() {
        return loteEstandarLabel;
    }

    public void setLoteEstandarLabel(JLabel loteEstandarLabel) {
        this.loteEstandarLabel = loteEstandarLabel;
    }

    public JTextField getLoteEstandarTextBox() {
        return loteEstandarTextBox;
    }

    public void setLoteEstandarTextBox(JTextField loteEstandarTextBox) {
        this.loteEstandarTextBox = loteEstandarTextBox;
    }

    public JButton getProbar() {
        return probar;
    }

    public void setProbar(JButton probar) {
        this.probar = probar;
    }

    public JLabel getProductoSeleccionadoLabel() {
        return productoSeleccionadoLabel;
    }

    public void setProductoSeleccionadoLabel(JLabel productoSeleccionadoLabel) {
        this.productoSeleccionadoLabel = productoSeleccionadoLabel;
    }

    public JTextField getProductoSeleccionadoTextBox() {
        return productoSeleccionadoTextBox;
    }

    public void setProductoSeleccionadoTextBox(JTextField productoSeleccionadoTextBox) {
        this.productoSeleccionadoTextBox = productoSeleccionadoTextBox;
    }

    public JButton getSalir() {
        return salir;
    }

    public void setSalir(JButton salir) {
        this.salir = salir;
    }

    public JTable getTablaOrdenesDeCompraYProduccion() {
        return tablaOrdenesDeCompraYProduccion;
    }

    public void setTablaOrdenesDeCompraYProduccion(JTable tablaOrdenesDeCompraYProduccion) {
        this.tablaOrdenesDeCompraYProduccion = tablaOrdenesDeCompraYProduccion;
    }

    public JComboBox getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(JComboBox tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public JLabel getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(JLabel unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }
}
