package ar.com.tecnologiaaplicada.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ar.com.tecnologiaaplicada.domain.Cliente;

public class ControllerClienteForm extends ControlerTecnologiaAplicada{
	private Window wndClienteForm;
	private Button btnGuardar;
	private Button btnCerrar;
	

	
	
	private Cliente objUpdated;
	boolean isUpd=true;
	
	
	public void onCreate$wndClienteForm(Event evt) throws Exception
	{
		binder = (AnnotateDataBinder) evt.getTarget().getVariable("binder",false);
		addUtiljs(wndClienteForm);
		validateLogin();
		objUpdated = (Cliente)arg.get("p_object");
		wndClienteForm.setTitle("Cliente");
		if (objUpdated == null)
		{
			objUpdated=new Cliente();
			isUpd=false;
		}
		else
		{
			objUpdated=clienteService.get(objUpdated.getCliId());	
		}
		
		binder.loadAll();
	}
	
	public void onClick$btnGuardar(Event evt) throws Exception
	{
		doSave();
	}

	public void onClick$btnCerrar(Event evt) throws Exception
	{
		cerrar();
	}
	
	
	void doSave () throws Exception
	{
		binder.saveAll();
		if (isUpd)
			clienteService.update (objUpdated);
		else
			clienteService.insert (objUpdated);

		wndClienteForm.setAttribute("OK", "S");
		wndClienteForm.detach();
	}

	void cerrar() {
		wndClienteForm.detach();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
	}
	
	public Cliente getObjUpdated() {
		return objUpdated;
	}
}
