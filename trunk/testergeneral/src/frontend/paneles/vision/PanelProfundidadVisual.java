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
public class PanelProfundidadVisual extends javax.swing.JPanel implements
		Finalisable, PanelExamen {

	private JToggleButton btn;
	private PersonaExamen personaExamen;
	private ExamenDetalle exaDetalle;
	private List<Resultado> resultados = new ArrayList<Resultado>();
	private int dibu1 = 0;
	private int dibu2 = 0;
	private int dibu3 = 0;
	//private ThreadTrama thTrama;

	/** Creates new form PanelAgudezaVisual */
	public PanelProfundidadVisual(JToggleButton btn, PersonaExamen personaExamen) {
		this.btn = btn;
		this.personaExamen = personaExamen;
		initComponents();
		cargarImagenes();
		setKoIcon(btn1);
		setKoIcon(btn2);
		setKoIcon(btn3);

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_PROFUNDIDAD);
			exaDetalle = (ExamenDetalle) examenDetalleService
					.getAll(exaDetalle).get(0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		inicializarThreads();
	}

	public void inicializarThreads() {

		try {
		
			if (Util.thTrama != null && !(Util.thTrama.getTrama() instanceof TramaVision))
				Util.thTrama.desconnect();
			
			if (Util.thTrama == null)
			{
				ThreadTrama thTrama = new ThreadTrama(new TramaVision());
				thTrama.setEjecutar(false);
				Util.thTrama = thTrama;
				thTrama.setEjecucion(99999);
				thTrama.start();
			}

			
			Util.thTrama.sendOrden(ThreadTrama.ORDEN_IR_TEST3);
			
			
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void cargarImagenes() {
			String img = ContextManager
					.getProperty("EXAMEN.PROFUNDIDAD.VISUAL.IMG");

			Util.setIcon(lbImagen, img);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnGuardar = new javax.swing.JToggleButton();
		jLayeredPane1 = new javax.swing.JLayeredPane();
		btn3 = new javax.swing.JButton();
		btn1 = new javax.swing.JButton();
		btn2 = new javax.swing.JButton();
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
		btn3.setBounds(320, 390, 50, 40);
		jLayeredPane1.add(btn3, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn1
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn1ActionPerformed(evt);
			}
		});
		btn1.setBounds(30, 390, 50, 40);
		jLayeredPane1.add(btn1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn2
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn2ActionPerformed(evt);
			}
		});
		btn2.setBounds(160, 390, 50, 40);
		jLayeredPane1.add(btn2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		lbImagen
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\vision\\profundidad visual\\planos.png")); // NOI18N
		lbImagen.setBounds(10, 10, 430, 420);
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
																btnGuardar)
														.addComponent(
																jLayeredPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																458,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(279, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addComponent(jLayeredPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 439,
						javax.swing.GroupLayout.PREFERRED_SIZE).addGap(5, 5, 5)
						.addComponent(btnGuardar).addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

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

	private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {
		if (dibu3 == 1) {
			dibu3 = 0;
			setKoIcon((JButton) evt.getSource());
		} else if (dibu3 == 0) {
			dibu3 = 1;
			setOkIcon((JButton) evt.getSource());
		}
	}

	public void setOkIcon(JButton btn) {
		Util.setIcon(btn, Constantes.IMG_BTN_ACEPTAR);
	}

	public void setKoIcon(JButton btn) {
		Util.setIcon(btn, Constantes.IMG_BTN_CANCELAR32);
	}

	public void setNullIcon(JButton btn) {
		btn.setIcon(null);
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

	public Double getPorcentaje() {
		Double porcentaje = ((dibu1 + dibu2 + dibu3) / 3d) * 100;
		return Util.redondear(porcentaje);
	}

	public boolean isAprobed() {
		if (this.personaExamen.getPexaTipoExamen().equals(PersonaExamen.TIPO_EXAMEN_PROFECIONAL)
				&& getPorcentaje().intValue() != 100)
			return false;

		if (this.personaExamen.getPexaTipoExamen().equals(PersonaExamen.TIPO_EXAMEN_PARTICULAR)
				&& getPorcentaje().intValue() < 66)
			return false;

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
			resultadoDetalleExamen.setRdeDetalleResultado("Porcentaje: "
					+ getPorcentaje());
			resultadoDetalleExamen.setRdeParametrosCorrecion(exaDetalle.getExadParametrosCorrecion());
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
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JLabel lbImagen;
	// End of variables declaration//GEN-END:variables

}