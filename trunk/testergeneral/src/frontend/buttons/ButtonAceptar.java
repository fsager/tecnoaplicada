package frontend.buttons;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonAceptar extends JButton {

	public ButtonAceptar() {
	}
	
	public void init()
	{
		this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_ACEPTAR)));
		this.setToolTipText(Constantes.BTN_ACEPTAR);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		this.setSize(48,48);
	}
}
