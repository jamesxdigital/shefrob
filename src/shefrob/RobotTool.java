import ShefRobot.*;

public class RobotTool {
	private Robot myRobot = new Robot();
	private Motor leftMotor = myRobot.getLargeMotor(Motor.Port.B);
	private Motor rightMotor = myRobot.getLargeMotor(Motor.Port.C);
	private Speaker speaker = myRobot.getSpeaker();
	private ColorSensor lightSensor;
	private UltrasonicSensor leftUltraSensor;
	private UltrasonicSensor rightUltraSensor;
	private TouchSensor touchSensor = myRobot.getTouchSensor(Sensor.Port.S1);
	
	public void setupUltrasonic(){
		leftUltraSensor = myRobot.getUltrasonicSensor(Sensor.Port.S2);
		rightUltraSensor = myRobot.getUltrasonicSensor(Sensor.Port.S3);

	}
	
	public void setupC3(){
		lightSensor = myRobot.getColorSensor(Sensor.Port.S4);
		lightSensor.setMode(ColorSensor.Mode.AMBIENT);
		leftUltraSensor = myRobot.getUltrasonicSensor(Sensor.Port.S2);
	}
	
	public void setupColorSensor(){
		lightSensor = myRobot.getColorSensor(Sensor.Port.S4);
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
		if(Math.abs(leftSpeed) > leftMotor.getMaxSpeed())
			leftMotor.setSpeed(leftMotor.getMaxSpeed());
		else if(Math.abs(leftSpeed) <= 1000 && Math.abs(leftSpeed) >=0)
			leftMotor.setSpeed(Math.abs((leftSpeed)));
		if (leftSpeed >= 0)
			leftMotor.forward();
		else
			leftMotor.backward();
	}

	public void rightMove(int rightSpeed){
		if(Math.abs(rightSpeed) > rightMotor.getMaxSpeed())
			rightMotor.setSpeed(rightMotor.getMaxSpeed());
		else if(Math.abs(rightSpeed) <= 1000 && Math.abs(rightSpeed) >=0)
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
		distances[0] = (leftUltraSensor.getDistance());
		distances[1] = (rightUltraSensor.getDistance());
		return distances;
	}
	
	public float getRightDistance(){
		return leftUltraSensor.getDistance();
	}
	
	public boolean touched(){
		return touchSensor.isTouched();
	}
	
	public void turnOff() {
		myRobot.close();
	}
	public void sleep(int mtime){
		myRobot.sleep(mtime);
		System.out.println("WAKE UP!!");
	}
	public int getMaxSpeed(){
		return leftMotor.getMaxSpeed();
	}
}
