/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.MateriaPrima;
import Entidades.Numerador;
import expertos.ExpertoMateriaPrima;
import interfacesGraficas.PantallaCrearMateriaPrima;
import interfacesGraficas.PantallaEditarMateriaPrima;
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
public class ControladorMateriaPrima {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaCrearMateriaPrima pantallaCrearMateriPrima;
    PantallaEditarMateriaPrima pantallaEditarMateriPrima;
    ExpertoMateriaPrima experto;
    
    ControladorMateriaPrima(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
        experto = new ExpertoMateriaPrima();
    }

    public void crearMateriaPrima() {
        pantallaCrearMateriPrima = new PantallaCrearMateriaPrima(controladorPantallaMadre.getPantalla(), false, this);
        pantallaCrearMateriPrima.setVisible(true);
        
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioNumerador.add(Restrictions.eq("codificacion", "1.1."));
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        String codifica = numeroDisponibles.get(0).getCodificacion();
        String num = numeroDisponibles.get(0).getUltimaClasificacion();
        int nume = Integer.parseInt(num);
        nume = nume + 1;
        num = String.valueOf(nume);
        String nuevoNumero = codifica + num;
        pantallaCrearMateriPrima.getCodigoTextBox().setText(nuevoNumero);
        
        
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
        matPrim.setTipoMateriaPrima((String)pantallaCrearMateriPrima.getTipoMateriaPrimaListBox().getModel().getSelectedItem());
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
        
        
        JOptionPane.showMessageDialog(pantallaCrearMateriPrima, "Materia prima guardada con éxito", "¡En hora buena!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cambiarTipoMateriaPrima() {
        
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        if(((String)pantallaCrearMateriPrima.getTipoMateriaPrimaListBox().getModel().getSelectedItem()).equals("Primaria")){
            criterioNumerador.add(Restrictions.eq("codificacion", "1.1."));
        }else{
            criterioNumerador.add(Restrictions.eq("codificacion", "1.2."));
        }
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        String codifica = numeroDisponibles.get(0).getCodificacion();
        String num = numeroDisponibles.get(0).getUltimaClasificacion();
        int nume = Integer.parseInt(num);
        nume = nume + 1;
        num = String.valueOf(nume);
        String nuevoNumero = codifica + num;
        pantallaCrearMateriPrima.getCodigoTextBox().setText(nuevoNumero);
    }

    public void editarMateriaPrima(String codigoMateriaPrima){
        MateriaPrima matPrim = experto.buscarMateriaPrima(codigoMateriaPrima);
        pantallaEditarMateriPrima = new PantallaEditarMateriaPrima(controladorPantallaMadre.getPantalla(), false, this);
        cargarDatosEnPantalla(matPrim);
        pantallaEditarMateriPrima.setVisible(true);
    
    }
    
    public void eliminar() {
        
    }

    private void cargarDatosEnPantalla(MateriaPrima matPrim) {
        pantallaEditarMateriPrima.getTipoMateriaPrimaListBox().setSelectedItem(matPrim.getTipoMateriaPrima());
        pantallaEditarMateriPrima.getCodigoTextBox().setText(matPrim.getCodigo());
        pantallaEditarMateriPrima.getNombreTextBox().setText(matPrim.getNombre());
        pantallaEditarMateriPrima.getDescripcionTextArea().setText(matPrim.getDescripcion());
        pantallaEditarMateriPrima.getUnidadDeMedidaListBox().setSelectedItem(matPrim.getUnidadDeMedida());
        pantallaEditarMateriPrima.getCategoriaListBox().setSelectedItem(matPrim.getCategoria());
        pantallaEditarMateriPrima.getCostoEstandarTextBox().setText(String.valueOf(matPrim.getCostoEstandar()));
        pantallaEditarMateriPrima.getCostoUnitarioPorOmisionTextBox().setText(String.valueOf(matPrim.getCostoUnitarioPorOmision()));
        pantallaEditarMateriPrima.getPrecioBaseTextBox().setText(String.valueOf(matPrim.getPrecioBase()));
        if(matPrim.getEliminado()){
            pantallaEditarMateriPrima.getEstadoListBox().setSelectedItem("Inactivo");
            if(matPrim.getFechaEntrarEnActividad() != null){
                pantallaEditarMateriPrima.getEstadoEntrarEnActividadEnFechajDateChooser().setDate(matPrim.getFechaEntrarEnActividad());
            }
        }else{
            pantallaEditarMateriPrima.getEstadoListBox().setSelectedItem("Activo");
        }
        pantallaEditarMateriPrima.getTamañoLoteEstandarLabel().setText(String.valueOf(matPrim.getTamanioLoteEstandar()));
        pantallaEditarMateriPrima.getUbicacionAlamcenTextBox().setText(matPrim.getUbicacionEnAlmacen());
        pantallaEditarMateriPrima.getObservacionTextArea().setText(matPrim.getObservacion());
        pantallaEditarMateriPrima.getProveedorPredeterminadoListBox().setModel(new DefaultComboBoxModel(matPrim.getProveedores().toArray()));
        pantallaEditarMateriPrima.getProveedorPredeterminadoListBox().setSelectedItem(matPrim.getProveedorPredeterminado());
    }
    
}
