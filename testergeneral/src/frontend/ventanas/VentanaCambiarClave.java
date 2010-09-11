/*
 * VentanaCambiarClave.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.UsuarioCommon;
import frontend.paneles.PanelCambiarClave;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class VentanaCambiarClave extends JInternalFrameTesterGral {

	/** Creates new form VentanaCambiarClave */
	public VentanaCambiarClave(UsuarioCommon usr) {
		super(Constantes.VTN_TITLE_CAMBIAR_CLAVE, false, false, false, false);
		initComponents();

		PanelCambiarClave panelCambiarClave = new PanelCambiarClave(usr);
		panelCambiarClave.setPadre(this);
		this.getContentPane().add(panelCambiarClave);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		setClosable(true);
		setIconifiable(true);
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
				formInternalFrameIconified(evt);
			}

			public void internalFrameOpened(
					javax.swing.event.InternalFrameEvent evt) {
			}
		});
		getContentPane().setLayout(new java.awt.GridLayout(1, 1));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formInternalFrameClosing(
			javax.swing.event.InternalFrameEvent evt) {
		testerGeneral.persistence.impl.Util.insertAudit(testerGeneral.persistence.impl.Util.ACTION_SALIR,null,null);
		Util.frameContenedor.dispose();
		System.exit(0);
	}

	private void formInternalFrameIconified(
			javax.swing.event.InternalFrameEvent evt) {
		Util.minimizar(this);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

}