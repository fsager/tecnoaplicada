package frontend.tablemodel;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RowRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row, column);
		MasterTableModel masterTableModel=(MasterTableModel)table.getModel();
		String tooltip=masterTableModel.getRowTooltip(row);
		if(tooltip!=null)
			setToolTipText("<html>Parámetros de corrección: "+tooltip+"</html>");
		
		return this;
	}
}