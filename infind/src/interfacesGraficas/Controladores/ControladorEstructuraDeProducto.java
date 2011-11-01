/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import expertos.ExpertoEstructuraDeProducto;
import interfacesGraficas.PantallaCrearEstructuraDeProducto;

/**
 *
 * @author rustu
 */
public class ControladorEstructuraDeProducto {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearEstructuraDeProducto pantallaCrearEstructuraDeProducto;
    ExpertoEstructuraDeProducto experto;
    
    public ControladorEstructuraDeProducto(ControladorPantallaMadre cont) {
        
        controladorPantallaMadre = cont;
        experto = new ExpertoEstructuraDeProducto();
    
    }
    
    public void crearEstructuraDeProducto(){
        pantallaCrearEstructuraDeProducto = new PantallaCrearEstructuraDeProducto(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearEstructuraDeProducto.setVisible(true);
    
    }

    public void buscarProducto() {
        String tipo = (String)(pantallaCrearEstructuraDeProducto.getTipoProductoListBox().getModel().getSelectedItem());
        String codigo = pantallaCrearEstructuraDeProducto.getCodigoProductoTextBox().getText();
        
        if(tipo.equals("Producto final")){
            //experto.buscarProductoFinal(codigo);
            
        }else if(tipo.equals("Producto IQE")){
            pantallaCrearEstructuraDeProducto.getColorModel();
        }else if(tipo.equals("Prodcuto intermedio")){
            pantallaCrearEstructuraDeProducto.getColorModel();
        
        }
    }
            
}
    
    

