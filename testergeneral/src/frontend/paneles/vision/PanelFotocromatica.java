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
 * @author __USER__
 */
public class PanelFotocromatica extends javax.swing.JPanel implements
		Finalisable, PanelExamen {

	private JToggleButton btn;
	private PersonaExamen personaExamen;
	private ExamenDetalle exaDetalle;
	private List<Resultado> resultados = new ArrayList<Resultado>();
	private static int total= 4;
	private static int cantidadAciertos= total;
	private boolean started=false;
	//private ThreadTrama thTrama;

	/** Creates new form PanelAgudezaVisual */
	public PanelFotocromatica(JToggleButton btn, PersonaExamen personaExamen) {
		this.btn = btn;
		this.personaExamen = personaExamen;
		initComponents();
		cargarImagenes();
		Util.mostrarError(lbError, null, true);
		initButtons();
		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_FOTOCROMATICA);
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
		catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void initVariables()
	{
		setActions();
		cantidadAciertos=total;
		started=true;
	}
	
	public void initButtons() {
		setActions();
		
		initBtnFilas();
		initBtnColumnas();
		
		initColumna1(false, false);
		initColumna2(false, false);
		initColumna3(false, false);
		initColumna4(false, false);
		initColumna5(false, false);
		initColumna6(false, false);
		
		initFila1(false, false);
		initFila2(false, false);
		initFila3(false, false);
		initFila4(false, false);
	}
	
	public void setActions()
	{
		btn111.setActionCommand("ok");
		btn122.setActionCommand("ok");
		btn133.setActionCommand("ok");
		btn144.setActionCommand("ok");
		btn155.setActionCommand("ok");
		btn166.setActionCommand("ok");
		btn211.setActionCommand("ok");
		btn222.setActionCommand("ok");
		btn233.setActionCommand("ok");
		btn244.setActionCommand("ok");
		btn255.setActionCommand("ok");
		btn266.setActionCommand("ok");
		btn311.setActionCommand("ok");
		btn322.setActionCommand("ok");
		btn333.setActionCommand("ok");
		btn344.setActionCommand("ok");
		btn355.setActionCommand("ok");
		btn366.setActionCommand("ok");
		btn411.setActionCommand("ok");
		btn422.setActionCommand("ok");
		btn433.setActionCommand("ok");
		btn444.setActionCommand("ok");
		btn455.setActionCommand("ok");
		btn466.setActionCommand("ok");
	}

	public void initBtnFilas()
	{
		setNullIcon(btnFila1);
		setNullIcon(btnFila2);
		setNullIcon(btnFila3);
		setNullIcon(btnFila4);
	}
	
	public void initBtnColumnas()
	{
		setNullIcon(btnCol1);
		setNullIcon(btnCol2);
		setNullIcon(btnCol3);
		setNullIcon(btnCol4);
		setNullIcon(btnCol5);
		setNullIcon(btnCol6);
	}
	
	public void initColumna1(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn111);
		else
			setNullIcon(btn111);
		btn111.setEnabled(enabled);

		if (ok)
			setOkIcon(btn211);
		else
			setNullIcon(btn211);
		btn211.setEnabled(enabled);

		if (ok)
			setOkIcon(btn311);
		else
			setNullIcon(btn311);
		btn311.setEnabled(enabled);

		if (ok)
			setOkIcon(btn411);
		else
			setNullIcon(btn411);
		btn411.setEnabled(enabled);

	}
	
	public void initColumna2(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn122);
		else
			setNullIcon(btn122);
		btn122.setEnabled(enabled);

		if (ok)
			setOkIcon(btn222);
		else
			setNullIcon(btn222);
		btn222.setEnabled(enabled);

		if (ok)
			setOkIcon(btn322);
		else
			setNullIcon(btn322);
		btn322.setEnabled(enabled);

		if (ok)
			setOkIcon(btn422);
		else
			setNullIcon(btn422);
		btn422.setEnabled(enabled);

	}
	
	public void initColumna3(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn133);
		else
			setNullIcon(btn133);
		btn133.setEnabled(enabled);

		if (ok)
			setOkIcon(btn233);
		else
			setNullIcon(btn233);
		btn233.setEnabled(enabled);

		if (ok)
			setOkIcon(btn333);
		else
			setNullIcon(btn333);
		btn333.setEnabled(enabled);

		if (ok)
			setOkIcon(btn433);
		else
			setNullIcon(btn433);
		btn433.setEnabled(enabled);

	}
	
	public void initColumna4(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn144);
		else
			setNullIcon(btn144);
		btn144.setEnabled(enabled);

		if (ok)
			setOkIcon(btn244);
		else
			setNullIcon(btn244);
		btn244.setEnabled(enabled);

		if (ok)
			setOkIcon(btn344);
		else
			setNullIcon(btn344);
		btn344.setEnabled(enabled);

		if (ok)
			setOkIcon(btn444);
		else
			setNullIcon(btn444);
		btn444.setEnabled(enabled);

	}
	
	public void initColumna5(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn155);
		else
			setNullIcon(btn155);
		btn155.setEnabled(enabled);

		if (ok)
			setOkIcon(btn255);
		else
			setNullIcon(btn255);
		btn255.setEnabled(enabled);

		if (ok)
			setOkIcon(btn355);
		else
			setNullIcon(btn355);
		btn355.setEnabled(enabled);

		if (ok)
			setOkIcon(btn455);
		else
			setNullIcon(btn455);
		btn455.setEnabled(enabled);

	}

	public void initColumna6(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn166);
		else
			setNullIcon(btn166);
		btn166.setEnabled(enabled);

		if (ok)
			setOkIcon(btn266);
		else
			setNullIcon(btn266);
		btn266.setEnabled(enabled);

		if (ok)
			setOkIcon(btn366);
		else
			setNullIcon(btn366);
		btn366.setEnabled(enabled);

		if (ok)
			setOkIcon(btn466);
		else
			setNullIcon(btn466);
		btn466.setEnabled(enabled);

	}
	
	public void initFila1(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn111);
		else
			setNullIcon(btn111);
		btn111.setEnabled(enabled);

		if (ok)
			setOkIcon(btn122);
		else
			setNullIcon(btn122);
		btn122.setEnabled(enabled);

		if (ok)
			setOkIcon(btn133);
		else
			setNullIcon(btn133);
		btn133.setEnabled(enabled);

		if (ok)
			setOkIcon(btn144);
		else
			setNullIcon(btn144);
		btn144.setEnabled(enabled);

		if (ok)
			setOkIcon(btn155);
		else
			setNullIcon(btn155);
		btn155.setEnabled(enabled);

		if (ok)
			setOkIcon(btn166);
		else
			setNullIcon(btn166);
		btn166.setEnabled(enabled);
	}

	public void initFila2(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn211);
		else
			setNullIcon(btn211);
		btn211.setEnabled(enabled);

		if (ok)
			setOkIcon(btn222);
		else
			setNullIcon(btn222);
		btn222.setEnabled(enabled);

		if (ok)
			setOkIcon(btn233);
		else
			setNullIcon(btn233);
		btn233.setEnabled(enabled);

		if (ok)
			setOkIcon(btn244);
		else
			setNullIcon(btn244);
		btn244.setEnabled(enabled);

		if (ok)
			setOkIcon(btn255);
		else
			setNullIcon(btn255);
		btn255.setEnabled(enabled);

		if (ok)
			setOkIcon(btn266);
		else
			setNullIcon(btn266);
		btn266.setEnabled(enabled);
	}

	public void initFila3(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn311);
		else
			setNullIcon(btn311);
		btn311.setEnabled(enabled);

		if (ok)
			setOkIcon(btn322);
		else
			setNullIcon(btn322);
		btn322.setEnabled(enabled);

		if (ok)
			setOkIcon(btn333);
		else
			setNullIcon(btn333);
		btn333.setEnabled(enabled);

		if (ok)
			setOkIcon(btn344);
		else
			setNullIcon(btn344);
		btn344.setEnabled(enabled);

		if (ok)
			setOkIcon(btn355);
		else
			setNullIcon(btn355);
		btn355.setEnabled(enabled);

		if (ok)
			setOkIcon(btn366);
		else
			setNullIcon(btn366);
		btn366.setEnabled(enabled);
	}
	
	public void initFila4(boolean enabled, boolean ok) {
		if (ok)
			setOkIcon(btn411);
		else
			setNullIcon(btn411);
		btn411.setEnabled(enabled);

		if (ok)
			setOkIcon(btn422);
		else
			setNullIcon(btn422);
		btn422.setEnabled(enabled);

		if (ok)
			setOkIcon(btn433);
		else
			setNullIcon(btn433);
		btn433.setEnabled(enabled);

		if (ok)
			setOkIcon(btn444);
		else
			setNullIcon(btn444);
		btn444.setEnabled(enabled);

		if (ok)
			setOkIcon(btn455);
		else
			setNullIcon(btn455);
		btn455.setEnabled(enabled);

		if (ok)
			setOkIcon(btn466);
		else
			setNullIcon(btn466);
		btn466.setEnabled(enabled);
	}
	
	public void cargarImagenes() {
		String binocular = ContextManager
				.getProperty("EXAMEN.FOTOCRAMATICA.VISUAL.IMG");
		Util.setIcon(lbImagen, binocular);
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

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnGuardar = new javax.swing.JToggleButton();
		lbError = new javax.swing.JLabel();
		jLayeredPane1 = new javax.swing.JLayeredPane();
		btn222 = new javax.swing.JButton();
		btn211 = new javax.swing.JButton();
		btn233 = new javax.swing.JButton();
		btn244 = new javax.swing.JButton();
		btn255 = new javax.swing.JButton();
		btn366 = new javax.swing.JButton();
		btn355 = new javax.swing.JButton();
		btn344 = new javax.swing.JButton();
		btn333 = new javax.swing.JButton();
		btn322 = new javax.swing.JButton();
		btn311 = new javax.swing.JButton();
		btn155 = new javax.swing.JButton();
		btn144 = new javax.swing.JButton();
		btn133 = new javax.swing.JButton();
		btn122 = new javax.swing.JButton();
		btn466 = new javax.swing.JButton();
		btn422 = new javax.swing.JButton();
		btn411 = new javax.swing.JButton();
		btn433 = new javax.swing.JButton();
		btn455 = new javax.swing.JButton();
		btn444 = new javax.swing.JButton();
		btn166 = new javax.swing.JButton();
		btn111 = new javax.swing.JButton();
		btnFila2 = new javax.swing.JButton();
		btnFila3 = new javax.swing.JButton();
		btnFila4 = new javax.swing.JButton();
		btnFila1 = new javax.swing.JButton();
		btnCol1 = new javax.swing.JButton();
		btnCol2 = new javax.swing.JButton();
		btnCol3 = new javax.swing.JButton();
		btnCol5 = new javax.swing.JButton();
		btnCol6 = new javax.swing.JButton();
		btnCol4 = new javax.swing.JButton();
		btn266 = new javax.swing.JButton();
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

		btn222
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn222.setBorderPainted(false);
		btn222.setContentAreaFilled(false);
		btn222.setEnabled(false);
		btn222.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn222ActionPerformed(evt);
			}
		});
		btn222.setBounds(170, 180, 80, 80);
		jLayeredPane1.add(btn222, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn211
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn211.setBorderPainted(false);
		btn211.setContentAreaFilled(false);
		btn211.setEnabled(false);
		btn211.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn211ActionPerformed(evt);
			}
		});
		btn211.setBounds(80, 180, 80, 80);
		jLayeredPane1.add(btn211, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn233
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn233.setBorderPainted(false);
		btn233.setContentAreaFilled(false);
		btn233.setEnabled(false);
		btn233.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn233ActionPerformed(evt);
			}
		});
		btn233.setBounds(260, 180, 80, 80);
		jLayeredPane1.add(btn233, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn244
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn244.setBorderPainted(false);
		btn244.setContentAreaFilled(false);
		btn244.setEnabled(false);
		btn244.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn244ActionPerformed(evt);
			}
		});
		btn244.setBounds(350, 180, 80, 80);
		jLayeredPane1.add(btn244, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn255
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn255.setBorderPainted(false);
		btn255.setContentAreaFilled(false);
		btn255.setEnabled(false);
		btn255.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn255ActionPerformed(evt);
			}
		});
		btn255.setBounds(430, 180, 80, 80);
		jLayeredPane1.add(btn255, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn366
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn366.setBorderPainted(false);
		btn366.setContentAreaFilled(false);
		btn366.setEnabled(false);
		btn366.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn366ActionPerformed(evt);
			}
		});
		btn366.setBounds(520, 260, 80, 80);
		jLayeredPane1.add(btn366, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn355
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn355.setBorderPainted(false);
		btn355.setContentAreaFilled(false);
		btn355.setEnabled(false);
		btn355.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn355ActionPerformed(evt);
			}
		});
		btn355.setBounds(430, 260, 80, 80);
		jLayeredPane1.add(btn355, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn344
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn344.setBorderPainted(false);
		btn344.setContentAreaFilled(false);
		btn344.setEnabled(false);
		btn344.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn344ActionPerformed(evt);
			}
		});
		btn344.setBounds(350, 260, 80, 80);
		jLayeredPane1.add(btn344, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn333
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn333.setBorderPainted(false);
		btn333.setContentAreaFilled(false);
		btn333.setEnabled(false);
		btn333.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn333ActionPerformed(evt);
			}
		});
		btn333.setBounds(260, 260, 80, 80);
		jLayeredPane1.add(btn333, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn322
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn322.setBorderPainted(false);
		btn322.setContentAreaFilled(false);
		btn322.setEnabled(false);
		btn322.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn322ActionPerformed(evt);
			}
		});
		btn322.setBounds(170, 260, 80, 80);
		jLayeredPane1.add(btn322, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn311
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn311.setBorderPainted(false);
		btn311.setContentAreaFilled(false);
		btn311.setEnabled(false);
		btn311.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn311ActionPerformed(evt);
			}
		});
		btn311.setBounds(80, 260, 80, 80);
		jLayeredPane1.add(btn311, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn155
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn155.setBorderPainted(false);
		btn155.setContentAreaFilled(false);
		btn155.setEnabled(false);
		btn155.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn155ActionPerformed(evt);
			}
		});
		btn155.setBounds(430, 100, 80, 80);
		jLayeredPane1.add(btn155, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn144
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn144.setBorderPainted(false);
		btn144.setContentAreaFilled(false);
		btn144.setEnabled(false);
		btn144.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn144ActionPerformed(evt);
			}
		});
		btn144.setBounds(350, 100, 80, 80);
		jLayeredPane1.add(btn144, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn133
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn133.setBorderPainted(false);
		btn133.setContentAreaFilled(false);
		btn133.setEnabled(false);
		btn133.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn133ActionPerformed(evt);
			}
		});
		btn133.setBounds(260, 100, 80, 80);
		jLayeredPane1.add(btn133, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn122
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn122.setBorderPainted(false);
		btn122.setContentAreaFilled(false);
		btn122.setEnabled(false);
		btn122.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn122ActionPerformed(evt);
			}
		});
		btn122.setBounds(170, 100, 80, 80);
		jLayeredPane1.add(btn122, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn466
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn466.setBorderPainted(false);
		btn466.setContentAreaFilled(false);
		btn466.setEnabled(false);
		btn466.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn466ActionPerformed(evt);
			}
		});
		btn466.setBounds(520, 340, 80, 80);
		jLayeredPane1.add(btn466, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn422
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn422.setBorderPainted(false);
		btn422.setContentAreaFilled(false);
		btn422.setEnabled(false);
		btn422.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn422ActionPerformed(evt);
			}
		});
		btn422.setBounds(170, 340, 80, 80);
		jLayeredPane1.add(btn422, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn411
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn411.setBorderPainted(false);
		btn411.setContentAreaFilled(false);
		btn411.setEnabled(false);
		btn411.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn411ActionPerformed(evt);
			}
		});
		btn411.setBounds(80, 340, 80, 80);
		jLayeredPane1.add(btn411, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn433
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn433.setBorderPainted(false);
		btn433.setContentAreaFilled(false);
		btn433.setEnabled(false);
		btn433.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn433ActionPerformed(evt);
			}
		});
		btn433.setBounds(260, 340, 80, 80);
		jLayeredPane1.add(btn433, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn455
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn455.setBorderPainted(false);
		btn455.setContentAreaFilled(false);
		btn455.setEnabled(false);
		btn455.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn455ActionPerformed(evt);
			}
		});
		btn455.setBounds(430, 340, 80, 80);
		jLayeredPane1.add(btn455, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn444
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn444.setBorderPainted(false);
		btn444.setContentAreaFilled(false);
		btn444.setEnabled(false);
		btn444.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn444ActionPerformed(evt);
			}
		});
		btn444.setBounds(350, 340, 80, 80);
		jLayeredPane1.add(btn444, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn166
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn166.setBorderPainted(false);
		btn166.setContentAreaFilled(false);
		btn166.setEnabled(false);
		btn166.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn166ActionPerformed(evt);
			}
		});
		btn166.setBounds(520, 100, 80, 80);
		jLayeredPane1.add(btn166, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn111
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn111.setBorderPainted(false);
		btn111.setContentAreaFilled(false);
		btn111.setEnabled(false);
		btn111.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn111ActionPerformed(evt);
			}
		});
		btn111.setBounds(80, 100, 80, 80);
		jLayeredPane1.add(btn111, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnFila2
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnFila2.setBorderPainted(false);
		btnFila2.setContentAreaFilled(false);
		btnFila2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFila2ActionPerformed(evt);
			}
		});
		btnFila2.setBounds(10, 180, 70, 80);
		jLayeredPane1.add(btnFila2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnFila3
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnFila3.setBorderPainted(false);
		btnFila3.setContentAreaFilled(false);
		btnFila3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFila3ActionPerformed(evt);
			}
		});
		btnFila3.setBounds(10, 260, 70, 80);
		jLayeredPane1.add(btnFila3, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnFila4
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnFila4.setBorderPainted(false);
		btnFila4.setContentAreaFilled(false);
		btnFila4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFila4ActionPerformed(evt);
			}
		});
		btnFila4.setBounds(10, 340, 70, 80);
		jLayeredPane1.add(btnFila4, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnFila1
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnFila1.setBorderPainted(false);
		btnFila1.setContentAreaFilled(false);
		btnFila1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnFila1ActionPerformed(evt);
			}
		});
		btnFila1.setBounds(10, 100, 70, 80);
		jLayeredPane1.add(btnFila1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnCol1
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnCol1.setBorderPainted(false);
		btnCol1.setContentAreaFilled(false);
		btnCol1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCol1ActionPerformed(evt);
			}
		});
		btnCol1.setBounds(80, 20, 80, 80);
		jLayeredPane1.add(btnCol1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnCol2
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnCol2.setBorderPainted(false);
		btnCol2.setContentAreaFilled(false);
		btnCol2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCol2ActionPerformed(evt);
			}
		});
		btnCol2.setBounds(170, 20, 80, 80);
		jLayeredPane1.add(btnCol2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnCol3
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnCol3.setBorderPainted(false);
		btnCol3.setContentAreaFilled(false);
		btnCol3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCol3ActionPerformed(evt);
			}
		});
		btnCol3.setBounds(260, 20, 80, 80);
		jLayeredPane1.add(btnCol3, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnCol5
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnCol5.setBorderPainted(false);
		btnCol5.setContentAreaFilled(false);
		btnCol5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCol5ActionPerformed(evt);
			}
		});
		btnCol5.setBounds(430, 20, 80, 80);
		jLayeredPane1.add(btnCol5, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnCol6
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnCol6.setBorderPainted(false);
		btnCol6.setContentAreaFilled(false);
		btnCol6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCol6ActionPerformed(evt);
			}
		});
		btnCol6.setBounds(520, 20, 80, 80);
		jLayeredPane1.add(btnCol6, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btnCol4
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btnCol4.setBorderPainted(false);
		btnCol4.setContentAreaFilled(false);
		btnCol4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCol4ActionPerformed(evt);
			}
		});
		btnCol4.setBounds(340, 20, 80, 80);
		jLayeredPane1.add(btnCol4, javax.swing.JLayeredPane.DEFAULT_LAYER);

		btn266
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\aceptar.png")); // NOI18N
		btn266.setBorderPainted(false);
		btn266.setContentAreaFilled(false);
		btn266.setEnabled(false);
		btn266.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn266ActionPerformed(evt);
			}
		});
		btn266.setBounds(520, 180, 80, 80);
		jLayeredPane1.add(btn266, javax.swing.JLayeredPane.DEFAULT_LAYER);

		lbImagen
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\vision\\colores\\colores.png")); // NOI18N
		lbImagen.setBounds(10, 10, 600, 415);
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
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				lbError,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				304,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnGuardar))
														.addComponent(
																jLayeredPane1,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																620,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(413, Short.MAX_VALUE)));
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
												432,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																lbError,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnGuardar))));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btn266ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btnCol4ActionPerformed(java.awt.event.ActionEvent evt) {
		total=4;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna1(false, false);
		initColumna2(false, false);
		initColumna3(false,false);
		
		initColumna5(false, false);
		initColumna6(false, false);
		
		initFila1(false, false);
		initFila2(false, false);
		initFila3(false, false);
		initFila4(false,false);	
		
		initColumna4(true, true);
		setOkIcon((JButton)evt.getSource());
	}

	private void btnCol6ActionPerformed(java.awt.event.ActionEvent evt) {
		total=4;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna1(false, false);
		initColumna2(false, false);
		initColumna3(false,false);
		initColumna4(false,false);
		initColumna5(false, false);
		
		
		initFila1(false, false);
		initFila2(false, false);
		initFila3(false, false);
		initFila4(false,false);	
		
		initColumna6(true,true);
		setOkIcon((JButton)evt.getSource());
	}

	private void btnCol5ActionPerformed(java.awt.event.ActionEvent evt) {
		total=4;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna1(false, false);
		initColumna2(false, false);
		initColumna3(false,false);
		initColumna4(false,false);
		
		initColumna6(false, false);
		
		initFila1(false, false);
		initFila2(false, false);
		initFila3(false, false);
		initFila4(false,false);	
		
		initColumna5(true,true);
		setOkIcon((JButton)evt.getSource());
	}

	private void btnCol3ActionPerformed(java.awt.event.ActionEvent evt) {
		total=4;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna1(false, false);
		initColumna2(false, false);
		
		initColumna4(false, false);
		initColumna5(false, false);
		initColumna6(false, false);
		
		initFila1(false, false);
		initFila2(false, false);
		initFila3(false, false);
		initFila4(false,false);	
		
		initColumna3(true, true);
		setOkIcon((JButton)evt.getSource());
	}

	private void btnCol2ActionPerformed(java.awt.event.ActionEvent evt) {
		total=4;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna1(false, false);
		initColumna3(false, false);
		initColumna4(false, false);
		initColumna5(false, false);
		initColumna6(false, false);
		
		initFila1(false, false);
		initFila2(false, false);
		initFila3(false, false);
		initFila4(false, false);
		
		initColumna2(true, true);
		setOkIcon((JButton)evt.getSource());
	}


	private void btnCol1ActionPerformed(java.awt.event.ActionEvent evt) {
		total=4;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna2(false, false);
		initColumna3(false, false);
		initColumna4(false, false);
		initColumna5(false, false);
		initColumna6(false, false);
		
		initFila1(false, false);
		initFila2(false, false);
		initFila3(false, false);
		initFila4(false, false);
		
		initColumna1(true,true);
		setOkIcon((JButton)evt.getSource());
	}

	private void btnFila1ActionPerformed(java.awt.event.ActionEvent evt) {
		total=6;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
				
		initColumna1(false, false);
		initColumna2(false, false);
		initColumna3(false, false);
		initColumna4(false, false);
		initColumna5(false, false);
		initColumna6(false, false);
		
		initFila2(false, false);
		initFila3(false, false);
		initFila4(false, false);
		
		initFila1(true, true);
		setOkIcon((JButton)evt.getSource());

	}

	private void btnFila2ActionPerformed(java.awt.event.ActionEvent evt) {
		total=6;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna1(false, false);
		initColumna2(false, false);
		initColumna3(false, false);
		initColumna4(false, false);
		initColumna5(false, false);
		initColumna6(false, false);
		
		
		initFila3(false, false);
		initFila4(false, false);
		initFila1(false, false);
		
		initFila2(true, true);
		setOkIcon((JButton)evt.getSource());
	}

	private void btnFila3ActionPerformed(java.awt.event.ActionEvent evt) {
		total=6;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna1(false, false);
		initColumna2(false, false);
		initColumna3(false, false);
		initColumna4(false, false);
		initColumna5(false, false);
		initColumna6(false, false);
		
		
		
		initFila4(false, false);
		initFila1(false, false);
		initFila2(false, false);
		
		initFila3(true, true);
		setOkIcon((JButton)evt.getSource());
		
	}

	private void btnFila4ActionPerformed(java.awt.event.ActionEvent evt) {
		total=6;
		initVariables();
		initBtnColumnas();
		initBtnFilas();
		
		initColumna1(false, false);
		initColumna2(false, false);
		initColumna3(false, false);
		initColumna4(false, false);
		initColumna5(false, false);
		initColumna6(false, false);
		
		initFila1(false, false);
		initFila2(false, false);
		initFila3(false, false);
		
		initFila4(true, true);		
		setOkIcon((JButton)evt.getSource());
		
	}
	
	public void actionBoton(java.awt.event.ActionEvent evt)
	{
		JButton btn=(JButton)evt.getSource();
		if(evt.getActionCommand().equals("ok"))
		{
			btn.setActionCommand("ko");
			cantidadAciertos--;
			setKoIcon(btn);
		}
		else
		{
			btn.setActionCommand("ok");
			cantidadAciertos++;
			setOkIcon(btn);
		}
	}

	private void btn18ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn19ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn111ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn166ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn444ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn455ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn433ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn411ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn422ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn466ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn122ActionPerformed(java.awt.event.ActionEvent evt) {
		 actionBoton(evt);
	}

	private void btn144ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn155ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn311ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn322ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn333ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn344ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn355ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn366ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn133ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn31ActioionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn29Act7ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn26A5ActctionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn23A2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn2n1n18ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn17ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn16ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btn255ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn244ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn233ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn211ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private void btn222ActionPerformed(java.awt.event.ActionEvent evt) {
		actionBoton(evt);
	}

	private boolean isExamenValid() {
		if (!started) {
			Util.mostrarError(lbError,"El examen no ha sido iniciado.",false);
			return false;
		}

		return true;
	}

	public void cargarResultados() {
		resultados.clear();
		Resultado res = new Resultado();
		res.setResEtapa(0l);
		res.setResValor1(getPorcentaje());
		resultados.add(res);
	}
	
	public Double getPorcentaje() {
		Double porcentaje = ((double)cantidadAciertos/ (double)total) * 100;
		Util.redondear(porcentaje);
		return Util.redondear(porcentaje);
	}

	public String getResultado() {
			if (!isAprobed())
				return Examen.RESULTADO_FUERA_DERIVACION;

		return Examen.RESULTADO_DENTRO;
	}

	public boolean isAprobed() {
		
		if(getPorcentaje().intValue()!=100)
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
				Set setResultados = resultadoDetalleExamen.getResultados();
				setResultados.clear();
				for (int i = 0; i < this.resultados.size(); i++) {
					this.resultados.get(i).setResultadoDetalleExamen(
							resultadoDetalleExamen);
					setResultados.add(this.resultados.get(i));
				}

				String resultado = getResultado();

				resultadoDetalleExamen.setRdeResultado(resultado);
				resultadoDetalleExamen.setRdeDetalleResultado("Porcentaje: "+ getPorcentaje());
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setBtn(JToggleButton btn) {
		this.btn = btn;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btn111;
	private javax.swing.JButton btn122;
	private javax.swing.JButton btn133;
	private javax.swing.JButton btn144;
	private javax.swing.JButton btn155;
	private javax.swing.JButton btn166;
	private javax.swing.JButton btn211;
	private javax.swing.JButton btn222;
	private javax.swing.JButton btn233;
	private javax.swing.JButton btn244;
	private javax.swing.JButton btn255;
	private javax.swing.JButton btn266;
	private javax.swing.JButton btn311;
	private javax.swing.JButton btn322;
	private javax.swing.JButton btn333;
	private javax.swing.JButton btn344;
	private javax.swing.JButton btn355;
	private javax.swing.JButton btn366;
	private javax.swing.JButton btn411;
	private javax.swing.JButton btn422;
	private javax.swing.JButton btn433;
	private javax.swing.JButton btn444;
	private javax.swing.JButton btn455;
	private javax.swing.JButton btn466;
	private javax.swing.JButton btnCol1;
	private javax.swing.JButton btnCol2;
	private javax.swing.JButton btnCol3;
	private javax.swing.JButton btnCol4;
	private javax.swing.JButton btnCol5;
	private javax.swing.JButton btnCol6;
	private javax.swing.JButton btnFila1;
	private javax.swing.JButton btnFila2;
	private javax.swing.JButton btnFila3;
	private javax.swing.JButton btnFila4;
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbImagen;
	// End of variables declaration//GEN-END:variables

}