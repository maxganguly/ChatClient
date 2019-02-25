package Version1;

import java.net.*;
import java.util.*;

import javax.swing.JLabel;

import java.io.IOException;
import java.lang.Thread;
import java.io.*;

public class Model {

	private boolean server;
	private Socket s;
	private ServerSocket ss;

	public String getIp() {
		String ip = "";
		try (final DatagramSocket socket = new DatagramSocket()) {
			try {
				socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ip = socket.getLocalAddress().getHostAddress();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ip;
	}

	public InetAddress textToIp(String text) throws UnknownHostException {
		return InetAddress.getByName(text);
	}




	public void message(String message) {
		try {
			System.out.println("sending " + message);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(message);
			dos.flush();
			System.out.println("sended");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void startListener(View v) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

					try {
						if (s.getInputStream().available() != 0) {
							try {
								v.newMessage(new DataInputStream(s.getInputStream()).readUTF());
							} catch (IOException e) {
								v.newMessage("Fehler");
								e.printStackTrace();

							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});
		t.start();
	}
}