/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MaestroDeCentroDeTrabajo;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCentroDeTrabajoException;
import expertos.ExpertoCentroDeTrabajo;
import interfacesGraficas.PantallaCrearCentro;
import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorCentroDeTrabajo {
    private PantallaCrearCentro pantallacrearcentro;
    PantallaMadre pantallaMadre;
    ExpertoCentroDeTrabajo expertoCentroDeTrabajo;
    MaestroDeCentroDeTrabajo centroDeTrabajoSeleccionado = null;
    
    

    
    public ControladorCentroDeTrabajo(ControladorPantallaMadre contrPantMadre) {
        pantallacrearcentro = new PantallaCrearCentro(pantallaMadre, false);
        expertoCentroDeTrabajo = (ExpertoCentroDeTrabajo) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.CENTRO_DE_TRABAJO);
        
//boton salir        
        pantallacrearcentro.getBotonSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                finalizar();
            }
        });
        
//boton guardar
        pantallacrearcentro.getBotonGuardar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guardarCentroDeTrabajo();
            }
        });
        
        
        
    }

    public ControladorCentroDeTrabajo() {
        
        
    
    }
    
    public void guardarCentroDeTrabajo(){
        
        if (centroDeTrabajoSeleccionado == null) {
            centroDeTrabajoSeleccionado = new MaestroDeCentroDeTrabajo();
        }
        
        centroDeTrabajoSeleccionado.setCodigo(pantallacrearcentro.getCampoCodigo().getText());
        centroDeTrabajoSeleccionado.setDescripcion(pantallacrearcentro.getCampoDescripcion().getText());
        centroDeTrabajoSeleccionado.setEliminado(Boolean.FALSE);
        //TODO: debo buscar el centro ingresado para saber si existe, si existe se muestra un mensaje, sino se guarda
        try {
            expertoCentroDeTrabajo.guardar(centroDeTrabajoSeleccionado);
            limpiarPantallaCentroDeTrabajo();
            centroDeTrabajoSeleccionado = null;
            JOptionPane.showMessageDialog(pantallacrearcentro, "Centro de Trabajo Guardado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            pantallacrearcentro.setVisible(false);
        } catch (ExpertoCentroDeTrabajoException ex) {
            Logger.getLogger(ControladorCentroDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void limpiarPantallaCentroDeTrabajo(){
        pantallacrearcentro.getCampoCodigo().setText("");
        pantallacrearcentro.getCampoDescripcion().setText("");
        
    }
    
    public void crearCentro() {
        
        iniciar();
    }
    
    public void iniciar(){
        pantallacrearcentro.setVisible(true);
        pantallacrearcentro.setLocationRelativeTo(null);
    }
    
    public void finalizar(){
        pantallacrearcentro.setVisible(false);
    }
    
}
