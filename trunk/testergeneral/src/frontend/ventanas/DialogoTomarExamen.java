/*
 * DialogoTomarExamen.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Examen;
import testerGeneral.domain.Persona;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.service.ExamenDefinition;
import examenes.util.ExamenesUtils;
import frontend.paneles.PanelMenuPrincipal;
import frontend.paneles.examenes.PanelExamenes;
import frontend.utils.Util;

/**
 * 
 * @author __USER__
 */
public class DialogoTomarExamen extends JInternalFrameTesterGral {//JInternalFrameTesterGral

	private Persona per;
	private PanelMenuPrincipal panelMenu;

	/** Creates new form DialogoTomarExamen */
	public DialogoTomarExamen(Persona per, PanelMenuPrincipal panelMenu) {

		super("Seleccione un examen", false, true, false, false);

		this.per = per;
		this.panelMenu = panelMenu;

		initComponents();
		jLabel1.setText("Tipo de Examen:");
		action = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				actionBtnExamen(evt);
			}
		};
		cargarTipoExamen();
		cargarExamenes();
	}
	
	public void cargarTipoExamen() {
		Util.cargarDominios(cmbTipoExamen,Constantes.DOMINIO_CLAVE_TIPO_EXAMEN, false);
	}

	public void cargarExamenes() {
		try {

			List<Examen> examenes = ExamenesUtils.obtenerExamenes(true);

			GridLayout gridLayout = (GridLayout) panelExamenes.getLayout();
			gridLayout.setHgap(20);
			gridLayout.setVgap(20);
			gridLayout.setColumns(1);
			gridLayout.setRows(examenes.size());

			for (int i = 0; i < examenes.size(); i++) {
				JButton btnExamen = new JButton(examenes.get(i).getExaNombre());
				btnExamen.setActionCommand(examenes.get(i).getExaCodigo());
				panelExamenes.add(btnExamen);
				btnExamen.addActionListener(action);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void actionBtnExamen(java.awt.event.ActionEvent evt) {
		try
		{
			JButton btn = (JButton) evt.getSource();
			cerrar();
			if (btn.getActionCommand().equals(Examen.EXA_CODIGO_PSICOMETRICO)) {
				
				ExamenDefinition examenService = (ExamenDefinition) ContextManager.getBizObject("examenService");
				Examen exa = new Examen();
				exa.setExaCodigo(Examen.EXA_CODIGO_PSICOMETRICO);
				exa = (Examen) examenService.getAll(exa).get(0);
		
		
				testerGeneral.persistence.impl.Util.insertAudit(testerGeneral.persistence.impl.Util.ACTION_MENU_EXAMEN_PSICOMETRICO,null, null);
		
				PersonaExamen personaExamen=new PersonaExamen();
				personaExamen.setPexaTipoExamen(cmbTipoExamen.getSelectedItem().toString());
				personaExamen.setPersona(per);
				personaExamen.setExamen(exa);	
				
				panelMenu.cargarSubMenuExamenes();
				panelMenu.seleccionarExamenPsicometrico(personaExamen);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		panelExamenes = new javax.swing.JPanel();
		cmbTipoExamen = new javax.swing.JComboBox();
		jLabel1 = new javax.swing.JLabel();

		setTitle("Seleccione el examen que sea tomar");
		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameActivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosed(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosing(
					javax.swing.event.InternalFrameEvent evt) {
				formInternalFrameClosing(evt);
			}

			public void internalFrameDeactivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameDeiconified(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameIconified(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameOpened(
					javax.swing.event.InternalFrameEvent evt) {
			}
		});

		panelExamenes.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Ex\u00e1menes",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		panelExamenes.setLayout(new java.awt.GridLayout(1, 0));

		cmbTipoExamen.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel1.setText("Tipo de Ex\u00e1men:");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																panelExamenes,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																283,
																Short.MAX_VALUE)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				cmbTipoExamen,
																				0,
																				191,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																cmbTipoExamen,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												13, Short.MAX_VALUE)
										.addComponent(
												panelExamenes,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												224,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formInternalFrameClosing(
			javax.swing.event.InternalFrameEvent evt) {
		cerrar();
	}

	public void cerrar() {
		close();
		this.dispose();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JComboBox cmbTipoExamen;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel panelExamenes;
	// End of variables declaration//GEN-END:variables
	private ActionListener action;

}