package frontend.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.domain.Dominio;

public class TableModelDominio extends AbstractTableModel {
	
	private String[] columnNames;  

    public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	private List<Dominio> lst;

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
    	
    	Dominio dom=lst.get(row);
    	
    	if(col==0)
    		return (Object)dom.getDomValorMostrar();
    	
    	return null;
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Dominio> getLstUsuario() {
		return lst;
	}

	public void setLst(List<Dominio> lst) {
		this.lst = lst;
	}

	public Dominio getValueAt(int row)
	{
		return lst.get(row);
	}
}
