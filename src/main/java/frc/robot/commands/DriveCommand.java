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
  public DriveCommand(DriveSubsystem driveSubystem, Joystick joyStick) {
    m_driveSubsystem = driveSubystem;
    m_stick = joyStick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get stick values and set the signs to match the arcade drive forward,rotate conventions
    double stickY = -m_stick.getY();
    double twist = -m_stick.getTwist();
    double bias = m_stick.getThrottle();
    Constants.DRIVE_TURN_BIAS = .25 * bias;
    // subtract the dead band and scale what is left outside the dead band
    double ySignMult = (stickY > 0.0) ? 1.0 : -1.0;
    double twistSignMult = (twist > 0.0) ? 1.0 : -1.0;
    double useY = (Math.abs(stickY) <= Constants.DRIVE_DEADBAND) ? 0.0 :
            (Math.abs(stickY) - Constants.DRIVE_DEADBAND) / (1.0 - Constants.DRIVE_DEADBAND);
    double useTwist = (Math.abs(twist) <= Constants.DRIVE_DEADBAND) ? 0.0 :
            (Math.abs(twist) - Constants.DRIVE_DEADBAND) / (1.0 - Constants.DRIVE_DEADBAND);
    // do the sensitivity power function
    useY = Math.pow(useY, Constants.DRIVE_SENSITIVITY);
    useTwist = Math.pow(useTwist, Constants.DRIVE_SENSITIVITY);
    // apply the gains
    double forward = useY * Constants.DRIVE_FORWARD_GAIN * ySignMult;
    double rotate = m_stick.getRawButton(2) ? 0.0 :
            ((useTwist * Constants.DRIVE_TURN_GAIN) -
              (useY * (Constants.DRIVE_TURN_GAIN - Constants.DRIVE_TURN_AT_SPEED_GAIN))) * twistSignMult;
    // Now set the speeds
    m_driveSubsystem.setArcadePower(forward, rotate);
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
