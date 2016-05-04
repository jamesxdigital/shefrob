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
						if(robotTool.getDistances()[1] <= 0.2f){
							robotTool.playSound(1000, 100, 30);
						}
						if(robotTool.touched()){
							robotTool.stopMoving();
							break;
						}
					}
				}
			}.start();
			new Thread(){
				@Override
				public void run() {
					while(!Thread.currentThread().isInterrupted()){
						robotTool.rightMove((int) rightController.ultraController(robotTool.getDistances()[1]));
						if(robotTool.getDistances()[1] <= 0.2f){
							robotTool.playSound(1000, 100, 30);
						}
						if(robotTool.touched()){
							robotTool.stopMoving();
							break;
						}
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
						if(robotTool.getDistances()[1] <= 0.2f){
							robotTool.playSound(1000, 100, 30);
						}
					}
				}
			}.start();
			new Thread(){
				@Override
				public void run() {
					while(!Thread.currentThread().isInterrupted()){
						robotTool.rightMove((int) rightController.ultraController(robotTool.getDistances()[0]));
						if(robotTool.getDistances()[0] <= 0.2f){
							System.out.println("Distance = " + robotTool.getDistances()[0]);
							robotTool.playSound(1000, 100, 30);
						}
					}
				}
			}.start();
			Thread.sleep(500000);
			break;
		case a3:
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
		case b3:
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
		case c3:
			//left
			robotTool.setupC3();
			new Thread(){
				public void run(){
					while(!Thread.currentThread().isInterrupted()){
						robotTool.rightMove((int) rightController.lightController(robotTool.getLightValue(), 2*robotTool.getLightValue()));
					}
				}
			}.start();
			new Thread(){
				public void run(){
					while(!Thread.currentThread().isInterrupted()){
						robotTool.leftMove((int) leftController.ultraController(robotTool.getRightDistance()));
					}
				}
			}.start();
			Thread.sleep(500000);
			break;
		default:
			break;
		}

	}
	public enum Vehicle{
		a1, a2, b2, a3, b3, c3,test
	}
}
