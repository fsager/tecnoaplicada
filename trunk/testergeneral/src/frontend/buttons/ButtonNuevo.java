package frontend.buttons;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonNuevo extends JButton {

	public ButtonNuevo() {
	}
	
	public void init()
	{
		//this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_ACEPTAR)));
		this.setToolTipText(Constantes.BTN_NUEVO);
		this.setText(Constantes.BTN_NUEVO);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		//this.setSize(100,100);
	}
}
