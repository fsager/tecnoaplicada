package frontend.tablemodel;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Persona;

public class TableModelPersona extends AbstractTableModel {
	SimpleDateFormat sdf=new SimpleDateFormat(ContextManager.getProperty("FORMATO.FECHA.HORA"));
    private String[] columnNames = {Constantes.LB_NOMBRE_COMPLETO.replaceAll(":", ""),Constantes.LB_NRO_DOCUMENTO.replaceAll(":", "")};  

    private List<Persona> lst;

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
    	
    	Persona per=lst.get(row);
    	
    	if(col==0)
    		return per.getPerNombreCompleto();
    	if(col==1)
    		return  per.getPerNumeroDoc();
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Persona> getLstUsuario() {
		return lst;
	}

	public void setLst(List<Persona> lst) {
		this.lst = lst;
	}

	public Persona getValueAt(int row)
	{
		return lst.get(row);
	}
}
