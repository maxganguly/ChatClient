package Version1;

import java.awt.event.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Control implements ActionListener {
	private View v;
	public Frame frame;
	private Model model;
	Socket socket;

	public Control() {

		model = new Model();
		v = new View(this, model.getIp());
		frame = new Frame("Version1", v);

	}

	// Action Listener
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Input")) {

			model.message(v.myname.getText() + " : " + v.input.getText());
			v.newMessage(v.myname.getText() + " : " + v.input.getText());
		}
		if (e.getActionCommand().equals("IP")) {
			System.out.println("Trying to Connect");
			try {
				connect(model.textToIp(v.myip.getText()));
				v.newMessage("Connected");
				model.startListener(v);
			} catch (UnknownHostException e1) {
System.out.println("Failed Host");				e1.printStackTrace();
			}
			

		}

	}

	public void connect(InetAddress ip) {
		try {
			if (ip.isReachable(2000)) {
				System.out.println("Reachable");
				socket = new Socket(ip, 8080);
				System.out.println("Connected");
			} else {
				System.out.println("Not Reachable");
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				System.out.println("Created Server");
				ServerSocket ss = new ServerSocket(8080);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							socket = ss.accept();
							System.out.println("Connection accepted");
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				});
				t.run();
			} catch (IOException e0) {
				e0.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new Control();

	}

}