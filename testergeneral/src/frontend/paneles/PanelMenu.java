package frontend.paneles;

import javax.swing.JPanel;

public abstract class PanelMenu extends JPanel{

	public abstract JPanel getPanelSubMenu();
	public abstract void setPanelContenido(JPanel panel);
	public abstract void cargarPrimeraOpcion();
	public abstract void cargarSubMenuPersona();
	public abstract void seleccionarPersona();
}
