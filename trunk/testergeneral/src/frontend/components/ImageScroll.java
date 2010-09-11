package frontend.components;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JScrollPane;

public class ImageScroll extends JScrollPane {
	public ImageScroll(Component view)
	{
		super(view);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	

	}

}
