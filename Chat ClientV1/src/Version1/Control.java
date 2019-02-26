package Version1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Control implements ActionListener {
	private View v;
	public Frame frame;
	public Model model;
	public String username = "";
	public static Socket socket;

	public Control() {

		model = new Model();
		v = new View(this, model.getIp());
		frame = new Frame("Version1", v);

	}
	

	// Action Listener
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Input")) {

			model.message(v.inputUser.getText() + " : " + v.input.getText());
			v.newMessage(v.inputUser.getText() + " : " + v.input.getText(), true);
		}
		if (e.getActionCommand().equals("IP")) {
			System.out.println("Trying to Connect");
				this.username = v.inputUser.getText().trim();
				System.out.println("INPUT: " + v.inputIP.getText());
				connect();
				
			
			

		}

	}

	public void connect() {
		try {
			//InetAddress ip = model.textToIp(v.inputIP.getText().trim());
			InetAddress ip = model.textToIp("localhost");

			if (ip.isReachable(2000)) {
				System.out.println("Reachable: " + ip.getHostAddress() + " " + "8080");
				model.socket = new Socket(ip, 8080);
				System.out.println("Connected");
				v.newMessage("Connected ", false);
				model.startListener(v);
			} else {
				System.out.println("Not Reachable");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			try {
				System.out.println("Created Server");
				ServerSocket ss = new ServerSocket(8080);
				System.out.println("Listening on: " + ss.getLocalPort());
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							model.socket = ss.accept();
							System.out.println("Connection accepted");
							v.newMessage("Connected ", false);
							model.startListener(v);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				});
				t.start();
			} catch (IOException e0) {
				e0.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		Control c = new Control();
		c.connect();

	}

}