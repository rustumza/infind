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

import excepciones.ExpertoABMDemandaExcepcion;
import excepciones.ExpertoPuntoEquilibrioException;
import interfacesGraficas.Controladores.ControladorPantallaMadre;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        crearProductoComponente = new javax.swing.JMenuItem();
        crearProductoIntermedio = new javax.swing.JMenuItem();
        crearProductoIQE = new javax.swing.JMenuItem();
        crearProductoFinal = new javax.swing.JMenuItem();
        Listar = new javax.swing.JMenu();
        listarMateriasPrimas = new javax.swing.JMenuItem();
        listarProductosComponentes = new javax.swing.JMenuItem();
        listarProductosIntermedios = new javax.swing.JMenuItem();
        listarProductosIQE = new javax.swing.JMenuItem();
        listarProductosFinales = new javax.swing.JMenuItem();
        estructuraDeProducto = new javax.swing.JMenu();
        crearEstructuraDeProducto = new javax.swing.JMenuItem();
        centrosDeTrabajo = new javax.swing.JMenu();
        crearCentro = new javax.swing.JMenuItem();
        editarCentro = new javax.swing.JMenuItem();
        rutaDeFabricacion = new javax.swing.JMenu();
        menuEditarRuta = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        operarios = new javax.swing.JMenu();
        menuIngresarOperarios = new javax.swing.JMenuItem();
        menuEditarOperarios = new javax.swing.JMenuItem();
        proveedores = new javax.swing.JMenu();
        crearProveedores = new javax.swing.JMenuItem();
        jMenuListarProveedores = new javax.swing.JMenuItem();
        crearPedidosAProveedores = new javax.swing.JMenuItem();
        listarPedidosAProveedores = new javax.swing.JMenuItem();
        ordenesDeProduccion = new javax.swing.JMenu();
        crearOrdenDeProduccion = new javax.swing.JMenuItem();
        listarOrdenesDeProduccion = new javax.swing.JMenuItem();
        inventarios = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menuDemanda = new javax.swing.JMenu();
        menuPrediccion = new javax.swing.JMenuItem();
        menuABMDemandasHistoricas = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

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

        crearProductoComponente.setText("Producto componente");
        crearProductoComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProductoComponenteActionPerformed(evt);
            }
        });
        crearArticulo.add(crearProductoComponente);

        crearProductoIntermedio.setText("Producto intermedio");
        crearProductoIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProductoIntermedioActionPerformed(evt);
            }
        });
        crearArticulo.add(crearProductoIntermedio);

        crearProductoIQE.setText("Producto IQE");
        crearProductoIQE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProductoIQEActionPerformed(evt);
            }
        });
        crearArticulo.add(crearProductoIQE);

        crearProductoFinal.setText("Producto final");
        crearProductoFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProductoFinalActionPerformed(evt);
            }
        });
        crearArticulo.add(crearProductoFinal);

        articulos.add(crearArticulo);

        Listar.setText("Listar articulos");

        listarMateriasPrimas.setText("Materias primas");
        listarMateriasPrimas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarMateriasPrimasActionPerformed(evt);
            }
        });
        Listar.add(listarMateriasPrimas);

        listarProductosComponentes.setText("Productos componentes");
        listarProductosComponentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarProductosComponentesActionPerformed(evt);
            }
        });
        Listar.add(listarProductosComponentes);

        listarProductosIntermedios.setText("Productos intermedios");
        listarProductosIntermedios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarProductosIntermediosActionPerformed(evt);
            }
        });
        Listar.add(listarProductosIntermedios);

        listarProductosIQE.setText("Productos IQE");
        listarProductosIQE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarProductosIQEActionPerformed(evt);
            }
        });
        Listar.add(listarProductosIQE);

        listarProductosFinales.setText("Productos finales");
        listarProductosFinales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarProductosFinalesActionPerformed(evt);
            }
        });
        Listar.add(listarProductosFinales);

        articulos.add(Listar);

        jMenuBar1.add(articulos);

        estructuraDeProducto.setText("Estructura de Producto");
        estructuraDeProducto.setMargin(new java.awt.Insets(2, 2, 2, 12));

        crearEstructuraDeProducto.setText("Crear estructura de producto");
        crearEstructuraDeProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearEstructuraDeProductoActionPerformed(evt);
            }
        });
        estructuraDeProducto.add(crearEstructuraDeProducto);

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
        rutaDeFabricacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rutaDeFabricacionActionPerformed(evt);
            }
        });

        menuEditarRuta.setText("Crear Ruta de Fabricación");
        menuEditarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditarRutaActionPerformed(evt);
            }
        });
        rutaDeFabricacion.add(menuEditarRuta);

        jMenuItem2.setText("Editar Ruta de Fabricación");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        rutaDeFabricacion.add(jMenuItem2);

        jMenuBar1.add(rutaDeFabricacion);

        operarios.setText("Operarios");
        operarios.setMargin(new java.awt.Insets(0, 0, 0, 5));

        menuIngresarOperarios.setText("Crear Operarios");
        menuIngresarOperarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIngresarOperariosActionPerformed(evt);
            }
        });
        operarios.add(menuIngresarOperarios);

        menuEditarOperarios.setText("Editar Operarios");
        menuEditarOperarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditarOperariosActionPerformed(evt);
            }
        });
        operarios.add(menuEditarOperarios);

        jMenuBar1.add(operarios);

        proveedores.setText("Proveedores");
        proveedores.setMargin(new java.awt.Insets(0, 5, 0, 5));

        crearProveedores.setText("Crear proveedores");
        crearProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProveedoresActionPerformed(evt);
            }
        });
        proveedores.add(crearProveedores);

        jMenuListarProveedores.setText("Listar proveedores");
        jMenuListarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuListarProveedoresActionPerformed(evt);
            }
        });
        proveedores.add(jMenuListarProveedores);

        crearPedidosAProveedores.setText("Crear pedidos a proveedores");
        crearPedidosAProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearPedidosAProveedoresActionPerformed(evt);
            }
        });
        proveedores.add(crearPedidosAProveedores);

        listarPedidosAProveedores.setText("Listar pedidos a proveedores");
        listarPedidosAProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarPedidosAProveedoresActionPerformed(evt);
            }
        });
        proveedores.add(listarPedidosAProveedores);

        jMenuBar1.add(proveedores);

        ordenesDeProduccion.setText("Ordenes de producción");
        ordenesDeProduccion.setMargin(new java.awt.Insets(0, 5, 0, 5));

        crearOrdenDeProduccion.setText("Crear orden de producción");
        crearOrdenDeProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearOrdenDeProduccionActionPerformed(evt);
            }
        });
        ordenesDeProduccion.add(crearOrdenDeProduccion);

        listarOrdenesDeProduccion.setText("Listar ordenes de Produccion");
        listarOrdenesDeProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarOrdenesDeProduccionActionPerformed(evt);
            }
        });
        ordenesDeProduccion.add(listarOrdenesDeProduccion);

        jMenuBar1.add(ordenesDeProduccion);

        inventarios.setText("Inventarios");
        inventarios.setMargin(new java.awt.Insets(0, 5, 0, 5));

        jMenuItem6.setText("Costo de gestion de inventario para un producto");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        inventarios.add(jMenuItem6);

        jMenuItem7.setText("Costo de gestion de inventario para todos los productos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        inventarios.add(jMenuItem7);

        jMenuItem8.setText("Listar productos por debajo del punto de pedido");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        inventarios.add(jMenuItem8);

        jMenuItem9.setText("Listar productos por debajo del punto de stock de seguridad");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        inventarios.add(jMenuItem9);

        jMenuBar1.add(inventarios);

        jMenu2.setText("Costos");
        jMenu2.setMargin(new java.awt.Insets(0, 5, 0, 5));

        jMenuItem1.setText("ABM Costos Fijos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setText("ABM Costos Variables");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        menuDemanda.setText("Demanda");
        menuDemanda.setMargin(new java.awt.Insets(0, 5, 0, 5));

        menuPrediccion.setText("Calcular Predicción");
        menuPrediccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPrediccionActionPerformed(evt);
            }
        });
        menuDemanda.add(menuPrediccion);

        menuABMDemandasHistoricas.setText("ABM Demandas Historicas");
        menuABMDemandasHistoricas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuABMDemandasHistoricasActionPerformed(evt);
            }
        });
        menuDemanda.add(menuABMDemandasHistoricas);

        jMenuBar1.add(menuDemanda);

        jMenu3.setText("Punto Equilibrio");
        jMenu3.setMargin(new java.awt.Insets(0, 5, 0, 5));

        jMenuItem4.setText("Punto Equilibrio");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Índices");
        jMenu4.setMargin(new java.awt.Insets(0, 5, 0, 5));

        jMenuItem5.setText("ABM Indices");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem10.setText("Mostrar niveles de normalidad de indices");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1454, Short.MAX_VALUE)
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

    private void listarMateriasPrimasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarMateriasPrimasActionPerformed
        controlador.listarMateriaPrima();
    }//GEN-LAST:event_listarMateriasPrimasActionPerformed

    private void listarProductosComponentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarProductosComponentesActionPerformed
        controlador.listarProductoComponente();
    }//GEN-LAST:event_listarProductosComponentesActionPerformed

    private void listarProductosIntermediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarProductosIntermediosActionPerformed
        controlador.listarProductosIntermedios();
    }//GEN-LAST:event_listarProductosIntermediosActionPerformed

    private void listarProductosFinalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarProductosFinalesActionPerformed
        controlador.listarProductosFinales();
    }//GEN-LAST:event_listarProductosFinalesActionPerformed

    private void rutaDeFabricacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rutaDeFabricacionActionPerformed
    }//GEN-LAST:event_rutaDeFabricacionActionPerformed

    private void menuEditarRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditarRutaActionPerformed
        controlador.crearRutaFabricacion();
    }//GEN-LAST:event_menuEditarRutaActionPerformed

private void crearEstructuraDeProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearEstructuraDeProductoActionPerformed
    controlador.crearEstructuraDeProducto();
}//GEN-LAST:event_crearEstructuraDeProductoActionPerformed

    private void crearProductoIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProductoIntermedioActionPerformed
        controlador.crearProductoIntermedio();
    }//GEN-LAST:event_crearProductoIntermedioActionPerformed

    private void crearProductoComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProductoComponenteActionPerformed
        controlador.crearProductoComponente();
    }//GEN-LAST:event_crearProductoComponenteActionPerformed

private void crearProductoIQEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProductoIQEActionPerformed
    controlador.crearProductoIQE();
}//GEN-LAST:event_crearProductoIQEActionPerformed

private void listarProductosIQEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarProductosIQEActionPerformed
    controlador.listarProductosIQE();
}//GEN-LAST:event_listarProductosIQEActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        controlador.EditarRutaFabricacion();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuListarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuListarProveedoresActionPerformed
    controlador.listarProveedores();
}//GEN-LAST:event_jMenuListarProveedoresActionPerformed

private void crearProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProveedoresActionPerformed
    controlador.crearProveedor();
}//GEN-LAST:event_crearProveedoresActionPerformed

private void crearPedidosAProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearPedidosAProveedoresActionPerformed
    controlador.crearPedidoAProveedor();
}//GEN-LAST:event_crearPedidosAProveedoresActionPerformed

private void listarPedidosAProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarPedidosAProveedoresActionPerformed
    controlador.listarPedidosAProveedores();
}//GEN-LAST:event_listarPedidosAProveedoresActionPerformed

private void crearOrdenDeProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearOrdenDeProduccionActionPerformed
    controlador.crearOrdeDeProduccion();
}//GEN-LAST:event_crearOrdenDeProduccionActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        controlador.abmCostosFijos();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    controlador.abmCostosVAriables();
}//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            controlador.puntoEquilibrio();
        } catch (ExpertoPuntoEquilibrioException ex) {
            Logger.getLogger(PantallaMadre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void menuABMDemandasHistoricasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuABMDemandasHistoricasActionPerformed
        try {
            controlador.abmDemandas();
        } catch (ExpertoABMDemandaExcepcion ex) {
            Logger.getLogger(PantallaMadre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuABMDemandasHistoricasActionPerformed

private void listarOrdenesDeProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarOrdenesDeProduccionActionPerformed
    controlador.listarOrdenesDeFabricacion();
}//GEN-LAST:event_listarOrdenesDeProduccionActionPerformed

    private void menuPrediccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPrediccionActionPerformed
        controlador.prediccion();
    }//GEN-LAST:event_menuPrediccionActionPerformed

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    controlador.crearIndices();
}//GEN-LAST:event_jMenuItem5ActionPerformed

private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
    controlador.cgiParaUnProducto();
}//GEN-LAST:event_jMenuItem6ActionPerformed

private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
    controlador.cgiParaTodosLosProductos();
}//GEN-LAST:event_jMenuItem7ActionPerformed

private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
    controlador.listarProductosBajoElPuntoDePedido();
}//GEN-LAST:event_jMenuItem8ActionPerformed

private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
    controlador.listarProductosBajoElStockDeSeguridad();
}//GEN-LAST:event_jMenuItem9ActionPerformed

private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
    controlador.mostrarNivelesDeNormalidadDeIndices();
}//GEN-LAST:event_jMenuItem10ActionPerformed
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
    private javax.swing.JMenuItem crearEstructuraDeProducto;
    private javax.swing.JMenuItem crearMateriaPrima;
    private javax.swing.JMenuItem crearOrdenDeProduccion;
    private javax.swing.JMenuItem crearPedidosAProveedores;
    private javax.swing.JMenuItem crearProductoComponente;
    private javax.swing.JMenuItem crearProductoFinal;
    private javax.swing.JMenuItem crearProductoIQE;
    private javax.swing.JMenuItem crearProductoIntermedio;
    private javax.swing.JMenuItem crearProveedores;
    private javax.swing.JMenuItem editarCentro;
    private javax.swing.JMenu estructuraDeProducto;
    private javax.swing.JMenu inventarios;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuListarProveedores;
    private javax.swing.JMenuItem listarMateriasPrimas;
    private javax.swing.JMenuItem listarOrdenesDeProduccion;
    private javax.swing.JMenuItem listarPedidosAProveedores;
    private javax.swing.JMenuItem listarProductosComponentes;
    private javax.swing.JMenuItem listarProductosFinales;
    private javax.swing.JMenuItem listarProductosIQE;
    private javax.swing.JMenuItem listarProductosIntermedios;
    private javax.swing.JMenuItem menuABMDemandasHistoricas;
    private javax.swing.JMenu menuDemanda;
    private javax.swing.JMenuItem menuEditarOperarios;
    private javax.swing.JMenuItem menuEditarRuta;
    private javax.swing.JMenuItem menuIngresarOperarios;
    private javax.swing.JMenuItem menuPrediccion;
    private javax.swing.JMenu operarios;
    private javax.swing.JMenu ordenesDeProduccion;
    private javax.swing.JMenu proveedores;
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
