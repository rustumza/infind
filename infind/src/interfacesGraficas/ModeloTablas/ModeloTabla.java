/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import persistencia.ObjetoPersitente;

/**
 *
 * @author diego
 */
public abstract class ModeloTabla extends AbstractTableModel {

    private List listaElementos;
    public static String[] columnName;
    private boolean editable = false;

    public ModeloTabla(String ... columnames) {
        columnName = columnames;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editable;
    }

    public void setCellEditable(boolean edit) {
        editable = edit;
    }

    public void setListaElementos(List nuevaLista) {
        if (listaElementos == null) {
            listaElementos = new ArrayList();
        }
        listaElementos = nuevaLista;
        fireTableDataChanged();
    }

    public List getListaElementos() {
        if(listaElementos == null){
            listaElementos = new ArrayList();
        }
        return listaElementos;
    }
    
    public int getRowCount() {
        if (listaElementos != null) {
            return listaElementos.size();
        } else {
            return 0;
        }
    }

    public int getColumnCount() {
        return columnName.length;
    }

    public abstract Object getValueAt(int rowIndex, int columnIndex);

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    public void addRow(Object newObject) {
        if (listaElementos == null) {
            listaElementos = new ArrayList();
        }
        listaElementos.add(newObject);
        fireTableDataChanged();
    }

    public void addAllRow(List nuevaLista) {
        if ( listaElementos == null) {
            listaElementos = new ArrayList();
        }
        listaElementos.addAll(nuevaLista);
        fireTableDataChanged();
    }

    public Object getRow(int rowIndex) {
        return listaElementos.get(rowIndex);
    }

    public void clear() {
        if(listaElementos==null){
            return;
        }
        listaElementos.clear();
        fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {
        listaElementos.remove(rowIndex);
        fireTableDataChanged();
    }
    
    public void removeElement(ObjetoPersitente obj){
        for (int i=0; i<listaElementos.size();i++) {
            if(((ObjetoPersitente)listaElementos.get(i)).getId().equals(obj.getId())){
                listaElementos.remove(i);
            }
        }
    }

    

}

