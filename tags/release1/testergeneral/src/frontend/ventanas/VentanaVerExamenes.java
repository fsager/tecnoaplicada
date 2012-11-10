/*
 * VentanaVerExamenes.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JasperRunManager;
import testerGeneral.business.ContextManager;
import testerGeneral.comparetors.DateComparator;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Examen;
import testerGeneral.domain.Persona;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.service.PersonaExamenDefinition;
import testerGeneral.service.PersonaRestricionDefinition;
import testerGeneral.service.ResultadoDetalleExamenDefinition;
import frontend.components.JOptionPaneTesterGral;
import frontend.tablemodel.TableModelPersonaExamen;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class VentanaVerExamenes extends JInternalFrameTesterGral {

	/** Creates new form VentanaVerExamenes */
	public VentanaVerExamenes(Persona per) {
		super("Ver examenes", false, true, false, false);
		this.per = per;

		initComponents();
		cargarExamenes();
	}

	public void cargarExamenes() {
		try {
			PersonaExamen perExamen = new PersonaExamen();
			perExamen.setPersona(per);
			List<PersonaExamen> personaExamenes = personaExamenService
					.getAll(perExamen);
			setTableModel(personaExamenes);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void setTableModel(List lst) {
		TableModelPersonaExamen tableModel = new TableModelPersonaExamen();
		tableModel.setLst(lst);

		TableRowSorter sorter = new TableRowSorter(tableModel);
		sorter.setComparator(1, com);
		//tablePersonaExamen.setColumnSelectionAllowed(true); 
		tablePersonaExamen.setRowSelectionAllowed(true); 
		tablePersonaExamen.setRowSorter(sorter);

		tablePersonaExamen.setModel(tableModel);
		tablePersonaExamen
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePersonaExamen.setAutoCreateRowSorter(true);
		
		SelectionListener listener = new SelectionListener(tablePersonaExamen);
		tablePersonaExamen.getSelectionModel().addListSelectionListener(listener);
		
		if(lst.size()>0)
			tablePersonaExamen.setRowSelectionInterval(0,0);
			

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tablePersonaExamen = new javax.swing.JTable();
		btnAdjunto = new javax.swing.JButton();
		btnDetalle = new javax.swing.JButton();

		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameActivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosed(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosing(
					javax.swing.event.InternalFrameEvent evt) {
				formInternalFrameClosing(evt);
			}

			public void internalFrameDeactivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameDeiconified(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameIconified(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameOpened(
					javax.swing.event.InternalFrameEvent evt) {
			}
		});

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Examenes",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel1.setFocusable(false);

		tablePersonaExamen.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { "fsager", "No" }, { "vpaolini", "Si" },
						{ "jtesta", "Si" }, { null, null } }, new String[] {
						"Nombre usuario", "Habilitado" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tablePersonaExamen.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tablePersonaExamenMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tablePersonaExamen);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 594,
								Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(
						jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE,
						282, Short.MAX_VALUE).addContainerGap()));

		btnAdjunto.setText("Ver adjunto");
		btnAdjunto.setEnabled(false);
		btnAdjunto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdjuntoActionPerformed(evt);
			}
		});

		btnDetalle.setText("Ver Detalle");
		btnDetalle.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDetalleActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addGap(430, 430, 430)
										.addComponent(
												btnDetalle,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												100,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnAdjunto)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnAdjunto,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnDetalle,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void tablePersonaExamenMouseClicked(java.awt.event.MouseEvent evt) {
		PersonaExamen perExa = getPersonaExamenFromTable();
		if(perExa.getPexaNombreAdjunto()!=null)
			btnAdjunto.setEnabled(true);
		else
			btnAdjunto.setEnabled(false);
	}

	private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {
		try
		{
			PersonaRestricionDefinition personaRestriccionService=(PersonaRestricionDefinition)ContextManager.getBizObject("personaRestricionService");
			
			HashMap parameterMap = new HashMap();
			PersonaExamen perExa = getPersonaExamenFromTable();
			parameterMap.put("p_pexa_id", perExa.getPexaId());
			parameterMap.put("otrasAflicciones",personaRestriccionService.getOtrasAflicciones(perExa.getPersona()));
			parameterMap.put("SUBREPORT_DIR",new File("./reportes").getCanonicalPath()+File.separator);
			
			final byte[] buf = JasperRunManager.runReportToPdf(Constantes.RPT_PERSONA_EXAMEN, parameterMap, ContextManager.getCurrentConnection());
			
			String file=System.getProperty("java.io.tmpdir")+System.currentTimeMillis()+".pdf";
			testerGeneral.persistence.impl.Util.toFile(file,buf);
			
			Process p = Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler "
							+ file);
		
		}
		catch (Exception e) {
			JOptionPaneTesterGral.showInternalMessageDialog(this,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnAdjuntoActionPerformed(java.awt.event.ActionEvent evt) {
			try {
				PersonaExamen perExa = getPersonaExamenFromTable();

				String file=System.getProperty("java.io.tmpdir")+perExa.getPexaNombreAdjunto();
				testerGeneral.persistence.impl.Util.toFile(file,perExa.getPexaAdj());

				Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ file);
				
			} catch (Exception e) {
				JOptionPaneTesterGral.showInternalMessageDialog(this,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
			}
	}

	public PersonaExamen getPersonaExamenFromTable() {
		int sel = tablePersonaExamen.getSelectedRow();
		TableModelPersonaExamen model = (TableModelPersonaExamen) tablePersonaExamen
				.getModel();
		PersonaExamen perExa = model.getValueAt(sel);
		return perExa;
	}

	private void formInternalFrameClosing(
			javax.swing.event.InternalFrameEvent evt) {
		cerrar();
	}

	public void cerrar() {
		close();
		this.dispose();
	}
	
	public class SelectionListener implements ListSelectionListener {
	    JTable table;

	    // It is necessary to keep the table since it is not possible
	    // to determine the table from the event's source
	    SelectionListener(JTable table) {
	        this.table = table;
	    }
	    public void valueChanged(ListSelectionEvent e) {
	    	int sel = tablePersonaExamen.getSelectedRow();
	    	if(sel!=-1)
	    		tablePersonaExamenMouseClicked(null);
	    	
	    	
	    }
	}


	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnAdjunto;
	private javax.swing.JButton btnDetalle;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tablePersonaExamen;
	// End of variables declaration//GEN-END:variables
	private DateComparator com = new DateComparator();

	private ResultadoDetalleExamenDefinition resultadoDetalleExamenService = (ResultadoDetalleExamenDefinition) ContextManager
			.getBizObject("resultadoDetalleExamenService");
	private PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
			.getBizObject("personaExamenService");
	private Persona per;

}