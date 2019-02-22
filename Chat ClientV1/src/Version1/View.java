package Version1;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class View extends JPanel{
public JTextField input;
private JTextField ipaddr;
public JLabel myip;
private JTextField myname;
private LinkedList<JLabel> message;
private Control control;
private JPanel Messages,ip,in;
private JButton sendm,sendip;
	View(Control c,String myIP){
		this.message = new LinkedList<JLabel>();
		this.control = c;
		sendip = new JButton("verbinden");
		sendip.setActionCommand("IP");
		sendip.addActionListener(control);
		sendm = new JButton("Senden");
		sendm.setActionCommand("Input");
		sendm.addActionListener(control);
		input = new JTextField(30);
		input.setActionCommand("Input");
		input.addActionListener(control);
		myname = new JTextField("Neuer Benutzer");
		myname.setActionCommand("Name"); 
		myname.addActionListener(control);
		myip = new JLabel(myIP);
		ipaddr = new JTextField(15);
		ipaddr.setActionCommand("IP");
		ipaddr.addActionListener(control); 
		JPanel up = new JPanel();
		GridLayout gr = new GridLayout(3,1);
		up.setLayout(gr);
		ip = new JPanel();
		in = new JPanel();
		up.add(ip);
		up.add(myip);
		up.add(myname);
		this.Messages = new JPanel();
		this.Messages.setLayout(new GridLayout(12,1));
		ip.setLayout(new FlowLayout(FlowLayout.LEFT));
		in.setLayout(new FlowLayout(FlowLayout.LEFT));
		ip.add(ipaddr);
		ip.add(sendip);
		in.add(input);
		in.add(sendm);
		this.setLayout(new BorderLayout());
		this.add(Messages,BorderLayout.CENTER);
		this.add(up, BorderLayout.NORTH);
		this.add(in ,BorderLayout.SOUTH);
	}
	public void newMessage(String message) {
		System.out.println(message);
		this.message.addFirst(new JLabel(myname.getText()+" : "+message));
		Iterator it = this.message.iterator();
		for(int i = 0; i < 12; i++) {
			if(it.hasNext()) {
				JLabel jl =(JLabel) it.next();
				this.Messages.add(jl);
			}
		}
		this.add(Messages,BorderLayout.CENTER);
		control.f.revalidate();
		
	}
	public void newMessage() {
		newMessage(this.input.getText());
	}
	public void error() {
		
	}
}
