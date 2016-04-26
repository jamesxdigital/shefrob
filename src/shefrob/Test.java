
import ShefRobot.*;
import java.math.*;
/**
 *
 * @author sdn
 */
public class Test {
	
    /**
     * @param args the command line arguments
     */
	public static Robot myRobot = new Robot();
	public static Motor leftMotor = myRobot.getLargeMotor(Motor.Port.B);
	public static Motor rightMotor = myRobot.getLargeMotor(Motor.Port.C);
	public static Speaker speaker = myRobot.getSpeaker();
	public static ColorSensor lightSenser = myRobot.getColorSensor(Sensor.Port.S1);
	public static void moveTogether(int speed){
        leftMotor.setSpeed(Math.abs((speed)));
        rightMotor.setSpeed(Math.abs((speed)));
        if(speed >= 0){
	        leftMotor.forward();
	        rightMotor.forward();
        }
        else{
        	leftMotor.backward();
	        rightMotor.backward();
        }
	}
    public static void main(String[] args) {

        //Create a robot object to use and connect to it
        //The robot is made of components which are themselves objects.
        //Create references to them as useful shortcuts
        //Go Forwards
        int i = 0;
        while(i <= 1000){
        	moveTogether((int) Nfc.controller(lightSenser.getAmbient(),speaker));
        	i++;
        };
        //Keep going for 5 seconds
        myRobot.sleep(5000);

        //Stop
        leftMotor.stop();
        rightMotor.stop();
        
        //Disconnect from the Robot
        myRobot.close();

    }

}