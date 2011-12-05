/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOIndices;
import expertos.ExpertoABMIndices;
import interfacesGraficas.ModeloTablas.ModeloTablaIndicesAnormales;
import interfacesGraficas.indices.PantallaIndicesAnormales;
import java.util.List;

/**
 *
 * @author rustu
 */
public class ControladorIndicesAnormales {
    
    private ControladorPantallaMadre controladorPantallaMadre;
    private PantallaIndicesAnormales pantalla;
    private ExpertoABMIndices experto;
    
    
    public ControladorIndicesAnormales(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        experto = new ExpertoABMIndices();
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaIndicesAnormales(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setLocationRelativeTo(null);
        ModeloTablaIndicesAnormales mod = new ModeloTablaIndicesAnormales();
        List<DTOIndices> lista = experto.buscarIndicesParaPantalla();
        mod.setListaElementos(lista);
        pantalla.getTabla().setModel(mod);
        pantalla.setVisible(true);
    }
    
}
