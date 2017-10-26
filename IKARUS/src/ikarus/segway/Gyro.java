package ikarus.segway;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.Gyroscope;
import lejos.robotics.SampleProvider;

public class Gyro {

	private Gyroscope gyro;
	private EV3GyroSensor gyroSensor = null;
	private SampleProvider gyroSamples = null;
	private double gyroOffset;
	private double gyroAngle1 = 0;
	private double gyroSpeed, gyroAngle;
	private double timeInterval;// Zeit für Balance Schleifen

	public void init() {
		gyroSensor = new EV3GyroSensor(SensorPort.S2);
		System.out.println("Gyro init");
		gyroSamples = gyroSensor.getAngleMode();
	}

	public void getGyroOffset() {
		System.out.println("calibrating Offset...");
		gyro.recalibrateOffset();
	}

	public void updateGyroData() {
		float gyroRaw;
		final double DRIFT_OFFSET = 0.0000; // drift alle 5sek testen
		gyroRaw = gyro.getAngularVelocity();
		gyroOffset = DRIFT_OFFSET * gyroRaw + (1 - DRIFT_OFFSET) * gyroOffset;
		double gyroAngleSpeed = gyroRaw - gyroOffset;

		gyroAngle1 = gyroAngleSpeed * timeInterval;
		gyroAngle = gyroAngle1; // [winkel]

	}

}
