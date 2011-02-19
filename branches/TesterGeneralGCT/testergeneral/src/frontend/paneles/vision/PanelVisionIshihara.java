/*
 * PanelAgudezaVisual.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.vision;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Examen;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.Resultado;
import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.exceptions.ExceptionIsNotHadware;
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
public class PanelVisionIshihara extends javax.swing.JPanel implements
		Finalisable, PanelExamen {

	private JToggleButton btn;
	private PersonaExamen personaExamen;
	private ExamenDetalle exaDetalle;
	private List<Resultado> resultados = new ArrayList<Resultado>();
	private int dibu1 = 1;
	private int dibu2 = 1;
	private int dibu3 = 1;
	private int dibu4 = 1;
	private int dibu5 = 1;

	//private ThreadTrama thTrama;

	/** Creates new form PanelAgudezaVisual */
	public PanelVisionIshihara(JToggleButton btn, PersonaExamen personaExamen) {
		this.btn = btn;
		this.personaExamen = personaExamen;
		initComponents();
		cargarImagenes();
		Util.mostrarError(lbError, null, true);
		setOkIcon(btn1);
		setOkIcon(btn2);
		setOkIcon(btn3);
		setOkIcon(btn4);
		setOkIcon(btn5);

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_ISHIHARA);
			exaDetalle = (ExamenDetalle) examenDetalleService
					.getAll(exaDetalle).get(0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (Util.connectToHard)
			inicializarThreads();
	}

	public void inicializarThreads() {

		try {
			if (Util.thTrama != null
					&& !(Util.thTrama.getTrama() instanceof TramaVision))
				Util.thTrama.desconnect();

			if (Util.thTrama == null) {
				ThreadTrama thTrama = new ThreadTrama(new TramaVision());
				Util.thTrama.setEjecutar(false);
				Util.thTrama = thTrama;
				Util.thTrama.setEjecucion(99999);
				Util.thTrama.start();
			}

			Util.thTrama.sendOrden(ThreadTrama.ORDEN_IR_TEST2);

		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void setOkIcon(JButton btn) {
		Util.setIcon(btn, Constantes.IMG_BTN_ACEPTAR);
	}

	public void setKoIcon(JButton btn) {
		Util.setIcon(btn, Constantes.IMG_BTN_CANCELAR32);
	}

	public void cargarImagenes() {
		String binocular = ContextManager
				.getProperty("EXAMEN.VISION.ISHIHARA.IMG");
		Util.setIcon(lbImagen, binocular);
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
		btn3 = new javax.swing.JButton();
		btn1 = new javax.swing.JButton();
		btn2 = new javax.swing.JButton();
		btn4 = new javax.swing.JButton();
		btn5 = new javax.swing.JButton();
		lbImagen = new javax.swing.JLabel();

		setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

		btn3
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn3ActionPerformed(evt);
			}
		});
		btn3.setBounds(80, 310, 70, 50);
		jLayeredPane1.add(btn3, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn1
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn1ActionPerformed(evt);
			}
		});
		btn1.setBounds(70, 130, 70, 50);
		jLayeredPane1.add(btn1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn2
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn2ActionPerformed(evt);
			}
		});
		btn2.setBounds(280, 130, 70, 50);
		jLayeredPane1.add(btn2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn4
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn4ActionPerformed(evt);
			}
		});
		btn4.setBounds(280, 310, 70, 50);
		jLayeredPane1.add(btn4, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn5
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn5ActionPerformed(evt);
			}
		});
		btn5.setBounds(180, 220, 70, 50);
		jLayeredPane1.add(btn5, javax.swing.JLayeredPane.DEFAULT_LAYER);

		lbImagen
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\vision\\isihara\\IsiharaApplicacion.png")); // NOI18N
		lbImagen.setBounds(10, 10, 400, 360);
		jLayeredPane1.add(lbImagen, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jLayeredPane1,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																436,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				lbError,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				272,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnGuardar)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												jLayeredPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												380,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lbError,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnGuardar))
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {
		if (dibu5 == 1) {
			dibu5 = 0;
			setKoIcon((JButton) evt.getSource());
		} else if (dibu5 == 0) {
			dibu5 = 1;
			setOkIcon((JButton) evt.getSource());
		}
	}

	private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {
		if (dibu4 == 1) {
			dibu4 = 0;
			setKoIcon((JButton) evt.getSource());
		} else if (dibu4 == 0) {
			dibu4 = 1;
			setOkIcon((JButton) evt.getSource());
		}
	}

	private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {
		if (dibu3 == 1) {
			dibu3 = 0;
			setKoIcon((JButton) evt.getSource());
		} else if (dibu3 == 0) {
			dibu3 = 1;
			setOkIcon((JButton) evt.getSource());
		}
	}

	private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {
		if (dibu2 == 1) {
			dibu2 = 0;
			setKoIcon((JButton) evt.getSource());
		} else if (dibu2 == 0) {
			dibu2 = 1;
			setOkIcon((JButton) evt.getSource());
		}
	}

	private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (dibu1 == 1) {
			dibu1 = 0;
			setKoIcon((JButton) evt.getSource());
		} else if (dibu1 == 0) {
			dibu1 = 1;
			setOkIcon((JButton) evt.getSource());
		}
	}

	public void cargarResultados() {
		resultados.clear();
		Resultado res = new Resultado();
		res.setResEtapa(0l);
		res.setResValor1(getPorcentaje());
		resultados.add(res);
	}

	public String getResultado() {
		if (!isAprobed())
			return Examen.RESULTADO_FUERA_DERIVACION;

		return Examen.RESULTADO_DENTRO;
	}

	public boolean isAprobed() {
		if (this.personaExamen.getPexaTipoExamen().equals(
				PersonaExamen.TIPO_EXAMEN_PROFECIONAL)
				&& getPorcentaje().intValue() < 80)
			return false;

		if (this.personaExamen.getPexaTipoExamen().equals(
				PersonaExamen.TIPO_EXAMEN_PARTICULAR)
				&& getPorcentaje().intValue() < 80)
			return false;

		return true;
	}

	public Double getPorcentaje() {
		Double porcentaje = ((dibu1 + dibu2 + dibu3 + dibu4 + dibu5) / 5d) * 100;
		Util.redondear(porcentaje);
		return Util.redondear(porcentaje);
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

			ResultadoDetalleExamen resultadoDetalleExamen = new ResultadoDetalleExamen();
			resultadoDetalleExamen.setExamenDetalle(exaDetalle);
			resultadoDetalleExamen.setPersonaExamen(personaExamen);
			List lstResultados = resultadoDetalleExamenService
					.getAll(resultadoDetalleExamen);

			if (lstResultados.size() < 1) {
				resultadoDetalleExamenService.insert(resultadoDetalleExamen);
			} else if (lstResultados.size() == 1) {
				resultadoDetalleExamen = (ResultadoDetalleExamen) lstResultados
						.get(0);
			}

			cargarResultados();

			Set setResultados = resultadoDetalleExamen.getResultados();
			setResultados.clear();
			for (int i = 0; i < this.resultados.size(); i++) {
				this.resultados.get(i).setResultadoDetalleExamen(
						resultadoDetalleExamen);
				setResultados.add(this.resultados.get(i));
			}

			String resultado = getResultado();

			resultadoDetalleExamen.setRdeResultado(resultado);

			String rde_detalle = "Normal";
			if (getPorcentaje() < 100) {
				rde_detalle = "Presenta alteraciones";
			}

			resultadoDetalleExamen.setRdeDetalleResultado(rde_detalle);

			resultadoDetalleExamen.setRdeParametrosCorrecion(exaDetalle
					.getExadParametrosCorrecion());

			resultadoDetalleExamenService.update(resultadoDetalleExamen);

			btn.setForeground(Color.BLACK);
			Util.setIcon(btn, Constantes.IMG_ACEPTAR_SMALL);

			((PanelDetalleExamen) btn.getParent()).nextExamen(btn);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void finalizar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBtn(JToggleButton btn) {
		this.btn = btn;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btn1;
	private javax.swing.JButton btn2;
	private javax.swing.JButton btn3;
	private javax.swing.JButton btn4;
	private javax.swing.JButton btn5;
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbImagen;
	// End of variables declaration//GEN-END:variables

}