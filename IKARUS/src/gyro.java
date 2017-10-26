
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;


public class gyro {

   private EV3GyroSensor gyro = null;
   private SampleProvider gyroSamples = null;
   float[] angle = { 0.0f };
   float gyroTacho = 0;

   
   public void resetGyro() {
      if (gyro != null) {
         
         gyro.reset();
         gyroSamples = gyro.getAngleMode();
         gyroTacho = 0;
         System.out.println("Gyro is reset");
      }
   }
   
   public void resetGyroTacho() {
      gyroTacho += getGyroAngle();
   }

   public void init() {
      gyro = new EV3GyroSensor(SensorPort.S2);
      System.out.println("Gyro init");
      gyroSamples = gyro.getAngleMode();
   }
   
   public float getGyroAngleRaw() {
      gyroSamples.fetchSample(angle, 0);
      return angle[0];
   }

   public float getGyroAngle() {
      float rawAngle = getGyroAngleRaw();
      return rawAngle - gyroTacho;
      }
   }
