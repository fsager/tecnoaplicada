package opcionesmultiples.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import opcionesmultiples.domain.Pregunta;
import opcionesmultiples.domain.Respuesta;

public class TableModelRespuesta extends AbstractTableModel {
    private String[] columnNames = {"","Correcta"};    
    private List<Respuesta> lstRespuesta;

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return lstRespuesta.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	if(row>=lstRespuesta.size())
    	{
    		return Object.class;
    	}
    	
    	Respuesta repuesta=lstRespuesta.get(row);
    	
    	if(col==0)
    		return repuesta.getResDetalle();
    	
    	if(col==1)
    	{
    		String correcta=repuesta.getResCorrecta().compareTo("S")==0 ? "Si" : "No"; 
    		return correcta;
    	}
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Respuesta> getLstRespuesta() {
		return lstRespuesta;
	}
	
	public void setLstRespuesta(List<Respuesta> lstRespuesta) {
		this.lstRespuesta = lstRespuesta;
	}

	public Respuesta getValueAt(int row)
	{
		return lstRespuesta.get(row);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
    /*public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }*/
}
