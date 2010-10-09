package actualizaciones;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * 
 * @author __USER__
 */
public class JDialogActualizar extends javax.swing.JDialog implements Runnable {

	/** Creates new form JDialogActualizar */
	public JDialogActualizar(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		try {
			this.setUndecorated(true);
			initComponents();
			this.setLocationRelativeTo(this);

			gestorActualizaciones = new GestorActualizaciones();
			gestorActualizaciones.start();

			Thread th = new Thread(this);
			th.start();
			jProgressBarProgresoActualizacion.setStringPainted(true);
			jProgressBarProgresoActualizacion.setForeground(Color.blue);
			jProgressBarProgresoActualizacion.setBackground(Color.white);
		} catch (RuntimeException ex) {
			GestorActualizaciones.setException(ex, "Error");
		} catch (Exception exc) {
			// log.error(exc.getMessage(), exc);
			System.exit(1);
		}

	}

	public void run() {

		while (true) {
			try {

				/*
				 * Cuando se sabe cuántos archivos se van a descargar del
				 * servidor, se establece el valor máximo de la barra de
				 * progreso.
				 */
				if (GestorActualizacionesUtil.getCantidadTotalArchivos() != 0
						&& jProgressBarProgresoActualizacion.getMaximum() != GestorActualizacionesUtil
								.getCantidadTotalArchivos()) {
					jProgressBarProgresoActualizacion
							.setMaximum(GestorActualizacionesUtil
									.getCantidadTotalArchivos());
				}

				if (!GestorActualizaciones.getUrlFTPActual().isEmpty()) {
					jLabelValorUrlServidorSeleccionado
							.setText(GestorActualizaciones.getUrlFTPActual());
				} else {
					jLabelValorUrlServidorSeleccionado.setText(" - ");
				}

				if (!GestorActualizaciones.getNombreUsuarioFTPActual()
						.isEmpty()) {
					jLabelValorNombreUsuarioEnServidor
							.setText(GestorActualizaciones
									.getNombreUsuarioFTPActual());
				} else {
					jLabelValorNombreUsuarioEnServidor.setText(" - ");
				}

				jLabelValorNombreArchivoActual
						.setText(GestorActualizacionesUtil
								.getNombreArchivoActualDescargando());

				/*
				 * Se actualiza el valor de la barra de progreso. Si se avanzó a
				 * otro archivo, la barra avanza.
				 */
				jProgressBarProgresoActualizacion
						.setValue(GestorActualizacionesUtil
								.getCantidadArchivosDescargando());

				jLabelValorDescargandoArchivoNumeroX
						.setText(GestorActualizacionesUtil
								.getCantidadArchivosDescargando()
								+ " de "
								+ GestorActualizacionesUtil
										.getCantidadTotalArchivos());

				if (!gestorActualizaciones.isAlive()) {
					if (GestorActualizaciones.getException() == null) {
						jProgressBarProgresoActualizacion.setVisible(false);
						jPanelBotones.setVisible(false);
						jLabelSeEstaActualizando
								.setText("Se ha actualizado correctamente a la última versión.");

						jLabelGifAnimado.setIcon(new javax.swing.ImageIcon(
								getClass().getResource("/imagenes/check.gif")));

					} else {

						System.out
								.println(GestorActualizaciones.getException());
						jLabelSeEstaActualizando.setText(GestorActualizaciones
								.getMensajeExcepcionParaElUsuario());
						jLabelGifAnimado.setIcon(new javax.swing.ImageIcon(
								getClass().getResource("/imagenes/error.gif")));
					}

					Thread.sleep(3500);// Espera 3.5 segundos, mostrando el
					// resultado de la actualización.
					try {
						Runtime.getRuntime().exec(
								GestorActualizacionesUtil.getNombreAplicacion()
										+ ".exe");
					} catch (IOException ex) {
						// Logger.getLogger(JDialogActualizar.class.getName()).log(Level.SEVERE,
						// null, ex);
						jButtonSalir.setText("Salir");
					}

					System.exit(0);
				}
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Logger.getLogger(JDialogActualizar.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanelDetallesActualizacion = new javax.swing.JPanel();
		jLabelServidorSeleccionado = new javax.swing.JLabel();
		jLabelValorUrlServidorSeleccionado = new javax.swing.JLabel();
		jLabelNombreUsuarioEnServidor = new javax.swing.JLabel();
		jLabelValorNombreUsuarioEnServidor = new javax.swing.JLabel();
		jPanelPanelActualizacion = new javax.swing.JPanel();
		jLabelSeEstaActualizando = new javax.swing.JLabel();
		jProgressBarProgresoActualizacion = new javax.swing.JProgressBar();
		jLabelGifAnimado = new javax.swing.JLabel();
		jLabelTitulo = new javax.swing.JLabel();
		jPanelStatusDescarga = new javax.swing.JPanel();
		jLabelDescargandoArchivoNumeroX = new javax.swing.JLabel();
		jLabelValorDescargandoArchivoNumeroX = new javax.swing.JLabel();
		jLabelNombreArchivoActual = new javax.swing.JLabel();
		jLabelValorNombreArchivoActual = new javax.swing.JLabel();
		jPanelBotones = new javax.swing.JPanel();
		jButtonSalir = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanelDetallesActualizacion.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Detalle de conexi\u00f3n"));

		jLabelServidorSeleccionado.setText("Servidor actual: ");

		jLabelValorUrlServidorSeleccionado.setText("-");

		jLabelNombreUsuarioEnServidor.setText("Nombre de usuario en servidor:");

		jLabelValorNombreUsuarioEnServidor.setText("-");

		javax.swing.GroupLayout jPanelDetallesActualizacionLayout = new javax.swing.GroupLayout(
				jPanelDetallesActualizacion);
		jPanelDetallesActualizacion
				.setLayout(jPanelDetallesActualizacionLayout);
		jPanelDetallesActualizacionLayout
				.setHorizontalGroup(jPanelDetallesActualizacionLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDetallesActualizacionLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelDetallesActualizacionLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelNombreUsuarioEnServidor)
														.addComponent(
																jLabelServidorSeleccionado))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelDetallesActualizacionLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelValorUrlServidorSeleccionado,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																387,
																Short.MAX_VALUE)
														.addComponent(
																jLabelValorNombreUsuarioEnServidor,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																387,
																Short.MAX_VALUE))
										.addContainerGap()));
		jPanelDetallesActualizacionLayout
				.setVerticalGroup(jPanelDetallesActualizacionLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDetallesActualizacionLayout
										.createSequentialGroup()
										.addGroup(
												jPanelDetallesActualizacionLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelServidorSeleccionado)
														.addComponent(
																jLabelValorUrlServidorSeleccionado))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelDetallesActualizacionLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelNombreUsuarioEnServidor)
														.addComponent(
																jLabelValorNombreUsuarioEnServidor))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jLabelSeEstaActualizando
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelSeEstaActualizando
				.setText("Se est\u00e1 actualizando el programa...");

		jProgressBarProgresoActualizacion.setForeground(new java.awt.Color(158,
				215, 215));

		jLabelGifAnimado
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		javax.swing.GroupLayout jPanelPanelActualizacionLayout = new javax.swing.GroupLayout(
				jPanelPanelActualizacion);
		jPanelPanelActualizacion.setLayout(jPanelPanelActualizacionLayout);
		jPanelPanelActualizacionLayout
				.setHorizontalGroup(jPanelPanelActualizacionLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelPanelActualizacionLayout
										.createSequentialGroup()
										.addGroup(
												jPanelPanelActualizacionLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelPanelActualizacionLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabelSeEstaActualizando,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				505,
																				Short.MAX_VALUE))
														.addGroup(
																jPanelPanelActualizacionLayout
																		.createSequentialGroup()
																		.addGap(
																				35,
																				35,
																				35)
																		.addComponent(
																				jProgressBarProgresoActualizacion,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				482,
																				Short.MAX_VALUE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelGifAnimado,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												26,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanelPanelActualizacionLayout
				.setVerticalGroup(jPanelPanelActualizacionLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelPanelActualizacionLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelPanelActualizacionLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jLabelGifAnimado,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																29,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanelPanelActualizacionLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelSeEstaActualizando)
																		.addGap(
																				20,
																				20,
																				20)
																		.addComponent(
																				jProgressBarProgresoActualizacion,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				22,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(59, 59, 59)));

		jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabelTitulo.setText("Actualizaci\u00f3n de software");

		jPanelStatusDescarga.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Infomaci\u00f3n de la descarga"));

		jLabelDescargandoArchivoNumeroX.setText("Descargando archivo: ");

		jLabelValorDescargandoArchivoNumeroX.setText("-");

		jLabelNombreArchivoActual.setText("Archivo actual:");

		jLabelValorNombreArchivoActual.setText("-");

		javax.swing.GroupLayout jPanelStatusDescargaLayout = new javax.swing.GroupLayout(
				jPanelStatusDescarga);
		jPanelStatusDescarga.setLayout(jPanelStatusDescargaLayout);
		jPanelStatusDescargaLayout
				.setHorizontalGroup(jPanelStatusDescargaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelStatusDescargaLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelStatusDescargaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelDescargandoArchivoNumeroX)
														.addComponent(
																jLabelNombreArchivoActual))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelStatusDescargaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelValorNombreArchivoActual,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																439,
																Short.MAX_VALUE)
														.addComponent(
																jLabelValorDescargandoArchivoNumeroX,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																439,
																Short.MAX_VALUE))
										.addContainerGap()));
		jPanelStatusDescargaLayout
				.setVerticalGroup(jPanelStatusDescargaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelStatusDescargaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelStatusDescargaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelDescargandoArchivoNumeroX)
														.addComponent(
																jLabelValorDescargandoArchivoNumeroX))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelStatusDescargaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelNombreArchivoActual)
														.addComponent(
																jLabelValorNombreArchivoActual))));

		jButtonSalir.setText("Cancelar actualizaci\u00f3n");
		jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonSalirActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(
				jPanelBotones);
		jPanelBotones.setLayout(jPanelBotonesLayout);
		jPanelBotonesLayout.setHorizontalGroup(jPanelBotonesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jButtonSalir));
		jPanelBotonesLayout.setVerticalGroup(jPanelBotonesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jButtonSalir));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
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
																		.addGap(
																				56,
																				56,
																				56)
																		.addComponent(
																				jPanelPanelActualizacion,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jLabelTitulo)
														.addComponent(
																jPanelStatusDescarga,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelDetallesActualizacion,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
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
										.addComponent(jLabelTitulo)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelPanelActualizacion,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												76,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												jPanelStatusDescarga,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelDetallesActualizacion,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelBotones,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {

		if (gestorActualizaciones.isAlive()) {
			int seleccion = JOptionPane.showConfirmDialog(this,
					"¿Desea cancelar la actualización?",
					"Confirmar cancelación", JOptionPane.YES_NO_OPTION);

			if (seleccion == JOptionPane.YES_OPTION) {
				System.exit(0);
			}

		} else {
			System.exit(0);
		}
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonSalir;
	private javax.swing.JLabel jLabelDescargandoArchivoNumeroX;
	private javax.swing.JLabel jLabelGifAnimado;
	private javax.swing.JLabel jLabelNombreArchivoActual;
	private javax.swing.JLabel jLabelNombreUsuarioEnServidor;
	private javax.swing.JLabel jLabelSeEstaActualizando;
	private javax.swing.JLabel jLabelServidorSeleccionado;
	private javax.swing.JLabel jLabelTitulo;
	private javax.swing.JLabel jLabelValorDescargandoArchivoNumeroX;
	private javax.swing.JLabel jLabelValorNombreArchivoActual;
	private javax.swing.JLabel jLabelValorNombreUsuarioEnServidor;
	private javax.swing.JLabel jLabelValorUrlServidorSeleccionado;
	private javax.swing.JPanel jPanelBotones;
	private javax.swing.JPanel jPanelDetallesActualizacion;
	private javax.swing.JPanel jPanelPanelActualizacion;
	private javax.swing.JPanel jPanelStatusDescarga;
	private javax.swing.JProgressBar jProgressBarProgresoActualizacion;
	// End of variables declaration//GEN-END:variables
	private GestorActualizaciones gestorActualizaciones;
}