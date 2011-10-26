/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOOperario;
import Entidades.Operario;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoOperarioException;
import expertos.ExpertoOperarios;
import interfacesGraficas.ModeloCombo.ModeloComboBoxTipoOperador;
import interfacesGraficas.ModeloCombo.ModeloComboBoxTipoOperadorActualizado;
import interfacesGraficas.ModeloTablas.ModeloTablaOperariosEncontrados;
import interfacesGraficas.PantallaCrearOperario;
import interfacesGraficas.PantallaEditarOperario;
import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
    List<Operario> operarioEncontrado;
    ModeloComboBoxTipoOperador modeloComboTipoOperador;
    ModeloComboBoxTipoOperadorActualizado modeloComboTipoOperadorActualizado;
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
        pantallaEditarOperario = new PantallaEditarOperario(pantallaMadre, true, this);
        modeloTablaOperariosEncontrados = new ModeloTablaOperariosEncontrados();
        pantallaEditarOperario.getTablaOperariosEncontrados().setModel(modeloTablaOperariosEncontrados);
        modeloComboTipoOperadorActualizado = new ModeloComboBoxTipoOperadorActualizado();
        modeloComboTipoOperadorActualizado = new ModeloComboBoxTipoOperadorActualizado(expertoOperarios.buscarTipoOperario());
        pantallaEditarOperario.getComboEditarPuesto().setModel(modeloComboTipoOperadorActualizado);

        pantallaEditarOperario.setVisible(true);
        pantallaEditarOperario.setLocationRelativeTo(null);

    }

    public void buscarOperarios() throws ExpertoOperarioException {


        if (!modeloTablaOperariosEncontrados.getListaElementos().isEmpty()) {
            modeloTablaOperariosEncontrados.clear();
        }

        if (pantallaEditarOperario.getRadioBotonCodigo().isSelected()) {
            operarioEncontrado = expertoOperarios.buscarOperarios(armarDTOOperario(1));

            if (!operarioEncontrado.get(0).getCodigoOperario().isEmpty()) {
                pantallaEditarOperario.getCampoCodigo().setText(operarioEncontrado.get(0).getCodigoOperario());
                pantallaEditarOperario.getCampoApellido().setText(operarioEncontrado.get(0).getApellido());
                pantallaEditarOperario.getCampoCorreo().setText(operarioEncontrado.get(0).getCorreoElectronico());
                pantallaEditarOperario.getCampoDNI().setText(Integer.toString(operarioEncontrado.get(0).getDni()));
                pantallaEditarOperario.getCampoDireccion().setText(operarioEncontrado.get(0).getDireccion());
                pantallaEditarOperario.getCampoNombre().setText(operarioEncontrado.get(0).getNombre());
                pantallaEditarOperario.getCampoTelefono().setText(Integer.toString(operarioEncontrado.get(0).getTelefono()));
                pantallaEditarOperario.getComboEditarPuesto().setSelectedItem(operarioEncontrado.get(0).getTipoOperario().getNombre());

            }

        } else if (pantallaEditarOperario.getRadioBotonNombre().isSelected()) {


            operarioEncontrado = expertoOperarios.buscarOperarios(armarDTOOperario(2));

            modeloTablaOperariosEncontrados.addAllRow(operarioEncontrado);
            


        }

    }

    public void tabla(Integer fila, Integer click) {

        operarioSeleccionado = modeloTablaOperariosEncontrados.buscarOperario(pantallaEditarOperario.getTablaOperariosEncontrados().getValueAt(fila, 1).toString());

        if (click == 2) {

            cargarOperario(operarioSeleccionado);

        }

    }

    public DTOOperario armarDTOOperario(Integer caso) {

        DTOOperario nuevoDto = new DTOOperario();

        switch (caso) {
            case 1:

                if (!pantallaEditarOperario.getCampoBuscaCodigo().getText().equals("")) {
                    nuevoDto.setCodigoOperario(pantallaEditarOperario.getCampoBuscaCodigo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaEditarOperario.getCampoBuscaNombre().getText().equals("")) {
                    nuevoDto.setNombreOperario(pantallaEditarOperario.getCampoBuscaNombre().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
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

    public void cargarOperario(Operario operario) {

        pantallaEditarOperario.getCampoCodigo().setText(operario.getCodigoOperario());
        pantallaEditarOperario.getCampoApellido().setText(operario.getApellido());
        pantallaEditarOperario.getCampoCorreo().setText(operario.getCorreoElectronico());
        pantallaEditarOperario.getCampoDNI().setText(Integer.toString(operario.getDni()));
        pantallaEditarOperario.getCampoDireccion().setText(operario.getDireccion());
        pantallaEditarOperario.getCampoNombre().setText(operario.getNombre());
        pantallaEditarOperario.getCampoTelefono().setText(Integer.toString(operario.getTelefono()));

    }

    public void actualizarOperario() throws ExpertoOperarioException {


        if (operarioEncontrado == null) {
            return;

        }
        operarioSeleccionado = operarioEncontrado.get(0);

        operarioSeleccionado.setApellido(pantallaEditarOperario.getCampoApellido().getText());
        operarioSeleccionado.setCodigoOperario(pantallaEditarOperario.getCampoCodigo().getText());
        operarioSeleccionado.setCorreoElectronico(pantallaEditarOperario.getCampoCorreo().getText());
        operarioSeleccionado.setDireccion(pantallaEditarOperario.getCampoDireccion().getText());
        operarioSeleccionado.setDni(Integer.valueOf(pantallaEditarOperario.getCampoDNI().getText()));
        operarioSeleccionado.setEliminado(Boolean.FALSE);
        operarioSeleccionado.setNombre(pantallaEditarOperario.getCampoNombre().getText());
        operarioSeleccionado.setTelefono(Integer.valueOf(pantallaEditarOperario.getCampoTelefono().getText()));
        operarioSeleccionado.setTipoOperario(((ModeloComboBoxTipoOperadorActualizado) pantallaEditarOperario.getComboEditarPuesto().getModel()).getPuestoSeleccionado());

        try {
            expertoOperarios.guardar(operarioSeleccionado);
            JOptionPane.showMessageDialog(pantallaCrearOperario, "Operario Guardado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarPantallaEditarOperador();
            operarioSeleccionado = null;
            pantallaEditarOperario.dispose();

        } catch (ExpertoOperarioException ex) {
            Logger.getLogger(ControladorOperarios.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    public void eliminarOperario() {
    }

    public void limpiarPantallaNuevoOperador() {

        pantallaCrearOperario.getCampoApellido().setText("");
        pantallaCrearOperario.getCampoCodigo().setText("");
        pantallaCrearOperario.getCampoCorreo().setText("");
        pantallaCrearOperario.getCampoDireccion().setText("");
        pantallaCrearOperario.getCampoDni().setText("");
        pantallaCrearOperario.getCampoNombre().setText("");
        pantallaCrearOperario.getCampoTelefono().setText("");
        pantallaCrearOperario.getComboPuesto().setModel(modeloComboTipoOperador);


    }

    public void limpiarPantallaEditarOperador() {

        pantallaEditarOperario.getCampoApellido().setText("");
        pantallaEditarOperario.getCampoCodigo().setText("");
        pantallaEditarOperario.getCampoCorreo().setText("");
        pantallaEditarOperario.getCampoDireccion().setText("");
        pantallaEditarOperario.getCampoDNI().setText("");
        pantallaEditarOperario.getCampoNombre().setText("");
        pantallaEditarOperario.getCampoTelefono().setText("");
        pantallaEditarOperario.getComboEditarPuesto().setModel(modeloComboTipoOperadorActualizado);
        modeloTablaOperariosEncontrados.clear();
        pantallaEditarOperario.getCampoBuscaCodigo().setText("");
        pantallaEditarOperario.getCampoBuscaNombre().setText("");
        


    }
}
