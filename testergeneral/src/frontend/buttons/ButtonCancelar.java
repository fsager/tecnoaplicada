package frontend.buttons;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonCancelar extends JButton {

	public ButtonCancelar() {
	}
	
	public void init()
	{
		this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_CANCELAR)));
		this.setToolTipText(Constantes.BTN_CANCELAR);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		this.setSize(48,48);
	}
}
