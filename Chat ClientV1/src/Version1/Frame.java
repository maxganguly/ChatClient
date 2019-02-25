package Version1;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	Frame(String Title, JPanel layout){
		super(Title);
		this.add(layout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 450, 700);
		this.setVisible(true); 
	}
}

