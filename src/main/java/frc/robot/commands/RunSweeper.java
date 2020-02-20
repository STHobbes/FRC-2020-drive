package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SweeperSubsystem;

// This is a really quick qnd dirty command to hook the sweeper motor to the throttle for testing
// sweeper operation and determining what are good power settings for input and output. There is no
// subsystem for the sweeper-collector-delivery mechanism yet.
public class RunSweeper extends CommandBase {

  private final Joystick m_stick;
  private final SweeperSubsystem m_sweeperSubsystem;

  /**
   * Creates a new RunSweeper.
   */
  public RunSweeper(SweeperSubsystem sweeperSubsystem, Joystick joyStick) {
    m_sweeperSubsystem = sweeperSubsystem;
    m_stick = joyStick;
    addRequirements(sweeperSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // What we are doing here is simple - we are setting the sweeper speed to the throttle of the joystick (the only
    // control that lets us easily set and hod a speed)
    m_sweeperSubsystem.setSweeperPower(m_stick.getThrottle());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_sweeperSubsystem.setSweeperPower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

