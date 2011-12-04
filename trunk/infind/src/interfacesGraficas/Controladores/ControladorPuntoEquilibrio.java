/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOPuntoEquilibrio;
import Entidades.DetalleEstructuraDeProducto;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.MaestroDeArticulo;
import Entidades.Operario;
import Entidades.ProductoFinal;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoPuntoEquilibrioException;
import expertos.ExpertoPuntoEquillibrio;
import interfacesGraficas.ModeloTablas.ModeloTablaPuntoEquilibrio;
import interfacesGraficas.PantallaPuntoDeEquilibrio;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class ControladorPuntoEquilibrio {

    ControladorPantallaMadre controlador;
    PantallaPuntoDeEquilibrio pantallaPuntoEquil;
    ModeloTablaPuntoEquilibrio modeloTablaPuntoEqui;
    ExpertoPuntoEquillibrio expertoPuntoEquilibrio;
    MaestroDeArticulo productoEstandar;
    private List<MaestroDeArticulo> productoFinal;
    private NumberFormat nF = NumberFormat.getNumberInstance();

    public ControladorPuntoEquilibrio(ControladorPantallaMadre control) {

        controlador = control;
        expertoPuntoEquilibrio = (ExpertoPuntoEquillibrio) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.PUNTO_EQUILIBRIO);
        nF.setMaximumFractionDigits(2);
    }

    void iniciar() throws ExpertoPuntoEquilibrioException {


        pantallaPuntoEquil = new PantallaPuntoDeEquilibrio(controlador.getPantalla(), true, this);

        modeloTablaPuntoEqui = new ModeloTablaPuntoEquilibrio();
        pantallaPuntoEquil.getTablaPuntoEquilibrio().setModel(modeloTablaPuntoEqui);

        pantallaPuntoEquil.getComboBoxProducto().setModel(new DefaultComboBoxModel(expertoPuntoEquilibrio.buscarProducto().toArray()));


////////////////////////////////// cargo los datos de los costos estandares y relaciones//////////////////////////////



        calcularCostosVariables();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pantallaPuntoEquil.setLocationRelativeTo(null);
        pantallaPuntoEquil.setVisible(true);

    }

    public void buscarProducto() throws ExpertoPuntoEquilibrioException {
        if (pantallaPuntoEquil.getCampoVolumen().getText().equals("")) {
            
            JOptionPane.showMessageDialog(pantallaPuntoEquil, "Debe ingresar un Volúmen de venta", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (!modeloTablaPuntoEqui.getListaElementos().isEmpty()) {
                modeloTablaPuntoEqui.clear();
                modeloTablaPuntoEqui.fireTableDataChanged();
            }
            pantallaPuntoEquil.getCampoPE().setText("");
            //busco los historiales de demandas y costos del producto seleccinado por pantalla
            ProductoFinal productoSeleccionado = (ProductoFinal) pantallaPuntoEquil.getComboBoxProducto().getSelectedItem();
            List<DTOPuntoEquilibrio> dtoPE = new ArrayList<DTOPuntoEquilibrio>();
            Integer valorVolumen = Integer.valueOf(pantallaPuntoEquil.getCampoVolumen().getText());
            double costoVariableProductoSeleccionado = costoVariableProductoSeleccionado();
            dtoPE = expertoPuntoEquilibrio.calcularFilaDeTabla(valorVolumen, productoSeleccionado, costoVariableProductoSeleccionado);
            modeloTablaPuntoEqui.addAllRow(dtoPE);
            pantallaPuntoEquil.getCampoPE().setText(dtoPE.get(0).getPuntoEquilibrio());
            pantallaPuntoEquil.getTablaPuntoEquilibrio().setModel(modeloTablaPuntoEqui);
        }

    }

    public void calcularCostosVariables() {

        List<DetalleEstructuraDeProducto> detalleEstructuraProductoMateriasPrimas = new ArrayList<DetalleEstructuraDeProducto>();
        List<ProductoFinal> prod = expertoPuntoEquilibrio.buscarProductoFinal();

        for (ProductoFinal productoFinal : prod) {

            if (productoFinal.getNombre().equals("Detergente de limon")) {

                double costoTotalMAteriasPrimas = 0.0;
                double costoTOtalManoDeObra = 0.0;
                double costoTotalGastosFAbricacion = 0.0;
                double costoVariableTotal = 0.0;

                //busco los costos de materia prima para un lote optimo
                detalleEstructuraProductoMateriasPrimas = productoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoMateriasPrimas) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : productoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                //busco los costos de mano de obra
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion) {
                    for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                        costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                    }

                }
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : productoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {
                    if (etapaDeRutaDeFabricacion.getMaestroCentroTrabajo() != null) {

                        for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                            costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                        }
                    }


                }
                //busco los gastos de fabricacion
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion1 = productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();

                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion1) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }
                detalleEstructuraProductoMateriasPrimas = null;

                costoVariableTotal = costoTOtalManoDeObra + costoTotalGastosFAbricacion + costoTotalMAteriasPrimas;
                pantallaPuntoEquil.getCampoCostoEstandar().setText(String.valueOf(costoVariableTotal));
                pantallaPuntoEquil.getCostoEstandarDetergente().setText(String.valueOf(costoVariableTotal));
                pantallaPuntoEquil.getCostoEstandarEnjuague().setText(String.valueOf(costoVariableTotal));
            } else if (productoFinal.getNombre().equals("Desodorante para pisos lavanda")) {

                double costoTotalMAteriasPrimas = 0.0;
                double costoTOtalManoDeObra = 0.0;
                double costoTotalGastosFAbricacion = 0.0;
                double costoVariableTotal = 0.0;
                //busco los costos de materia prima para un lote optimo
                detalleEstructuraProductoMateriasPrimas = productoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoMateriasPrimas) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : productoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                //busco los costos de mano de obra
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion) {
                    for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                        costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                    }

                }
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : productoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {
                    if (etapaDeRutaDeFabricacion.getMaestroCentroTrabajo() != null) {

                        for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                            costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                        }
                    }


                }
                //busco los gastos de fabricacion
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion1 = productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();

                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion1) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }
                detalleEstructuraProductoMateriasPrimas = null;
                costoVariableTotal = costoTOtalManoDeObra + costoTotalGastosFAbricacion + costoTotalMAteriasPrimas;
                pantallaPuntoEquil.getCampoCostoDetergente().setText(String.valueOf(costoVariableTotal));

            } else if (productoFinal.getNombre().equals("Enjuague para ropa imitacion vivere")) {
                double costoTotalMAteriasPrimas = 0.0;
                double costoTOtalManoDeObra = 0.0;
                double costoTotalGastosFAbricacion = 0.0;
                double costoVariableTotal = 0.0;

                //busco los costos de materia prima para un lote optimo
                detalleEstructuraProductoMateriasPrimas = productoFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoMateriasPrimas) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : productoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                //busco los costos de mano de obra
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion) {
                    for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                        costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                    }

                }
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : productoFinal.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {
                    if (etapaDeRutaDeFabricacion.getMaestroCentroTrabajo() != null) {

                        for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                            costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                        }
                    }


                }
                //busco los gastos de fabricacion
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion1 = productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();

                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion1) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : productoFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }
                detalleEstructuraProductoMateriasPrimas = null;
                costoVariableTotal = costoTOtalManoDeObra + costoTotalGastosFAbricacion + costoTotalMAteriasPrimas;
                pantallaPuntoEquil.getCampoCostoEnjuague().setText(String.valueOf(costoVariableTotal));
            }

        }
        String text = pantallaPuntoEquil.getCampoCostoEstandar().getText();
        String text1 = pantallaPuntoEquil.getCampoCostoEnjuague().getText();
        String text2 = pantallaPuntoEquil.getCampoCostoDetergente().getText();

        double relacionEnjuague = 0.0;
        double relacionDesodorante = 0.0;

        relacionDesodorante = Double.valueOf(text2) / Double.valueOf(text);
        relacionEnjuague = Double.valueOf(text1) / Double.valueOf(text);
        pantallaPuntoEquil.getCampoRelacionDetergente().setText(String.valueOf(nF.format(relacionDesodorante)));
        pantallaPuntoEquil.getCampoRelacionEnjuague().setText(String.valueOf(nF.format(relacionEnjuague)));

    }

    public double costoVariableProductoSeleccionado() {


        List<DetalleEstructuraDeProducto> detalleEstructuraProductoMateriasPrimas = new ArrayList<DetalleEstructuraDeProducto>();
        ProductoFinal prod = expertoPuntoEquilibrio.buscarProductoFinal((ProductoFinal) pantallaPuntoEquil.getComboBoxProducto().getSelectedItem());

        double costoTotalMAteriasPrimas = 0.0;
        double costoTOtalManoDeObra = 0.0;
        double costoTotalGastosFAbricacion = 0.0;
        double costoVariableTotal = 0.0;

        //busco los costos de materia prima para un lote optimo
        detalleEstructuraProductoMateriasPrimas = prod.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoMateriasPrimas) {

            costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
        }
        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : prod.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {

            costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
        }
        //busco los costos de mano de obra
        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = prod.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion) {
            for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
            }

        }
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : prod.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {
            if (etapaDeRutaDeFabricacion.getMaestroCentroTrabajo() != null) {

                for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                    costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                }
            }


        }
        //busco los gastos de fabricacion
        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion1 = prod.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();

        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion1) {

            costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
        }
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : prod.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {

            costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
        }
        detalleEstructuraProductoMateriasPrimas = null;

        costoVariableTotal = costoTOtalManoDeObra + costoTotalGastosFAbricacion + costoTotalMAteriasPrimas;

        return costoVariableTotal;
    }
    
    
    
    // hacer todo lo listado de PE del producto standard por periodo 
}
