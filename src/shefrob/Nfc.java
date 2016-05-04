import ShefRobot.*;

public class Nfc extends Thread{
	private float r; // reference
	private float kp = 200;// proportionality constant be controllable by user
	private float e;// error signal
	private float i;// intention
	private RobotTool robotTool;
	public Nfc(RobotTool robotTool){
		this.robotTool = robotTool;
	}
	public float ultraController(float i) {
		//distance from 0 to infinity
		//light from 0 to 1 (dark to light)
		this.i = i;
		r = 0;
		e = r - this.i + 2.5f;
		float speed = kp * e;
		//robotTool.playSound((int) (200 * (r - this.i + 1)), 200, 2);
		return speed;
	}
	public float lightController(float i, float r){
		this.i = i;
		e = r - this.i;
		float speed = kp * e;
		//robotTool.playSo
		return speed;
	}
}
