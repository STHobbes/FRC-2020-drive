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

  public Solenoid shifter = new Solenoid(Constants.Pneumatics.DRIVE_SHIFTER);
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
    setNeutralMode(NeutralMode.Brake);
    rightMaster.setInverted(InvertType.InvertMotorOutput);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Set the drive motor power based on an arcade control model of forward and turn speed.
   *
   * @param forward (double) forward speed in the range -1.0 to 1.0 (negative is
   *                backwards, positive is forward).
   * @param rotate  (double) rotation speed in the range -1.0 to 1.0 (negative is
   *                clockwise, positive is counter-clockwise).
   */
  public void setArcadePower(double forward, double rotate) {
    double max = Math.abs(forward) + (Math.abs(forward) * Math.abs(Constants.DRIVE_TURN_BIAS)) + Math.abs(rotate);
    double scale = (max <= 1.0) ? 1.0 : (1.0 / max);
    rightMaster.set(ControlMode.PercentOutput, scale * (forward + (rotate + (forward * Constants.DRIVE_TURN_BIAS))));
    leftMaster.set(ControlMode.PercentOutput, scale * (forward - (rotate + (forward * Constants.DRIVE_TURN_BIAS))));
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
  public double getLeftPosition() {
    return leftMaster.getSelectedSensorPosition();
  }

  public double getRightPosition() {
    return rightMaster.getSelectedSensorPosition();
  }

}
