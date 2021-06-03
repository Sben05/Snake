import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SnakeMain extends JFrame implements ActionListener{
	
	private Food food;
	private ArrayList<SnakeCube> cube;
	private long timeCurr;
	 
	public SnakeMain() {
		setTitle("Snake                            Count: 1 of 100");	
	    setBounds(570, 100, 400, 380);
	    setLayout(null);
	    getContentPane().setBackground(Color.black);
	    
	    BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C://Users/Shree/Downloads/gc.jpg"));
			Image dimg = img.getScaledInstance(650, 649, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			setContentPane(new JLabel(imageIcon));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    
	    cube = new ArrayList <SnakeCube>();
	    
	    food = new Food (200, 155);
	    cube.add(new SnakeCube (105, 155));
	    timeCurr = System.currentTimeMillis();
	    
	    add(food);
	    add(cube.get(0));
	    
	    this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				long timeNew = (System.currentTimeMillis())-timeCurr;
				int timeMin = 30;
				if(e.getKeyCode() == e.VK_UP && cube.get(0).getY()-3>getContentPane().getY() && timeNew>timeMin && !(cube.get(0).getDy()>0)) {
					cube.get(0).setDy(-1);
					timeCurr = System.currentTimeMillis();
				}
				if (e.getKeyCode() == e.VK_DOWN && cube.get(0).getY()+3+cube.get(0).getHeight()<getContentPane().getY()+getContentPane().getHeight() && timeNew>timeMin && !(cube.get(0).getDy()<0)) {
					cube.get(0).setDy(1);
					timeCurr = System.currentTimeMillis();
				}
				if(e.getKeyCode() == e.VK_LEFT && cube.get(0).getX()-3>getContentPane().getX() && timeNew>timeMin && !(cube.get(0).getDx()>0)) {
					cube.get(0).setDx(-1);
					timeCurr = System.currentTimeMillis();
				}
				if(e.getKeyCode() == e.VK_RIGHT && cube.get(0).getX()+3+cube.get(0).getWidth()<getContentPane().getX()+getContentPane().getWidth() && timeNew>timeMin && !(cube.get(0).getDx()<0)) {
					cube.get(0).setDx(1);
					timeCurr = System.currentTimeMillis();
				}
				if(e.getKeyCode() == e.VK_SPACE) {
					
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
					
			}

		});

		Timer time = new Timer(10,this);
		time.start();
	    
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (cube.get(0).getX()-3<getContentPane().getX() ||
				cube.get(0).getX()+3+cube.get(0).getWidth()>getContentPane().getX()+getContentPane().getWidth() ||
				cube.get(0).getY()+3+cube.get(0).getHeight()>getContentPane().getY()+getContentPane().getHeight() ||
				cube.get(0).getY()-3<getContentPane().getY() || cube.size() == 100) {
			JOptionPane pane = new JOptionPane();
			if (cube.size()!=100) {
				ps (false);
				pane.showMessageDialog(null,"Game Over!");
			}
			else {
				ps (true);
				pane.showMessageDialog(null,"Congrats, You Win!");
			}
			System.exit(0);
		}
		int rxpos = cube.get(0).getX();
		int rypos = cube.get(0).getY();
		for (int i = 1; i<cube.size(); i++) {
			SnakeCube cb = cube.get(i);
			if (Math.abs(rxpos-cb.getX())<10 && Math.abs(rypos-cb.getY())<10) {
				ps(false);
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null,"Game Over!");
				System.exit(0);
			}
		}
		
		cube.get(0).update();
		for (int i = 1; i<cube.size(); i++) {
			SnakeCube cb = cube.get(i);
			SnakeCube cb2 = cube.get(i-1);
			
			if (cb.getDx()!=cb2.getDx() && Math.abs(cb.getX()-cb2.getX()) >= 20) {
				cb.setDx(cube.get(i-1).getDx()/5);
				if (cb.getDx()<0) {
					cb.setLocation(cb2.getX()+20, cb2.getY());
				}
				else {
					cb.setLocation(cb2.getX()-20, cb2.getY());
				}
			}
			
			else if (cb.getDy()!=cb2.getDy() && Math.abs(cb.getY()-cb2.getY()) >= 20) {
				cb.setDy(cube.get(i-1).getDy()/5);
				if (cb.getDy()<0) {
					cb.setLocation(cb2.getX(), cb2.getY()+20);
				}
				else {
					cb.setLocation(cb2.getX(), cb2.getY()-20);
				}
			}
			cb.update();
		}
		boolean touched = false;
		if ((cube.get(0).getY()<(food.getY()+food.getHeight())) 
				&& (cube.get(0).getX()<food.getX()+food.getWidth() && !(cube.get(0).getX()+cube.get(0).getWidth()<food.getX()))
				&& (cube.get(0).getY()+cube.get(0).getHeight()>food.getY())) {
			touched = true;
		}
		else if ((cube.get(0).getY()+cube.get(0).getHeight()>(food.getY()))
				&& (cube.get(0).getX()<food.getX()+food.getWidth() && !(cube.get(0).getX()+cube.get(0).getWidth()<food.getX()))
				&& (cube.get(0).getY()<food.getY()+food.getHeight())) {
			touched = true;
		}
		else if((cube.get(0).getX()<(food.getX()+food.getWidth()))
				&& (cube.get(0).getY()<food.getY()+food.getHeight() && !(cube.get(0).getY()+cube.get(0).getHeight()<food.getY()))
				&& (cube.get(0).getX()+cube.get(0).getWidth()>food.getX())){
			touched = true;
		}
		else if((cube.get(0).getX()+cube.get(0).getWidth()>(food.getX()))
				&&(cube.get(0).getY()<food.getY()+food.getHeight() && (cube.get(0).getY()+cube.get(0).getHeight()>food.getY()))
				&& (cube.get(0).getX()<food.getX()+food.getWidth())){
			touched = true;
		}
		if (touched) {
			ps (true);
			boolean located = false;
			int x = 0,y = 0;
			while (!located) {
				x = (int)(Math.random()*360);
				y = (int)(Math.random()*320);
				for (SnakeCube cb:cube) {
					if (Math.abs(x-cb.getX())>20 && Math.abs(y-cb.getY())>20) {
						located = true;
					}
				}
			}
			System.out.println(x+" "+y);
			food.update(x, y);
			SnakeCube newCube = null;
			SnakeCube oldCube = cube.get(cube.size()-1);
			if (oldCube.getDx()>0) {
				newCube = new SnakeCube(oldCube.getX()-20, oldCube.getY());
				newCube.setDx(oldCube.getDx()/5);
			}
			else if (oldCube.getDx()<0) {
				newCube = new SnakeCube(oldCube.getX()+20, oldCube.getY());
				newCube.setDx(oldCube.getDx()/5);
			}
			else if (oldCube.getDy()>0) {
				newCube = new SnakeCube(oldCube.getX(), oldCube.getY()-20);
				newCube.setDy(oldCube.getDy()/5);
			}
			else if (oldCube.getDy()<0) {
				newCube = new SnakeCube(oldCube.getX(), oldCube.getY()+20);
				newCube.setDy(oldCube.getDy()/5);
			}
			
			try {
				cube.add(newCube);
				add(newCube);
				setTitle("Snake                            Count: "+cube.size()+" of 100");	
			} catch (Exception ex){
				cube.remove(cube.size()-1);
				System.out.println(1);
			}
		}
		repaint();
	}
	
	public void ps (boolean ed) {
		if (ed) {
			String soundName = "C://Users/Shree/Videos/WAV/Eat.wav";    
			AudioInputStream audioInputStream = null;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(audioInputStream);
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			clip.start();	
		}
		else {
			String soundName = "C://Users/Shree/Videos/WAV/Die.wav";    
			AudioInputStream audioInputStream = null;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(audioInputStream);
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			clip.start();	
		}
	}
	
	public static void main(String[] args) {
		new SnakeMain();
	}
	

}
