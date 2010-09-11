package frontend.ventanas;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class VentanaExaminar extends JInternalFrameTesterGral {
	String rutaSeleccionada;
	JFileChooser chooser = new JFileChooser();
	FileFilter fileFilter;

	public String getRutaSeleccionada() {
		return rutaSeleccionada;
	}

	private void setRutaSeleccionada(String rutaSeleccionada) {
		this.rutaSeleccionada = rutaSeleccionada;
	}

	public VentanaExaminar(int modoSeleccionArchivoODirectorio, int dialogType,
			FileFilter fileFilter) {

		this(modoSeleccionArchivoODirectorio, dialogType);
		chooser.setFileFilter(fileFilter);

	}

	public VentanaExaminar(int modoSeleccionArchivoODirectorio, int dialogType) {

		super("Seleccionar...", false, false, false, false);

		chooser = new JFileChooser();
		chooser.setDialogType(dialogType);
		this.getContentPane().add(chooser);
		chooser.setFileSelectionMode(modoSeleccionArchivoODirectorio);

		// chooser.addChoosableFileFilter(new ImageFilter());
		chooser.setMultiSelectionEnabled(false);

		// ImagePreviewPanel preview = new ImagePreviewPanel();
		// chooser.setAccessory(preview);
		// chooser.addPropertyChangeListener(preview);
		chooser.setVisible(true);
		// this.panelContenido.setMinimumSize(chooser.getPreferredSize());
		// this.panelContenido.setMaximumSize(chooser.getPreferredSize());
		// this.panelContenido.setSize(chooser.getPreferredSize());
		// this.panelContenido.setPreferredSize(chooser.getPreferredSize());
		this.getContentPane().validate();
		this.pack();

		chooser.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				JFileChooser chooser = (JFileChooser) evt.getSource();
				if (JFileChooser.APPROVE_SELECTION.equals(evt
						.getActionCommand())) {
					File f = chooser.getSelectedFile();
					setRutaSeleccionada(f.getAbsolutePath());
					// ImageIcon img = new ImageIcon(f.getAbsolutePath());
					VentanaExaminar.this.close();
					VentanaExaminar.this.dispose();

				} else if (JFileChooser.CANCEL_SELECTION.equals(evt
						.getActionCommand())) {
					VentanaExaminar.this.close();
					VentanaExaminar.this.dispose();

				}
			}
		});
	}

	public VentanaExaminar(int modoSeleccionArchivoODirectorio, int dialogType,
			String ubicacion) {
		chooser = new JFileChooser(ubicacion);
		chooser.setDialogType(dialogType);
		this.getContentPane().add(chooser);
		chooser.setFileSelectionMode(modoSeleccionArchivoODirectorio);

		// chooser.addChoosableFileFilter(new ImageFilter());
		chooser.setMultiSelectionEnabled(false);

		// ImagePreviewPanel preview = new ImagePreviewPanel();
		// chooser.setAccessory(preview);
		// chooser.addPropertyChangeListener(preview);
		chooser.setVisible(true);
		// this.panelContenido.setMinimumSize(chooser.getPreferredSize());
		// this.panelContenido.setMaximumSize(chooser.getPreferredSize());
		// this.panelContenido.setSize(chooser.getPreferredSize());
		// this.panelContenido.setPreferredSize(chooser.getPreferredSize());
		this.getContentPane().validate();
		this.pack();

		chooser.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				JFileChooser chooser = (JFileChooser) evt.getSource();
				if (JFileChooser.APPROVE_SELECTION.equals(evt
						.getActionCommand())) {
					File f = chooser.getSelectedFile();
					setRutaSeleccionada(f.getAbsolutePath());
					// ImageIcon img = new ImageIcon(f.getAbsolutePath());
					VentanaExaminar.this.close();
					VentanaExaminar.this.dispose();

				} else if (JFileChooser.CANCEL_SELECTION.equals(evt
						.getActionCommand())) {
					VentanaExaminar.this.close();
					VentanaExaminar.this.dispose();

				}
			}
		});
	}
	
	
	public VentanaExaminar(int modoSeleccionArchivoODirectorio, int dialogType,
			String ubicacion,FileFilter filefilter) {
		chooser = new JFileChooser(ubicacion);
		chooser.setFileFilter(filefilter);
		chooser.setDialogType(dialogType);
		this.getContentPane().add(chooser);
		chooser.setFileSelectionMode(modoSeleccionArchivoODirectorio);

		// chooser.addChoosableFileFilter(new ImageFilter());
		chooser.setMultiSelectionEnabled(false);

		// ImagePreviewPanel preview = new ImagePreviewPanel();
		// chooser.setAccessory(preview);
		// chooser.addPropertyChangeListener(preview);
		chooser.setVisible(true);
		// this.panelContenido.setMinimumSize(chooser.getPreferredSize());
		// this.panelContenido.setMaximumSize(chooser.getPreferredSize());
		// this.panelContenido.setSize(chooser.getPreferredSize());
		// this.panelContenido.setPreferredSize(chooser.getPreferredSize());
		this.getContentPane().validate();
		this.pack();

		chooser.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				JFileChooser chooser = (JFileChooser) evt.getSource();
				if (JFileChooser.APPROVE_SELECTION.equals(evt
						.getActionCommand())) {
					File f = chooser.getSelectedFile();
					setRutaSeleccionada(f.getAbsolutePath());
					// ImageIcon img = new ImageIcon(f.getAbsolutePath());
					VentanaExaminar.this.close();
					VentanaExaminar.this.dispose();

				} else if (JFileChooser.CANCEL_SELECTION.equals(evt
						.getActionCommand())) {
					VentanaExaminar.this.close();
					VentanaExaminar.this.dispose();

				}
			}
		});
	}

}
