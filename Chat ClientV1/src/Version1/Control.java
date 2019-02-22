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
			v.newMessage();
			m.message(v.input.getText());
		}
		if(e.getActionCommand().equals("IP")) {
			try {
			m.connectclient(m.texttoip(v.myip.getText()));
			v.newMessage("Connectet");
		}catch(Exception ex) {
			ex.printStackTrace();

			try {
			m.makeserver();
			v.newMessage("Connectet");
			}catch(Exception exe) {
				v.error();
				exe.printStackTrace();

			}
			System.out.println("lollipop");
		}
		}
	}
	public void getmessage() {
		while(true) {
			m.getmessage(v);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Control();

	}

}
