package frontend.ventanas;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;

import testerGeneral.domain.Constantes;

import frontend.paneles.PanelConfiguracionDB;
import frontend.utils.Util;

public class VtnConfigurarDb extends JFrame{
	 public VtnConfigurarDb(String icon,String aplicacion) {
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setSize(800,320);
		this.setTitle("Error en la conexión con la base de datos");
		
		this.setIconImage(new ImageIcon(Util.class.getResource(icon)).getImage());

		Dimension dim = this.getToolkit().getScreenSize();
		this.setLocation((dim.width - this.getWidth()) / 2,(dim.height - this.getHeight()) / 2);
		this.setLayout(new GridLayout());
		
		PanelConfiguracionDB panelConfiguracionDB=new PanelConfiguracionDB(aplicacion,true);
		panelConfiguracionDB.setVisible(true);
		
		this.getContentPane().add(panelConfiguracionDB);
		this.setVisible(true);	
	}
}
