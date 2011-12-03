/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Fabricas.FabricaExpertos;
import excepciones.ExpertoCostosFijosException;
import expertos.ExpertoParametros;
import interfacesGraficas.PantallaParametros;

/**
 *
 * @author diego
 */
public class ControladorParametros {

    PantallaParametros pantallaParametros;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoParametros expertoParametros;

    public ControladorParametros(ControladorPantallaMadre controlador) {
        controladorPantMadre = controlador;
        expertoParametros = (ExpertoParametros) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.PARAMETROS);
    }

    public void iniciar() {
        pantallaParametros = new PantallaParametros(controladorPantMadre.getPantalla(), false, this);
        pantallaParametros.setLocationRelativeTo(null);
        pantallaParametros.setVisible(true);
    }
}
