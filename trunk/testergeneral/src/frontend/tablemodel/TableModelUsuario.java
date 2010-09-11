package frontend.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Usuario;

public class TableModelUsuario extends AbstractTableModel {
    private String[] columnNames = {Constantes.LB_USUARIO.replaceAll(":", ""),Constantes.LB_HABILITADO.replaceAll(":", "")};    
    private List<Usuario> lstUsuario;

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return lstUsuario.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	if(row>=lstUsuario.size())
    	{
    		return Object.class;
    	}
    	
    	Usuario usuario=lstUsuario.get(row);
    	
    	if(col==0)
    		return usuario.getUsrNombre();
    	if(col==1)
    	{
    		String habilitado=usuario.getUsrHabilitadoSn().compareTo("S")==0 ? "Si" : "No"; 
    		return habilitado;
    	}
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(List<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	public Usuario getValueAt(int row)
	{
		return lstUsuario.get(row);
	}
    /*public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }*/
}
