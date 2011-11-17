/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MaestroDeArticulo;
import expertos.ExpertoPedidoAproveedores;
import interfacesGraficas.PantallaCrearPedidoAProveedores;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorPedidoAProveedor {
    
    ControladorPantallaMadre controladorPantallaMadre;
    ExpertoPedidoAproveedores experto;   
    PantallaCrearPedidoAProveedores pantallaCrearPedidoAProveedor;
    
    public ControladorPedidoAProveedor(ControladorPantallaMadre cont) {
        
        controladorPantallaMadre = cont;
        experto = new ExpertoPedidoAproveedores();        
        
    }
    
    public void iniciar(){
        pantallaCrearPedidoAProveedor = new PantallaCrearPedidoAProveedores(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearPedidoAProveedor.setVisible(true);
    }

    public void buscarProducto() {
        
        String codigo = pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().getText();
        MaestroDeArticulo articulo = null;
        if(!codigo.equals("")){
            
            if(((String)pantallaCrearPedidoAProveedor.getTipoProductoListBox().getSelectedItem()).equals("Materia prima")){
                articulo = experto.buscarMateriaPrima(codigo);

            }else{
                articulo = experto.buscarProductoComponente(codigo);
            }
            if(articulo == null){
                JOptionPane.showMessageDialog(pantallaCrearPedidoAProveedor, "No se ha encontrado ningún producto con ese código", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearPedidoAProveedor.getCodigoProductoTextBox().requestFocus();
                return;
            }
            pantallaCrearPedidoAProveedor.getProductoSeleccionadoTextBox().setText(articulo.getNombre());
        }

        
    }
    
}
