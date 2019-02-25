package Version1;

import java.awt.event.*;
import java.net.*;
import java.net.InetAddress;

public class Control implements ActionListener {
	private View v;
	public Frame frame;
	private Model model;

	public Control() {
		model = new Model();
		v = new View(this, model.getIp());
		frame = new Frame("Version1", v);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Input")) {
			model.message(v.myname.getText() + " : " + v.input.getText());

			v.newMessage(v.myname.getText() + " : " + v.input.getText());

			if (e.getActionCommand().equals("IP")) {
				try {
					model.connectClient(model.textToIp(v.myip.getText()));
					v.newMessage("Connected");
					getMessage();

				} catch (Exception ex) {
					ex.printStackTrace();

					try {
						model.makeServer();
						v.newMessage("Connected");
						getMessage();

					} catch (Exception exe) {
						v.error();
						exe.printStackTrace();

					}
				}
			}
		}

	}

	public void getMessage() {
		model.startListener(v);

	}

	public static void main(String[] args) {
		new Control();

	}

}