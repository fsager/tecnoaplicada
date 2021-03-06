/*
 * PanelEditarClaseLicencia.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.service.ClaseLicenciaDefinition;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import frontend.components.JOptionPaneTesterGral;

/**
 * 
 * @author __USER__
 */
public class PanelEdicionClaseLicencia extends javax.swing.JPanel {
	private JInternalFrameTesterGral parent;
	private static final Log log = LogFactory
			.getLog(PanelEdicionClaseLicencia.class);
	private ClaseLicenciaDefinition claseLicenciaService = (ClaseLicenciaDefinition) ContextManager
			.getBizObject("claseLicenciaService");

	ClaseLicencia claseLicenciaRecibida = new ClaseLicencia();
	private boolean esEdicionDeClaseExistente;

	/**
	 * Creates new form PanelEditarClaseLicencia Editar clase de licencia
	 * */
	public PanelEdicionClaseLicencia(ClaseLicencia claseLicenciaAEditar,
			JInternalFrameTesterGral parent) {
		this(parent);// Llama al otro constructor

		esEdicionDeClaseExistente = true;
		claseLicenciaRecibida = claseLicenciaAEditar;
		establecerLicenciaAEditar();// Establece cu�l ser� la licencia que
		// aparezca por defecto cuando se abra la
		// pantalla.
		jPanelDatosClaseLicencia.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Editar clase de licencia",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));
		jTextFieldNombreClaseLicencia.setEditable(false);
	}

	/**
	 * Creates new form PanelEditarClaseLicencia Nueva clase de licencia
	 * */
	public PanelEdicionClaseLicencia(JInternalFrameTesterGral parent) {
		initComponents();
		this.parent = parent;
		llenarComboBoxVigenciaPredeterminada();
		esEdicionDeClaseExistente = false;
	}

	private void establecerLicenciaAEditar() {

		try {
			jTextFieldNombreClaseLicencia.setText(claseLicenciaRecibida
					.getCllNombreClase());

			jTextFieldDescripcionCortaClaseLicencia
					.setText(claseLicenciaRecibida.getCllDescripcionCorta());
			jTextAreaDescripcion.setText(claseLicenciaRecibida
					.getCllDescripcion());
			jFormattedTextFieldEdadMaxima.setText(String
					.valueOf(claseLicenciaRecibida.getCllEdadMaxima()));
			jFormattedTextFieldEdadMinima.setText(String
					.valueOf(claseLicenciaRecibida.getCllEdadMinima()));

			/*jftf6.setText(String.valueOf(claseLicenciaRecibida
					.getCllImportex6meses()));
			jftf12.setText(String.valueOf(claseLicenciaRecibida
					.getCllImportex12meses()));

			jftf24.setText(String.valueOf(claseLicenciaRecibida
					.getCllImportex24meses()));

			jftf36.setText(String.valueOf(claseLicenciaRecibida
					.getCllImportex36meses()));

			jftf48.setText(String.valueOf(claseLicenciaRecibida
					.getCllImportex48meses()));

			jftf60.setText(String.valueOf(claseLicenciaRecibida
					.getCllImportex60meses()));*/

			jftf6.setValue(claseLicenciaRecibida.getCllImportex6meses());
			jftf12.setValue(claseLicenciaRecibida.getCllImportex12meses());

			jftf24.setValue(claseLicenciaRecibida.getCllImportex24meses());

			jftf36.setValue(claseLicenciaRecibida.getCllImportex36meses());

			jftf48.setValue(claseLicenciaRecibida.getCllImportex48meses());

			jftf60.setValue(claseLicenciaRecibida.getCllImportex60meses());

			Dominio dominio = new Dominio();
			dominio.setDomTipo("LICENCIA");
			dominio.setDomClave("DURACIONES_PREDETERMINADAS_LICENCIA");
			dominio.setDomCodigo(String.valueOf(claseLicenciaRecibida
					.getCllVigenciaPredeterminada()));
			dominio.setDomDescripcion(String.valueOf(claseLicenciaRecibida
					.getCllVigenciaPredeterminada()));
			dominio.setDomValorMostrar(String.valueOf(claseLicenciaRecibida
					.getCllVigenciaPredeterminada()));

			jComboBoxVigenciaPredeterminada.setSelectedItem(dominio);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private void llenarComboBoxVigenciaPredeterminada() {
		List<Dominio> listaDominios = Util.getDominios(new String(
				"DURACIONES_PREDETERMINADAS_LICENCIA"));
		Util.cargarDominios(jComboBoxVigenciaPredeterminada, new String(
				new String("DURACIONES_PREDETERMINADAS_LICENCIA")), false);
	}

	public boolean validarClaseLicencia() {

		if (jTextFieldNombreClaseLicencia.getText() == null
				|| jTextFieldNombreClaseLicencia.getText().equals("")) {
			Util.mostrarError(jLabelError, Constantes.ERROR_LICENCIA_NOMBRE,
					false);
			return false;
		}

		if (jTextFieldDescripcionCortaClaseLicencia.getText() == null
				|| jTextFieldDescripcionCortaClaseLicencia.getText().equals("")) {
			Util.mostrarError(jLabelError,
					Constantes.ERROR_LICENCIA_DESCRIPCORTA, false);
			return false;
		}

		if (jTextAreaDescripcion.getText() == null
				|| jTextAreaDescripcion.getText().equals("")) {
			Util.mostrarError(jLabelError,
					Constantes.ERROR_LICENCIA_DESCRIPCION, false);
			return false;
		}

		if (jComboBoxVigenciaPredeterminada.getSelectedItem() == null) {
			Util.mostrarError(jLabelError,
					Constantes.ERROR_LICENCIA_VIGENCIAPREDET, false);
			return false;
		}
		if (jFormattedTextFieldEdadMinima.getText() == null
				|| jFormattedTextFieldEdadMinima.getText().equals("")) {
			Util.mostrarError(jLabelError,
					Constantes.ERROR_LICENCIA_EDADMINIMA, false);
			return false;
		}
		if (jFormattedTextFieldEdadMaxima.getText() == null
				|| jFormattedTextFieldEdadMaxima.getText().equals("")) {
			Util.mostrarError(jLabelError,
					Constantes.ERROR_LICENCIA_EDADMAXIMA, false);
			return false;
		}

		return true;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanelDatosClaseLicencia = new javax.swing.JPanel();
		jLabelNombreClaseLicencia = new javax.swing.JLabel();
		jLabelDescripcionCortaClaseLicencia = new javax.swing.JLabel();
		jLabelVigenciaPredeterminadaEnMeses = new javax.swing.JLabel();
		jTextFieldDescripcionCortaClaseLicencia = new javax.swing.JTextField();
		jComboBoxVigenciaPredeterminada = new javax.swing.JComboBox();
		jLabelDescripcion = new javax.swing.JLabel();
		jScrollPaneDescripcion = new javax.swing.JScrollPane();
		jTextAreaDescripcion = new javax.swing.JTextArea();
		jTextFieldNombreClaseLicencia = new javax.swing.JTextField();
		jPanelRequisitos = new javax.swing.JPanel();
		jLabelEdadMinima = new javax.swing.JLabel();
		jLabelEdadMaxima = new javax.swing.JLabel();
		jLabelEdadMinimaA�os = new javax.swing.JLabel();
		jLabelEdadMaximaA�os = new javax.swing.JLabel();
		jFormattedTextFieldEdadMinima = new javax.swing.JFormattedTextField();
		jFormattedTextFieldEdadMaxima = new javax.swing.JFormattedTextField();
		jPanelBotones = new javax.swing.JPanel();
		jButtonCerrar = new javax.swing.JButton();
		jButtonGuardar = new javax.swing.JButton();
		jLabelError = new javax.swing.JLabel();
		jPanelRequisitos1 = new javax.swing.JPanel();
		jLabelEdadMinima1 = new javax.swing.JLabel();
		jftf6 = new javax.swing.JFormattedTextField();
		jLabelEdadMinima2 = new javax.swing.JLabel();
		jftf12 = new javax.swing.JFormattedTextField();
		jLabelEdadMinima3 = new javax.swing.JLabel();
		jftf24 = new javax.swing.JFormattedTextField();
		jLabelEdadMinima4 = new javax.swing.JLabel();
		jftf36 = new javax.swing.JFormattedTextField();
		jLabelEdadMinima5 = new javax.swing.JLabel();
		jftf48 = new javax.swing.JFormattedTextField();
		jLabel60m = new javax.swing.JLabel();
		jftf60 = new javax.swing.JFormattedTextField();

		jPanelDatosClaseLicencia.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Nueva clase de licencia",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jLabelNombreClaseLicencia.setText("Nombre de clase:");

		jLabelDescripcionCortaClaseLicencia.setText("Descripci\u00f3n corta:");

		jLabelVigenciaPredeterminadaEnMeses
				.setText("Vigencia predeterminada (en meses):");

		jTextFieldDescripcionCortaClaseLicencia
				.setMaximumSize(new java.awt.Dimension(406, 22));

		jLabelDescripcion.setText("Descripci\u00f3n:");

		jTextAreaDescripcion.setColumns(20);
		jTextAreaDescripcion.setRows(5);
		jTextAreaDescripcion.setMaximumSize(new java.awt.Dimension(406, 22));
		jScrollPaneDescripcion.setViewportView(jTextAreaDescripcion);

		jTextFieldNombreClaseLicencia.setMaximumSize(new java.awt.Dimension(86,
				22));

		javax.swing.GroupLayout jPanelDatosClaseLicenciaLayout = new javax.swing.GroupLayout(
				jPanelDatosClaseLicencia);
		jPanelDatosClaseLicencia.setLayout(jPanelDatosClaseLicenciaLayout);
		jPanelDatosClaseLicenciaLayout
				.setHorizontalGroup(jPanelDatosClaseLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDatosClaseLicenciaLayout
										.createSequentialGroup()
										.addComponent(
												jLabelVigenciaPredeterminadaEnMeses)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jComboBoxVigenciaPredeterminada,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												60,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(
								jPanelDatosClaseLicenciaLayout
										.createSequentialGroup()
										.addComponent(jLabelDescripcion)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPaneDescripcion,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												421, Short.MAX_VALUE))
						.addGroup(
								jPanelDatosClaseLicenciaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelDatosClaseLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelDescripcionCortaClaseLicencia)
														.addComponent(
																jLabelNombreClaseLicencia))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelDatosClaseLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jTextFieldDescripcionCortaClaseLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																359,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jTextFieldNombreClaseLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																88,
																javax.swing.GroupLayout.PREFERRED_SIZE))));
		jPanelDatosClaseLicenciaLayout
				.setVerticalGroup(jPanelDatosClaseLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDatosClaseLicenciaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelDatosClaseLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelNombreClaseLicencia)
														.addComponent(
																jTextFieldNombreClaseLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelDatosClaseLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelDescripcionCortaClaseLicencia)
														.addComponent(
																jTextFieldDescripcionCortaClaseLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelDatosClaseLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelVigenciaPredeterminadaEnMeses)
														.addComponent(
																jComboBoxVigenciaPredeterminada,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanelDatosClaseLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanelDatosClaseLicenciaLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelDescripcion)
																		.addContainerGap(
																				80,
																				Short.MAX_VALUE))
														.addComponent(
																jScrollPaneDescripcion,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																96,
																Short.MAX_VALUE))));

		jPanelRequisitos.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Requisitos",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jLabelEdadMinima.setText("Edad m\u00ednima requerida:");

		jLabelEdadMaxima.setText("Edad m\u00e1xima permitida:");

		jLabelEdadMinimaA�os.setText("a\u00f1os.");

		jLabelEdadMaximaA�os.setText("a\u00f1os.");

		jFormattedTextFieldEdadMinima
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedTextFieldEdadMinima.setMaximumSize(new java.awt.Dimension(86,
				22));

		jFormattedTextFieldEdadMaxima
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedTextFieldEdadMaxima.setMaximumSize(new java.awt.Dimension(86,
				22));

		javax.swing.GroupLayout jPanelRequisitosLayout = new javax.swing.GroupLayout(
				jPanelRequisitos);
		jPanelRequisitos.setLayout(jPanelRequisitosLayout);
		jPanelRequisitosLayout
				.setHorizontalGroup(jPanelRequisitosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelRequisitosLayout
										.createSequentialGroup()
										.addGroup(
												jPanelRequisitosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelEdadMinima)
														.addComponent(
																jLabelEdadMaxima))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanelRequisitosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jFormattedTextFieldEdadMinima,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jFormattedTextFieldEdadMaxima,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																42,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelRequisitosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelEdadMaximaA�os)
														.addComponent(
																jLabelEdadMinimaA�os))));
		jPanelRequisitosLayout
				.setVerticalGroup(jPanelRequisitosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelRequisitosLayout
										.createSequentialGroup()
										.addGroup(
												jPanelRequisitosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelEdadMinima)
														.addComponent(
																jLabelEdadMinimaA�os)
														.addComponent(
																jFormattedTextFieldEdadMinima,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelRequisitosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelEdadMaximaA�os)
														.addComponent(
																jLabelEdadMaxima)
														.addComponent(
																jFormattedTextFieldEdadMaxima,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		jButtonCerrar.setMnemonic('C');
		jButtonCerrar.setText("Cerrar");
		jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCerrarActionPerformed(evt);
			}
		});

		jButtonGuardar.setMnemonic('G');
		jButtonGuardar.setText("Guardar");
		jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGuardarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(
				jPanelBotones);
		jPanelBotones.setLayout(jPanelBotonesLayout);
		jPanelBotonesLayout
				.setHorizontalGroup(jPanelBotonesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelBotonesLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jButtonGuardar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												75,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButtonCerrar).addGap(6,
												6, 6)));
		jPanelBotonesLayout.setVerticalGroup(jPanelBotonesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanelBotonesLayout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButtonCerrar).addComponent(
										jButtonGuardar)));

		jPanelRequisitos1.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Importes",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jLabelEdadMinima1.setText("6 Meses ($)");

		jftf6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
				new javax.swing.text.NumberFormatter(
						new java.text.DecimalFormat("#0.00"))));
		jftf6.setMaximumSize(new java.awt.Dimension(86, 22));

		jLabelEdadMinima2.setText("12 Meses ($)");

		jftf12
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));
		jftf12.setMaximumSize(new java.awt.Dimension(86, 22));

		jLabelEdadMinima3.setText("24 Meses ($)");

		jftf24
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));
		jftf24.setMaximumSize(new java.awt.Dimension(86, 22));

		jLabelEdadMinima4.setText("36 Meses ($)");

		jftf36
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));
		jftf36.setMaximumSize(new java.awt.Dimension(86, 22));

		jLabelEdadMinima5.setText("48 Meses ($)");

		jftf48
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));
		jftf48.setMaximumSize(new java.awt.Dimension(86, 22));

		jLabel60m.setText("60 Meses ($)");

		jftf60
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));
		jftf60.setMaximumSize(new java.awt.Dimension(86, 22));

		javax.swing.GroupLayout jPanelRequisitos1Layout = new javax.swing.GroupLayout(
				jPanelRequisitos1);
		jPanelRequisitos1.setLayout(jPanelRequisitos1Layout);
		jPanelRequisitos1Layout
				.setHorizontalGroup(jPanelRequisitos1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelRequisitos1Layout
										.createSequentialGroup()
										.addGroup(
												jPanelRequisitos1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelRequisitos1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelEdadMinima1)
																		.addGap(
																				11,
																				11,
																				11)
																		.addComponent(
																				jftf6,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				48,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabelEdadMinima2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jftf12,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				51,
																				Short.MAX_VALUE))
														.addGroup(
																jPanelRequisitos1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelEdadMinima3)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jftf24,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				39,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabelEdadMinima4)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jftf36,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				39,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanelRequisitos1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelEdadMinima5)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jftf48,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				39,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabel60m)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jftf60,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				39,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));

		jPanelRequisitos1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { jftf24, jftf48, jftf6 });

		jPanelRequisitos1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { jftf12, jftf36, jftf60 });

		jPanelRequisitos1Layout
				.setVerticalGroup(jPanelRequisitos1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelRequisitos1Layout
										.createSequentialGroup()
										.addGroup(
												jPanelRequisitos1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelEdadMinima1)
														.addComponent(
																jLabelEdadMinima2)
														.addComponent(
																jftf6,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jftf12,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelRequisitos1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelEdadMinima3)
														.addComponent(
																jftf24,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelEdadMinima4)
														.addComponent(
																jftf36,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelRequisitos1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelEdadMinima5)
														.addComponent(
																jftf48,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel60m)
														.addComponent(
																jftf60,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanelRequisitos1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				7,
																				7,
																				7)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jPanelRequisitos,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jPanelBotones,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																jPanelDatosClaseLicencia,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabelError,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				491,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												jPanelDatosClaseLicencia,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
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
																				jPanelRequisitos,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jPanelBotones,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jPanelRequisitos1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												25,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		if (validarClaseLicencia()) {
			ClaseLicencia nuevaClaseLicencia = new ClaseLicencia();

			if (esEdicionDeClaseExistente == true) {
				nuevaClaseLicencia.setCllId(claseLicenciaRecibida.getCllId());
			}

			nuevaClaseLicencia
					.setCllDescripcion(jTextAreaDescripcion.getText());
			nuevaClaseLicencia.setCllNombreClase(jTextFieldNombreClaseLicencia
					.getText());

			nuevaClaseLicencia
					.setCllDescripcionCorta(jTextFieldDescripcionCortaClaseLicencia
							.getText());
			nuevaClaseLicencia.setCllEdadMaxima(Long
					.valueOf(jFormattedTextFieldEdadMaxima.getText()));
			nuevaClaseLicencia.setCllEdadMinima(Long
					.valueOf(jFormattedTextFieldEdadMinima.getText()));
			nuevaClaseLicencia.setCllVigenciaPredeterminada(Long
					.valueOf(jComboBoxVigenciaPredeterminada.getSelectedItem()
							.toString()));

			/*nuevaClaseLicencia.setCllImportex6meses(Double.valueOf(jftf6
					.getText()));
			nuevaClaseLicencia.setCllImportex12meses(Double.valueOf(jftf12
					.getText()));
			nuevaClaseLicencia.setCllImportex24meses(Double.valueOf(jftf24
					.getText()));
			nuevaClaseLicencia.setCllImportex36meses(Double.valueOf(jftf36
					.getText()));
			nuevaClaseLicencia.setCllImportex48meses(Double.valueOf(jftf48
					.getText()));
			nuevaClaseLicencia.setCllImportex60meses(Double.valueOf(jftf60
					.getText()));*/

			if(jftf6.getValue()!=null)
				nuevaClaseLicencia.setCllImportex6meses(((Number)  jftf6.getValue()).doubleValue());
			if(jftf12.getValue()!=null)
				nuevaClaseLicencia
					.setCllImportex12meses(((Number) jftf12.getValue()).doubleValue());
			if(jftf24.getValue()!=null)
				nuevaClaseLicencia
					.setCllImportex24meses(((Number)  jftf24.getValue()).doubleValue());
			if(jftf36.getValue()!=null)
				nuevaClaseLicencia
					.setCllImportex36meses(((Number)  jftf36.getValue()).doubleValue());
			if(jftf48.getValue()!=null)
				nuevaClaseLicencia
					.setCllImportex48meses(((Number)  jftf48.getValue()).doubleValue());
			if(jftf60.getValue()!=null)
				nuevaClaseLicencia
					.setCllImportex60meses(((Number)  jftf60.getValue()).doubleValue());

			try {
				/*
				 * Se inserta s�lo si no estaba en la base de datos, si no se edita la existente.
				 */
				if (esEdicionDeClaseExistente == true) {
					claseLicenciaService.update(nuevaClaseLicencia);
				} else {
					claseLicenciaService.insert(nuevaClaseLicencia);
				}

				JOptionPaneTesterGral.showInternalMessageDialog(this,
						"Los cambios se guardaron correctamente",
						Constantes.MENSAJE_GUARDADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);
				parent.close();
			} catch (DataIntegrityViolationException ex) {
				JOptionPaneTesterGral.showInternalMessageDialog(this,
						Constantes.ERROR_CLASE_LICENCIA_EXISTE, "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {
		parent.close();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonCerrar;
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JComboBox jComboBoxVigenciaPredeterminada;
	private javax.swing.JFormattedTextField jFormattedTextFieldEdadMaxima;
	private javax.swing.JFormattedTextField jFormattedTextFieldEdadMinima;
	private javax.swing.JLabel jLabel60m;
	private javax.swing.JLabel jLabelDescripcion;
	private javax.swing.JLabel jLabelDescripcionCortaClaseLicencia;
	private javax.swing.JLabel jLabelEdadMaxima;
	private javax.swing.JLabel jLabelEdadMaximaA�os;
	private javax.swing.JLabel jLabelEdadMinima;
	private javax.swing.JLabel jLabelEdadMinima1;
	private javax.swing.JLabel jLabelEdadMinima2;
	private javax.swing.JLabel jLabelEdadMinima3;
	private javax.swing.JLabel jLabelEdadMinima4;
	private javax.swing.JLabel jLabelEdadMinima5;
	private javax.swing.JLabel jLabelEdadMinimaA�os;
	private javax.swing.JLabel jLabelError;
	private javax.swing.JLabel jLabelNombreClaseLicencia;
	private javax.swing.JLabel jLabelVigenciaPredeterminadaEnMeses;
	private javax.swing.JPanel jPanelBotones;
	private javax.swing.JPanel jPanelDatosClaseLicencia;
	private javax.swing.JPanel jPanelRequisitos;
	private javax.swing.JPanel jPanelRequisitos1;
	private javax.swing.JScrollPane jScrollPaneDescripcion;
	private javax.swing.JTextArea jTextAreaDescripcion;
	private javax.swing.JTextField jTextFieldDescripcionCortaClaseLicencia;
	private javax.swing.JTextField jTextFieldNombreClaseLicencia;
	private javax.swing.JFormattedTextField jftf12;
	private javax.swing.JFormattedTextField jftf24;
	private javax.swing.JFormattedTextField jftf36;
	private javax.swing.JFormattedTextField jftf48;
	private javax.swing.JFormattedTextField jftf6;
	private javax.swing.JFormattedTextField jftf60;
	// End of variables declaration//GEN-END:variables

}