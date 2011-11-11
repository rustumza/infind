/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Proveedor;
import interfacesGraficas.ModeloTablas.ModeloTablaListarProveedores;
import interfacesGraficas.PantallaListarProveedores;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;



/**
 *
 * @author diego
 */
public class ControladorListarProveedores {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaListarProveedores pantalla;
    
    
    public ControladorListarProveedores(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaListarProveedores(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
        recargarTabla();
    }

    public void editarProveedores(int proveedorSeleccionado) {
       String nombreProveedor = (String) pantalla.getTablaProveedores().getModel().getValueAt(proveedorSeleccionado, 0);
       new ControladorProveedores(controladorPantallaMadre).editarProveedores(nombreProveedor);
    }

    public void recargarTabla() {
        
        List<Proveedor> proveedorEncontrada = null;
        Criteria criterioCentro = null;
        
            criterioCentro = Fachada.getInstancia().crearCriterio(Proveedor.class);
        
        proveedorEncontrada = Fachada.getInstancia().buscar(Proveedor.class, criterioCentro);
        ModeloTablaListarProveedores mod = new ModeloTablaListarProveedores();
        mod.setListaElementos(proveedorEncontrada);
        pantalla.getTablaProveedores().setModel(mod);     
    }     
}