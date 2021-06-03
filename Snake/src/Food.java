import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Food extends JComponent{
	private Ellipse2D.Double circle; 
	private Rectangle2D.Double stem;
	private int dx;
	private int dy;
	
	
	public Food(int x, int y){
		circle = new Ellipse2D.Double(0,5,15,15);
		stem = new Rectangle2D.Double(6,0,2,10);
		this.setSize(new Dimension(25,25));
		this.setLocation(x,y);
		
		dx = 0;
		dy = 0;

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Image img = null;
		try {
			img = ImageIO.read(new File("SnakeBall.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image dimg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		
		g2.drawImage(dimg, null, null);
	}

// JFrame drawn apple. Not as good quality. Can be turned on if the processor is weak to improve performance. 
//	public void paintComponent(Graphics g){
//		Graphics2D g2 = (Graphics2D) g;
//		g2.setColor(Color.red);
//		g2.fill(circle);
//		g2.setColor(Color.black);
//		g2.draw(circle);
//		g2.setColor(new Color(178,255,102));
//		g2.fill(stem);
//	}
	
	public void setDx(int x) {
		dy = 0;
		dx = x*5;
	}
	public void setDy(int y) {
		dx = 0;
		dy = y*5;
	}
	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
	}
	public void update (int x, int y) {
		setLocation(x, y);
	}
}
