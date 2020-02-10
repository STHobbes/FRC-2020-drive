package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

  /**
   * The Singleton instance of this CollectorSubsystem. External classes should
   * use the {@link #getInstance()} method to get the instance.
   */
  private final static ArmSubsystem INSTANCE = new ArmSubsystem();
  private TalonSRX m_position = new TalonSRX(Constants.MotorControllers.COLLECTOR_POSITION);

  /**
   * Creates a new instance of this CollectorSubsystem.
   * This constructor is private since this class is a Singleton. External classes
   * should use the {@link #getInstance()} method to get the instance.
   */
  private ArmSubsystem() {
    // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
    //       in the constructor or in the robot coordination class, such as RobotContainer.
    //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
    //       such as SpeedControllers, Encoders, DigitalInputs, etc.
    m_position.configFactoryDefault();
    m_position.setNeutralMode(NeutralMode.Coast);
    m_position.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    m_position.setSelectedSensorPosition(0, 0, 10);
    m_position.setSensorPhase(true);

  }

  /**
   * Returns the Singleton instance of this CollectorSubsystem. This static method
   * should be used -- {@code CollectorSubsystem.getInstance();} -- by external
   * classes, rather than the constructor to get the instance of this class.
   */
  public static ArmSubsystem getInstance() {
    return INSTANCE;
  }

  public double getPosition() {
    return m_position.getSelectedSensorPosition();
  }

  public double getPositionPower() {
    return m_position.getMotorOutputPercent();
  }

  public void setPositionPower(double power) {
    if ((m_position.getSelectedSensorPosition() < 0.0) && (power < 0.0)) {
      power = 0;
    } else if ((m_position.getSelectedSensorPosition() > 43500.0) && (power > 0.0)) {
      power = .05;
    } else {
      power = .2 + power;
    }
    m_position.set(ControlMode.PercentOutput, power);
  }

}

