/*
 * PanelNuevaClaseLicencia.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.print.PrinterJob;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;
import testerGeneral.domain.Propiedad;
import testerGeneral.service.PropiedadDefinition;
import testerGeneral.service.UsuarioDefinition;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.Antecedente;
import autoimpresor.domain.CarnetLicencias;
import autoimpresor.domain.CarnetLicenciasQR;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;
import autoimpresor.domain.Usuario;
import autoimpresor.frontend.ventanas.GuardarLicenciaConRequerimientosIncompletos;
import autoimpresor.service.AntecedenteDefinition;
import autoimpresor.service.ClaseLicenciaDefinition;
import autoimpresor.service.LicenciaDefinition;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;

/**
 * 
 * @author __USER__
 */
public class PanelNuevaLicenciaDeUsuario extends javax.swing.JPanel {
	private static final Log log = LogFactory
			.getLog(PanelNuevaLicenciaDeUsuario.class);
	private ClaseLicenciaDefinition claseLicenciaService = (ClaseLicenciaDefinition) ContextManager
			.getBizObject("claseLicenciaService");
	private UsuarioDefinition usuarioService = (UsuarioDefinition) ContextManager
			.getBizObject("usuarioService");
	private LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
			.getBizObject("licenciaService");
	private AntecedenteDefinition antecedenteService = (AntecedenteDefinition) ContextManager
			.getBizObject("antecedenteService");
	private PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
	private ClaseLicencia claseLicenciaSeleccionada;

	long edadPersona;
	Licencia licencia = new Licencia();

	private String formatoFecha = ContextManager.getProperty("FORMATO.FECHA");
	private SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
	private Date fechaOtorgamiento;
	private Persona persona;
	private Licencia licenciaRecibida;
	private JInternalFrameTesterGral parent;

	/** Creates new form PanelNuevaClaseLicencia */
	public PanelNuevaLicenciaDeUsuario(Persona persona, Licencia lic,
			JInternalFrameTesterGral parent) {
		String utilizarCaja = ContextManager.getProperty("UTILIZAR_CAJA_SN");
		if(utilizarCaja.equals("S"))
			isCajaEnable=true;
		else
			isCajaEnable=false;
		
		this.persona = persona;
		this.parent = parent;
		this.licenciaRecibida = lic;
		initComponents();
		edadPersona = calcularEdadDePersona(this.persona);
		llenarComboBoxClasesLicencia();
		llenarComboBoxVigenciaPredeterminada();
		llenarCombosBoxExamenes();
		llenarComboBoxTipoTramite();
		llenarPanelAntecedentes();
		llenarComboBoxFirmanteDeLicencia();
		buttonGroupAntecedentes.add(jCheckBoxRegistraDeuda);
		buttonGroupAntecedentes.add(jCheckBoxNoRegistraDeuda);
		jFormattedFieldFechaOtorgamiento.setText(sdf.format(new Date()));
		fechaOtorgamiento = getFechaOtorgamiento();
		calcularFechaVencimiento();
		
		if(isCajaEnable)
			setImporteSegunDuracionyClase();
		else
		{
			jLabeImporte.setVisible(false);
			jftfImporte.setVisible(false);
		}
			
		// Util.mostrarError(jLabelError, null, true);

		if (licenciaRecibida != null) {// La ventana sólo muestra datos de la
			// licencia recibida. Pasa a ser una
			// ventana para ver, no para cargar

			mostrarDatosDeLicencia(licenciaRecibida);
			bloquearEdicionElementosVisuales();
			jButtonGuardar.setVisible(false);
			jButtonGuardar1.setVisible(false);

		} else {
			try {
				Propiedad propiedadRangoHasta = ContextManager
						.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.HASTA");

				Long hasta = new Long(propiedadRangoHasta.getPropValor());

				Long numLicenciaActual = licenciaService.getMaxNumeroLicencia() + 1;

				if (numLicenciaActual.equals(hasta)) {
					bloquearEdicionElementosVisuales();
					JOptionPaneTesterGral.showInternalMessageDialog(
							"No se dispone de más número de licencias.",
							"Número de Licencias", JOptionPane.ERROR_MESSAGE);

				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void bloquearEdicionElementosVisuales() {
		jComboBoxTipoTramite.setEnabled(false);
		jComboBoxClaseLicencia.setEnabled(false);
		jFormattedFieldFechaOtorgamiento.setEnabled(false);
		jComboBoxPeriodoDeVigencia.setEnabled(false);
		jTextAreaObservaciones.setEnabled(false);
		jftfImporte.setEnabled(false);
		jComboBoxExamenTeorico.setEnabled(false);
		jComboBoxExamenPractico.setEnabled(false);
		jComboBoxExamenMedico.setEnabled(false);
		jComboBoxExamenOftalmologico.setEnabled(false);
		jComboBoxExamenPsicofisico.setEnabled(false);
		jComboBoxAntecedentes.setEnabled(false);
		jCheckBoxNoRegistraDeuda.setEnabled(false);
		jCheckBoxRegistraDeuda.setEnabled(false);
		jComboBoxResponsableFirmanteDeLicencia.setEnabled(false);
	}

	private void mostrarDatosDeLicencia(Licencia licencia) {

		try {
			ClaseLicencia clase = new ClaseLicencia();
			clase.setCllNombreClase(licencia.getLicClase());
			List<ClaseLicencia> lstClase = claseLicenciaService.getAll(clase);

			Util.selectDominios(jComboBoxTipoTramite, licencia.getLicTramite());
			jComboBoxClaseLicencia.setSelectedItem(lstClase.get(0));

			Dominio dominioPeriodoVigencia = new Dominio();
			dominioPeriodoVigencia.setDomTipo("LICENCIA");
			dominioPeriodoVigencia
					.setDomClave("DURACIONES_PREDETERMINADAS_LICENCIA");
			dominioPeriodoVigencia.setDomCodigo("-");
			dominioPeriodoVigencia.setDomDescripcion("-");
			dominioPeriodoVigencia.setDomValorMostrar("-");

			jComboBoxPeriodoDeVigencia.addItem(dominioPeriodoVigencia);
			Util.selectDominios(jComboBoxPeriodoDeVigencia, "-");

			jTextAreaObservaciones.setText(licencia.getLicObservaciones());

			Util.selectDominios(jComboBoxExamenTeorico, licencia
					.getLicExamenTeorico());
			Util.selectDominios(jComboBoxExamenPractico, licencia
					.getLicExamenPractico());
			Util.selectDominios(jComboBoxExamenMedico, licencia
					.getLicExamenMedico());
			Util.selectDominios(jComboBoxExamenOftalmologico, licencia
					.getLicExamenOftalmologico());
			Util.selectDominios(jComboBoxExamenPsicofisico, licencia
					.getLicExamenPsicofisico());
			jComboBoxAntecedentes.setSelectedItem(licencia.getAntecedente());

			if (licencia.getLicDeudaSn().equalsIgnoreCase("S")) {
				jCheckBoxRegistraDeuda.setSelected(true);
			}
			if (licencia.getLicDeudaSn().equalsIgnoreCase("N")) {
				jCheckBoxNoRegistraDeuda.setSelected(true);
			}

			if(isCajaEnable)
				jftfImporte.setValue(licencia.getLicImporte());
			
			
			Usuario usu = new Usuario();
			usu.setUsrNombre(licencia.getUsuarioByUsrNombreFirma()
					.getUsrNombre());

			jComboBoxResponsableFirmanteDeLicencia.addItem(usu);
			jComboBoxResponsableFirmanteDeLicencia.setSelectedItem(usu);

			jFormattedFieldFechaOtorgamiento.setText(sdf.format(licencia
					.getLicFechaOtorgada()));
			jLabelFechaVencimiento.setText(sdf.format(licencia
					.getLicFechaVencimiento()));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private void llenarComboBoxVigenciaPredeterminada() {
		List<Dominio> listaDominios = Util.getDominios(new String(
				"DURACIONES_PREDETERMINADAS_LICENCIA"));
		Util.cargarDominios(jComboBoxPeriodoDeVigencia,
				"DURACIONES_PREDETERMINADAS_LICENCIA", false);

		Dominio dominio = new Dominio();
		dominio.setDomTipo("LICENCIA");
		dominio.setDomClave("DURACIONES_PREDETERMINADAS_LICENCIA");
		dominio.setDomCodigo(String.valueOf(claseLicenciaSeleccionada
				.getCllVigenciaPredeterminada()));
		dominio.setDomDescripcion(String.valueOf(claseLicenciaSeleccionada
				.getCllVigenciaPredeterminada()));
		dominio.setDomValorMostrar(String.valueOf(claseLicenciaSeleccionada
				.getCllVigenciaPredeterminada()));

		jComboBoxPeriodoDeVigencia.setSelectedItem(dominio);

		if (validarSiEsMenorA70anos() == false) {// Tiene 70 años o más.
			for (int i = jComboBoxPeriodoDeVigencia.getItemCount() - 1; i >= 0; i--) {

				long numeroPeriodoVigenciaSeleccionado = Long
						.valueOf(jComboBoxPeriodoDeVigencia.getItemAt(i)
								.toString());
				if (numeroPeriodoVigenciaSeleccionado > 12) {
					jComboBoxPeriodoDeVigencia.removeItemAt(i);
				}
			}
		}

	}

	public void setImporteSegunDuracionyClase() {
		Dominio dom = (Dominio) jComboBoxPeriodoDeVigencia.getSelectedItem();
		ClaseLicencia clase = (ClaseLicencia) jComboBoxClaseLicencia
				.getSelectedItem();
		Double importe = getImporteSegunDuracionyClase(dom, clase);
		jftfImporte.setValue(importe);
	}

	public Double getImporteSegunDuracionyClase(Dominio dom, ClaseLicencia clase) {
		if (dom.getDomCodigo().equals("06")) {
			return clase.getCllImportex6meses();
		} else if (dom.getDomCodigo().equals("12")) {
			return clase.getCllImportex12meses();
		} else if (dom.getDomCodigo().equals("24")) {
			return clase.getCllImportex24meses();
		} else if (dom.getDomCodigo().equals("36")) {
			return clase.getCllImportex36meses();
		} else if (dom.getDomCodigo().equals("48")) {
			return clase.getCllImportex48meses();
		} else {
			return clase.getCllImportex60meses();
		}
	}

	private void llenarComboBoxClasesLicencia() {
		try {
			List<ClaseLicencia> listaDeClasesDeLicencia = claseLicenciaService
					.getAll(new ClaseLicencia());
			jComboBoxClaseLicencia.removeAllItems();

			ClaseLicencia claseInsercion = new ClaseLicencia();
			jComboBoxClaseLicencia.addItem(claseInsercion);

			for (int i = 0; i < listaDeClasesDeLicencia.size(); i++) {
				claseInsercion = listaDeClasesDeLicencia.get(i);
				jComboBoxClaseLicencia.addItem(claseInsercion);
			}
			claseLicenciaSeleccionada = (ClaseLicencia) jComboBoxClaseLicencia
					.getSelectedItem();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private void llenarCombosBoxExamenes() {

		boolean nulls = false;

		if (proValidar.equals("S"))
			nulls = true;

		Util.cargarDominios(jComboBoxExamenMedico, new String(new String(
				"EXAMEN_MEDICO_ESTADO")), nulls);
		Util.cargarDominios(jComboBoxExamenOftalmologico, new String(
				new String("EXAMEN_MEDICO_ESTADO")), nulls);
		Util.cargarDominios(jComboBoxExamenPractico, new String(new String(
				"EXAMEN_ESTADO")), nulls);
		Util.cargarDominios(jComboBoxExamenPsicofisico, new String(new String(
				"EXAMEN_MEDICO_ESTADO")), nulls);
		Util.cargarDominios(jComboBoxExamenTeorico, new String(new String(
				"EXAMEN_ESTADO")), nulls);

		if (jComboBoxExamenMedico.getComponentCount() != 0) {
			jComboBoxExamenMedico.setSelectedIndex(0);
		}
		if (jComboBoxExamenOftalmologico.getComponentCount() != 0) {
			jComboBoxExamenOftalmologico.setSelectedIndex(0);
		}
		if (jComboBoxExamenPractico.getComponentCount() != 0) {
			jComboBoxExamenPractico.setSelectedIndex(0);
		}
		if (jComboBoxExamenPsicofisico.getComponentCount() != 0) {
			jComboBoxExamenPsicofisico.setSelectedIndex(0);
		}
		if (jComboBoxExamenTeorico.getComponentCount() != 0) {
			jComboBoxExamenTeorico.setSelectedIndex(0);
		}
	}

	private void llenarComboBoxTipoTramite() {
		List<Dominio> listaDominios = Util.getDominios(new String(
				"TIPO_TRAMITE"));
		Util.cargarDominios(jComboBoxTipoTramite, new String(new String(
				"TIPO_TRAMITE")), true);
		if (jComboBoxTipoTramite.getComponentCount() != 0) {
			jComboBoxTipoTramite.setSelectedIndex(0);
		}
	}

	private void llenarPanelAntecedentes() {
		try {

			List<Antecedente> listaDeAntecedentes = antecedenteService
					.getAll(new Antecedente());
			jComboBoxAntecedentes.removeAllItems();

			if (proValidar.equals("S")) {
				Antecedente antecedenteInsercion = new Antecedente();
				jComboBoxAntecedentes.addItem(antecedenteInsercion);
			}

			for (int i = 0; i < listaDeAntecedentes.size(); i++) {
				Antecedente antecedenteInsercion = listaDeAntecedentes.get(i);
				jComboBoxAntecedentes.addItem(antecedenteInsercion);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void llenarComboBoxFirmanteDeLicencia() {
		try {

			String usr = ContextManager.getProperty("SISTEMA.ULTIMO.FIRMANTE");
			List<Usuario> listaDeUsuarios = usuarioService
					.getAll(new Usuario());
			jComboBoxResponsableFirmanteDeLicencia.removeAllItems();
			Usuario usuarioInsercion = new Usuario();
			jComboBoxResponsableFirmanteDeLicencia.addItem(usuarioInsercion);

			for (int i = 0; i < listaDeUsuarios.size(); i++) {

				usuarioInsercion = listaDeUsuarios.get(i);
				if (usuarioInsercion.getUsrPuedeFirmarSn()
						.equalsIgnoreCase("S")
						&& usuarioInsercion.getUsrHabilitadoSn()
								.equalsIgnoreCase("S")) {
					jComboBoxResponsableFirmanteDeLicencia
							.addItem(usuarioInsercion);
					if (usr.compareTo(usuarioInsercion.getUsrNombre()) == 0)
						jComboBoxResponsableFirmanteDeLicencia
								.setSelectedItem(usuarioInsercion);
				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public JFormattedTextField setFecha() {
		try {
			String mascara = formatoFecha.replaceAll("y", "#");
			mascara = mascara.replaceAll("M", "#");
			mascara = mascara.replaceAll("d", "#");
			JFormattedTextField textField = new javax.swing.JFormattedTextField(
					new MaskFormatter(mascara));
			textField.setFocusLostBehavior(JFormattedTextField.COMMIT);

			return textField;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("deprecation")
	private Date calcularFechaVencimiento() {

		if (fechaOtorgamiento != null) {
			Calendar hoy = Calendar.getInstance();

			hoy.setTime(fechaOtorgamiento);
			hoy.add(Calendar.MONTH, Integer.valueOf(jComboBoxPeriodoDeVigencia
					.getSelectedItem().toString()));

			// Calendar to Date Conversion
			int year = hoy.get(Calendar.YEAR);
			int month = hoy.get(Calendar.MONTH);
			int day = hoy.get(Calendar.DATE);

			Date date = hoy.getTime();

			String formattedDate = sdf.format(date);
			jLabelFechaVencimiento.setText(formattedDate);
			return date;
		} else {
			jLabelFechaVencimiento.setText("");
		}
		return null;
	}

	public Date getFechaOtorgamiento() {
		if (!jFormattedFieldFechaOtorgamiento.getText().isEmpty()) {
			AbstractFormatter formatter = jFormattedFieldFechaOtorgamiento
					.getFormatter();
			if (formatter != null) {
				String text = jFormattedFieldFechaOtorgamiento.getText();

				try {
					formatter.stringToValue(text);

					sdf.setLenient(false);

					Date hoy = new Date();
					Util.redondearFecha(hoy);

					Date fecha = sdf.parse(jFormattedFieldFechaOtorgamiento
							.getText());

					if (fecha.compareTo(hoy) < 0) {
						Util.mostrarError(jLabelError,
								Constantes.ERROR_PER_FECHA_PASADO, false);
						return null;
					}
					Util.mostrarError(jLabelError,
							Constantes.ERROR_PER_FECHA_SINFORMATO, true);
					return fecha;
				} catch (ParseException pe) {
					Util.mostrarError(jLabelError,
							Constantes.ERROR_PER_FECHA_SINFORMATO, false);
				}
			}
		} else {
			Util.mostrarError(jLabelError, "Debe ingresar una fecha válida",
					false);
		}

		return null;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroupAntecedentes = new javax.swing.ButtonGroup();
		jPanelLicencia = new javax.swing.JPanel();
		jLabelTipoTramite = new javax.swing.JLabel();
		jComboBoxTipoTramite = new javax.swing.JComboBox();
		jLabelClaseDeLicencia = new javax.swing.JLabel();
		jComboBoxClaseLicencia = new javax.swing.JComboBox();
		jLabelFechaDeOtorgamiento = new javax.swing.JLabel();
		jLabelPeriodoDeVigencia = new javax.swing.JLabel();
		jComboBoxPeriodoDeVigencia = new javax.swing.JComboBox();
		jLabelPeriodoDeVigencia2 = new javax.swing.JLabel();
		jLabelFechaVencimiento = new javax.swing.JLabel();
		jLabelObservaciones = new javax.swing.JLabel();
		jScrollPaneObservaciones = new javax.swing.JScrollPane();
		jTextAreaObservaciones = new javax.swing.JTextArea();
		jFormattedFieldFechaOtorgamiento = setFecha();
		lbFechaEjemplo2 = new javax.swing.JLabel();
		jLabeImporte = new javax.swing.JLabel();
		jftfImporte = new javax.swing.JFormattedTextField();
		jPanelRequisitosPrevios = new javax.swing.JPanel();
		jLabelTeorico = new javax.swing.JLabel();
		jLabelExamenPractico = new javax.swing.JLabel();
		jLabelMedico = new javax.swing.JLabel();
		jPanelDeuda = new javax.swing.JPanel();
		jCheckBoxNoRegistraDeuda = new javax.swing.JCheckBox();
		jCheckBoxRegistraDeuda = new javax.swing.JCheckBox();
		jLabelAntecedentes = new javax.swing.JLabel();
		jComboBoxAntecedentes = new javax.swing.JComboBox();
		jPanel1 = new javax.swing.JPanel();
		jComboBoxExamenTeorico = new javax.swing.JComboBox();
		jLabelErrorExamenTeorico = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jComboBoxExamenMedico = new javax.swing.JComboBox();
		jLabelErrorExamenMedico = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jComboBoxExamenOftalmologico = new javax.swing.JComboBox();
		jLabelErrorExamenOftalmologico = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jComboBoxExamenPsicofisico = new javax.swing.JComboBox();
		jLabelErrorExamenPsicofisico = new javax.swing.JLabel();
		jLabelExamenOftalmologico = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		jComboBoxExamenPractico = new javax.swing.JComboBox();
		jLabelErrorExamenPractico = new javax.swing.JLabel();
		jLabelExamenPsicoFisico = new javax.swing.JLabel();
		jLabelResponsableFirmanteDeLicencia = new javax.swing.JLabel();
		jComboBoxResponsableFirmanteDeLicencia = new javax.swing.JComboBox();
		jPanel6 = new javax.swing.JPanel();
		jButtonCerrar = new javax.swing.JButton();
		jButtonGuardar = new javax.swing.JButton();
		jLabelError = new javax.swing.JLabel();
		jButtonGuardar1 = new javax.swing.JButton();

		jPanelLicencia.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Licencia",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jLabelTipoTramite.setText("Tipo de tr\u00e1mite:");

		jComboBoxTipoTramite.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2" }));
		jComboBoxTipoTramite
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jComboBoxTipoTramiteActionPerformed(evt);
					}
				});

		jLabelClaseDeLicencia.setText("Clase de licencia:");

		jComboBoxClaseLicencia
				.setModel(new javax.swing.DefaultComboBoxModel(
						new String[] { "A-1: Ciclomotores c/ cilindrada menor a 50cc" }));
		jComboBoxClaseLicencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jComboBoxClaseLicenciaActionPerformed(evt);
					}
				});

		jLabelFechaDeOtorgamiento.setText("Fecha de otorgamiento:");

		jLabelPeriodoDeVigencia.setText("Per\u00edodo de vigencia:");

		jComboBoxPeriodoDeVigencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jComboBoxPeriodoDeVigenciaActionPerformed(evt);
					}
				});

		jLabelPeriodoDeVigencia2.setText("meses. Fecha de Vencimiento:");

		jLabelFechaVencimiento.setFont(new java.awt.Font("Segoe UI", 3, 12));
		jLabelFechaVencimiento.setText("01/01/2011");

		jLabelObservaciones.setText("Observaciones:");

		jTextAreaObservaciones.setColumns(20);
		jTextAreaObservaciones.setRows(5);
		jTextAreaObservaciones.setMaximumSize(new java.awt.Dimension(164, 94));
		jScrollPaneObservaciones.setViewportView(jTextAreaObservaciones);

		jFormattedFieldFechaOtorgamiento.setMaximumSize(new java.awt.Dimension(
				78, 22));
		jFormattedFieldFechaOtorgamiento
				.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusLost(java.awt.event.FocusEvent evt) {
						jFormattedFieldFechaOtorgamientoFocusLost(evt);
					}
				});

		lbFechaEjemplo2.setFont(new java.awt.Font("Segoe UI", 0, 11));
		lbFechaEjemplo2.setText("DD-MM-AAAA");

		jLabeImporte.setText("Importe:");

		jftfImporte.setMaximumSize(new java.awt.Dimension(86, 22));

		javax.swing.GroupLayout jPanelLicenciaLayout = new javax.swing.GroupLayout(
				jPanelLicencia);
		jPanelLicencia.setLayout(jPanelLicenciaLayout);
		jPanelLicenciaLayout
				.setHorizontalGroup(jPanelLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelLicenciaLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelFechaDeOtorgamiento)
														.addComponent(
																jLabelClaseDeLicencia)
														.addComponent(
																jLabelTipoTramite)
														.addComponent(
																jLabelPeriodoDeVigencia)
														.addComponent(
																jLabeImporte)
														.addComponent(
																jLabelObservaciones))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPaneObservaciones,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																307,
																Short.MAX_VALUE)
														.addGroup(
																jPanelLicenciaLayout
																		.createSequentialGroup()
																		.addComponent(
																				jComboBoxPeriodoDeVigencia,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				60,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jLabelPeriodoDeVigencia2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				10,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabelFechaVencimiento))
														.addComponent(
																jComboBoxTipoTramite,
																0, 307,
																Short.MAX_VALUE)
														.addComponent(
																jComboBoxClaseLicencia,
																0, 307,
																Short.MAX_VALUE)
														.addGroup(
																jPanelLicenciaLayout
																		.createSequentialGroup()
																		.addComponent(
																				jFormattedFieldFechaOtorgamiento,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				78,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				lbFechaEjemplo2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				77,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jftfImporte,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																60,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		jPanelLicenciaLayout
				.setVerticalGroup(jPanelLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelLicenciaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelTipoTramite)
														.addComponent(
																jComboBoxTipoTramite,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelClaseDeLicencia)
														.addComponent(
																jComboBoxClaseLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelFechaDeOtorgamiento)
														.addComponent(
																jFormattedFieldFechaOtorgamiento,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lbFechaEjemplo2))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelPeriodoDeVigencia)
														.addComponent(
																jComboBoxPeriodoDeVigencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelFechaVencimiento)
														.addComponent(
																jLabelPeriodoDeVigencia2))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jftfImporte,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabeImporte))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												14, Short.MAX_VALUE)
										.addGroup(
												jPanelLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelObservaciones)
														.addComponent(
																jScrollPaneObservaciones,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																39,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		jPanelRequisitosPrevios.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Requisitos previos",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jLabelTeorico.setText("Examen te\u00f3rico:");

		jLabelExamenPractico.setText("Examen pr\u00e1ctico:");

		jLabelMedico.setText("Examen m\u00e9dico:");

		jCheckBoxNoRegistraDeuda.setSelected(true);
		jCheckBoxNoRegistraDeuda.setText("NO registra deuda.");

		jCheckBoxRegistraDeuda.setText("S\u00cd registra deuda. ");

		jLabelAntecedentes.setText("Antecedentes:");

		jComboBoxAntecedentes.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanelDeudaLayout = new javax.swing.GroupLayout(
				jPanelDeuda);
		jPanelDeuda.setLayout(jPanelDeudaLayout);
		jPanelDeudaLayout
				.setHorizontalGroup(jPanelDeudaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDeudaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelDeudaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelDeudaLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelAntecedentes)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				69,
																				Short.MAX_VALUE)
																		.addComponent(
																				jComboBoxAntecedentes,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				294,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanelDeudaLayout
																		.createSequentialGroup()
																		.addGap(
																				65,
																				65,
																				65)
																		.addComponent(
																				jCheckBoxNoRegistraDeuda)
																		.addGap(
																				26,
																				26,
																				26)
																		.addComponent(
																				jCheckBoxRegistraDeuda)))
										.addContainerGap()));
		jPanelDeudaLayout
				.setVerticalGroup(jPanelDeudaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDeudaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelDeudaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelAntecedentes)
														.addComponent(
																jComboBoxAntecedentes,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanelDeudaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jCheckBoxRegistraDeuda)
														.addComponent(
																jCheckBoxNoRegistraDeuda))));

		jComboBoxExamenTeorico.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(
												jComboBoxExamenTeorico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												154,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelErrorExamenTeorico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												91,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jLabelErrorExamenTeorico,
								javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jComboBoxExamenTeorico,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		jComboBoxExamenMedico.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(
												jComboBoxExamenMedico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												154,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelErrorExamenMedico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												91,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jLabelErrorExamenMedico,
								javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jComboBoxExamenMedico,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		jComboBoxExamenOftalmologico
				.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
						"Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(
												jComboBoxExamenOftalmologico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												154,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelErrorExamenOftalmologico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												91,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jLabelErrorExamenOftalmologico,
								javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jComboBoxExamenOftalmologico,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		jComboBoxExamenPsicofisico
				.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
						"Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addComponent(
												jComboBoxExamenPsicofisico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												154,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelErrorExamenPsicofisico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												91,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jLabelErrorExamenPsicofisico,
								javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jComboBoxExamenPsicofisico,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		jLabelExamenOftalmologico.setText("Examen oftalmol\u00f3gico:");

		jComboBoxExamenPractico.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addComponent(
												jComboBoxExamenPractico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												154,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelErrorExamenPractico,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												91,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel5Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(jLabelErrorExamenPractico,
								javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jComboBoxExamenPractico,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		jLabelExamenPsicoFisico.setText("Examen psicof\u00edsico:");

		javax.swing.GroupLayout jPanelRequisitosPreviosLayout = new javax.swing.GroupLayout(
				jPanelRequisitosPrevios);
		jPanelRequisitosPrevios.setLayout(jPanelRequisitosPreviosLayout);
		jPanelRequisitosPreviosLayout
				.setHorizontalGroup(jPanelRequisitosPreviosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelRequisitosPreviosLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jPanelDeuda,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(
								jPanelRequisitosPreviosLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelRequisitosPreviosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelTeorico)
														.addComponent(
																jLabelExamenPractico)
														.addComponent(
																jLabelMedico)
														.addComponent(
																jLabelExamenOftalmologico)
														.addComponent(
																jLabelExamenPsicoFisico))
										.addGap(23, 23, 23)
										.addGroup(
												jPanelRequisitosPreviosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(43, Short.MAX_VALUE)));
		jPanelRequisitosPreviosLayout
				.setVerticalGroup(jPanelRequisitosPreviosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelRequisitosPreviosLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanelRequisitosPreviosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jLabelTeorico)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelRequisitosPreviosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelExamenPractico,
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel5,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelRequisitosPreviosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelMedico,
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel2,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelRequisitosPreviosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelExamenOftalmologico))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelRequisitosPreviosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelExamenPsicoFisico))
										.addGap(18, 18, 18)
										.addComponent(
												jPanelDeuda,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		jLabelResponsableFirmanteDeLicencia
				.setText("Reponsable (firmante de la licencia):");

		jComboBoxResponsableFirmanteDeLicencia
				.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
						"Item 1", "Item 2", "Item 3", "Item 4" }));
		jComboBoxResponsableFirmanteDeLicencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jComboBoxResponsableFirmanteDeLicenciaActionPerformed(evt);
					}
				});

		jButtonCerrar.setText("Cerrar");
		jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCerrarActionPerformed(evt);
			}
		});

		jButtonGuardar.setText("Guardar");
		jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGuardarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButtonGuardar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButtonCerrar)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel6Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jButtonCerrar).addComponent(
								jButtonGuardar)));

		jButtonGuardar1.setForeground(new java.awt.Color(0, 0, 255));
		jButtonGuardar1.setText("Imprimir plantilla de conformidad");
		jButtonGuardar1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGuardar1ActionPerformed(evt);
			}
		});

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
										.addComponent(
												jLabelResponsableFirmanteDeLicencia)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jComboBoxResponsableFirmanteDeLicencia,
												0, 254, Short.MAX_VALUE)
										.addContainerGap())
						.addComponent(jPanelLicencia,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jPanelRequisitosPrevios,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap(
										303, Short.MAX_VALUE).addComponent(
										jPanel6,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jLabelError,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												462, Short.MAX_VALUE))
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jButtonGuardar1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												450, Short.MAX_VALUE)
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												jPanelLicencia,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelRequisitosPrevios,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelResponsableFirmanteDeLicencia)
														.addComponent(
																jComboBoxResponsableFirmanteDeLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jButtonGuardar1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												19, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jLabelError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												jPanel6,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButtonGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {

		if (validarLicencia()) {
			if (calcularFechaVencimiento() != null) {
				uiToLicencia();
				imprimirPlantillaConformida(licencia);
			}
		}
	}

	public static void imprimirPlantillaConformida(Licencia licencia) {

		try {

			String nombreMunicipio = ContextManager
					.getProperty("SISTEMA.NOMBRE.MUNICIPIO");
			String codigoMunicipio = ContextManager
					.getProperty("SISTEMA.CODIGO.MUNICIPIO");
			byte[] escudoMunicipio = ContextManager.getPropertyObj(
					"SISTEMA.FOTO.MUNICIPIO").getPropBlob();

			CarnetLicenciasQR carnet = new CarnetLicenciasQR(licencia,
					nombreMunicipio, codigoMunicipio, escudoMunicipio,null);
			List<CarnetLicencias> carnets = new ArrayList();
			carnets.add(carnet);

			String srcString = "reportes/planillaConformidad.jasper";
			File f = new File(srcString);

			HashMap parameterMap = new HashMap();

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					carnets);

			JasperPrint print = JasperFillManager.fillReport(f
					.getAbsolutePath(), parameterMap, ds);
			PrinterJob job = PrinterJob.getPrinterJob();
			/*PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			printRequestAttributeSet.add(new PageRanges(1,2));*/

			JRPrintServiceExporter exporter;
			exporter = new JRPrintServiceExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(
					JRPrintServiceExporterParameter.PRINT_SERVICE, job
							.getPrintService());
			exporter
					.setParameter(
							JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
							job.getPrintService().getAttributes());
			//JRPrintServiceExporterParameter.
			//exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
			exporter.setParameter(
					JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
					Boolean.FALSE);
			exporter.setParameter(
					JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
					Boolean.TRUE);
			exporter.exportReport();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		if (validarLicencia()) {
			if (calcularFechaVencimiento() != null) {
				guardarLicencia();
			}
		}
	}

	public void uiToLicencia() {
		licencia.setLicObservaciones(jTextAreaObservaciones.getText());
		licencia.setLicClase(claseLicenciaSeleccionada.getCllNombreClase());
		licencia.setLicTramite(jComboBoxTipoTramite.getSelectedItem()
				.toString());
		if(isCajaEnable)
			licencia.setLicImporte(((Number)jftfImporte.getValue()).doubleValue());
			
		licencia.setLicFechaOtorgada(getFechaOtorgamiento());
		licencia.setLicFechaVencimiento(calcularFechaVencimiento());
		if (ContextManager.getProperty(
				"SISTEMA.MUNICIPIO.ES_CENTRO_IMPRESOR_S_N").equals("S") && !isCajaEnable) {
			licencia.setLicEstado("H");
		} else
			licencia.setLicEstado("P");

		Usuario usuarioSeleccionado = (Usuario) jComboBoxResponsableFirmanteDeLicencia
				.getSelectedItem();
		licencia.setUsuarioByUsrNombreFirma(usuarioSeleccionado);
		licencia.setLicExamenTeorico(jComboBoxExamenTeorico.getSelectedItem()
				.toString());
		licencia.setLicExamenPractico(jComboBoxExamenPractico.getSelectedItem()
				.toString());
		Antecedente antecedente = (Antecedente) jComboBoxAntecedentes
				.getSelectedItem();
		licencia.setAntecedente(antecedente);
		if (jCheckBoxRegistraDeuda.isSelected()) {
			licencia.setLicDeudaSn("S");
		} else {
			licencia.setLicDeudaSn("N");
		}
		licencia.setUsuarioByUsrNombreResponsable(usuarioSeleccionado);
		licencia.setLicFechaReal(new Date());
		licencia.setLicRequisitosSn("S");

		licencia.setUsuarioByUsrConfeccionoLicencia(usuarioSeleccionado);
		licencia.setPersona(persona);
		licencia.setLicExamenMedico(jComboBoxExamenMedico.getSelectedItem()
				.toString());
		licencia.setLicExamenOftalmologico(jComboBoxExamenOftalmologico
				.getSelectedItem().toString());
		licencia.setLicExamenPsicofisico(jComboBoxExamenPsicofisico
				.getSelectedItem().toString());

	}

	private void guardarLicencia() {
		String listaRequerimientosIncompletos = "";

		if (proValidar.equals("S"))
			listaRequerimientosIncompletos = validarNuevaLicencia();

		uiToLicencia();

		try {
			Propiedad propiedadRangoHasta = ContextManager
					.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.HASTA");
			Propiedad propiedadRangoFaltan = ContextManager
					.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.AVISAR.CUANDO.FALTAN");

			Long hasta = new Long(propiedadRangoHasta.getPropValor());
			Long faltan = new Long(propiedadRangoFaltan.getPropValor());

			Long numLicenciaActual = licenciaService.getMaxNumeroLicencia() + 1;

			if (numLicenciaActual.equals(hasta)) {
				JOptionPaneTesterGral.showInternalMessageDialog(
						"No se dispone de más número de licencias.",
						"Número de Licencias", JOptionPane.ERROR_MESSAGE);
				return;
			}

			licencia.setLicNumero(numLicenciaActual);
			Propiedad propiedadCodigoMunicipio = propiedadService
					.get("SISTEMA.CODIGO.MUNICIPIO");
			String valorPropiedadCodigoMunicipio = propiedadCodigoMunicipio
					.getPropValor();
			licencia.setLicCodLicencia(valorPropiedadCodigoMunicipio + " - "
					+ licencia.getLicNumero().toString());

			if (listaRequerimientosIncompletos.isEmpty()) {
				/*Si no hay requerimientos incompletos, se procede a guardar la licencia.*/
				try {
					licenciaService.insert(licencia);
					Propiedad pro = ContextManager
							.getPropertyObj("SISTEMA.ULTIMO.FIRMANTE");
					pro.setPropValor(licencia.getUsuarioByUsrNombreFirma()
							.getUsrNombre());
					pro.setPropBlob(new byte[1]);
					propiedadService.update(pro);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

				if (!numLicenciaActual.equals(hasta)) {
					Long diff = hasta - numLicenciaActual - 1;
					if (diff <= faltan) {
						JOptionPaneTesterGral.showInternalMessageDialog(
								"Quedan " + diff + " números de licencias",
								"Número de Licencias",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

				JOptionPaneTesterGral.showInternalMessageDialog(
						"Los cambios se guardaron correctamente",
						Constantes.MENSAJE_GUARDADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);
				parent.close();
			} else {
				GuardarLicenciaConRequerimientosIncompletos guardarLicRqsIncompletos = new GuardarLicenciaConRequerimientosIncompletos(
						PanelNuevaLicenciaDeUsuario.class, new Usuario(),
						listaRequerimientosIncompletos, licencia,
						(JInternalFrameTesterGral) parent);
				Util.agregarIframe(guardarLicRqsIncompletos);
				guardarLicRqsIncompletos.doModal(this.getRootPane()
						.getRootPane());
				guardarLicRqsIncompletos.setVisible(true);

			}
			
			if (isCajaEnable && licencia.getLicId()!=null)
			{
				
				List<Licencia> licencias = new ArrayList<Licencia>();
				licencias.add(licencia);

				autoimpresor.util.Util
						.printReportRecibo(new HashMap(), licencias);
			}

			if (ContextManager.getProperty(
					"SISTEMA.MUNICIPIO.ES_CENTRO_IMPRESOR_S_N").equals("S") && !isCajaEnable && licencia.getLicId()!=null) {
				
				//IMPREME LICENCIAS
				
							String nombreMunicipio = ContextManager
							.getProperty("SISTEMA.NOMBRE.MUNICIPIO");
					String codigoMunicipio = ContextManager
							.getProperty("SISTEMA.CODIGO.MUNICIPIO");
					byte[] escudoMunicipio = ContextManager.getPropertyObj(
							"SISTEMA.FOTO.MUNICIPIO").getPropBlob();
		
					CarnetLicencias carnet = new CarnetLicenciasQR(licenciaService
							.get(licencia.getLicId()), nombreMunicipio,
							codigoMunicipio, escudoMunicipio,null);
					List<CarnetLicencias> carnets = new ArrayList<CarnetLicencias>();
					carnets.add(carnet);
					
					autoimpresor.util.Util
							.printReportCarnet(new HashMap(), carnets);
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public boolean validarTipoClaseLicencia() {
		if (jComboBoxTipoTramite.getSelectedIndex() == 0) {
			Util.mostrarError(jLabelError,
					"Debe seleccionar un tipo de trámite.", false);
			return false;
		}

		if (jComboBoxClaseLicencia.getSelectedIndex() == 0) {
			Util.mostrarError(jLabelError,
					"Debe seleccionar una clase de licencia.", false);
			return false;
		}

		return true;
	}

	public boolean validarLicencia() {

		if (!validarTipoClaseLicencia()) {
			return false;
		}

		if (getFechaOtorgamiento() == null) {
			return false;
		}
		
		if(isCajaEnable)
		{
			if(jftfImporte.getValue()==null)
			{
				Util.mostrarError(jLabelError,
						"Debe indicar un importe.", false);
				return false;
			}
			
			Dominio dom = (Dominio) jComboBoxPeriodoDeVigencia.getSelectedItem();
			ClaseLicencia clase = (ClaseLicencia) jComboBoxClaseLicencia.getSelectedItem();
			Double importeCorrespondiente=getImporteSegunDuracionyClase(dom, clase);
			Double importeActual=((Number)jftfImporte.getValue()).doubleValue();
			
			if(importeCorrespondiente.compareTo(importeActual)!=0 && jTextAreaObservaciones.getText().equals(""))
			{
				Util.mostrarError(jLabelError,
						"Se ha modificado el importe. Explique el motivo en las observaciones.", false);
				return false;
			}
		}

		if (proValidar.equals("S")) {

			if (jComboBoxExamenTeorico.getSelectedIndex() == 0) {
				Util.mostrarError(jLabelError,
						"Debe seleccionar un examen teórico.", false);
				return false;
			}

			if (jComboBoxExamenPractico.getSelectedIndex() == 0) {
				Util.mostrarError(jLabelError,
						"Debe seleccionar un examen práctico.", false);
				return false;
			}

			if (jComboBoxExamenMedico.getSelectedIndex() == 0) {
				Util.mostrarError(jLabelError,
						"Debe seleccionar un examen médico.", false);
				return false;
			}
			if (jComboBoxExamenOftalmologico.getSelectedIndex() == 0) {
				Util.mostrarError(jLabelError,
						"Debe seleccionar un examen oftalmológico.", false);
				return false;
			}

			if (jComboBoxExamenPsicofisico.getSelectedIndex() == 0) {
				Util.mostrarError(jLabelError,
						"Debe seleccionar un examen psicofísico.", false);
				return false;
			}

			if (jComboBoxAntecedentes.getSelectedIndex() == 0) {
				Util.mostrarError(jLabelError,
						"Debe seleccionar un antecedente.", false);
				return false;
			}
		}

		if (jComboBoxResponsableFirmanteDeLicencia.getSelectedIndex() == 0) {
			Util.mostrarError(jLabelError,
					"Debe seleccionar un firmante de la licencia.", false);
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @return true si la persona cumple con la edad mínima y máxima
	 *         especificadas en la clase de licencia seleccionada.
	 */
	private boolean validarCumplimientoEdad() {

		long edadMinima = claseLicenciaSeleccionada.getCllEdadMinima();
		long edadMaxima = claseLicenciaSeleccionada.getCllEdadMaxima();

		if (edadPersona > edadMaxima || edadPersona < edadMinima) {
			return false;
		} else {
			return true;
		}
	}

	private boolean validarSiEsMenorA70anos() {
		if (edadPersona < 70) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validarExamenTeorico() {
		if (jComboBoxExamenTeorico.getSelectedItem().toString()
				.equalsIgnoreCase("Aprobado")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validarClasesLicenciaAprendizaje() {
		String[] clasePermitidas = { "A-1", "A-2", "A-3", "B-1", "F" };
		String clase = claseLicenciaSeleccionada.getCllNombreClase();
		for (int i = 0; i < clasePermitidas.length; i++) {
			if (clase.equals(clasePermitidas[i]))
				return true;
		}
		return false;
	}

	private boolean validarClasesB1() {
		String clase = claseLicenciaSeleccionada.getCllNombreClase();
		if (clase.equals("B-1")) {
			long numeroPeriodoVigenciaSeleccionado = Long
					.valueOf(jComboBoxPeriodoDeVigencia.getSelectedItem()
							.toString());
			if (numeroPeriodoVigenciaSeleccionado > 12)
				return false;
		}

		return true;
	}

	private boolean tieneLicencia() {

		try {
			Licencia lic = new Licencia();
			//String clase=claseLicenciaSeleccionada.getCllNombreClase();
			lic.setPersona(persona);
			//lic.setLicClase(clase);
			List list = licenciaService.getAll(lic);

			if (list.size() > 0)
				return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return false;
	}

	private boolean validarClasesDependientesE1() {
		String[] claseDependientesE1 = { "B-2", "C", "D-1", "D-2", "D-3",
				"E-1", "E-2", "G" };
		String clase = claseLicenciaSeleccionada.getCllNombreClase();
		for (int i = 0; i < claseDependientesE1.length; i++) {
			if (clase.equals(claseDependientesE1[i])) {
				if (!tieneLicenciaE1PorUnAño())
					return false;
				else
					return true;
			}
		}

		return true;
	}

	private boolean tieneLicenciaE1PorUnAño() {
		try {
			String[] claseDependientesE1 = { "B-1", "B-2", "C", "D-1", "D-2",
					"D-3", "E-1", "E-2", "G" };
			String clase = claseLicenciaSeleccionada.getCllNombreClase();
			for (int i = 0; i < claseDependientesE1.length; i++) {
				Licencia lic = new Licencia();
				lic.setLicClase(claseDependientesE1[i]);
				lic.setPersona(persona);
				List<Licencia> list = licenciaService.getAll(lic);

				if (list.size() > 0) {
					for (int j = 0; j < list.size(); j++) {
						Licencia licAux = list.get(j);
						int anos = Util.calcularEdad(licAux
								.getLicFechaOtorgada());
						if (clase.equalsIgnoreCase(licAux.getLicClase())
								|| anos >= 1) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return false;
	}

	private boolean validarExamenPractico() {
		if (jComboBoxExamenPractico.getSelectedItem().toString()
				.equalsIgnoreCase("Aprobado")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validarExamenMedico() {
		if (jComboBoxExamenMedico.getSelectedItem().toString()
				.equalsIgnoreCase("Apto")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validarExamenOftalmologico() {
		if (jComboBoxExamenOftalmologico.getSelectedItem().toString()
				.equalsIgnoreCase("Apto")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validarExamenPsicofisico() {
		if (jComboBoxExamenPsicofisico.getSelectedItem().toString()
				.equalsIgnoreCase("Apto")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validarAntecedentes() {
		Antecedente antecedente = (Antecedente) jComboBoxAntecedentes
				.getSelectedItem();
		if (antecedente.getAntApruebaSn().equalsIgnoreCase("S")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Valida que se cumplan todos los requisitos para poder insertar una nueva
	 * licencia.
	 * 
	 * @return un String con la lista de requisitos que no se cumplen. Si el
	 *         String está vacío quiere decir que se cumple todos los
	 *         requisitos.
	 */
	public String validarNuevaLicencia() {
		String listaRequerimientosIncompletos = new String();

		if (!validarCumplimientoEdad()) {
			listaRequerimientosIncompletos = listaRequerimientosIncompletos
					.concat("- La edad de la persona no permite otorgarle el tipo de licencia seleccionado.\n");
		}
		if (!validarAntecedentes()) {
			listaRequerimientosIncompletos = listaRequerimientosIncompletos
					.concat("- Antecedentes no permitidos.\n");
		}

		if (!validarExamenMedico()) {
			listaRequerimientosIncompletos = listaRequerimientosIncompletos
					.concat("- Examen médico requerido.\n");

		}

		if (!validarExamenOftalmologico()) {
			listaRequerimientosIncompletos = listaRequerimientosIncompletos
					.concat("- Examen oftalmológico requerido.\n");

		}

		if (!validarExamenPsicofisico()) {
			listaRequerimientosIncompletos = listaRequerimientosIncompletos
					.concat("- Examen psicofísico requerido.\n");

		}

		if (!validarClasesDependientesE1()) {
			listaRequerimientosIncompletos = listaRequerimientosIncompletos
					.concat("- Debe tener experiencia con una Clase B1 de un año como mínimo.\n");
		}

		// Comprobaciones para licencias Aprendizaje y Primer licencia.
		if (jComboBoxTipoTramite.getSelectedItem().toString().equalsIgnoreCase(
				"Aprendizaje")
				|| jComboBoxTipoTramite.getSelectedItem().toString()
						.equalsIgnoreCase("Primer licencia")) {

			if (!validarExamenPractico()) {
				listaRequerimientosIncompletos = listaRequerimientosIncompletos
						.concat("- Examen práctico requerido.\n");
			}

			if (!validarExamenTeorico()) {
				listaRequerimientosIncompletos = listaRequerimientosIncompletos
						.concat("- Examen teórico requerido.\n");
			}

			if (!validarClasesLicenciaAprendizaje()) {
				listaRequerimientosIncompletos = listaRequerimientosIncompletos
						.concat("- Clase de licencia no permitida para tipo trámite.\n");
			}

			if (!validarClasesB1()) {
				listaRequerimientosIncompletos = listaRequerimientosIncompletos
						.concat("- Período de vigencia no permitido. Máximo permitido 1 año.\n");
			}

		}

		// Presenta licencia anterior
		if (jComboBoxTipoTramite.getSelectedItem().toString().equalsIgnoreCase(
				"Presenta licencia anterior")) {

		}

		// Comprobaciones para licencias Duplicado, Modificada y Renovación.
		if (jComboBoxTipoTramite.getSelectedItem().toString().equalsIgnoreCase(
				"Duplicado")
				|| jComboBoxTipoTramite.getSelectedItem().toString()
						.equalsIgnoreCase("Modificada")
				|| jComboBoxTipoTramite.getSelectedItem().toString()
						.equalsIgnoreCase("Renovación")) {
			if (!tieneLicencia()) {
				//listaRequerimientosIncompletos = listaRequerimientosIncompletos.concat("- Debe tener por lo menos una licencia clase: "+claseLicenciaSeleccionada.getCllNombreClase()+" emitida para realizar el tipo trámite.\n");
				listaRequerimientosIncompletos = listaRequerimientosIncompletos
						.concat("- Debe tener por lo menos una licencia emitida para realizar el tipo trámite.\n");
			}

		}

		return listaRequerimientosIncompletos;
	}

	/**
	 * 
	 * @param persona
	 *            de la que se desea calcular la edad.
	 * @return edad de la persona y -1 en caso de error.
	 */
	private int calcularEdadDePersona(Persona persona) {
		Date nacimiento = persona.getPerFechaNacimiento();
		if (nacimiento != null) {
			int anos = Util.calcularEdad(nacimiento);
			if (anos >= 0) {
				return anos;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	private void jComboBoxResponsableFirmanteDeLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
	}

	private void jFormattedFieldFechaOtorgamientoFocusLost(
			java.awt.event.FocusEvent evt) {
		fechaOtorgamiento = getFechaOtorgamiento();
		calcularFechaVencimiento();
	}

	private void jComboBoxPeriodoDeVigenciaActionPerformed(
			java.awt.event.ActionEvent evt) {

		if (licenciaRecibida == null) {
			fechaOtorgamiento = getFechaOtorgamiento();
			calcularFechaVencimiento();
		}
		
		setImporteSegunDuracionyClase();
	}

	private void jComboBoxClaseLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
		try {
			claseLicenciaSeleccionada = (ClaseLicencia) jComboBoxClaseLicencia
					.getSelectedItem();

			Dominio dominio = new Dominio();
			dominio.setDomTipo("LICENCIA");
			dominio.setDomClave("DURACIONES_PREDETERMINADAS_LICENCIA");
			dominio.setDomCodigo(String.valueOf(claseLicenciaSeleccionada
					.getCllVigenciaPredeterminada()));
			dominio.setDomDescripcion(String.valueOf(claseLicenciaSeleccionada
					.getCllVigenciaPredeterminada()));
			dominio.setDomValorMostrar(String.valueOf(claseLicenciaSeleccionada
					.getCllVigenciaPredeterminada()));

			jComboBoxPeriodoDeVigencia.setSelectedItem(dominio);
			
			setImporteSegunDuracionyClase();

		} catch (NullPointerException e) {

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void jComboBoxTipoTramiteActionPerformed(
			java.awt.event.ActionEvent evt) {
		Dominio dom = (Dominio) jComboBoxTipoTramite.getSelectedItem();

		/*if(dom.getDomCodigo().equals("Presenta licencia anterior"))
		{
			jTextAreaObservaciones.setText("Presenta licencia anterior")
		}*/
	}

	private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {
		parent.close();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroupAntecedentes;
	private javax.swing.JButton jButtonCerrar;
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JButton jButtonGuardar1;
	private javax.swing.JCheckBox jCheckBoxNoRegistraDeuda;
	private javax.swing.JCheckBox jCheckBoxRegistraDeuda;
	private javax.swing.JComboBox jComboBoxAntecedentes;
	private javax.swing.JComboBox jComboBoxClaseLicencia;
	private javax.swing.JComboBox jComboBoxExamenMedico;
	private javax.swing.JComboBox jComboBoxExamenOftalmologico;
	private javax.swing.JComboBox jComboBoxExamenPractico;
	private javax.swing.JComboBox jComboBoxExamenPsicofisico;
	private javax.swing.JComboBox jComboBoxExamenTeorico;
	private javax.swing.JComboBox jComboBoxPeriodoDeVigencia;
	private javax.swing.JComboBox jComboBoxResponsableFirmanteDeLicencia;
	private javax.swing.JComboBox jComboBoxTipoTramite;
	private javax.swing.JFormattedTextField jFormattedFieldFechaOtorgamiento;
	private javax.swing.JLabel jLabeImporte;
	private javax.swing.JLabel jLabelAntecedentes;
	private javax.swing.JLabel jLabelClaseDeLicencia;
	private javax.swing.JLabel jLabelError;
	private javax.swing.JLabel jLabelErrorExamenMedico;
	private javax.swing.JLabel jLabelErrorExamenOftalmologico;
	private javax.swing.JLabel jLabelErrorExamenPractico;
	private javax.swing.JLabel jLabelErrorExamenPsicofisico;
	private javax.swing.JLabel jLabelErrorExamenTeorico;
	private javax.swing.JLabel jLabelExamenOftalmologico;
	private javax.swing.JLabel jLabelExamenPractico;
	private javax.swing.JLabel jLabelExamenPsicoFisico;
	private javax.swing.JLabel jLabelFechaDeOtorgamiento;
	private javax.swing.JLabel jLabelFechaVencimiento;
	private javax.swing.JLabel jLabelMedico;
	private javax.swing.JLabel jLabelObservaciones;
	private javax.swing.JLabel jLabelPeriodoDeVigencia;
	private javax.swing.JLabel jLabelPeriodoDeVigencia2;
	private javax.swing.JLabel jLabelResponsableFirmanteDeLicencia;
	private javax.swing.JLabel jLabelTeorico;
	private javax.swing.JLabel jLabelTipoTramite;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanelDeuda;
	private javax.swing.JPanel jPanelLicencia;
	private javax.swing.JPanel jPanelRequisitosPrevios;
	private javax.swing.JScrollPane jScrollPaneObservaciones;
	private javax.swing.JTextArea jTextAreaObservaciones;
	private javax.swing.JFormattedTextField jftfImporte;
	private javax.swing.JLabel lbFechaEjemplo2;
	// End of variables declaration//GEN-END:variables

	private String proValidar = ContextManager
			.getProperty("SISTEMA.VALIDAR_REQUISITOS_SN");
	private boolean isCajaEnable;
}