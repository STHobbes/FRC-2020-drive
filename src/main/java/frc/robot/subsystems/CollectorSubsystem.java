package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CollectorSubsystem extends SubsystemBase {

    private TalonSRX m_position = new TalonSRX(Constants.MotorControllers.COLLECTOR_POSITION);
    private double m_positionPower;
    private TalonSRX m_sweeper = new TalonSRX(Constants.MotorControllers.COLLECTOR_SWEEPER);
    private double m_sweeperPower;
    


    /**
     * The Singleton instance of this CollectorSubsystem. External classes should
     * use the {@link #getInstance()} method to get the instance.
     */
    private final static CollectorSubsystem INSTANCE = new CollectorSubsystem();

    /**
     * Returns the Singleton instance of this CollectorSubsystem. This static method
     * should be used -- {@code CollectorSubsystem.getInstance();} -- by external
     * classes, rather than the constructor to get the instance of this class.
     */
    public static CollectorSubsystem getInstance() {
        return INSTANCE;
    }


    /**
     * Creates a new instance of this CollectorSubsystem.
     * This constructor is private since this class is a Singleton. External classes
     * should use the {@link #getInstance()} method to get the instance.
     */
    private CollectorSubsystem() {
        m_sweeper.configFactoryDefault();
        m_sweeper.setNeutralMode(NeutralMode.Brake);
        m_position.configFactoryDefault();
        m_position.setNeutralMode(NeutralMode.Brake);
    }

    /**
     *
     * @param sweeperPower The power. in the range -1.0 to 1.0 that should be applied to the sweeper motor.
     */
    public void setSweeperPower(double sweeperPower) {
        m_sweeperPower = sweeperPower;
        m_sweeper.set(ControlMode.PercentOutput,sweeperPower);
    }

    public double getSweeperPower() {
        return m_sweeperPower;
    }

    /**
     *
     * @param positionPower The power. in the range -1.0 to 1.0 that should be applied to the arm position motor.
     */
    public void setPositionPower(double positionPower) {
        m_positionPower = positionPower;
        m_position.set(ControlMode.PercentOutput,positionPower);
    }

    public double getPositionPower() {
        return m_positionPower;
    }

    public void resetPositionEncoder() {
        m_position.setSelectedSensorPosition(0);
    }

    public double getPositionEncoder() {
        return m_position.getSelectedSensorPosition();
    }

}

