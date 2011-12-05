/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOCGI;
import expertos.ExpertoGestionDeInventarios;
import interfacesGraficas.ModeloTablas.ModeloTablaPantallaGestionDeInventariosParaTodosLosProductos;
import interfacesGraficas.PantallaGestionDeInventariosParaTodosLosProductos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rustu
 */
public class ControladorGestionDeInventariosParaTodosLosProductos {
 
    ControladorPantallaMadre controladorPantallaMadre;
    PantallaGestionDeInventariosParaTodosLosProductos pantalla;
    ExpertoGestionDeInventarios experto;
    
    ControladorGestionDeInventariosParaTodosLosProductos(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoGestionDeInventarios();
    }
    
    public void iniciar(){
        pantalla = new PantallaGestionDeInventariosParaTodosLosProductos(controladorPantallaMadre.getPantalla(), false, this);                
        ModeloTablaPantallaGestionDeInventariosParaTodosLosProductos mod = new ModeloTablaPantallaGestionDeInventariosParaTodosLosProductos();
        List<DTOCGI> lista = experto.calculoDeCostoDeGestionDeInventariosDeTodosLosProductos();
        mod.setListaElementos(lista);
        pantalla.getTabla().setModel(mod);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
    
    }
    
}
