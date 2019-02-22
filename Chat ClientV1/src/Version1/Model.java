package Version1;
import java.net.*;
import java.net.InetAddress;
import java.util.*;
import java.io.IOException;
import java.lang.Thread;
import java.io.*;
public class Model {

	private boolean server;
	private Socket s;
	private ServerSocket ss;
	public String getIp() {
		try {
		    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		    while (interfaces.hasMoreElements()) {
		        NetworkInterface iface = interfaces.nextElement();
		        if (iface.isLoopback() || !iface.isUp() || iface.isVirtual() || iface.isPointToPoint())
		            continue;

		        Enumeration<InetAddress> addresses = iface.getInetAddresses();
		        while(addresses.hasMoreElements()) {
		            InetAddress addr = addresses.nextElement();

		            final String ip = addr.getHostAddress();
		            if(Inet4Address.class == addr.getClass()) return ip;
		        }
		    }
		} catch (SocketException e) {
		    throw new RuntimeException(e);
		}
		return null;
	}
	public InetAddress texttoip(String text) throws UnknownHostException{
		return InetAddress.getByName(text);
	}
	public void makeserver() throws IOException{
		this.ss = new ServerSocket(666);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					s = ss.accept();
				} catch (IOException e) {
					
				}
				
			}
		});
		t.run();
		
		server = true;
	}
	public void connectclient(InetAddress addr) throws Exception{
		this.s = new Socket(addr,666);
		server = false;
	}
	public void message(String message) {
		try {
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(message);
			dos.flush();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void getmessage(View v) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
				v.newMessage(new DataInputStream(s.getInputStream()).readUTF());
				}catch(IOException e) {
				v.newMessage("Fehler");
				}
				
			}
		});
		t.run();
	}
}
