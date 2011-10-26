/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MateriaPrima;
import expertos.ExpertoMateriaPrima;
import interfacesGraficas.PantallaCrearMateriaPrima;
import javax.swing.JOptionPane;

/**
 *
 * @author rustu
 */
public class ControladorMateriaPrima {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearMateriaPrima pantallaCrearMateriPrima;
    ExpertoMateriaPrima experto;
    
    ControladorMateriaPrima(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoMateriaPrima();
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
        try{
            matPrim.setCostoEstandar(Float.valueOf(pantallaCrearMateriPrima.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearMateriPrima, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearMateriPrima.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            matPrim.setCostoUnitarioPorOmision(Float.valueOf(pantallaCrearMateriPrima.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearMateriPrima, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearMateriPrima.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            matPrim.setPrecioBase(Float.valueOf(pantallaCrearMateriPrima.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearMateriPrima, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearMateriPrima.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaCrearMateriPrima.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            matPrim.setEliminado(false);
        }else{
            matPrim.setEliminado(true);
            matPrim.setFechaEntrarEnActividad(pantallaCrearMateriPrima.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            matPrim.setTamanioLoteEstandar(Integer.valueOf(pantallaCrearMateriPrima.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearMateriPrima, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearMateriPrima.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        matPrim.setUbicacionEnAlmacen(pantallaCrearMateriPrima.getUbicacionAlamcenTextBox().getText());
        matPrim.setObservacion(pantallaCrearMateriPrima.getObservacionTextArea().getText());
        
        experto.guardar(matPrim);
        
    }
    
}
