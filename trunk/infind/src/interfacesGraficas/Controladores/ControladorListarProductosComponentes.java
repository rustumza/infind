/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.ProductoComponente;
import interfacesGraficas.ModeloTablas.ModeloTablaListarProductosComponentes;
import interfacesGraficas.PantallaListarProductosComponentes;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;



/**
 *
 * @author diego
 */
public class ControladorListarProductosComponentes {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaListarProductosComponentes pantalla;
    
    
    public ControladorListarProductosComponentes(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaListarProductosComponentes(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setVisible(true);
        recargarTabla();
    }

    public void editarProductosComponentes(int pcSeleccionado) {
       String codigoProductoComponente = (String) pantalla.getTablaProductosComponentesEncontrados().getModel().getValueAt(pcSeleccionado, 0);
       //TODO: llamar al controlador de edicion de ProductosComponentes con el codigo obtenido
        System.out.println("me hicieron doble clic");
    }

    public void recargarTabla() {
        
        List<ProductoComponente> productoComponenteEncontrado = null;
        Criteria criterioCentro = null;
        
        if(pantalla.getMostrarProductosComponentesEliminadosCheckBox().isSelected()){
            criterioCentro = Fachada.getInstancia().crearCriterioSinEliminado(ProductoComponente.class);
        }else{
            criterioCentro = Fachada.getInstancia().crearCriterio(ProductoComponente.class);
        }
        
        productoComponenteEncontrado = Fachada.getInstancia().buscar(ProductoComponente.class, criterioCentro);
        ModeloTablaListarProductosComponentes mod = new ModeloTablaListarProductosComponentes();
        mod.setListaElementos(productoComponenteEncontrado);
        pantalla.getTablaProductosComponentesEncontrados().setModel(mod);     
    }     
}