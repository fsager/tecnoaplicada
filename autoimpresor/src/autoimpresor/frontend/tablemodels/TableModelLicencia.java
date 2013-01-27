package autoimpresor.frontend.tablemodels;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.business.ContextManager;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;

public class TableModelLicencia extends AbstractTableModel {
	SimpleDateFormat sdf=new SimpleDateFormat(ContextManager.getProperty("FORMATO.FECHA"));
    private String[] columnNames = {"Código de Seguridad","Clase","Trámite","Otorgada","Vencimiento"};  

    private List<Licencia> lst;

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
    	
    	Licencia lic=lst.get(row);
    	
    	if(col==0)
    		return lic.getLicCodLicencia();
    	if(col==1)
    		return lic.getLicClase();
    	if(col==2)
    		return  lic.getLicTramite();
    	if(col==3)
    		return  sdf.format(lic.getLicFechaOtorgada());
    	if(col==4)
    		return  sdf.format(lic.getLicFechaVencimiento());
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Licencia> getLstUsuario() {
		return lst;
	}

	public void setLst(List<Licencia> lst) {
		this.lst = lst;
	}

	public Licencia getValueAt(int row)
	{
		return lst.get(row);
	}
}
