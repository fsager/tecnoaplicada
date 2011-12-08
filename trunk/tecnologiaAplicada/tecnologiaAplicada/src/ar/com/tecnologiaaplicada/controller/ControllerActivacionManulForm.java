package ar.com.tecnologiaaplicada.controller;

import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ar.com.tecnologiaaplicada.LicenseException;
import ar.com.tecnologiaaplicada.domain.Dominio;

public class ControllerActivacionManulForm extends ControlerTecnologiaAplicada{
	private Window wndActivacionManualForm;
	private Button btnEnviar;
	
	private Listbox lbProducto;
	private Textbox txtLicencia;
	private Textbox txtCodigo;
	
	public void onCreate$wndActivacionManualForm(Event evt) throws Exception
	{
		binder = (AnnotateDataBinder) evt.getTarget().getVariable("binder",false);
		addUtiljs(wndActivacionManualForm);
		cargarDominio(lbProducto,"PRODUCTO", false,null);
		binder.loadAll();
	}
	
	public void onClick$btnEnviar(Event evt)  throws Exception
	{
		try
		{
			Dominio dom=(Dominio)lbProducto.getSelectedItem().getValue();
			byte[] licencia=examenDetalleService.getDetalleLicenciaPorCliente(txtLicencia.getValue(),dom.getDomCodigo(),txtCodigo.getValue());
			Filedownload.save(licencia,"binary","licence.jtt");
		}
		catch(LicenseException e)
		{
			Messagebox.show(e.getMessage(),
					"Error", Messagebox.OK,
					Messagebox.ERROR);
	
		}
		
	}
}
