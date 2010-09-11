package autoimpresor.frontend.tablemodels;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.business.ContextManager;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;

public class TableModelClaseLicencia extends AbstractTableModel {
	
    private String[] columnNames = {"Clase","Descripción corta","Descripción","Vigencia predeterminada", "Edad mínima", "Edad máxima"};  

    private List<ClaseLicencia> lst;

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return lst.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	if(row>=lst.size())
    	{
    		return Object.class;
    	}
    	
    	ClaseLicencia claseLic=lst.get(row);
    	
    	if(col==0)
    		return claseLic.getCllNombreClase();
    	if(col==1)
    		return  claseLic.getCllDescripcionCorta();
    	if(col==2)
    		return  claseLic.getCllDescripcion();
    	if(col==3)
    		return  claseLic.getCllVigenciaPredeterminada();
    	if(col==4)
    		return  claseLic.getCllEdadMinima();
    	if(col==5)
    		return  claseLic.getCllEdadMaxima();
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<ClaseLicencia> getLstUsuario() {
		return lst;
	}

	public void setLst(List<ClaseLicencia> lst) {
		this.lst = lst;
	}

	public ClaseLicencia getValueAt(int row)
	{
		return lst.get(row);
	}
}
