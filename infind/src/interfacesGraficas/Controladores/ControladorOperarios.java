/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaMadre;

/**
 *
 * @author eduardo
 */
public class ControladorOperarios {
    
    ControladorPantallaMadre controladorPantallaMadre;
    PantallaMadre pantallaMadre;

    ControladorOperarios(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();
    }
    
    
    
    public void crearOperario(){
        
    }
    
    

    public void editarOperario() {
        
    }
    
}
