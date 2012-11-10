package frontend.buttons;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonBuscar extends JButton {

	public ButtonBuscar() {
	}
	
	public void init()
	{
		this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_BUSCAR)));
		this.setToolTipText(Constantes.BTN_BUSCAR);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		this.setSize(48,48);
	}
}
