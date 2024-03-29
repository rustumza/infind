/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MaestroDeArticulo;
import Entidades.OrdenDeFabricacion;
import Entidades.PedidoAProveedor;
import Entidades.ProductosFabricables;
import excepciones.StockExcepcion;
import expertos.ExpertoOrdenDeFabricacion;
import interfacesGraficas.ModeloTablas.ModeloTablaOrdenDeProduccionPantallaListarOrdenDeProduccion;
import interfacesGraficas.ModeloTablas.ModeloTablaPedidosAProveedoresPantallaListarOrdenDeFabricacion;
import interfacesGraficas.PantallaCrearOrdenDeFabricacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorCrearOrdenDeFabricacion {
    
    
    private ControladorPantallaMadre controladorPantallaMadre;
    private PantallaCrearOrdenDeFabricacion pantalla;
    private ExpertoOrdenDeFabricacion experto;
    private OrdenDeFabricacion orden;
    
    
    public ControladorCrearOrdenDeFabricacion(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        experto = new ExpertoOrdenDeFabricacion();
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaCrearOrdenDeFabricacion(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setLocationRelativeTo(null);
        pantalla.getGenerar().setEnabled(false);
        pantalla.getTablaOrdenesDeCompraYProduccion().setModel(new ModeloTablaPedidosAProveedoresPantallaListarOrdenDeFabricacion());
        pantalla.getTablaOrdenesDeProduccion().setModel(new ModeloTablaOrdenDeProduccionPantallaListarOrdenDeProduccion());
        pantalla.setVisible(true);
    }

    public void buscarProducto() { 
        String codigo = pantalla.getCodigoTextBox().getText();
        MaestroDeArticulo articulo;
        if(!codigo.equals("")){
            if(((String)pantalla.getTipoProducto().getSelectedItem()).equals("Producto final")){
                articulo = experto.buscarProductoFinal(codigo);
                if(articulo == null){
                    JOptionPane.showMessageDialog(pantalla, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantalla.getCodigoTextBox().requestFocus();
                    return;
                }
            }else{
                articulo = experto.buscarProductoIntermedio(codigo);
                if(articulo == null){
                    JOptionPane.showMessageDialog(pantalla, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantalla.getCodigoTextBox().requestFocus();
                    return;
                }
            }
            pantalla.getProductoSeleccionadoTextBox().setText(articulo.getNombre());
            pantalla.getLoteEstandarTextBox().setText(String.valueOf(articulo.getTamanioLoteEstandar()));
            pantalla.getUnidadDeMedida().setText(articulo.getUnidadDeMedida());            
        }

    }

    public void probarOrden() {
        try{
            Date fecha = pantalla.getFechaDeInicioDateChooser().getDate();
            if(fecha == null){
                JOptionPane.showMessageDialog(pantalla, "No se ha ingresado una fecha incorrecta", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantalla.getFechaDeInicioDateChooser().requestFocus();
                return;
            }
            String cantidadString = pantalla.getCantidadDeLotesTextBox().getText();
            int cantidadInt = 0;
            if(!cantidadString.equals("")){
                cantidadInt = Integer.valueOf(cantidadString);
            }else{
                JOptionPane.showMessageDialog(pantalla, "No se ha ingresado una cantidad incorrecta", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantalla.getCantidadDeLotesTextBox().requestFocus();
                return;
            }
            orden = experto.probarGeneracionDeOrdenDeFabricacion(fecha, cantidadInt);
            
            pantalla.getFechaPosibleRealizacionDataChooser().setDate(orden.getFecha());
            
            ((ProductosFabricables)experto.getArticulo()).removeOrden(orden);
            
            ModeloTablaPedidosAProveedoresPantallaListarOrdenDeFabricacion mod = new ModeloTablaPedidosAProveedoresPantallaListarOrdenDeFabricacion();
            List<PedidoAProveedor> listaDePedidos = new ArrayList<PedidoAProveedor>();
            listaDePedidos = listaDePedidos(orden);
            mod.setListaElementos(listaDePedidos);
            pantalla.getTablaOrdenesDeCompraYProduccion().setModel(mod);
            
            
            ModeloTablaOrdenDeProduccionPantallaListarOrdenDeProduccion mod1 = new ModeloTablaOrdenDeProduccionPantallaListarOrdenDeProduccion();
            List<OrdenDeFabricacion> listaDeOrdenes = new ArrayList<OrdenDeFabricacion>();
            listaDeOrdenes = listaDeOrdenes(orden);
            mod1.setListaElementos(listaDeOrdenes);
            pantalla.getTablaOrdenesDeProduccion().setModel(mod1);
            
            
            pantalla.getGenerar().setEnabled(true);
            
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantalla, "Se ha ingresado una cantidad incorrecta", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantalla.getCantidadDeLotesTextBox().requestFocus();
            return;
        }
    }

    
    
    
    public void generarOrden(){
        try{
            Date fecha = pantalla.getFechaDeInicioDateChooser().getDate();
            if(fecha == null){
                JOptionPane.showMessageDialog(pantalla, "No se ha ingresado una fecha incorrecta", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantalla.getFechaDeInicioDateChooser().requestFocus();
                return;
            }
            String cantidadString = pantalla.getCantidadDeLotesTextBox().getText();
            int cantidadInt = 0;
            if(!cantidadString.equals("")){
                cantidadInt = Integer.valueOf(cantidadString);
            }else{
                JOptionPane.showMessageDialog(pantalla, "No se ha ingresado una cantidad incorrecta", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantalla.getCantidadDeLotesTextBox().requestFocus();
                return;
            }            
            OrdenDeFabricacion ordenFab = experto.generarOrdenesPrimero(fecha, cantidadInt);
            if(ordenFab == null){
                JOptionPane.showMessageDialog(pantalla, "Error al generar la orden", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            pantalla.getGenerar().setEnabled(false);
            pantalla.getTablaOrdenesDeCompraYProduccion().setModel(new ModeloTablaPedidosAProveedoresPantallaListarOrdenDeFabricacion());
            pantalla.getTablaOrdenesDeProduccion().setModel(new ModeloTablaOrdenDeProduccionPantallaListarOrdenDeProduccion());
            pantalla.getCantidadDeLotesTextBox().setText("");
            pantalla.getFechaDeInicioDateChooser().setDate(null);
            pantalla.getCodigoTextBox().setText("");
            pantalla.getProductoSeleccionadoTextBox().setText("");
            pantalla.getLoteEstandarTextBox().setText("");
            pantalla.getUnidadDeMedida().setText("");
            pantalla.getFechaPosibleRealizacionDataChooser().setDate(null);
            experto = new ExpertoOrdenDeFabricacion();
            JOptionPane.showMessageDialog(pantalla, "Orden generada correctamente", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(StockExcepcion e){
            JOptionPane.showMessageDialog(pantalla, "Error al reservar el stock para generar la orden", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            return;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(pantalla, "Error al generar la orden", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
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
