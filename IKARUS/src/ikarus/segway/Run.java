package ikarus.segway;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;

public class Run {
	public static void main(String[] args) {
		
		EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S2);
		System.out.println("Gyro init");
		System.out.println("Press to start");
		
		Button.LEDPattern(2);
		Sound.beepSequenceUp();
		
		Button.waitForAnyPress();
		
		
		
		
		
		
		
		
		
		
		
		Sound.beepSequence();
	}
}
