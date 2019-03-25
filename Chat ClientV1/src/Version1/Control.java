package Version1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Control implements ActionListener {

	private View v;
	public static JFrame frame;
	public Model model;
	public String username = "";
	public static Socket socket;
	public static boolean isconn = false;

	public Control() {

		model = new Model();
		v = new View(this, model.getIp());
		frame = new Frame("Version1", v);
		createSpeaker();

	}

	private void createSpeaker() {
		File file = new File(System.getProperty("java.io.tmpdir") + "\\texttospeech.vbs");
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			pw.println("Createobject(\"sapi.spvoice\").speak (WScript.Arguments(0))");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Input")) {
			System.out.println("send");
			model.message(v.inputUser.getText() + " : " + v.input.getText());
			v.newMessage(v.inputUser.getText() + " : " + v.input.getText(), true);
		}
		if (e.getActionCommand().equals("IP")) {
			if (!isconn) {
				System.out.println("Trying to Connect");
				this.username = v.inputUser.getText().trim();
				System.out.println("INPUT: " + v.inputIP.getText());
				connect();
			} else {
				try {
					model.socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("falseus");
				isconn = false;
			}

		}

	}

	public void connect() {
		try {
			InetAddress ip = model.textToIp(v.inputIP.getText().trim());
			// InetAddress ip = model.textToIp("localhost");

			if (ip.isReachable(2000)) {
				System.out.println("Reachable: " + ip.getHostAddress() + " " + "8080");
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							model.socket = new Socket(ip, 8080);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							isconn = false;
						}

					}
				});
				thread.start();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				thread = null;

				if (model.socket == null) {
					throw new IOException();
				}
				System.out.println("Connected");
				v.newMessage("Connected ", false);
				isconn = true;
				v.connect.setText("trennen");
				model.startListener(v);
			} else {

				System.out.println("Not Reachable");
				throw new IOException();
			}

		} catch (IOException e) {
			e.printStackTrace();
			try {
				System.out.println("Created Server");
				ServerSocket ss = new ServerSocket(8080);
				v.newMessage("Creating Server", false);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							model.socket = ss.accept();
							System.out.println("Connection accepted");
							v.newMessage("Connected ", false);
							isconn = true;
							v.connect.setText("trennen");
							Control.frame.revalidate();
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

	public void close() {
		try {
			// socket.close();
			model.socket.close();
			isconn = false;
			v.connect.setText("verbinden");
			v.newMessage("Connection closed ", false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Control c = new Control();
		/*
		 * try { Runtime.getRuntime().exec("cmd/ taskkill /IM /F"); }catch(Exception e)
		 * { }
		 */

	}

}