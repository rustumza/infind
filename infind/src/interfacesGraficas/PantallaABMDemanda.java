/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaABMCostosFijos.java
 *
 * Created on 29/11/2011, 08:57:13
 */
package interfacesGraficas;

import interfacesGraficas.Controladores.ControladorABMDemanda;
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
 * @author eduardo
 */
public class PantallaABMDemanda extends javax.swing.JDialog {

    ControladorABMDemanda controlador;

    /** Creates new form PantallaABMCostosFijos */
    public PantallaABMDemanda(java.awt.Frame parent, boolean modal, ControladorABMDemanda control) {
        super(parent, modal);
        initComponents();
        controlador = control;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        botonGuardar = new javax.swing.JButton();
        botonEliminarDemanda = new javax.swing.JButton();
        botonImprimirDemanda = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDemandas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabelValorDemanda = new javax.swing.JLabel();
        jLabelValorPeriodo = new javax.swing.JLabel();
        campoValorDemanda = new javax.swing.JTextField();
        comboListaProducto = new javax.swing.JComboBox();
        jLabelProductos = new javax.swing.JLabel();
        jComboBoxPeriodo = new javax.swing.JComboBox();
        jButtonBuscarDemanda = new javax.swing.JButton();
        botonAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jToolBar1.setRollover(true);

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/Save_30.png"))); // NOI18N
        botonGuardar.setToolTipText("Guardar");
        botonGuardar.setFocusable(false);
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(botonGuardar);

        botonEliminarDemanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/user-trash-30.png"))); // NOI18N
        botonEliminarDemanda.setToolTipText("Eliminar");
        botonEliminarDemanda.setFocusable(false);
        botonEliminarDemanda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEliminarDemanda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEliminarDemanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarDemandaActionPerformed(evt);
            }
        });
        jToolBar1.add(botonEliminarDemanda);

        botonImprimirDemanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/Printer-30.png"))); // NOI18N
        botonImprimirDemanda.setToolTipText("Imprimir");
        botonImprimirDemanda.setFocusable(false);
        botonImprimirDemanda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonImprimirDemanda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonImprimirDemanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImprimirDemandaActionPerformed(evt);
            }
        });
        jToolBar1.add(botonImprimirDemanda);

        botonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utilidades/imagenes/iconos/Log-out-30.png"))); // NOI18N
        botonSalir.setToolTipText("Salir");
        botonSalir.setFocusable(false);
        botonSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(botonSalir);

        jPanel1.add(jToolBar1, new java.awt.GridBagConstraints());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Demandas"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        tablaDemandas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDemandas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDemandasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDemandas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(33, 18, 18, 18);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Demanda"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelValorDemanda.setText("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        jPanel3.add(jLabelValorDemanda, gridBagConstraints);

        jLabelValorPeriodo.setText("Periodo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        jPanel3.add(jLabelValorPeriodo, gridBagConstraints);

        campoValorDemanda.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel3.add(campoValorDemanda, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 0, 0);
        jPanel3.add(comboListaProducto, gridBagConstraints);

        jLabelProductos.setText("Productos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        jPanel3.add(jLabelProductos, gridBagConstraints);

        jComboBoxPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", " " }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        jPanel3.add(jComboBoxPeriodo, gridBagConstraints);

        jButtonBuscarDemanda.setText("Buscar");
        jButtonBuscarDemanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarDemandaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 10, 0, 0);
        jPanel3.add(jButtonBuscarDemanda, gridBagConstraints);

        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 0, 0);
        jPanel3.add(botonAgregar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonEliminarDemandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarDemandaActionPerformed
        controlador.eliminarDemanda();
    }//GEN-LAST:event_botonEliminarDemandaActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        controlador.actualizarDemandas();
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonImprimirDemandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImprimirDemandaActionPerformed
        controlador.imprimirDemanda();
    }//GEN-LAST:event_botonImprimirDemandaActionPerformed

    private void tablaDemandasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDemandasMouseClicked
        int fila = getTablaDemandas().rowAtPoint(evt.getPoint());
        int click = evt.getClickCount();
        controlador.tabla(fila, click);
    }//GEN-LAST:event_tablaDemandasMouseClicked

    private void jButtonBuscarDemandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarDemandaActionPerformed
        controlador.buscarDemandas();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarDemandaActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        controlador.nuevaDemanda();
    }//GEN-LAST:event_botonAgregarActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonEliminarDemanda;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonImprimirDemanda;
    private javax.swing.JButton botonSalir;
    private javax.swing.JTextField campoValorDemanda;
    private javax.swing.JComboBox comboListaProducto;
    private javax.swing.JButton jButtonBuscarDemanda;
    private javax.swing.JComboBox jComboBoxPeriodo;
    private javax.swing.JLabel jLabelProductos;
    private javax.swing.JLabel jLabelValorDemanda;
    private javax.swing.JLabel jLabelValorPeriodo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tablaDemandas;
    // End of variables declaration//GEN-END:variables

    public JButton getBotonEliminarDemanda() {
        return botonEliminarDemanda;
    }

    public void setBotonEliminarDemanda(JButton botonEliminarDemanda) {
        this.botonEliminarDemanda = botonEliminarDemanda;
    }

    public JButton getBotonImprimirDemanda() {
        return botonImprimirDemanda;
    }

    public void setBotonImprimirDemanda(JButton botonImprimirDemanda) {
        this.botonImprimirDemanda = botonImprimirDemanda;
    }

    public JTextField getCampoValorDemanda() {
        return campoValorDemanda;
    }

    public void setCampoValorDemanda(JTextField campoValorDemanda) {
        this.campoValorDemanda = campoValorDemanda;
    }

    public JComboBox getComboListaProducto() {
        return comboListaProducto;
    }

    public void setComboListaProducto(JComboBox comboListaProducto) {
        this.comboListaProducto = comboListaProducto;
    }

    public JButton getjButtonBuscarDemanda() {
        return jButtonBuscarDemanda;
    }

    public void setjButtonBuscarDemanda(JButton jButtonBuscarDemanda) {
        this.jButtonBuscarDemanda = jButtonBuscarDemanda;
    }

    public JComboBox getjComboBoxPeriodo() {
        return jComboBoxPeriodo;
    }

    public void setjComboBoxPeriodo(JComboBox jComboBoxPeriodo) {
        this.jComboBoxPeriodo = jComboBoxPeriodo;
    }

    public JLabel getjLabelProductos() {
        return jLabelProductos;
    }

    public void setjLabelProductos(JLabel jLabelProductos) {
        this.jLabelProductos = jLabelProductos;
    }

    public JLabel getjLabelValorDemanda() {
        return jLabelValorDemanda;
    }

    public void setjLabelValorDemanda(JLabel jLabelValorDemanda) {
        this.jLabelValorDemanda = jLabelValorDemanda;
    }

    public JLabel getjLabelValorPeriodo() {
        return jLabelValorPeriodo;
    }

    public void setjLabelValorPeriodo(JLabel jLabelValorPeriodo) {
        this.jLabelValorPeriodo = jLabelValorPeriodo;
    }

    public JTable getTablaDemandas() {
        return tablaDemandas;
    }

    public void setTablaDemandas(JTable tablaDemandas) {
        this.tablaDemandas = tablaDemandas;
    }

    public JButton getBotonGuardar() {
        return botonGuardar;
    }

    public void setBotonGuardar(JButton botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public void setBotonSalir(JButton botonSalir) {
        this.botonSalir = botonSalir;
    }

    public ControladorABMDemanda getControlador() {
        return controlador;
    }

    public void setControlador(ControladorABMDemanda controlador) {
        this.controlador = controlador;
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

    public JToolBar getjToolBar1() {
        return jToolBar1;
    }

    public void setjToolBar1(JToolBar jToolBar1) {
        this.jToolBar1 = jToolBar1;
    }
}
