/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaCrearCentro;
import interfacesGraficas.PantallaMadre;

/**
 *
 * @author diego
 */
public class ControladorCentroDeTrabajo {
    private PantallaCrearCentro pantallacrearcentro;
    PantallaMadre pantallaMadre;
    

    
    public ControladorCentroDeTrabajo(ControladorPantallaMadre contrPantMadre) {
        pantallacrearcentro = new PantallaCrearCentro(pantallaMadre, false);
        
        
    }

    public void crearCentro() {
        
        iniciar();
    }
    
    public void iniciar(){
        pantallacrearcentro.setVisible(true);
        pantallacrearcentro.setLocationRelativeTo(null);
    }
    
}
