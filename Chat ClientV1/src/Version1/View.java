package Version1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class View extends JPanel {
	public JTextField input;
	public JTextField inputIP;
	public JLabel myip;
	public JTextField inputUser;
	public ArrayList<MessagePanel> messages;
	private Control control;
	private JPanel messagePanel, ip, in;
	private int panelHeight = 0;
	public JScrollPane scrollPane = new JScrollPane(messagePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	private JButton sendm, connect;

	View(Control c, String myIP) {
		this.messages = new ArrayList<MessagePanel>();
		this.control = c;
		connect = new JButton("verbinden");
		connect.setActionCommand("IP");
		connect.addActionListener(control);
		connect.setBackground(new Color(20, 20, 20));
		connect.setForeground(new Color(200, 200, 200));
		connect.setMargin(new Insets(5, 5, 5, 5));

		this.setBackground(new Color(20, 20, 20));

		sendm = new JButton("Senden");
		sendm.setActionCommand("Input");
		sendm.addActionListener(control);
		sendm.setBackground(new Color(20, 20, 20));
		sendm.setForeground(new Color(200, 200, 200));

		input = new JTextField(30);
		input.setActionCommand("Input");
		input.addActionListener(control);
		input.setBackground(new Color(20, 20, 20));
		input.setForeground(new Color(200, 200, 200));
		input.setPreferredSize(new Dimension(220, 30));

		inputUser = new JTextField("Benutzername");
		inputUser.addActionListener(control);
		inputUser.setBackground(new Color(20, 20, 20));
		inputUser.setForeground(new Color(200, 200, 200));
		inputUser.setPreferredSize(new Dimension(120, 30));

		myip = new JLabel(myIP);
		myip.setForeground(new Color(200, 200, 200));
		inputIP = new JTextField("IP/Hostname");
		inputIP.addActionListener(control);
		inputIP.setBackground(new Color(20, 20, 20));
		inputIP.setForeground(new Color(200, 200, 200));
		inputIP.setPreferredSize(new Dimension(120, 30));
		JPanel up = new JPanel();
		// GridLayout gr = new GridLayout(1, 4);
		// gr.setHgap(4);
		// gr.setVgap(5);
		up.setLayout(new FlowLayout(FlowLayout.LEFT));
		up.setBackground(new Color(20, 20, 20));

		ip = new JPanel();
		up.setPreferredSize(new Dimension(450, 40));
		this.ip.setBackground(new Color(20, 20, 20));

		in = new JPanel();
		this.in.setBackground(new Color(20, 20, 20));

		up.add(inputIP);
		up.add(inputUser);
		up.add(connect);

		up.add(myip);

		this.messagePanel = new JPanel();
		this.messagePanel.setPreferredSize(new Dimension(450, 600 + messages.size() * 20));
		this.messagePanel.setBackground(new Color(20, 20, 20));

		// this.messagePanel.setLayout(new BoxLayout(this.messagePanel,
		// BoxLayout.PAGE_AXIS));
		// this.messagePanel.setLayout(new GridLayout( message.size() + 5,1));
		scrollPane = new JScrollPane(messagePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ip.setLayout(new FlowLayout(FlowLayout.LEFT));
		in.setLayout(new FlowLayout(FlowLayout.LEFT));

		in.add(input);
		in.add(sendm);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(up, BorderLayout.NORTH);
		// this.add(input ,BorderLayout.SOUTH);

		this.add(in, BorderLayout.SOUTH);
	}

	public void newMessage(String message, boolean isOwn) {
		System.out.println(message);
		MessagePanel mp = new MessagePanel(message, isOwn);
		this.panelHeight += mp.height;
		messages.add(mp);
		
		for (MessagePanel temp : messages) {
			System.out.println("added");
			this.messagePanel.add(temp);

		}
		// this.messagePanel.setLayout(new GridLayout( this.message.size() + 5,1));
		this.messagePanel.setPreferredSize(new Dimension(450, 20 + this.panelHeight));
		// this.add(new JLabel(""));

		this.add(scrollPane, BorderLayout.CENTER);

		control.frame.revalidate();
		if(scrollPane.getVerticalScrollBar().getValue() != scrollPane.getVerticalScrollBar().getMaximum()) {
			scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

		}
		input.setText("");
	}

}
