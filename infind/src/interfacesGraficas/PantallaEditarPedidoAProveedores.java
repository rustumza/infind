/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaCrearPedidoAProveedores.java
 *
 * Created on 15/11/2011, 20:06:50
 */
package interfacesGraficas;

import interfacesGraficas.Controladores.ControladorEditarPedidoAProveedor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class PantallaEditarPedidoAProveedores extends javax.swing.JDialog {
    
    ControladorEditarPedidoAProveedor controlador;
    
    /** Creates new form PantallaCrearPedidoAProveedores */
    public PantallaEditarPedidoAProveedores(java.awt.Frame parent, boolean modal, ControladorEditarPedidoAProveedor contr) {
        super(parent, modal);
        initComponents();
        controlador = contr;
        setLocationRelativeTo(null);
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
        guardar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tipoProductoListBox = new javax.swing.JComboBox();
        codigoProductoTextBox = new javax.swing.JTextField();
        codigoLabel = new javax.swing.JLabel();
        productoSeleccionadoLabel = new javax.swing.JLabel();
        productoSeleccionadoTextBox = new javax.swing.JTextField();
        loteEstandarLabel = new javax.swing.JLabel();
        loteEstandarTextBox = new javax.swing.JTextField();
        tiempoDeDemoraTextBox = new javax.swing.JTextField();
        tiempoDeDemoraLabel = new javax.swing.JLabel();
        unidadDeMedida = new javax.swing.JLabel();
        cantidadDeLotesLabel = new javax.swing.JLabel();
        cantidadDeLotesTextBox = new javax.swing.JTextField();
        editar = new javax.swing.JButton();
        quitar = new javax.swing.JButton();
        cancelarCantidad = new javax.swing.JButton();
        proveedorLabel = new javax.swing.JLabel();
        proveedorListBox = new javax.swing.JComboBox();
        seConcreto = new javax.swing.JCheckBox();
        soloSinConfirmar = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDePedidos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);

        guardar.setText("Guardar");
        guardar.setFocusable(false);
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(guardar);

        cancelar.setText("Cancelar");
        cancelar.setFocusable(false);
        cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(cancelar);

        salir.setText("Salir");
        salir.setFocusable(false);
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(salir);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Producto")));

        tipoProductoListBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Materia prima", "Producto componente" }));
        tipoProductoListBox.setEnabled(false);

        codigoProductoTextBox.setEnabled(false);

        codigoLabel.setText("Codigo");

        productoSeleccionadoLabel.setText("Producto seleccionado: ");

        productoSeleccionadoTextBox.setEnabled(false);

        loteEstandarLabel.setText("Lote estandar");

        loteEstandarTextBox.setEnabled(false);

        tiempoDeDemoraTextBox.setEnabled(false);

        tiempoDeDemoraLabel.setText("Tiempo de demora");

        cantidadDeLotesLabel.setText("Cantidad de lotes");

        cantidadDeLotesTextBox.setEnabled(false);

        editar.setText("editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        quitar.setText("quitar");
        quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarActionPerformed(evt);
            }
        });

        cancelarCantidad.setText("cancelar");
        cancelarCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarCantidadActionPerformed(evt);
            }
        });

        proveedorLabel.setText("Proveedor");

        proveedorListBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        seConcreto.setText("¿Se concreto?");

        soloSinConfirmar.setText("Solo sin confirmar");
        soloSinConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soloSinConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tipoProductoListBox, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(codigoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigoProductoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(soloSinConfirmar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(productoSeleccionadoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productoSeleccionadoTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(loteEstandarLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loteEstandarTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unidadDeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(tiempoDeDemoraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(tiempoDeDemoraTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cantidadDeLotesLabel)
                        .addGap(4, 4, 4)
                        .addComponent(cantidadDeLotesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(proveedorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(proveedorListBox, 0, 252, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(seConcreto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarCantidad)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoProductoListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoLabel)
                    .addComponent(codigoProductoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soloSinConfirmar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productoSeleccionadoLabel)
                    .addComponent(productoSeleccionadoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loteEstandarLabel)
                    .addComponent(loteEstandarTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tiempoDeDemoraTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tiempoDeDemoraLabel)
                    .addComponent(unidadDeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cantidadDeLotesLabel)
                    .addComponent(proveedorListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadDeLotesTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proveedorLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarCantidad)
                    .addComponent(editar)
                    .addComponent(quitar)
                    .addComponent(seConcreto)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pedidos"));

        tablaDePedidos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDePedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDePedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDePedidos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tablaDePedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDePedidosMouseClicked
if (evt.getClickCount() == 1) {
        controlador.cargarPedidoSeleccionado();
    }
}//GEN-LAST:event_tablaDePedidosMouseClicked

private void cancelarCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarCantidadActionPerformed
    controlador.cancelar();
}//GEN-LAST:event_cancelarCantidadActionPerformed

private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
    controlador.editar();
}//GEN-LAST:event_editarActionPerformed

private void quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarActionPerformed
    controlador.quitar();
}//GEN-LAST:event_quitarActionPerformed

private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
    controlador.guardar();
}//GEN-LAST:event_guardarActionPerformed

private void soloSinConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soloSinConfirmarActionPerformed
    controlador.soloSinConfirmar();
}//GEN-LAST:event_soloSinConfirmarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton cancelarCantidad;
    private javax.swing.JLabel cantidadDeLotesLabel;
    private javax.swing.JTextField cantidadDeLotesTextBox;
    private javax.swing.JLabel codigoLabel;
    private javax.swing.JTextField codigoProductoTextBox;
    private javax.swing.JButton editar;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel loteEstandarLabel;
    private javax.swing.JTextField loteEstandarTextBox;
    private javax.swing.JLabel productoSeleccionadoLabel;
    private javax.swing.JTextField productoSeleccionadoTextBox;
    private javax.swing.JLabel proveedorLabel;
    private javax.swing.JComboBox proveedorListBox;
    private javax.swing.JButton quitar;
    private javax.swing.JButton salir;
    private javax.swing.JCheckBox seConcreto;
    private javax.swing.JCheckBox soloSinConfirmar;
    private javax.swing.JTable tablaDePedidos;
    private javax.swing.JLabel tiempoDeDemoraLabel;
    private javax.swing.JTextField tiempoDeDemoraTextBox;
    private javax.swing.JComboBox tipoProductoListBox;
    private javax.swing.JLabel unidadDeMedida;
    // End of variables declaration//GEN-END:variables



    public JButton getCancelar() {
        return cancelar;
    }

    public void setCancelar(JButton cancelar) {
        this.cancelar = cancelar;
    }

    public JButton getCancelarCantidad() {
        return cancelarCantidad;
    }

    public void setCancelarCantidad(JButton cancelarCantidad) {
        this.cancelarCantidad = cancelarCantidad;
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

    public JTextField getCodigoProductoTextBox() {
        return codigoProductoTextBox;
    }

    public void setCodigoProductoTextBox(JTextField codigoProductoTextBox) {
        this.codigoProductoTextBox = codigoProductoTextBox;
    }

    public ControladorEditarPedidoAProveedor getControlador() {
        return controlador;
    }

    public void setControlador(ControladorEditarPedidoAProveedor controlador) {
        this.controlador = controlador;
    }

    public JButton getEditar() {
        return editar;
    }

    public void setEditar(JButton editar) {
        this.editar = editar;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public void setGuardar(JButton guardar) {
        this.guardar = guardar;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
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

    public JTable getTablaDePedidos() {
        return tablaDePedidos;
    }

    public void setTablaDePedidos(JTable tablaDePedidos) {
        this.tablaDePedidos = tablaDePedidos;
    }

    public JLabel getTiempoDeDemoraLabel() {
        return tiempoDeDemoraLabel;
    }

    public void setTiempoDeDemoraLabel(JLabel tiempoDeDemoraLabel) {
        this.tiempoDeDemoraLabel = tiempoDeDemoraLabel;
    }

    public JTextField getTiempoDeDemoraTextBox() {
        return tiempoDeDemoraTextBox;
    }

    public void setTiempoDeDemoraTextBox(JTextField tiempoDeDemoraTextBox) {
        this.tiempoDeDemoraTextBox = tiempoDeDemoraTextBox;
    }

    public JComboBox getTipoProductoListBox() {
        return tipoProductoListBox;
    }

    public void setTipoProductoListBox(JComboBox tipoProductoListBox) {
        this.tipoProductoListBox = tipoProductoListBox;
    }

    public JLabel getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(JLabel unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public JButton getQuitar() {
        return quitar;
    }

    public void setQuitar(JButton quitar) {
        this.quitar = quitar;
    }

    public JLabel getProveedorLabel() {
        return proveedorLabel;
    }

    public void setProveedorLabel(JLabel proveedorLabel) {
        this.proveedorLabel = proveedorLabel;
    }

    public JComboBox getProveedorListBox() {
        return proveedorListBox;
    }

    public void setProveedorListBox(JComboBox proveedorListBox) {
        this.proveedorListBox = proveedorListBox;
    }

    public JCheckBox getSeConcreto() {
        return seConcreto;
    }

    public void setSeConcreto(JCheckBox seConcreto) {
        this.seConcreto = seConcreto;
    }

    public JCheckBox getSoloSinConfirmar() {
        return soloSinConfirmar;
    }

    public void setSoloSinConfirmar(JCheckBox soloSinConfirmar) {
        this.soloSinConfirmar = soloSinConfirmar;
    }
    
    
}
