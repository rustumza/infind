/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloCombo;

//import entidadesNegocio.ABC;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author eduardo
 */
public class ModeloComboABC extends DefaultComboBoxModel{

   // private List<ABC> listaABC;


    public ModeloComboABC() {
        addElement("Seleccione una Clasificación");
    }

    /*public ModeloComboABC(List<ABC> nuevalista) {

        listaABC = nuevalista;
        addElement("Seleccione Una Clasificación");
        for (ABC abc : nuevalista) {
            addElement(abc.getNombreClasificacion());

        }

    }*/

   /* public void setListaCombo(List<ABC> nuevalista) {
         listaABC = nuevalista;
        for (ABC abc : nuevalista) {
            addElement(abc.getNombreClasificacion());

        }

    }

    public ModeloComboABC(ModeloComboABC modelo) {
        addElement("Seleccione un tipo");
        listaABC = modelo.getListaABC();
        for (ABC abc : listaABC) {
            addElement(abc.getNombreClasificacion());

        }
    }

    public ModeloComboABC(ComboBoxModel modelo) {
        addElement("Seleccione un tipo");
        listaABC = ((ModeloComboABC) modelo).getListaABC();
        for (ABC abc : listaABC) {
            addElement(abc.getNombreClasificacion());

        }
    }

    public ABC getABCSeleccionado() {
        ABC abcEncontrado = null;
        for (ABC abc : listaABC) {
            if (abc.getNombreClasificacion().equals(super.getSelectedItem().toString())) {
                abcEncontrado = abc;
            }
        }

        return abcEncontrado;

    }

    public List<ABC> getListaABC() {
        return listaABC;
    }

    public void setABCSeleccionado(ABC abc) {
        if (listaABC != null) {
            for (ABC aBc : listaABC) {
                if (aBc.getNombreClasificacion().equals(abc.getNombreClasificacion())) {
                    setSelectedItem(abc.getNombreClasificacion());
                }
            }
        }
    }
*/
}
