package Version1;
import java.awt.event.*;
import java.net.*;
import java.net.InetAddress;
public class Control implements ActionListener {
	private View v;
	public Frame f;
	private Model m;
	public Control() {
		m = new Model();
		v = new View(this,m.getIp());
		f = new Frame("Version1",v);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Input")) {
			System.out.println("creating message");
			//v.newMessage();
			m.message(v.input.getText());
		}
		if(e.getActionCommand().equals("IP")) {
			try {
			m.connectclient(m.texttoip(v.myip.getText()));
			v.newMessage("Connected");
			getmessage();

		}catch(Exception ex) {
			ex.printStackTrace();

			try {
			m.makeserver();
			v.newMessage("Connectet");
			getmessage();

			}catch(Exception exe) {
				v.error();
				exe.printStackTrace();

			}
		}
		}
	}
	public void getmessage() {
			m.startListener(v);
			
		
	}
	public static void main(String[] args) {
		new Control();

	}

}
