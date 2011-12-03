/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.OrdenDeFabricacion;
import Entidades.PedidoAProveedor;
import expertos.ExpertoOrdenDeFabricacion;
import interfacesGraficas.ModeloTablas.ModeloTablaListarOrdenesDeProduccion;
import interfacesGraficas.ModeloTablas.ModeloTablaOrdenDeProduccionPantallaOrdenDeproduccion;
import interfacesGraficas.ModeloTablas.ModeloTablaPedidosAProveedoresPantallaOrdenDeFabricacion;
import interfacesGraficas.PantallaEditarEstadoALaOrdenDeProduccion;
import interfacesGraficas.PantallaListarOrdendesDeFabricacion;
import java.util.ArrayList;
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
    
    public ControladorListarOrdenesDeFabricacion(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoOrdenDeFabricacion();
        pantalla = new PantallaListarOrdendesDeFabricacion(contPantMad.getPantalla(), false, this);
    }
    
    
    public void iniciar(){
        List<OrdenDeFabricacion> lista = experto.buscarOrdenes(null);
        ModeloTablaListarOrdenesDeProduccion mod = new ModeloTablaListarOrdenesDeProduccion();
        mod.setListaElementos(lista);
        pantalla.getTablaOrdenesDeProduccion().setModel(mod);
        pantalla.setLocationRelativeTo(null);
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
        OrdenDeFabricacion orden = experto.getOrdenSeleccionada(columnaSeleccionada);
        pantallaEditar = new PantallaEditarEstadoALaOrdenDeProduccion(controladorPantallaMadre.getPantalla(), true, this);
        
        pantallaEditar.getProductoAFabricarValorReal().setText(orden.getProductoFabricable().getNombre());
        if(orden.getEstado().equals("Generada")){
            pantallaEditar.getCambiarAEstadoValorReal().setText("En curso");
        }else if(orden.getEstado().equals("En curso")){
            pantallaEditar.getCambiarAEstadoValorReal().setText("Finalizada");
        }else{
            JOptionPane.showMessageDialog(pantalla, "Ya no se le puede cambiar el estado a esta orden porque está finalizada", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditar = null;
            return;
        }
        ModeloTablaPedidosAProveedoresPantallaOrdenDeFabricacion mod = new ModeloTablaPedidosAProveedoresPantallaOrdenDeFabricacion();
        List<PedidoAProveedor> listaDePedidos = new ArrayList<PedidoAProveedor>();
        listaDePedidos = listaDePedidos(orden);
        mod.setListaElementos(listaDePedidos);
        pantallaEditar.getTablaOrdenesDeCompraYProduccion().setModel(mod);


        ModeloTablaOrdenDeProduccionPantallaOrdenDeproduccion mod1 = new ModeloTablaOrdenDeProduccionPantallaOrdenDeproduccion();
        List<OrdenDeFabricacion> listaDeOrdenes = new ArrayList<OrdenDeFabricacion>();
        listaDeOrdenes = listaDeOrdenes(orden);
        mod1.setListaElementos(listaDeOrdenes);
        pantallaEditar.getTablaOrdenesDeProduccion().setModel(mod1);
        
        pantallaEditar.setVisible(true);
    }
    
    
    private List<PedidoAProveedor> listaDePedidos(OrdenDeFabricacion orden){
        List<PedidoAProveedor> listaDePedidos = new ArrayList<PedidoAProveedor>();
        listaDePedidos.addAll(orden.getListaDePedido());
        for (OrdenDeFabricacion ord : orden.getListaDeOrdenes()) {
            listaDePedidos.addAll(listaDePedidos(ord));            
        }
        return listaDePedidos;
    }
    
    
    private List<OrdenDeFabricacion> listaDeOrdenes(OrdenDeFabricacion orden){
        List<OrdenDeFabricacion> listaDeOrdenes = new ArrayList<OrdenDeFabricacion>();
        listaDeOrdenes.addAll(orden.getListaDeOrdenes());
        for (OrdenDeFabricacion ord : orden.getListaDeOrdenes()) {
            listaDeOrdenes.addAll(listaDeOrdenes(ord));            
        }
        return listaDeOrdenes;
    }
}
