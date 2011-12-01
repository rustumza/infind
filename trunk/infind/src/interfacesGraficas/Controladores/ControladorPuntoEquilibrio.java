/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.ModeloTablas.ModeloTablaPuntoEquilibrio;
import interfacesGraficas.PantallaPuntoDeEquilibrio;

/**
 *
 * @author eduardo
 */
public class ControladorPuntoEquilibrio {

    ControladorPantallaMadre controlador;
    PantallaPuntoDeEquilibrio pantallaPuntoEquil;
    ModeloTablaPuntoEquilibrio modeloTablaPuntoEqui;
    public ControladorPuntoEquilibrio(ControladorPantallaMadre control) {
    
    
        controlador = control;
    }

    
    
    void iniciar() {
        pantallaPuntoEquil  = new PantallaPuntoDeEquilibrio(controlador.getPantalla(), true, this);
        modeloTablaPuntoEqui = new ModeloTablaPuntoEquilibrio();
        pantallaPuntoEquil.getTablaPuntoEquilibrio().setModel(modeloTablaPuntoEqui);
        pantallaPuntoEquil.setLocationRelativeTo(null);
        pantallaPuntoEquil.setVisible(true);
    }
    
}
