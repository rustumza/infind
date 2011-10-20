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
    

    
    public ControladorCentroDeTrabajo(ControladorPantallaMadre contrPantMadre) {
        pantallacrearcentro = new PantallaCrearCentro(contrPantMadre.get, false);
        
        
    }

    public void crearCentro() {
        
        iniciar();
    }
    
    public void iniciar(){
        pantallacrearcentro.setVisible(true);
        pantallacrearcentro.setLocationRelativeTo(null);
    }
    
}
