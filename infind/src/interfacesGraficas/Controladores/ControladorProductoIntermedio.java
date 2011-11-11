/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.ProductoIntermedio;
import Entidades.Numerador;
import Entidades.ProductoTipoIQE;
import expertos.ExpertoProductoIntermedio;
import interfacesGraficas.PantallaCrearProductoIntermedio;
import interfacesGraficas.PantallaEditarProductoIntermedio;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ControladorProductoIntermedio {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearProductoIntermedio pantallaCrearProductoIntermedio;
    PantallaEditarProductoIntermedio pantallaEditarProductoIntermedio;
    ExpertoProductoIntermedio experto;
    
    ControladorProductoIntermedio(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoProductoIntermedio();
    }

    public void crearProductoIntermedio() {
        pantallaCrearProductoIntermedio = new PantallaCrearProductoIntermedio(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearProductoIntermedio.setLocationRelativeTo(null);
        pantallaCrearProductoIntermedio.setVisible(true);
        
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioNumerador.add(Restrictions.eq("codificacion", "3.1."));
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        String codifica = numeroDisponibles.get(0).getCodificacion();
        String num = numeroDisponibles.get(0).getUltimaClasificacion();
        int nume = Integer.parseInt(num);
        nume = nume + 1;
        num = String.valueOf(nume);
        String nuevoNumero = codifica + num;
        pantallaCrearProductoIntermedio.getCodigoTextBox().setText(nuevoNumero);
        List prodIQE = experto.buscarProductosIQE();
        pantallaCrearProductoIntermedio.getProductoIQERelacionadoListBox().setModel(new DefaultComboBoxModel(prodIQE.toArray()));
        
    }

    public void guardar() {
        
        //TODO bloquear botones de guardar, cancelar. etc
        
        ProductoIntermedio proInter = new ProductoIntermedio();
        proInter.setCodigo(pantallaCrearProductoIntermedio.getCodigoTextBox().getText());
        if(pantallaCrearProductoIntermedio.getNombreTextBox().getText().isEmpty() | pantallaCrearProductoIntermedio.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaCrearProductoIntermedio, "Debe ingresar un nombre para el producto intermedio", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearProductoIntermedio.getNombreTextBox().requestFocus();
                return;
        }else{
            proInter.setNombre(pantallaCrearProductoIntermedio.getNombreTextBox().getText());
        }
        proInter.setDescripcion(pantallaCrearProductoIntermedio.getDescripcionTextArea().getText());
        proInter.setUnidadDeMedida((String)pantallaCrearProductoIntermedio.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        proInter.setCategoria(((String)pantallaCrearProductoIntermedio.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        try{
            proInter.setCostoEstandar(Float.valueOf(pantallaCrearProductoIntermedio.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoIntermedio, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoIntermedio.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            proInter.setCostoUnitarioPorOmision(Float.valueOf(pantallaCrearProductoIntermedio.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoIntermedio, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoIntermedio.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            proInter.setPrecioBase(Float.valueOf(pantallaCrearProductoIntermedio.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoIntermedio, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoIntermedio.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaCrearProductoIntermedio.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            proInter.setEliminado(false);
        }else{
            proInter.setEliminado(true);
            proInter.setFechaEntrarEnActividad(pantallaCrearProductoIntermedio.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            proInter.setTamanioLoteEstandar(Integer.valueOf(pantallaCrearProductoIntermedio.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoIntermedio, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoIntermedio.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        proInter.setUbicacionEnAlmacen(pantallaCrearProductoIntermedio.getUbicacionAlamcenTextBox().getText());
        proInter.setObservacion(pantallaCrearProductoIntermedio.getObservacionTextArea().getText());
        proInter.setProductoTipoIQE((ProductoTipoIQE)pantallaCrearProductoIntermedio.getProductoIQERelacionadoListBox().getSelectedItem());
        
        experto.guardar(proInter);
        
        
        JOptionPane.showMessageDialog(pantallaCrearProductoIntermedio, "Producto intermedio guardado con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editarProductoIntermedio(String codigoProductoIntermedio){
        ProductoIntermedio proInter = experto.buscarProductoIntermedio(codigoProductoIntermedio);
        pantallaEditarProductoIntermedio = new PantallaEditarProductoIntermedio(controladorPantallaMadre.getPantalla(), false, this);
        cargarDatosEnPantallaEditar(proInter);
        pantallaEditarProductoIntermedio.setVisible(true);
    
    }
    
    public void eliminar() {
        
    }

    private void cargarDatosEnPantallaEditar(ProductoIntermedio proInter) {
        pantallaEditarProductoIntermedio.getCodigoTextBox().setText(proInter.getCodigo());
        pantallaEditarProductoIntermedio.getNombreTextBox().setText(proInter.getNombre());
        pantallaEditarProductoIntermedio.getDescripcionTextArea().setText(proInter.getDescripcion());
        pantallaEditarProductoIntermedio.getUnidadDeMedidaListBox().setSelectedItem(proInter.getUnidadDeMedida());
        pantallaEditarProductoIntermedio.getCategoriaListBox().setSelectedItem(proInter.getCategoria());
        pantallaEditarProductoIntermedio.getCostoEstandarTextBox().setText(String.valueOf(proInter.getCostoEstandar()));
        pantallaEditarProductoIntermedio.getCostoUnitarioPorOmisionTextBox().setText(String.valueOf(proInter.getCostoUnitarioPorOmision()));
        pantallaEditarProductoIntermedio.getPrecioBaseTextBox().setText(String.valueOf(proInter.getPrecioBase()));
        if(proInter.getEliminado()){
            pantallaEditarProductoIntermedio.getEstadoListBox().setSelectedItem("Inactivo");
            if(proInter.getFechaEntrarEnActividad() != null){
                pantallaEditarProductoIntermedio.getEstadoEntrarEnActividadEnFechajDateChooser().setDate(proInter.getFechaEntrarEnActividad());
            }
        }else{
            pantallaEditarProductoIntermedio.getEstadoListBox().setSelectedItem("Activo");
        }
        pantallaEditarProductoIntermedio.getTamanioLoteEstandarTextBox().setText(String.valueOf(proInter.getTamanioLoteEstandar()));
        pantallaEditarProductoIntermedio.getUbicacionAlamcenTextBox().setText(proInter.getUbicacionEnAlmacen());
        pantallaEditarProductoIntermedio.getObservacionTextArea().setText(proInter.getObservacion());
        pantallaEditarProductoIntermedio.getProductoIQERelacionadoListBox().setModel(new DefaultComboBoxModel(experto.buscarProductosIQE().toArray()));
        pantallaEditarProductoIntermedio.getProductoIQERelacionadoListBox().setSelectedItem(proInter.getProductoTipoIQE());
    }
    
    
    public void editar() {
        ProductoIntermedio proInter = experto.getProductoIntermedio();
        //TODO bloquear botones de gaurdar, cancear. etc
        if(pantallaEditarProductoIntermedio.getNombreTextBox().getText().isEmpty() | pantallaEditarProductoIntermedio.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaEditarProductoIntermedio, "Debe ingresar un nombre para el producto intermedio", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaEditarProductoIntermedio.getNombreTextBox().requestFocus();
                return;
        }else{
            proInter.setNombre(pantallaEditarProductoIntermedio.getNombreTextBox().getText());
        }
        proInter.setDescripcion(pantallaEditarProductoIntermedio.getDescripcionTextArea().getText());
        proInter.setUnidadDeMedida((String)pantallaEditarProductoIntermedio.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        proInter.setCategoria(((String)pantallaEditarProductoIntermedio.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        try{
            proInter.setCostoEstandar(Float.valueOf(pantallaEditarProductoIntermedio.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoIntermedio, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoIntermedio.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            proInter.setCostoUnitarioPorOmision(Float.valueOf(pantallaEditarProductoIntermedio.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoIntermedio, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoIntermedio.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            proInter.setPrecioBase(Float.valueOf(pantallaEditarProductoIntermedio.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoIntermedio, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoIntermedio.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaEditarProductoIntermedio.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            proInter.setEliminado(false);
        }else{
            proInter.setEliminado(true);
            proInter.setFechaEntrarEnActividad(pantallaEditarProductoIntermedio.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            proInter.setTamanioLoteEstandar(Integer.valueOf(pantallaEditarProductoIntermedio.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoIntermedio, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoIntermedio.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        proInter.setUbicacionEnAlmacen(pantallaEditarProductoIntermedio.getUbicacionAlamcenTextBox().getText());
        proInter.setObservacion(pantallaEditarProductoIntermedio.getObservacionTextArea().getText());
        proInter.setProductoTipoIQE((ProductoTipoIQE)pantallaEditarProductoIntermedio.getProductoIQERelacionadoListBox().getSelectedItem());
        experto.editar(proInter);
        
        
        JOptionPane.showMessageDialog(pantallaEditarProductoIntermedio, "Producto Intermedio guardado con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
