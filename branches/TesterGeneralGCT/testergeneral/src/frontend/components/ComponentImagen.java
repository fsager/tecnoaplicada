package frontend.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.JLabel;

public class ComponentImagen extends JLabel{


	Image img;
	Graphics2D big;
	int width;
	int height;
	
    BufferedImage biSrc, biDest, bi; 
    RescaleOp rescale;
    float scaleFactor = 1.0f;
    float offset = 10;

	public ComponentImagen(Image img)
	{
		this.img=img;
		createBufferedImages();
		this.width=img.getWidth(this);
		this.height=img.getHeight(this);
	}
	
	public Image getImg() {
        
		return bi.getSubimage(0,0, bi.getWidth(),bi.getHeight());
	}

	public void setImg(Image img) {
		this.img = img;
	}

    public void changeOffSet(int value) {
        if (offset < 255)
           offset = offset+value;
    }

    public void changeScaleFactor(float value) {
            if (scaleFactor < 2)
                scaleFactor = scaleFactor+value;
    }

    public void rescale() {
        rescale = new RescaleOp(scaleFactor, offset, null);
        rescale.filter(biSrc, biDest);
        bi = biDest;
    }

    public void createBufferedImages() {
        biSrc = new BufferedImage(img.getWidth(this),
        		img.getHeight(this),
                                  BufferedImage.TYPE_INT_RGB);

        big = biSrc.createGraphics();
        big.drawImage(img, 0, 0, this);

        biDest = new BufferedImage(img.getWidth(this),
        		img.getHeight(this),
                                   BufferedImage.TYPE_INT_RGB);
        bi = biSrc;
        
		this.setMinimumSize(new Dimension(img.getWidth(this), img.getHeight(this)));
		this.setPreferredSize(new Dimension(img.getWidth(this), img.getHeight(this)));		

    }
    
    public void update(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        paintComponent(g);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(bi, 0, 0, this);
    }    
}
