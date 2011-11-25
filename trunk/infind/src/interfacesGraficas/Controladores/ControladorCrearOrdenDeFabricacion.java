/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MaestroDeArticulo;
import expertos.ExpertoOrdenDeFabricacion;
import interfacesGraficas.PantallaCrearOrdenDeFabricacion;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorCrearOrdenDeFabricacion {
    
    
    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearOrdenDeFabricacion pantalla;
    ExpertoOrdenDeFabricacion experto;
    
    public ControladorCrearOrdenDeFabricacion(ControladorPantallaMadre aThis) {
        controladorPantallaMadre = aThis;
        experto = new ExpertoOrdenDeFabricacion();
        
    }
    
    public void iniciar(){
    
        pantalla = new PantallaCrearOrdenDeFabricacion(controladorPantallaMadre.getPantalla(), false, this);
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
    }

    public void buscarProducto() { 
        String codigo = pantalla.getCodigoTextBox().getText();
        MaestroDeArticulo articulo;
        if(!codigo.equals("")){
            if(((String)pantalla.getTipoProducto().getSelectedItem()).equals("Producto final")){
                articulo = experto.buscarProductoFinal(codigo);
                if(articulo == null){
                    JOptionPane.showMessageDialog(pantalla, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantalla.getCodigoTextBox().requestFocus();
                    return;
                }
            }else{
                articulo = experto.buscarProductoIntermedio(codigo);
                if(articulo == null){
                    JOptionPane.showMessageDialog(pantalla, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    pantalla.getCodigoTextBox().requestFocus();
                    return;
                }
            }
            pantalla.getProductoSeleccionadoTextBox().setText(articulo.getNombre());
            pantalla.getLoteEstandarTextBox().setText(String.valueOf(articulo.getTamanioLoteEstandar()));
            pantalla.getUnidadDeMedida().setText(articulo.getUnidadDeMedida());            
        }

    }

    public void probarOrden() {
        JOptionPane.showMessageDialog(pantalla, "Esta funcionalidad le tocaba a Dios, si el no la hizo, no es culpa mia", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void generarOrden() {
        JOptionPane.showMessageDialog(pantalla, "Esta tambien le tocaba a Dios, se esta haciendo el tonto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
