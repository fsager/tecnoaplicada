package frontend.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import testerGeneral.domain.Resultado;
import frontend.utils.Util;

public class TableModelResultado extends AbstractTableModel {
	private String[] columnNames =null;
	private String[] unidades;
	public final static int SOLO_ERRORES=0;
	public final static int ERRORES_Y_RESULTADO=1;
	public final static int SOLO_RESULTADO = 2;
	
	private int colContendido;

    private List<Resultado> lst;

    public TableModelResultado(int colContendido,String unidades[])
    {
    	this.colContendido=colContendido;
    	if(colContendido==SOLO_ERRORES)
    	{
    		 String[] columnNames = {"Etapa","Errores"};
    		 this.columnNames=columnNames;
    	}
    	else if(colContendido==ERRORES_Y_RESULTADO)
    	{
	   		 String[] columnNames = {"Etapa","Resultado","Errores"};
			 this.columnNames=columnNames;		 
    	}
    	else if(colContendido==SOLO_RESULTADO)
    	{
	   		 String[] columnNames = {"Etapa","Resultado"};
			 this.columnNames=columnNames;		 
    	}
    	
    	this.unidades=unidades;
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
    	
    	Resultado res=lst.get(row);
    	
    	String etapa=(res.getResEtapa()+1)+" ";
    	if(res.getResEtapaDesc()!=null)
    		etapa+="-"+res.getResEtapaDesc();
    	
    	if(col==0)
    		return etapa;
    	
    	if(colContendido==SOLO_ERRORES)
    	{
        	if(col==1)
        		return  Util.redondear(res.getResValor2());
    	}
    	else if(colContendido==SOLO_RESULTADO)
    	{
        	if(col==1)
        		return  Util.redondear(res.getResValor1())+" "+unidades[0];
    	}
    	else if(colContendido==ERRORES_Y_RESULTADO)
    	{
        	if(col==1)
        		return  res.getResValor1()==null ? "-" :Util.redondear(res.getResValor1())+" "+unidades[0];
        	if(col==2)
        		return  res.getResValor2()==null ? 0 : Util.redondear(res.getResValor2());
    	}
    	
    	return new String();
    }

    public Class getColumnClass(int c) {
    	if(getValueAt(0, c)!=null)
    		return getValueAt(0, c).getClass();
    	else
    		return null;
    }

	public List<Resultado> getLstUsuario() {
		return lst;
	}

	public void setLst(List<Resultado> lst) {
		this.lst = lst;
	}

	public Resultado getValueAt(int row)
	{
		return lst.get(row);
	}
}
