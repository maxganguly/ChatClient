package Version1;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	Frame(String Title, JPanel layout) {
		super(Title);
		this.add(layout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 450, 700);
		this.setBackground(new Color(20, 20, 20));
		this.setResizable(false);
		this.setVisible(true);
	}
}
