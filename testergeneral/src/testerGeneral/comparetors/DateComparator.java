package testerGeneral.comparetors;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import testerGeneral.business.ContextManager;

public class DateComparator implements Comparator{

	private String formatoFechaHora = ContextManager.getProperty("FORMATO.FECHA.HORA");
	private String formatoFecha = ContextManager.getProperty("FORMATO.FECHA");
	private SimpleDateFormat sdf =null;
	//private SimpleDateFormat sdfHora = new SimpleDateFormat(formatoFechaHora);

	public DateComparator()
	{
		sdf= new SimpleDateFormat(formatoFechaHora);
	}
	
	public DateComparator(int i)
	{
		sdf =new SimpleDateFormat(formatoFecha);
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		try
		{
			String obj1=(String)o1;
			String obj2=(String)o2;
			Date d1=sdf.parse(obj1);
			Date d2=sdf.parse(obj2);
			
			return d1.compareTo(d2);
		}
		catch (Exception e) {
			return 0;
		}
	}

}
