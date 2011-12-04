package opcionesmultiples.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import opcionesmultiples.domain.Pregunta;
import testerGeneral.domain.Usuario;

public class TableModelPregunta extends AbstractTableModel {
    private String[] columnNames = {"Pregunta","Exámen"};    
    private List<Pregunta> lstPregunta;

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return lstPregunta.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	if(row>=lstPregunta.size())
    	{
    		return Object.class;
    	}
    	
    	Pregunta pregunta=lstPregunta.get(row);
    	
    	if(col==0)
    		return pregunta.getPreDetalle();
    	if(col==1)
    	{
    		return pregunta.getPreCat();
    	}
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Pregunta> getLstPregunta() {
		return lstPregunta;
	}

	public void setLstPregunta(List<Pregunta> lstPregunta) {
		this.lstPregunta= lstPregunta;
	}

	public Pregunta getValueAt(int row)
	{
		return lstPregunta.get(row);
	}
    /*public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }*/
}
