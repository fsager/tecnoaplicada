/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package frontend.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/* 
 * ScrollDemo.java requires these files:
 *   Rule.java
 *   Corner.java
 *   ScrollablePicture.java
 *   images/youngdad.jpeg
 */
public class ScrollImage extends JPanel {
    //private Rule columnView;
    //private Rule rowView;
    //private JToggleButton isMetric;
    private ScrollablePicture picture;
    private int x, y;
    private PanelImagenFinal panel;
    private Dimension dim;

    public ScrollImage(Dimension dim,ImageIcon img,PanelImagenFinal panel) {
    	this.panel=panel;
    	this.dim=dim;
    	Cursor cursor=new Cursor(Cursor.HAND_CURSOR);
    	this.setCursor(cursor);
    	addMouseMotionListener(new MouseMotionHandler());
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        picture = new ScrollablePicture(img);
        picture.addMouseMotionListener(new MouseMotionHandler());
        
        ImageScroll pictureScrollPane = new ImageScroll(picture);
        pictureScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
        add(pictureScrollPane);
        drawImageFinalSize();

    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);    	
    }

    public void drawImageFinalSize()
    {
    	int x=picture.getX();
    	int y=picture.getY();
    	
    	ImageIcon imageIcon = (ImageIcon) picture.getI();
		Image img = imageIcon.getImage();
		
		BufferedImage bi = new BufferedImage(img.getWidth(this),img.getHeight(this),BufferedImage.TYPE_INT_RGB);
		Graphics2D big = bi.createGraphics();
		big.drawImage(img, 0, 0,panel);
		try
		{	float height=dim.height;
			float width=dim.width;
			if(imageIcon.getIconHeight()<height)
				height=imageIcon.getIconHeight();
			if(imageIcon.getIconWidth()<width)
				width=imageIcon.getIconWidth();
	    	
			Point p=new Point();
			
			p.x=x*-1+(int)((572/2f)-(width/2f));
			p.y=y*-1+(int)((487/2f)-(height/2f));
			
			

			if((p.x+width)>bi.getWidth())
			{
				width=bi.getWidth()-p.x;
			}
			if((p.y+height)>bi.getHeight())
			{
				height=bi.getHeight()-p.y;
			}
			
			if(p.x<0)
			{
				width=width+p.x;
				p.x=0;
			}
			if(p.y<0)
			{
				height=height+p.y;
				p.y=0;
			}
			
			img=bi.getSubimage(p.x,p.y,(int)width,(int)height);
			img=img.getScaledInstance((int)width,(int)height, Image.SCALE_DEFAULT);
		
		}
		catch (java.awt.image.RasterFormatException e) {
			img=null;
		}
		
		
		panel.setImg(img);
		panel.repaint();
    }
    

    
    public void reSize(float value)
    {
    	ImageIcon imageIcon = (ImageIcon) picture.getI();
		Image img = imageIcon.getImage();		
		
    	int width1=(int)(imageIcon.getIconWidth()*value);
    	int height1=(int)(imageIcon.getIconHeight()*value);
    	
    	img=img.getScaledInstance(width1,height1, Image.SCALE_SMOOTH);
    	imageIcon=new ImageIcon(img);
    	
    	picture.setI(imageIcon);
    	picture.repaint();
    	picture.setPreferredSize(new Dimension(width1,height1));
    	picture.revalidate();
    	drawImageFinalSize();
    }
    
    public void resetImageSize()
    {
    	ImageIcon imageIcon = (ImageIcon) picture.getIcon();
		Image img = imageIcon.getImage();
    	
    	img=img.getScaledInstance(imageIcon.getIconWidth(),imageIcon.getIconHeight(), Image.SCALE_DEFAULT);
    	imageIcon=new ImageIcon(img);
    	
    	picture.setI(imageIcon);
    	picture.repaint();
    }
    
	
	class MouseMotionHandler extends MouseMotionAdapter {
		int previusX;
		int previusY;

		public void mouseMoved(MouseEvent e) {
			previusX = e.getX();
			previusY = e.getY();
		}

		public void mouseDragged(MouseEvent e) {

			int desplasamientoX = e.getX() - previusX;
			int desplasamientoY = e.getY() - previusY;
			
			x = x + desplasamientoX;
			y = y + desplasamientoY;
			
			picture.setBounds(x,y,picture.getWidth(),picture.getHeight());
			
			ScrollImage.this.repaint();
			drawImageFinalSize();
			
		}
	}
}
