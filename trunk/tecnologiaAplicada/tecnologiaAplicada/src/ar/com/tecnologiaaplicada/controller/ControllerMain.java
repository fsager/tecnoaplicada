package ar.com.tecnologiaaplicada.controller;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class ControllerMain extends ControlerTecnologiaAplicada{
	
	private Listbox lstboxOpciones;
	private Window wnd_main;
	private Button btnCerrarSesion;
	private Iframe iFrameContenido;
	private Label lbUsr;
	
	public void onCreate$wnd_main(Event evt) throws Exception
	{
		addUtiljs(wnd_main);
		validateLogin();
		lbUsr.setValue(usuario.getUsrNombre());
	}
	
	public void onSelect$lstboxOpciones(Event evt) throws Exception
	{
		String url=(String)lstboxOpciones.getSelectedItem().getValue();
		iFrameContenido.setSrc(url+"?p_time="+System.currentTimeMillis());
	}

	public void onClick$btnCerrarSesion(Event evt) throws Exception
	{
		session.setAttribute("usuario",null);
		execution.sendRedirect("index.zul");
	}
}
