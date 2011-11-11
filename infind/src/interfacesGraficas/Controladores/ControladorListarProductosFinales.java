/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.ProductoFinal;
import interfacesGraficas.ModeloTablas.ModeloTablaListarProductosFinales;
import interfacesGraficas.PantallaListarProductosFinales;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;



/**
 *
 * @author diego
 */
public class ControladorListarProductosFinales {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaListarProductosFinales pantalla;
    
    
    public ControladorListarProductosFinales(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaListarProductosFinales(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
        recargarTabla();
    }

    public void editarProductosFinales(int pfinalSeleccionado) {
       String codigoProductoFinal = (String) pantalla.getTablaProductosFinalesEncontrados().getModel().getValueAt(pfinalSeleccionado, 0);
       new ControladorProductoFinal(controladorPantallaMadre).editarProductoFinal(codigoProductoFinal);
    }

    public void recargarTabla() {
        
        List<ProductoFinal> productoFinalEncontrado = null;
        Criteria criterioCentro = null;
        
        if(pantalla.getMostrarProductosFinalesEliminadosCheckBox().isSelected()){
            criterioCentro = Fachada.getInstancia().crearCriterioSinEliminado(ProductoFinal.class);
        }else{
            criterioCentro = Fachada.getInstancia().crearCriterio(ProductoFinal.class);
        }
        
        productoFinalEncontrado = Fachada.getInstancia().buscar(ProductoFinal.class, criterioCentro);
        ModeloTablaListarProductosFinales mod = new ModeloTablaListarProductosFinales();
        mod.setListaElementos(productoFinalEncontrado);
        pantalla.getTablaProductosFinalesEncontrados().setModel(mod);     
    }     
}
