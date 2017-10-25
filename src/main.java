
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.utility.Timer;
import lejos.hardware.Power;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.Button;

public class main {

	public static void main(String[] args) {
		
		
		RegulatedMotor m1= new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor m2 = new EV3LargeRegulatedMotor(MotorPort.B);
		
		PID pid; 
		
		pid = new PID(0.25, 0.01, 0.4);
		pid.setOutputLimits(10);
		//miniPID.setMaxIOutput(2);
		//miniPID.setOutputRampRate(3);
		//miniPID.setOutputFilter(.3);
		pid.setSetpointRange(40);
		
		double sensor = 0;
		double target = 0;

		
		
		gyro gy = new gyro();
		
		gy.init();
		
		System.out.println("Roboter aufrecht ausrichten und einen Knopf betätigen");
		
		Button.waitForAnyPress();
		gy.resetGyro();

		int button = 0;
		
		while(((button = Button.waitForAnyPress(1)) == 0)){
			  //get some sort of sensor value
			sensor = gy.getGyroAngle();
			  //set some sort of target value
			  double output=pid.getOutput(sensor,target);
			  //do something with the output
			  //Delay(10);
			  System.out.println(output);
			  m1.rotate((int)output*5);
			  m2.rotate((int)output*5);
			}
		
		
		  while (((button = Button.waitForAnyPress(50)) == 0)) {
				System.out.println("gyro: ");
				System.out.println(gy.getGyroAngle());
			  }
		
	}

}
