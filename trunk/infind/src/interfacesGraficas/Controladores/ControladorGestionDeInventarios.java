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
            if(articulo.getCategoria() == 'A'){
                pantalla.getTipoInventarioComboBox().setSelectedItem("Lote fijo");
            }else if(articulo.getCategoria() == 'B'){
                pantalla.getTipoInventarioComboBox().setSelectedItem("Lote fijo");
            }else{
                pantalla.getTipoInventarioComboBox().setSelectedItem("Intervalo fijo");
            }
            eventoComboBox();
        
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
        if(pantalla.getTipoInventarioComboBox().getSelectedItem().equals("Intervalo fijo")){
            try{
                dto.setCantidadMaximaDeStock(Float.valueOf(pantalla.getCantidadMaximaTextBox().getText())); 
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(pantalla, "Stock máximo incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantalla.getCantidadMaximaTextBox().requestFocus();
                return;
            }                    
        }
        
        experto.calcularInventario(dto);
    
        JOptionPane.showMessageDialog(pantalla, "Información generada correctamente", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
        
        
        
    }
    
    public void actualizarOtrasPantallas(){
        contDeRetorno.actualizarPorGestionDeInventarios();
        
    }

    public void eventoComboBox() {
        if(pantalla.getTipoInventarioComboBox().getSelectedItem().equals("Intervalo fijo")){
            pantalla.getCantidadMaximaLabel().setVisible(true);
            pantalla.getCantidadMaximaTextBox().setVisible(true);
        }else{
            pantalla.getCantidadMaximaLabel().setVisible(false);
            pantalla.getCantidadMaximaTextBox().setVisible(false);
        }
    }
    
}
