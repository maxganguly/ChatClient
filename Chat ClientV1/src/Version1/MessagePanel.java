package Version1;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


public class MessagePanel extends JPanel {

	private boolean own = false;
	private String message = "";
	private String source = "";

	public MessagePanel(String message, boolean isOwn) {
		this.source = message.substring(0, message.indexOf(" "));
		this.message = message.replace(this.source, "");
		this.own = isOwn;
		
		if (this.own) {
			this.setBackground(Color.green);
		} else {
			this.setBackground(Color.blue);
		}
		JLabel head = new JLabel(this.source);
		JTextArea content = new JTextArea(this.message);
		head.setForeground(Color.WHITE);
		content.setForeground(Color.BLACK);
		if (this.message.length() < 20) {
			this.setPreferredSize(new Dimension(this.message.length() * 10 + this.source.length() * 10, 20));
		} else {
			this.setPreferredSize(new Dimension(200, 20 + this.source.length() + this.message.length()));
		}
		//this.setBounds(0, 0, 440, 20 + this.source.length() + this.message.length());
		//this.setBorder(new EmptyBorder(10, 10, 10, 0));
		
		this.add(head);
		this.add(content);
		this.add(Box.createRigidArea(new Dimension(440-(20 + this.source.length() + this.message.length()), 20)));
		this.add(new JLabel(""));
	}

}
