/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Fabricas.FabricaExpertos;
import expertos.Experto;
import interfacesGraficas.PantallaABMCostosFijos;

/**
 *
 * @author eduardo
 */
public class ControladorABMCostosFijos {
    
    PantallaABMCostosFijos pantallaABMCostosFijos;
    ControladorPantallaMadre controladorPantMadre;
    private final Experto expertoABMCostosFijos;

    public ControladorABMCostosFijos(ControladorPantallaMadre controlador) {
        
        controladorPantMadre = controlador;
        pantallaABMCostosFijos = new PantallaABMCostosFijos(controladorPantMadre.getPantalla(), true, this);
        expertoABMCostosFijos = FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_COSTOS_FIJOS);
        
    }
    
    
    
    
    
}
