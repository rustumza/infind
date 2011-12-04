/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.ProductoComponente;
import Entidades.Numerador;
import Entidades.Proveedor;
import expertos.ExpertoProductoComponente;
import interfacesGraficas.PantallaCrearProductoComponente;
import interfacesGraficas.PantallaEditarProductoComponente;
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
public class ControladorProductoComponente implements Controlador{

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearProductoComponente pantallaCrearProductoComponente;
    PantallaEditarProductoComponente pantallaEditarProductoComponente;
    ExpertoProductoComponente experto;
    
    ControladorProductoComponente(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoProductoComponente();
    }

    public void crearProductoComponente() {
        pantallaCrearProductoComponente = new PantallaCrearProductoComponente(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearProductoComponente.setLocationRelativeTo(null);
        pantallaCrearProductoComponente.setVisible(true);
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioNumerador.add(Restrictions.eq("codificacion", "2."));
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        String codifica = numeroDisponibles.get(0).getCodificacion();
        String num = numeroDisponibles.get(0).getUltimaClasificacion();
        int nume = Integer.parseInt(num);
        nume = nume + 1;
        num = String.valueOf(nume);
        String nuevoNumero = codifica + num;
        pantallaCrearProductoComponente.getCodigoTextBox().setText(nuevoNumero);
        
        
    }

    public void guardar() {
        
        //TODO bloquear botones de gaurdar, cancear. etc
        
        ProductoComponente proComp = new ProductoComponente();
        proComp.setCodigo(pantallaCrearProductoComponente.getCodigoTextBox().getText());
        if(pantallaCrearProductoComponente.getNombreTextBox().getText().isEmpty() | pantallaCrearProductoComponente.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaCrearProductoComponente, "Debe ingresar un nombre para el producto componente", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaCrearProductoComponente.getNombreTextBox().requestFocus();
                return;
        }else{
            proComp.setNombre(pantallaCrearProductoComponente.getNombreTextBox().getText());
        }
        proComp.setDescripcion(pantallaCrearProductoComponente.getDescripcionTextArea().getText());
        proComp.setUnidadDeMedida((String)pantallaCrearProductoComponente.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        proComp.setCategoria(((String)pantallaCrearProductoComponente.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        try{
            proComp.setCostoEstandar(Float.valueOf(pantallaCrearProductoComponente.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoComponente, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoComponente.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            proComp.setCostoUnitarioPorOmision(Float.valueOf(pantallaCrearProductoComponente.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoComponente, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoComponente.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            proComp.setPrecioBase(Float.valueOf(pantallaCrearProductoComponente.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoComponente, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoComponente.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaCrearProductoComponente.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            proComp.setEliminado(false);
        }else{
            proComp.setEliminado(true);
            proComp.setFechaEntrarEnActividad(pantallaCrearProductoComponente.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            proComp.setTamanioLoteEstandar(Float.valueOf(pantallaCrearProductoComponente.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoComponente, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoComponente.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        try{
            proComp.setTiempoDeObtenecion(Integer.valueOf(pantallaCrearProductoComponente.getTiempoDeObtencionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaCrearProductoComponente, "Ha ingresado un tiempo de obtención incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaCrearProductoComponente.getTiempoDeObtencionTextBox().requestFocus();
            return;       
        }
        proComp.setUbicacionEnAlmacen(pantallaCrearProductoComponente.getUbicacionAlamcenTextBox().getText());
        proComp.setObservacion(pantallaCrearProductoComponente.getObservacionTextArea().getText());
        proComp.setTipoInventario(null);
        proComp.setCostoDeAlmacenamiento((float)0);
        proComp.setCostoDePedido((float)0);
        experto.guardar(proComp);
        
        
        JOptionPane.showMessageDialog(pantallaCrearProductoComponente, "Producto componente guardado con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editarProductoComponente(String codigoMateriaPrima){
        ProductoComponente proComp = experto.buscarProductoComponente(codigoMateriaPrima);
        pantallaEditarProductoComponente = new PantallaEditarProductoComponente(controladorPantallaMadre.getPantalla(), false, this);
        cargarDatosEnPantallaEditar(proComp);
        pantallaEditarProductoComponente.setVisible(true);
    
    }
    
    public void eliminar() {
        
    }

    private void cargarDatosEnPantallaEditar(ProductoComponente proComp) {
        pantallaEditarProductoComponente.getCodigoTextBox().setText(proComp.getCodigo());
        pantallaEditarProductoComponente.getNombreTextBox().setText(proComp.getNombre());
        pantallaEditarProductoComponente.getDescripcionTextArea().setText(proComp.getDescripcion());
        pantallaEditarProductoComponente.getUnidadDeMedidaListBox().setSelectedItem(proComp.getUnidadDeMedida());
        pantallaEditarProductoComponente.getCategoriaListBox().setSelectedItem(proComp.getCategoria());
        pantallaEditarProductoComponente.getCostoEstandarTextBox().setText(String.valueOf(proComp.getCostoEstandar()));
        pantallaEditarProductoComponente.getCostoUnitarioPorOmisionTextBox().setText(String.valueOf(proComp.getCostoUnitarioPorOmision()));
        pantallaEditarProductoComponente.getPrecioBaseTextBox().setText(String.valueOf(proComp.getPrecioBase()));
        if(proComp.getEliminado()){
            pantallaEditarProductoComponente.getEstadoListBox().setSelectedItem("Inactivo");
            if(proComp.getFechaEntrarEnActividad() != null){
                pantallaEditarProductoComponente.getEstadoEntrarEnActividadEnFechajDateChooser().setDate(proComp.getFechaEntrarEnActividad());
            }
        }else{
            pantallaEditarProductoComponente.getEstadoListBox().setSelectedItem("Activo");
        }
        pantallaEditarProductoComponente.getTamanioLoteEstandarTextBox().setText(String.valueOf(proComp.getTamanioLoteEstandar()));
        pantallaEditarProductoComponente.getUbicacionAlamcenTextBox().setText(proComp.getUbicacionEnAlmacen());
        pantallaEditarProductoComponente.getTiempoDeObtencionTextBox().setText(String.valueOf(proComp.getTiempoDeObtenecion()));
        pantallaEditarProductoComponente.getObservacionTextArea().setText(proComp.getObservacion());
        pantallaEditarProductoComponente.getProveedorPredeterminadoListBox().setModel(new DefaultComboBoxModel(proComp.getProveedores().toArray()));
        if(proComp.getProveedor() != null){
            pantallaEditarProductoComponente.getProveedorPredeterminadoListBox().setSelectedItem(proComp.getProveedor());
        }
    }
    
    
    public void editar() {
        ProductoComponente proComp = experto.getProductoComponente();
        //TODO bloquear botones de gaurdar, cancear. etc
        if(pantallaEditarProductoComponente.getNombreTextBox().getText().isEmpty() | pantallaEditarProductoComponente.getNombreTextBox().getText().equals(" ")){
                JOptionPane.showMessageDialog(pantallaEditarProductoComponente, "Debe ingresar un nombre para el producto componente", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                pantallaEditarProductoComponente.getNombreTextBox().requestFocus();
                return;
        }else{
            proComp.setNombre(pantallaEditarProductoComponente.getNombreTextBox().getText());
        }
        proComp.setDescripcion(pantallaEditarProductoComponente.getDescripcionTextArea().getText());
        proComp.setUnidadDeMedida((String)pantallaEditarProductoComponente.getUnidadDeMedidaListBox().getModel().getSelectedItem());
        proComp.setCategoria(((String)pantallaEditarProductoComponente.getCategoriaListBox().getModel().getSelectedItem()).charAt(0));
        try{
            proComp.setCostoEstandar(Float.valueOf(pantallaEditarProductoComponente.getCostoEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoComponente, "Ha ingresado un costo estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoComponente.getCostoEstandarTextBox().requestFocus();
            return;
        }
        
        try{
            proComp.setCostoUnitarioPorOmision(Float.valueOf(pantallaEditarProductoComponente.getCostoUnitarioPorOmisionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoComponente, "Ha ingresado un costo unitario por omisión incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoComponente.getCostoUnitarioPorOmisionTextBox().requestFocus();
            return;      
        }
        
        try{
            proComp.setPrecioBase(Float.valueOf(pantallaEditarProductoComponente.getPrecioBaseTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoComponente, "Ha ingresado un precio base incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoComponente.getPrecioBaseTextBox().requestFocus();
            return;       
        }
        if(((String)(pantallaEditarProductoComponente.getEstadoListBox().getModel().getSelectedItem())).equals("Activo")){
            proComp.setEliminado(false);
        }else{
            proComp.setEliminado(true);
            proComp.setFechaEntrarEnActividad(pantallaEditarProductoComponente.getEstadoEntrarEnActividadEnFechajDateChooser().getDate());
        }
        
        try{
            proComp.setTamanioLoteEstandar(Float.valueOf(pantallaEditarProductoComponente.getTamanioLoteEstandarTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoComponente, "Ha ingresado un tamaño de lote estandar incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoComponente.getTamanioLoteEstandarTextBox().requestFocus();
            return;       
        }
        try{
            proComp.setTiempoDeObtenecion(Integer.valueOf(pantallaEditarProductoComponente.getTiempoDeObtencionTextBox().getText()));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(pantallaEditarProductoComponente, "Ha ingresado un tiempo de obtención incorrecto", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            pantallaEditarProductoComponente.getTiempoDeObtencionTextBox().requestFocus();
            return;       
        }
        proComp.setUbicacionEnAlmacen(pantallaEditarProductoComponente.getUbicacionAlamcenTextBox().getText());
        proComp.setObservacion(pantallaEditarProductoComponente.getObservacionTextArea().getText());
        proComp.setProveedor((Proveedor)pantallaEditarProductoComponente.getProveedorPredeterminadoListBox().getModel().getSelectedItem());
        experto.editar(proComp);
        
        
        JOptionPane.showMessageDialog(pantallaEditarProductoComponente, "Producto componente guardado con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void gestionDeInventario() {
        new ControladorGestionDeInventarios(controladorPantallaMadre, this).iniciar(experto.getProductoComponente());
    }

    public void actualizarPorGestionDeInventarios() {
         pantallaEditarProductoComponente.getTamanioLoteEstandarTextBox().setText(String.valueOf(experto.getProductoComponente().getTamanioLoteEstandar()));
    }
    
    
}
