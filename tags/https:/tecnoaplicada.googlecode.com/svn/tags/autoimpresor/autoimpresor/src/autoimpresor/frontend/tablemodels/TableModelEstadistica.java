package autoimpresor.frontend.tablemodels;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Dominio;
import testerGeneral.service.DominioDefinition;
import autoimpresor.domain.CarnetLicencias;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;

public class TableModelEstadistica extends AbstractTableModel {
	SimpleDateFormat sdf=new SimpleDateFormat(ContextManager.getProperty("FORMATO.FECHA"));
    private String[] columnNames = {"","Cantidad"};
    private String domainToConvert;

    private List<Object[]> lst;

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return lst.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public void setPrimerColumna(String nombre)
    {
    	columnNames[0]=nombre;    	
    }

    public Object getValueAt(int row, int col) {
    	
    	if(row>=lst.size())
    	{
    		return Object.class;
    	}
    	
    	Object[] obj=lst.get(row);
    	
    	if(col==0)
    	{
    		if(domainToConvert!=null && obj[0]!=null)
    		{
    			try
    			{
        			String codigo=(String)obj[0];
        			Dominio dominio=new Dominio();
        			dominio.setDomClave(domainToConvert);
        			dominio.setDomCodigo(codigo);
        			DominioDefinition dominioService=(DominioDefinition)ContextManager.getBizObject("dominioService");		
        			List<Dominio> lst=dominioService.getAll(dominio);
        			
        			if(lst.size()>0)
        			{
        				return lst.get(0).getDomValorMostrar();
        			}
        			else
        				return "Valor no definido";
    			}
    			catch(Exception e)
    			{
    				throw new RuntimeException(e);
    			}
    		}
    		else
    			return  obj[0]!=null ? obj[0] : "";
    	}
    	if(col==1)
    		return  obj[1]!=null ? obj[1] : "";   
    	
    	    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Object[]> getLst() {
		return lst;
	}

	public void setLst(List<Object[]> lst) {
		this.lst = lst;
		this.fireTableStructureChanged();
	}

	public Object[] getValueAt(int row)
	{
		return lst.get(row);
	}
	
	public void removeAll()
	{
		lst.clear();
		this.fireTableStructureChanged();
	}
	
	public String getDomainToConvert() {
		return domainToConvert;
	}
	
	public void setDomainToConvert(String domainToConvert) {
		this.domainToConvert = domainToConvert;
	}
	
}
