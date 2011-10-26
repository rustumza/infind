/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaMadre.java
 *
 * Created on 19/10/2011, 19:14:23
 */
package interfacesGraficas;

import interfacesGraficas.Controladores.ControladorPantallaMadre;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author rustu
 */
public class PantallaMadre extends javax.swing.JFrame {
    
    ControladorPantallaMadre controlador;

    /** Creates new form PantallaMadre */
    public PantallaMadre(ControladorPantallaMadre cpm) {
        initComponents();
        controlador = cpm;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        botonAyuda = new javax.swing.JMenuItem();
        botonSalir = new javax.swing.JMenuItem();
        articulos = new javax.swing.JMenu();
        crearArticulo = new javax.swing.JMenu();
        crearMateriaPrima = new javax.swing.JMenuItem();
        crearProductoIntermedio = new javax.swing.JMenuItem();
        crearProductoComponente = new javax.swing.JMenuItem();
        crearProductoFinal = new javax.swing.JMenuItem();
        editarArticulo = new javax.swing.JMenuItem();
        Listar = new javax.swing.JMenu();
        listarMateriasPrimas = new javax.swing.JMenuItem();
        listarProductosComponentes = new javax.swing.JMenuItem();
        listarProductosIntermedios = new javax.swing.JMenuItem();
        listarProductosFinales = new javax.swing.JMenuItem();
        estructuraDeProducto = new javax.swing.JMenu();
        centrosDeTrabajo = new javax.swing.JMenu();
        crearCentro = new javax.swing.JMenuItem();
        editarCentro = new javax.swing.JMenuItem();
        rutaDeFabricacion = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menuIngresarOperarios = new javax.swing.JMenuItem();
        menuEditarOperarios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Informática Industrial - EON Argentina");

        jMenu1.setText("Archivo");
        jMenu1.setMargin(new java.awt.Insets(2, 2, 2, 12));

        botonAyuda.setText("Ayuda");
        jMenu1.add(botonAyuda);

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        jMenu1.add(botonSalir);

        jMenuBar1.add(jMenu1);

        articulos.setText("Articulos");
        articulos.setMargin(new java.awt.Insets(2, 2, 2, 12));

        crearArticulo.setText("Crear Articulo");

        crearMateriaPrima.setText("Materia prima");
        crearMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearMateriaPrimaActionPerformed(evt);
            }
        });
        crearArticulo.add(crearMateriaPrima);

        crearProductoIntermedio.setText("Producto intermedio");
        crearArticulo.add(crearProductoIntermedio);

        crearProductoComponente.setText("Producto componente");
        crearArticulo.add(crearProductoComponente);

        crearProductoFinal.setText("Producto final");
        crearProductoFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProductoFinalActionPerformed(evt);
            }
        });
        crearArticulo.add(crearProductoFinal);

        articulos.add(crearArticulo);

        editarArticulo.setText("Editar articulo");
        articulos.add(editarArticulo);

        Listar.setText("Listar articulos");

        listarMateriasPrimas.setText("Materias primas");
        Listar.add(listarMateriasPrimas);

        listarProductosComponentes.setText("Productos componentes");
        Listar.add(listarProductosComponentes);

        listarProductosIntermedios.setText("Productos intermedios");
        Listar.add(listarProductosIntermedios);

        listarProductosFinales.setText("Productos finales");
        Listar.add(listarProductosFinales);

        articulos.add(Listar);

        jMenuBar1.add(articulos);

        estructuraDeProducto.setText("Estructura de Producto");
        estructuraDeProducto.setMargin(new java.awt.Insets(2, 2, 2, 12));
        jMenuBar1.add(estructuraDeProducto);

        centrosDeTrabajo.setText("Centros de trabajo");
        centrosDeTrabajo.setMargin(new java.awt.Insets(2, 2, 2, 12));

        crearCentro.setText("Crear centro");
        crearCentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearCentroActionPerformed(evt);
            }
        });
        centrosDeTrabajo.add(crearCentro);

        editarCentro.setText("Editar centro");
        editarCentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarCentroActionPerformed(evt);
            }
        });
        centrosDeTrabajo.add(editarCentro);

        jMenuBar1.add(centrosDeTrabajo);

        rutaDeFabricacion.setText("Ruta de fabricación");
        rutaDeFabricacion.setMargin(new java.awt.Insets(2, 2, 2, 12));
        jMenuBar1.add(rutaDeFabricacion);

        jMenu2.setText("Operarios");

        menuIngresarOperarios.setText("Ingresar Operarios");
        menuIngresarOperarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIngresarOperariosActionPerformed(evt);
            }
        });
        jMenu2.add(menuIngresarOperarios);

        menuEditarOperarios.setText("Editar Operarios");
        menuEditarOperarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditarOperariosActionPerformed(evt);
            }
        });
        jMenu2.add(menuEditarOperarios);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void crearCentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearCentroActionPerformed
    controlador.crearCentro();
}//GEN-LAST:event_crearCentroActionPerformed

private void crearProductoFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProductoFinalActionPerformed
    controlador.crearProductoFinal();
}//GEN-LAST:event_crearProductoFinalActionPerformed

private void crearMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearMateriaPrimaActionPerformed
    controlador.crearMateriaPrima();
}//GEN-LAST:event_crearMateriaPrimaActionPerformed

private void editarCentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarCentroActionPerformed
    controlador.editarCentro();
}//GEN-LAST:event_editarCentroActionPerformed

private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
    System.exit(0);
}//GEN-LAST:event_botonSalirActionPerformed

    private void menuIngresarOperariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIngresarOperariosActionPerformed
        controlador.crearOperario();
    }//GEN-LAST:event_menuIngresarOperariosActionPerformed

    private void menuEditarOperariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditarOperariosActionPerformed
        controlador.editarOperario();
    }//GEN-LAST:event_menuEditarOperariosActionPerformed

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
    
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaMadre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaMadre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaMadre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaMadre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    
      java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PantallaMadre().setVisible(true);
            }
        });
    }
     * 
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Listar;
    private javax.swing.JMenu articulos;
    private javax.swing.JMenuItem botonAyuda;
    private javax.swing.JMenuItem botonSalir;
    private javax.swing.JMenu centrosDeTrabajo;
    private javax.swing.JMenu crearArticulo;
    private javax.swing.JMenuItem crearCentro;
    private javax.swing.JMenuItem crearMateriaPrima;
    private javax.swing.JMenuItem crearProductoComponente;
    private javax.swing.JMenuItem crearProductoFinal;
    private javax.swing.JMenuItem crearProductoIntermedio;
    private javax.swing.JMenuItem editarArticulo;
    private javax.swing.JMenuItem editarCentro;
    private javax.swing.JMenu estructuraDeProducto;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem listarMateriasPrimas;
    private javax.swing.JMenuItem listarProductosComponentes;
    private javax.swing.JMenuItem listarProductosFinales;
    private javax.swing.JMenuItem listarProductosIntermedios;
    private javax.swing.JMenuItem menuEditarOperarios;
    private javax.swing.JMenuItem menuIngresarOperarios;
    private javax.swing.JMenu rutaDeFabricacion;
    // End of variables declaration//GEN-END:variables

    public JMenu getArticulos() {
        return articulos;
    }

    public void setArticulos(JMenu articulos) {
        this.articulos = articulos;
    }

    public JMenu getCentrosDeTrabajo() {
        return centrosDeTrabajo;
    }

    public void setCentrosDeTrabajo(JMenu centrosDeTrabajo) {
        this.centrosDeTrabajo = centrosDeTrabajo;
    }

    public JMenu getCrearArticulo() {
        return crearArticulo;
    }

    public void setCrearArticulo(JMenu crearArticulo) {
        this.crearArticulo = crearArticulo;
    }

    public JMenuItem getCrearMateriaPrima() {
        return crearMateriaPrima;
    }

    public void setCrearMateriaPrima(JMenuItem crearMateriaPrima) {
        this.crearMateriaPrima = crearMateriaPrima;
    }

    public JMenuItem getCrearProductoComponente() {
        return crearProductoComponente;
    }

    public void setCrearProductoComponente(JMenuItem crearProductoComponente) {
        this.crearProductoComponente = crearProductoComponente;
    }

    public JMenuItem getCrearProductoFinal() {
        return crearProductoFinal;
    }

    public void setCrearProductoFinal(JMenuItem crearProductoFinal) {
        this.crearProductoFinal = crearProductoFinal;
    }

    public JMenuItem getCrearProductoIntermedio() {
        return crearProductoIntermedio;
    }

    public void setCrearProductoIntermedio(JMenuItem crearProductoIntermedio) {
        this.crearProductoIntermedio = crearProductoIntermedio;
    }

    public JMenuItem getEditarArticulo() {
        return editarArticulo;
    }

    public void setEditarArticulo(JMenuItem editarArticulo) {
        this.editarArticulo = editarArticulo;
    }

    public JMenuItem getListarMateriasPrimas() {
        return listarMateriasPrimas;
    }

    public void setListarMateriasPrimas(JMenuItem listarMateriasPrimas) {
        this.listarMateriasPrimas = listarMateriasPrimas;
    }

    public JMenuItem getListarProductosComponentes() {
        return listarProductosComponentes;
    }

    public void setListarProductosComponentes(JMenuItem listarProductosComponentes) {
        this.listarProductosComponentes = listarProductosComponentes;
    }

    public JMenuItem getListarProductosFinales() {
        return listarProductosFinales;
    }

    public void setListarProductosFinales(JMenuItem listarProductosFinales) {
        this.listarProductosFinales = listarProductosFinales;
    }

    public JMenuItem getListarProductosIntermedios() {
        return listarProductosIntermedios;
    }

    public void setListarProductosIntermedios(JMenuItem listarProductosIntermedios) {
        this.listarProductosIntermedios = listarProductosIntermedios;
    }
    
    

    

    public JMenuItem getCrearCentro() {
        return crearCentro;
    }

    public void setCrearCentro(JMenuItem crearCentro) {
        this.crearCentro = crearCentro;
    }

    public JMenuItem getEditarCentro() {
        return editarCentro;
    }

    public void setEditarCentro(JMenuItem editarCentro) {
        this.editarCentro = editarCentro;
    }

    public JMenu getEstructuraDeProducto() {
        return estructuraDeProducto;
    }

    public void setEstructuraDeProducto(JMenu estructuraDeProducto) {
        this.estructuraDeProducto = estructuraDeProducto;
    }

    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    public JMenu getRutaDeFabricacion() {
        return rutaDeFabricacion;
    }

    public void setRutaDeFabricacion(JMenu rutaDeFabricacion) {
        this.rutaDeFabricacion = rutaDeFabricacion;
    }

    public JMenuItem getBotonAyuda() {
        return botonAyuda;
    }

    public void setBotonAyuda(JMenuItem botonAyuda) {
        this.botonAyuda = botonAyuda;
    }

    public JMenuItem getBotonSalir() {
        return botonSalir;
    }

    public void setBotonSalir(JMenuItem botonSalir) {
        this.botonSalir = botonSalir;
    }

    

}
