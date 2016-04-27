package shefrob;

import ShefRobot.*;
import ShefRobot.Sensor;
import sun.management.*;

public class RobotTool {
	private Robot myRobot = new Robot();
	private Motor leftMotor = myRobot.getLargeMotor(Motor.Port.B);
	private Motor rightMotor = myRobot.getLargeMotor(Motor.Port.C);
	private Speaker speaker = myRobot.getSpeaker();
	private ColorSensor lightSensor = myRobot.getColorSensor(Sensor.Port.S1);
	private UltrasonicSensor leftUltraSensor = myRobot.getUltrasonicSensor(Sensor.Port.S2);
	private UltrasonicSensor rightUltraSensor = myRobot.getUltrasonicSensor(Sensor.Port.S3);

	public RobotTool() {
		lightSensor.setMode(ColorSensor.Mode.AMBIENT);
	}

	public void playSound(int freq, int duration) {
		speaker.playTone(freq, duration);
	}

	public void playSound(int freq, int duration, int volume) {
		speaker.setVolume(volume);
		speaker.playTone(freq, duration);
	}

	public void moveTogether(int speed) {
		leftMotor.setSpeed(Math.abs((speed)));
		rightMotor.setSpeed(Math.abs((speed)));
		if (speed >= 0) {
			leftMotor.forward();
			rightMotor.forward();
		}
		else {
			leftMotor.backward();
			rightMotor.backward();
		}
	}

	public void moveSeparate(int leftSpeed, int rightSpeed) {
		leftMotor.setSpeed(Math.abs((leftSpeed)));
		rightMotor.setSpeed(Math.abs((rightSpeed)));
		if (leftSpeed >= 0)
			leftMotor.forward();
		else
			leftMotor.backward();
		if (rightSpeed >= 0)
			rightMotor.forward();
		else
			rightMotor.backward();
	}

	public void leftMove(int leftSpeed){
		leftMotor.setSpeed(Math.abs((leftSpeed)));
		if (leftSpeed >= 0)
			leftMotor.forward();
		else
			leftMotor.backward();
	}

	public void rightMove(int rightSpeed){
		rightMotor.setSpeed(Math.abs((rightSpeed)));
		if (rightSpeed >= 0)
			rightMotor.forward();
		else
			rightMotor.backward();
	}

	public void stopMoving() {
		leftMotor.stop();
		rightMotor.stop();
	}

	public float getLightValue() {
		return lightSensor.getAmbient();
	}

	public float[] getDistances() {
		float distances[] = new float[2];
		distances[0] = leftUltraSensor.getDistance();
		distances[1] = rightUltraSensor.getDistance();
		return distances;
	}

	public void turnOff() {
		myRobot.close();
	}
}
