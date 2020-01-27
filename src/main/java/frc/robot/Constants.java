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

    public static final class MotorControllers {
        public static int
                DRIVE_RIGHT_MASTER = 1,
                DRIVE_RIGHT_SLAVE_1 = 2,
                DRIVE_RIGHT_SLAVE_2 = 3,
                DRIVE_LEFT_MASTER = 4,
                DRIVE_LEFT_SLAVE_1 = 5,
                DRIVE_LEFT_SLAVE_2 = 6;
    }

    public static final class Pneumatics {
        public static int DRIVE_SHIFTER = 0;

    }
    // -----------------------------------------------------------------------------------------------------------------------------
    // Conditioning stick values - constants used in the 2019 for stick tuning

    // The multiplier for full stick to give the power/speed requested from the drive.
    public static double DRIVE_SPEED_GAIN = 1.0;

    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_GAIN = 0.5;

    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_AT_SPEED_GAIN = 0.1;

    // The center-stick sensitivity, which is really the exponent applied to the stick position to flatten drive
    // response to stick position for greater sensitivity at low speed.
    public static double DRIVE_SPEED_SENSITIVITY = 2.0;

    public static double DRIVE_TURN_SENSITIVITY = 3.0;

    // The width of the 0 dead-band of the stick as a fraction of full stick movement.
    public static double DRIVE_SPEED_DEADBAND = 0.05;

    public static double DRIVE_TURN_DEADBAND = 0.05;


    public static boolean DRIVE_USE_TWIST = true;

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
        COMPETITION_ROBOT("competition", 0.0, 4.5, 2.5, 0.0, 0.0, 230.0),
        PRACTICE_ROBOT("practice", 0.019, 4.5, 2.5, 0.0, 0.0, 230.0);

        // The robot configuration that is running.
        public final String ROBOT_NAME;
        public final double DRIVE_TURN_BIAS;
        // The
        public final double DRIVE_Kf;
        public final double DRIVE_Kp;
        public final double DRIVE_Ki;
        public final double DRIVE_INTEGRAL_ZONE;
        public final double DRIVE_MAX_RPM;
        Robots(String name, double bias, double Kf, double Kp, double Ki, double integral_zone, double max_rpm) {
            ROBOT_NAME = name;
            DRIVE_TURN_BIAS = bias;
            DRIVE_Kf = Kf;
            DRIVE_Kp = Kp;
            DRIVE_Ki = Ki;
            DRIVE_INTEGRAL_ZONE = integral_zone;
            DRIVE_MAX_RPM = max_rpm;
        }

        public static Robots getNextRobot(Robots robot) {
            int index = robot.ordinal();
            int nextIndex = index + 1;
            Robots[] robots = Robots.values();
            nextIndex %= robots.length;
            return robots[nextIndex];
        }
    }

    public static Robots ROBOT = Robots.PRACTICE_ROBOT;

    // -----------------------------------------------------------------------------------------------------------------------------
    // Tuning IMU control of direction (heading)

}
