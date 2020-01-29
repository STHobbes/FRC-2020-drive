package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

// This is a really quick qnd dirty command to hook the sweeper motor to the throttle for testing
// seeper operation and determining what are good power settings for input and output. There is no
// subsystem for the sweeper-collector-delivery mechanism yet.
public class RunSweeper extends CommandBase {

    private RobotContainer m_container;

    /**
     * Creates a new RunSweeper.
     */
    public RunSweeper(RobotContainer container) {
        // initiaslize the motor controller - this will go into the subsystem later
        m_container = container;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Constants.DRIVER = Constants.Drivers.getNextDriver(Constants.DRIVER);
        m_container.resetDriver();
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
