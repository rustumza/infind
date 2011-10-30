/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.Controladores;

import expertos.ExpertoRutaDeFabricacion;
import interfacesGraficas.ModeloTablas.ModeloTablaEtapaRutaAgregada;
import interfacesGraficas.PantallaCrearOperario;
import interfacesGraficas.PantallaCrearRutaDeFabricacion;
import interfacesGraficas.PantallaMadre;

/**
 *
 * @author eduardo
 */
public class ControladorCrearRutaDeFabricacion {


    PantallaCrearRutaDeFabricacion pantallaCrearRutaFabricacion;
    PantallaMadre pantallaMadre;
    ExpertoRutaDeFabricacion expertoRutaFabricacion;
    ControladorPantallaMadre controladorPantallaMadre;
    ModeloTablaEtapaRutaAgregada modeloTAblaEtapaAgregada;

    public ControladorCrearRutaDeFabricacion(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre= controladorPantallaMadre.getPantalla();
    }


    public void crearRutaFabricacion(){


        pantallaCrearRutaFabricacion = new PantallaCrearRutaDeFabricacion(pantallaMadre, false, this);
        modeloTAblaEtapaAgregada = new ModeloTablaEtapaRutaAgregada();
        pantallaCrearRutaFabricacion.getTablaEtapasAgregadas().setModel(modeloTAblaEtapaAgregada);
        pantallaCrearRutaFabricacion.setVisible(true);
        pantallaCrearRutaFabricacion.setLocationRelativeTo(null);

    }



}
