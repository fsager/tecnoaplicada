/*
 * PanelPersonaExamen.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.examenes;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Persona;
import testerGeneral.domain.PersonaExamen;

/**
 *
 * @author  __USER__
 */
public class PanelPersonaExamen extends javax.swing.JPanel {

	/** Creates new form PanelPersonaExamen */
	public PanelPersonaExamen(PersonaExamen perExamen) {
		initComponents();

		txtApellido.setText(perExamen.getPersona().getPerApellido());
		txtNombre.setText(perExamen.getPersona().getPerNombre());
		txtTipoDoc.setText(perExamen.getPersona().getPerTipoDoc());
		txtNroDoc.setText(perExamen.getPersona().getPerNumeroDoc());
		txtTipoExamen.setText(perExamen.getPexaTipoExamen());
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		txtApellido = new javax.swing.JTextField();
		txtNombre = new javax.swing.JTextField();
		txtNroDoc = new javax.swing.JFormattedTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		txtTipoDoc = new javax.swing.JFormattedTextField();
		jLabel3 = new javax.swing.JLabel();
		txtTipoExamen = new javax.swing.JFormattedTextField();

		txtApellido.setEditable(false);

		txtNombre.setEditable(false);

		txtNroDoc.setEditable(false);

		jLabel1.setText(Constantes.LB_APELLIDO);

		jLabel2.setText(Constantes.LB_NOMBRE);

		jLabel4.setText(Constantes.LB_TIPO_DNI);

		jLabel5.setText(Constantes.LB_NRO_DOCUMENTO);

		txtTipoDoc.setEditable(false);

		jLabel3.setText("Tipo Examen:");

		txtTipoExamen.setEditable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
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
																jLabel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																86,
																Short.MAX_VALUE)
														.addComponent(
																jLabel4,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																86,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																txtApellido)
														.addComponent(
																txtTipoDoc,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																105,
																Short.MAX_VALUE))
										.addGap(53, 53, 53)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																89,
																Short.MAX_VALUE)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																89,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				txtNroDoc,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				159,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				33,
																				33,
																				33)
																		.addComponent(
																				jLabel3)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtTipoExamen,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				180,
																				Short.MAX_VALUE))
														.addComponent(
																txtNombre,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																448,
																Short.MAX_VALUE))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
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
																		.addContainerGap(
																				16,
																				Short.MAX_VALUE)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addGap(
																												26,
																												26,
																												26)
																										.addComponent(
																												jLabel4,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												23,
																												Short.MAX_VALUE))
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addGap(
																												29,
																												29,
																												29)
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																txtTipoDoc,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel5)
																														.addComponent(
																																txtNroDoc,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel3)
																														.addComponent(
																																txtTipoExamen,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)))))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								txtApellido,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								txtNombre,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JTextField txtApellido;
	private javax.swing.JTextField txtNombre;
	private javax.swing.JFormattedTextField txtNroDoc;
	private javax.swing.JFormattedTextField txtTipoDoc;
	private javax.swing.JFormattedTextField txtTipoExamen;
	// End of variables declaration//GEN-END:variables

}