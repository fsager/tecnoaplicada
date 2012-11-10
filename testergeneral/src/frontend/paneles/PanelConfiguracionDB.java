package frontend.paneles;

import java.io.File;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import testerGeneral.business.ContextManager;
import testerGeneral.db.ConexionManagerTesterGeneral;
import testerGeneral.domain.Constantes;
import testerGeneral.persistence.backup.GestorDBBackup;
import testerGeneral.seguridad.Encriptadora;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelConfiguracionDB extends javax.swing.JPanel {

	public String aplicacion;
	public boolean externo;
	
	/** Creates new form PanelConfiguracionDB */
	public PanelConfiguracionDB(String aplicacion,boolean externo) {
		initComponents();
		this.aplicacion=aplicacion;
		this.externo=externo;

		jTextFieldContrasena.setText(aplicacion);
		jTextFieldNombreDeUsuario.setText(aplicacion);
		jTextFieldNombreEquipo.setText("localhost");
		jFormattedTextFieldPuerto.setText("1527");
		cargarDatos();
		//jTextFieldRutaEnDiscoRemoto.setText("C:\\programacion\\Workspaces3\\Autoimpresor\\db\\autoimpresor");
	}

	public void cargarDatos() {
		File archivoDeConexionDBRemota = new File(System
				.getProperty("user.dir")
				+ File.separator + "db_param.cfg");
		if (archivoDeConexionDBRemota.exists()
				&& archivoDeConexionDBRemota.isFile()
				&& archivoDeConexionDBRemota.length() > 0) {
			jRadioButtonConexionRemota.setSelected(true);

			String[] arrayParametrosDB = GestorDBBackup
					.cargarParametrosDeActualizacionDesdeArchivo(new File(
							System.getProperty("user.dir")));

			jTextFieldNombreEquipo.setText(arrayParametrosDB[0]);
			jFormattedTextFieldPuerto.setText(arrayParametrosDB[1]);
			jTextFieldNombreDeUsuario.setText(arrayParametrosDB[2]);
			jTextFieldContrasena.setText(arrayParametrosDB[3]);
			jTextFieldRutaEnDiscoRemoto.setText(arrayParametrosDB[4]);
		} else {
			jRadioButtonConexionLocal.setSelected(true);
			jTextFieldContrasena.setEnabled(false);
			jTextFieldNombreDeUsuario.setEnabled(false);
			jTextFieldNombreEquipo.setEnabled(false);
			jFormattedTextFieldPuerto.setEnabled(false);
			jTextFieldRutaEnDiscoRemoto.setEnabled(false);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroupTipoConexionDB = new javax.swing.ButtonGroup();
		jPanelParametrosDBRemota = new javax.swing.JPanel();
		jLabelNombreEquipo = new javax.swing.JLabel();
		jTextFieldNombreEquipo = new javax.swing.JTextField();
		jLabelNumeroPuerto = new javax.swing.JLabel();
		jLabelUsuario = new javax.swing.JLabel();
		jTextFieldNombreDeUsuario = new javax.swing.JTextField();
		jLabelContrasena = new javax.swing.JLabel();
		jLabelRutaEnDiscoRemoto = new javax.swing.JLabel();
		jTextFieldRutaEnDiscoRemoto = new javax.swing.JTextField();
		jFormattedTextFieldPuerto = new javax.swing.JFormattedTextField();
		jTextFieldContrasena = new javax.swing.JPasswordField();
		jPanelTipoConexion = new javax.swing.JPanel();
		jRadioButtonConexionLocal = new javax.swing.JRadioButton();
		jRadioButtonConexionRemota = new javax.swing.JRadioButton();
		jButtonGuardar = new javax.swing.JButton();

		jPanelParametrosDBRemota
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder("Par\u00e1metros de configuraci\u00f3n de la Base de Datos Remota"));

		jLabelNombreEquipo.setText("Direcci\u00f3n IP o nombre de equipo: ");

		jTextFieldNombreEquipo.setText("localhost");

		jLabelNumeroPuerto.setText("Puerto:");

		jLabelUsuario.setText("Usuario:");

		jTextFieldNombreDeUsuario.setText("autoimpresor");

		jLabelContrasena.setText("Contrase\u00f1a:");

		jLabelRutaEnDiscoRemoto.setText("Ruta de acceso en disco remoto:");

		jFormattedTextFieldPuerto
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedTextFieldPuerto.setText("1527");

		jTextFieldContrasena.setText("autoimpresor");

		javax.swing.GroupLayout jPanelParametrosDBRemotaLayout = new javax.swing.GroupLayout(
				jPanelParametrosDBRemota);
		jPanelParametrosDBRemota.setLayout(jPanelParametrosDBRemotaLayout);
		jPanelParametrosDBRemotaLayout
				.setHorizontalGroup(jPanelParametrosDBRemotaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelParametrosDBRemotaLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelParametrosDBRemotaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelNombreEquipo)
														.addComponent(
																jLabelNumeroPuerto)
														.addComponent(
																jLabelUsuario)
														.addComponent(
																jLabelContrasena)
														.addComponent(
																jLabelRutaEnDiscoRemoto))
										.addGap(25, 25, 25)
										.addGroup(
												jPanelParametrosDBRemotaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jTextFieldRutaEnDiscoRemoto,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																304,
																Short.MAX_VALUE)
														.addComponent(
																jTextFieldNombreEquipo,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																304,
																Short.MAX_VALUE)
														.addComponent(
																jTextFieldNombreDeUsuario,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																304,
																Short.MAX_VALUE)
														.addComponent(
																jFormattedTextFieldPuerto,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																100,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jTextFieldContrasena,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																304,
																Short.MAX_VALUE))
										.addContainerGap()));
		jPanelParametrosDBRemotaLayout
				.setVerticalGroup(jPanelParametrosDBRemotaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelParametrosDBRemotaLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanelParametrosDBRemotaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelNombreEquipo)
														.addComponent(
																jTextFieldNombreEquipo,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelParametrosDBRemotaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelNumeroPuerto)
														.addComponent(
																jFormattedTextFieldPuerto,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelParametrosDBRemotaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelUsuario)
														.addComponent(
																jTextFieldNombreDeUsuario,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelParametrosDBRemotaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelContrasena)
														.addComponent(
																jTextFieldContrasena,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelParametrosDBRemotaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelRutaEnDiscoRemoto)
														.addComponent(
																jTextFieldRutaEnDiscoRemoto,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		jPanelTipoConexion
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder("Tipo de conexi\u00f3n a la Base de Datos"));

		buttonGroupTipoConexionDB.add(jRadioButtonConexionLocal);
		jRadioButtonConexionLocal.setText("Local");
		jRadioButtonConexionLocal
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonConexionLocalActionPerformed(evt);
					}
				});

		buttonGroupTipoConexionDB.add(jRadioButtonConexionRemota);
		jRadioButtonConexionRemota.setText("Remota");
		jRadioButtonConexionRemota
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonConexionRemotaActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanelTipoConexionLayout = new javax.swing.GroupLayout(
				jPanelTipoConexion);
		jPanelTipoConexion.setLayout(jPanelTipoConexionLayout);
		jPanelTipoConexionLayout.setHorizontalGroup(jPanelTipoConexionLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanelTipoConexionLayout.createSequentialGroup()
								.addContainerGap().addComponent(
										jRadioButtonConexionLocal).addGap(18,
										18, 18).addComponent(
										jRadioButtonConexionRemota)
								.addContainerGap(387, Short.MAX_VALUE)));
		jPanelTipoConexionLayout
				.setVerticalGroup(jPanelTipoConexionLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelTipoConexionLayout
										.createSequentialGroup()
										.addGroup(
												jPanelTipoConexionLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButtonConexionLocal)
														.addComponent(
																jRadioButtonConexionRemota))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jButtonGuardar.setText("Guardar");
		jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGuardarActionPerformed(evt);
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
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addContainerGap(
																				470,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButtonGuardar))
														.addComponent(
																jPanelParametrosDBRemota,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelTipoConexion,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												jPanelTipoConexion,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelParametrosDBRemota,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButtonGuardar)
										.addContainerGap(17, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		//String nombreAplicacion=ContextManager.getProperty("SISTEMA.NOMBRE.PROGRAMA");
		
		File archivoDeConexionDBRemota = new File(System
				.getProperty("user.dir")
				+ File.separator + "db_param.cfg");

		if (jRadioButtonConexionRemota.isSelected()) {

			int puerto;
			try {
				puerto = Integer.valueOf(jFormattedTextFieldPuerto.getText());
			} catch (NumberFormatException ex) {
				puerto = 1527;
			}

			SecretKeySpec clavePrivada = new SecretKeySpec(new String(
					"czbmrndoritlekaz").getBytes(), "AES");
			Encriptadora encriptador = new Encriptadora("AES", clavePrivada);
			String encriptPass = encriptador.encriptar(new String(
					jTextFieldContrasena.getPassword()));

			GestorDBBackup.generarArchivoDeConexionDBRemota(
					jTextFieldNombreEquipo.getText(), puerto,
					jTextFieldNombreDeUsuario.getText(), encriptPass,
					jTextFieldRutaEnDiscoRemoto.getText());

			/*try {
				datasource.destroy();
				datasource.getConnection();
				JOptionPaneTesterGral.showInternalMessageDialog(
						"Se ha establecido la conexión satisfactoriamente",
						"Conexión", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				if(archivoDeConexionDBRemota.exists())
					archivoDeConexionDBRemota.delete();
				try
				{
					datasource.getConnection();
				}
				catch(Exception ex)
				{
					throw new RuntimeException(ex);
				}

				JOptionPaneTesterGral
						.showInternalMessageDialog(
								"No se ha establecido la conexión. Revise la configuración.",
								"Conexión", JOptionPane.ERROR_MESSAGE);
			}*/

		} else {

			archivoDeConexionDBRemota.delete();
			/*try {
				datasource.getConnection();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}*/
		}
		cerrar(aplicacion);
	}

	public void cerrar(String nombreAplicacion){
		try
		{
			int op=-2;
			if(!externo)
			{
				op = JOptionPaneTesterGral.showInternal("<HTML>Para que los cambios tengan efecto, debe reiniciar la aplicación.<BR>¿Desea reiniciar?</HTML>",
					Constantes.MENSAJE_SALIR_TIT, JOptionPane.QUESTION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "<HTML>Para que los cambios tengan efecto, debe reiniciar la aplicación.</HTML>",
					Constantes.MENSAJE_SALIR_TIT,JOptionPane.INFORMATION_MESSAGE);
				op = JOptionPane.YES_OPTION;
			}
			

			if (op == JOptionPane.YES_OPTION) {

				if(!externo)
					Util.frameContenedor.dispose();
				
				Runtime.getRuntime().exec(nombreAplicacion+".exe");
				System.exit(0);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private void jRadioButtonConexionLocalActionPerformed(
			java.awt.event.ActionEvent evt) {
		jTextFieldContrasena.setEnabled(false);
		jTextFieldNombreDeUsuario.setEnabled(false);
		jTextFieldNombreEquipo.setEnabled(false);
		jFormattedTextFieldPuerto.setEnabled(false);
		jTextFieldRutaEnDiscoRemoto.setEnabled(false);
	}

	private void jRadioButtonConexionRemotaActionPerformed(
			java.awt.event.ActionEvent evt) {
		jTextFieldContrasena.setEnabled(true);
		jTextFieldNombreDeUsuario.setEnabled(true);
		jTextFieldNombreEquipo.setEnabled(true);
		jFormattedTextFieldPuerto.setEnabled(true);
		jTextFieldRutaEnDiscoRemoto.setEnabled(true);

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroupTipoConexionDB;
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JFormattedTextField jFormattedTextFieldPuerto;
	private javax.swing.JLabel jLabelContrasena;
	private javax.swing.JLabel jLabelNombreEquipo;
	private javax.swing.JLabel jLabelNumeroPuerto;
	private javax.swing.JLabel jLabelRutaEnDiscoRemoto;
	private javax.swing.JLabel jLabelUsuario;
	private javax.swing.JPanel jPanelParametrosDBRemota;
	private javax.swing.JPanel jPanelTipoConexion;
	private javax.swing.JRadioButton jRadioButtonConexionLocal;
	private javax.swing.JRadioButton jRadioButtonConexionRemota;
	private javax.swing.JPasswordField jTextFieldContrasena;
	private javax.swing.JTextField jTextFieldNombreDeUsuario;
	private javax.swing.JTextField jTextFieldNombreEquipo;
	private javax.swing.JTextField jTextFieldRutaEnDiscoRemoto;
	// End of variables declaration//GEN-END:variables

}