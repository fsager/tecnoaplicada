/*
 * PanelPersonaExamen.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.examenes;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Persona;

/**
 *
 * @author  __USER__
 */
public class PanelPersonaExamen extends javax.swing.JPanel {

	/** Creates new form PanelPersonaExamen */
	public PanelPersonaExamen(Persona per) {
		initComponents();

		txtApellido.setText(per.getPerApellido());
		txtNombre.setText(per.getPerNombre());
		txtTipoDoc.setText(per.getPerTipoDoc());
		txtNroDoc.setText(per.getPerNumeroDoc());
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

		txtApellido.setEditable(false);

		txtNombre.setEditable(false);

		txtNroDoc.setEditable(false);

		jLabel1.setText(Constantes.LB_APELLIDO);

		jLabel2.setText(Constantes.LB_NOMBRE);

		jLabel4.setText(Constantes.LB_TIPO_DNI);

		jLabel5.setText(Constantes.LB_NRO_DOCUMENTO);

		txtTipoDoc.setEditable(false);

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
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																104,
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
																txtNombre,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																219,
																Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel4,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																93,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																txtNroDoc,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																159,
																Short.MAX_VALUE)
														.addComponent(
																txtTipoDoc,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																159,
																Short.MAX_VALUE))
										.addContainerGap()));
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
														.addComponent(jLabel1)
														.addComponent(jLabel4)
														.addComponent(
																txtTipoDoc,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtApellido,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(jLabel5)
														.addComponent(
																txtNombre,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtNroDoc,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JTextField txtApellido;
	private javax.swing.JTextField txtNombre;
	private javax.swing.JFormattedTextField txtNroDoc;
	private javax.swing.JFormattedTextField txtTipoDoc;
	// End of variables declaration//GEN-END:variables

}