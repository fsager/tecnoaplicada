package frontend.tablemodel;

import javax.swing.table.AbstractTableModel;

public abstract class MasterTableModel extends AbstractTableModel{

	public abstract String getRowTooltip(int row);

}
