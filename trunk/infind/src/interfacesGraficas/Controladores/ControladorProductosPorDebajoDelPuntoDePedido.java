/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MaestroDeArticulo;
import expertos.ExpertoGestionDeInventarios;
import interfacesGraficas.ModeloTablas.ModeloTablaProductosPorDebajoDelPuntoDePedido;
import interfacesGraficas.PantallaProductosPorDebajoDelPuntoDePedido;
import java.util.List;

/**
 *
 * @author rustu
 */
public class ControladorProductosPorDebajoDelPuntoDePedido {
    ControladorPantallaMadre controladorPantallaMadre;
    PantallaProductosPorDebajoDelPuntoDePedido pantalla;
    ExpertoGestionDeInventarios experto;
    
    ControladorProductosPorDebajoDelPuntoDePedido(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoGestionDeInventarios();
    }
    
    public void iniciar(){
        pantalla = new PantallaProductosPorDebajoDelPuntoDePedido(controladorPantallaMadre.getPantalla(), false, this);                
        ModeloTablaProductosPorDebajoDelPuntoDePedido mod = new ModeloTablaProductosPorDebajoDelPuntoDePedido();
        List<MaestroDeArticulo> lista = experto.buscarProductosPorDebajoDelPuntoDePedido();
        mod.setListaElementos(lista);
        pantalla.getTabla().setModel(mod);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
    
    }
    
}
