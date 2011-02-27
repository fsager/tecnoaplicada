package ar.com.tecnologiaaplicada.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ar.com.tecnologiaaplicada.domain.Usuario;


public class ControllerIndex extends ControlerTecnologiaAplicada{

	private static final Log log = LogFactory.getLog(ControllerIndex.class);
	
	private Window wds_login;
	private Button btnIngresar;
	private Listbox lstUserName;
	private Textbox fldPassword;
	
	public void onCreate$wds_login(Event evt) throws Exception
	{
		if(session.getAttribute("usuario")!=null)
			execution.sendRedirect("main.zul"); 
		
		addUtiljs(wds_login);
		agregarUsuarios();
	}
	
	public void agregarUsuarios() throws Exception
	{
		lstUserName.getItems().clear();
		
		Usuario usr=new Usuario();
		usr.setDeletedSn(null);
		usr.setUsrHabilitadoSn("S");

		List<Usuario> usuarios = usuarioService.getAll(usr);

		for (int i = 0; i < usuarios.size(); i++) {
			Listitem item=new Listitem(usuarios.get(i).getUsrNombre());
			item.setValue(usuarios.get(i));
			item.setParent(lstUserName);
			lstUserName.setSelectedIndex(0);
		}
	}
	
	public void onClick$btnIngresar(Event event) throws Exception
	{
		log.info("onClick$btnIngresar");
		doLogin();
	}
	
	public void onOK$fldPassword(Event event) throws Exception
	{
		log.info("onOK$fldPassword");
		doLogin();
	}
	
	public void doLogin() throws Exception
	{
		fldPassword.getValue();
		
		Usuario usr=(Usuario)lstUserName.getSelectedItem().getValue();
		String clave=fldPassword.getValue();
		
		if(usr.getUsrClave().equals(clave))
		{
			usr.setUsrUltimoAcceso(new Date());
			usuarioService.update(usr);
			
			session.setAttribute("usuario",usr);
			execution.sendRedirect("main.zul"); 
		}
		else
			Messagebox.show("La clave es incorrecta", "Inicio de sesión", Messagebox.OK, Messagebox.INFORMATION);
	}
	
}
