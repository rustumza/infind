/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOCentro;
import Entidades.MaestroDeCentroDeTrabajo;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCentroDeTrabajoException;
import expertos.ExpertoCentroDeTrabajo;
import interfacesGraficas.PantallaCrearCentro;
import interfacesGraficas.PantallaEditarCentro;
import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorCentroDeTrabajo {

    PantallaCrearCentro pantallacrearcentro;
    PantallaMadre pantallaMadre;
    PantallaEditarCentro pantallaEditarCentro;
    ExpertoCentroDeTrabajo expertoCentroDeTrabajo;
    MaestroDeCentroDeTrabajo centroDeTrabajoSeleccionado = null;
    List<MaestroDeCentroDeTrabajo> centroEncontrado = null;

    public ControladorCentroDeTrabajo(ControladorPantallaMadre contrPantMadre) {
        pantallacrearcentro = new PantallaCrearCentro(pantallaMadre, false);
        pantallaEditarCentro = new PantallaEditarCentro(pantallaMadre, false);
        expertoCentroDeTrabajo = (ExpertoCentroDeTrabajo) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.CENTRO_DE_TRABAJO);

//pantalla nuevo centro        
        //boton salir        
        pantallacrearcentro.getBotonSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                finalizarCrearCentro();
            }
        });

        //boton guardar
        pantallacrearcentro.getBotonGuardar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guardarCentroDeTrabajo();
            }
        });

//pantalla editar centro 

        pantallaEditarCentro.getBotonBuscarCentro().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {

                    buscarCentroDeTrabajo();
                } catch (ExpertoCentroDeTrabajoException ex) {
                    JOptionPane.showMessageDialog(pantallacrearcentro, "No se encontró ningún Centro de Trabajo", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                    limpiarPantallaEditarCentroDeTrabajo();
                }
            }
        });

        pantallaEditarCentro.getBotonActualizar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                actualizarCentro();
            }
        });

        pantallaEditarCentro.getBotonSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                finalizarEditarCentro();
            }
        });

        pantallaEditarCentro.getjRadioCodigo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (pantallaEditarCentro.getjRadioCodigo().isSelected()) {
                    pantallaEditarCentro.getCampoBuscaCodigo().setEnabled(true);
                    pantallaEditarCentro.getCampoBuscaNombre().setEnabled(false);
                }
            }
        });

        pantallaEditarCentro.getjRadioNombre().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (pantallaEditarCentro.getjRadioNombre().isSelected()) {
                    pantallaEditarCentro.getCampoBuscaNombre().setEnabled(true);
                    pantallaEditarCentro.getCampoBuscaCodigo().setEnabled(false);
                }
            }
        });

    }

    public void buscarCentroDeTrabajo() throws ExpertoCentroDeTrabajoException {


        if (pantallaEditarCentro.getjRadioCodigo().isSelected()) {
            centroEncontrado = expertoCentroDeTrabajo.buscarCentros(armarDTOCentro(1));

            if (!centroEncontrado.isEmpty()) {
                pantallaEditarCentro.getCampoCodigo().setText(centroEncontrado.get(0).getCodigo());
                pantallaEditarCentro.getCampoDescripcion().setText(centroEncontrado.get(0).getDescripcion());
                pantallaEditarCentro.getCampoNombre().setText(centroEncontrado.get(0).getNombreCentro());
            }

        } else if (pantallaEditarCentro.getjRadioNombre().isSelected()) {
            centroEncontrado = expertoCentroDeTrabajo.buscarCentros(armarDTOCentro(2));

            if (!centroEncontrado.isEmpty()) {
                pantallaEditarCentro.getCampoCodigo().setText(centroEncontrado.get(0).getCodigo());
                pantallaEditarCentro.getCampoDescripcion().setText(centroEncontrado.get(0).getDescripcion());
                pantallaEditarCentro.getCampoNombre().setText(centroEncontrado.get(0).getNombreCentro());
            }


        }

    }

    public DTOCentro armarDTOCentro(Integer caso) {

        DTOCentro nuevoDto = new DTOCentro();

        switch (caso) {
            case 1:

                if (!pantallaEditarCentro.getCampoBuscaCodigo().getText().equals("")) {
                    nuevoDto.setCodigoCentro(pantallaEditarCentro.getCampoBuscaCodigo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaEditarCentro.getCampoBuscaNombre().getText().equals("")) {
                    nuevoDto.setNombreCentro(pantallaEditarCentro.getCampoBuscaNombre().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void guardarCentroDeTrabajo() {

        if (centroDeTrabajoSeleccionado == null) {
            centroDeTrabajoSeleccionado = new MaestroDeCentroDeTrabajo();
        }

        centroDeTrabajoSeleccionado.setCodigo(pantallacrearcentro.getCampoCodigo().getText());
        centroDeTrabajoSeleccionado.setDescripcion(pantallacrearcentro.getCampoDescripcion().getText());
        centroDeTrabajoSeleccionado.setNombreCentro(pantallacrearcentro.getCampoNombre().getText());
        centroDeTrabajoSeleccionado.setEliminado(Boolean.FALSE);


        try {
            List<MaestroDeCentroDeTrabajo> centrosEncontrados = expertoCentroDeTrabajo.buscarCentrosDeTrabajo(centroDeTrabajoSeleccionado);
            if (!centrosEncontrados.isEmpty()) {

                JOptionPane.showMessageDialog(pantallacrearcentro, "El Centro De Trabajo Ingresado ya Existe", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

            } else {

                expertoCentroDeTrabajo.guardar(centroDeTrabajoSeleccionado);
                limpiarPantallaCentroDeTrabajo();
                centroDeTrabajoSeleccionado = null;
                JOptionPane.showMessageDialog(pantallacrearcentro, "Centro de Trabajo Guardado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                pantallacrearcentro.setVisible(false);
            }
        } catch (ExpertoCentroDeTrabajoException ex) {
            Logger.getLogger(ControladorCentroDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void actualizarCentro() {
        //asdfjkagsd
    }

    public void limpiarPantallaCentroDeTrabajo() {
        pantallacrearcentro.getCampoCodigo().setText("");
        pantallacrearcentro.getCampoDescripcion().setText("");

    }

    public void limpiarPantallaEditarCentroDeTrabajo() {
        pantallaEditarCentro.getCampoBuscaCodigo().setText("");
        pantallaEditarCentro.getCampoBuscaNombre().setText("");
        pantallaEditarCentro.getCampoCodigo().setText("");
        pantallaEditarCentro.getCampoDescripcion().setText("");
        pantallaEditarCentro.getCampoNombre().setText("");


    }

    public void crearCentro() {

        iniciarCrearCentro();
    }

    public void editarCentro() {
        iniciarEditarCentro();
    }

    public void iniciarCrearCentro() {
        pantallacrearcentro.setVisible(true);
        pantallacrearcentro.setLocationRelativeTo(null);
    }

    public void iniciarEditarCentro() {
        pantallaEditarCentro.setVisible(true);
        pantallaEditarCentro.setLocationRelativeTo(null);
    }

    public void finalizarCrearCentro() {
        pantallacrearcentro.setVisible(false);
    }

    public void finalizarEditarCentro() {
        pantallaEditarCentro.setVisible(false);
    }
}
