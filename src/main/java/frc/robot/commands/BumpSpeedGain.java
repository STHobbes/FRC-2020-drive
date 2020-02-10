/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class BumpSpeedGain extends CommandBase {

  final double m_inc;

  /**
   * Creates a new BumpDriveGain.
   */
  public BumpSpeedGain(double inc) {
    m_inc = inc;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Constants.DRIVER.DRIVE_SPEED_GAIN += m_inc;
    if (Constants.DRIVER.DRIVE_SPEED_GAIN > 1.0) {
      Constants.DRIVER.DRIVE_SPEED_GAIN = 1.0;
    } else if (Constants.DRIVER.DRIVE_SPEED_GAIN < 0.1) {
      Constants.DRIVER.DRIVE_SPEED_GAIN = 0.1;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
