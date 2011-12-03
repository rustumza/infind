/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.OrdenDeFabricacion;
import expertos.ExpertoOrdenDeFabricacion;
import interfacesGraficas.ModeloTablas.ModeloTablaListarOrdenesDeProduccion;
import interfacesGraficas.ModeloTablas.ModeloTablaOrdenDeProduccionPantallaOrdenDeproduccion;
import interfacesGraficas.ModeloTablas.ModeloTablaPedidosAProveedoresPantallaOrdenDeFabricacion;
import interfacesGraficas.PantallaEditarEstadoALaOrdenDeProduccion;
import interfacesGraficas.PantallaListarOrdendesDeFabricacion;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorListarOrdenesDeFabricacion {
    
    ControladorPantallaMadre controladorPantallaMadre;
    PantallaListarOrdendesDeFabricacion pantalla;    
    ExpertoOrdenDeFabricacion experto;
    PantallaEditarEstadoALaOrdenDeProduccion pantallaEditar;
    OrdenDeFabricacion orden;
    
    public ControladorListarOrdenesDeFabricacion(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoOrdenDeFabricacion();
        pantalla = new PantallaListarOrdendesDeFabricacion(contPantMad.getPantalla(), false, this);
    }
    
    
    public void iniciar(){
        List<OrdenDeFabricacion> lista = experto.buscarOrdenes("Todas");
        ModeloTablaListarOrdenesDeProduccion mod = new ModeloTablaListarOrdenesDeProduccion();
        mod.setListaElementos(lista);
        pantalla.getTablaOrdenesDeProduccion().setModel(mod);
        pantalla.setLocationRelativeTo(controladorPantallaMadre.getPantalla());
        pantalla.setVisible(true);
    
    }

    public void listarOrdenes() {
        
        List<OrdenDeFabricacion> lista = experto.buscarOrdenes((String)pantalla.getEstadoOrdenListBox().getSelectedItem());
        ModeloTablaListarOrdenesDeProduccion mod = new ModeloTablaListarOrdenesDeProduccion();
        mod.setListaElementos(lista);
        pantalla.getTablaOrdenesDeProduccion().setModel(mod);
        
    }

    public void editarOrden() {
        int columnaSeleccionada = pantalla.getTablaOrdenesDeProduccion().getSelectedRow();
        orden = experto.getOrdenSeleccionada(columnaSeleccionada);
        pantallaEditar = new PantallaEditarEstadoALaOrdenDeProduccion(controladorPantallaMadre.getPantalla(), true, this);
        
        pantallaEditar.getProductoAFabricarValorReal().setText(orden.getProductoFabricable().getNombre());
        if(orden.getEstado().equals("Generada")){
            pantallaEditar.getCambiarAEstadoValorReal().setText("En curso");
            pantallaEditar.getSi().setEnabled(true);
            pantallaEditar.getNo().setEnabled(true);
            pantallaEditar.getEliminar().setEnabled(true);
        }else if(orden.getEstado().equals("En curso")){
            pantallaEditar.getCambiarAEstadoValorReal().setText("Finalizada");
            pantallaEditar.getSi().setEnabled(true);
            pantallaEditar.getNo().setEnabled(true);
            pantallaEditar.getEliminar().setEnabled(true);
        }else{
            pantallaEditar.getSi().setEnabled(false);
            pantallaEditar.getNo().setEnabled(false);
            pantallaEditar.getEliminar().setEnabled(false);
        }
        ModeloTablaPedidosAProveedoresPantallaOrdenDeFabricacion mod = new ModeloTablaPedidosAProveedoresPantallaOrdenDeFabricacion();
        mod.setListaElementos(orden.getListaDePedido());
        pantallaEditar.getTablaOrdenesDeCompraYProduccion().setModel(mod);


        ModeloTablaOrdenDeProduccionPantallaOrdenDeproduccion mod1 = new ModeloTablaOrdenDeProduccionPantallaOrdenDeproduccion();
        mod1.setListaElementos(orden.getListaDeOrdenes());
        pantallaEditar.getTablaOrdenesDeProduccion().setModel(mod1);
        
        pantallaEditar.setVisible(true);
    }
    
    


    public void cambiarEstadoALaOrden() {
        
        experto.camibiarEstadoDeLaOrden();
    }

    public void eliminarOrden() {
        
    }
}
