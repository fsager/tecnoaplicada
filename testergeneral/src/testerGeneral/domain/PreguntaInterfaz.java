package testerGeneral.domain;

import java.util.HashSet;
import java.util.Set;

public interface PreguntaInterfaz {

	public Long getId();

	public void setId(Long id);

	public String getPreDetalle();

	public void setPreDetalle(String preDetalle);

	public String getPreCat();

	public void setPreCat(String preCat);
	
	public Long getPreValoracion();

	public void setPreValoracion(Long preValoracion);
	
	public Long getPreOrden();

	public void setPreOrden(Long preOrden);

	public byte[] getPreImagen();
	
	public void setPreImagen(byte[] preImagen);
}
