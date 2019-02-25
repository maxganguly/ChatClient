package Version1;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class View extends JPanel{
public JTextField input; 
JTextField ipaddr;
public JLabel myip;
public JTextField myname;
private LinkedList<JLabel> message;
private Control control; 
private JPanel Messages,ip,in;

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
		
		this.Messages = new JPanel();
		this.Messages.setLayout(new GridLayout(20,1));
		ip.setLayout(new FlowLayout(FlowLayout.LEFT));
		in.setLayout(new FlowLayout(FlowLayout.LEFT));

		in.add(input);
		in.add(sendm);
		this.setLayout(new BorderLayout());
		this.add(Messages,BorderLayout.CENTER);
		this.add(up, BorderLayout.NORTH);
		//this.add(input ,BorderLayout.SOUTH);

		this.add(in ,BorderLayout.SOUTH);
	}
	public void newMessage(String message) {
		System.out.println(message);
		this.message.addFirst(new JLabel(message));
		Iterator it = this.message.iterator();
		for(int i = 0; i < 12; i++) {
			if(it.hasNext()) {
				JLabel jl =(JLabel) it.next();
				this.Messages.add(jl);
			}
		}
		this.add(Messages,BorderLayout.CENTER);
		control.frame.revalidate();
		input.setText("");
	}
	public void newMessage() {
		newMessage(this.input.getText());
	}
	public void error() {
		
	}
}
