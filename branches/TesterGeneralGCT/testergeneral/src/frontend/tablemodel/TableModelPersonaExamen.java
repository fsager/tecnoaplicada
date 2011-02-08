package frontend.tablemodel;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.ResultadoDetalleExamen;
import examenes.util.ExamenesUtils;
import frontend.utils.Util;

public class TableModelPersonaExamen extends AbstractTableModel {
	private String[] columnNames = {"Examen","Fecha","Estado","Resultado"};
	private SimpleDateFormat sdf=new SimpleDateFormat(ContextManager.getProperty("FORMATO.FECHA.HORA"));
    private List<PersonaExamen> lst;

    public TableModelPersonaExamen()
    {
    }
    
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
    	
    	PersonaExamen personaExamen=lst.get(row);
    	
    	if(col==0)
    		return personaExamen.getExamen().getExaNombre();
    	if(col==1)
    		return sdf.format(personaExamen.getPexaFecha());;
    	if(col==2)
    		return personaExamen.getPexaEstado();
    	if(col==3)
    		return  personaExamen.getPexaResultadoMedico()==null ? new String() : personaExamen.getPexaResultadoMedico() ;
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<PersonaExamen> getLstUsuario() {
		return lst;
	}

	public void setLst(List<PersonaExamen> lst) {
		this.lst = lst;
	}

	public PersonaExamen getValueAt(int row)
	{
		return lst.get(row);
	}
}
