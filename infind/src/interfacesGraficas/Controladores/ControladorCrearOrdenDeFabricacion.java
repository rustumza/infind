/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaCrearOrdenDeFabricacion;

/**
 *
 * @author rustu
 */
public class ControladorCrearOrdenDeFabricacion {
    
    
    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearOrdenDeFabricacion pantalla;
    
    
    public ControladorCrearOrdenDeFabricacion(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaCrearOrdenDeFabricacion(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
    }
    
    
}
