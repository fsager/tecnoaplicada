package frontend.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.ResultadoDetalleExamen;
import examenes.util.ExamenesUtils;
import frontend.utils.Util;

public class TableModelResultadoExamen extends AbstractTableModel {
	private String[] columnNames = {"Evaluación","Valor Promedio","Errores","Resultado"};

    private List<ResultadoDetalleExamen> lst;

    public TableModelResultadoExamen()
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
    	
    	ResultadoDetalleExamen rdexamen=lst.get(row);
    	String unidades[]=ExamenesUtils.getUnidadesExamen(rdexamen.getExamenDetalle());
    	
    	if(col==0)
    		return rdexamen.getExamenDetalle().getExadDetalle();
    	if(col==1)
    		return  rdexamen.getRdeNota()!=null ? Util.redondear(rdexamen.getRdeNota())+" "+unidades[0] : "N/A";
    	if(col==2)
    		return  rdexamen.getRdeNota2()!=null ? Util.redondear(rdexamen.getRdeNota2()) : "N/A";
    	if(col==3)
    		return  rdexamen.getRdeResultado()!=null ? rdexamen.getRdeResultado() : "NO REALIZADO";
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<ResultadoDetalleExamen> getLstUsuario() {
		return lst;
	}

	public void setLst(List<ResultadoDetalleExamen> lst) {
		this.lst = lst;
	}

	public ResultadoDetalleExamen getValueAt(int row)
	{
		return lst.get(row);
	}
}
