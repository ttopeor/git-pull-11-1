/**
 * Do not modify this file without permission from your TA.
 **/
// I made edits without permission from the TA because it was done this weekend
// however, I made sure that I was not altering the logistics of the pre-
// written code
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
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller extends JPanel{

	private static Model model;
	private static View view;
	public static Timer t;
    final int drawDelay = 50; //msec
	private Action drawAction;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		drawAction = new AbstractAction(){
			 public void actionPerformed(ActionEvent e){
				 model.updateLocationAndDirection();
				 view.update(model.getX(), model.getY(), model.getDirect());
				 view.drawPanel();
			 }
		};
		

	}


    public void addKeyFunction(){
        view.getPanel().setFocusable(true);
        view.getPanel().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    model.setDirect(Direction.NORTHEAST);
                }
            }
        });
    }

	public static void main(String[] args) {
		Controller ctrllr = new Controller();
        ctrllr.addKeyFunction();
		t = new Timer(ctrllr.drawDelay, ctrllr.drawAction);	
        //javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {
        //        Button.createAndShowGUI(); 
        //    }
        //});
	}
}
