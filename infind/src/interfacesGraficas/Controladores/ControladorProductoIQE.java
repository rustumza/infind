/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Numerador;
import Entidades.ProductoTipoIQE;
import expertos.ExpertoProductoIQE;
import interfacesGraficas.PantallaCrearProductoIQE;
import interfacesGraficas.PantallaEditarProductoIQE;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ControladorProductoIQE {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearProductoIQE pantallaCrearProductoIQE;
    PantallaEditarProductoIQE pantallaEditarProductoIQE;
    ExpertoProductoIQE experto;
    
    ControladorProductoIQE(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoProductoIQE();
    }

    public void crearProductoIQE() {
        pantallaCrearProductoIQE = new PantallaCrearProductoIQE(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearProductoIQE.setLocationRelativeTo(null);
        pantallaCrearProductoIQE.setVisible(true);
        
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioNumerador.add(Restrictions.eq("codificacion", "3.2."));//TODO: buscar el numero de las iqe
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        String codifica = numeroDisponibles.get(0).getCodificacion();
        String num = numeroDisponibles.get(0).getUltimaClasificacion();
        int nume = Integer.parseInt(num);
        nume = nume + 1;
        num = String.valueOf(nume);
        String nuevoNumero = codifica + num;
        pantallaCrearProductoIQE.getCodigoTextBox().setText(nuevoNumero);
        
        
    }

    public void guardar() {
        
        //TODO bloquear botones de gaurdar, cancear. etc
        
        ProductoTipoIQE proIQE = new ProductoTipoIQE();
        proIQE.setCodigo(pantallaCrearProductoIQE.getCodigoTextBox().getText());
        if(pantallaCrearProductoIQE.getNombreTextBox().getText().isEmpty() | pantallaCrearProductoIQE.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaCrearProductoIQE, "Debe ingresar un nombre para el producto IQE", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearProductoIQE.getNombreTextBox().requestFocus();
                return;
        }else{
            proIQE.setNombre(pantallaCrearProductoIQE.getNombreTextBox().getText());
        }
        proIQE.setDescripcion(pantallaCrearProductoIQE.getDescripcionTextArea().getText());
        proIQE.setUnidadDeMedida((String)pantallaCrearProductoIQE.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        proIQE.setCategoria(((String)pantallaCrearProductoIQE.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        try{
            proIQE.setCostoEstandar(Float.valueOf(pantallaCrearProductoIQE.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoIQE, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoIQE.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            proIQE.setCostoUnitarioPorOmision(Float.valueOf(pantallaCrearProductoIQE.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoIQE, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoIQE.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            proIQE.setPrecioBase(Float.valueOf(pantallaCrearProductoIQE.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoIQE, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoIQE.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaCrearProductoIQE.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            proIQE.setEliminado(false);
        }else{
            proIQE.setEliminado(true);
            proIQE.setFechaEntrarEnActividad(pantallaCrearProductoIQE.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            proIQE.setTamanioLoteEstandar(Float.valueOf(pantallaCrearProductoIQE.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoIQE, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoIQE.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        proIQE.setUbicacionEnAlmacen(pantallaCrearProductoIQE.getUbicacionAlamcenTextBox().getText());
        proIQE.setObservacion(pantallaCrearProductoIQE.getObservacionTextArea().getText());
        proIQE.setTipoInventario(null);
        proIQE.setCostoDeAlmacenamiento((float)0);
        proIQE.setCostoDePedido((float)0);
        proIQE.setPuntoDePedido((float)0);
        proIQE.setStockDeSeguridad((float)0);
        proIQE.setStockMaximoInventarioIntervaloFijo((float)0);
        experto.guardar(proIQE);
        
        
        JOptionPane.showMessageDialog(pantallaCrearProductoIQE, "Producto IQE guardado con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editarProductoIQE(String codigoPIQE){
        ProductoTipoIQE proIQE = experto.buscarProductoIQE(codigoPIQE);
        pantallaEditarProductoIQE = new PantallaEditarProductoIQE(controladorPantallaMadre.getPantalla(), false, this);
        cargarDatosEnPantallaEditar(proIQE);
        pantallaEditarProductoIQE.setVisible(true);
    
    }
    
    public void eliminar() {
        
    }

    private void cargarDatosEnPantallaEditar(ProductoTipoIQE pIQE) {
        pantallaEditarProductoIQE.getCodigoTextBox().setText(pIQE.getCodigo());
        pantallaEditarProductoIQE.getNombreTextBox().setText(pIQE.getNombre());
        pantallaEditarProductoIQE.getDescripcionTextArea().setText(pIQE.getDescripcion());
        pantallaEditarProductoIQE.getUnidadDeMedidaListBox().setSelectedItem(pIQE.getUnidadDeMedida());
        pantallaEditarProductoIQE.getCategoriaListBox().setSelectedItem(pIQE.getCategoria());
        pantallaEditarProductoIQE.getCostoEstandarTextBox().setText(String.valueOf(pIQE.getCostoEstandar()));
        pantallaEditarProductoIQE.getCostoUnitarioPorOmisionTextBox().setText(String.valueOf(pIQE.getCostoUnitarioPorOmision()));
        pantallaEditarProductoIQE.getPrecioBaseTextBox().setText(String.valueOf(pIQE.getPrecioBase()));
        if(pIQE.getEliminado()){
            pantallaEditarProductoIQE.getEstadoListBox().setSelectedItem("Inactivo");
            if(pIQE.getFechaEntrarEnActividad() != null){
                pantallaEditarProductoIQE.getEstadoEntrarEnActividadEnFechajDateChooser().setDate(pIQE.getFechaEntrarEnActividad());
            }
        }else{
            pantallaEditarProductoIQE.getEstadoListBox().setSelectedItem("Activo");
        }
        pantallaEditarProductoIQE.getTamanioLoteEstandarTextBox().setText(String.valueOf(pIQE.getTamanioLoteEstandar()));
        pantallaEditarProductoIQE.getUbicacionAlamcenTextBox().setText(pIQE.getUbicacionEnAlmacen());
        pantallaEditarProductoIQE.getObservacionTextArea().setText(pIQE.getObservacion());

    }
    
    
    public void editar() {
        ProductoTipoIQE pIQE = experto.getProductoIQE();
        //TODO bloquear botones de gaurdar, cancear. etc
        if(pantallaEditarProductoIQE.getNombreTextBox().getText().isEmpty() | pantallaEditarProductoIQE.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaEditarProductoIQE, "Debe ingresar un nombre para el producto IQE", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaEditarProductoIQE.getNombreTextBox().requestFocus();
                return;
        }else{
            pIQE.setNombre(pantallaEditarProductoIQE.getNombreTextBox().getText());
        }
        pIQE.setDescripcion(pantallaEditarProductoIQE.getDescripcionTextArea().getText());
        pIQE.setUnidadDeMedida((String)pantallaEditarProductoIQE.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        pIQE.setCategoria(((String)pantallaEditarProductoIQE.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        try{
            pIQE.setCostoEstandar(Float.valueOf(pantallaEditarProductoIQE.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoIQE, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoIQE.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            pIQE.setCostoUnitarioPorOmision(Float.valueOf(pantallaEditarProductoIQE.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoIQE, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoIQE.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            pIQE.setPrecioBase(Float.valueOf(pantallaEditarProductoIQE.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoIQE, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoIQE.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaEditarProductoIQE.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            pIQE.setEliminado(false);
        }else{
            pIQE.setEliminado(true);
            pIQE.setFechaEntrarEnActividad(pantallaEditarProductoIQE.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            pIQE.setTamanioLoteEstandar(Float.valueOf(pantallaEditarProductoIQE.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoIQE, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoIQE.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        pIQE.setUbicacionEnAlmacen(pantallaEditarProductoIQE.getUbicacionAlamcenTextBox().getText());
        pIQE.setObservacion(pantallaEditarProductoIQE.getObservacionTextArea().getText());
        experto.editar(pIQE);
        
        
        JOptionPane.showMessageDialog(pantallaEditarProductoIQE, "Producto IQE guardado con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
