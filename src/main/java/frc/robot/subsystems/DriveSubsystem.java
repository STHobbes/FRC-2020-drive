/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveSubsystem extends SubsystemBase {

  private double targetLeftSpeed;
  private double targetRightSpeed;

  //public Solenoid shifter = new Solenoid(Constants.Pneumatics.DRIVE_SHIFTER);
  public TalonSRX rightMaster = new TalonSRX(Constants.MotorControllers.DRIVE_RIGHT_MASTER);
  public TalonSRX rm2 = new TalonSRX(Constants.MotorControllers.DRIVE_RIGHT_SLAVE_1);
  public TalonSRX rm3 = new TalonSRX(Constants.MotorControllers.DRIVE_RIGHT_SLAVE_2);
  public TalonSRX leftMaster = new TalonSRX(Constants.MotorControllers.DRIVE_LEFT_MASTER);
  public TalonSRX lm2 = new TalonSRX(Constants.MotorControllers.DRIVE_LEFT_SLAVE_1);
  public TalonSRX lm3 = new TalonSRX(Constants.MotorControllers.DRIVE_LEFT_SLAVE_2);

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    // constructs and configures all six drive motors
    // restore everything to known factory default state
    rightMaster.configFactoryDefault();
    rm2.configFactoryDefault();
    rm3.configFactoryDefault();
    leftMaster.configFactoryDefault();
    lm2.configFactoryDefault();
    lm3.configFactoryDefault();
    // now configure them
    rm2.follow(rightMaster);
    rm3.follow(rightMaster);
    lm2.follow(leftMaster);
    lm3.follow(leftMaster);
    rm2.setInverted(InvertType.FollowMaster);
    rm3.setInverted(InvertType.FollowMaster);
    lm2.setInverted(InvertType.FollowMaster);
    lm3.setInverted(InvertType.FollowMaster);
    setNeutralMode(NeutralMode.Coast);
    rightMaster.setInverted(InvertType.InvertMotorOutput);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    // setup the drivetrain constants specific to the robot ...
    setRobot();
    // reset encoders
    resetEncoders();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Set the drive motor power based on an arcade control model of forward and turn speed.
   *
   * @param forward (double) forward power in the range -1.0 to 1.0 (negative is
   *                backwards, positive is forward).
   * @param rotate  (double) rotation power in the range -1.0 to 1.0 (negative is
   *                clockwise, positive is counter-clockwise).
   */
  public void setArcadePower(double forward, double rotate) {
    double max = Math.abs(forward) + (Math.abs(forward) * Math.abs(Constants.ROBOT.DRIVE_TURN_BIAS)) + Math.abs(rotate);
    double scale = (max <= 1.0) ? 1.0 : (1.0 / max);
    rightMaster.set(ControlMode.PercentOutput, scale * (forward + (rotate + (forward * Constants.ROBOT.DRIVE_TURN_BIAS))));
    leftMaster.set(ControlMode.PercentOutput, scale * (forward - (rotate + (forward * Constants.ROBOT.DRIVE_TURN_BIAS))));
  }

  /**
   * Set the desired drive speed as a factor of maximum drive speed in the range 0 to 1 (0 to -1) where 0 is stopped and
   * 1 (-1) is the maximum forward (backward) speed that can be achieved by the drive.
   *
   * @param forward (double) forward speed in the range -1.0 to 1.0 (negative is
   *                backwards, positive is forward).
   * @param rotate  (double) rotation speed in the range -1.0 to 1.0 (negative is
   *                clockwise, positive is counter-clockwise).
   */
  public void setArcadeSpeed(double forward, double rotate) {

    double max = Math.abs(forward) + Math.abs(rotate);
    double scale = (max <= 1.0) ? 1.0 : (1.0 / max);

    targetRightSpeed = scale * (forward + rotate) * Constants.ROBOT.DRIVE_MAX_RPM;
    targetLeftSpeed = scale * (forward - rotate) * Constants.ROBOT.DRIVE_MAX_RPM;

    rightMaster.set(ControlMode.Velocity, targetRightSpeed);
    leftMaster.set(ControlMode.Velocity, targetLeftSpeed);
  }

  public void setNeutralMode(NeutralMode mode) {
    //method to easily set the neutral mode of all of the driveTrain motors
    rightMaster.setNeutralMode(mode);
    rm2.setNeutralMode(mode);
    rm3.setNeutralMode(mode);
    leftMaster.setNeutralMode(mode);
    lm2.setNeutralMode(mode);
    lm3.setNeutralMode(mode);
  }

  public void setRobot() {
    // right drivetrain TalonSRX PID
    rightMaster.config_kF(0, Constants.ROBOT.DRIVE_Kf * (1.0 + Constants.ROBOT.DRIVE_TURN_BIAS));
    rightMaster.config_kP(0, Constants.ROBOT.DRIVE_Kp);
    rightMaster.config_kI(0, Constants.ROBOT.DRIVE_Ki);
    rightMaster.config_IntegralZone(0, (int)(Constants.ROBOT.DRIVE_Ki * Constants.ROBOT.DRIVE_MAX_RPM));
    rightMaster.config_kD(0, 0);
    rightMaster.setSensorPhase(false);
    // left drivetrain TalonSRX PID
    leftMaster.config_kF(0, Constants.ROBOT.DRIVE_Kf * (1.0 - Constants.ROBOT.DRIVE_TURN_BIAS));
    leftMaster.config_kP(0, Constants.ROBOT.DRIVE_Kp);
    leftMaster.config_kI(0, Constants.ROBOT.DRIVE_Ki);
    leftMaster.config_IntegralZone(0, (int)(Constants.ROBOT.DRIVE_Ki * Constants.ROBOT.DRIVE_MAX_RPM));
    leftMaster.config_kD(0, 0);
    leftMaster.setSensorPhase(false);
  }

  /**
   *
   * @return
   */
  public double getRightPosition() {
    return rightMaster.getSelectedSensorPosition();
  }

  /**
   *
   * @return
   */
  public double getLeftPosition() {
    return leftMaster.getSelectedSensorPosition();
  }

  /**
   *
   */
  public void resetEncoders() {
    rightMaster.setSelectedSensorPosition(0, 0, 10);
    leftMaster.setSelectedSensorPosition(0, 0, 10);
  }

  /**
   *
   * @return
   */
  public double getRightSpeed() {
    return rightMaster.getSelectedSensorVelocity();
  }

  /**
   *
   * @return
   */
  public double getLeftSpeed() {
    return leftMaster.getSelectedSensorVelocity();
  }

  /**
   *
   * @return
   */
  public double getTargetRightSpeed() {
    return targetRightSpeed;
  }

  /**
   *
   * @return
   */
  public double getTargetLeftSpeed() {
    return targetLeftSpeed;
  }

  /**
   *
   */
  public void resetIntegral() {
    rightMaster.setIntegralAccumulator(0);
    leftMaster.setIntegralAccumulator(0);
  }
}
