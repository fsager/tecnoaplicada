/*
 * PanelPanelDeControl.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.OrigenFotosEnum;
import testerGeneral.domain.Propiedad;
import testerGeneral.persistence.GestorExportarDB;
import testerGeneral.service.PropiedadDefinition;
import actualizaciones.GestorActualizacionesUtil;
import autoimpresor.business.ContextManager;
import autoimpresor.business.DBImporter;
import autoimpresor.frontend.ExtensionFileFilter;
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
	private String rutaOrigenArchivoAccess;
	private static final Log log = LogFactory.getLog(PanelPanelDeControl.class);
	private PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");

	/** Creates new form PanelPanelDeControl */
	public PanelPanelDeControl() {
		initComponents();
		Util.mostrarError(lbSinResultados, "", true);
		setColor();
		cargarValoresDePropiedades();
	}

	private void cargarValoresDePropiedades() {
		cargarValoresPanelFotos();
		cargarValoresPanelActualizaciones();
		cargarValoresPanelCompletitudDatosDeUsuario();
		cargarValoresPanelDocumento();
		cargarValoresPanelSeguridad();
		cargarValoresPanelImagenDeFondo();
		cargarValoresPanelBackup();
		cargarValoresPanelImpresionLicencias();

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
			Propiedad propiedadTamanoFirmaRedimensionada = propiedadService
			.get("SISTEMA.FIRMAS.PORCENTAJE.REDUCCION");
			Propiedad propiedadDispositivoOrigenFoto = propiedadService
					.get("SISTEMA.ORIGEN.FOTOS");
			Propiedad propiedadRutaOrigenFoto = propiedadService
					.get("DIRECTORIO.IMAGENES");

			String valorPropiedadTamanoFotoRedimensionada = propiedadTamanoFotoRedimensionada
					.getPropValor();
			String valorPropiedadTamanoFirmaRedimensionada = propiedadTamanoFirmaRedimensionada
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
			jSpinnerTamanoFirmas.setValue(Integer
					.valueOf(valorPropiedadTamanoFirmaRedimensionada));
			jTextFieldRutaOrigenFotos.setText(valorPropiedadRutaOrigenFoto);

		} catch (Exception ex) {
			throw new RuntimeException(ex);

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
			throw new RuntimeException(ex);

		}

	}

	private void cargarValoresPanelSeguridad() {
		try {
			Propiedad propiedadGuardarLogDeEventos = propiedadService
					.get("SISTEMA.GUARDAR.LOG.EVENTOS");
			Propiedad propiedadDiasAGuardarLogDeEventos = propiedadService
					.get("SISTEMA.CONSERVAR.DATOS.LOG.EVENTOS.XDIAS");

			String valorPropiedadGuardarLogDeEventos = propiedadGuardarLogDeEventos
					.getPropValor();
			String valorPropiedadDiasAGuardarLogDeEventos = propiedadDiasAGuardarLogDeEventos
					.getPropValor();

			if (valorPropiedadGuardarLogDeEventos.equals("S")) {
				jCheckBoxGuardarLogDeActividades.setSelected(true);
				jSpinnerGuardarLogXDias.setEnabled(true);
			} else {
				jCheckBoxGuardarLogDeActividades.setSelected(false);
				jSpinnerGuardarLogXDias.setEnabled(false);
			}

			jSpinnerGuardarLogXDias.setValue(Integer
					.valueOf(valorPropiedadDiasAGuardarLogDeEventos));

		} catch (Exception ex) {
			throw new RuntimeException(ex);

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
			throw new RuntimeException(ex);

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
			throw new RuntimeException(ex);

		}

	}

	private void cargarValoresPanelImagenDeFondo() {

		try {

			Propiedad propiedadImagenFondoAplicacion = propiedadService
					.get("SISTEMA.IMAGEN.PRIMARIA");

			if (!propiedadImagenFondoAplicacion.getPropValor().isEmpty()) {
				panelColor
						.setBackground(new Color(Integer
								.valueOf(propiedadImagenFondoAplicacion
										.getPropValor())));
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
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
			throw new RuntimeException(ex);
		}

	}

	//metodo para validar correo electronio
	public boolean isEmail(String correo) {
		if (isValidEmailAddress(correo)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidEmailAddress(String aEmailAddress) {
		if (aEmailAddress == null)
			return false;
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(aEmailAddress);
			emailAddr.validate();

			if (!hasNameAndDomain(aEmailAddress)) {
				result = false;
			}
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	private static boolean hasNameAndDomain(String aEmailAddress) {
		String[] tokens = aEmailAddress.split("@");
		return tokens.length == 2 && tokens[0].length() > 3
				&& tokens[0].length() > 3;
	}

	private void cargarValoresPanelImpresionLicencias() {
		try {

			Propiedad propiedadValidarRequisitos = propiedadService
					.get("SISTEMA.VALIDAR_REQUISITOS_SN");

			Propiedad propiedadEnviarArchivosLicenciaPorMail = propiedadService
					.get("SISTEMA.EMAIL.AUTOMATICO.IMPRESION.LICENCIAS");
			Propiedad propiedadMailCentroImpresionLicencias = propiedadService
					.get("SISTEMA.EMAIL.CENTRO.IMPRESION.LICENCIAS");

			String valorPropiedadValidarRequisitos = propiedadValidarRequisitos
					.getPropValor();
			String valorPropiedadEnviarArchivosLicenciaPorMail = propiedadEnviarArchivosLicenciaPorMail
					.getPropValor();
			String valorPropiedadMailCentroImpresionLicencias = propiedadMailCentroImpresionLicencias
					.getPropValor();

			if (valorPropiedadValidarRequisitos.equals("S")) {
				jCheckBoxValidarRequisitos.setSelected(true);
			} else {
				jCheckBoxValidarRequisitos.setSelected(false);
			}

			jTextFieldEmailCentroImpresion
					.setText(valorPropiedadMailCentroImpresionLicencias);

		} catch (Exception ex) {
			throw new RuntimeException(ex);

		}

	}

	private void guardarValoresPanelFotos() {

		Propiedad propiedadTamanoFotoRedimensionada = new Propiedad();
		propiedadTamanoFotoRedimensionada
				.setPropClave("SISTEMA.IMAGENES.PORCENTAJE.REDUCCION");
		Propiedad propiedadTamanoFirmaRedimensionada = new Propiedad();
		propiedadTamanoFirmaRedimensionada
				.setPropClave("SISTEMA.FIRMAS.PORCENTAJE.REDUCCION");
		Propiedad propiedadDispositivoOrigenFoto = new Propiedad();
		propiedadDispositivoOrigenFoto.setPropClave("SISTEMA.ORIGEN.FOTOS");
		Propiedad propiedadRutaOrigenFoto = new Propiedad();
		propiedadRutaOrigenFoto.setPropClave("DIRECTORIO.IMAGENES");

		propiedadTamanoFotoRedimensionada.setPropValor(String
				.valueOf(jSpinnerTamanoFotos.getValue()));
		propiedadTamanoFirmaRedimensionada.setPropValor(String
				.valueOf(jSpinnerTamanoFirmas.getValue()));

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
			propiedadService.update(propiedadTamanoFirmaRedimensionada);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
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
			throw new RuntimeException(ex);
		}

	}

	private void guardarValoresPanelSeguridad() {

		Propiedad propiedadGuardarLogDeEventos = new Propiedad();
		propiedadGuardarLogDeEventos
				.setPropClave("SISTEMA.GUARDAR.LOG.EVENTOS");

		Propiedad propiedadDiasAGuardarLogDeEventos = new Propiedad();
		propiedadDiasAGuardarLogDeEventos
				.setPropClave("SISTEMA.CONSERVAR.DATOS.LOG.EVENTOS.XDIAS");

		if (jCheckBoxGuardarLogDeActividades.isSelected()) {
			propiedadGuardarLogDeEventos.setPropValor("S");

		} else {
			propiedadGuardarLogDeEventos.setPropValor("N");
		}

		propiedadDiasAGuardarLogDeEventos.setPropValor(String
				.valueOf(jSpinnerGuardarLogXDias.getValue()));

		try {
			propiedadService.update(propiedadGuardarLogDeEventos);
			propiedadService.update(propiedadDiasAGuardarLogDeEventos);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
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
			throw new RuntimeException(ex);
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
			throw new RuntimeException(ex);
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
			throw new RuntimeException(ex);
		}
	}

	private void guardarValoresPanelImagenDeFondo() {
		Propiedad propiedadImagenFondoAplicacion = new Propiedad();
		propiedadImagenFondoAplicacion.setPropClave("SISTEMA.IMAGEN.PRIMARIA");
		propiedadImagenFondoAplicacion.setPropValor(""
				+ panelColor.getBackground().getRGB());

		ContextManager.updatePropiedad(propiedadImagenFondoAplicacion);
		Util.frameContenedor.getContentPane().setBackground(
				panelColor.getBackground());
		Util.dp.repaint();

	}

	private void guardarValoresPanelImpresionLicencias() {

		Propiedad propiedadValidarRequisitos = new Propiedad();
		propiedadValidarRequisitos
				.setPropClave("SISTEMA.VALIDAR_REQUISITOS_SN");

		Propiedad propiedadMailCentroImpresionLicencias = new Propiedad();
		propiedadMailCentroImpresionLicencias
				.setPropClave("SISTEMA.EMAIL.CENTRO.IMPRESION.LICENCIAS");

		if (jCheckBoxValidarRequisitos.isSelected()) {
			propiedadValidarRequisitos.setPropValor("S");

		} else {
			propiedadValidarRequisitos.setPropValor("N");
		}

		propiedadMailCentroImpresionLicencias
				.setPropValor(jTextFieldEmailCentroImpresion.getText());

		try {
			propiedadService.update(propiedadValidarRequisitos);
			propiedadService.update(propiedadMailCentroImpresionLicencias);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroupOrigenFotos = new javax.swing.ButtonGroup();
		buttonGroupActualizacionAlInicio = new javax.swing.ButtonGroup();
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
		jSpinnerTamanoFirmas = new javax.swing.JSpinner();
		jLabel1jLabelTamanoFotos3 = new javax.swing.JLabel();
		jPanelSeguridad = new javax.swing.JPanel();
		jCheckBoxGuardarLogDeActividades = new javax.swing.JCheckBox();
		jLabelGuardarLogXDias = new javax.swing.JLabel();
		jSpinnerGuardarLogXDias = new javax.swing.JSpinner();
		jLabelGuardarLogXDias2 = new javax.swing.JLabel();
		jPanelBuscarActualizaciones = new javax.swing.JPanel();
		jButtonBuscarActualizaciones = new javax.swing.JButton();
		jLabelBuscarActualizacionesAlInicio = new javax.swing.JLabel();
		jRadioButtonBuscarActualizacionesSi = new javax.swing.JRadioButton();
		jRadioButtonBuscarActualizacionesNo = new javax.swing.JRadioButton();
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
		jButtonImportarDBAccess = new javax.swing.JButton();
		jPanelImagenFondo = new javax.swing.JPanel();
		jLabelRutaOrigenFondoAplicacion = new javax.swing.JLabel();
		panelColor = new javax.swing.JPanel();
		jColorChooser = new javax.swing.JColorChooser();
		jPanelCompletitudDatosUsuario = new javax.swing.JPanel();
		jCheckBoxExigirFotografia = new javax.swing.JCheckBox();
		jCheckBoxExigirFirmaDigital = new javax.swing.JCheckBox();
		jPanelImpresionLicencias = new javax.swing.JPanel();
		jLabelEmailCentroImpresion = new javax.swing.JLabel();
		jTextFieldEmailCentroImpresion = new javax.swing.JTextField();
		jCheckBoxValidarRequisitos = new javax.swing.JCheckBox();
		jPanelDocumento = new javax.swing.JPanel();
		jCheckBoxDocumentoPermitirSoloNumeros = new javax.swing.JCheckBox();
		jSpinnerDocumentoCantidadCaracteresFija = new javax.swing.JSpinner();
		jCheckBoxDocumentoCantidadCaracteresFija = new javax.swing.JCheckBox();
		jButtonGuardar = new javax.swing.JButton();
		jButtonCancelar = new javax.swing.JButton();
		lbSinResultados = new javax.swing.JLabel();

		jPanelFotos.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Fotos",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jPanelOrigenDeFotos.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Origen de fotos"));

		buttonGroupOrigenFotos.add(jRadioButtonObtenerFotosDesdeDisco);
		jRadioButtonObtenerFotosDesdeDisco
				.setText("Obtener fotos desde c\u00e1mara de fotos.");
		jRadioButtonObtenerFotosDesdeDisco
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonObtenerFotosDesdeDiscoActionPerformed(evt);
					}
				});

		buttonGroupOrigenFotos.add(jRadioButtonObtenerFotosDesdeCamaraWeb);
		jRadioButtonObtenerFotosDesdeCamaraWeb
				.setText("Obtener fotos desde c\u00e1mara Web.");
		jRadioButtonObtenerFotosDesdeCamaraWeb
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonObtenerFotosDesdeCamaraWebActionPerformed(evt);
					}
				});

		jLabelRutaOrigenFotos.setText("Buscar fotos en:");

		jTextFieldRutaOrigenFotos.setEditable(false);
		jTextFieldRutaOrigenFotos
				.setMaximumSize(new java.awt.Dimension(236, 22));

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
												290, Short.MAX_VALUE)
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
										.addContainerGap(263, Short.MAX_VALUE)));
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

		jLabelTamanoFotos2.setText("% de su tama\u00f1o original y firmas al");

		jLabelMostrarFotos.setText("Mostar fotos al");

		jSpinnerTamanoFirmas.setFont(new java.awt.Font("Segoe UI", 0, 12));
		jSpinnerTamanoFirmas.setModel(new javax.swing.SpinnerNumberModel(25, 1,
				365, 1));
		jSpinnerTamanoFirmas
				.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
					public void mouseWheelMoved(
							java.awt.event.MouseWheelEvent evt) {
						jSpinnerTamanoFirmasMouseWheelMoved(evt);
					}
				});

		jLabel1jLabelTamanoFotos3.setText("%.");

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
										.addGap(12, 12, 12)
										.addComponent(jLabelMostrarFotos)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jSpinnerTamanoFotos,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												47,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabelTamanoFotos2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jSpinnerTamanoFirmas,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												47,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabel1jLabelTamanoFotos3)
										.addContainerGap(107, Short.MAX_VALUE))
						.addGroup(
								jPanelFotosLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jPanelOrigenDeFotos,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
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
																jLabelTamanoFotos2)
														.addComponent(
																jSpinnerTamanoFirmas,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabel1jLabelTamanoFotos3))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelOrigenDeFotos,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		jPanelSeguridad.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Seguridad",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jCheckBoxGuardarLogDeActividades
				.setText("Guardar un Log de las actividades del sistema.");
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
																jCheckBoxGuardarLogDeActividades))
										.addContainerGap(243, Short.MAX_VALUE)));
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
										.addContainerGap(3, Short.MAX_VALUE)));

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
										.addGroup(
												jPanelBuscarActualizacionesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelBuscarActualizacionesLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelBuscarActualizacionesAlInicio)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jRadioButtonBuscarActualizacionesSi)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jRadioButtonBuscarActualizacionesNo))
														.addComponent(
																jButtonBuscarActualizaciones))
										.addContainerGap(177, Short.MAX_VALUE)));
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
																jLabelBuscarActualizacionesAlInicio))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jButtonBuscarActualizaciones)
										.addContainerGap(21, Short.MAX_VALUE)));

		jPanelBackup.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Backup",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jTextFieldRutaUbicacionBackupPrincipal.setEditable(false);
		jTextFieldRutaUbicacionBackupPrincipal
				.setMaximumSize(new java.awt.Dimension(196, 22));

		jButtonExaminarRutaBackupPrincipal.setText("Examinar");
		jButtonExaminarRutaBackupPrincipal
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExaminarRutaBackupPrincipalActionPerformed(evt);
					}
				});

		jCheckBoxHacerBackupSecundario.setText("Hacer una segunda copia en:");
		jCheckBoxHacerBackupSecundario
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxHacerBackupSecundarioActionPerformed(evt);
					}
				});

		jTextFieldRutaUbicacionBackupSecundario.setEditable(false);
		jTextFieldRutaUbicacionBackupSecundario
				.setMaximumSize(new java.awt.Dimension(196, 22));

		jButtonExaminarRutaBackupSecundario.setText("Examinar");
		jButtonExaminarRutaBackupSecundario
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExaminarRutaBackupSecundarioActionPerformed(evt);
					}
				});

		jLabelUbicacionBackupPrincipal
				.setText("Ubicaci\u00f3n del archivo de backup:");

		jCheckBoxBackupAutomaticoCadaXDias
				.setText("Guardar copia de seguridad (backup) autom\u00e1ticamente cada");
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
				.setText("Exportar base de datos a un archivo");
		jButtonExportarBaseDeDatos
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExportarBaseDeDatosActionPerformed(evt);
					}
				});

		jButtonImportarDBAccess.setText("Importar base de datos");
		jButtonImportarDBAccess
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonImportarDBAccessActionPerformed(evt);
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
																				jLabelBackupAutomaticoCadaXDias2))
														.addGroup(
																jPanelBackupLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				jPanelBackupLayout
																						.createSequentialGroup()
																						.addComponent(
																								jButtonExportarBaseDeDatos,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								249,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jButtonImportarDBAccess))
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
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
																												190,
																												Short.MAX_VALUE)
																										.addComponent(
																												jTextFieldRutaUbicacionBackupSecundario,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												190,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																								jPanelBackupLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING,
																												false)
																										.addComponent(
																												jButtonExaminarRutaBackupSecundario)
																										.addComponent(
																												jButtonExaminarRutaBackupPrincipal)))))
										.addContainerGap(13, Short.MAX_VALUE)));
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
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												7, Short.MAX_VALUE)
										.addGroup(
												jPanelBackupLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanelBackupLayout
																		.createSequentialGroup()
																		.addComponent(
																				jTextFieldRutaUbicacionBackupPrincipal,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanelBackupLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jCheckBoxHacerBackupSecundario)
																						.addComponent(
																								jTextFieldRutaUbicacionBackupSecundario,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanelBackupLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelUbicacionBackupPrincipal)
																		.addGap(
																				32,
																				32,
																				32))
														.addGroup(
																jPanelBackupLayout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonExaminarRutaBackupPrincipal)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButtonExaminarRutaBackupSecundario)))
										.addGap(15, 15, 15)
										.addGroup(
												jPanelBackupLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonExportarBaseDeDatos)
														.addComponent(
																jButtonImportarDBAccess))
										.addContainerGap(15, Short.MAX_VALUE)));

		jPanelImagenFondo.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Fondo",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jLabelRutaOrigenFondoAplicacion
				.setText("Color de Fondo de la aplicaci\u00f3n:");

		panelColor.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));

		javax.swing.GroupLayout panelColorLayout = new javax.swing.GroupLayout(
				panelColor);
		panelColor.setLayout(panelColorLayout);
		panelColorLayout.setHorizontalGroup(panelColorLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 59, Short.MAX_VALUE));
		panelColorLayout.setVerticalGroup(panelColorLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 21,
				Short.MAX_VALUE));

		javax.swing.GroupLayout jPanelImagenFondoLayout = new javax.swing.GroupLayout(
				jPanelImagenFondo);
		jPanelImagenFondo.setLayout(jPanelImagenFondoLayout);
		jPanelImagenFondoLayout
				.setHorizontalGroup(jPanelImagenFondoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelImagenFondoLayout
										.createSequentialGroup()
										.addGroup(
												jPanelImagenFondoLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelImagenFondoLayout
																		.createSequentialGroup()
																		.addGap(
																				7,
																				7,
																				7)
																		.addComponent(
																				jLabelRutaOrigenFondoAplicacion)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				panelColor,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanelImagenFondoLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jColorChooser,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				461,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanelImagenFondoLayout
				.setVerticalGroup(jPanelImagenFondoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelImagenFondoLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelImagenFondoLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																panelColor,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelRutaOrigenFondoAplicacion))
										.addGap(18, 18, 18)
										.addComponent(
												jColorChooser,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												229,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(101, Short.MAX_VALUE)));

		jPanelCompletitudDatosUsuario.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Completitud de datos de la persona",
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
										.addComponent(jCheckBoxExigirFotografia)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												72, Short.MAX_VALUE)
										.addComponent(
												jCheckBoxExigirFirmaDigital)
										.addContainerGap()));
		jPanelCompletitudDatosUsuarioLayout
				.setVerticalGroup(jPanelCompletitudDatosUsuarioLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelCompletitudDatosUsuarioLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jCheckBoxExigirFotografia)
										.addComponent(
												jCheckBoxExigirFirmaDigital)));

		jPanelImpresionLicencias.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Licencias",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jLabelEmailCentroImpresion
				.setText("E-mail del centro de impresi\u00f3n:");

		jTextFieldEmailCentroImpresion.setText("@");
		jTextFieldEmailCentroImpresion.setMaximumSize(new java.awt.Dimension(
				281, 22));

		jCheckBoxValidarRequisitos
				.setText("Validar requisitos al momento de generar nuevas licencias.");
		jCheckBoxValidarRequisitos
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxValidarRequisitosActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanelImpresionLicenciasLayout = new javax.swing.GroupLayout(
				jPanelImpresionLicencias);
		jPanelImpresionLicencias.setLayout(jPanelImpresionLicenciasLayout);
		jPanelImpresionLicenciasLayout
				.setHorizontalGroup(jPanelImpresionLicenciasLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelImpresionLicenciasLayout
										.createSequentialGroup()
										.addGroup(
												jPanelImpresionLicenciasLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelImpresionLicenciasLayout
																		.createSequentialGroup()
																		.addGap(
																				5,
																				5,
																				5)
																		.addComponent(
																				jLabelEmailCentroImpresion)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTextFieldEmailCentroImpresion,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				323,
																				Short.MAX_VALUE))
														.addComponent(
																jCheckBoxValidarRequisitos))
										.addContainerGap()));
		jPanelImpresionLicenciasLayout
				.setVerticalGroup(jPanelImpresionLicenciasLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelImpresionLicenciasLayout
										.createSequentialGroup()
										.addComponent(
												jCheckBoxValidarRequisitos)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelImpresionLicenciasLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelEmailCentroImpresion)
														.addComponent(
																jTextFieldEmailCentroImpresion,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		jPanelDocumento.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Documento",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jCheckBoxDocumentoPermitirSoloNumeros
				.setText("Permitir s\u00f3lo n\u00fameros.");

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
										.addComponent(
												jCheckBoxDocumentoPermitirSoloNumeros)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												139, Short.MAX_VALUE)
										.addComponent(
												jCheckBoxDocumentoCantidadCaracteresFija)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jSpinnerDocumentoCantidadCaracteresFija,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												39,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		jPanelDocumentoLayout
				.setVerticalGroup(jPanelDocumentoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDocumentoLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(
												jCheckBoxDocumentoPermitirSoloNumeros)
										.addComponent(
												jSpinnerDocumentoCantidadCaracteresFija,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												jCheckBoxDocumentoCantidadCaracteresFija)));

		jButtonGuardar.setMnemonic('G');
		jButtonGuardar.setText("Guardar");
		jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGuardarActionPerformed(evt);
			}
		});

		jButtonCancelar.setMnemonic('C');
		jButtonCancelar.setText("Cancelar");
		jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCancelarActionPerformed(evt);
			}
		});

		lbSinResultados.setForeground(new java.awt.Color(204, 0, 0));
		lbSinResultados.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbSinResultados.setText(Constantes.ERROR_SIN_RESULTADOS);

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
																jPanelBuscarActualizaciones,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelImpresionLicencias,
																0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lbSinResultados,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelFotos,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelSeguridad,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelDocumento,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelCompletitudDatosUsuario,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addComponent(
																				jPanelImagenFondo,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jPanelBackup,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonGuardar)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButtonCancelar)))
										.addContainerGap(12, Short.MAX_VALUE)));
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
														.addComponent(
																jPanelBackup,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelFotos,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																169,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanelSeguridad,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				82,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanelCompletitudDatosUsuario,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanelDocumento,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanelImpresionLicencias,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanelBuscarActualizaciones,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jPanelImagenFondo,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lbSinResultados,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jButtonGuardar)
														.addComponent(
																jButtonCancelar))
										.addGap(14, 14, 14)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jSpinnerTamanoFirmasMouseWheelMoved(
			java.awt.event.MouseWheelEvent evt) {
		int valorActualSpinner = new Integer(((Integer) jSpinnerTamanoFirmas
				.getValue()).intValue()
				- evt.getWheelRotation());

		if (valorActualSpinner > 0) {
			jSpinnerTamanoFirmas.setValue(new Integer(
					((Integer) jSpinnerTamanoFirmas.getValue()).intValue()
							- evt.getWheelRotation()));

		}
	}

	private void jCheckBoxValidarRequisitosActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButtonImportarDBAccessActionPerformed(
			java.awt.event.ActionEvent evt) {
		// int op =
		// JOptionPaneTesterGral.showInternal(Constantes.MENSAJE_IMPORTAR_DB_ACCESS,"Advertencia",
		// JOptionPane.QUESTION_MESSAGE);

		int op = JOptionPane.showConfirmDialog(getParent(),
				Constantes.MENSAJE_IMPORTAR_DB_ACCESS, "Advertencia",
				JOptionPane.YES_NO_OPTION);
		if (op == JOptionPane.YES_OPTION) {

			FileFilter fileFilter = new ExtensionFileFilter(
					"Base de datos de Microsoft Access (*.mdb)",
					new String[] { "MDB" });
			final VentanaExaminar ventanaExaminar = new VentanaExaminar(
					JFileChooser.FILES_ONLY, JFileChooser.OPEN_DIALOG,
					fileFilter);
			ventanaExaminar.pack();
			Util.agregarIframe(ventanaExaminar);
			ventanaExaminar.doModal(this.getRootPane());
			ventanaExaminar.setVisible(true);

			if (ventanaExaminar.getRutaSeleccionada() != null) {
				File archivoSeleccionado = new File(ventanaExaminar
						.getRutaSeleccionada());
				rutaOrigenArchivoAccess = archivoSeleccionado.getAbsolutePath();

				Util.mostrarPanelOperacionesLargas();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						GestorExportarDB gestorExportar = new GestorExportarDB();
						GestorExportarDB
								.setRutaDestinoBackup(rutaDestinoArchivoSQL);
						Thread threadExportar = new Thread(gestorExportar);
						threadExportar.run();

						DBImporter importadorAccess = new DBImporter();
						importadorAccess.importar(rutaOrigenArchivoAccess);

						Util.ocultarPanelOperacionesLargas();
						JOptionPaneTesterGral
								.showInternalMessageDialog(
										"La base de datos se importó satisfactoriamente",
										"Importar",
										JOptionPane.INFORMATION_MESSAGE);
					}

				});

			}
		}
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
		Util.mostrarError(lbSinResultados, "", true);
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
		AbstractColorChooserPanel[] colorPane = jColorChooser
				.getChooserPanels();
		AbstractColorChooserPanel[] colorPane1 = new AbstractColorChooserPanel[1];
		colorPane1[0] = colorPane[0];

		jColorChooser.setChooserPanels(colorPane1);
		jColorChooser.getPreviewPanel().setVisible(false);
		jColorChooser.getSelectionModel().addChangeListener(
				new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						Color newColor = jColorChooser.getColor();
						panelColor.setForeground(newColor);
						panelColor.setBackground(newColor);

					}

				});
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
			Util.mostrarPanelOperacionesLargas();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					GestorExportarDB gestorExportar = new GestorExportarDB();
					GestorExportarDB
							.setRutaDestinoBackup(rutaDestinoArchivoSQL);
					Thread threadExportar = new Thread(gestorExportar);
					threadExportar.run();

					Util.ocultarPanelOperacionesLargas();

					JOptionPaneTesterGral.showInternalMessageDialog(
							"La base de datos se exportó satisfactoriamente en "
									+ GestorExportarDB.getRutaDestinoBackup(),
							"Export", JOptionPane.INFORMATION_MESSAGE);
				}

			});
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
		if (isEmail(jTextFieldEmailCentroImpresion.getText())) {
			guardarValoresPanelActualizaciones();
			guardarValoresPanelCompletitudDatosDeUsuario();
			guardarValoresPanelDocumento();
			guardarValoresPanelFotos();
			guardarValoresPanelSeguridad();
			guardarValoresPanelBackup();
			guardarValoresPanelImagenDeFondo();
			guardarValoresPanelImpresionLicencias();

			Util.mostrarError(lbSinResultados, "", true);
			JOptionPaneTesterGral.showInternalMessageDialog(
					"Los cambios se guardaron correctamente",
					Constantes.MENSAJE_GUARDADO_TIT,
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			Util.mostrarError(lbSinResultados,
					"La dirección de correo electrónico no es válida.", false);
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
	private javax.swing.JButton jButtonBuscarActualizaciones;
	private javax.swing.JButton jButtonCancelar;
	private javax.swing.JButton jButtonExaminarRutaBackupPrincipal;
	private javax.swing.JButton jButtonExaminarRutaBackupSecundario;
	private javax.swing.JButton jButtonExaminarRutaOrigenFotos;
	private javax.swing.JButton jButtonExportarBaseDeDatos;
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JButton jButtonImportarDBAccess;
	private javax.swing.JCheckBox jCheckBoxBackupAutomaticoCadaXDias;
	private javax.swing.JCheckBox jCheckBoxDocumentoCantidadCaracteresFija;
	private javax.swing.JCheckBox jCheckBoxDocumentoPermitirSoloNumeros;
	private javax.swing.JCheckBox jCheckBoxExigirFirmaDigital;
	private javax.swing.JCheckBox jCheckBoxExigirFotografia;
	private javax.swing.JCheckBox jCheckBoxGuardarLogDeActividades;
	private javax.swing.JCheckBox jCheckBoxHacerBackupSecundario;
	private javax.swing.JCheckBox jCheckBoxValidarRequisitos;
	private javax.swing.JColorChooser jColorChooser;
	private javax.swing.JLabel jLabel1jLabelTamanoFotos3;
	private javax.swing.JLabel jLabelBackupAutomaticoCadaXDias2;
	private javax.swing.JLabel jLabelBuscarActualizacionesAlInicio;
	private javax.swing.JLabel jLabelEmailCentroImpresion;
	private javax.swing.JLabel jLabelGuardarLogXDias;
	private javax.swing.JLabel jLabelGuardarLogXDias2;
	private javax.swing.JLabel jLabelMostrarFotos;
	private javax.swing.JLabel jLabelRutaOrigenFondoAplicacion;
	private javax.swing.JLabel jLabelRutaOrigenFotos;
	private javax.swing.JLabel jLabelTamanoFotos2;
	private javax.swing.JLabel jLabelUbicacionBackupPrincipal;
	private javax.swing.JPanel jPanelBackup;
	private javax.swing.JPanel jPanelBuscarActualizaciones;
	private javax.swing.JPanel jPanelCompletitudDatosUsuario;
	private javax.swing.JPanel jPanelDocumento;
	private javax.swing.JPanel jPanelFotos;
	private javax.swing.JPanel jPanelImagenFondo;
	private javax.swing.JPanel jPanelImpresionLicencias;
	private javax.swing.JPanel jPanelOrigenDeFotos;
	private javax.swing.JPanel jPanelSeguridad;
	private javax.swing.JRadioButton jRadioButtonBuscarActualizacionesNo;
	private javax.swing.JRadioButton jRadioButtonBuscarActualizacionesSi;
	private javax.swing.JRadioButton jRadioButtonObtenerFotosDesdeCamaraWeb;
	private javax.swing.JRadioButton jRadioButtonObtenerFotosDesdeDisco;
	private javax.swing.JSpinner jSpinnerBackupAutomaticoCadaXDias;
	private javax.swing.JSpinner jSpinnerDocumentoCantidadCaracteresFija;
	private javax.swing.JSpinner jSpinnerGuardarLogXDias;
	private javax.swing.JSpinner jSpinnerTamanoFirmas;
	private javax.swing.JSpinner jSpinnerTamanoFotos;
	private javax.swing.JTextField jTextFieldEmailCentroImpresion;
	private javax.swing.JTextField jTextFieldRutaOrigenFotos;
	private javax.swing.JTextField jTextFieldRutaUbicacionBackupPrincipal;
	private javax.swing.JTextField jTextFieldRutaUbicacionBackupSecundario;
	private javax.swing.JLabel lbSinResultados;
	private javax.swing.JPanel panelColor;
	// End of variables declaration//GEN-END:variables

}