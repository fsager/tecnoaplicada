/*
 * PanelResultado.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.examenes;

import java.util.List;

import javax.swing.ListSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.Resultado;
import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.service.ResultadoDefinition;
import examenes.util.ExamenesUtils;
import frontend.tablemodel.TableModelResultado;

/**
 *
 * @author  __USER__
 */
public class PanelResultado extends javax.swing.JPanel {

	private static final Log log = LogFactory.getLog(PanelResultado.class);
	private ExamenDetalle exad;
	private int colContenido;

	/** Creates new form PanelResultado */
	public PanelResultado(ResultadoDetalleExamen resultadoDetalleExamen,
			int colContenido, ExamenDetalle exad) {
		initComponents();
		this.colContenido = colContenido;
		this.exad = exad;

		try {
			ResultadoDefinition resultadoService = (ResultadoDefinition) ContextManager
					.getBizObject("resultadoService");
			Resultado resultado = new Resultado();
			resultado.setResultadoDetalleExamen(resultadoDetalleExamen);

			List<Resultado> resultados = resultadoService.getAll(resultado);
			setTableModel(resultados);

			String promedio = ExamenesUtils.mostrarResultados(resultados,
					resultadoDetalleExamen.getExamenDetalle());
			promedio += " - "
					+ ExamenesUtils.detalleExamenResultado(
							resultadoDetalleExamen.getExamenDetalle(),
							resultados);
			lbRes.setText(promedio);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public PanelResultado(List<Resultado> resultados, int colContenido,
			ExamenDetalle exad) {
		initComponents();
		this.colContenido = colContenido;
		this.exad = exad;

		setTableModel(resultados);

		String promedio = ExamenesUtils.mostrarResultados(resultados, exad);
		promedio += " - "
				+ ExamenesUtils.detalleExamenResultado(exad, resultados);
		lbRes.setText(promedio);
	}

	public void setTableModel(List lst) {

		TableModelResultado tableModel = new TableModelResultado(colContenido,
				ExamenesUtils.getUnidadesExamen(exad));
		tableModel.setLst(lst);
		tableDetalleExamen.setModel(tableModel);
		tableDetalleExamen
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableDetalleExamen = new javax.swing.JTable();
		lbRes = new javax.swing.JLabel();

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Detalle Exámen",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel1.setFocusable(false);

		tableDetalleExamen.setModel(new javax.swing.table.DefaultTableModel(
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
		jScrollPane1.setViewportView(tableDetalleExamen);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 332,
								Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281,
				Short.MAX_VALUE));

		lbRes.setFont(new java.awt.Font("Segoe UI", 1, 14));
		lbRes.setText("jLabel1");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addGap(20, 20, 20)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lbRes,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																368,
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
												lbRes,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												53,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lbRes;
	private javax.swing.JTable tableDetalleExamen;
	// End of variables declaration//GEN-END:variables

}