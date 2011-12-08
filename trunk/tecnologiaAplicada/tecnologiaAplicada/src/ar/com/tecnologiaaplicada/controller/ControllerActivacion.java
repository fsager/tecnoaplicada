package ar.com.tecnologiaaplicada.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import ar.com.tecnologiaaplicada.domain.Activacion;
import ar.com.tecnologiaaplicada.domain.Cliente;
import ar.com.tecnologiaaplicada.domain.Licencia;

public class ControllerActivacion extends ControlerTecnologiaAplicada{

	private Window wnd_activacion;
	private Listbox lbMain;
	private Button btnFiltrar;
	private Label lbNoRegistros;
	private java.util.List lista = new java.util.ArrayList();
	private Activacion example=new Activacion();
	private Licencia licencia;
	
	//Filtros
	public void onCreate$wnd_activacion(Event evt) throws Exception
	{
		binder = (AnnotateDataBinder) evt.getTarget().getVariable("binder",false);
		addUtiljs(wnd_activacion);
		validateLogin();
		licencia = (Licencia)arg.get("p_licencia");
		example.setLicencia(licencia);
		filtrar();
	}	
	
	void filtrar () throws Exception
	{	
		binder.saveAll();
		
		lista = activacionService.getAll(example);
		lbNoRegistros.setVisible((lista.size()<=0));

		if (lista.size()<= (lbMain.getPageSize()*lbMain.getActivePage()) && lbMain.getActivePage()>0)
		{	
			int pagina=(lista.size()-lbMain.getPageSize())/lbMain.getPageSize();
			if(pagina<0)
				pagina=0;
			lbMain.setActivePage(pagina);
		}
				
		binder.loadComponent(lbMain);		
	}
		
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
	}
	
	public java.util.List getLista() {
		return lista;
	}
	
	public void setLista(java.util.List lista) {
		this.lista = lista;
	}
	
	public Activacion getExample() {
		return example;
	}
	
	public void setExample(Activacion example) {
		this.example = example;
	}
}

