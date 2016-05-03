import java.util.Scanner;

/**
 * Created by Administrator on 2016/4/26 0026.
 */
public class Start {
	public static void main(String[] args) throws InterruptedException {
		final RobotTool robotTool = new RobotTool();
		final Nfc both = new Nfc(robotTool);
		final Nfc leftController = new Nfc(robotTool);
		final Nfc rightController = new Nfc(robotTool);
		Scanner reader = new Scanner(System.in);
		System.out.println("Please input which vehical u want to test eg. a1 a2 b2 a3 b3 c3 test");
		String input = reader.next();
		Vehicle type = Vehicle.valueOf(input);
		switch(type){
		case a1:
			robotTool.setupColorSensor();
			while(!Thread.currentThread().isInterrupted()){
				robotTool.playSound((int) (robotTool.getLightValue()*1500 + 1), 100, (int) (robotTool.getLightValue() * 50 + 10));
				robotTool.moveTogether((int) both.lightController(robotTool.getLightValue(), 0.5f));
			}
			break;
		case a2:
			robotTool.setupUltrasonic();
			new Thread(){
				@Override
				public void run() {
					while(!Thread.currentThread().isInterrupted()){
						robotTool.leftMove((int) leftController.ultraController(robotTool.getDistances()[0]));
						robotTool.playSound(
								1+((int) ((leftController.ultraController(robotTool.getDistances()[0])+rightController.ultraController(robotTool.getDistances()[1]))*25.0f)) 
								, 100, 30);
					}
				}
			}.start();
			new Thread(){
				@Override
				public void run() {
					while(!Thread.currentThread().isInterrupted()){
						robotTool.rightMove((int) rightController.ultraController(robotTool.getDistances()[1]));
					}
				}
			}.start();
			Thread.sleep(500000);
			break;
		case b2:
			robotTool.setupUltrasonic();
			new Thread(){
				@Override
				public void run() {
					while(!Thread.currentThread().isInterrupted()){
						robotTool.leftMove((int) leftController.ultraController(robotTool.getDistances()[1]));
					}
				}
			}.start();
			new Thread(){
				@Override
				public void run() {
					while(!Thread.currentThread().isInterrupted()){
						robotTool.rightMove((int) rightController.ultraController(robotTool.getDistances()[0]));
					}
				}
			}.start();
			Thread.sleep(500000);
			break;
		case a3:
			break;
		case b3:
			robotTool.leftMove(100);
			break;
		case test:
			int freq = 1;
			while(freq < 20000){
			robotTool.playSound(freq, 100, 15);
			robotTool.sleep(100);
			freq += 20;
			}
			break;
		default:
			break;
		}

	}
	public enum Vehicle{
		a1, a2, b2, a3, b3, c3,test
	}
}
