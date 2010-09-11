/*
 * PanelDetalleMenu.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.examenes;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JToggleButton;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Examen;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.service.ExamenDetalleDefinition;
import frontend.paneles.psicometrico.anticipacion.PanelAnticipacion;
import frontend.paneles.psicometrico.cooordinacion.bimanual.PanelCoordinacionBimanual;
import frontend.paneles.psicometrico.cooordinacion.visomotora.PanelCoorVisomotora;
import frontend.paneles.psicometrico.palanca.PanelPalanca;
import frontend.paneles.psicometrico.percepcionreaccion.PanelPercepcionReaccion;
import frontend.paneles.psicometrico.reaccionsimple.PanelReaccionSimple;

/**
 *
 * @author  __USER__
 */
public class PanelDetalleExamen extends javax.swing.JPanel {

	private PanelExamenes panelExamen;
	private PersonaExamen personaExamen;
	ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager.getBizObject("examenDetalleService");

	/** Creates new form PanelDetalleMenu */
	public PanelDetalleExamen(PersonaExamen personaExamen,PanelExamenes panelExamen) {
		this.personaExamen = personaExamen;
		this.panelExamen=panelExamen;
		
		initComponents();
		action = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				actionBtnDetalleExamen(evt);
			}
		};
		
		actionFinalizar = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				agregarPanelFinalizar(evt);
			}
		};
		
		cargarDetalleExamenes();		
	}
	
	public void agregarPanelFinalizar(ActionEvent evt)
	{
		JToggleButton btn = (JToggleButton) evt.getSource();
		unSelectButtons(btn);
		panelExamen.getPanelAnimacion().removeAll();
		
		PanelFinalizarExamen panelFinalizarExamen=new PanelFinalizarExamen(personaExamen);
		panelFinalizarExamen.setVisible(true);
		panelExamen.getPanelAnimacion().add(panelFinalizarExamen);
		
		panelExamen.getPanelAnimacion().repaint();
		panelExamen.repaint();
		panelExamen.validate();
	}

	public void cargarDetalleExamenes() {
		try {

			ExamenDetalle detallesExamenen=new ExamenDetalle();
			detallesExamenen.setExamen(personaExamen.getExamen());
			ExamenDetalleDefinition examenDetalleService =(ExamenDetalleDefinition)ContextManager.getBizObject("examenDetalleService");
			
			List<ExamenDetalle> detallesExamenes = examenDetalleService.getAll(detallesExamenen);

			GridLayout gridLayout = (GridLayout) this.getLayout();
			gridLayout.setHgap(5);
			gridLayout.setVgap(5);
			gridLayout.setColumns(1);
			gridLayout.setRows(detallesExamenes.size()+1);

			for(int i=0;i<detallesExamenes.size();i++)
			{
				ExamenDetalle de=detallesExamenes.get(i);
				JToggleButton btnExamen = new JToggleButton(de.getExadDetalle());
				btnExamen.setActionCommand(de.getExadCodigo());
				btnExamen.setForeground(Color.red);
				
				this.add(btnExamen);
				btnExamen.addActionListener(action);
				
				if(i==0)
					btnExamenPrimero=btnExamen;
			}
			
			if(personaExamen.getPersona()!=null)
			{
				JToggleButton btnFinalizar = new JToggleButton("Finalizar Examen");
				btnFinalizar.setActionCommand("finalizar");
				
				this.add(btnFinalizar);
				btnFinalizar.addActionListener(actionFinalizar);				
			}
			
			if(personaExamen.getExamen().getExaCodigo().equals(Examen.EXA_CODIGO_PSICOMETRICO))
				unSelectButtons(ExamenDetalle.EXAD_CODIGO_TEST_CTR_TEMPORO);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public PanelExamenes getPanelExamen() {
		return panelExamen;
	}

	public void setPanelExamen(PanelExamenes panelExamen) {
		this.panelExamen = panelExamen;
	}

	public PersonaExamen getPersonaExamen() {
		return personaExamen;
	}

	public void setPersonaExamen(PersonaExamen personaExamen) {
		this.personaExamen = personaExamen;
	}

	public java.awt.event.ActionListener getAction() {
		return action;
	}

	public void setAction(java.awt.event.ActionListener action) {
		this.action = action;
	}

	public JToggleButton getBtnExamenPrimero() {
		return btnExamenPrimero;
	}

	public void setBtnExamenPrimero(JToggleButton btnExamenPrimero) {
		this.btnExamenPrimero = btnExamenPrimero;
	}

	public void actionBtnDetalleExamen(java.awt.event.ActionEvent evt) {
		System.gc();
		JToggleButton btn = (JToggleButton) evt.getSource();
		unSelectButtons(btn);
		panelExamen.getPanelAnimacion().removeAll();
		
		if (btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_CTR_TEMPORO)) {			
			addTestCtrTemporo(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
		{
			addTestPercepcionReaccion(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
		{
			addTestReacioneMultiples(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
		{
			addTestReacioneMultiplesNoCond(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_SIMPLE))
		{
			addTestReaccionSimple(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL))
		{
			addTestCoorBimanual(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA))
		{
			addTestPalanca(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_COOR_VISOMOTORA))
		{
			addTestCoorVisomotora(btn);
		}
		
		

		panelExamen.getPanelAnimacion().repaint();
		panelExamen.repaint();

		panelExamen.getPanelAnimacion().validate();
		panelExamen.validate();
	}
	
	public void addTestCtrTemporo(JToggleButton btn)
	{		
		
		PanelAnticipacion pa=new PanelAnticipacion(btn,personaExamen);
		panelExamen.getPanelAnimacion().add(pa);
		
	}
	
	public void addTestPercepcionReaccion(JToggleButton btn)
	{	
		try {
						
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelPercepcionReaccion ppr=new PanelPercepcionReaccion(btn,personaExamen,exaDetalle);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTestReacioneMultiples(JToggleButton btn)
	{	
		try {
						
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelPercepcionReaccion ppr=new PanelPercepcionReaccion(btn,personaExamen,exaDetalle);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTestCoorVisomotora(JToggleButton btn)
	{	
		PanelCoorVisomotora ppr=new PanelCoorVisomotora(btn,personaExamen);
		panelExamen.getPanelAnimacion().add(ppr);
	}
	
	
	public void addTestReacioneMultiplesNoCond(JToggleButton btn)
	{	
		try {
						
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelPercepcionReaccion ppr=new PanelPercepcionReaccion(btn,personaExamen,exaDetalle);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}		

	public void addTestReaccionSimple(JToggleButton btn)
	{		
		PanelReaccionSimple ppr=new PanelReaccionSimple(btn,personaExamen);
		panelExamen.getPanelAnimacion().add(ppr);
	}
	
	public void addTestPalanca(JToggleButton btn)
	{	
		PanelPalanca pa=new PanelPalanca(btn,personaExamen);
		panelExamen.getPanelAnimacion().add(pa);
	}
	
	public void addTestCoorBimanual(JToggleButton btn)
	{	
		PanelCoordinacionBimanual pa=new PanelCoordinacionBimanual(btn,personaExamen);
		panelExamen.getPanelAnimacion().add(pa);
	}
	
	public void unSelectButtons(JToggleButton btnSource) {
		Component[] cmps = this.getComponents();
		for (int i = 0; i < cmps.length; i++) {
			if (cmps[i].getClass().equals(JToggleButton.class)) {
				JToggleButton toggleBtn = (JToggleButton) cmps[i];
				if (!btnSource.equals(toggleBtn))
					toggleBtn.setSelected(false);
				else
					toggleBtn.setSelected(true);
			}
		}
	}
	
	public void unSelectButtons(String exaDetalleCodigo) {
		Component[] cmps = this.getComponents();
		for (int i = 0; i < cmps.length; i++) {
			if (cmps[i].getClass().equals(JToggleButton.class)) {
				JToggleButton toggleBtn = (JToggleButton) cmps[i];
				if (!exaDetalleCodigo.equals(toggleBtn.getActionCommand()))
					toggleBtn.setSelected(false);
				else
					toggleBtn.setSelected(true);
			}
		}
	}
	
 

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		setLayout(new java.awt.GridLayout());
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

	private java.awt.event.ActionListener action;
	private java.awt.event.ActionListener actionFinalizar;	
	private JToggleButton btnExamenPrimero;
}