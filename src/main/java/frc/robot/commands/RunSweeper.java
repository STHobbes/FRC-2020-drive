package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

// This is a really quick qnd dirty command to hook the sweeper motor to the throttle for testing
// seeper operation and determining what are good power settings for input and output. There is no
// subsystem for the sweeper-collector-delivery mechanism yet.
public class RunSweeper extends CommandBase {

    private final Joystick m_stick;
    private TalonSRX m_sweeper = new TalonSRX(Constants.MotorControllers.COLLECTOR_SWEEPER);

    /**
     * Creates a new RunSweeper.
     */
    public RunSweeper(Joystick joyStick) {
        m_stick = joyStick;
        m_sweeper.configFactoryDefault();
        m_sweeper.setNeutralMode(NeutralMode.Brake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("RunSweeper.initialize");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        System.out.println("RunSweeper.execute");
        m_sweeper.set(ControlMode.PercentOutput,m_stick.getThrottle());
    }

    public double getPower() { return m_sweeper.getMotorOutputPercent(); }
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("RunSweeper.end");
        m_sweeper.set(ControlMode.PercentOutput,0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
