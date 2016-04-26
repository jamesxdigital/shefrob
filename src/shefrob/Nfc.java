import ShefRobot.Speaker;

public class Nfc {
	private static float r; // reference
	private static float kp = 200;// proportionality constant be controllable by user
	private static float e;// error signal
	public static float controller(float i, Speaker emmotion){
		r = (float) 0.35;
		float speed = kp*(r-i);
		emmotion.setVolume((int) (50*(r-i+1)));
		emmotion.playTone((int) (200*(r-i+1)), 100);
		return speed;
	}
}
