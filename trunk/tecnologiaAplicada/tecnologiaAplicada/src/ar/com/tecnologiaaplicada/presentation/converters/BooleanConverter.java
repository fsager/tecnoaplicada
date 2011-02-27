package ar.com.tecnologiaaplicada.presentation.converters;

import org.zkoss.zkplus.databind.TypeConverter;

public class BooleanConverter implements TypeConverter {
	public Object coerceToBean(java.lang.Object val,org.zkoss.zk.ui.Component comp) {

		if(val == null || val.equals(Boolean.FALSE))
			return new String("N");
		else
			return new String("S");
	}
 
	public Object coerceToUi(java.lang.Object val, org.zkoss.zk.ui.Component comp) {
		
		if(val == null || (val).equals("N") || (val).equals("n"))
			return Boolean.FALSE;
		else
			return Boolean.TRUE;
	}
}