package Version1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class MessagePanel extends JPanel {

	private boolean own = false;
	private String message = "";
	private String source = "";

	public MessagePanel(String message, boolean isOwn) {
		JPanel contentpane = new JPanel();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentpane.setLayout(new BorderLayout());
		if(message.contains("Connected")) {
		this.source = message.substring(0, message.indexOf(" "));
		}else {
			this.source = message.substring(0, message.indexOf(": "));

		}
		this.message = message.replace(this.source, "");
		this.own = isOwn;
		
		if (this.own) {
			contentpane.setBackground(Color.green);
		} else {
			contentpane.setBackground(Color.blue);
		}
		JLabel head = new JLabel(this.source);
		JTextArea content = new JTextArea(this.message);
		content.setLineWrap(true);
		head.setForeground(Color.WHITE);
		content.setForeground(Color.BLACK);
		if (this.message.length() < 20) {
			contentpane.setPreferredSize(new Dimension(200, 30));
		} else {
			contentpane.setPreferredSize(new Dimension(200, 30 + (int)((this.source.length() + this.message.length())/2.1)));
		}
		//this.setBounds(0, 0, 440, 20 + this.source.length() + this.message.length());
		//this.setBorder(new EmptyBorder(10, 10, 10, 0));
		
		contentpane.add(head, BorderLayout.PAGE_START);
		contentpane.add(content, BorderLayout.CENTER);
		if(isOwn) {
			this.add(Box.createRigidArea(new Dimension(240, 20)));
			this.add(contentpane);

		}else {
			this.add(contentpane);
			this.add(Box.createRigidArea(new Dimension(240 , 20)));

		}
		
		this.add(new JLabel(""));
	}

}
