package frontend.buttons;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonAgregarMini extends JButton {

	public ButtonAgregarMini() {
	}
	
	public void init()
	{
		this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_ADD)));
		this.setToolTipText(Constantes.BTN_AGREGAR);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		this.setSize(48,48);
	}
}
