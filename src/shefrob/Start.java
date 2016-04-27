package shefrob;

/**
 * Created by Administrator on 2016/4/26 0026.
 */
public class Start {
	public static void main(String[] args) {
		final RobotTool robotTool = new RobotTool();
		Nfc leftController = new Nfc(robotTool);
		Nfc rightController = new Nfc(robotTool);
		new Thread(){
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()){
					robotTool.leftMove((int) robotTool.getDistances()[0]);
				}
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()){
					robotTool.rightMove((int) robotTool.getDistances()[1]);
				}
			}
		}.start();
	}
}
