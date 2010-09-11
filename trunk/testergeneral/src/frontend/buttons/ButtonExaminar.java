package frontend.buttons;
import javax.swing.JButton;

import testerGeneral.domain.Constantes;


public class ButtonExaminar extends JButton {

	public ButtonExaminar() {
	}
	
	public void init()
	{
		//this.setIcon(new ImageIcon(this.getClass().getResource(Constantes.IMG_BTN_EXAMINAR)));
		this.setIcon(null);
		this.setToolTipText(Constantes.BTN_EXAMINAR);
		this.setText(Constantes.BTN_EXAMINAR);
		this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		this.setSize(48,48);
	}
}
