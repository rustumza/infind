/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.ProductoFinal;
import Entidades.Numerador;
import Entidades.ProductoTipoIQE;
import expertos.ExpertoProductoFinal;
import interfacesGraficas.PantallaCrearProductoFinal;
import interfacesGraficas.PantallaEditarProductoFinal;
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
public class ControladorProductoFinal implements Controlador{

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearProductoFinal pantallaCrearProductoFinal;
    PantallaEditarProductoFinal pantallaEditarProductoFinal;
    ExpertoProductoFinal experto;
    
    ControladorProductoFinal(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoProductoFinal();
    }

    public void crearProductoFinal() {
        pantallaCrearProductoFinal = new PantallaCrearProductoFinal(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearProductoFinal.setLocationRelativeTo(null);
        pantallaCrearProductoFinal.setVisible(true);
        
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioNumerador.add(Restrictions.eq("codificacion", "4.1."));
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        String codifica = numeroDisponibles.get(0).getCodificacion();
        String num = numeroDisponibles.get(0).getUltimaClasificacion();
        int nume = Integer.parseInt(num);
        nume = nume + 1;
        num = String.valueOf(nume);
        String nuevoNumero = codifica + num;
        pantallaCrearProductoFinal.getCodigoTextBox().setText(nuevoNumero);
        
        List prodIQE = experto.buscarProductosIQE();
        pantallaCrearProductoFinal.getProductoIQERelacionadoListBox().setModel(new DefaultComboBoxModel(prodIQE.toArray()));
        
        
    }

    public void guardar() {
        
        //TODO bloquear botones de gaurdar, cancear. etc
        
        ProductoFinal proFinal = new ProductoFinal();
        proFinal.setTipoProductoFinal((String)pantallaCrearProductoFinal.getTipoListBox().getSelectedItem());
        proFinal.setCodigo(pantallaCrearProductoFinal.getCodigoTextBox().getText());
        if(pantallaCrearProductoFinal.getNombreTextBox().getText().isEmpty() | pantallaCrearProductoFinal.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaCrearProductoFinal, "Debe ingresar un nombre para el producto final", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearProductoFinal.getNombreTextBox().requestFocus();
                return;
        }else{
            proFinal.setNombre(pantallaCrearProductoFinal.getNombreTextBox().getText());
        }
        proFinal.setDescripcion(pantallaCrearProductoFinal.getDescripcionTextArea().getText());
        proFinal.setUnidadDeMedida((String)pantallaCrearProductoFinal.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        proFinal.setCategoria(((String)pantallaCrearProductoFinal.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        try{
            proFinal.setCostoEstandar(Float.valueOf(pantallaCrearProductoFinal.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoFinal, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoFinal.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            proFinal.setCostoUnitarioPorOmision(Float.valueOf(pantallaCrearProductoFinal.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoFinal, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoFinal.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            proFinal.setPrecioBase(Float.valueOf(pantallaCrearProductoFinal.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoFinal, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoFinal.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaCrearProductoFinal.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            proFinal.setEliminado(false);
        }else{
            proFinal.setEliminado(true);
            proFinal.setFechaEntrarEnActividad(pantallaCrearProductoFinal.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            proFinal.setTamanioLoteEstandar(Float.valueOf(pantallaCrearProductoFinal.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoFinal, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoFinal.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        try{
            proFinal.setTiempoDeObtenecion(Integer.valueOf(pantallaCrearProductoFinal.getTiempoDeObtencionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoFinal, "Ha ingresado un tiempo de obtención incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoFinal.getTiempoDeObtencionTextBox().requestFocus();
            return;       
        }
        proFinal.setUbicacionEnAlmacen(pantallaCrearProductoFinal.getUbicacionAlamcenTextBox().getText());
        proFinal.setObservacion(pantallaCrearProductoFinal.getObservacionTextArea().getText());
        proFinal.setProductoTipoIQE((ProductoTipoIQE)pantallaCrearProductoFinal.getProductoIQERelacionadoListBox().getSelectedItem());
        proFinal.setTipoInventario(null);
        proFinal.setCostoDeAlmacenamiento((float)0);
        proFinal.setCostoDePedido((float)0);
        proFinal.setPuntoDePedido((float)0);
        proFinal.setStockDeSeguridad((float)0);
        experto.guardar(proFinal);
        
        
        JOptionPane.showMessageDialog(pantallaCrearProductoFinal, "Producto final guardado con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editarProductoFinal(String codigoPFinal){
        ProductoFinal proFinal = experto.buscarProductoFinal(codigoPFinal);
        pantallaEditarProductoFinal = new PantallaEditarProductoFinal(controladorPantallaMadre.getPantalla(), false, this);
        cargarDatosEnPantallaEditar(proFinal);
        pantallaEditarProductoFinal.setVisible(true);
    
    }
    
    public void eliminar() {
        
    }

    private void cargarDatosEnPantallaEditar(ProductoFinal pFinal) {
        pantallaEditarProductoFinal.getCodigoTextBox().setText(pFinal.getCodigo());
        pantallaEditarProductoFinal.getTipoListBox().setSelectedItem(pFinal.getTipoProductoFinal());
        pantallaEditarProductoFinal.getNombreTextBox().setText(pFinal.getNombre());
        pantallaEditarProductoFinal.getDescripcionTextArea().setText(pFinal.getDescripcion());
        pantallaEditarProductoFinal.getUnidadDeMedidaListBox().setSelectedItem(pFinal.getUnidadDeMedida());
        pantallaEditarProductoFinal.getCategoriaListBox().setSelectedItem(pFinal.getCategoria());
        pantallaEditarProductoFinal.getCostoEstandarTextBox().setText(String.valueOf(pFinal.getCostoEstandar()));
        pantallaEditarProductoFinal.getCostoUnitarioPorOmisionTextBox().setText(String.valueOf(pFinal.getCostoUnitarioPorOmision()));
        pantallaEditarProductoFinal.getPrecioBaseTextBox().setText(String.valueOf(pFinal.getPrecioBase()));
        if(pFinal.getEliminado()){
            pantallaEditarProductoFinal.getEstadoListBox().setSelectedItem("Inactivo");
            if(pFinal.getFechaEntrarEnActividad() != null){
                pantallaEditarProductoFinal.getEstadoEntrarEnActividadEnFechajDateChooser().setDate(pFinal.getFechaEntrarEnActividad());
            }
        }else{
            pantallaEditarProductoFinal.getEstadoListBox().setSelectedItem("Activo");
        }
        pantallaEditarProductoFinal.getTamanioLoteEstandarTextBox().setText(String.valueOf(pFinal.getTamanioLoteEstandar()));
        pantallaEditarProductoFinal.getUbicacionAlamcenTextBox().setText(pFinal.getUbicacionEnAlmacen());
        pantallaEditarProductoFinal.getTiempoDeObtencionTextBox().setText(String.valueOf(pFinal.getTiempoDeObtenecion()));
        pantallaEditarProductoFinal.getObservacionTextArea().setText(pFinal.getObservacion());
        pantallaEditarProductoFinal.getProductoIQERelacionadoListBox().setModel(new DefaultComboBoxModel(experto.buscarProductosIQE().toArray()));
        pantallaEditarProductoFinal.getProductoIQERelacionadoListBox().getModel().setSelectedItem(pFinal.getProductoTipoIQE());
    }
    
    
    public void editar() {
        ProductoFinal pFinal = experto.getProductoFinal();
        //TODO bloquear botones de gaurdar, cancear. etc
        if(pantallaEditarProductoFinal.getNombreTextBox().getText().isEmpty() | pantallaEditarProductoFinal.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaEditarProductoFinal, "Debe ingresar un nombre para el producto final", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaEditarProductoFinal.getNombreTextBox().requestFocus();
                return;
        }else{
            pFinal.setNombre(pantallaEditarProductoFinal.getNombreTextBox().getText());
        }
        pFinal.setTipoProductoFinal((String)pantallaEditarProductoFinal.getTipoListBox().getSelectedItem());
        pFinal.setDescripcion(pantallaEditarProductoFinal.getDescripcionTextArea().getText());
        pFinal.setUnidadDeMedida((String)pantallaEditarProductoFinal.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        pFinal.setCategoria(((String)pantallaEditarProductoFinal.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        try{
            pFinal.setCostoEstandar(Float.valueOf(pantallaEditarProductoFinal.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoFinal, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoFinal.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            pFinal.setCostoUnitarioPorOmision(Float.valueOf(pantallaEditarProductoFinal.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoFinal, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoFinal.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            pFinal.setPrecioBase(Float.valueOf(pantallaEditarProductoFinal.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoFinal, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoFinal.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaEditarProductoFinal.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            pFinal.setEliminado(false);
        }else{
            pFinal.setEliminado(true);
            pFinal.setFechaEntrarEnActividad(pantallaEditarProductoFinal.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            pFinal.setTamanioLoteEstandar(Float.valueOf(pantallaEditarProductoFinal.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoFinal, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoFinal.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        try{
            pFinal.setTiempoDeObtenecion(Integer.valueOf(pantallaEditarProductoFinal.getTiempoDeObtencionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoFinal, "Ha ingresado un tiempo de obtención incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoFinal.getTiempoDeObtencionTextBox().requestFocus();
            return;       
        }
        pFinal.setUbicacionEnAlmacen(pantallaEditarProductoFinal.getUbicacionAlamcenTextBox().getText());
        pFinal.setObservacion(pantallaEditarProductoFinal.getObservacionTextArea().getText());
        pFinal.setProductoTipoIQE((ProductoTipoIQE)pantallaEditarProductoFinal.getProductoIQERelacionadoListBox().getSelectedItem());
        experto.editar(pFinal);
        
        
        JOptionPane.showMessageDialog(pantallaEditarProductoFinal, "Producto final guardado con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void buscarCodigoSegunTipo() {
        if(((String)pantallaCrearProductoFinal.getTipoListBox().getSelectedItem()).equals("Domisanitarios")){
        
            List<Numerador> numeroDisponibles = null;
            Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
            criterioNumerador.add(Restrictions.eq("codificacion", "4.1."));
            numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
            String codifica = numeroDisponibles.get(0).getCodificacion();
            String num = numeroDisponibles.get(0).getUltimaClasificacion();
            int nume = Integer.parseInt(num);
            nume = nume + 1;
            num = String.valueOf(nume);
            String nuevoNumero = codifica + num;
            pantallaCrearProductoFinal.getCodigoTextBox().setText(nuevoNumero);
            
        }else{
            List<Numerador> numeroDisponibles = null;
            Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
            criterioNumerador.add(Restrictions.eq("codificacion", "4.2."));
            numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
            String codifica = numeroDisponibles.get(0).getCodificacion();
            String num = numeroDisponibles.get(0).getUltimaClasificacion();
            int nume = Integer.parseInt(num);
            nume = nume + 1;
            num = String.valueOf(nume);
            String nuevoNumero = codifica + num;
            pantallaCrearProductoFinal.getCodigoTextBox().setText(nuevoNumero);
            
        
        }
    }
    


    public void gestionDeInventario() {
       new ControladorGestionDeInventarios(controladorPantallaMadre, this).iniciar(experto.getProductoFinal());
    }

    public void actualizarPorGestionDeInventarios() {
         pantallaEditarProductoFinal.getTamanioLoteEstandarTextBox().setText(String.valueOf(experto.getProductoFinal().getTamanioLoteEstandar()));
    }
    
}
