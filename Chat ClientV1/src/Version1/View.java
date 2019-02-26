package Version1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class View extends JPanel{
public JTextField input; 
public JTextField inputIP;
public JLabel myip;
public JTextField inputUser;
public LinkedList<JPanel> message;
private Control control; 
private JPanel messagePanel,ip,in;
public JScrollPane scrollPane = new JScrollPane(messagePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

private JButton sendm,connect;

	View(Control c,String myIP){
		this.message = new LinkedList<JPanel>();
		this.control = c;
		connect = new JButton("verbinden");
		connect.setActionCommand("IP");
		connect.addActionListener(control);
		sendm = new JButton("Senden");
		sendm.setActionCommand("Input");
		sendm.addActionListener(control);
		input = new JTextField(30);
		input.setActionCommand("Input");
		input.addActionListener(control);
		inputUser = new JTextField("Benutzername");
		inputUser.addActionListener(control);
		myip = new JLabel(myIP);
		inputIP = new JTextField("IP/Hostname");
		inputIP.addActionListener(control); 
		JPanel up = new JPanel();
		GridLayout gr = new GridLayout(1,4);
		gr.setHgap(4);
		up.setLayout(gr); 
		ip = new JPanel();

		in = new JPanel();
		up.add(inputIP);
		up.add(inputUser);
		up.add(connect);

		up.add(myip);
		
		this.messagePanel = new JPanel();
		this.messagePanel.setPreferredSize(new Dimension(450, 1000 + message.size() * 20));
		//this.messagePanel.setLayout(new BoxLayout(this.messagePanel, BoxLayout.PAGE_AXIS));
		//this.messagePanel.setLayout(new GridLayout( message.size() + 5,1));
		scrollPane = new JScrollPane(messagePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ip.setLayout(new FlowLayout(FlowLayout.LEFT));
		in.setLayout(new FlowLayout(FlowLayout.LEFT));

		in.add(input);
		in.add(sendm);
		this.setLayout(new BorderLayout());
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(up, BorderLayout.NORTH);
		//this.add(input ,BorderLayout.SOUTH);

		this.add(in ,BorderLayout.SOUTH);
	}
	public void newMessage(String message, boolean isOwn) {
		System.out.println(message);
		this.message.addFirst(new MessagePanel(message, isOwn));
		Iterator it = this.message.iterator();
		for(int i = 0; i < this.message.size() + 5; i++) {
			if(it.hasNext()) {
				MessagePanel jl =(MessagePanel) it.next();
				
				this.messagePanel.add(jl);
			}
		}
		//this.messagePanel.setLayout(new GridLayout( this.message.size() + 5,1));

		this.add(scrollPane,BorderLayout.CENTER);
		this.messagePanel.setPreferredSize(new Dimension(450, 700 +this.message.size() * 20));

		control.frame.revalidate();
		input.setText("");
	}

	public void error() {
		
	}
}
