/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOPromedioPonderadoMovil;
import Entidades.Demanda;
import Entidades.MaestroDeArticulo;
import Entidades.Parametros;
import expertos.ExpertoPrediccionDemanda;
import interfacesGraficas.PantallaPrediccionDemanda;
import interfacesGraficas.pantallaPromedioPonderadoMovil;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorPrediccionDemanda {

    PantallaPrediccionDemanda pantallaPrediccion;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoPrediccionDemanda expertoPrediccionDemanda;
    Parametros nuevoParametros = null;

    public ControladorPrediccionDemanda(ControladorPantallaMadre controlador) {
        controladorPantMadre = controlador;
        expertoPrediccionDemanda = new ExpertoPrediccionDemanda();
    }

    public void iniciar() {
        pantallaPrediccion = new PantallaPrediccionDemanda(controladorPantMadre.getPantalla(), false, this);
        List<MaestroDeArticulo> lista = expertoPrediccionDemanda.buscarProductos();
        pantallaPrediccion.getjComboBoxProducto().setModel(new DefaultComboBoxModel(lista.toArray()));
        MaestroDeArticulo articulo = (MaestroDeArticulo)pantallaPrediccion.getjComboBoxProducto().getSelectedItem();
        int tamanio = articulo.getDemanda().size();
        Integer[] listaDeInt = new Integer[tamanio];
        if(tamanio != 0){    
            for (int i = 1; i <=  tamanio; i++) {
                listaDeInt[i-1] = i;

            }
        }else{
            JOptionPane.showMessageDialog(controladorPantMadre.getPantalla(),"Este producto no tiene demandas cargadas", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
        pantallaPrediccion.getjComboBoxperiodoDeInicio().setModel(new DefaultComboBoxModel(listaDeInt));
        activarCosas();
        pantallaPrediccion.setLocationRelativeTo(null);
        pantallaPrediccion.setVisible(true);
    }

    public void activarCosas(){
    
        if(pantallaPrediccion.getjComboBoxMetodo().getSelectedItem().equals("Promedio ponderado movil")){
            pantallaPrediccion.getjComboBoxAlfa().setEnabled(false);
            pantallaPrediccion.getjComboBoxBeta().setEnabled(false);
            pantallaPrediccion.getjComboBoxGama().setEnabled(false);


        }else if(pantallaPrediccion.getjComboBoxMetodo().getSelectedItem().equals("Promedio ponderado Exponencialmente")){
            pantallaPrediccion.getjComboBoxAlfa().setEnabled(true);
            pantallaPrediccion.getjComboBoxBeta().setEnabled(false);
            pantallaPrediccion.getjComboBoxGama().setEnabled(false);

        }else if(pantallaPrediccion.getjComboBoxMetodo().getSelectedItem().equals("Metodo Regresion lineal")){//revisar
            pantallaPrediccion.getjComboBoxAlfa().setEnabled(true);
            pantallaPrediccion.getjComboBoxBeta().setEnabled(false);
            pantallaPrediccion.getjComboBoxGama().setEnabled(false);
        }else{//estacionalidad
            pantallaPrediccion.getjComboBoxAlfa().setEnabled(true);
            pantallaPrediccion.getjComboBoxBeta().setEnabled(false);
            pantallaPrediccion.getjComboBoxGama().setEnabled(true);

        }
    
    }
    
    
    public void predecir() {
        
        MaestroDeArticulo articulo = (MaestroDeArticulo)pantallaPrediccion.getjComboBoxProducto().getSelectedItem();
        if(pantallaPrediccion.getjComboBoxMetodo().getSelectedItem().equals("Promedio ponderado movil")){
            
            int cantidadDePeriodos = Integer.valueOf((String)pantallaPrediccion.getComboBoxPeriodos().getSelectedItem());
            int periodoDesde = (Integer)pantallaPrediccion.getjComboBoxperiodoDeInicio().getSelectedItem();
            List<DTOPromedioPonderadoMovil> list = expertoPrediccionDemanda.calcularPPMovil(cantidadDePeriodos, articulo, periodoDesde);
            double  sumError = (double)0;
            double promError = (double)0;
            int contador = 0;
            for (DTOPromedioPonderadoMovil dTOPromedioPonderadoMovil : list) {
                if(!(dTOPromedioPonderadoMovil.getError() == (double)0)){
                    sumError += dTOPromedioPonderadoMovil.getError();
                    contador++;
                }
            }
            if(list.get(0).getError() == (double)0){
                contador++;
            }
            if(contador !=0){
                promError = sumError/contador;
            }   
            new pantallaPromedioPonderadoMovil(controladorPantMadre.getPantalla(), false).iniciar(list, promError);
          
    


        }else if(pantallaPrediccion.getjComboBoxMetodo().getSelectedItem().equals("Promedio ponderado Exponencialmente")){
            int cantidadDePeriodos = Integer.valueOf((String)pantallaPrediccion.getComboBoxPeriodos().getSelectedItem());
            int periodoDesde = (Integer)pantallaPrediccion.getjComboBoxperiodoDeInicio().getSelectedItem();
            float alfa = Float.valueOf((String)pantallaPrediccion.getjComboBoxAlfa().getSelectedItem());
            List<DTOPromedioPonderadoMovil> list = expertoPrediccionDemanda.calcularPromExpSimple(cantidadDePeriodos, periodoDesde ,alfa, articulo);
            double  sumError = (double)0;
            double promError = (double)0;
            int contador = 0;
            for (DTOPromedioPonderadoMovil dTOPromedioPonderadoMovil : list) {
                if(!(dTOPromedioPonderadoMovil.getError() == (double)0)){
                    sumError += dTOPromedioPonderadoMovil.getError();
                    contador++;
                }
            }
            if(list.get(0).getError() == (double)0){
                contador++;
            }
            if(contador !=0){
                promError = sumError/contador;
            }
            new pantallaPromedioPonderadoMovil(controladorPantMadre.getPantalla(), false).iniciar(list, promError);
            
        }else if(pantallaPrediccion.getjComboBoxMetodo().getSelectedItem().equals("Metodo Regresion lineal")){//revisar
            pantallaPrediccion.getjComboBoxAlfa().setEnabled(true);
            pantallaPrediccion.getjComboBoxBeta().setEnabled(false);
            pantallaPrediccion.getjComboBoxGama().setEnabled(false);
            
        }else{//estacionalidad
            int cantidadDePeriodos = Integer.valueOf((String)pantallaPrediccion.getComboBoxPeriodos().getSelectedItem());
            int periodoDesde = (Integer)pantallaPrediccion.getjComboBoxperiodoDeInicio().getSelectedItem();
            float gama = Float.valueOf((String)pantallaPrediccion.getjComboBoxGama().getSelectedItem());
            float alfa = Float.valueOf((String)pantallaPrediccion.getjComboBoxAlfa().getSelectedItem());
            List<DTOPromedioPonderadoMovil> list = expertoPrediccionDemanda.calcularTendencia(cantidadDePeriodos, periodoDesde ,alfa,gama, articulo);
            double  sumError = (double)0;
            double promError = (double)0;
            int contador = 0;
            for (DTOPromedioPonderadoMovil dTOPromedioPonderadoMovil : list) {
                if(!(dTOPromedioPonderadoMovil.getError() == (double)0)){
                    sumError += dTOPromedioPonderadoMovil.getError();
                    contador++;
                }
            }
            if(list.get(0).getError() == (double)0){
                contador++;
            }
            if(contador !=0){
                promError = sumError/contador;
            }            
            new pantallaPromedioPonderadoMovil(controladorPantMadre.getPantalla(), false).iniciar(list, promError);

        }
        
        
        
    }

    public void cargarCantidadDePeriodos() {
        MaestroDeArticulo articulo = (MaestroDeArticulo)pantallaPrediccion.getjComboBoxProducto().getSelectedItem();
        int tamanio = articulo.getDemanda().size();
        Integer[] listaDeInt = new Integer[tamanio];
        if(tamanio != 0){    
            for (int i = 1; i <=  tamanio; i++) {
                listaDeInt[i-1] = i;

            }
        }else{
            JOptionPane.showMessageDialog(controladorPantMadre.getPantalla(),"Este producto no tiene demandas cargadas", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
        pantallaPrediccion.getjComboBoxperiodoDeInicio().setModel(new DefaultComboBoxModel(listaDeInt));
    }
    
}
