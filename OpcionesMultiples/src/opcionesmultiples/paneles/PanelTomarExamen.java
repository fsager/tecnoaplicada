/*
 * PanelTomarExamen.java
 *
 * Created on __DATE__, __TIME__
 */

package opcionesmultiples.paneles;

import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import opcionesmultiples.domain.PersonaExamen;
import opcionesmultiples.domain.Pregunta;
import opcionesmultiples.service.PreguntaDefinition;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Examen;
import testerGeneral.domain.Persona;
import testerGeneral.domain.PreguntaInterfaz;
import testerGeneral.service.ExamenDefinition;
import testerGeneral.service.PersonaExamenDefinition;
import frontend.buttons.ButtonCancelarConTexto;
import frontend.buttons.ButtonGuardar;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;

/**
 *
 * @author  __USER__
 */
public class PanelTomarExamen extends javax.swing.JPanel {

	/** Creates new form PanelTomarExamen */
	public PanelTomarExamen(Persona per, PanelMenuPrincipal panelMenu,JInternalFrameTesterGral jif) {
		this.per=per;
		this.panelMenu=panelMenu;
		this.jif = jif;
		String preguntas=ContextManager.getProperty("PREGUNTAS_X_EXAMEN");
		String tiempo=ContextManager.getProperty("TIEMPO_X_EXAMEN");
		
		initComponents();
		btnGuardar.setText("Iniciar");
		Util.cargarDominios(cbExamen,
				Constantes.DOMINIO_CLAVE_MP_CATEGORIA_EXAMEN, false);
		Util.mostrarError(lbError, null, true);
		txtPregunta.setText(preguntas);
		txtTiempo.setText(tiempo);
		
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		cbExamen = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		txtPregunta = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		txtTiempo = new javax.swing.JTextField();
		btnCancelar = new ButtonCancelarConTexto();
		btnGuardar = new ButtonGuardar();
		lbError = new javax.swing.JLabel();

		cbExamen.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel4.setText("Ex\u00e1men:");

		jLabel1.setText("Preguntas:");

		jLabel2.setText("Tiempo del Ex\u00e1men (Minutos):");

		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
			}
		});

		btnGuardar.setText("Iniciar");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbError.setText(Constantes.ERROR_SIN_RESULTADOS);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addContainerGap()
																										.addComponent(
																												jLabel4,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												63,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								layout
																										.createSequentialGroup()
																										.addContainerGap(
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												jLabel1)
																										.addGap(
																												14,
																												14,
																												14)))
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								cbExamen,
																								0,
																								306,
																								Short.MAX_VALUE)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												txtPregunta,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												38,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																												64,
																												Short.MAX_VALUE)
																										.addComponent(
																												jLabel2)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtTiempo,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												37,
																												javax.swing.GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				116,
																				116,
																				116)
																		.addComponent(
																				btnGuardar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnCancelar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()).addComponent(
								lbError, javax.swing.GroupLayout.DEFAULT_SIZE,
								400, Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																cbExamen,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel1)
														.addGroup(
																layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel2)
																		.addComponent(
																				txtTiempo,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				txtPregunta,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(18, 18, 18)
										.addComponent(
												lbError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												24,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnCancelar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnGuardar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		((ButtonCancelarConTexto) btnCancelar).init();
		((ButtonGuardar) btnGuardar).init();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		try
		{
		
			Util.mostrarError(lbError, null, true);
			int preguntas = 0;
			int tiempoMinutos = 0;
			
			try {
				tiempoMinutos = Integer.valueOf(txtTiempo.getText());
			} catch (Exception e) {
				Util.mostrarError(lbError, "Error en la duraci�n del ex�men.", false);
			}
			
			try {
				preguntas = Integer.valueOf(txtPregunta.getText());
			} catch (Exception e) {
				Util.mostrarError(lbError, "Error en la cantidad de preguntas.", false);
			}
			
	
			ExamenDefinition examenService = (ExamenDefinition) ContextManager.getBizObject("examenService");
			Examen exa = new Examen();
			exa.setExaCodigo(Examen.EXA_CODIGO_MP);
			exa = (Examen) examenService.getAll(exa).get(0);
			
			PersonaExamen personaExamen=new PersonaExamen();
			personaExamen.setPexaTipoExamen(cbExamen.getSelectedItem().toString());
			personaExamen.setPersona(per);
			personaExamen.setExamen(exa);
			personaExamen.setPexaCantidadPreguntas(preguntas);
			personaExamen.setPexaTiempo(tiempoMinutos);
			personaExamen.setPexaEstado("INICIADO");
			personaExamen.setPexaFecha(new Date());
			
			PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager.getBizObject("personaExamenService");
			personaExamenService.insert(personaExamen);
			
			panelMenu.cargarSubMenuExamenes(new javax.swing.JToggleButton (cbExamen.getSelectedItem().toString()));
			panelMenu.cargarExamenMultiplesChoice(personaExamen,generarListadoPreguntas(personaExamen));
			jif.setClosed(true);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<PreguntaInterfaz> generarListadoPreguntas(PersonaExamen personaExamen) throws Exception
	{
		PreguntaDefinition preguntaService = (PreguntaDefinition) ContextManager.getBizObject("preguntaService");
		Pregunta example=new Pregunta();
		example.setPreCat(personaExamen.getPexaTipoExamen());
		return preguntaService.getAll(example);
	}

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		jif.setClosed(true);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnGuardar;
	private javax.swing.JComboBox cbExamen;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel lbError;
	private javax.swing.JTextField txtPregunta;
	private javax.swing.JTextField txtTiempo;
	// End of variables declaration//GEN-END:variables
	private JInternalFrameTesterGral jif;
	private Persona per;
	private PanelMenuPrincipal panelMenu;
}