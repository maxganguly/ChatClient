package Version1;

import java.awt.Color;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Frame extends JFrame {
	
	
	static Socket socket;
	static String ip;
	static BufferedOutputStream out;
	static BufferedInputStream in;
	static byte[] buffer = new byte[1024];
	static boolean connected = false;
	
	Frame(String Title, JPanel layout) {
		this.add(layout);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 450, 700);
		this.setBackground(new Color(20, 20, 20));
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	
}





