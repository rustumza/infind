/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import expertos.ExpertoGestionDeInventarios;
import interfacesGraficas.PantallaGestionDeInventarioParaUnProducto;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorGestionDeInventarioParaUnProducto {
    
    ControladorPantallaMadre controladorPantallaMadre;
    PantallaGestionDeInventarioParaUnProducto pantalla;
    ExpertoGestionDeInventarios experto;
    
    ControladorGestionDeInventarioParaUnProducto(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoGestionDeInventarios();
    }
    
    public void iniciar(){
        pantalla = new PantallaGestionDeInventarioParaUnProducto(controladorPantallaMadre.getPantalla(), false, this);                
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
    
    }
    
    

    public void buscar() {
        String codigo = pantalla.getCodigoTextBox().getText();
        float codigoFloat = (float) 0;
        if(!codigo.equals("")){
            if(pantalla.getTipoProductoComboBox().getSelectedItem().equals("Materia Prima")){
                codigoFloat = experto.calcularCGIParaMateriaPrima(codigo);
            }else if(pantalla.getTipoProductoComboBox().getSelectedItem().equals("Producto Componente")){
                codigoFloat = experto.calcularCGIParaProductoComponente(codigo);
            }else{
                codigoFloat = experto.calcularCGIParaProductoIntermedio(codigo);
            }
            if(codigoFloat<0){
                JOptionPane.showMessageDialog(pantalla, "No se ha encontrado un producto con ese codigo", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantalla.getCodigoTextBox().requestFocus();
                return;
            }else{
                pantalla.getCgiTextBox().setText(String.valueOf(codigoFloat));
            }
        }else{
            JOptionPane.showMessageDialog(pantalla, "Ingrese un codigo correcto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantalla.getCodigoTextBox().requestFocus();
            return;
            
        }
    }
    
}
