/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class AdjustkI extends AdjusterBase {

    private final DriveSubsystem m_drive;

  public AdjustkI(double deltakI, DriveSubsystem subsystem) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(deltakI);
    m_drive = subsystem;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    Constants.ROBOT.DRIVE_Ki += delta;
    m_drive.resetIntegral();
    return true;
  }
}