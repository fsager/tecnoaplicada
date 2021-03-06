/*
 * PanelAgudezaVisual.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.vision;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Examen;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.Resultado;
import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.exceptions.ExceptionIsNotHadware;
import testerGeneral.focus.MyOwnFocusTraversalPolicy;
import testerGeneral.service.ExamenDetalleDefinition;
import testerGeneral.service.PersonaExamenDefinition;
import testerGeneral.service.ResultadoDetalleExamenDefinition;
import testerGeneral.threads.ThreadTrama;
import examenes.psicometrico.domain.TramaVision;
import examenes.util.ExamenesUtils;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.examenes.Finalisable;
import frontend.paneles.examenes.PanelDetalleExamen;
import frontend.paneles.examenes.PanelExamen;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelForia extends javax.swing.JPanel implements Finalisable,
		PanelExamen {

	private JToggleButton btn;
	private PersonaExamen personaExamen;
	private ExamenDetalle exaDetalle;
	private List<Resultado> resultados = new ArrayList<Resultado>();
	//private ThreadTrama thTrama;

	/** Creates new form PanelAgudezaVisual */
	public PanelForia(JToggleButton btn, PersonaExamen personaExamen) {
		this.btn = btn;
		this.personaExamen = personaExamen;
		initComponents();
		cargarImagenes();
		Util.mostrarError(lbError, null, true);

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_FORIA);
			exaDetalle = (ExamenDetalle) examenDetalleService
					.getAll(exaDetalle).get(0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		if(Util.connectToHard)
			inicializarThreads();

	}

	public void inicializarThreads() {

		try {

			if (Util.thTrama != null && !(Util.thTrama.getTrama() instanceof TramaVision))
				Util.thTrama.desconnect();
			
			if (Util.thTrama == null)
			{
				ThreadTrama thTrama = new ThreadTrama(new TramaVision());
				Util.thTrama.setEjecutar(false);
				Util.thTrama = thTrama;
				Util.thTrama.setEjecucion(99999);
				Util.thTrama.start();
			}
			
			Util.thTrama.sendOrden(ThreadTrama.ORDEN_IR_TEST4);

		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void cargarImagenes() {

		String img = ContextManager.getProperty("EXAMEN.FORIA.IMG");
		Util.setIcon(lbImagen, img);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnGuardar = new javax.swing.JToggleButton();
		lbError = new javax.swing.JLabel();
		jLayeredPane1 = new javax.swing.JLayeredPane();
		lbImagen = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jFormattedColumna = new javax.swing.JFormattedTextField();
		jLabel5 = new javax.swing.JLabel();
		jFormattedFile = new javax.swing.JFormattedTextField();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();

		setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				formFocusGained(evt);
			}
		});

		btnGuardar.setFont(new java.awt.Font("Segoe UI", 3, 14));
		btnGuardar.setForeground(new java.awt.Color(0, 0, 255));
		btnGuardar.setText("Guardar Resultados");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbError.setText(Constantes.ERROR_SIN_RESULTADOS);

		jLayeredPane1.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		lbImagen
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\vision\\foria\\foria.png")); // NOI18N
		lbImagen.setBounds(10, 10, 660, 270);
		jLayeredPane1.add(lbImagen, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel2.setForeground(new java.awt.Color(0, 0, 255));
		jLabel2.setText("Fila indicada:");

		jFormattedColumna
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedColumna
				.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

		jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel5.setForeground(new java.awt.Color(0, 0, 255));
		jLabel5.setText("Columna indicada:");

		jFormattedFile
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedFile
				.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

		jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel3.setForeground(new java.awt.Color(0, 0, 255));
		jLabel3.setText("(A-C)");

		jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel4.setForeground(new java.awt.Color(0, 0, 255));
		jLabel4.setText("(1-13)");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(12, 12, 12)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				lbError,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				509,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnGuardar))
														.addComponent(
																jLayeredPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																682,
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
																				jLabel5)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jFormattedColumna,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				31,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				5,
																				5,
																				5)
																		.addComponent(
																				jLabel4))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jFormattedFile,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				31,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabel3)))
										.addContainerGap(42, Short.MAX_VALUE)));
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
																		.addGap(
																				166,
																				166,
																				166)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel5)
																						.addComponent(
																								jFormattedColumna,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel4))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jFormattedFile,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel3)))
														.addComponent(
																jLayeredPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																287,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnGuardar)
														.addComponent(
																lbError,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(156, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void formFocusGained(java.awt.event.FocusEvent evt) {
		jFormattedColumna.requestFocus();
	}

	public boolean isExamenValid() {
		Util.mostrarError(lbError, null, true);

		if (!isColValueValid()) {
			Util
					.mostrarError(
							lbError,
							"Debe ingresar una columna indicada v�lida. Valores entre 1 y 13.",
							false);
			return false;
		} else if (!isFilaValueValid()) {
			Util
					.mostrarError(
							lbError,
							"Debe ingresar una fila indicada v�lida. Valores entre A y C.",
							false);
			return false;
		}

		return true;
	}

	public boolean isColValueValid() {
		try {
			int value = Integer.valueOf(jFormattedColumna.getText());
			if (value == 0 || value > 13)
				return false;
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public boolean isFilaValueValid() {

		
		try {
			if (!jFormattedFile.getText().isEmpty()) {
				
				int value = getFilaNumber().intValue();
				if (value==0 || value > 3)
					return false;
			}
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}
	
	public Double getFilaNumber()
	{
		String fila=jFormattedFile.getText();
		
		if(fila.compareToIgnoreCase("A")==0)
		{
			return new Double(1);
		}
		else if(fila.compareToIgnoreCase("B")==0)
		{
			return new Double(2);
		}
		else if(fila.compareToIgnoreCase("C")==0)
		{
			return new Double(3);
		}
		else
		{
			return new Double(4);
		}
	}

	public void cargarResultados() {
		resultados.clear();

		Resultado res = new Resultado();
		res.setResEtapa(0l);
		res.setResEtapaDesc("Foria Columna");
		res.setResValor1(Double.valueOf(jFormattedColumna.getText()));

		if (res.getResValor1().intValue() >= 4
				&& res.getResValor1().intValue() <= 6)
			res.setResEtapaDesc("Ligera foria vertical izquierda.");
		if (res.getResValor1().intValue() >= 8
				&& res.getResValor1().intValue() <= 10)
			res.setResEtapaDesc("Ligera foria vertical derecha.");
		else if (res.getResValor1().intValue() == 7)
			res.setResEtapaDesc("No presenta foria vertical.");
		else if (res.getResValor1().intValue() > 10)
			res.setResEtapaDesc("Foria vertical derecha.");
		else if (res.getResValor1().intValue() < 4)
			res.setResEtapaDesc("Foria vertical izquierda.");

		resultados.add(res);

		if (!jFormattedFile.getText().isEmpty())
		{
			res = new Resultado();
			res.setResEtapa(1l);
			res.setResValor1(getFilaNumber());

			if (res.getResValor1().intValue() == 1)
				res.setResEtapaDesc("Ligera foria horizontal superior.");
			else if (res.getResValor1().intValue() == 2)
				res.setResEtapaDesc("No presenta foria horizontal.");
			else if (res.getResValor1().intValue() == 3)
				res.setResEtapaDesc("Ligera foria horizontal inferior.");
			/*else if (res.getResValor1().intValue() > 3)
				res.setResEtapaDesc("Foria horizontal inferior.");
			else if (res.getResValor1().intValue() < 1)
				res.setResEtapaDesc("Foria horizontal superior.");*/

			resultados.add(res);
		}


	}

	public String getResultado() {

		if (!isAprobed())
			return Examen.RESULTADO_FUERA_DERIVACION;

		return Examen.RESULTADO_DENTRO;
	}

	public boolean isAprobed() {

		int columna = Integer.valueOf(jFormattedColumna.getText());

		if (columna < 4 || columna > 10) {
			return false;
		}

		try {
			int fila = Integer.valueOf(jFormattedFile.getText());

			if (fila < 1 || fila > 3) {
				return false;
			}
		} catch (java.lang.NumberFormatException e) {

		}

		return true;
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		btnGuardar.setSelected(false);
		ResultadoDetalleExamenDefinition resultadoDetalleExamenService = (ResultadoDetalleExamenDefinition) ContextManager
				.getBizObject("resultadoDetalleExamenService");
		PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
				.getBizObject("personaExamenService");

		try {

			if (personaExamen != null && personaExamen.getPexaId() == null) {
				personaExamen.setPexaEstado("INICIADO");
				personaExamen.setPexaFecha(new Date());
				personaExamenService.insert(personaExamen);
			}

			if (isExamenValid()) {
				ResultadoDetalleExamen resultadoDetalleExamen = new ResultadoDetalleExamen();
				resultadoDetalleExamen.setExamenDetalle(exaDetalle);
				resultadoDetalleExamen.setPersonaExamen(personaExamen);
				List lstResultados = resultadoDetalleExamenService
						.getAll(resultadoDetalleExamen);

				if (lstResultados.size() < 1) {
					resultadoDetalleExamenService
							.insert(resultadoDetalleExamen);
				} else if (lstResultados.size() == 1) {
					resultadoDetalleExamen = (ResultadoDetalleExamen) lstResultados
							.get(0);
				}

				cargarResultados();
				String detalleResultado = "<HTML>";
				Set setResultados = resultadoDetalleExamen.getResultados();
				setResultados.clear();
				for (int i = 0; i < this.resultados.size(); i++) {
					this.resultados.get(i).setResultadoDetalleExamen(
							resultadoDetalleExamen);
					setResultados.add(this.resultados.get(i));
					detalleResultado = detalleResultado
							+ this.resultados.get(i).getResEtapaDesc() + "<BR>";
				}

				String resultado = getResultado();

				resultadoDetalleExamen.setRdeResultado(resultado);
				resultadoDetalleExamen.setRdeDetalleResultado(detalleResultado
						+ "</HTML>");
				resultadoDetalleExamen.setRdeParametrosCorrecion(exaDetalle.getExadParametrosCorrecion());
				resultadoDetalleExamenService.update(resultadoDetalleExamen);

				btn.setForeground(Color.BLACK);
				Util.setIcon(btn, Constantes.IMG_ACEPTAR_SMALL);

				((PanelDetalleExamen) btn.getParent()).nextExamen(btn);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void finalizar() {


	}

	@Override
	public void setBtn(JToggleButton btn) {
		this.btn = btn;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.JFormattedTextField jFormattedColumna;
	private javax.swing.JFormattedTextField jFormattedFile;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbImagen;
	// End of variables declaration//GEN-END:variables

}