/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MaestroDeArticulo;
import expertos.ExpertoGestionDeInventarios;
import interfacesGraficas.ModeloTablas.ModeloTablaProductosPorDebajoDelStockDeSeguridad;
import interfacesGraficas.PantallaProductosPorDebajoDelPuntoDePedido;
import interfacesGraficas.PantallaProductosPorDebajoDelStockMinimo;
import java.util.List;

/**
 *
 * @author rustu
 */
public class ControladorProductosQueTieneElStockDebajoDelNivelDeSeguridad {
    
        ControladorPantallaMadre controladorPantallaMadre;
        PantallaProductosPorDebajoDelStockMinimo pantalla;
        ExpertoGestionDeInventarios experto;
    
    ControladorProductosQueTieneElStockDebajoDelNivelDeSeguridad(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoGestionDeInventarios();
    }
    
    public void iniciar(){
        pantalla = new PantallaProductosPorDebajoDelStockMinimo(controladorPantallaMadre.getPantalla(), false, this);                
        ModeloTablaProductosPorDebajoDelStockDeSeguridad mod = new ModeloTablaProductosPorDebajoDelStockDeSeguridad();
        List<MaestroDeArticulo> lista = experto.buscarProductosPorDebajoDelStockDeSeguridad();
        mod.setListaElementos(lista);
        pantalla.getTabla().setModel(mod);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
    
    }
    
    
}
