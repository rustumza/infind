/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.DetalleEstructuraDeProducto;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeEstructuraDeProducto;
import Entidades.ProductosFabricables;
import expertos.ExpertoEstructuraDeProducto;
import interfacesGraficas.ModeloTablas.ModeloTablaPantallaEstructuraDeProducto;
import interfacesGraficas.PantallaCrearEstructuraDeProducto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorEstructuraDeProducto {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearEstructuraDeProducto pantallaCrearEstructuraDeProducto;
    ExpertoEstructuraDeProducto experto;
    int detalleSeleccionado;
    
    public ControladorEstructuraDeProducto(ControladorPantallaMadre cont) {
        
        controladorPantallaMadre = cont;
        experto = new ExpertoEstructuraDeProducto();
        
    }
    
    public void crearEstructuraDeProducto(){
        pantallaCrearEstructuraDeProducto = new PantallaCrearEstructuraDeProducto(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearEstructuraDeProducto.setVisible(true);
        
    
    }

    public void buscarProducto() {
        String tipo = (String)(pantallaCrearEstructuraDeProducto.getTipoProductoListBox().getModel().getSelectedItem());
        String codigo = pantallaCrearEstructuraDeProducto.getCodigoProductoTextBox().getText();
        MaestroDeEstructuraDeProducto estructura = null;
        ProductosFabricables  prodFab = null;
        if(tipo.equals("Producto final")){
            
             estructura = experto.buscarProductoFinal(codigo);
             prodFab = estructura.getProductoFinal();
        }else if(tipo.equals("Producto IQE")){
            
            estructura = experto.buscarProductoIQE(codigo);
            prodFab = estructura.getProductoTipoIQE();
        }else if(tipo.equals("Prodcuto intermedio")){
            
            estructura = experto.buscarProductoIntermedio(codigo);
            prodFab = estructura.getProductoIntermedio();
        
        }
        
        
        pantallaCrearEstructuraDeProducto.getProductoSeleccionadoTextBox().setText(prodFab.getNombre());        
        //inicializo tabla articulos de prod iqe
        ModeloTablaPantallaEstructuraDeProducto mod = new ModeloTablaPantallaEstructuraDeProducto();
        mod.setListaElementos(prodFab.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList());
        pantallaCrearEstructuraDeProducto.getTablaArticulosProdIQE().setModel(mod);
        //inicializo tabla de articulos del producto
        ModeloTablaPantallaEstructuraDeProducto mod1 = new ModeloTablaPantallaEstructuraDeProducto();
        mod.setListaElementos(new ArrayList<DetalleEstructuraDeProducto>());
        pantallaCrearEstructuraDeProducto.getTablaArticulosProdIQE().setModel(mod1);
        pantallaCrearEstructuraDeProducto.getEditar().setEnabled(false);
        pantallaCrearEstructuraDeProducto.getQuitar().setEnabled(false);
        
        
        
        
    }

    public void cargarListaDeMatPrimProdComp() {
        String aux = (String)pantallaCrearEstructuraDeProducto.getTipoMateriaPrimaProductoComponete().getModel().getSelectedItem();
        List productos = null;
        if(aux.equals("Materia prima")){
            productos = experto.buscarMateriasPrimas();
    
        }else if(aux.equals("Producto componente")){
            productos = experto.buscarProductoComponente();
        
        
        }else if(aux.equals("Producto intermedio")){
            productos = experto.buscarProductoIntermedio();
        
        }
        pantallaCrearEstructuraDeProducto.getMatPrimProdComp().setModel(new DefaultComboBoxModel(productos.toArray()));
        cargarUnidadDeMedida();
    
    }

    public void cargarUnidadDeMedida() {
        pantallaCrearEstructuraDeProducto.getUnidadDeMedida().setText(((MaestroDeArticulo)pantallaCrearEstructuraDeProducto.getMatPrimProdComp().getSelectedItem()).getUnidadDeMedida());
    }

    public void agregarDetalle() {
        try{
            DetalleEstructuraDeProducto detalle = new DetalleEstructuraDeProducto();
            detalle.setTipo((String)pantallaCrearEstructuraDeProducto.getTipoMateriaPrimaProductoComponete().getSelectedItem());
            detalle.setMaestroArticulo((MaestroDeArticulo)pantallaCrearEstructuraDeProducto.getMatPrimProdComp().getSelectedItem());
            detalle.setCantidad(Float.valueOf(pantallaCrearEstructuraDeProducto.getCantidadTextBox().getText()));
            detalle.setEliminado(false);
            actualizarPantalla(experto.agregarDetalle(detalle));
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(pantallaCrearEstructuraDeProducto, "La cantidad ingresada no es válida", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearEstructuraDeProducto.getCantidadTextBox().requestFocus();
        }
    }
        
    
    private void actualizarPantalla(MaestroDeEstructuraDeProducto estructura){
    
        ProductosFabricables  prodFab = null;
        if(estructura.getProductoFinal()!=null){
            
             prodFab = estructura.getProductoFinal();
             
        }else if(estructura.getProductoTipoIQE() != null){
            
            prodFab = estructura.getProductoTipoIQE();
            
        }else if(estructura.getProductoIntermedio() != null){
            
            prodFab = estructura.getProductoIntermedio();
        
        }
        
        
        pantallaCrearEstructuraDeProducto.getProductoSeleccionadoTextBox().setText(prodFab.getNombre());        
        //inicializo tabla de articulos del producto
        ModeloTablaPantallaEstructuraDeProducto mod1 = new ModeloTablaPantallaEstructuraDeProducto();
        mod1.setListaElementos(estructura.getDetalleEstructuraProductoList());
        pantallaCrearEstructuraDeProducto.getTablaArticulosProdIQE().setModel(mod1);
        
        pantallaCrearEstructuraDeProducto.getEditar().setEnabled(false);
        pantallaCrearEstructuraDeProducto.getQuitar().setEnabled(false);
        
    
    }

    public void cargarDetalleSeleccionado(int detalleSeleccionado) {
        pantallaCrearEstructuraDeProducto.getQuitar().setEnabled(true);
        pantallaCrearEstructuraDeProducto.getEditar().setEnabled(true);
        DetalleEstructuraDeProducto detalle = (DetalleEstructuraDeProducto)((ModeloTablaPantallaEstructuraDeProducto)pantallaCrearEstructuraDeProducto.getTablaDeMatPrimProdComp().getModel()).getRow(detalleSeleccionado);
        pantallaCrearEstructuraDeProducto.getTipoMateriaPrimaProductoComponete().setSelectedItem(detalle.getTipo());
        cargarListaDeMatPrimProdComp();
        pantallaCrearEstructuraDeProducto.getMatPrimProdComp().setSelectedItem(detalle.getMaestroArticulo());
        cargarUnidadDeMedida();
        pantallaCrearEstructuraDeProducto.getCantidadTextBox().setText(String.valueOf(detalle.getCantidad()));
        
    }

    public void cancelar() {
        pantallaCrearEstructuraDeProducto.getEditar().setEnabled(false);
        pantallaCrearEstructuraDeProducto.getQuitar().setEnabled(false);
        pantallaCrearEstructuraDeProducto.getCantidadTextBox().setText("");
        pantallaCrearEstructuraDeProducto.getUnidadDeMedida().setText(" ");
        pantallaCrearEstructuraDeProducto.getMatPrimProdComp().setSelectedItem("Materia prima");
        cargarListaDeMatPrimProdComp();
    }

    public void quitar() {
        MaestroDeEstructuraDeProducto estructura = experto.quitarDetalle((DetalleEstructuraDeProducto)((ModeloTablaPantallaEstructuraDeProducto)pantallaCrearEstructuraDeProducto.getTablaDeMatPrimProdComp().getModel()).getRow(detalleSeleccionado));
        actualizarPantalla(estructura);
    }

    public void guardarEstructura() {
        experto.guardarEstructura();
    }
    


}
    
    

