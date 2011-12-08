package ar.com.tecnologiaaplicada.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Tree;

import ar.com.tecnologiaaplicada.domain.Cliente;
import ar.com.tecnologiaaplicada.domain.DetalleLicencia;
import ar.com.tecnologiaaplicada.domain.Dominio;
import ar.com.tecnologiaaplicada.domain.Examen;
import ar.com.tecnologiaaplicada.domain.ExamenDetalle;
import ar.com.tecnologiaaplicada.domain.Licencia;

public class ControllerLicenciaForm extends ControlerTecnologiaAplicada{
	private Window wndLicenciaForm;
	private Button btnGuardar;
	private Button btnCerrar;
	
	
	private Licencia objUpdated;
	private List<DetalleLicencia> detallesLicencia;
	boolean isUpd=true;
	
	private Listbox lbCliente;
	private Listbox lbTipoLicencia;
	private Listbox lbProducto;
	private Tree treeExamenes; 
	private Treechildren treeChildrenExamenes;
	
	
	public void onCreate$wndLicenciaForm(Event evt) throws Exception
	{
		binder = (AnnotateDataBinder) evt.getTarget().getVariable("binder",false);
		addUtiljs(wndLicenciaForm);
		validateLogin();
		objUpdated = (Licencia)arg.get("p_object");
		wndLicenciaForm.setTitle("Licencia");
		if (objUpdated == null)
		{
			objUpdated=new Licencia();
			objUpdated.setLicActivaSn("S");
			objUpdated.setLicFechaGeneracion(new Date());
			objUpdated.setLicNro(generarNroLicencia());
			
			cargarClientes(lbCliente,false,null);
			cargarDominio(lbTipoLicencia,"TIPO_LICENCIA", false,null);
			cargarDominio(lbProducto,"PRODUCTO", false,null);
			
			isUpd=false;
		}
		else
		{
			objUpdated=licenciaService.get(objUpdated.getLicId());	
			cargarClientes(lbCliente,false,objUpdated.getCliente());
			cargarDominio(lbTipoLicencia,"TIPO_LICENCIA", false,String.valueOf(objUpdated.getLicTipo()));
			cargarDominio(lbProducto,"PRODUCTO", false,String.valueOf(objUpdated.getLicProducto()));
		}
		
		if(objUpdated.getLicId()!=null)
		{
			DetalleLicencia detalleLicencia=new DetalleLicencia(); 
			detalleLicencia.setLicencia(objUpdated);
			detallesLicencia=detalleLicenciaService.getAll(detalleLicencia);			
		}
		

		cargarArbol();
		binder.loadAll();
	}
	
	public void setSelectedPadre()
	{
		List<Treeitem> treeitems=treeChildrenExamenes.getChildren();
		for(Treeitem treeitem:treeitems)
		{
			Treechildren treechildren=treeitem.getTreechildren();
			if(treechildren!=null)
			{
				List<Treeitem> subTreeitems= treechildren.getChildren();
				boolean isSelected=true;

				for(Treeitem subTreeitem:subTreeitems)
				{	
					if(!subTreeitem.isSelected())
					{
						isSelected=false;
						break;
					}
				}
				treeitem.setSelected(isSelected);
				if(subTreeitems.size()==0)
				{
					treeitem.setSelected(false);
				}
			}
		}
	}
	
	public void cargarArbol() throws Exception
	{
		ArrayList<DetalleLicencia> detallesLicencia=new ArrayList(); 
		treeExamenes.setAttribute("detallesLicencia",detallesLicencia);
		treeExamenes.addEventListener("onSelect", new EventListener() {
			public void onEvent(Event event) {
				Treeitem current = (Treeitem)((org.zkoss.zk.ui.event.SelectEvent)event).getReference();
				
				Treechildren treechildren=current.getTreechildren();
				if(treechildren!=null)
				{
					List<Treeitem> treeitems= treechildren.getChildren();
					for(Treeitem treeitem:treeitems)
					{
						treeitem.setSelected(current.isSelected());
						DetalleLicencia detalleLicencia=(DetalleLicencia)treeitem.getValue();
						String sn= current.isSelected() ? "S" : "N"; 
						detalleLicencia.setDlicActivaSn(sn);
					}
				}
				else
				{
					
					DetalleLicencia detalleLicencia=(DetalleLicencia)current.getValue();
					String sn= current.isSelected() ? "S" : "N"; 
					detalleLicencia.setDlicActivaSn(sn);
				}
				
				setSelectedPadre();
			}
		});
		
		List<Examen> examenes=examenService.getAll(new Examen());
		
		for(Examen examen:examenes)
		{
			Treeitem treeExamen=new Treeitem();
			treeExamen.setOpen(false);
			treeExamen.setValue(examen);
			treeExamen.setLabel(examen.getExaNombre());
			
			ExamenDetalle examenDetalleExample=new ExamenDetalle();
			examenDetalleExample.setExamen(examen);
			
			List<ExamenDetalle> examenesDetalle=examenDetalleService.getAll(examenDetalleExample);
			Treechildren childrensExamen=new Treechildren();
			childrensExamen.setParent(treeExamen);
			
			for(ExamenDetalle examenDetalle:examenesDetalle)
			{
				boolean selected=tieneLicencia(examenDetalle);
				String sn= selected ? "S" : "N";
				
				DetalleLicencia detalleLicencia=new DetalleLicencia();
				detalleLicencia.setExamenDetalle(examenDetalle);
				detalleLicencia.setLicencia(objUpdated);
				detalleLicencia.setDlicActivaSn(sn);
				detallesLicencia.add(detalleLicencia);
				
				Treeitem treeExamenDetalle=new Treeitem();				
				treeExamenDetalle.setValue(detalleLicencia);
				treeExamenDetalle.setLabel(examenDetalle.getExadDetalle());
				treeExamenDetalle.setParent(childrensExamen);
				treeExamenDetalle.setSelected(selected);				
			}
			treeExamen.setParent(treeChildrenExamenes);
		}
		
		setSelectedPadre();
	}
	
	public boolean tieneLicencia(ExamenDetalle examenDetalle)
	{
		if(detallesLicencia==null)
		{
			return false;
		}
		else
		{
			for(DetalleLicencia detalleLicencia:detallesLicencia)
			{
				if(detalleLicencia.getExamenDetalle().getExadCodigo().equals(examenDetalle.getExadCodigo()) 
				   && detalleLicencia.getDlicActivaSn().equals("S"))
				{
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	public void onClick$btnGuardar(Event evt) throws Exception
	{
		doSave();
	}

	public void onClick$btnCerrar(Event evt) throws Exception
	{
		cerrar();
	}
	
	public String generarNroLicencia() throws Exception
	{
		String nroLicencia=null;
		boolean generar=true;
		while(generar)
		{
			String part1=getRandomString(4);
			String part2=getRandomString(4);
			
			nroLicencia=part1+"-"+part2;
			
			Licencia licencia=new Licencia();
			licencia.setLicNro(nroLicencia);
			List<Licencia> licencias=licenciaService.getAll(licencia);
			if(licencias.size()>0)
				generar=true;
			else
				generar=false;
		}
		
		return nroLicencia;
	}
	
    public static String getRandomString(int size)
    {
        if (size == 0)
            return "";

        Random r = new Random();
        String str = new String("QA0LUK2HJTP8XF61DON9BI5GYV3CSMZW4E7R");
        StringBuffer sb = new StringBuffer();
        int te = 0;

        if (size == 1)
        {
            te = r.nextInt(36);
            sb.append(str.charAt(te));
            return sb.toString();
        }
        else
        {
            for (int i = 1; i <= size; i++)
            {
                te = r.nextInt(36);
                sb.append(str.charAt(te));
            }
            return sb.toString();
        }
    }
	
	
	void doSave () throws Exception
	{
		binder.saveAll();

		Dominio dom=(Dominio)lbTipoLicencia.getSelectedItem().getValue();
		Long tipoLic=Long.valueOf(dom.getDomCodigo());
		
		dom=(Dominio)lbProducto.getSelectedItem().getValue();
		
		objUpdated.setLicProducto(dom.getDomCodigo());
		objUpdated.setLicTipo(tipoLic);
		objUpdated.setCliente((Cliente)lbCliente.getSelectedItem().getValue());
		
		if (isUpd)
			licenciaService.update (objUpdated);
		else
			licenciaService.insert (objUpdated);
		
		//Elimino todo el detalle de licencias
		if(this.detallesLicencia!=null)
		{
			for (int i = 0; i <this.detallesLicencia.size(); i++) {
				detalleLicenciaService.delete(this.detallesLicencia.get(i));	
				
				//System.out.println("Examen: "+this.detallesLicencia.get(i).getExamenDetalle().getExadDetalle()+" selected: "+ this.detallesLicencia.get(i).getDlicActivaSn());
			}
		}
		
		ArrayList<DetalleLicencia> detallesLicencia=(ArrayList<DetalleLicencia>)treeExamenes.getAttribute("detallesLicencia");
		for (int i = 0; i <detallesLicencia.size(); i++) {
			//detalleLicencia.add(detallesLicencia.get(i));
			detalleLicenciaService.insert(detallesLicencia.get(i));	
			//System.out.println("Examen: "+detallesLicencia.get(i).getExamenDetalle().getExadDetalle()+" selected: "+ detallesLicencia.get(i).getDlicActivaSn());
		}

		wndLicenciaForm.setAttribute("OK", "S");
		wndLicenciaForm.detach();
	}

	void cerrar() {
		wndLicenciaForm.detach();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
	}
	
	public Licencia getObjUpdated() {
		return objUpdated;
	}
	
	public class TreeListener implements EventListener {

		public void onEvent(Event ev) { 
		System.out.println(ev.getName()); 
		}

		public boolean isAsap() { 
			return true; 
		}
	}
}
