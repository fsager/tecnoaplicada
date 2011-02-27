package ar.com.tecnologiaaplicada.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import ar.com.tecnologiaaplicada.domain.Cliente;
import ar.com.tecnologiaaplicada.domain.Licencia;

public class ControllerLicencia extends ControlerTecnologiaAplicada{

	private Window wnd_licencia;
	private Listbox lbMain;
	private Button btnFiltrar;
	private Label lbNoRegistros;
	
	
	//Filtros
	private Listbox lbCliente;
	
	private Cliente example=new Cliente();
	private java.util.List lista = new java.util.ArrayList();
	
	String form="/opciones/licencia_form.zul";
	
	
	public void onCreate$wnd_licencia(Event evt) throws Exception
	{
		binder = (AnnotateDataBinder) evt.getTarget().getVariable("binder",false);
		addUtiljs(wnd_licencia);
		validateLogin();
		cargarClientes(lbCliente,true,null);
		filtrar();
	}
	
	
	public void onClick$btnInsertar(Event evt) throws Exception
	{
		java.util.Properties params=new java.util.Properties ();
		Window win = (Window) Executions.createComponents(form, wnd_licencia, params);
		win.setWidth ("800px");
		win.doModal();
		if (win.getAttribute("OK")!=null)
			filtrar();
	}
	
	public void onDoubleClick$lbMain(Event evt) throws Exception
	{
		doUpdate();
	}
	
	
	public void doUpdate() throws Exception
	{
		Listitem li=lbMain.getSelectedItem();
		if (li==null)
		{
			Messagebox.show(msgActSel, "Actualizar", Messagebox.OK, Messagebox.INFORMATION);
		}
		else
		{
			java.util.Properties params=new java.util.Properties ();
			params.put("p_object", li.getValue());
			Window win = (Window) Executions.createComponents(form, wnd_licencia, params);
			win.setWidth ("800px");
			win.doModal();
			if (win.getAttribute("OK")!=null)
				filtrar();
		}
	}
	
	
	public void onClick$btnModificar(Event evt) throws Exception
	{
		doUpdate();
	}
	
	public void onClick$btnEliminar(Event evt) throws Exception
	{
		Listitem li=lbMain.getSelectedItem();
		if (li==null)
		{
			Messagebox.show(msgActSel, "Eliminar", Messagebox.OK, Messagebox.INFORMATION);
		}
		else
		{	
			if (Messagebox.show(msgDelQuest, "Eliminar"
						  	  , Messagebox.YES | Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES)
			{
				try {
						licenciaService.delete ((Licencia)li.getValue());
						Messagebox.show(msgDel, "Eliminar", Messagebox.OK, Messagebox.INFORMATION);
						filtrar();
				}
				catch (org.hibernate.exception.ConstraintViolationException cve) {
					Messagebox.show(msgDelErrRel, "Eliminar", Messagebox.OK, Messagebox.ERROR);
				}
				catch (org.springframework.dao.DataIntegrityViolationException dive) {
					Messagebox.show(msgDelErrRel, "Eliminar", Messagebox.OK, Messagebox.ERROR);
				}
				catch (Exception e) {
					Messagebox.show(msgDelErrGral+":"+e.getMessage(), "Eliminar"
							, Messagebox.OK, Messagebox.ERROR);
				}
			}
		}
	}
	
	
	
	
	public void onOK$gridFiltros(Event evt) throws Exception
	{
		filtrar();
	}
	
	public void onClick$btnFiltrar(Event evt) throws Exception
	{
		filtrar();
	}
	
	void filtrar () throws Exception
	{	
		binder.saveAll();
		
		Cliente cliente=null;
		if(lbCliente.getSelectedItem().getValue()!=null)
		{
			cliente=(Cliente)lbCliente.getSelectedItem().getValue();
		}
		Licencia lic=new Licencia();
		lic.setCliente(cliente);
		
		lista = licenciaService.getAll(lic);
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
	
	public Cliente getExample() {
		return example;
	}
	
	public void setExample(Cliente example) {
		this.example = example;
	}
}

