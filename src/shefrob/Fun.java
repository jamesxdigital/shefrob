import java.awt.event.*;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Fun extends JFrame {
	int leftSpeed = 0;
	int rightSpeed = 0;
	static final int var = 50;
	RobotTool robotTool = new RobotTool();
	public Fun() {
		super("BlueRoverGUI");
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent keyEvent) {
				switch (keyEvent.getKeyCode()) {
				case KeyEvent.VK_UP:
					leftSpeed += var;
					rightSpeed += var;
					robotTool.moveSeparate(leftSpeed, rightSpeed);
					break;
				case KeyEvent.VK_DOWN:
					leftSpeed -= var;
					rightSpeed -= var;
					robotTool.moveSeparate(leftSpeed, rightSpeed);
					break;
				case KeyEvent.VK_LEFT:
					rightSpeed += var;
					robotTool.moveSeparate(leftSpeed, rightSpeed);
					break;
				case KeyEvent.VK_RIGHT:
					leftSpeed += var;
					robotTool.moveSeparate(leftSpeed, rightSpeed);
					break;
				case KeyEvent.VK_B:
					leftSpeed = 0;
					rightSpeed = 0;
					robotTool.moveSeparate(leftSpeed, rightSpeed);
					break;
				case KeyEvent.VK_G:
					leftSpeed = robotTool.getMaxSpeed();
					robotTool.moveTogether(leftSpeed);
					break;
				case KeyEvent.VK_H:
					leftSpeed = robotTool.getMaxSpeed();
					robotTool.moveTogether(-leftSpeed);
					break;
				}
			}
		});
	}

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Fun cont = new Fun();
				cont.setSize(500, 300);
				cont.setDefaultCloseOperation(EXIT_ON_CLOSE);
				cont.setVisible(true);
			}
		});
	}
}

