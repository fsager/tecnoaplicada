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

import ar.com.tecnologiaaplicada.domain.Usuario;

public class ControllerUsuarioForm extends ControlerTecnologiaAplicada{
	private Window wndUsuarioForm;
	private Button btnGuardar;
	private Button btnCerrar;
	private Textbox fld_Nombre;
	private Textbox fld_clave;
	private Textbox fld_re_clave;
	private Checkbox ckHabilitado;
	
	
	private Usuario objUpdated;
	boolean isUpd=true;
	
	
	public void onCreate$wndUsuarioForm(Event evt) throws Exception
	{
		binder = (AnnotateDataBinder) evt.getTarget().getVariable("binder",false);
		addUtiljs(wndUsuarioForm);
		validateLogin();
		objUpdated = (Usuario)arg.get("p_object");
		wndUsuarioForm.setTitle("Usuario");
		if (objUpdated == null)
		{
			objUpdated=new Usuario();
			objUpdated.setDeletedSn("S");
			isUpd=false;
		}
		else
		{
			objUpdated=usuarioService.get(objUpdated.getUsrId());	
			fld_Nombre.setReadonly(true);
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
		
		if(!fld_clave.getValue().equals(fld_re_clave.getValue()))
		{
			Messagebox.show("Las contraseñas no son iguales.", "Claves", Messagebox.OK, Messagebox.INFORMATION);
		}
		else
		{
			try
			{
				if (isUpd)
					usuarioService.update (objUpdated);
				else
					usuarioService.insert (objUpdated);
	
				wndUsuarioForm.setAttribute("OK", "S");
				wndUsuarioForm.detach();
			}
			catch (DataIntegrityViolationException cve) {
				Messagebox.show("El usuario ya existe", "Nuevo usuario", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	void cerrar() {
		wndUsuarioForm.detach();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
	}
	
	public Usuario getObjUpdated() {
		return objUpdated;
	}
}
