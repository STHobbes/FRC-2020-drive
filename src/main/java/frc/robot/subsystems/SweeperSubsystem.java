package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SweeperSubsystem extends SubsystemBase {

  /**
   * The Singleton instance of this CollectorSubsystem. External classes should
   * use the {@link #getInstance()} method to get the instance.
   */
  private final static SweeperSubsystem INSTANCE = new SweeperSubsystem();
  private TalonSRX m_sweeper = new TalonSRX(Constants.MotorControllers.COLLECTOR_SWEEPER);

  /**
   * Creates a new instance of this CollectorSubsystem.
   * This constructor is private since this class is a Singleton. External classes
   * should use the {@link #getInstance()} method to get the instance.
   */
  private SweeperSubsystem() {
    // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
    //       in the constructor or in the robot coordination class, such as RobotContainer.
    //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
    //       such as SpeedControllers, Encoders, DigitalInputs, etc.
    m_sweeper.configFactoryDefault();
    m_sweeper.setNeutralMode(NeutralMode.Brake);

  }

  /**
   * Returns the Singleton instance of this CollectorSubsystem. This static method
   * should be used -- {@code CollectorSubsystem.getInstance();} -- by external
   * classes, rather than the constructor to get the instance of this class.
   */
  public static SweeperSubsystem getInstance() {
    return INSTANCE;
  }

  public double getSweeperPower() {
    return m_sweeper.getMotorOutputPercent();
  }

  public void setSweeperPower(double power) {
    m_sweeper.set(ControlMode.PercentOutput, power);
  }


}

