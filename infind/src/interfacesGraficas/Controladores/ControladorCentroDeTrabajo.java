/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOCentro;
import DTOs.DTOHerramienta;
import DTOs.DTOMaquina;
import DTOs.DTOOperario;
import Entidades.Herramientas;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.Maquina;
import Entidades.Numerador;
import Entidades.Operario;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCentroDeTrabajoException;
import expertos.ExpertoCentroDeTrabajo;
import interfacesGraficas.ModeloTablas.ModeloTablaAgregaHerramientasNuevo;
import interfacesGraficas.ModeloTablas.ModeloTablaAgregaMaquinasNuevo;
import interfacesGraficas.ModeloTablas.ModeloTablaAgregaOperarioNuevo;
import interfacesGraficas.ModeloTablas.ModeloTablaBuscaHerramientasNuevo;
import interfacesGraficas.ModeloTablas.ModeloTablaBuscaMaquinasNuevo;
import interfacesGraficas.ModeloTablas.ModeloTablaBuscaOperarioNuevo;
import interfacesGraficas.PantallaCrearCentro;
import interfacesGraficas.PantallaEDCentro;
import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author diego
 */
public class ControladorCentroDeTrabajo {

    PantallaCrearCentro pantallacrearcentro;
    PantallaMadre pantallaMadre;
    PantallaEDCentro pantallaEditarCentro;
    ExpertoCentroDeTrabajo expertoCentroDeTrabajo;
    MaestroDeCentroDeTrabajo centroDeTrabajoSeleccionado = null;
    MaestroDeCentroDeTrabajo centroEncontrado = null;
    ControladorPantallaMadre controladorPantallaMadre;
    ModeloTablaBuscaHerramientasNuevo modeloTablaBuscaHerramientasNuevo;
    ModeloTablaAgregaHerramientasNuevo modeloTablaAgregaHerramientasNuevo;
    ModeloTablaAgregaMaquinasNuevo modeloTablaAgregaMaquinasNuevo;
    ModeloTablaAgregaOperarioNuevo modeloTablaAgregaOperarioNuevo;
    ModeloTablaBuscaMaquinasNuevo modeloTablaBuscaMaquinasNuevo;
    ModeloTablaBuscaOperarioNuevo modeloTablaBuscaOperarioNuevo;
    Operario operarioNuevoSeleccionado;
    Maquina maquinaNuevoSeleccionado;
    Herramientas herramientaNuevoSeleccionado;

    public ControladorCentroDeTrabajo(ControladorPantallaMadre contrPantMadre) {
        controladorPantallaMadre = contrPantMadre;
        pantallaMadre = controladorPantallaMadre.getPantalla();
        pantallacrearcentro = new PantallaCrearCentro(pantallaMadre, false);
        pantallaEditarCentro = new PantallaEDCentro(pantallaMadre, false, this);
        expertoCentroDeTrabajo = (ExpertoCentroDeTrabajo) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.CENTRO_DE_TRABAJO);
        modeloTablaAgregaHerramientasNuevo = new ModeloTablaAgregaHerramientasNuevo();
        pantallacrearcentro.getTablaAgregaHerramientaNuevo().setModel(modeloTablaAgregaHerramientasNuevo);
        pantallaEditarCentro.getTablaAgregaHerramientaEditar().setModel(modeloTablaAgregaHerramientasNuevo);
        modeloTablaAgregaOperarioNuevo = new ModeloTablaAgregaOperarioNuevo();
        pantallacrearcentro.getTablaAgregaOperariosNuevo().setModel(modeloTablaAgregaOperarioNuevo);
        pantallaEditarCentro.getTablaAgregaOperariosEditar().setModel(modeloTablaAgregaOperarioNuevo);
        modeloTablaAgregaMaquinasNuevo = new ModeloTablaAgregaMaquinasNuevo();
        pantallacrearcentro.getTablaAgregaMaquinaNuevo().setModel(modeloTablaAgregaMaquinasNuevo);
        pantallaEditarCentro.getTablaAgregaMaquinaEditar().setModel(modeloTablaAgregaMaquinasNuevo);
        modeloTablaBuscaHerramientasNuevo = new ModeloTablaBuscaHerramientasNuevo();
        pantallacrearcentro.getTablaBuscaHerramientaNuevo().setModel(modeloTablaBuscaHerramientasNuevo);
        pantallaEditarCentro.getTablaBuscaHerramientaEditar().setModel(modeloTablaBuscaHerramientasNuevo);
        modeloTablaBuscaMaquinasNuevo = new ModeloTablaBuscaMaquinasNuevo();
        pantallacrearcentro.getTablaBuscaMaquinaNuevo().setModel(modeloTablaBuscaMaquinasNuevo);
        pantallaEditarCentro.getTablaBuscaMaquinaEditar().setModel(modeloTablaBuscaMaquinasNuevo);
        modeloTablaBuscaOperarioNuevo = new ModeloTablaBuscaOperarioNuevo();
        pantallacrearcentro.getTablaBuscaOperariosNuevo().setModel(modeloTablaBuscaOperarioNuevo);
        pantallaEditarCentro.getTablaBuscaOperariosEditar().setModel(modeloTablaBuscaOperarioNuevo);




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

        //busca Operario
        //boton buscar
        pantallacrearcentro.getBotonBuscaOperarioNuevo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    buscarOperarioNuevo();
                } catch (ExpertoCentroDeTrabajoException ex) {
                    JOptionPane.showMessageDialog(pantallacrearcentro, "No se encontró ningún Operario", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                    //limpiarPantallaEditarCentroDeTrabajo();
                }
            }
        });

        //pantalla busca Operarios Nuevo
        pantallacrearcentro.getTablaBuscaOperariosNuevo().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = pantallacrearcentro.getTablaBuscaOperariosNuevo().rowAtPoint(e.getPoint());
                operarioNuevoSeleccionado = modeloTablaBuscaOperarioNuevo.buscarOperario(pantallacrearcentro.getTablaBuscaOperariosNuevo().getValueAt(fila, 1).toString());
                if (e.getClickCount() == 2) {
                    agregarOperarioNuevo();
                }
            }
        });

        pantallacrearcentro.getBotonEliminarOperarioAgregado().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {


                eliminarOperarioNuevo();
            }
        });


        pantallacrearcentro.getBotonEliminarHerramientaAgregada().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                eliminarHerramientaNuevo();
            }
        });


        pantallacrearcentro.getBotonEliminarMaquinaAgregada().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                eliminarMaquinaNuevo();
            }
        });
        //busca MAquinas
        //boton buscar
        pantallacrearcentro.getBotonBuscarMaquinasNuevo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    buscarMaquinaNuevo();
                } catch (ExpertoCentroDeTrabajoException ex) {
                    JOptionPane.showMessageDialog(pantallacrearcentro, "No se encontró ninguna Máquina", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //pantalla busca Operarios Nuevo
        pantallacrearcentro.getTablaBuscaMaquinaNuevo().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = pantallacrearcentro.getTablaBuscaMaquinaNuevo().rowAtPoint(e.getPoint());
                maquinaNuevoSeleccionado = modeloTablaBuscaMaquinasNuevo.buscarMaquina(pantallacrearcentro.getTablaBuscaMaquinaNuevo().getValueAt(fila, 1).toString());
                if (e.getClickCount() == 2) {
                    agregarMaquinaNuevo();
                }
            }
        });

        //busca Herramientas
        //boton buscar
        pantallacrearcentro.getBotonBuscaHerramientaNuevo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    buscarHerramientasNuevo();



                } catch (ExpertoCentroDeTrabajoException ex) {
                    JOptionPane.showMessageDialog(pantallacrearcentro, "No se encontró ninguna Herramienta", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //pantalla busca Operarios Nuevo
        pantallacrearcentro.getTablaBuscaHerramientaNuevo().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = pantallacrearcentro.getTablaBuscaHerramientaNuevo().rowAtPoint(e.getPoint());
                herramientaNuevoSeleccionado = modeloTablaBuscaHerramientasNuevo.buscarHerramientas(pantallacrearcentro.getTablaBuscaHerramientaNuevo().getValueAt(fila, 1).toString());
                if (e.getClickCount() == 2) {
                    agregarHerramientaNuevo();
                }
            }
        });





//pantalla editar centro 

        pantallaEditarCentro.getBotonBuscarCentro().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    try {
                        buscarCentroDeTrabajo();
                    } catch (NoSuchFieldException ex) {
                        Logger.getLogger(ControladorCentroDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

        pantallaEditarCentro.getRadioBotonCodigoBuscaCentro().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (pantallaEditarCentro.getRadioBotonCodigoBuscaCentro().isSelected()) {
                    pantallaEditarCentro.getCampoBuscaCodigoCentro().setEnabled(true);
                    pantallaEditarCentro.getCampoBuscaNombreCentro().setEnabled(false);
                }
            }
        });

        pantallaEditarCentro.getRadioBotonNombreBuscaCentro().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (pantallaEditarCentro.getRadioBotonNombreBuscaCentro().isSelected()) {
                    pantallaEditarCentro.getCampoBuscaNombreCentro().setEnabled(true);
                    pantallaEditarCentro.getCampoBuscaCodigoCentro().setEnabled(false);
                }
            }
        });

        pantallaEditarCentro.getBotonEliminarCentro().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                eliminarCentro();
            }
        });


        pantallaEditarCentro.getTablaBuscaOperariosEditar().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = pantallaEditarCentro.getTablaBuscaOperariosEditar().rowAtPoint(e.getPoint());
                operarioNuevoSeleccionado = modeloTablaBuscaOperarioNuevo.buscarOperario(pantallaEditarCentro.getTablaBuscaOperariosEditar().getValueAt(fila, 1).toString());
                if (e.getClickCount() == 2) {
                    agregarOperarioEditar();
                }
            }
        });

        pantallaEditarCentro.getBotonEliminarOperarioAgregado().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {


                eliminarOperarioEditar();
            }
        });


        pantallaEditarCentro.getBotonEliminarHerramientaAgregada().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                eliminarHerramientaEditar();
            }
        });


        pantallaEditarCentro.getBotonEliminarMaquinaAgregada().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
               eliminarMaquinaEditar();
            }
        });

        pantallaEditarCentro.getTablaBuscaMaquinaEditar().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = pantallaEditarCentro.getTablaBuscaMaquinaEditar().rowAtPoint(e.getPoint());
                maquinaNuevoSeleccionado = modeloTablaBuscaMaquinasNuevo.buscarMaquina(pantallaEditarCentro.getTablaBuscaMaquinaEditar().getValueAt(fila, 1).toString());
                if (e.getClickCount() == 2) {
                    agregarMaquinaEditar();
                }
            }
        });

        pantallaEditarCentro.getTablaBuscaHerramientaEditar().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = pantallaEditarCentro.getTablaBuscaHerramientaEditar().rowAtPoint(e.getPoint());
                herramientaNuevoSeleccionado = modeloTablaBuscaHerramientasNuevo.buscarHerramientas(pantallaEditarCentro.getTablaBuscaHerramientaEditar().getValueAt(fila, 1).toString());
                if (e.getClickCount() == 2) {
                   agregarHerramientaEditar();
                }
            }
        });

    }

    public void buscarOperarioNuevo() throws ExpertoCentroDeTrabajoException {


        if (!modeloTablaBuscaOperarioNuevo.getListaElementos().isEmpty()) {
            modeloTablaBuscaOperarioNuevo.clear();

        }
        if (pantallacrearcentro.getRadioBotonCodigoOperario().isSelected()) {
            modeloTablaBuscaOperarioNuevo.addAllRow(expertoCentroDeTrabajo.buscarOperarios(armarDTOOperario(1)));

        } else if (pantallacrearcentro.getRadioBotonApellidoOperario().isSelected()) {
            modeloTablaBuscaOperarioNuevo.addAllRow(expertoCentroDeTrabajo.buscarOperarios(armarDTOOperario(2)));

        }

    }

    public DTOOperario armarDTOOperario(Integer caso) {

        DTOOperario nuevoDto = new DTOOperario();

        switch (caso) {
            case 1:

                if (!pantallacrearcentro.getCampoBuscaCodigoOperarioNuevo().getText().equals("")) {
                    nuevoDto.setCodigoOperario(pantallacrearcentro.getCampoBuscaCodigoOperarioNuevo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallacrearcentro.getCampoBuscaApellidoOperarioNuevo().getText().equals("")) {
                    nuevoDto.setApellidoOperario(pantallacrearcentro.getCampoBuscaApellidoOperarioNuevo().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void buscarMaquinaNuevo() throws ExpertoCentroDeTrabajoException {


        if (!modeloTablaBuscaMaquinasNuevo.getListaElementos().isEmpty()) {
            modeloTablaBuscaMaquinasNuevo.clear();

        }
        if (pantallacrearcentro.getRadioBotonBuscaCodigoMaquinas().isSelected()) {
            modeloTablaBuscaMaquinasNuevo.addAllRow(expertoCentroDeTrabajo.buscarMaquina(armarDTOMaquina(1)));

        } else if (pantallacrearcentro.getRadioBotonBuscaNombreMaquinas().isSelected()) {
            modeloTablaBuscaMaquinasNuevo.addAllRow(expertoCentroDeTrabajo.buscarMaquina(armarDTOMaquina(2)));

        }

    }

    public DTOMaquina armarDTOMaquina(Integer caso) {

        DTOMaquina nuevoDto = new DTOMaquina();

        switch (caso) {
            case 1:

                if (!pantallacrearcentro.getCampoBuscaCodigoMaquinaNuevo().getText().equals("")) {
                    nuevoDto.setCodigoMaquina(pantallacrearcentro.getCampoBuscaCodigoMaquinaNuevo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallacrearcentro.getCampoBuscaNombreMaquinaNuevo().getText().equals("")) {
                    nuevoDto.setNombreMaquina(pantallacrearcentro.getCampoBuscaNombreMaquinaNuevo().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void buscarHerramientasNuevo() throws ExpertoCentroDeTrabajoException {


        if (!modeloTablaBuscaHerramientasNuevo.getListaElementos().isEmpty()) {
            modeloTablaBuscaHerramientasNuevo.clear();

        }
        if (pantallacrearcentro.getRadioBotonCodigoHerramientas().isSelected()) {
            modeloTablaBuscaHerramientasNuevo.addAllRow(expertoCentroDeTrabajo.buscarHerramienta(armarDTOHerramienta(1)));

        } else if (pantallacrearcentro.getRadioBotonNombreHerramientas().isSelected()) {
            modeloTablaBuscaHerramientasNuevo.addAllRow(expertoCentroDeTrabajo.buscarHerramienta(armarDTOHerramienta(2)));

        }

    }

    public DTOHerramienta armarDTOHerramienta(Integer caso) {

        DTOHerramienta nuevoDto = new DTOHerramienta();

        switch (caso) {
            case 1:

                if (!pantallacrearcentro.getCampoBuscaCodigoHerramientaNuevo().getText().equals("")) {
                    nuevoDto.setCodigoHerramienta(pantallacrearcentro.getCampoBuscaCodigoHerramientaNuevo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallacrearcentro.getCampoBuscaNombreHerramientaNuevo().getText().equals("")) {
                    nuevoDto.setNombreHerramienta(pantallacrearcentro.getCampoBuscaNombreHerramientaNuevo().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void agregarOperarioNuevo() {

        modeloTablaAgregaOperarioNuevo.addRow(operarioNuevoSeleccionado);
        modeloTablaBuscaOperarioNuevo.clear();
        pantallacrearcentro.getCampoBuscaApellidoOperarioNuevo().setText("");
        pantallacrearcentro.getCampoBuscaCodigoOperarioNuevo().setText("");

    }

    public void agregarHerramientaNuevo() {

        modeloTablaAgregaHerramientasNuevo.addRow(herramientaNuevoSeleccionado);
        modeloTablaBuscaHerramientasNuevo.clear();
        pantallacrearcentro.getCampoBuscaCodigoHerramientaNuevo().setText("");
        pantallacrearcentro.getCampoBuscaNombreHerramientaNuevo().setText("");


    }

    public void agregarMaquinaNuevo() {

        modeloTablaAgregaMaquinasNuevo.addRow(maquinaNuevoSeleccionado);
        modeloTablaBuscaMaquinasNuevo.clear();
        pantallacrearcentro.getCampoBuscaCodigoMaquinaNuevo().setText("");
        pantallacrearcentro.getCampoBuscaNombreMaquinaNuevo().setText("");

    }

    public void agregarOperarioEditar(){
        modeloTablaAgregaOperarioNuevo.addRow(operarioNuevoSeleccionado);
        modeloTablaBuscaOperarioNuevo.clear();
        pantallaEditarCentro.getCampoBuscaApellidoOperarioNuevo().setText("");
        pantallaEditarCentro.getCampoBuscaCodigoOperarioNuevo().setText("");

    }

    public void agregarMaquinaEditar(){

        modeloTablaAgregaMaquinasNuevo.addRow(maquinaNuevoSeleccionado);
        modeloTablaBuscaMaquinasNuevo.clear();
        pantallaEditarCentro.getCampoBuscaCodigoMaquinaNuevo().setText("");
        pantallaEditarCentro.getCampoBuscaNombreMaquinaNuevo().setText("");


    }
    public void agregarHerramientaEditar(){

        modeloTablaAgregaHerramientasNuevo.addRow(herramientaNuevoSeleccionado);
        modeloTablaBuscaHerramientasNuevo.clear();
        pantallaEditarCentro.getCampoBuscaCodigoHerramientaNuevo().setText("");
        pantallaEditarCentro.getCampoBuscaNombreHerramientaNuevo().setText("");



    }

    public void eliminarOperarioEditar(){

        int r = pantallaEditarCentro.getTablaAgregaOperariosEditar().getSelectedRow();

        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaEditarCentro, "Debe seleccionar un Operario a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        }else{
        modeloTablaAgregaOperarioNuevo.removeRow(r);
        modeloTablaAgregaOperarioNuevo.fireTableDataChanged();
        }

    }

    public void eliminarOperarioNuevo() {

    

        int r = pantallacrearcentro.getTablaAgregaOperariosNuevo().getSelectedRow();

        if (r == -1) {

            JOptionPane.showMessageDialog(pantallacrearcentro, "Debe seleccionar un Operario a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        }else{
        modeloTablaAgregaOperarioNuevo.removeRow(r);
        modeloTablaAgregaOperarioNuevo.fireTableDataChanged();
        }
    }

    public void eliminarMaquinaNuevo() {

        //TODO: verificar que se halla seleccionado una fila

        int r = pantallacrearcentro.getTablaAgregaMaquinaNuevo().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallacrearcentro, "Debe seleccionar una Máquina a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        }else{
        modeloTablaAgregaMaquinasNuevo.removeRow(r);
        modeloTablaAgregaMaquinasNuevo.fireTableDataChanged();
        }

    }

    public void eliminarMaquinaEditar(){

        int r = pantallaEditarCentro.getTablaAgregaMaquinaEditar().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaEditarCentro, "Debe seleccionar una Máquina a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        }else{
        modeloTablaAgregaMaquinasNuevo.removeRow(r);
        modeloTablaAgregaMaquinasNuevo.fireTableDataChanged();
        }


    }

    public void eliminarHerramientaNuevo() {

        //TODO: verificar que se halla seleccionado una fila para poder eliminarla

        int r = pantallacrearcentro.getTablaAgregaHerramientaNuevo().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallacrearcentro, "Debe seleccionar una Herramienta a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        }else{
        modeloTablaAgregaHerramientasNuevo.removeRow(r);
        modeloTablaAgregaHerramientasNuevo.fireTableDataChanged();

        }
        

    }

    public void eliminarHerramientaEditar(){

        int r = pantallaEditarCentro.getTablaAgregaHerramientaEditar().getSelectedRow();

        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaEditarCentro, "Debe seleccionar una Herramienta a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        }else{
        modeloTablaAgregaHerramientasNuevo.removeRow(r);
        modeloTablaAgregaHerramientasNuevo.fireTableDataChanged();
        }


    }

    public void buscarCentroDeTrabajo() throws ExpertoCentroDeTrabajoException, NoSuchFieldException {


        if (pantallaEditarCentro.getRadioBotonCodigoBuscaCentro().isSelected()) {
            centroEncontrado = expertoCentroDeTrabajo.buscarCentros(armarDTOCentro(1));

            if (!centroEncontrado.getCodigo().isEmpty()) {
                pantallaEditarCentro.getCampoCodigoCentro().setText(centroEncontrado.getCodigo());
                pantallaEditarCentro.getCampoDescripcionCentro().setText(centroEncontrado.getDescripcion());
                pantallaEditarCentro.getCampoNombreCentro().setText(centroEncontrado.getNombreCentro());
                List<Herramientas> herramientas = centroEncontrado.getHerramientas();
                List<Maquina> maquinas = centroEncontrado.getMaquinas();
                List<Operario> operario = centroEncontrado.getOperario();


                modeloTablaAgregaHerramientasNuevo.addAllRow(herramientas);
                modeloTablaAgregaMaquinasNuevo.addAllRow(maquinas);
                modeloTablaAgregaOperarioNuevo.addAllRow(operario);
            }

        } else if (pantallaEditarCentro.getRadioBotonNombreBuscaCentro().isSelected()) {
            centroEncontrado = expertoCentroDeTrabajo.buscarCentros(armarDTOCentro(2));

            if (!centroEncontrado.getNombreCentro().isEmpty()) {
                pantallaEditarCentro.getCampoCodigoCentro().setText(centroEncontrado.getCodigo());
                pantallaEditarCentro.getCampoDescripcionCentro().setText(centroEncontrado.getDescripcion());
                pantallaEditarCentro.getCampoNombreCentro().setText(centroEncontrado.getNombreCentro());
                List<Herramientas> herramientas = centroEncontrado.getHerramientas();
                List<Maquina> maquinas = centroEncontrado.getMaquinas();
                List<Operario> operario = centroEncontrado.getOperario();


                modeloTablaAgregaHerramientasNuevo.addAllRow(herramientas);
                modeloTablaAgregaMaquinasNuevo.addAllRow(maquinas);
                modeloTablaAgregaOperarioNuevo.addAllRow(operario);
            }


        }

    }

    public DTOCentro armarDTOCentro(Integer caso) {

        DTOCentro nuevoDto = new DTOCentro();

        switch (caso) {
            case 1:

                if (!pantallaEditarCentro.getCampoBuscaCodigoCentro().getText().equals("")) {
                    nuevoDto.setCodigoCentro(pantallaEditarCentro.getCampoBuscaCodigoCentro().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaEditarCentro.getCampoBuscaNombreCentro().getText().equals("")) {
                    nuevoDto.setNombreCentro(pantallaEditarCentro.getCampoBuscaNombreCentro().getText());
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
        centroDeTrabajoSeleccionado.setOperario(modeloTablaAgregaOperarioNuevo.getListaElementos());
        centroDeTrabajoSeleccionado.setHerramientas(modeloTablaAgregaHerramientasNuevo.getListaElementos());
        centroDeTrabajoSeleccionado.setMaquinas(modeloTablaAgregaMaquinasNuevo.getListaElementos());


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

                //genero el nuevo numero de la clase numerador para la fila de centros de trabajo

                List<Numerador> numeroDisponibles = null;
                Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
                criterioNumerador.add(Restrictions.eq("codificacion", "7.1.1."));
                numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
                String codifica = numeroDisponibles.get(0).getCodificacion();
                String num = numeroDisponibles.get(0).getUltimaClasificacion();
                int nume = Integer.parseInt(num);
                nume = nume + 1;
                num = String.valueOf(nume);
                numeroDisponibles.get(0).setUltimaClasificacion(num);
                expertoCentroDeTrabajo.guardarNumerador(numeroDisponibles.get(0));


            }
        } catch (ExpertoCentroDeTrabajoException ex) {
            Logger.getLogger(ControladorCentroDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void actualizarCentro() {

        if (centroEncontrado == null) {
            return;
        }

        centroEncontrado.setCodigo(pantallaEditarCentro.getCampoCodigoCentro().getText());
        centroEncontrado.setDescripcion(pantallaEditarCentro.getCampoDescripcionCentro().getText());
        centroEncontrado.setNombreCentro(pantallaEditarCentro.getCampoNombreCentro().getText());
        centroEncontrado.setEliminado(Boolean.FALSE);
        centroEncontrado.setMaquinas(modeloTablaAgregaMaquinasNuevo.getListaElementos());
        centroEncontrado.setOperario(modeloTablaAgregaOperarioNuevo.getListaElementos());
        centroEncontrado.setHerramientas(modeloTablaAgregaHerramientasNuevo.getListaElementos());


        try {

            expertoCentroDeTrabajo.guardar(centroEncontrado);
            limpiarPantallaEditarCentroDeTrabajo();
            centroEncontrado = null;
            JOptionPane.showMessageDialog(pantallacrearcentro, "Centro de Trabajo Actualizado Correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarPantallaEditarCentroDeTrabajo();
            pantallacrearcentro.setVisible(false);

        } catch (ExpertoCentroDeTrabajoException ex) {
            Logger.getLogger(ControladorCentroDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarCentro() {

        if (centroEncontrado != null) {
            int seleccion = JOptionPane.showOptionDialog(null,
                    "Está seguro que desea eliminar el Centro de Trabajo \nCódigo: " + centroEncontrado.getCodigo() + ". Nombre: " + centroEncontrado.getNombreCentro(),
                    "ELIMINAR CENTRO DE TRABAJO",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, // null para icono por defecto.
                    new Object[]{"Aceptar", "Cancelar"}, // null para YES, NO y CANCEL
                    "Cancelar");
            if (seleccion == 0) {
                try {
                    //centroEncontrado.setEliminado(Boolean.TRUE);
                    expertoCentroDeTrabajo.eliminar(centroEncontrado);
                    JOptionPane.showMessageDialog(pantallaEditarCentro, "Centro de Trabajo Eliminado Correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    limpiarPantallaEditarCentroDeTrabajo();

                } catch (ExpertoCentroDeTrabajoException ex) {
                    JOptionPane.showMessageDialog(pantallaEditarCentro, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }


            }
        } else {
            JOptionPane.showMessageDialog(pantallaEditarCentro, "Debe seleccionar un Centro de Trabajo", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        }


    }

    public void limpiarPantallaCentroDeTrabajo() {
        pantallacrearcentro.getCampoCodigo().setText("");
        pantallacrearcentro.getCampoDescripcion().setText("");

    }

    public void limpiarPantallaEditarCentroDeTrabajo() {
        pantallaEditarCentro.getCampoBuscaCodigoCentro().setText("");
        pantallaEditarCentro.getCampoBuscaNombreCentro().setText("");
        pantallaEditarCentro.getCampoCodigoCentro().setText("");
        pantallaEditarCentro.getCampoDescripcionCentro().setText("");
        pantallaEditarCentro.getCampoNombreCentro().setText("");
        modeloTablaAgregaHerramientasNuevo.clear();
        modeloTablaAgregaMaquinasNuevo.clear();
        modeloTablaAgregaOperarioNuevo.clear();





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

        // busco el último número y le sumo 1 para obtener el nuevo código del centro de trabajo
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        criterioNumerador.add(Restrictions.eq("codificacion", "7.1.1."));
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        String codifica = numeroDisponibles.get(0).getCodificacion();
        String num = numeroDisponibles.get(0).getUltimaClasificacion();
        int nume = Integer.parseInt(num);
        nume = nume + 1;
        num = String.valueOf(nume);
        String nuevoNumero = codifica + num;
        pantallacrearcentro.getCampoCodigo().setText(nuevoNumero);

    }

    public void buscarOperarioEditar() throws ExpertoCentroDeTrabajoException {


        if (!modeloTablaBuscaOperarioNuevo.getListaElementos().isEmpty()) {
            modeloTablaBuscaOperarioNuevo.clear();

        }
        if (pantallaEditarCentro.getRadioBotonCodigoOperario().isSelected()) {
            modeloTablaBuscaOperarioNuevo.addAllRow(expertoCentroDeTrabajo.buscarOperarios(armarDTOOperarioEditar(1)));

        } else if (pantallaEditarCentro.getRadioBotonApellidoOperario().isSelected()) {
            modeloTablaBuscaOperarioNuevo.addAllRow(expertoCentroDeTrabajo.buscarOperarios(armarDTOOperarioEditar(2)));

        }

    }

    public DTOOperario armarDTOOperarioEditar(Integer caso) {

        DTOOperario nuevoDto = new DTOOperario();

        switch (caso) {
            case 1:

                if (!pantallaEditarCentro.getCampoBuscaCodigoOperarioNuevo().getText().equals("")) {
                    nuevoDto.setCodigoOperario(pantallaEditarCentro.getCampoBuscaCodigoOperarioNuevo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaEditarCentro.getCampoBuscaApellidoOperarioNuevo().getText().equals("")) {
                    nuevoDto.setApellidoOperario(pantallaEditarCentro.getCampoBuscaApellidoOperarioNuevo().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void buscarMaquinaEditar() throws ExpertoCentroDeTrabajoException {


        if (!modeloTablaBuscaMaquinasNuevo.getListaElementos().isEmpty()) {
            modeloTablaBuscaMaquinasNuevo.clear();

        }
        if (pantallaEditarCentro.getRadioBotonBuscaCodigoMaquinas().isSelected()) {
            modeloTablaBuscaMaquinasNuevo.addAllRow(expertoCentroDeTrabajo.buscarMaquina(armarDTOMaquinaEditar(1)));

        } else if (pantallaEditarCentro.getRadioBotonBuscaNombreMaquinas().isSelected()) {
            modeloTablaBuscaMaquinasNuevo.addAllRow(expertoCentroDeTrabajo.buscarMaquina(armarDTOMaquinaEditar(2)));

        }

    }

    public DTOMaquina armarDTOMaquinaEditar(Integer caso) {

        DTOMaquina nuevoDto = new DTOMaquina();

        switch (caso) {
            case 1:

                if (!pantallaEditarCentro.getCampoBuscaCodigoMaquinaNuevo().getText().equals("")) {
                    nuevoDto.setCodigoMaquina(pantallaEditarCentro.getCampoBuscaCodigoMaquinaNuevo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaEditarCentro.getCampoBuscaNombreMaquinaNuevo().getText().equals("")) {
                    nuevoDto.setNombreMaquina(pantallaEditarCentro.getCampoBuscaNombreMaquinaNuevo().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void buscarHerramientasEditar() throws ExpertoCentroDeTrabajoException {


        if (!modeloTablaBuscaHerramientasNuevo.getListaElementos().isEmpty()) {
            modeloTablaBuscaHerramientasNuevo.clear();

        }
        if (pantallaEditarCentro.getRadioBotonCodigoHerramientas().isSelected()) {
            modeloTablaBuscaHerramientasNuevo.addAllRow(expertoCentroDeTrabajo.buscarHerramienta(armarDTOHerramientaEditar(1)));

        } else if (pantallacrearcentro.getRadioBotonNombreHerramientas().isSelected()) {
            modeloTablaBuscaHerramientasNuevo.addAllRow(expertoCentroDeTrabajo.buscarHerramienta(armarDTOHerramientaEditar(2)));

        }

    }

    public DTOHerramienta armarDTOHerramientaEditar(Integer caso) {

        DTOHerramienta nuevoDto = new DTOHerramienta();

        switch (caso) {
            case 1:

                if (!pantallaEditarCentro.getCampoBuscaCodigoHerramientaNuevo().getText().equals("")) {
                    nuevoDto.setCodigoHerramienta(pantallaEditarCentro.getCampoBuscaCodigoHerramientaNuevo().getText());
                } else {
                    nuevoDto = null;
                }

                break;
            case 2:

                if (!pantallaEditarCentro.getCampoBuscaNombreHerramientaNuevo().getText().equals("")) {
                    nuevoDto.setNombreHerramienta(pantallaEditarCentro.getCampoBuscaNombreHerramientaNuevo().getText());
                } else {
                    nuevoDto = null;
                }
                break;
        }
        return nuevoDto;
    }

    public void iniciarEditarCentro() {
        pantallaEditarCentro.setVisible(true);
        pantallaEditarCentro.setLocationRelativeTo(null);
    }

    public void finalizarCrearCentro() {
        pantallacrearcentro.dispose();
    }

    public void finalizarEditarCentro() {
        pantallaEditarCentro.dispose();
    }
}
