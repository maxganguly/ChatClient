package Version1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Listener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		MessagePanel src =  (MessagePanel) arg0.getSource();
		try {
			System.out.println("cmd/ start " + System.getProperty("java.io.tmpdir") + "texttospeech.vbs " + src.message.replace(" ", ""));
			Runtime.getRuntime().exec("cmd /c start " + System.getProperty("java.io.tmpdir") + "texttospeech.vbs " + src.message.replace(" ", ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
