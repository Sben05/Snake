/**
 * @author: Shreeniket Bendre
 * Project: Pong
 * File: Paddle.java
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class SnakeCube extends JComponent{

	private Rectangle2D.Double rect; 
	private int dx;
	private int dy;
	private static boolean isFirst = true;
	private boolean col;
	
	
	public SnakeCube(int x, int y){
		rect = new Rectangle2D.Double(0,0,15,15);
		this.setSize(new Dimension(16,16));
		this.setLocation(x,y);
		if (isFirst) {
			col = true;
			isFirst = false;
		}
		dx = 0;
		dy = 0;

	}
//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		
//		Image img = null;
//		try {
//			img = ImageIO.read(new File("C://Users/Shree/Downloads/SnakeBall.png"));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		Image dimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//		
//		g2.drawImage(dimg, xpos, ypos, null);
//	}
	
	public void paintComponent(Graphics g){
		
		Color red = Color.red;
		Color green = new Color(178,255,102);
		
		Graphics2D g2 = (Graphics2D) g;
		if (!col)
			g2.setColor(green);
		else
			g2.setColor(red);
		g2.fill(rect);
		g2.setColor(Color.black);
		g2.draw(rect);
	}
	
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
	public void update () {
		setLocation(getX()+dx, getY()+dy);
	}
}
