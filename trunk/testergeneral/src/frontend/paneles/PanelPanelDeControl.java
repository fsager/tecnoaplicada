/*
 * PanelPanelDeControl.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.OrigenFotosEnum;
import testerGeneral.domain.Propiedad;
import testerGeneral.persistence.GestorExportarDB;
import testerGeneral.service.ExamenDetalleDefinition;
import testerGeneral.service.PropiedadDefinition;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;
import frontend.ventanas.VentanaExaminar;

/**
 * 
 * @author __USER__
 */
@SuppressWarnings("serial")
public class PanelPanelDeControl extends javax.swing.JPanel implements Runnable {

	private String rutaDestinoArchivoSQL;
	private static final Log log = LogFactory.getLog(PanelPanelDeControl.class);
	private PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
	private ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
			.getBizObject("examenDetalleService");

	/** Creates new form PanelPanelDeControl */
	public PanelPanelDeControl() {
		initComponents();
		setColor();
		cargarValoresDePropiedades();
		jCheckBoxMostrarResultadosAExaminado.setVisible(false);
	}

	private void cargarValoresDePropiedades() {
		cargarValoresPanelFotos();
		cargarValoresPanelActualizaciones();
		cargarValoresPanelCompletitudDatosDeUsuario();
		cargarValoresPanelDocumento();
		cargarValoresPanelSeguridad();
		cargarValoresPanelImagenDeFondo();
		cargarValoresPanelBackup();

		String parametros = ContextManager
				.getProperty("PARAMETROS.CONFIGURACION");
		if (parametros.equals("ARGENTINA")) {
			radioArgentina.setSelected(true);
		} else if (parametros.equals("PERU")) {
			radioPeru.setSelected(true);
		}

	}

	public void run() {
		GestorExportarDB gestor = new GestorExportarDB();
		GestorExportarDB.setRutaDestinoBackup(rutaDestinoArchivoSQL);
		gestor.run();
	}

	private void cargarValoresPanelFotos() {

		try {

			Propiedad propiedadTamanoFotoRedimensionada = propiedadService
					.get("SISTEMA.IMAGENES.PORCENTAJE.REDUCCION");
			Propiedad propiedadDispositivoOrigenFoto = propiedadService
					.get("SISTEMA.ORIGEN.FOTOS");
			Propiedad propiedadRutaOrigenFoto = propiedadService
					.get("DIRECTORIO.IMAGENES");

			String valorPropiedadTamanoFotoRedimensionada = propiedadTamanoFotoRedimensionada
					.getPropValor();
			String valorPropiedadDispositivoOrigenFoto = propiedadDispositivoOrigenFoto
					.getPropValor();
			String valorPropiedadRutaOrigenFoto = propiedadRutaOrigenFoto
					.getPropValor();

			if (valorPropiedadDispositivoOrigenFoto
					.equals(OrigenFotosEnum.DISCO.getValue())) {
				jRadioButtonObtenerFotosDesdeDisco.setSelected(true);
				jTextFieldRutaOrigenFotos.setEnabled(true);
				jButtonExaminarRutaOrigenFotos.setEnabled(true);

			} else {
				jRadioButtonObtenerFotosDesdeCamaraWeb.setSelected(true);
				jTextFieldRutaOrigenFotos.setEnabled(false);
				jButtonExaminarRutaOrigenFotos.setEnabled(false);
			}
			jSpinnerTamanoFotos.setValue(Integer
					.valueOf(valorPropiedadTamanoFotoRedimensionada));
			jTextFieldRutaOrigenFotos.setText(valorPropiedadRutaOrigenFoto);

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	private void cargarValoresPanelActualizaciones() {

		try {
			Propiedad propiedadActualizacionAlIniciarPrograma = propiedadService
					.get("SISTEMA.ACTUALIZACION.AUTOMATICA.INICIO");

			String valorPropiedadActualizacionAlIniciarPrograma = propiedadActualizacionAlIniciarPrograma
					.getPropValor();

			if (valorPropiedadActualizacionAlIniciarPrograma.equals("S")) {
				jRadioButtonBuscarActualizacionesSi.setSelected(true);

			} else {
				jRadioButtonBuscarActualizacionesNo.setSelected(true);

			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	private void cargarValoresPanelSeguridad() {
		try {
			Propiedad propiedadGuardarLogDeEventos = propiedadService
					.get("SISTEMA.GUARDAR.LOG.EVENTOS");
			Propiedad propiedadDiasAGuardarLogDeEventos = propiedadService
					.get("SISTEMA.CONSERVAR.DATOS.LOG.EVENTOS.XDIAS");
			Propiedad propiedadMostrarResultdosAExaminado = propiedadService
					.get("EXAMEN.RESULTADOS.EXAMADO");

			String valorPropiedadGuardarLogDeEventos = propiedadGuardarLogDeEventos
					.getPropValor();
			String valorPropiedadDiasAGuardarLogDeEventos = propiedadDiasAGuardarLogDeEventos
					.getPropValor();
			String valorPropiedadMostrarResultdosAExaminadoString = propiedadMostrarResultdosAExaminado
					.getPropValor();

			if (valorPropiedadGuardarLogDeEventos.equals("S")) {
				jCheckBoxGuardarLogDeActividades.setSelected(true);
				jSpinnerGuardarLogXDias.setEnabled(true);
			} else {
				jCheckBoxGuardarLogDeActividades.setSelected(false);
				jSpinnerGuardarLogXDias.setEnabled(false);
			}

			if (valorPropiedadMostrarResultdosAExaminadoString.equals("S")) {
				jCheckBoxMostrarResultadosAExaminado.setSelected(true);
			} else {
				jCheckBoxMostrarResultadosAExaminado.setSelected(false);
			}

			jSpinnerGuardarLogXDias.setValue(Integer
					.valueOf(valorPropiedadDiasAGuardarLogDeEventos));

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	private void cargarValoresPanelDocumento() {
		try {
			Propiedad propiedadPermitirSoloNumeros = propiedadService
					.get("PERSONA.DNI.PERMITIR.SOLO.NUMEROS");
			Propiedad propiedadCantidadCaracteresFijada = propiedadService
					.get("PERSONA.DNI.CANTIDAD.CARACTERES.FIJA");
			Propiedad propiedadFijarCantidadCaracteresSiONo = propiedadService
					.get("PERSONA.DNI.FIJAR.CANTIDAD.CARACTERES");

			String valorPropiedadPermiirSoloNumeros = propiedadPermitirSoloNumeros
					.getPropValor();
			String valorPropiedadFijarCantidadCaracteres = propiedadFijarCantidadCaracteresSiONo
					.getPropValor();
			String valorPropiedadCantidadCaracteresFijada = propiedadCantidadCaracteresFijada
					.getPropValor();

			if (valorPropiedadPermiirSoloNumeros.equals("S")) {
				jCheckBoxDocumentoPermitirSoloNumeros.setSelected(true);
			} else {
				jCheckBoxDocumentoPermitirSoloNumeros.setSelected(false);
			}

			if (valorPropiedadFijarCantidadCaracteres.equals("S")) {
				jCheckBoxDocumentoCantidadCaracteresFija.setSelected(true);
				jSpinnerDocumentoCantidadCaracteresFija.setEnabled(true);
			} else {
				jCheckBoxDocumentoCantidadCaracteresFija.setSelected(false);
				jSpinnerDocumentoCantidadCaracteresFija.setEnabled(false);
			}
			jSpinnerDocumentoCantidadCaracteresFija.setValue(Integer
					.valueOf(valorPropiedadCantidadCaracteresFijada));

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	private void cargarValoresPanelCompletitudDatosDeUsuario() {

		try {
			Propiedad propiedadFotoRequerida = propiedadService
					.get("PERSONA.FOTO.REQUERIDA");
			Propiedad propiedadFirmaRequerida = propiedadService
					.get("PERSONA.FIRMA.REQUERIDA");

			String valorPropiedadFotoRequerida = propiedadFotoRequerida
					.getPropValor();

			String valorPropiedadFirmaRequerida = propiedadFirmaRequerida
					.getPropValor();

			if (valorPropiedadFotoRequerida.equals("S")) {
				jCheckBoxExigirFotografia.setSelected(true);

			} else {
				jCheckBoxExigirFotografia.setSelected(false);

			}

			if (valorPropiedadFirmaRequerida.equals("S")) {
				jCheckBoxExigirFirmaDigital.setSelected(true);

			} else {
				jCheckBoxExigirFirmaDigital.setSelected(false);

			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	private void cargarValoresPanelImagenDeFondo() {

		try {

			Propiedad propiedadImagenFondoAplicacionP = propiedadService
					.get("SISTEMA.IMAGEN.PRIMARIA");
//
//			panelColor.setBackground(new Color(Integer
//					.valueOf(propiedadImagenFondoAplicacionP.getPropValor())));

			Propiedad propiedadImagenFondoAplicacion = propiedadService
					.get("SISTEMA.IMAGEN.SECUNDARIA");

			String valorPropiedadImagenFondoAplicacion = propiedadImagenFondoAplicacion
					.getPropValor();
			/*jTextFieldRutaOrigenFondoMonitorSecundario
					.setText(valorPropiedadImagenFondoAplicacion);*/

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void cargarValoresPanelBackup() {

		try {
			Propiedad propiedadActivarBackupAutomaticoCadaXDias = propiedadService
					.get("SISTEMA.BACKUP.AUTOMATICO.ACTIVADO");
			Propiedad propiedadCantidadDiasBackupAutomatico = propiedadService
					.get("SISTEMA.BACKUP.AUTOMATICO.CADAXDIAS");
			Propiedad propiedadRutaBackupPrimario = propiedadService
					.get("DIRECTORIO.BACKUP.PRIMARIO");
			Propiedad propiedadRutaBackupSecundario = propiedadService
					.get("DIRECTORIO.BACKUP.SECUNDARIO");
			Propiedad propiedadHacerBackupSecundarioSiONo = propiedadService
					.get("SISTEMA.BACKUP.SECUNDARIO.SI-NO");

			String valorPropiedadActivarBackupAutomaticoCadaXDias = propiedadActivarBackupAutomaticoCadaXDias
					.getPropValor();

			String valorPropiedadCantidadDiasBackupAutomatico = propiedadCantidadDiasBackupAutomatico
					.getPropValor();

			String valorPropiedadRutaBackupPrimario = propiedadRutaBackupPrimario
					.getPropValor();

			String valorPropiedadRutaBackupSecundario = propiedadRutaBackupSecundario
					.getPropValor();

			String valorPropiedadHacerBackupSecundarioSiONo = propiedadHacerBackupSecundarioSiONo
					.getPropValor();

			if (valorPropiedadActivarBackupAutomaticoCadaXDias.equals("S")) {
				jCheckBoxBackupAutomaticoCadaXDias.setSelected(true);
				jSpinnerBackupAutomaticoCadaXDias.setEnabled(true);
				jButtonExaminarRutaBackupPrincipal.setEnabled(true);
				jButtonExaminarRutaBackupSecundario.setEnabled(true);
				jTextFieldRutaUbicacionBackupPrincipal.setEnabled(true);
				jTextFieldRutaUbicacionBackupSecundario.setEnabled(true);
				jCheckBoxHacerBackupSecundario.setEnabled(true);

			} else {
				jCheckBoxBackupAutomaticoCadaXDias.setSelected(false);
				jSpinnerBackupAutomaticoCadaXDias.setEnabled(false);
				jButtonExaminarRutaBackupPrincipal.setEnabled(false);
				jButtonExaminarRutaBackupSecundario.setEnabled(false);
				jTextFieldRutaUbicacionBackupPrincipal.setEnabled(false);
				jTextFieldRutaUbicacionBackupSecundario.setEnabled(false);
				jCheckBoxHacerBackupSecundario.setEnabled(false);

			}

			jSpinnerBackupAutomaticoCadaXDias.setValue(Integer
					.valueOf(valorPropiedadCantidadDiasBackupAutomatico));

			if (valorPropiedadHacerBackupSecundarioSiONo.equals("S")) {
				jCheckBoxHacerBackupSecundario.setSelected(true);
			} else {
				jCheckBoxHacerBackupSecundario.setSelected(false);
				jTextFieldRutaUbicacionBackupSecundario.setEnabled(false);
				jButtonExaminarRutaBackupSecundario.setEnabled(false);
			}

			jTextFieldRutaUbicacionBackupPrincipal
					.setText(valorPropiedadRutaBackupPrimario);
			jTextFieldRutaUbicacionBackupSecundario
					.setText(valorPropiedadRutaBackupSecundario);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void guardarValoresPanelFotos() {

		Propiedad propiedadTamanoFotoRedimensionada = new Propiedad();
		propiedadTamanoFotoRedimensionada
				.setPropClave("SISTEMA.IMAGENES.PORCENTAJE.REDUCCION");
		Propiedad propiedadDispositivoOrigenFoto = new Propiedad();
		propiedadDispositivoOrigenFoto.setPropClave("SISTEMA.ORIGEN.FOTOS");
		Propiedad propiedadRutaOrigenFoto = new Propiedad();
		propiedadRutaOrigenFoto.setPropClave("DIRECTORIO.IMAGENES");

		propiedadTamanoFotoRedimensionada.setPropValor(String
				.valueOf(jSpinnerTamanoFotos.getValue()));

		if (jRadioButtonObtenerFotosDesdeDisco.isSelected()) {
			propiedadDispositivoOrigenFoto.setPropValor(OrigenFotosEnum.DISCO
					.getValue());
		}
		if (jRadioButtonObtenerFotosDesdeCamaraWeb.isSelected()) {
			propiedadDispositivoOrigenFoto.setPropValor(OrigenFotosEnum.WEBCAM
					.getValue());
		}
		propiedadRutaOrigenFoto.setPropValor(jTextFieldRutaOrigenFotos
				.getText());

		try {
			propiedadService.update(propiedadDispositivoOrigenFoto);
			propiedadService.update(propiedadRutaOrigenFoto);
			propiedadService.update(propiedadTamanoFotoRedimensionada);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void guardarValoresPanelActualizaciones() {

		Propiedad propiedadActualizacionAlIniciarPrograma = new Propiedad();
		propiedadActualizacionAlIniciarPrograma
				.setPropClave("SISTEMA.ACTUALIZACION.AUTOMATICA.INICIO");

		if (jRadioButtonBuscarActualizacionesSi.isSelected()) {

			propiedadActualizacionAlIniciarPrograma.setPropValor("S");

		} else {
			propiedadActualizacionAlIniciarPrograma.setPropValor("N");
		}

		try {
			propiedadService.update(propiedadActualizacionAlIniciarPrograma);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void guardarValoresPanelSeguridad() {

		Propiedad propiedadGuardarLogDeEventos = new Propiedad();
		propiedadGuardarLogDeEventos
				.setPropClave("SISTEMA.GUARDAR.LOG.EVENTOS");

		Propiedad propiedadDiasAGuardarLogDeEventos = new Propiedad();
		propiedadDiasAGuardarLogDeEventos
				.setPropClave("SISTEMA.CONSERVAR.DATOS.LOG.EVENTOS.XDIAS");

		Propiedad propiedadMostrarResultadosAExaminado = new Propiedad();
		propiedadMostrarResultadosAExaminado
				.setPropClave("EXAMEN.RESULTADOS.EXAMADO");

		if (jCheckBoxGuardarLogDeActividades.isSelected()) {
			propiedadGuardarLogDeEventos.setPropValor("S");

		} else {
			propiedadGuardarLogDeEventos.setPropValor("N");
		}

		if (jCheckBoxMostrarResultadosAExaminado.isSelected()) {
			propiedadMostrarResultadosAExaminado.setPropValor("S");
		} else {
			propiedadMostrarResultadosAExaminado.setPropValor("N");
		}

		propiedadDiasAGuardarLogDeEventos.setPropValor(String
				.valueOf(jSpinnerGuardarLogXDias.getValue()));

		try {
			propiedadService.update(propiedadGuardarLogDeEventos);
			propiedadService.update(propiedadDiasAGuardarLogDeEventos);
			propiedadService.update(propiedadMostrarResultadosAExaminado);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void guardarValoresPanelDocumento() {

		Propiedad propiedadPermitirSoloNumeros = new Propiedad();
		propiedadPermitirSoloNumeros
				.setPropClave("PERSONA.DNI.PERMITIR.SOLO.NUMEROS");

		Propiedad propiedadCantidadCaracteresFijada = new Propiedad();
		propiedadCantidadCaracteresFijada
				.setPropClave("PERSONA.DNI.CANTIDAD.CARACTERES.FIJA");

		Propiedad propiedadFijarCantidadCaracteresSiONo = new Propiedad();
		propiedadFijarCantidadCaracteresSiONo
				.setPropClave("PERSONA.DNI.FIJAR.CANTIDAD.CARACTERES");

		if (jCheckBoxDocumentoCantidadCaracteresFija.isSelected()) {
			propiedadFijarCantidadCaracteresSiONo.setPropValor("S");

		} else {
			propiedadFijarCantidadCaracteresSiONo.setPropValor("N");
		}
		if (jCheckBoxDocumentoPermitirSoloNumeros.isSelected()) {
			propiedadPermitirSoloNumeros.setPropValor("S");
		} else {
			propiedadPermitirSoloNumeros.setPropValor("N");
		}

		propiedadCantidadCaracteresFijada.setPropValor(String
				.valueOf(jSpinnerDocumentoCantidadCaracteresFija.getValue()));

		try {
			propiedadService.update(propiedadCantidadCaracteresFijada);
			propiedadService.update(propiedadFijarCantidadCaracteresSiONo);
			propiedadService.update(propiedadPermitirSoloNumeros);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void guardarValoresPanelCompletitudDatosDeUsuario() {

		Propiedad propiedadFotoRequerida = new Propiedad();
		Propiedad propiedadFirmaDigitalRequerida = new Propiedad();
		propiedadFotoRequerida.setPropClave("PERSONA.FOTO.REQUERIDA");
		propiedadFirmaDigitalRequerida.setPropClave("PERSONA.FIRMA.REQUERIDA");

		if (jCheckBoxExigirFirmaDigital.isSelected()) {
			propiedadFirmaDigitalRequerida.setPropValor("S");

		} else {
			propiedadFirmaDigitalRequerida.setPropValor("N");
		}
		if (jCheckBoxExigirFotografia.isSelected()) {
			propiedadFotoRequerida.setPropValor("S");
		} else {
			propiedadFotoRequerida.setPropValor("N");
		}

		try {
			propiedadService.update(propiedadFotoRequerida);
			propiedadService.update(propiedadFirmaDigitalRequerida);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void guardarValoresPanelBackup() {
		Propiedad propiedadActivarBackupAutomaticoCadaXDias = new Propiedad();
		propiedadActivarBackupAutomaticoCadaXDias
				.setPropClave("SISTEMA.BACKUP.AUTOMATICO.ACTIVADO");

		Propiedad propiedadCantidadDiasBackupAutomatico = new Propiedad();
		propiedadCantidadDiasBackupAutomatico
				.setPropClave("SISTEMA.BACKUP.AUTOMATICO.CADAXDIAS");

		Propiedad propiedadRutaBackupPrimario = new Propiedad();
		propiedadRutaBackupPrimario.setPropClave("DIRECTORIO.BACKUP.PRIMARIO");

		Propiedad propiedadRutaBackupSecundario = new Propiedad();
		propiedadRutaBackupSecundario
				.setPropClave("DIRECTORIO.BACKUP.SECUNDARIO");

		Propiedad propiedadHacerBackupSecundarioSiONo = new Propiedad();
		propiedadHacerBackupSecundarioSiONo
				.setPropClave("SISTEMA.BACKUP.SECUNDARIO.SI-NO");

		if (jCheckBoxBackupAutomaticoCadaXDias.isSelected()) {
			propiedadActivarBackupAutomaticoCadaXDias.setPropValor("S");
		} else {
			propiedadActivarBackupAutomaticoCadaXDias.setPropValor("N");
		}

		if (jCheckBoxHacerBackupSecundario.isSelected()) {
			propiedadHacerBackupSecundarioSiONo.setPropValor("S");
		} else {
			propiedadHacerBackupSecundarioSiONo.setPropValor("N");
		}

		propiedadCantidadDiasBackupAutomatico.setPropValor(String
				.valueOf(jSpinnerBackupAutomaticoCadaXDias.getValue()));
		propiedadRutaBackupPrimario
				.setPropValor(jTextFieldRutaUbicacionBackupPrincipal.getText());
		propiedadRutaBackupSecundario
				.setPropValor(jTextFieldRutaUbicacionBackupSecundario.getText());

		try {
			propiedadService.update(propiedadActivarBackupAutomaticoCadaXDias);
			propiedadService.update(propiedadCantidadDiasBackupAutomatico);
			propiedadService.update(propiedadRutaBackupPrimario);
			propiedadService.update(propiedadRutaBackupSecundario);
			propiedadService.update(propiedadHacerBackupSecundarioSiONo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void guardarValoresPanelImagenDeFondo() {
		/*Propiedad propiedadImagenFondoAplicacionP = new Propiedad();
		propiedadImagenFondoAplicacionP.setPropClave("SISTEMA.IMAGEN.PRIMARIA");
		propiedadImagenFondoAplicacionP.setPropValor(""
				+ panelColor.getBackground().getRGB());

		Propiedad propiedadImagenFondoAplicacion = new Propiedad();
		propiedadImagenFondoAplicacion
				.setPropClave("SISTEMA.IMAGEN.SECUNDARIA");

		propiedadImagenFondoAplicacion
				.setPropValor(jTextFieldRutaOrigenFondoMonitorSecundario
						.getText());

		ContextManager.updatePropiedad(propiedadImagenFondoAplicacionP);
		Util.frameContenedor.getContentPane().setBackground(
				panelColor.getBackground());
		Util.dp.repaint();

		try {

			byte[] bytes = testerGeneral.persistence.impl.Util
					.getBytesFromFile(jTextFieldRutaOrigenFondoMonitorSecundario
							.getText());

			propiedadImagenFondoAplicacion.setPropBlob(bytes);

			ContextManager.updatePropiedad(propiedadImagenFondoAplicacion);

			if (Util.frameSecundario != null) {
				VentanasUtilTesterGral.setFondo((JFrame) Util.frameSecundario,
						Util.dpSecundario, Util.lbSecundario,
						propiedadImagenFondoAplicacion);
				Util.frameSecundario.getContentPane().setBackground(
						panelColor.getBackground());
				Util.frameSecundario.getContentPane().setBackground(
						panelColor.getBackground());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroupOrigenFotos = new javax.swing.ButtonGroup();
		buttonGroupActualizacionAlInicio = new javax.swing.ButtonGroup();
		groupParametros = new javax.swing.ButtonGroup();
		jPanelFotos = new javax.swing.JPanel();
		jPanelOrigenDeFotos = new javax.swing.JPanel();
		jRadioButtonObtenerFotosDesdeDisco = new javax.swing.JRadioButton();
		jRadioButtonObtenerFotosDesdeCamaraWeb = new javax.swing.JRadioButton();
		jLabelRutaOrigenFotos = new javax.swing.JLabel();
		jTextFieldRutaOrigenFotos = new javax.swing.JTextField();
		jButtonExaminarRutaOrigenFotos = new javax.swing.JButton();
		jSpinnerTamanoFotos = new javax.swing.JSpinner();
		jLabelTamanoFotos2 = new javax.swing.JLabel();
		jLabelMostrarFotos = new javax.swing.JLabel();
		jPanelSeguridad = new javax.swing.JPanel();
		jCheckBoxGuardarLogDeActividades = new javax.swing.JCheckBox();
		jLabelGuardarLogXDias = new javax.swing.JLabel();
		jSpinnerGuardarLogXDias = new javax.swing.JSpinner();
		jLabelGuardarLogXDias2 = new javax.swing.JLabel();
		jCheckBoxMostrarResultadosAExaminado = new javax.swing.JCheckBox();
		jPanelDocumento = new javax.swing.JPanel();
		jCheckBoxDocumentoPermitirSoloNumeros = new javax.swing.JCheckBox();
		jSpinnerDocumentoCantidadCaracteresFija = new javax.swing.JSpinner();
		jCheckBoxDocumentoCantidadCaracteresFija = new javax.swing.JCheckBox();
		jPanelBuscarActualizaciones = new javax.swing.JPanel();
		jButtonBuscarActualizaciones = new javax.swing.JButton();
		jLabelBuscarActualizacionesAlInicio = new javax.swing.JLabel();
		jRadioButtonBuscarActualizacionesSi = new javax.swing.JRadioButton();
		jRadioButtonBuscarActualizacionesNo = new javax.swing.JRadioButton();
		jPanelCompletitudDatosUsuario = new javax.swing.JPanel();
		jCheckBoxExigirFotografia = new javax.swing.JCheckBox();
		jCheckBoxExigirFirmaDigital = new javax.swing.JCheckBox();
		jPanelBackup = new javax.swing.JPanel();
		jTextFieldRutaUbicacionBackupPrincipal = new javax.swing.JTextField();
		jButtonExaminarRutaBackupPrincipal = new javax.swing.JButton();
		jCheckBoxHacerBackupSecundario = new javax.swing.JCheckBox();
		jTextFieldRutaUbicacionBackupSecundario = new javax.swing.JTextField();
		jButtonExaminarRutaBackupSecundario = new javax.swing.JButton();
		jLabelUbicacionBackupPrincipal = new javax.swing.JLabel();
		jCheckBoxBackupAutomaticoCadaXDias = new javax.swing.JCheckBox();
		jSpinnerBackupAutomaticoCadaXDias = new javax.swing.JSpinner();
		jLabelBackupAutomaticoCadaXDias2 = new javax.swing.JLabel();
		jButtonExportarBaseDeDatos = new javax.swing.JButton();
		jPanelBotones = new javax.swing.JPanel();
		jButtonCancelar = new javax.swing.JButton();
		jButtonGuardar = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		radioArgentina = new javax.swing.JRadioButton();
		radioPeru = new javax.swing.JRadioButton();

		jPanelFotos.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Fotos",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jPanelOrigenDeFotos.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Origen de fotos"));

		buttonGroupOrigenFotos.add(jRadioButtonObtenerFotosDesdeDisco);
		jRadioButtonObtenerFotosDesdeDisco
				.setText("Obtener fotos desde c\u00e1mara de fotos");
		jRadioButtonObtenerFotosDesdeDisco
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonObtenerFotosDesdeDiscoActionPerformed(evt);
					}
				});

		buttonGroupOrigenFotos.add(jRadioButtonObtenerFotosDesdeCamaraWeb);
		jRadioButtonObtenerFotosDesdeCamaraWeb
				.setText("Obtener fotos desde c\u00e1mara Web");
		jRadioButtonObtenerFotosDesdeCamaraWeb
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonObtenerFotosDesdeCamaraWebActionPerformed(evt);
					}
				});

		jLabelRutaOrigenFotos.setText("Buscar fotos en:");

		jTextFieldRutaOrigenFotos.setEditable(false);

		jButtonExaminarRutaOrigenFotos.setText("Examinar");
		jButtonExaminarRutaOrigenFotos
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExaminarRutaOrigenFotosActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanelOrigenDeFotosLayout = new javax.swing.GroupLayout(
				jPanelOrigenDeFotos);
		jPanelOrigenDeFotos.setLayout(jPanelOrigenDeFotosLayout);
		jPanelOrigenDeFotosLayout
				.setHorizontalGroup(jPanelOrigenDeFotosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelOrigenDeFotosLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabelRutaOrigenFotos)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jTextFieldRutaOrigenFotos,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												169, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jButtonExaminarRutaOrigenFotos))
						.addGroup(
								jPanelOrigenDeFotosLayout
										.createSequentialGroup()
										.addGroup(
												jPanelOrigenDeFotosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jRadioButtonObtenerFotosDesdeDisco)
														.addComponent(
																jRadioButtonObtenerFotosDesdeCamaraWeb))
										.addContainerGap(144, Short.MAX_VALUE)));
		jPanelOrigenDeFotosLayout
				.setVerticalGroup(jPanelOrigenDeFotosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelOrigenDeFotosLayout
										.createSequentialGroup()
										.addComponent(
												jRadioButtonObtenerFotosDesdeCamaraWeb)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jRadioButtonObtenerFotosDesdeDisco)
										.addGap(5, 5, 5)
										.addGroup(
												jPanelOrigenDeFotosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonExaminarRutaOrigenFotos)
														.addComponent(
																jLabelRutaOrigenFotos)
														.addComponent(
																jTextFieldRutaOrigenFotos,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		jSpinnerTamanoFotos.setFont(new java.awt.Font("Segoe UI", 0, 12));
		jSpinnerTamanoFotos.setModel(new javax.swing.SpinnerNumberModel(25, 1,
				365, 1));
		jSpinnerTamanoFotos
				.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
					public void mouseWheelMoved(
							java.awt.event.MouseWheelEvent evt) {
						jSpinnerTamanoFotosMouseWheelMoved(evt);
					}
				});

		jLabelTamanoFotos2.setText("% de su tama\u00f1o original.");

		jLabelMostrarFotos.setText("Mostar fotos al");

		javax.swing.GroupLayout jPanelFotosLayout = new javax.swing.GroupLayout(
				jPanelFotos);
		jPanelFotos.setLayout(jPanelFotosLayout);
		jPanelFotosLayout
				.setHorizontalGroup(jPanelFotosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelFotosLayout
										.createSequentialGroup()
										.addGroup(
												jPanelFotosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelFotosLayout
																		.createSequentialGroup()
																		.addGap(
																				12,
																				12,
																				12)
																		.addComponent(
																				jLabelMostrarFotos)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jSpinnerTamanoFotos,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				47,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabelTamanoFotos2))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanelFotosLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jPanelOrigenDeFotos,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanelFotosLayout
				.setVerticalGroup(jPanelFotosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelFotosLayout
										.createSequentialGroup()
										.addGroup(
												jPanelFotosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelMostrarFotos)
														.addComponent(
																jSpinnerTamanoFotos,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelTamanoFotos2))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelOrigenDeFotos,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		jPanelSeguridad.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Seguridad",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jCheckBoxGuardarLogDeActividades
				.setText("Guardar un Log de las actividades del sistema");
		jCheckBoxGuardarLogDeActividades
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxGuardarLogDeActividadesActionPerformed(evt);
					}
				});

		jLabelGuardarLogXDias.setText("Conservar los datos por");

		jSpinnerGuardarLogXDias.setFont(new java.awt.Font("Segoe UI", 0, 12));
		jSpinnerGuardarLogXDias.setModel(new javax.swing.SpinnerNumberModel(20,
				1, 365, 1));
		jSpinnerGuardarLogXDias
				.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
					public void mouseWheelMoved(
							java.awt.event.MouseWheelEvent evt) {
						jSpinnerGuardarLogXDiasMouseWheelMoved(evt);
					}
				});

		jLabelGuardarLogXDias2.setText("d\u00edas.");

		jCheckBoxMostrarResultadosAExaminado
				.setText("Mostrar resultados de las pruebas tambi\u00e9n en el monitor del evaluado");

		javax.swing.GroupLayout jPanelSeguridadLayout = new javax.swing.GroupLayout(
				jPanelSeguridad);
		jPanelSeguridad.setLayout(jPanelSeguridadLayout);
		jPanelSeguridadLayout
				.setHorizontalGroup(jPanelSeguridadLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelSeguridadLayout
										.createSequentialGroup()
										.addGroup(
												jPanelSeguridadLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelSeguridadLayout
																		.createSequentialGroup()
																		.addGap(
																				22,
																				22,
																				22)
																		.addComponent(
																				jLabelGuardarLogXDias)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jSpinnerGuardarLogXDias,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				47,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				12,
																				12,
																				12)
																		.addComponent(
																				jLabelGuardarLogXDias2))
														.addComponent(
																jCheckBoxGuardarLogDeActividades)
														.addComponent(
																jCheckBoxMostrarResultadosAExaminado))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanelSeguridadLayout
				.setVerticalGroup(jPanelSeguridadLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelSeguridadLayout
										.createSequentialGroup()
										.addComponent(
												jCheckBoxGuardarLogDeActividades)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelSeguridadLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelGuardarLogXDias)
														.addComponent(
																jLabelGuardarLogXDias2)
														.addComponent(
																jSpinnerGuardarLogXDias,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jCheckBoxMostrarResultadosAExaminado)
										.addContainerGap(17, Short.MAX_VALUE)));

		jPanelDocumento.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Documento",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jCheckBoxDocumentoPermitirSoloNumeros
				.setText("Permitir solo n\u00fameros");

		jSpinnerDocumentoCantidadCaracteresFija.setFont(new java.awt.Font(
				"Segoe UI", 0, 12));
		jSpinnerDocumentoCantidadCaracteresFija
				.setModel(new javax.swing.SpinnerNumberModel(7, 1, 99, 1));
		jSpinnerDocumentoCantidadCaracteresFija
				.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
					public void mouseWheelMoved(
							java.awt.event.MouseWheelEvent evt) {
						jSpinnerDocumentoCantidadCaracteresFijaMouseWheelMoved(evt);
					}
				});

		jCheckBoxDocumentoCantidadCaracteresFija
				.setText("Cantidad de caracteres fija:");
		jCheckBoxDocumentoCantidadCaracteresFija
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxDocumentoCantidadCaracteresFijaActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanelDocumentoLayout = new javax.swing.GroupLayout(
				jPanelDocumento);
		jPanelDocumento.setLayout(jPanelDocumentoLayout);
		jPanelDocumentoLayout
				.setHorizontalGroup(jPanelDocumentoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDocumentoLayout
										.createSequentialGroup()
										.addGroup(
												jPanelDocumentoLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelDocumentoLayout
																		.createSequentialGroup()
																		.addComponent(
																				jCheckBoxDocumentoCantidadCaracteresFija)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jSpinnerDocumentoCantidadCaracteresFija,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				39,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jCheckBoxDocumentoPermitirSoloNumeros))
										.addContainerGap(703, Short.MAX_VALUE)));
		jPanelDocumentoLayout
				.setVerticalGroup(jPanelDocumentoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDocumentoLayout
										.createSequentialGroup()
										.addComponent(
												jCheckBoxDocumentoPermitirSoloNumeros)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelDocumentoLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jCheckBoxDocumentoCantidadCaracteresFija)
														.addComponent(
																jSpinnerDocumentoCantidadCaracteresFija,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanelBuscarActualizaciones.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Actualizaci\u00f3n autom\u00e1tica",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jButtonBuscarActualizaciones.setText("Buscar actualizaciones ahora");
		jButtonBuscarActualizaciones
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonBuscarActualizacionesActionPerformed(evt);
					}
				});

		jLabelBuscarActualizacionesAlInicio
				.setText("Buscar actualizaciones al inicio del sistema:");

		buttonGroupActualizacionAlInicio
				.add(jRadioButtonBuscarActualizacionesSi);
		jRadioButtonBuscarActualizacionesSi.setText("Si");

		buttonGroupActualizacionAlInicio
				.add(jRadioButtonBuscarActualizacionesNo);
		jRadioButtonBuscarActualizacionesNo.setText("No");

		javax.swing.GroupLayout jPanelBuscarActualizacionesLayout = new javax.swing.GroupLayout(
				jPanelBuscarActualizaciones);
		jPanelBuscarActualizaciones
				.setLayout(jPanelBuscarActualizacionesLayout);
		jPanelBuscarActualizacionesLayout
				.setHorizontalGroup(jPanelBuscarActualizacionesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelBuscarActualizacionesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jLabelBuscarActualizacionesAlInicio)
										.addGap(18, 18, 18)
										.addComponent(
												jRadioButtonBuscarActualizacionesSi)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jRadioButtonBuscarActualizacionesNo)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												396, Short.MAX_VALUE)
										.addComponent(
												jButtonBuscarActualizaciones)));
		jPanelBuscarActualizacionesLayout
				.setVerticalGroup(jPanelBuscarActualizacionesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelBuscarActualizacionesLayout
										.createSequentialGroup()
										.addGroup(
												jPanelBuscarActualizacionesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButtonBuscarActualizacionesSi)
														.addComponent(
																jRadioButtonBuscarActualizacionesNo)
														.addComponent(
																jLabelBuscarActualizacionesAlInicio)
														.addComponent(
																jButtonBuscarActualizaciones))
										.addGap(38, 38, 38)));

		jPanelCompletitudDatosUsuario.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Completitud de datos de usuario",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jCheckBoxExigirFotografia
				.setText("Exigir fotograf\u00eda al cargar los datos.");

		jCheckBoxExigirFirmaDigital
				.setText("Exigir firma digital al cargar los datos.");

		javax.swing.GroupLayout jPanelCompletitudDatosUsuarioLayout = new javax.swing.GroupLayout(
				jPanelCompletitudDatosUsuario);
		jPanelCompletitudDatosUsuario
				.setLayout(jPanelCompletitudDatosUsuarioLayout);
		jPanelCompletitudDatosUsuarioLayout
				.setHorizontalGroup(jPanelCompletitudDatosUsuarioLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelCompletitudDatosUsuarioLayout
										.createSequentialGroup()
										.addGroup(
												jPanelCompletitudDatosUsuarioLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jCheckBoxExigirFotografia)
														.addComponent(
																jCheckBoxExigirFirmaDigital))
										.addContainerGap(180, Short.MAX_VALUE)));
		jPanelCompletitudDatosUsuarioLayout
				.setVerticalGroup(jPanelCompletitudDatosUsuarioLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelCompletitudDatosUsuarioLayout
										.createSequentialGroup()
										.addComponent(jCheckBoxExigirFotografia)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jCheckBoxExigirFirmaDigital)
										.addContainerGap(19, Short.MAX_VALUE)));

		jPanelBackup.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Backup",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jTextFieldRutaUbicacionBackupPrincipal.setEditable(false);

		jButtonExaminarRutaBackupPrincipal.setText("Examinar");
		jButtonExaminarRutaBackupPrincipal
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExaminarRutaBackupPrincipalActionPerformed(evt);
					}
				});

		jCheckBoxHacerBackupSecundario
				.setText("Hacer una segunda copia de seguridad en:");
		jCheckBoxHacerBackupSecundario
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxHacerBackupSecundarioActionPerformed(evt);
					}
				});

		jTextFieldRutaUbicacionBackupSecundario.setEditable(false);

		jButtonExaminarRutaBackupSecundario.setText("Examinar");
		jButtonExaminarRutaBackupSecundario
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExaminarRutaBackupSecundarioActionPerformed(evt);
					}
				});

		jLabelUbicacionBackupPrincipal
				.setText("Ubicaci\u00f3n del archivo de copia de seguridad:");

		jCheckBoxBackupAutomaticoCadaXDias
				.setText("Guardar copia de seguridad autom\u00e1ticamente cada");
		jCheckBoxBackupAutomaticoCadaXDias
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxBackupAutomaticoCadaXDiasActionPerformed(evt);
					}
				});

		jSpinnerBackupAutomaticoCadaXDias.setFont(new java.awt.Font("Segoe UI",
				0, 12));
		jSpinnerBackupAutomaticoCadaXDias
				.setModel(new javax.swing.SpinnerNumberModel(5, 1, 365, 1));
		jSpinnerBackupAutomaticoCadaXDias
				.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
					public void mouseWheelMoved(
							java.awt.event.MouseWheelEvent evt) {
						jSpinnerBackupAutomaticoCadaXDiasMouseWheelMoved(evt);
					}
				});

		jLabelBackupAutomaticoCadaXDias2.setText("d\u00edas.");

		jButtonExportarBaseDeDatos
				.setText("Exportar base de datos a un archivo ...");
		jButtonExportarBaseDeDatos
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExportarBaseDeDatosActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanelBackupLayout = new javax.swing.GroupLayout(
				jPanelBackup);
		jPanelBackup.setLayout(jPanelBackupLayout);
		jPanelBackupLayout
				.setHorizontalGroup(jPanelBackupLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelBackupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelBackupLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelBackupLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				jPanelBackupLayout
																						.createSequentialGroup()
																						.addComponent(
																								jCheckBoxBackupAutomaticoCadaXDias)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jSpinnerBackupAutomaticoCadaXDias,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								47,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jLabelBackupAutomaticoCadaXDias2)
																						.addGap(
																								243,
																								243,
																								243))
																		.addGroup(
																				jPanelBackupLayout
																						.createSequentialGroup()
																						.addGroup(
																								jPanelBackupLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(
																												jCheckBoxHacerBackupSecundario)
																										.addComponent(
																												jLabelUbicacionBackupPrincipal))
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																								jPanelBackupLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(
																												jTextFieldRutaUbicacionBackupPrincipal,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												548,
																												Short.MAX_VALUE)
																										.addComponent(
																												jTextFieldRutaUbicacionBackupSecundario,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												548,
																												Short.MAX_VALUE))
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																								jPanelBackupLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																										.addComponent(
																												jButtonExaminarRutaBackupPrincipal)
																										.addComponent(
																												jButtonExaminarRutaBackupSecundario))
																						.addContainerGap()))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanelBackupLayout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonExportarBaseDeDatos,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				249,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));
		jPanelBackupLayout
				.setVerticalGroup(jPanelBackupLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelBackupLayout
										.createSequentialGroup()
										.addGroup(
												jPanelBackupLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jCheckBoxBackupAutomaticoCadaXDias)
														.addComponent(
																jSpinnerBackupAutomaticoCadaXDias,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelBackupAutomaticoCadaXDias2))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelBackupLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jCheckBoxHacerBackupSecundario)
														.addGroup(
																jPanelBackupLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPanelBackupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jButtonExaminarRutaBackupPrincipal)
																						.addComponent(
																								jTextFieldRutaUbicacionBackupPrincipal,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabelUbicacionBackupPrincipal))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanelBackupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jButtonExaminarRutaBackupSecundario)
																						.addComponent(
																								jTextFieldRutaUbicacionBackupSecundario,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGap(18, 18, 18).addComponent(
												jButtonExportarBaseDeDatos)
										.addContainerGap(26, Short.MAX_VALUE)));

		jButtonCancelar.setMnemonic('C');
		jButtonCancelar.setText("Cancelar");
		jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCancelarActionPerformed(evt);
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
								jPanelBotonesLayout
										.createSequentialGroup()
										.addGap(7, 7, 7)
										.addComponent(jButtonGuardar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButtonCancelar)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanelBotonesLayout
				.setVerticalGroup(jPanelBotonesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelBotonesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelBotonesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonCancelar)
														.addComponent(
																jButtonGuardar))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Par\u00e1metros"));

		groupParametros.add(radioArgentina);
		radioArgentina.setSelected(true);
		radioArgentina.setText("Argentina");

		groupParametros.add(radioPeru);
		radioPeru.setText("Per\u00fa");
		radioPeru.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioPeruActionPerformed(evt);
			}
		});

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
										.addContainerGap()
										.addComponent(radioArgentina)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(radioPeru)
										.addContainerGap(774, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(radioArgentina).addComponent(radioPeru)));

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
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanelCompletitudDatosUsuario,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanelSeguridad,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanelFotos,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanelDocumento,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanelBackup,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanel1,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanelBuscarActualizaciones,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addComponent(
																jPanelBotones,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPanelBackup,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelFotos,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																185,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jPanelBuscarActualizaciones,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				69,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jPanelSeguridad,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				13,
																				13,
																				13)
																		.addComponent(
																				jPanelDocumento,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanelCompletitudDatosUsuario,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelBotones,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(21, 21, 21)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void radioPeruActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jCheckBoxBackupAutomaticoCadaXDiasActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (jCheckBoxBackupAutomaticoCadaXDias.isSelected()) {
			jSpinnerBackupAutomaticoCadaXDias.setEnabled(true);
			jButtonExaminarRutaBackupPrincipal.setEnabled(true);
			jButtonExaminarRutaBackupSecundario.setEnabled(true);
			jTextFieldRutaUbicacionBackupPrincipal.setEnabled(true);
			jTextFieldRutaUbicacionBackupSecundario.setEnabled(true);
			jCheckBoxHacerBackupSecundario.setEnabled(true);

		} else {
			jSpinnerBackupAutomaticoCadaXDias.setEnabled(false);
			jButtonExaminarRutaBackupPrincipal.setEnabled(false);
			jButtonExaminarRutaBackupSecundario.setEnabled(false);
			jTextFieldRutaUbicacionBackupPrincipal.setEnabled(false);
			jTextFieldRutaUbicacionBackupSecundario.setEnabled(false);
			jCheckBoxHacerBackupSecundario.setEnabled(false);
		}
	}

	private void jSpinnerBackupAutomaticoCadaXDiasMouseWheelMoved(
			java.awt.event.MouseWheelEvent evt) {
		int valorActualSpinner = new Integer(
				((Integer) jSpinnerBackupAutomaticoCadaXDias.getValue())
						.intValue()
						- evt.getWheelRotation());

		if (valorActualSpinner > 0) {

			jSpinnerBackupAutomaticoCadaXDias.setValue(new Integer(
					((Integer) jSpinnerBackupAutomaticoCadaXDias.getValue())
							.intValue()
							- evt.getWheelRotation()));
		}
	}

	private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		cargarValoresDePropiedades();
	}

	private void jCheckBoxHacerBackupSecundarioActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (jCheckBoxHacerBackupSecundario.isSelected()) {
			jTextFieldRutaUbicacionBackupSecundario.setEnabled(true);
			jButtonExaminarRutaBackupSecundario.setEnabled(true);
		} else {
			jTextFieldRutaUbicacionBackupSecundario.setEnabled(false);
			jButtonExaminarRutaBackupSecundario.setEnabled(false);
		}
	}

	private void jSpinnerDocumentoCantidadCaracteresFijaMouseWheelMoved(
			java.awt.event.MouseWheelEvent evt) {

		int valorActualSpinner = new Integer(
				((Integer) jSpinnerDocumentoCantidadCaracteresFija.getValue())
						.intValue()
						- evt.getWheelRotation());

		if (valorActualSpinner > 0) {
			jSpinnerDocumentoCantidadCaracteresFija.setValue(new Integer(
					((Integer) jSpinnerDocumentoCantidadCaracteresFija
							.getValue()).intValue()
							- evt.getWheelRotation()));
		}
	}

	private void jSpinnerGuardarLogXDiasMouseWheelMoved(
			java.awt.event.MouseWheelEvent evt) {

		int valorActualSpinner = new Integer(((Integer) jSpinnerGuardarLogXDias
				.getValue()).intValue()
				- evt.getWheelRotation());

		if (valorActualSpinner > 0) {

			jSpinnerGuardarLogXDias.setValue(new Integer(
					((Integer) jSpinnerGuardarLogXDias.getValue()).intValue()
							- evt.getWheelRotation()));

		}

	}

	private void jSpinnerTamanoFotosMouseWheelMoved(
			java.awt.event.MouseWheelEvent evt) {

		int valorActualSpinner = new Integer(((Integer) jSpinnerTamanoFotos
				.getValue()).intValue()
				- evt.getWheelRotation());

		if (valorActualSpinner > 0) {
			jSpinnerTamanoFotos.setValue(new Integer(
					((Integer) jSpinnerTamanoFotos.getValue()).intValue()
							- evt.getWheelRotation()));

		}
	}

	private void jButtonExaminarRutaBackupSecundarioActionPerformed(
			java.awt.event.ActionEvent evt) {
		final VentanaExaminar ventanaExaminar = new VentanaExaminar(
				JFileChooser.DIRECTORIES_ONLY, JFileChooser.SAVE_DIALOG);
		ventanaExaminar.pack();
		Util.agregarIframe(ventanaExaminar);
		ventanaExaminar.doModal(this.getRootPane());
		ventanaExaminar.setVisible(true);

		if (ventanaExaminar.getRutaSeleccionada() != null) {
			jTextFieldRutaUbicacionBackupSecundario.setText(ventanaExaminar
					.getRutaSeleccionada());
		}
	}

	private void jButtonExaminarRutaBackupPrincipalActionPerformed(
			java.awt.event.ActionEvent evt) {
		final VentanaExaminar ventanaExaminar = new VentanaExaminar(
				JFileChooser.DIRECTORIES_ONLY, JFileChooser.SAVE_DIALOG);
		ventanaExaminar.pack();
		Util.agregarIframe(ventanaExaminar);
		ventanaExaminar.doModal(this.getRootPane());
		ventanaExaminar.setVisible(true);

		if (ventanaExaminar.getRutaSeleccionada() != null) {
			jTextFieldRutaUbicacionBackupPrincipal.setText(ventanaExaminar
					.getRutaSeleccionada());
		}
	}

	public void setColor() {
//		AbstractColorChooserPanel[] colorPane = jColorChooser
//				.getChooserPanels();
//		AbstractColorChooserPanel[] colorPane1 = new AbstractColorChooserPanel[1];
//		colorPane1[0] = colorPane[0];

//		jColorChooser.setChooserPanels(colorPane1);
//		jColorChooser.getPreviewPanel().setVisible(false);
//		jColorChooser.getSelectionModel().addChangeListener(
//				new ChangeListener() {
//					public void stateChanged(ChangeEvent e) {
//						Color newColor = jColorChooser.getColor();
//						panelColor.setForeground(newColor);
//						panelColor.setBackground(newColor);
//
//					}
//
//				});
	}

	private void jButtonExportarBaseDeDatosActionPerformed(
			java.awt.event.ActionEvent evt) {

		final VentanaExaminar ventanaExaminar = new VentanaExaminar(
				JFileChooser.DIRECTORIES_ONLY, JFileChooser.SAVE_DIALOG);
		ventanaExaminar.pack();
		Util.agregarIframe(ventanaExaminar);
		ventanaExaminar.doModal(this.getRootPane());
		ventanaExaminar.setVisible(true);

		if (ventanaExaminar.getRutaSeleccionada() != null) {
			File archivoSeleccionado = new File(ventanaExaminar
					.getRutaSeleccionada());
			rutaDestinoArchivoSQL = archivoSeleccionado.getAbsolutePath();
			GestorExportarDB gestorExportar = new GestorExportarDB();
			GestorExportarDB.setRutaDestinoBackup(rutaDestinoArchivoSQL);
			Thread threadExportar = new Thread(gestorExportar);
			threadExportar.start();

			JOptionPaneTesterGral.showInternalMessageDialog(
					"La base de datos se exportó satisfactoriamente en "
							+ GestorExportarDB.getRutaDestinoBackup(),
					"Export", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void jButtonBuscarActualizacionesActionPerformed(
			java.awt.event.ActionEvent evt) {

		Util.mostrarPanelOperacionesLargas();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Util.panelControlActualizaciones();
				Util.ocultarPanelOperacionesLargas();
			}
		});

	}

	private void jRadioButtonObtenerFotosDesdeCamaraWebActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (jRadioButtonObtenerFotosDesdeCamaraWeb.isSelected()) {
			jTextFieldRutaOrigenFotos.setEnabled(false);
			jButtonExaminarRutaOrigenFotos.setEnabled(false);
		}
	}

	private void jRadioButtonObtenerFotosDesdeDiscoActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (jRadioButtonObtenerFotosDesdeDisco.isSelected()) {
			jTextFieldRutaOrigenFotos.setEnabled(true);
			jButtonExaminarRutaOrigenFotos.setEnabled(true);
		}

	}

	private void jCheckBoxGuardarLogDeActividadesActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (jCheckBoxGuardarLogDeActividades.isSelected()) {
			jSpinnerGuardarLogXDias.setEnabled(true);
		} else {
			jSpinnerGuardarLogXDias.setEnabled(false);
		}
	}

	private void jCheckBoxDocumentoCantidadCaracteresFijaActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (jCheckBoxDocumentoCantidadCaracteresFija.isSelected()) {
			jSpinnerDocumentoCantidadCaracteresFija.setEnabled(true);
		} else {
			jSpinnerDocumentoCantidadCaracteresFija.setEnabled(false);
		}

	}

	private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		guardarValoresPanelActualizaciones();
		guardarValoresPanelCompletitudDatosDeUsuario();
		guardarValoresPanelDocumento();
		guardarValoresPanelFotos();
		guardarValoresPanelSeguridad();
		guardarValoresPanelBackup();
		guardarValoresPanelImagenDeFondo();
		guardarValoresPanelParametros();

		JOptionPaneTesterGral.showInternalMessageDialog(
				"Los cambios se han guardado correctamente",
				Constantes.MENSAJE_GUARDADO_TIT,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void guardarValoresPanelParametros() {

		Propiedad propiedad = new Propiedad();
		propiedad.setPropClave("PARAMETROS.CONFIGURACION");

		if (radioArgentina.isSelected()) {

			propiedad.setPropValor("ARGENTINA");
			actualizaraParametrosArgentina();

		} else if (radioPeru.isSelected()) {
			propiedad.setPropValor("PERU");
			actualizaraParametrosPeru();
		}

		try {
			propiedadService.update(propiedad);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void actualizaraParametrosArgentina() {
		try {
			PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
					.getBizObject("propiedadService");
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");

			//PUNTEO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//La duración de esta prueba debe ser de 60 segundos.
			Propiedad propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.EXAMEN.DURACION");
			propiedad.setPropValor("60000");
			propiedadService.update(propiedad);

			//Este parámetro debe corregirse para permitir hasta 10 errores como máximo.
			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.ERRORES.PERMITIDOS.HASTA");
			propiedad.setPropValor("10");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.LUZ.PROFECIONAL");
			propiedad.setPropValor("850");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.ENTRE.TIEMPO.PROFECIONAL");
			propiedad.setPropValor("420");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.LUZ.PARTICULAR");
			propiedad.setPropValor("970");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.ENTRE.TIEMPO.PARTICULAR");
			propiedad.setPropValor("540");
			propiedadService.update(propiedad);

			ExamenDetalle example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_COOR_VISOMOTORA);
			List<ExamenDetalle> examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "Errores permitidos menor o igual a 10.";
				ExamenDetalle coordinacionVM = examanes.get(0);
				coordinacionVM.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(coordinacionVM);
			}

			//PALANCA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//tolere hasta 14 errores como máximo.
			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.PALANCA.ERRORES.PUNTOS.SIN.ACTIVAR.HASTA");
			propiedad.setPropValor("14");
			propiedadService.update(propiedad);

			//El tiempo de permanencia en error (tiempo fuera del camino) es de 18 segundos.
			propiedad = new Propiedad();
			propiedad.setPropClave("EXAMEN.PALANCA.ERRORES.TIEMPO");
			propiedad.setPropValor("18000");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.PALANCA.TIEMPO.DURACION.HASTA.PROFECIONAL");
			propiedad.setPropValor("80000");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.PALANCA.TIEMPO.DURACION.HASTA.PARTICULAR");
			propiedad.setPropValor("100000");
			propiedadService.update(propiedad);

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Tiempo fuera del circuito menor o igual a 1800 Centésimas de segundos.</li> "
						+ "<li>Puntos sin activar menor o igual a 14.</li> "
						+ "</ul>";
				ExamenDetalle coordinacionFina = examanes.get(0);
				coordinacionFina
						.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(coordinacionFina);
			}

			////////VISION

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL_CERCANA);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional: 0.8 como mínimo (línea 6).</li> "
						+ "<li>Particular: 0.7 como mínimo (línea 5).</li> "
						+ "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL_LEJANA);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional: 0.8 como mínimo (línea 6).</li> "
						+ "<li>Particular: 0.7 como mínimo (línea 5).</li> "
						+ "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_VISION_NOCTURNA);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "80% de las imágenes reconocidas.";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_ENCANDILAMIENTO);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "Ver la imagen.";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_REC_ENCANDILAMIENTO);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "Ver la imagen antes de los 5 segundos.";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			//AUDIO
			example = new ExamenDetalle();
			example.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional:Como mínimo 50 Db.</li> "
						+ "<li>Particular:Como mínimo 60 Db.</li> " + "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO_DER);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional:Como mínimo 50 Db.</li> "
						+ "<li>Particular:Como mínimo 60 Db.</li> " + "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO_IZQ);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional:Como mínimo 50 Db.</li> "
						+ "<li>Particular:Como mínimo 60 Db.</li> " + "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void actualizaraParametrosPeru() {

		try {
			PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
					.getBizObject("propiedadService");

			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");

			//PUNTEO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//La duración de esta prueba debe ser de 30 segundos.
			Propiedad propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.EXAMEN.DURACION");
			propiedad.setPropValor("30000");
			propiedadService.update(propiedad);

			//Este parámetro debe corregirse para permitir hasta 23 errores como máximo.
			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.ERRORES.PERMITIDOS.HASTA");
			propiedad.setPropValor("23");
			propiedadService.update(propiedad);

			//Se requiere una velocidad equivalente a 30 revoluciones por minuto (es decir, una vuelta completa en 2 segundos). 
			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.LUZ.PROFECIONAL");
			propiedad.setPropValor("400");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.ENTRE.TIEMPO.PROFECIONAL");
			propiedad.setPropValor("100");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.LUZ.PARTICULAR");
			propiedad.setPropValor("400");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.COORDINACION.VISOMOTORA.ENTRE.TIEMPO.PARTICULAR");
			propiedad.setPropValor("100");
			propiedadService.update(propiedad);

			ExamenDetalle example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_COOR_VISOMOTORA);
			List<ExamenDetalle> examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Errores permitidos menor o igual a 23.</li> "
						+ "<li>Aciertos: mayor o igual a 24.</li> "
						+ "<li>Por lo menos 4 segundos de permanencia en aciertos.</li> "
						+ "</ul>";
				ExamenDetalle coordinacionVM = examanes.get(0);
				coordinacionVM.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(coordinacionVM);
			}

			//PALANCA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//tolere hasta 12 errores como máximo.
			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.PALANCA.ERRORES.PUNTOS.SIN.ACTIVAR.HASTA");
			propiedad.setPropValor("12");
			propiedadService.update(propiedad);

			//El tiempo de permanencia en error (tiempo fuera del camino) es de 5 segundos.
			propiedad = new Propiedad();
			propiedad.setPropClave("EXAMEN.PALANCA.ERRORES.TIEMPO");
			propiedad.setPropValor("5000");
			propiedadService.update(propiedad);

			//El tiempo límite de la prueba debe ser 60 segundos.
			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.PALANCA.TIEMPO.DURACION.HASTA.PROFECIONAL");
			propiedad.setPropValor("60000");
			propiedadService.update(propiedad);

			propiedad = new Propiedad();
			propiedad
					.setPropClave("EXAMEN.PALANCA.TIEMPO.DURACION.HASTA.PARTICULAR");
			propiedad.setPropValor("60000");
			propiedadService.update(propiedad);

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Tiempo fuera del circuito menor o igual a 500 Centésimas de segundos.</li> "
						+ "<li>Puntos sin activar menor o igual a 12.</li> "
						+ "</ul>";
				ExamenDetalle coordinacionFina = examanes.get(0);
				coordinacionFina
						.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(coordinacionFina);
			}

			//VISIO

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL_CERCANA);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional: 20/20 como mínimo.</li> "
						+ "<li>Particular: 20/30 como mínimo.</li> " + "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL_LEJANA);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional: 20/20 como mínimo.</li> "
						+ "<li>Particular: 20/30 como mínimo.</li> " + "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_VISION_NOCTURNA);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "100% de las imágenes reconocidas (Iluminación a 35 cd).";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_ENCANDILAMIENTO);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "Ver la imagen (Iluminación a 45 cd).";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_REC_ENCANDILAMIENTO);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "Ver la imagen antes de los 5 segundos (Iluminación a 35 cd).";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			//AUDIO

			example = new ExamenDetalle();
			example.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional:Como mínimo 40 Db.</li> "
						+ "<li>Particular:Como mínimo 80 Db.</li> " + "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO_DER);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional:Como mínimo 40 Db.</li> "
						+ "<li>Particular:Como mínimo 80 Db.</li> " + "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

			example = new ExamenDetalle();
			example.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO_IZQ);
			examanes = examenDetalleService.getAll(example);
			if (examanes.size() > 0) {
				String parametroCorreccion = "<ul> "
						+ "<li>Profesional:Como mínimo 40 Db.</li> "
						+ "<li>Particular:Como mínimo 80 Db.</li> " + "</ul>";
				ExamenDetalle detalle = examanes.get(0);
				detalle.setExadParametrosCorrecion(parametroCorreccion);
				examenDetalleService.update(detalle);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private void jButtonExaminarRutaOrigenFotosActionPerformed(
			java.awt.event.ActionEvent evt) {

		final VentanaExaminar ventanaExaminar = new VentanaExaminar(
				JFileChooser.DIRECTORIES_ONLY, JFileChooser.OPEN_DIALOG);
		ventanaExaminar.pack();
		Util.agregarIframe(ventanaExaminar);
		ventanaExaminar.doModal(this.getRootPane());
		ventanaExaminar.setVisible(true);

		if (ventanaExaminar.getRutaSeleccionada() != null) {
			jTextFieldRutaOrigenFotos.setText(ventanaExaminar
					.getRutaSeleccionada());
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroupActualizacionAlInicio;
	private javax.swing.ButtonGroup buttonGroupOrigenFotos;
	private javax.swing.ButtonGroup groupParametros;
	private javax.swing.JButton jButtonBuscarActualizaciones;
	private javax.swing.JButton jButtonCancelar;
	private javax.swing.JButton jButtonExaminarRutaBackupPrincipal;
	private javax.swing.JButton jButtonExaminarRutaBackupSecundario;
	private javax.swing.JButton jButtonExaminarRutaOrigenFotos;
	private javax.swing.JButton jButtonExportarBaseDeDatos;
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JCheckBox jCheckBoxBackupAutomaticoCadaXDias;
	private javax.swing.JCheckBox jCheckBoxDocumentoCantidadCaracteresFija;
	private javax.swing.JCheckBox jCheckBoxDocumentoPermitirSoloNumeros;
	private javax.swing.JCheckBox jCheckBoxExigirFirmaDigital;
	private javax.swing.JCheckBox jCheckBoxExigirFotografia;
	private javax.swing.JCheckBox jCheckBoxGuardarLogDeActividades;
	private javax.swing.JCheckBox jCheckBoxHacerBackupSecundario;
	private javax.swing.JCheckBox jCheckBoxMostrarResultadosAExaminado;
	private javax.swing.JLabel jLabelBackupAutomaticoCadaXDias2;
	private javax.swing.JLabel jLabelBuscarActualizacionesAlInicio;
	private javax.swing.JLabel jLabelGuardarLogXDias;
	private javax.swing.JLabel jLabelGuardarLogXDias2;
	private javax.swing.JLabel jLabelMostrarFotos;
	private javax.swing.JLabel jLabelRutaOrigenFotos;
	private javax.swing.JLabel jLabelTamanoFotos2;
	private javax.swing.JLabel jLabelUbicacionBackupPrincipal;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanelBackup;
	private javax.swing.JPanel jPanelBotones;
	private javax.swing.JPanel jPanelBuscarActualizaciones;
	private javax.swing.JPanel jPanelCompletitudDatosUsuario;
	private javax.swing.JPanel jPanelDocumento;
	private javax.swing.JPanel jPanelFotos;
	private javax.swing.JPanel jPanelOrigenDeFotos;
	private javax.swing.JPanel jPanelSeguridad;
	private javax.swing.JRadioButton jRadioButtonBuscarActualizacionesNo;
	private javax.swing.JRadioButton jRadioButtonBuscarActualizacionesSi;
	private javax.swing.JRadioButton jRadioButtonObtenerFotosDesdeCamaraWeb;
	private javax.swing.JRadioButton jRadioButtonObtenerFotosDesdeDisco;
	private javax.swing.JSpinner jSpinnerBackupAutomaticoCadaXDias;
	private javax.swing.JSpinner jSpinnerDocumentoCantidadCaracteresFija;
	private javax.swing.JSpinner jSpinnerGuardarLogXDias;
	private javax.swing.JSpinner jSpinnerTamanoFotos;
	private javax.swing.JTextField jTextFieldRutaOrigenFotos;
	private javax.swing.JTextField jTextFieldRutaUbicacionBackupPrincipal;
	private javax.swing.JTextField jTextFieldRutaUbicacionBackupSecundario;
	private javax.swing.JRadioButton radioArgentina;
	private javax.swing.JRadioButton radioPeru;
	// End of variables declaration//GEN-END:variables

}