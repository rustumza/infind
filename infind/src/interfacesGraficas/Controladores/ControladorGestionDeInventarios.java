/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOGestionDeInventario;
import Entidades.MaestroDeArticulo;
import Entidades.ProductoIntermedio;
import expertos.ExpertoGestionDeInventarios;
import interfacesGraficas.PantallaGestionDeInventarios;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorGestionDeInventarios {
    
    
    private ControladorPantallaMadre controladorPantallaMadre;
    private PantallaGestionDeInventarios pantalla;
    private ExpertoGestionDeInventarios experto;
    private Controlador contDeRetorno;
    private String[] fabInt = {"Fabricacion interna"};
    private String[] otroInv = { "Intervalo fijo", "Lote fijo"};
    
    ControladorGestionDeInventarios(ControladorPantallaMadre contPantMad, Controlador contr) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoGestionDeInventarios();
        contDeRetorno = contr;
    }
    
    public void iniciar(MaestroDeArticulo articulo){
        if(articulo.getDemanda() == null | articulo.getDemanda().isEmpty()){
            JOptionPane.showMessageDialog(pantalla, "El articulo no tiene demandas cargadas", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        pantalla = new PantallaGestionDeInventarios(controladorPantallaMadre.getPantalla(), true, this);
        if(articulo.getClass().equals(ProductoIntermedio.class)){
            pantalla.getTipoInventarioComboBox().setModel(new DefaultComboBoxModel(fabInt));
            pantalla.getTipoInventarioComboBox().setSelectedItem("Fabricacion interna");
            pantalla.getTipoInventarioComboBox().setEnabled(false);
        
        }else{
            pantalla.getTipoInventarioComboBox().setModel(new DefaultComboBoxModel(otroInv));
            pantalla.getTipoInventarioComboBox().setEnabled(true);
        
        }
        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(true);
        experto.setArticulo(articulo);
        
    }

    public void guardar() {
        DTOGestionDeInventario dto = new DTOGestionDeInventario();
        dto.setTipoInventario((String)pantalla.getTipoInventarioComboBox().getSelectedItem());
        try{
            dto.setCostoDeAlmacenamiento(Float.valueOf(pantalla.getCostoAlmacenamientoTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantalla, "Costo de almacenamiento incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantalla.getCostoAlmacenamientoTextBox().requestFocus();
            return;
        }
        try{
            dto.setCostoDePedido((Float.valueOf(pantalla.getCostoDePedidoTextBox().getText())));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantalla, "Costo de pedido incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantalla.getCostoDePedidoTextBox().requestFocus();
            return;
        }
        
        experto.calcularInventario(dto);
        contDeRetorno.actualizarPorGestionDeInventarios();
        JOptionPane.showMessageDialog(pantalla, "El lote optimo es: " + experto.getArticulo().getTamanioLoteEstandar(), "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
        pantalla.dispose();
        
        
    }
    
}
