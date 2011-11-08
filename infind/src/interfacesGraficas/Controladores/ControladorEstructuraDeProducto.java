/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;
import Entidades.DetalleEstructuraDeProducto;
import Entidades.ProductosFabricables;
import expertos.ExpertoEstructuraDeProducto;
import interfacesGraficas.ModeloTablas.ModeloTablaPantallaEstructuraDeProducto;
import interfacesGraficas.PantallaCrearEstructuraDeProducto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author rustu
 */
public class ControladorEstructuraDeProducto {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearEstructuraDeProducto pantallaCrearEstructuraDeProducto;
    ExpertoEstructuraDeProducto experto;
    
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
        ProductosFabricables prodFab = null;
        
        if(tipo.equals("Producto final")){
            
            prodFab = experto.buscarProductoFinal(codigo);
            
        }else if(tipo.equals("Producto IQE")){
            
            prodFab = experto.buscarProductoIQE(codigo);
            
        }else if(tipo.equals("Prodcuto intermedio")){
            
            prodFab = experto.buscarProductoIntermedio(codigo);
        
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
    
    
    }   
            
    


}
    
    

