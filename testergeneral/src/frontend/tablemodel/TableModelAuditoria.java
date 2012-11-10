package frontend.tablemodel;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Constantes;

public class TableModelAuditoria extends AbstractTableModel{
	SimpleDateFormat sdf=new SimpleDateFormat(ContextManager.getProperty("FORMATO.FECHA.HORA"));
    private String[] columnNames = {Constantes.LB_FECHA.replaceAll(":", ""),Constantes.LB_OBJETO.replaceAll(":", ""),Constantes.LB_OBJETO_DETALLE.replaceAll(":", ""),Constantes.LB_USUARIO.replaceAll(":", ""),Constantes.LB_ACCION.replaceAll(":", ""),Constantes.LB_ESTACION.replaceAll(":", "")};  

    private List<Auditoria> lst;

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
    	
    	Auditoria aud=lst.get(row);
    	
    	if(col==0)
    		return sdf.format(aud.getAudFecha());
    	if(col==1)
    		return  aud.getAudObjeto()==null ?  new String() : aud.getAudObjeto();
    	if(col==2)
    		return  aud.getAudFk()==null ?  new String() : aud.getAudFk();
    	if(col==3)
    		return aud.getAudUsuario();  
    	if(col==4)
    		return aud.getAudAccion();
    	if(col==5)
    		return aud.getAudEstacion();
    	
    	return new String();
    }
    
    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Auditoria> getLstUsuario() {
		return lst;
	}

	public void setLst(List<Auditoria> lst) {
		this.lst = lst;
	}

	public Auditoria getValueAt(int row)
	{
		return lst.get(row);
	}
}
