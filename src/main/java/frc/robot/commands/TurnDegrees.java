package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.NavX;
import frc.robot.subsystems.DriveSubsystem;

public class TurnDegrees extends CommandBase {

  DriveSubsystem m_driveSubsystem;

  public TurnDegrees(DriveSubsystem driveSubsystem, double degrees) {
    m_driveSubsystem = driveSubsystem;
    NavX.HeadingInfo headingInfo = NavX.getInstance().getHeadingInfo();

  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {

  }

  @Override
  public boolean isFinished() {
    // TODO: Make this return true when this Command no longer needs to run execute()
    return false;
  }

  @Override
  public void end(boolean interrupted) {

  }
}
