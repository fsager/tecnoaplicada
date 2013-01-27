package autoimpresor.frontend.tablemodels;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.UsuarioCommon;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;

public class TableModelLicenciaFull extends AbstractTableModel {
	SimpleDateFormat sdf=new SimpleDateFormat(ContextManager.getProperty("FORMATO.FECHA"));
    private String[] columnNames = {"Nombre Completo","Documento","Código de Seguridad","Trámite","Otorgada","Vencimiento","Clase","Importe","Observaciones","Confeccionó","Responsable"};
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
    		return  lic.getPersona().getPerNombreCompleto();
    	if(col==1)
    		return  lic.getPersona().getPerTipoDoc()+" - "+lic.getPersona().getPerNumeroDoc();
    	if(col==2)
    		return  lic.getLicCodLicencia();
    	if(col==3)
    		return  lic.getLicTramite();
    	if(col==4)
    		return  sdf.format(lic.getLicFechaOtorgada());
    	if(col==5)
    		return  sdf.format(lic.getLicFechaVencimiento());
    	if(col==6)
    		return lic.getLicClase();
    	if(col==7)
    		return lic.getLicImporte()!=null ? lic.getLicImporte() : 0;
    	if(col==8)
    		return lic.getLicObservaciones()!=null ? lic.getLicObservaciones() : "";
    	if(col==9)
    		return lic.getUsuarioByUsrConfeccionoLicencia().getUsrNombre();
    	if(col==10)
    		return lic.getUsuarioByUsrNombreResponsable().getUsrNombre();
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Licencia> getLst() {
		return lst;
	}

	public void setLst(List<Licencia> lst) {
		this.lst = lst;
		this.fireTableStructureChanged();
	}

	public Licencia getValueAt(int row)
	{
		return lst.get(row);
	}
	
	public void removeAll()
	{
		lst.clear();
		this.fireTableStructureChanged();
	}
	
	
	
}
