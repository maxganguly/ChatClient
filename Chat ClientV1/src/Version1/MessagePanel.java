package Version1;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessagePanel extends JPanel {

	private boolean own = false;
	private String message = "";
	private String source = "";
	private int wraps = 0;
	int height = 0;

	public MessagePanel(String message0, boolean isOwn) {
		JPanel contentpane = new JPanel() {
			@Override
			public void paintComponent(final Graphics g) {
				g.setColor(new Color(50, 50, 50));
				g.fillRoundRect(0, 0, this.getBounds().width + 0, this.getBounds().height, 10, 10);
			}
		};
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentpane.setLayout(new BorderLayout());
		if (message0.contains("Connected")) {
			this.source = "" + message0.substring(0, message0.indexOf(" "));
		} else {
			this.source = "" + message0.substring(0, message0.indexOf(":") + 1);

		}
		this.message = " " + message0.replace(this.source, "");
		this.setBackground(new Color(20, 20, 20));

		this.own = isOwn;

		if (this.own) {
			contentpane.setBackground(new Color(50, 50, 50));
		} else {
			contentpane.setBackground(new Color(50, 50, 50));
		}
		JLabel head = new JLabel(" " + this.source);
		JTextArea content = new JTextArea(this.message);
		content.setLineWrap(true);

		content.setWrapStyleWord(true);
		content.setEditable(false);
		content.setBackground(new Color(50, 50, 50));
		head.setForeground(Color.WHITE);
		content.setForeground(
				new Color(200, 200, 200));
		if (this.message.length() < 20) {
			contentpane.setPreferredSize(new Dimension(200, 40));
		} else {
			contentpane.setPreferredSize(
					new Dimension(200, 40 + (int) ((this.source.length() + this.message.length()) / 2.1)));
		}
		// this.setBounds(0, 0, 440, 20 + this.source.length() + this.message.length());
		// this.setBorder(new EmptyBorder(10, 10, 10, 0));

		contentpane.add(head, BorderLayout.PAGE_START);
		contentpane.add(content, BorderLayout.CENTER);
		contentpane.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.PAGE_END);
		if (isOwn) {
			this.add(Box.createRigidArea(new Dimension(170, 20)));
			this.add(contentpane);

		} else {
			this.add(contentpane);
			this.add(Box.createRigidArea(new Dimension(235, 20)));

		}
		this.height = (int) contentpane.getPreferredSize().getHeight() + 15;

	}

}