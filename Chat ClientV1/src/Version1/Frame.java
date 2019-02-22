package Version1;
import javax.swing.*;

public class Frame extends JFrame {
	Frame(String Title, JPanel layout){
		super(Title);
		this.add(layout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 400, 600);
		this.setVisible(true);
	}
}
