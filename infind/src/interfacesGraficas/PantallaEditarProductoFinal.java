/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJDialog.java
 *
 * Created on 19/10/2011, 20:00:38
 */
package interfacesGraficas;

import interfacesGraficas.*;
import com.toedter.calendar.JDateChooser;
import interfacesGraficas.Controladores.ControladorProductoFinal;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author rustu
 */
public class PantallaEditarProductoFinal extends javax.swing.JDialog {
    
    ControladorProductoFinal controlador;
    
    /** Creates new form NewJDialog */
    public PantallaEditarProductoFinal(java.awt.Frame parent, boolean modal, ControladorProductoFinal controladorPFinal) {
        super(parent, modal);
        initComponents();
        controlador = controladorPFinal;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        toolbar = new javax.swing.JToolBar();
        guardar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionTextArea = new javax.swing.JTextArea();
        codigoTextBox = new javax.swing.JTextField();
        nombreTextBox = new javax.swing.JTextField();
        ubicacionEnElAlmacenLabel = new javax.swing.JLabel();
        unidadDeMedidaLabel = new javax.swing.JLabel();
        tamanioLoteEstandarLabel = new javax.swing.JLabel();
        observacionLabel = new javax.swing.JLabel();
        precioBaseLabel = new javax.swing.JLabel();
        categoriaLabel = new javax.swing.JLabel();
        nombreLabel = new javax.swing.JLabel();
        descripcionLabel = new javax.swing.JLabel();
        costoUnitarioPorOmisionLabel = new javax.swing.JLabel();
        costoEstandarLabel = new javax.swing.JLabel();
        codigoLabel = new javax.swing.JLabel();
        estadoLabel = new javax.swing.JLabel();
        categoriaListBox = new javax.swing.JComboBox();
        costoEstandarTextBox = new javax.swing.JTextField();
        costoUnitarioPorOmisionTextBox = new javax.swing.JTextField();
        precioBaseTextBox = new javax.swing.JTextField();
        tamanioLoteEstandarTextBox = new javax.swing.JTextField();
        estadoListBox = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        observacionTextArea = new javax.swing.JTextArea();
        ubicacionAlamcenTextBox = new javax.swing.JTextField();
        unidadDeMedidaListBox = new javax.swing.JComboBox();
        estadoEntrarEnActividadEnFechaLabel = new javax.swing.JLabel();
        estadoEntrarEnActividadEnFechajDateChooser = new com.toedter.calendar.JDateChooser();
        productoIQERelacionadoLabel = new javax.swing.JLabel();
        productoIQERelacionadoListBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        toolbar.setRollover(true);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/Save_30.png"))); // NOI18N
        guardar.setToolTipText("Guardar Producto Final");
        guardar.setFocusable(false);
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.setMargin(new java.awt.Insets(2, 0, 2, 14));
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        toolbar.add(guardar);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/user-trash-30.png"))); // NOI18N
        eliminar.setToolTipText("Eliminar");
        eliminar.setFocusable(false);
        eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        toolbar.add(eliminar);

        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/Cancel2-20.png"))); // NOI18N
        cancelar.setToolTipText("Cancelar");
        cancelar.setFocusable(false);
        cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelar.setMargin(new java.awt.Insets(2, 0, 2, 14));
        cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(cancelar);

        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/Log-out-30.png"))); // NOI18N
        salir.setToolTipText("Salir");
        salir.setFocusable(false);
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setMargin(new java.awt.Insets(2, 0, 2, 14));
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(salir);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));

        descripcionTextArea.setColumns(20);
        descripcionTextArea.setRows(5);
        jScrollPane1.setViewportView(descripcionTextArea);

        codigoTextBox.setEnabled(false);

        ubicacionEnElAlmacenLabel.setText("Ubicación en el almacén");

        unidadDeMedidaLabel.setText("Unidad de medida");

        tamanioLoteEstandarLabel.setText("Tamaño lote estandar");

        observacionLabel.setText("Observación");

        precioBaseLabel.setText("Precio base");

        categoriaLabel.setText("Categoria");

        nombreLabel.setText("Nombre");

        descripcionLabel.setText("Descripción");

        costoUnitarioPorOmisionLabel.setText("Costo unitario por omisión");

        costoEstandarLabel.setText("Costo Estandar");

        codigoLabel.setText("Código");

        estadoLabel.setText("Estado");

        categoriaListBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C" }));

        estadoListBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
        estadoListBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoListBoxActionPerformed(evt);
            }
        });

        observacionTextArea.setColumns(20);
        observacionTextArea.setRows(5);
        jScrollPane3.setViewportView(observacionTextArea);

        unidadDeMedidaListBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Litros", "Kilios", "Gramos", "Cm3", "Unidades" }));

        estadoEntrarEnActividadEnFechaLabel.setText("Entrar en actividad en fecha");

        estadoEntrarEnActividadEnFechajDateChooser.setEnabled(false);

        productoIQERelacionadoLabel.setText("Producto IQE relacionado");

        productoIQERelacionadoListBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estadoLabel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreLabel)
                            .addComponent(descripcionLabel))
                        .addGap(116, 116, 116)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(codigoLabel)
                        .addGap(146, 146, 146)
                        .addComponent(codigoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unidadDeMedidaLabel)
                            .addComponent(categoriaLabel)
                            .addComponent(costoEstandarLabel)
                            .addComponent(costoUnitarioPorOmisionLabel)
                            .addComponent(precioBaseLabel)
                            .addComponent(tamanioLoteEstandarLabel)
                            .addComponent(ubicacionEnElAlmacenLabel)
                            .addComponent(observacionLabel)
                            .addComponent(productoIQERelacionadoLabel))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(precioBaseTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costoEstandarTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(estadoListBox, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(estadoEntrarEnActividadEnFechaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(estadoEntrarEnActividadEnFechajDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(categoriaListBox, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unidadDeMedidaListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costoUnitarioPorOmisionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tamanioLoteEstandarTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ubicacionAlamcenTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(productoIQERelacionadoListBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoLabel)
                    .addComponent(codigoTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nombreTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcionLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unidadDeMedidaLabel)
                    .addComponent(unidadDeMedidaListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriaLabel)
                    .addComponent(categoriaListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costoEstandarLabel)
                    .addComponent(costoEstandarTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costoUnitarioPorOmisionLabel)
                    .addComponent(costoUnitarioPorOmisionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precioBaseLabel)
                    .addComponent(precioBaseTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estadoLabel)
                            .addComponent(estadoListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estadoEntrarEnActividadEnFechaLabel))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tamanioLoteEstandarLabel)
                            .addComponent(tamanioLoteEstandarTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ubicacionEnElAlmacenLabel)
                            .addComponent(ubicacionAlamcenTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(estadoEntrarEnActividadEnFechajDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(observacionLabel)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productoIQERelacionadoLabel)
                    .addComponent(productoIQERelacionadoListBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addComponent(toolbar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void estadoListBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoListBoxActionPerformed
    if(((String)estadoListBox.getModel().getSelectedItem()).equals("Inactivo")){
        estadoEntrarEnActividadEnFechajDateChooser.setEnabled(true);
    }else{
        estadoEntrarEnActividadEnFechajDateChooser.setEnabled(false);
    }
}//GEN-LAST:event_estadoListBoxActionPerformed

private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
    controlador.editar();
}//GEN-LAST:event_guardarActionPerformed

private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
    controlador.eliminar();
}//GEN-LAST:event_eliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel categoriaLabel;
    private javax.swing.JComboBox categoriaListBox;
    private javax.swing.JLabel codigoLabel;
    private javax.swing.JTextField codigoTextBox;
    private javax.swing.JLabel costoEstandarLabel;
    private javax.swing.JTextField costoEstandarTextBox;
    private javax.swing.JLabel costoUnitarioPorOmisionLabel;
    private javax.swing.JTextField costoUnitarioPorOmisionTextBox;
    private javax.swing.JLabel descripcionLabel;
    private javax.swing.JTextArea descripcionTextArea;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel estadoEntrarEnActividadEnFechaLabel;
    private com.toedter.calendar.JDateChooser estadoEntrarEnActividadEnFechajDateChooser;
    private javax.swing.JLabel estadoLabel;
    private javax.swing.JComboBox estadoListBox;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JTextField nombreTextBox;
    private javax.swing.JLabel observacionLabel;
    private javax.swing.JTextArea observacionTextArea;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JLabel precioBaseLabel;
    private javax.swing.JTextField precioBaseTextBox;
    private javax.swing.JLabel productoIQERelacionadoLabel;
    private javax.swing.JComboBox productoIQERelacionadoListBox;
    private javax.swing.JButton salir;
    private javax.swing.JLabel tamanioLoteEstandarLabel;
    private javax.swing.JTextField tamanioLoteEstandarTextBox;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JTextField ubicacionAlamcenTextBox;
    private javax.swing.JLabel ubicacionEnElAlmacenLabel;
    private javax.swing.JLabel unidadDeMedidaLabel;
    private javax.swing.JComboBox unidadDeMedidaListBox;
    // End of variables declaration//GEN-END:variables

    public JButton getCancelar() {
        return cancelar;
    }

    public void setCancelar(JButton cancelar) {
        this.cancelar = cancelar;
    }

    public JLabel getCategoriaLabel() {
        return categoriaLabel;
    }

    public void setCategoriaLabel(JLabel categoriaLabel) {
        this.categoriaLabel = categoriaLabel;
    }

    public JComboBox getCategoriaListBox() {
        return categoriaListBox;
    }

    public void setCategoriaListBox(JComboBox categoriaListBox) {
        this.categoriaListBox = categoriaListBox;
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

    public ControladorProductoFinal getControlador() {
        return controlador;
    }

    public void setControlador(ControladorProductoFinal controlador) {
        this.controlador = controlador;
    }

    public JLabel getCostoEstandarLabel() {
        return costoEstandarLabel;
    }

    public void setCostoEstandarLabel(JLabel costoEstandarLabel) {
        this.costoEstandarLabel = costoEstandarLabel;
    }

    public JTextField getCostoEstandarTextBox() {
        return costoEstandarTextBox;
    }

    public void setCostoEstandarTextBox(JTextField costoEstandarTextBox) {
        this.costoEstandarTextBox = costoEstandarTextBox;
    }

    public JLabel getCostoUnitarioPorOmisionLabel() {
        return costoUnitarioPorOmisionLabel;
    }

    public void setCostoUnitarioPorOmisionLabel(JLabel costoUnitarioPorOmisionLabel) {
        this.costoUnitarioPorOmisionLabel = costoUnitarioPorOmisionLabel;
    }

    public JTextField getCostoUnitarioPorOmisionTextBox() {
        return costoUnitarioPorOmisionTextBox;
    }

    public void setCostoUnitarioPorOmisionTextBox(JTextField costoUnitarioPorOmisionTextBox) {
        this.costoUnitarioPorOmisionTextBox = costoUnitarioPorOmisionTextBox;
    }

    public JLabel getDescripcionLabel() {
        return descripcionLabel;
    }

    public void setDescripcionLabel(JLabel descripcionLabel) {
        this.descripcionLabel = descripcionLabel;
    }

    public JTextArea getDescripcionTextArea() {
        return descripcionTextArea;
    }

    public void setDescripcionTextArea(JTextArea descripcionTextArea) {
        this.descripcionTextArea = descripcionTextArea;
    }

    public JLabel getEstadoLabel() {
        return estadoLabel;
    }

    public void setEstadoLabel(JLabel estadoLabel) {
        this.estadoLabel = estadoLabel;
    }

    public JComboBox getEstadoListBox() {
        return estadoListBox;
    }

    public void setEstadoListBox(JComboBox estadoListBox) {
        this.estadoListBox = estadoListBox;
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

    public JScrollPane getjScrollPane3() {
        return jScrollPane3;
    }

    public void setjScrollPane3(JScrollPane jScrollPane3) {
        this.jScrollPane3 = jScrollPane3;
    }

    public JLabel getNombreLabel() {
        return nombreLabel;
    }

    public void setNombreLabel(JLabel nombreLabel) {
        this.nombreLabel = nombreLabel;
    }

    public JTextField getNombreTextBox() {
        return nombreTextBox;
    }

    public void setNombreTextBox(JTextField nombreTextBox) {
        this.nombreTextBox = nombreTextBox;
    }

    public JLabel getObservacionLabel() {
        return observacionLabel;
    }

    public void setObservacionLabel(JLabel observacionLabel) {
        this.observacionLabel = observacionLabel;
    }

    public JTextArea getObservacionTextArea() {
        return observacionTextArea;
    }

    public void setObservacionTextArea(JTextArea observacionTextArea) {
        this.observacionTextArea = observacionTextArea;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JLabel getPrecioBaseLabel() {
        return precioBaseLabel;
    }

    public void setPrecioBaseLabel(JLabel precioBaseLabel) {
        this.precioBaseLabel = precioBaseLabel;
    }

    public JTextField getPrecioBaseTextBox() {
        return precioBaseTextBox;
    }

    public void setPrecioBaseTextBox(JTextField precioBaseTextBox) {
        this.precioBaseTextBox = precioBaseTextBox;
    }

    public JButton getSalir() {
        return salir;
    }

    public void setSalir(JButton salir) {
        this.salir = salir;
    }

    public JTextField getTamanioLoteEstandarTextBox() {
        return tamanioLoteEstandarTextBox;
    }

    public void setTamanioLoteEstandarTextBox(JTextField tamanioLoteEstandarTextBox) {
        this.tamanioLoteEstandarTextBox = tamanioLoteEstandarTextBox;
    }

    public JLabel getTamanioLoteEstandarLabel() {
        return tamanioLoteEstandarLabel;
    }

    public void setTamanioLoteEstandarLabel(JLabel tamanioLoteEstandarLabel) {
        this.tamanioLoteEstandarLabel = tamanioLoteEstandarLabel;
    }

    public JToolBar getToolbar() {
        return toolbar;
    }

    public void setToolbar(JToolBar toolbar) {
        this.toolbar = toolbar;
    }

    public JTextField getUbicacionAlamcenTextBox() {
        return ubicacionAlamcenTextBox;
    }

    public void setUbicacionAlamcenTextBox(JTextField ubicacionAlamcenTextBox) {
        this.ubicacionAlamcenTextBox = ubicacionAlamcenTextBox;
    }

    public JLabel getUbicacionEnElAlmacenLabel() {
        return ubicacionEnElAlmacenLabel;
    }

    public void setUbicacionEnElAlmacenLabel(JLabel ubicacionEnElAlmacenLabel) {
        this.ubicacionEnElAlmacenLabel = ubicacionEnElAlmacenLabel;
    }

    public JLabel getUnidadDeMedidaLabel() {
        return unidadDeMedidaLabel;
    }

    public void setUnidadDeMedidaLabel(JLabel unidadDeMedidaLabel) {
        this.unidadDeMedidaLabel = unidadDeMedidaLabel;
    }

    public JComboBox getUnidadDeMedidaListBox() {
        return unidadDeMedidaListBox;
    }

    public void setUnidadDeMedidaListBox(JComboBox unidadDeMedidaListBox) {
        this.unidadDeMedidaListBox = unidadDeMedidaListBox;
    }

    public JLabel getEstadoEntrarEnActividadEnFechaLabel() {
        return estadoEntrarEnActividadEnFechaLabel;
    }

    public void setEstadoEntrarEnActividadEnFechaLabel(JLabel estadoEntrarEnActividadEnFechaLabel) {
        this.estadoEntrarEnActividadEnFechaLabel = estadoEntrarEnActividadEnFechaLabel;
    }

    public JDateChooser getEstadoEntrarEnActividadEnFechajDateChooser() {
        return estadoEntrarEnActividadEnFechajDateChooser;
    }

    public void setEstadoEntrarEnActividadEnFechajDateChooser(JDateChooser estadoEntrarEnActividadEnFechajDateChooser) {
        this.estadoEntrarEnActividadEnFechajDateChooser = estadoEntrarEnActividadEnFechajDateChooser;
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public void setEliminar(JButton eliminar) {
        this.eliminar = eliminar;
    }

    public JLabel setProductoIQERelacionadoLabel() {
        return productoIQERelacionadoLabel;
    }

    public void setProductoIQERelacionado(JLabel ProductoIQERelacionado) {
        this.productoIQERelacionadoLabel = ProductoIQERelacionado;
    }

    public JComboBox getProductoIQERelacionadoListBox() {
        return productoIQERelacionadoListBox;
    }

    public void setProductoIQERelacionadoListBox(JComboBox proveedorPredeterminadoListBox) {
        this.productoIQERelacionadoListBox = proveedorPredeterminadoListBox;
    }

}
