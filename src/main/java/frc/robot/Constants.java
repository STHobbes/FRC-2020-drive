/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // -----------------------------------------------------------------------------------------------------------------------------
  // Physical Mappings - where are motors, pneumatics, sensors, and servos connected to the electronics

  public static Drivers DRIVER = Drivers.ADEN;
  public static Robots ROBOT = Robots.COMPETITION_ROBOT;

  // -----------------------------------------------------------------------------------------------------------------------------
  // Driver Configurations
  // -----------------------------------------------------------------------------------------------------------------------------
  // Conditioning stick values - constants used in the 2019 for stick tuning. We found that it was important for each
  // driver to tune the drive for their driving style so the
  public enum Drivers {
    ADEN("Aden", true, 1.0, 0.5, 0.5, 2.0, 3.0, 0.05, 0.05),
    LUCAS("Lucas", true, 1.0, 0.5, 0.1, 2.0, 3.0, 0.05, 0.05),
    ROY("Roy", false, 1.0, 0.5, 0.1, 2.0, 3.0, 0.05, 0.05);

    // The driver name presented on the dashboard.
    public final String DRIVER_NAME;

    // If true, use the joystick twist for turn control; if false yous the stick X value for turn control.
    public final boolean DRIVE_USE_TWIST;

    // The multiplier for full stick to give the power/speed requested from the drive.
    public final double DRIVE_SPEED_GAIN;

    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public final double DRIVE_TURN_GAIN;

    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public final double DRIVE_TURN_AT_SPEED_GAIN;

    // The width of the 0 dead-band of the speed stick as a fraction of full stick movement.
    public final double DRIVE_SPEED_DEADBAND;

    // The width of the 0 dead-band of the turn control as a fraction of full control movement.
    public final double DRIVE_TURN_DEADBAND;

    // The center-stick sensitivity for forward-reverse control, which is really the exponent applied to the stick
    // position to flatten drive response to stick position for greater sensitivity at low speed.
    public final double DRIVE_SPEED_SENSITIVITY;

    // The center-stick sensitivity for turn control, which is really the exponent applied to the stick
    // position to flatten drive response to stick position for greater sensitivity at low speed.
    public final double DRIVE_TURN_SENSITIVITY;

    Drivers(String driverName, boolean useTwist, double speedGain, double turnGain, double turnAtSpeedGain,
            double forwardSensitivity, double turnSensitivity, double speedDeadband, double turnDeadband) {
      DRIVER_NAME = driverName;
      DRIVE_USE_TWIST = useTwist;
      DRIVE_SPEED_GAIN = speedGain;
      DRIVE_TURN_GAIN = turnGain;
      DRIVE_TURN_AT_SPEED_GAIN = turnAtSpeedGain;
      DRIVE_SPEED_SENSITIVITY = forwardSensitivity;
      DRIVE_TURN_SENSITIVITY = turnSensitivity;
      DRIVE_SPEED_DEADBAND = speedDeadband;
      DRIVE_TURN_DEADBAND = turnDeadband;
    }

    public static Drivers getNextDriver(Drivers driver) {
      int index = driver.ordinal();
      int nextIndex = index + 1;
      Drivers[] drivers = Drivers.values();
      nextIndex %= drivers.length;
      return drivers[nextIndex];
    }
  }

  // -----------------------------------------------------------------------------------------------------------------------------
  // Robot Configurations
  // -----------------------------------------------------------------------------------------------------------------------------
  // We have a competition robot and a test robot. It is unclear which parts of the competition will be reproduced on the test
  // robot. We do know that right now the test robot is available for driver practice and tuning. This is an enumeration of our
  //  robots and the characteristics specific to each.
  //
  // - Tuning speed Drive tuning (using encoders and the Talon SRX PID control) - from 2019 summer sessions
  //   - We have noted that each 2-speed 3-wheel drive has different characteristics (motor, assembly, drag, belt tensioning,
  //     etc.) that gives them a different performance character.  The DRIVE_TURN_BIAS is the performance difference between
  //     the left and right drive trains of the robot.
  //   - Kf -
  //   - Kp -
  //   - Ki -
  //   - integral_zone -
  public enum Robots {
    COMPETITION_ROBOT("competition", 0.009, true, 0.131, 0.09, 0.0, 0.0, 7800.0, 1781.87, 457.95),
    PRACTICE_ROBOT("practice", 0.019, false, 4.5, 2.5, 0.0, 0.0, 230.0, 52.54, 13.5);

    // The robot configuration that is running.
    public final String ROBOT_NAME;
    // The setup of the drive PID for the Talon SRX
    public final double DRIVE_TURN_BIAS;
    public final boolean DRIVE_ENCODER_PHASE;
    public final double DRIVE_Kf;
    public final double DRIVE_Kp;
    public final double DRIVE_Ki;
    public final double DRIVE_INTEGRAL_ZONE;
    public final double DRIVE_MAX_RPM;
    // The encoder values for autonomous move some distance and turn some degrees.
    public final double DRIVE_TICS_PER_INCH;
    public final double DRIVE_TICS_PER_DEGREE;

    Robots(String robotName, double bias, boolean encoderPhase, double Kf, double Kp, double Ki,
           double integralZone, double maxRpm, double ticsPerInch, double ticsPerDegree) {
      ROBOT_NAME = robotName;
      DRIVE_TURN_BIAS = bias;
      DRIVE_ENCODER_PHASE = encoderPhase;
      DRIVE_Kf = Kf;
      DRIVE_Kp = Kp;
      DRIVE_Ki = Ki;
      DRIVE_INTEGRAL_ZONE = integralZone;
      DRIVE_MAX_RPM = maxRpm;
      DRIVE_TICS_PER_INCH = ticsPerInch;
      DRIVE_TICS_PER_DEGREE = ticsPerDegree;
    }

    public static Robots getNextRobot(Robots robot) {
      int index = robot.ordinal();
      int nextIndex = index + 1;
      Robots[] robots = Robots.values();
      nextIndex %= robots.length;
      return robots[nextIndex];
    }
  }

  public static final class MotorControllers {
    public static int
        DRIVE_RIGHT_MASTER = 1,
        DRIVE_RIGHT_SLAVE_1 = 2,
        DRIVE_RIGHT_SLAVE_2 = 3,
        DRIVE_LEFT_MASTER = 4,
        DRIVE_LEFT_SLAVE_1 = 5,
        DRIVE_LEFT_SLAVE_2 = 6,
        COLLECTOR_POSITION = 7,
        COLLECTOR_SWEEPER = 8;
  }

  public static final class Pneumatics {
    public static int DRIVE_SHIFTER = 0;

  }

  // -----------------------------------------------------------------------------------------------------------------------------
  // Tuning IMU control of direction (heading)

}
