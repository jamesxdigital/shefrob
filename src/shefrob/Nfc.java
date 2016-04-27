package shefrob;

import ShefRobot.*;

public class Nfc extends Thread{
	private float r; // reference
	private float kp = 200;// proportionality constant be controllable by user
	private float e;// error signal
	private RobotTool robotTool;
	public Nfc(RobotTool robotTool){
		this.robotTool = robotTool;
	}
	public float controller(float i) {
		r = (float) 0.35;
		e = r - i;
		float speed = kp * e;
		robotTool.playSound((int) (200 * (r - i + 1)), 200);
		return speed;
	}
}
