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
private JPanel Messages;
	View(Control c,String myIP){
		this.message = new LinkedList<JLabel>();
		this.control = c;
		input = new JTextField();
		input.setActionCommand("Input");
		input.addActionListener(control);
		myname = new JTextField("Neuer Benutzer");
		myname.setActionCommand("Name"); 
		myname.addActionListener(control);
		myip = new JLabel(myIP);
		ipaddr = new JTextField();
		ipaddr.setActionCommand("IP");
		ipaddr.addActionListener(control);
		JPanel up = new JPanel();
		GridLayout gr = new GridLayout(3,1);
		up.setLayout(gr);
		up.add(ipaddr);
		up.add(myip);
		up.add(myname);
		BorderLayout bl = new BorderLayout();
		this.Messages = new JPanel();
		GridLayout gl = new GridLayout(12,1);
		this.Messages.setLayout(gl);
		this.setLayout(bl);
		this.add(Messages,BorderLayout.CENTER);
		this.add(up, BorderLayout.NORTH);
		this.add(input ,BorderLayout.SOUTH);
		System.out.println("lollipop");

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
