/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/


import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.EventQueue; 
import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 
import javax.swing.AbstractAction; 
import javax.swing.Action; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame{

    final static int frameWidth = 800;//500
   	final static int frameHeight = 800;//300
    final static int imgWidth = 165;//165
	final static int imgHeight = 165;
	final int frameCount = 8;
	private int picNum = 0;
	private BufferedImage[][] pics;
	private BufferedImage[][] jumppics;


	private Direction dir = Direction.SOUTHEAST;
	
	private int xloc;
	private int yloc;
    	private DrawPanel drawPanel= new DrawPanel();
	final int frameStartSize = 800;

	View () {
        add(drawPanel);           
		// load any of the images that you would want into the picNames, in case you want to do anything
    		// other than just move the orc
    		String[] picNames = {"images/orc/orc_forward_southeast.png", 
    			"images/orc/orc_forward_southwest.png", "images/orc/orc_forward_west.png",
    			"images/orc/orc_forward_south.png", "images/orc/orc_forward_northwest.png",
    			"images/orc/orc_forward_northeast.png", "images/orc/orc_forward_north.png", 
    			"images/orc/orc_forward_east.png","images/orc/orc_jump_north.png","images/orc/orc_jump_south.png",
				"images/orc/orc_jump_west.png","images/orc/orc_jump_east.png","images/orc/orc_jump_northwest.png",
    			"images/orc/orc_jump_southeast.png","images/orc/orc_jump_northeast.png",
    			"images/orc/orc_jump_southwest.png"};
 
    		
    		
    		// Initialize pics with a give 2D array
    		
    		pics = new BufferedImage[picNames.length][frameCount];
    		//jumppics = new BufferedImage[picjumpNames.length][8];
    		
    		for(int j = 0; j < picNames.length; j++) {
    			// load the image
    			BufferedImage img = createImage(picNames[j]);
    			//BufferedImage jumpimg = createImage(picjumpNames[j]);
    			// create the frameCount of the orc from that certain png to make it walk(change)
	    		pics[j] = new BufferedImage[frameCount];
	    		//jumppics[j] = new BufferedImage[8];
	    		for(int i = 0; i < frameCount; i++) {
	    			// all of the different movements of that image
	    			pics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		}
	    		//for(int i = 0; i < 8; i++) {
	    			// all of the different movements of that image
	    			//jumppics[j][i] = jumpimg.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		//}
    		}
    setBackground(Color.gray);                 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
	setSize(getWidth(), getHeight());
	setVisible(true);
        pack();	
	}
	
	// Override the paint method in JPanel, and use getPicDir to get the right image
	// corresponding to the correct direction
	public void paint(Graphics g) {
		g.drawImage(pics[getPicDir(dir)][picNum], xloc, yloc, Color.gray, this);
	}

	public void drawPanel() {
		drawPanel.repaint();
	}

	private BufferedImage createImage(String fname){
    		
		BufferedImage bufferedImage;
    		
    		try {
    			// Method now takes a String (fname) in order to get the 
			// right image to load for all the images passed through
    			bufferedImage = ImageIO.read(new File(fname));
    			return bufferedImage;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return null;
	}

	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageWidth() {
		return imgWidth;
	}
	public int getImageHeight() {
		return imgHeight;
	}

    	public DrawPanel getPanel(){
        	return drawPanel;
    	}

    	public Direction getDir(){
        	return dir;
    	}

	// I wanted this to be a switch statement, so that would be the only thing to change this into
	// but switch-case statements needed ints and was having trouble with getting the enums to work
	// in my favor with converting to ints, but I found a way to compare the strings, which works
	// so it is just an alternative to what I originally wanted
	private int getPicDir(Direction d) {
		if (d.getName().equals(Direction.SOUTHEAST.getName())) { return 0;}
		else if (d.getName().equals(Direction.SOUTHWEST.getName())) { return 1;}
		else if (d.getName().equals(Direction.WEST.getName())) { return 2;}
		else if (d.getName().equals(Direction.SOUTH.getName())) { return 3;}
		else if (d.getName().equals(Direction.NORTHWEST.getName())) { return 4;}
		else if (d.getName().equals(Direction.NORTHEAST.getName())) { return 5;}
		else if (d.getName().equals(Direction.NORTH.getName())) { return 6;}
		else if (d.getName().equals(Direction.EAST.getName())) { return 7;}
		else if (d.getName().equals(Direction.JUMP_NORTH.getName())) { return 8;}
		else if (d.getName().equals(Direction.JUMP_SOUTH.getName())) { return 9;}
		else if (d.getName().equals(Direction.JUMP_WEST.getName())) { return 10;}
		else if (d.getName().equals(Direction.JUMP_EAST.getName())) { return 11;}
		else if (d.getName().equals(Direction.JUMP_NORTHWEST.getName())) { return 12;}
		else if (d.getName().equals(Direction.JUMP_SOUTHEAST.getName())) { return 13;}
		else if (d.getName().equals(Direction.JUMP_NORTHEAST.getName())) { return 14;}
		else if (d.getName().equals(Direction.JUMP_SOUTHWEST.getName())) { return 15;}
		else { return 99999;}// Never going to happen, just a default for compiling
	}

    	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel {

        	public DrawPanel(){
            		super();
            		setFocusable(true);
            		addKeyListener(new KeyAdapter(){
                		@Override
                	public void keyPressed(KeyEvent e){
                			if (e.getKeyCode() == KeyEvent.VK_UP){
                				dir = Direction.NORTH;
                    		}
                			else if (e.getKeyCode() == KeyEvent.VK_DOWN){
                				dir = Direction.SOUTH;
                			}
                			else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                				dir = Direction.EAST;
                			}
                			else if (e.getKeyCode() == KeyEvent.VK_LEFT){
                				dir = Direction.WEST;
                			}
                			else if (e.getKeyCode() == KeyEvent.VK_J){
                				dir = Direction.JUMP_NORTH;
                			}
                			else if (e.getKeyCode() == KeyEvent.VK_F){
                				dir = Direction.WEST;
                			}
                			}
            			});
        		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
	    		picNum = (picNum + 1) % 8;
	    		g.drawImage(pics[getPicDir(dir)][picNum], xloc, yloc, Color.gray, this);
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
	}

	// update location, continue through the subimages(picNum),, and then repaint the image
	public void update(int xloc, int yloc, Direction d) {//, JFrame frame) {
		this.xloc = xloc;
		this.yloc = yloc;
		dir = d;
	}
}
