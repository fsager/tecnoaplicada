package frontend.buttons;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonAceptarConTexto extends JButton {

	public ButtonAceptarConTexto() {
	}
	
	public void init()
	{
		//this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_CANCELAR)));
		this.setText(Constantes.BTN_ACEPTAR);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		//this.setSize(48,48);
	}
}
