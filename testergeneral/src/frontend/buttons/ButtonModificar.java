package frontend.buttons;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonModificar extends JButton {

	public ButtonModificar() {
	}
	
	public void init()
	{
		//this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_ACEPTAR)));
		this.setToolTipText(Constantes.BTN_MODIFICAR);
		this.setText(Constantes.BTN_MODIFICAR);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		//this.setSize(48,48);
	}
}
