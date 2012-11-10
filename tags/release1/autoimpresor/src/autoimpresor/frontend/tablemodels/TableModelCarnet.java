package autoimpresor.frontend.tablemodels;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.business.ContextManager;
import autoimpresor.domain.CarnetLicencias;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;

public class TableModelCarnet extends AbstractTableModel {
	SimpleDateFormat sdf=new SimpleDateFormat(ContextManager.getProperty("FORMATO.FECHA"));
    private String[] columnNames = {"Fecha Impresión","Cant. de Impresiones","Nombre Completo","Documento","Municipalidad","Clase"};  

    private List<CarnetLicencias> lst;

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
    	
    	CarnetLicencias car=lst.get(row);
    	
    	if(col==0)
    		return  car.getCliFechaImpresion()!=null ? sdf.format(car.getCliFechaImpresion()) : "";
    	if(col==1)
    		return  car.getCliCantImpresiones()!=null ? car.getCliCantImpresiones() : "";
    	if(col==2)
    		return  car.getPerNombreCompleto();
    	if(col==3)
    		return  car.getPerTipoDoc()+" - "+car.getPerNumeroDoc();
    	if(col==4)
    		return  car.getMncNombre();
    	if(col==5)
    		return  car.getLicClase(); 
    	
    	    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<CarnetLicencias> getLst() {
		return lst;
	}

	public void setLst(List<CarnetLicencias> lst) {
		this.lst = lst;
		this.fireTableStructureChanged();
	}

	public CarnetLicencias getValueAt(int row)
	{
		return lst.get(row);
	}
	
	public void removeAll()
	{
		lst.clear();
		this.fireTableStructureChanged();
	}
	
	
	
}
