package frontend.buttons;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonCancelarMini extends JButton {

	public ButtonCancelarMini() {
	}
	
	public void init()
	{
		///this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_CANCELAR32)));
		this.setIcon(null);
		this.setToolTipText(Constantes.BTN_CANCELAR);
		this.setText(Constantes.BTN_CANCELAR);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		this.setSize(48,48);
	}
}
