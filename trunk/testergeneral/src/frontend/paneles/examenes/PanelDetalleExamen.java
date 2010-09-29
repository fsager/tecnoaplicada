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
import frontend.paneles.vision.PanelAgudezaVisual;
import frontend.paneles.vision.PanelAudio;
import frontend.paneles.vision.PanelCampimetria;
import frontend.paneles.vision.PanelEncandilamiento;
import frontend.paneles.vision.PanelForia;
import frontend.paneles.vision.PanelFotocromatica;
import frontend.paneles.vision.PanelProfundidadVisual;
import frontend.paneles.vision.PanelRecEncandilamiento;
import frontend.paneles.vision.PanelVisionNocturna;

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
				agregarPanelFinalizar((JToggleButton) evt.getSource());
			}
		};
		
		cargarDetalleExamenes();		
	}
	
	public void agregarPanelFinalizar(JToggleButton btn)
	{
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
			else if(personaExamen.getExamen().getExaCodigo().equals(Examen.EXA_CODIGO_VISION))
				unSelectButtons(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL);

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
		seleccionarExamen(btn);
	}
	
	public void seleccionarExamen(JToggleButton btn)
	{
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
		
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL))
		{
			addTestAgudezaVisual(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO))
		{
			addTestAudio(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_PROFUNDIDAD))
		{
			addTestProfundidad(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_CAMPIMETRIA))
		{
			addTestCampimetria(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_ENCANDILAMIENTO))
		{
			addTestEncandilamiento(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_FORIA))
		{
			addTestForia(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_FOTOCROMATICA))
		{
			addTestFotoCromatica(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_REC_ENCANDILAMIENTO))
		{
			addTestFotoReacEncandilamiento(btn);
		}
		else if(btn.getActionCommand().equals(ExamenDetalle.EXAD_CODIGO_TEST_VISION_NOCTURNA))
		{
			addTestFotoVisionNoctura(btn);
		}
		else if(btn.getActionCommand().equals("finalizar"))
		{
			 agregarPanelFinalizar(btn);
		}
		
		
		panelExamen.getPanelAnimacion().repaint();
		panelExamen.repaint();

		panelExamen.getPanelAnimacion().validate();
		panelExamen.validate();
		
		if(panelExamen.getPanelAnimacion().getComponentCount()>0)
			panelExamen.getPanelAnimacion().getComponent(0).requestFocus();
	}
	
	
	
	public void nextExamen(JToggleButton src)
	{
		int nuevaPos=-1;
		
		Component[] components=this.getComponents();
		for(int i=0;i<components.length;i++)
		{
			if(components[i].equals(src))
			{
				nuevaPos=i+1;
				break;
			}
		}
		
		if(nuevaPos<this.getComponentCount())
		{
			JToggleButton btn=(JToggleButton)this.getComponent(nuevaPos);
			seleccionarExamen(btn);
		}
	}


	public void addTestFotoVisionNoctura(JToggleButton btn)
	{	
		try {
			
			unSelectButtons(btn.getActionCommand());
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_VISION_NOCTURNA);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelVisionNocturna ppr=new PanelVisionNocturna(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTestFotoReacEncandilamiento(JToggleButton btn)
	{	
		try {
			
			unSelectButtons(btn.getActionCommand());
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_REC_ENCANDILAMIENTO);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelRecEncandilamiento ppr=new PanelRecEncandilamiento(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTestFotoCromatica(JToggleButton btn)
	{	
		try {
			
			unSelectButtons(btn.getActionCommand());
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_FOTOCROMATICA);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelFotocromatica ppr=new PanelFotocromatica(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTestForia(JToggleButton btn)
	{	
		try {
			
			unSelectButtons(btn.getActionCommand());
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_FORIA);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelForia ppr=new PanelForia(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTestEncandilamiento(JToggleButton btn)
	{	
		try {
			
			unSelectButtons(btn.getActionCommand());
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_ENCANDILAMIENTO);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelEncandilamiento ppr=new PanelEncandilamiento(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTestCampimetria(JToggleButton btn)
	{	
		try {
			
			unSelectButtons(btn.getActionCommand());
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_CAMPIMETRIA);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelCampimetria ppr=new PanelCampimetria(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
		
	public void addTestProfundidad(JToggleButton btn)
	{	
		try {
			
			unSelectButtons(btn.getActionCommand());
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_PROFUNDIDAD);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelProfundidadVisual ppr=new PanelProfundidadVisual(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
		
	public void addTestAudio(JToggleButton btn)
	{	
		try {
			
			unSelectButtons(btn.getActionCommand());
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelAudio ppr=new PanelAudio(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addTestAgudezaVisual(JToggleButton btn)
	{	
		try {
						
			ExamenDetalle exaDetalle=new ExamenDetalle(); 
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL);
			exaDetalle = (ExamenDetalle) examenDetalleService.getAll(exaDetalle).get(0);
			PanelAgudezaVisual ppr=new PanelAgudezaVisual(btn,personaExamen);
			panelExamen.getPanelAnimacion().add(ppr);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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