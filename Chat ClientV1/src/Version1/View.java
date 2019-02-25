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

public class View extends JPanel{
public JTextField input; 
public JTextField ipaddr;
public JLabel myip;
public JTextField myname;
public LinkedList<JLabel> message;
private Control control; 
private JPanel messagePanel,ip,in;
public JScrollPane scrollPane = new JScrollPane(messagePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

private JButton sendm,connect;

	View(Control c,String myIP){
		this.message = new LinkedList<JLabel>();
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
		myname = new JTextField("Benutzername");
		myname.addActionListener(control);
		myip = new JLabel(myIP);
		ipaddr = new JTextField("IP/Hostname");
		ipaddr.addActionListener(control); 
		JPanel up = new JPanel();
		GridLayout gr = new GridLayout(1,4);
		gr.setHgap(4);
		up.setLayout(gr); 
		ip = new JPanel();

		in = new JPanel();
		up.add(ipaddr);
		up.add(myname);
		up.add(connect);

		up.add(myip);
		
		this.messagePanel = new JPanel();
		this.messagePanel.setPreferredSize(new Dimension(450, 1000 + message.size() * 20));
		this.messagePanel.setLayout(new GridLayout( message.size() + 5,1));
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
	public void newMessage(String message) {
		System.out.println(message);
		this.message.addFirst(new JLabel(message));
		Iterator it = this.message.iterator();
		for(int i = 0; i < this.message.size() + 5; i++) {
			if(it.hasNext()) {
				JLabel jl =(JLabel) it.next();
				this.messagePanel.add(jl);
			}
		}
		this.messagePanel.setLayout(new GridLayout( this.message.size() + 5,1));

		this.add(scrollPane,BorderLayout.CENTER);
		this.messagePanel.setPreferredSize(new Dimension(450, 700 +this.message.size() * 20));

		control.frame.revalidate();
		input.setText("");
	}
	public void newMessage() {
		newMessage(this.input.getText());
	}
	public void error() {
		
	}
}
