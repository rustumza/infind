/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MateriaPrima;
import interfacesGraficas.ModeloTablas.ModeloTablaListarMateriasPrimas;
import interfacesGraficas.PantallaListarMateriaPrima;
import java.util.List;
import org.hibernate.Criteria;
import persistencia.Fachada;



/**
 *
 * @author diego
 */
public class ControladorListarMateriasPrimas {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaListarMateriaPrima pantalla;
    
    
    public ControladorListarMateriasPrimas(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaListarMateriaPrima(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setVisible(true);
        recargarTabla();
    }

    public void editarMateriaPrima(int matePrimSeleccionada) {
       String codigoMateriaPrima = (String) pantalla.getTablaMateriasPrimasEncontradas().getModel().getValueAt(matePrimSeleccionada, 0);
       //TODO: llamar al controlador de edicion de materia prima con el codigo obtenido
        System.out.println("me hicieron doble clic");
    }

    public void recargarTabla() {
        
        List<MateriaPrima> materiaPrimaEncontrada = null;
        Criteria criterioCentro = null;
        
        if(pantalla.getMostrarMateriasPrimasEliminadasCheckBox().isSelected()){
            Fachada.getInstancia().crearCriterio(MateriaPrima.class);
        }else{
            Fachada.getInstancia().crearCriterioSinEliminado(MateriaPrima.class);
        }
        
        materiaPrimaEncontrada = Fachada.getInstancia().buscar(MateriaPrima.class, criterioCentro);
        ModeloTablaListarMateriasPrimas mod = new ModeloTablaListarMateriasPrimas();
        mod.setListaElementos(materiaPrimaEncontrada);
        pantalla.getTablaMateriasPrimasEncontradas().setModel(mod);     
    }     
}