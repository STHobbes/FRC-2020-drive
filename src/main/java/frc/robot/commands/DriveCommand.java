/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {

  private final Joystick m_stick;
  private final DriveSubsystem m_driveSubsystem;

  /**
   * Creates a new Drive.
   */
  public DriveCommand(DriveSubsystem driveSubsystem, Joystick joyStick) {
    m_driveSubsystem = driveSubsystem;
    m_stick = joyStick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get stick values and set the signs to match the arcade drive forward,rotate conventions
    double stickSpeed = -m_stick.getY();
    double stickTurn = Constants.DRIVER.DRIVE_USE_TWIST ? -m_stick.getTwist() : -m_stick.getX();
    // subtract the dead band and scale what is left outside the dead band
    double speedSignMult = (stickSpeed > 0.0) ? 1.0 : -1.0;
    double turnSignMult = (stickTurn > 0.0) ? 1.0 : -1.0;
    double useSpeed = (Math.abs(stickSpeed) <= Constants.DRIVER.DRIVE_SPEED_DEADBAND) ? 0.0 :
        (Math.abs(stickSpeed) - Constants.DRIVER.DRIVE_SPEED_DEADBAND) / (1.0 - Constants.DRIVER.DRIVE_SPEED_DEADBAND);
    double useTurn = (Math.abs(stickTurn) <= Constants.DRIVER.DRIVE_TURN_DEADBAND) ? 0.0 :
        (Math.abs(stickTurn) - Constants.DRIVER.DRIVE_TURN_DEADBAND) / (1.0 - Constants.DRIVER.DRIVE_TURN_DEADBAND);
    // do the sensitivity power function
    useSpeed = Math.pow(useSpeed, Constants.DRIVER.DRIVE_SPEED_SENSITIVITY);
    useTurn = Math.pow(useTurn, Constants.DRIVER.DRIVE_TURN_SENSITIVITY);
    // apply the gains
    double forward = useSpeed * speedSignMult * Constants.DRIVER.DRIVE_SPEED_GAIN;
    double rotate = m_stick.getRawButton(2) ? 0.0 : useTurn * turnSignMult *
        (Constants.DRIVER.DRIVE_TURN_GAIN +
            (useSpeed * (Constants.DRIVER.DRIVE_TURN_AT_SPEED_GAIN - Constants.DRIVER.DRIVE_TURN_GAIN)));
    // Now set the speeds
    m_driveSubsystem.setArcadeSpeed(forward, rotate);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
