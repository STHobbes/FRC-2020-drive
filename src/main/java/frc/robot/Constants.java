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
    public static double DRIVE_FORWARD_GAIN = 1.0;

    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_GAIN = 0.5;

    // The multiplier for full twist to give the power/speed differential requested from the drive.
    public static double DRIVE_TURN_AT_SPEED_GAIN = 0.1;

    // The center-stick sensitivity, which is really the exponent applied to the stick position to flatten drive
    // response to stick position for greater sensitivity at low speed.
    public static double DRIVE_SENSITIVITY = 2.0;

    // The width of the 0 dead-band of the stick as a fraction of full stick movement.
    public static double DRIVE_DEADBAND = 0.05;

    // The correction for the tendency of the robot to systemically turn as power is applied to the drive. This
    // tendency to turn can result from many factors - alignment, friction, motor differences, controller
    // differences, etc.
    public static double DRIVE_TURN_BIAS = 0.0;

    // -----------------------------------------------------------------------------------------------------------------------------
    // Tuning speed Drive tuning (using encoders and the Talon SRX PID control) - from 2019 summer sessions
    public static boolean DRIVE_ENCODER_PHASE = true;

    public static double DRIVE_KP = 0.0;

    public static double DRIVE_KI = 0.110;

    public static double DRIVE_KF = 0.129;

    public static double INTEGRAL_ZONE = 0.0;

    // MAX_SPEED of motors for SpeedDrive
    public static final double MAX_SPEED = 7800;

    // -----------------------------------------------------------------------------------------------------------------------------
    // Tuning IMU control of direction (heading)

}
