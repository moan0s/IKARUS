package ikarus.segway;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Balance extends Gyro {
	
	private double mPos = 0;
	private long mSum = 0, mPrevSum;
	private long mDiff;
	private long mDeltaP3 = 0;
	private long mDeltaP2 = 0;
	private long mDeltaP1 = 0;

	RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);

	private void updateMotorData() {
		
		long mLeft, mRight, mDelta;
		
		// Motorposition & Geschwindigkeit
		mLeft = leftMotor.getTachoCount();
		mRight = rightMotor.getTachoCount();
		
		mPrevSum = mSum;
		mSum = mLeft + mRight;
		mDiff = mLeft - mRight;
		mDelta = mSum - mPrevSum;
		mPos += mDelta + mPos;
		
	}

	
	
	public void balance() {

		System.out.println("I am balancing...");

		// Motoren auf Startposition
		leftMotor.resetTachoCount();
		rightMotor.resetTachoCount();

		// Balance-loop
		while (true) {
			updateGyroData();
		}
	}
}
