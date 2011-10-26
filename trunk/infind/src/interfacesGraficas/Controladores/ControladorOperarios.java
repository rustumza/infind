/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Operario;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoOperarioException;
import expertos.ExpertoOperarios;
import interfacesGraficas.ModeloCombo.ModeloComboBoxTipoOperador;
import interfacesGraficas.ModeloTablas.ModeloTablaOperariosEncontrados;
import interfacesGraficas.PantallaCrearOperario;
import interfacesGraficas.PantallaEditarOperario;
import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class ControladorOperarios {

    ControladorPantallaMadre controladorPantallaMadre;
    PantallaMadre pantallaMadre;
    PantallaCrearOperario pantallaCrearOperario;
    PantallaEditarOperario pantallaEditarOperario;
    ModeloTablaOperariosEncontrados modeloTablaOperariosEncontrados;
    Operario operarioSeleccionado;
    ModeloComboBoxTipoOperador modeloComboTipoOperador;
    ExpertoOperarios expertoOperarios;
    

    ControladorOperarios(ControladorPantallaMadre contPantMadre) {
        controladorPantallaMadre = contPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();
        expertoOperarios = (ExpertoOperarios) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.OPERARIOS);

       

    }

    public void crearOperario() {

        pantallaCrearOperario = new PantallaCrearOperario(pantallaMadre, false, this);
        modeloComboTipoOperador = new ModeloComboBoxTipoOperador();
        modeloComboTipoOperador = new ModeloComboBoxTipoOperador(expertoOperarios.buscarTipoOperario());
        pantallaCrearOperario.getComboPuesto().setModel(modeloComboTipoOperador);
        pantallaCrearOperario.setVisible(true);
        pantallaCrearOperario.setLocationRelativeTo(null);
         
    }

    public void editarOperario() {
        pantallaEditarOperario = new PantallaEditarOperario(pantallaMadre, true);
        modeloTablaOperariosEncontrados = new ModeloTablaOperariosEncontrados();
        pantallaEditarOperario.getTablaOperariosEncontrados().setModel(modeloTablaOperariosEncontrados);
        pantallaEditarOperario.setVisible(true);
        pantallaEditarOperario.setLocationRelativeTo(null);

    }

    public void guardarOperario() {
        if (operarioSeleccionado == null) {
            operarioSeleccionado = new Operario();

        }

        operarioSeleccionado.setApellido(pantallaCrearOperario.getCampoApellido().getText());
        operarioSeleccionado.setCodigoOperario(pantallaCrearOperario.getCampoCodigo().getText());
        operarioSeleccionado.setCorreoElectronico(pantallaCrearOperario.getCampoCorreo().getText());
        operarioSeleccionado.setDireccion(pantallaCrearOperario.getCampoDireccion().getText());
        operarioSeleccionado.setDni(Integer.valueOf(pantallaCrearOperario.getCampoDni().getText()));
        operarioSeleccionado.setEliminado(Boolean.FALSE);
        operarioSeleccionado.setNombre(pantallaCrearOperario.getCampoNombre().getText());
        operarioSeleccionado.setTelefono(Integer.valueOf(pantallaCrearOperario.getCampoTelefono().getText()));
        operarioSeleccionado.setTipoOperario(((ModeloComboBoxTipoOperador) pantallaCrearOperario.getComboPuesto().getModel()).getPuestoSeleccionado());

        try {
            expertoOperarios.guardar(operarioSeleccionado);
            JOptionPane.showMessageDialog(pantallaCrearOperario, "Operario Guardado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarPantallaNuevoOperador();
            operarioSeleccionado = null;
            pantallaCrearOperario.dispose();
            
        } catch (ExpertoOperarioException ex) {
            Logger.getLogger(ControladorOperarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void limpiarPantallaNuevoOperador(){
        
        pantallaCrearOperario.getCampoApellido().setText("");
        pantallaCrearOperario.getCampoCodigo().setText("");
        pantallaCrearOperario.getCampoCorreo().setText("");
        pantallaCrearOperario.getCampoDireccion().setText("");
        pantallaCrearOperario.getCampoDni().setText("");
        pantallaCrearOperario.getCampoNombre().setText("");
        pantallaCrearOperario.getCampoTelefono().setText("");
        pantallaCrearOperario.getComboPuesto().setModel(modeloComboTipoOperador);
        
        
    }
}
