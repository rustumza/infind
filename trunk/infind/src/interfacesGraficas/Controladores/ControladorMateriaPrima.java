/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MateriaPrima;
import interfacesGraficas.PantallaCrearMateriaPrima;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorMateriaPrima {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearMateriaPrima pantallaCrearMateriPrima;
    
    ControladorMateriaPrima(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
    }

    public void crearMateriaPrima() {
        pantallaCrearMateriPrima = new PantallaCrearMateriaPrima(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearMateriPrima.setVisible(true);
    }

    public void guardar() {
        
        //TODO bloquear botones de gaurdar, cancear. etc
        
        MateriaPrima matPrim = new MateriaPrima();
        matPrim.setCodigo(pantallaCrearMateriPrima.getCodigoTextBox().getText());
        if(pantallaCrearMateriPrima.getNombreTextBox().getText().isEmpty() | pantallaCrearMateriPrima.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaCrearMateriPrima, "Debe ingresar un nombre para la materia prima", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearMateriPrima.getNombreTextBox().requestFocus();
                return;
        }else{
            matPrim.setNombre(pantallaCrearMateriPrima.getNombreTextBox().getText());
        }
        matPrim.setDescripcion(pantallaCrearMateriPrima.getDescripcionTextArea().getText());
        matPrim.setUnidadDeMedida((String)pantallaCrearMateriPrima.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        matPrim.setCategoria(((String)pantallaCrearMateriPrima.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        
    }
    
}
