package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Run the drive motors at full power until the joystick trigger is released.
 */
public class RunDriveAtMaxPower extends CommandBase {
  private final DriveSubsystem m_driveSubsystem;
  private final Joystick m_stick;

  public RunDriveAtMaxPower(DriveSubsystem driveSubsystem, Joystick stick) {
    m_driveSubsystem = driveSubsystem;
    m_stick = stick;
    addRequirements(driveSubsystem);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    m_driveSubsystem.setArcadePower(1.0, 0.0);
  }

  @Override
  public boolean isFinished() {
    // we are done when the trigger is released
    return !m_stick.getTrigger();
  }

  @Override
  public void end(boolean interrupted) {

  }
}
