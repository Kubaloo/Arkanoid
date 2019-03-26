import javax.swing.JFrame;


import Arkanoid.Arkanoid;

import Menu.MenuState;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Main {
		
	public static void main(String[] args) {
		JFrame frame = new JFrame("Arkanoid");
		//frame.setCursor(frame.getToolkit().createCustomCursor(
	            //new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
	            //"null")); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 700);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		Arkanoid game = new Arkanoid(1280, 700);
		frame.add(game, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	
}
