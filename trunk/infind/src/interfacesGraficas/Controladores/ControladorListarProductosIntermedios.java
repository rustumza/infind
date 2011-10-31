/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.ProductoIntermedio;
import interfacesGraficas.ModeloTablas.ModeloTablaListarProductosIntermedios;
import interfacesGraficas.PantallaListarProductosIntermedios;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;



/**
 *
 * @author diego
 */
public class ControladorListarProductosIntermedios {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaListarProductosIntermedios pantalla;
    
    
    public ControladorListarProductosIntermedios(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaListarProductosIntermedios(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setVisible(true);
        recargarTabla();
    }

    public void editarProductosIntermedios(int piSeleccionado) {
       String codigoProductoIntermedio = (String) pantalla.getTablaProductosIntermediosEncontrados().getModel().getValueAt(piSeleccionado, 0);
       //TODO: llamar al controlador de edicion de ProductosIntermedios con el codigo obtenido
        System.out.println("me hicieron doble clic");
    }

    public void recargarTabla() {
        
        List<ProductoIntermedio> productoIntermedioEncontrado = null;
        Criteria criterioCentro = null;
        
        if(pantalla.getMostrarProductosIntermediosEliminadosCheckBox().isSelected()){
            criterioCentro = Fachada.getInstancia().crearCriterioSinEliminado(ProductoIntermedio.class);
        }else{
            criterioCentro = Fachada.getInstancia().crearCriterio(ProductoIntermedio.class);
        }
        
        productoIntermedioEncontrado = Fachada.getInstancia().buscar(ProductoIntermedio.class, criterioCentro);
        ModeloTablaListarProductosIntermedios mod = new ModeloTablaListarProductosIntermedios();
        mod.setListaElementos(productoIntermedioEncontrado);
        pantalla.getTablaProductosIntermediosEncontrados().setModel(mod);     
    }     
}
