/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.ProductoTipoIQE;
import interfacesGraficas.ModeloTablas.ModeloTablaListarProductosIQE;
import interfacesGraficas.PantallaListarProductosIQE;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;



/**
 *
 * @author diego
 */
public class ControladorListarProductosIQE {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaListarProductosIQE pantalla;
    
    
    public ControladorListarProductosIQE(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaListarProductosIQE(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
        recargarTabla();
    }

    public void editarProductosIQE(int pIQESeleccionado) {
        String codigoProductoIQE = (String) pantalla.getTablaProductosIQEEncontrados().getModel().getValueAt(pIQESeleccionado, 0);
        new ControladorProductoIQE(controladorPantallaMadre).editarProductoIQE(codigoProductoIQE);
    }

    public void recargarTabla() {
        
        List<ProductoTipoIQE> productoIQEEncontrado = null;
        Criteria criterioPIQE = null;
        
        if(pantalla.getMostrarProductosIQEEliminadosCheckBox().isSelected()){
            criterioPIQE = Fachada.getInstancia().crearCriterioSinEliminado(ProductoTipoIQE.class);
        }else{
            criterioPIQE = Fachada.getInstancia().crearCriterio(ProductoTipoIQE.class);
        }
        
        productoIQEEncontrado = Fachada.getInstancia().buscar(ProductoTipoIQE.class, criterioPIQE);
        ModeloTablaListarProductosIQE mod = new ModeloTablaListarProductosIQE();
        mod.setListaElementos(productoIQEEncontrado);
        pantalla.getTablaProductosIQEEncontrados().setModel(mod);     
    }     
}
