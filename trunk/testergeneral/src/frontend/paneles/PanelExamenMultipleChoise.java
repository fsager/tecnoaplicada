/*
 * PanelExamenMultipleChoise.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.util.List;

import javax.swing.JToggleButton;

import testerGeneral.business.PreguntasBiz;
import testerGeneral.domain.PreguntaInterfaz;
import frontend.paneles.examenes.Finalisable;
import frontend.paneles.examenes.PanelExamen;

/**
 *
 * @author  __USER__
 */
public class PanelExamenMultipleChoise extends javax.swing.JPanel implements
		Finalisable, PanelExamen {

	private PreguntaInterfaz pregunta;
	private PreguntasBiz preguntasBiz;

	/** Creates new form PanelExamenMultipleChoise */
	public PanelExamenMultipleChoise(List<PreguntaInterfaz> preguntas) {
		initComponents();
	}

	public void mostrarPregunta(String idPregunta) throws Exception {
		pregunta = preguntasBiz.getPreguntaFromId(idPregunta);
		mostrarPregunta();
	}

	public void mostrarPregunta() {
		lbPregunta.setText(pregunta.getPreDetalle());
	}
	
	public void setPreguntasBiz(PreguntasBiz preguntasBiz) {
		this.preguntasBiz = preguntasBiz;
	}
	
	public PreguntasBiz getPreguntasBiz() {
		return preguntasBiz;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		lbPregunta = new javax.swing.JLabel();

		lbPregunta.setText("jLabel1");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(
						lbPregunta, javax.swing.GroupLayout.PREFERRED_SIZE,
						345, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(43, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(87, 87, 87).addComponent(
						lbPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(123, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	@Override
	public void finalizar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBtn(JToggleButton btn) {
		// TODO Auto-generated method stub

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel lbPregunta;
	// End of variables declaration//GEN-END:variables

}