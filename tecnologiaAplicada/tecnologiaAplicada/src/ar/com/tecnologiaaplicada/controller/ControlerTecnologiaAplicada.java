package ar.com.tecnologiaaplicada.controller;

import java.util.List;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import ar.com.tecnologiaaplicada.business.ContextManager;
import ar.com.tecnologiaaplicada.domain.Cliente;
import ar.com.tecnologiaaplicada.domain.Dominio;
import ar.com.tecnologiaaplicada.domain.Usuario;
import ar.com.tecnologiaaplicada.service.ClienteDefinition;
import ar.com.tecnologiaaplicada.service.DetalleLicenciaDefinition;
import ar.com.tecnologiaaplicada.service.DominioDefinition;
import ar.com.tecnologiaaplicada.service.ExamenDefinition;
import ar.com.tecnologiaaplicada.service.ExamenDetalleDefinition;
import ar.com.tecnologiaaplicada.service.LicenciaDefinition;
import ar.com.tecnologiaaplicada.service.UsuarioDefinition;

public class ControlerTecnologiaAplicada extends GenericForwardComposer{
	protected AnnotateDataBinder binder;
	
	protected UsuarioDefinition usuarioService=(UsuarioDefinition)ContextManager.getBizObject("usuarioService");
	protected ClienteDefinition clienteService=(ClienteDefinition)ContextManager.getBizObject("clienteService");
	protected LicenciaDefinition licenciaService=(LicenciaDefinition)ContextManager.getBizObject("licenciaService");
	protected DominioDefinition  dominioService= (DominioDefinition) ContextManager.getBizObject("dominioService");
	
	protected ExamenDefinition  examenService= (ExamenDefinition) ContextManager.getBizObject("examenService");
	protected ExamenDetalleDefinition  examenDetalleService= (ExamenDetalleDefinition) ContextManager.getBizObject("examenDetalleService");
	protected DetalleLicenciaDefinition  detalleLicenciaService= (DetalleLicenciaDefinition) ContextManager.getBizObject("detalleLicenciaService");
	
	
	
	
	protected String msgAct = "El registro se ha guardado correctamente.";
	protected String msgDel = "El registro se ha eliminado correctamente.";
	protected String msgActSel = "Debe seleccionar un registro";
	protected String msgDelQuest = "¿Está seguro que desea eliminar el registro?";
	protected String msgDelErrRel = "No se puede eliminar el registro, hay información asociada al mismo.";
	protected String msgDelErrGral = "Error desconocido";
	
	protected Usuario usuario;
	
	
	public void addUtiljs(Window wnd)
	{
		Include utiljs=new Include();
		utiljs.setSrc("/zs/utiljs.zul");
		utiljs.setParent(wnd);
	}
	
	
	public void validateLogin() throws Exception
	{
		if(session.getAttribute("usuario")==null)
			execution.sendRedirect("index.zul");
		else
		{
			usuario=(Usuario)session.getAttribute("usuario");
		}
	}
	
	public void cargarClientes(Listbox lbCliente,boolean nulls,Cliente clienteSel) throws Exception
	{
		lbCliente.getItems().clear();
		List<Cliente> clientes=clienteService.getAll(new Cliente());
		
		Listitem item=new Listitem();
		if(nulls)
		{
			item.setParent(lbCliente);
			item.setSelected(true);
		}
		
		for(Cliente cliente:clientes)
		{
			item=new Listitem(cliente.getCliRazonSocial());
			item.setValue(cliente);
			item.setParent(lbCliente);
			
			if(clienteSel==null)
				lbCliente.setSelectedIndex(0);
			
			if(clienteSel!=null && clienteSel.getCliId().equals(cliente.getCliId()))
			{
				item.setSelected(true);
			}
		}
	}
	
	public void cargarDominio(Listbox lbDominios,String dominio, boolean nulls,String valueSel) throws Exception
	{
		lbDominios.getItems().clear();
		Dominio domExample=new Dominio();
		domExample.setDomClave(dominio);
		
		List<Dominio> dominios=dominioService.getAll(domExample);
		
		Listitem item=new Listitem();
		if(nulls)
		{
			item.setParent(lbDominios);
			item.setSelected(true);			
		}
		
		for(Dominio dominioIt:dominios)
		{
			item=new Listitem(dominioIt.getDomValorMostrar());
			item.setValue(dominioIt);
			item.setParent(lbDominios);
			//lbDominios.setSelectedIndex(0);
			
			if(valueSel==null)
				lbDominios.setSelectedIndex(0);
			
			if(valueSel!=null && valueSel.equals(dominioIt.getDomCodigo()))
			{
				item.setSelected(true);
			}
		}
	}

}
