package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

// This is a really quick qnd dirty command to hook the sweeper motor to the driver joystic throttle, and the arm position
// to the XBox controller right stick Y for testing. This is completely manual control so the builder/testers can
// move stuff and describe to the programmers what it is they want programmed - i.e. what operations are there and what
// buttons/sticks are they connected to.
public class ManualCollector extends CommandBase {

  private final XboxController m_xbox;
  private final ArmSubsystem m_armSubsystem;

  /**
   * @param armSubsystem
   * @param xbox
   */
  public ManualCollector(ArmSubsystem armSubsystem, XboxController xbox) {
    m_xbox = xbox;
    m_armSubsystem = armSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // What we are doing here is simple - we are setting the position motor power based on the setting of the
    // xbox right stick Y
    double stickY = m_xbox.getY(GenericHID.Hand.kRight);
    double powerSignMult = (stickY > 0.0) ? 1.0 : -1.0;
    double usePower = Math.pow((Math.abs(0.75 * stickY)), 2.0);
    m_armSubsystem.setPositionPower(usePower * powerSignMult);
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

