/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SweeperSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private Limelight m_limelight;
//  private SendableChooser<Constants.Robots> robotChooser = new SendableChooser<>();

  /**
   * Display a double value on the smart dashboard telemetry panel.
   *
   * @param port The index where the string should appear in the dashboard.
   * @param key  The keyword(s) describing what the value is.
   * @param var  The value to be displayed.
   */
  @SuppressWarnings("unused")
  private void dashboardTelemetry(int port, String key, double var) {
    SmartDashboard.putString(String.format("DB/String %d", port), String.format("%s: %4.3f", key, var));
  }

  /**
   * Display a string value on the smart dashboard telemetry panel.
   *
   * @param port The index where the string should appear in the dashboard.
   * @param key  The keyword(s) describing what the value is.
   * @param var  The value to be displayed.
   */
  @SuppressWarnings("unused")
  private void dashboardTelemetry(int port, String key, String var) {
    SmartDashboard.putString(String.format("DB/String %d", port), String.format("%s: %s", key, var));
  }

  /**
   * Display a boolean value on the smart dashboard telemetry panel as either 'on' ({@code true}) or
   * 'off' ({@code false}).
   *
   * @param port The index where the string should appear in the dashboard.
   * @param key  The keyword(s) describing what the value is.
   * @param var  The value to be displayed.
   */
  @SuppressWarnings("unused")
  private void dashboardTelemetry(int port, String key, boolean var) {
    SmartDashboard.putString(String.format("DB/String %d", port),
        String.format("%s: %s", key, var ? "on" : "off"));
  }

  /**
   * This method displays the telemetry for the robot. It is run during both enabled and disabled modes, so it is very useful
   * if you want to get sensor feedback while the robot is disabled. Please put your driver team (and builder/programmer)
   * feedback printouts here so they are handled uniformly with all other driver team (and builder/programmer) feedback.
   */
  private void useTelemetry() {
    dashboardTelemetry(0, "robot", Constants.ROBOT.ROBOT_NAME);
    dashboardTelemetry(5, "driver", Constants.DRIVER.DRIVER_NAME);

    NavX.NavInfo navInfo = NavX.getInstance().getNavInfo();
    dashboardTelemetry(2, "pitch", navInfo.rawPitch);
    dashboardTelemetry(3, "yaw", navInfo.rawYaw);
    dashboardTelemetry(4, "roll", navInfo.rawRoll);

    NavX.HeadingInfo headinnInfo = NavX.getInstance().getHeadingInfo();
    dashboardTelemetry(8, "expected:", headinnInfo.expectedHeading);
    dashboardTelemetry(9, "actual", headinnInfo.heading);

  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    // empty the telemetry display
    for (int i = 0; i < 10; i++) {
      SmartDashboard.putString(String.format("DB/String %d", i), " ");
    }
    NavX.getInstance().initializeHeadingAndNav();

    m_limelight = m_robotContainer.getLimelight();
    m_limelight.setDriveCamera();

//    robotChooser.setDefaultOption(Constants.Robots.COMPETITION_ROBOT.ROBOT_NAME, Constants.Robots.COMPETITION_ROBOT);
//    robotChooser.addOption(Constants.Robots.PRACTICE_ROBOT.ROBOT_NAME, Constants.Robots.PRACTICE_ROBOT);
//    SmartDashboard.putData("Robot Selection", robotChooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    useTelemetry(); // output telemetry
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
//    Constants.ROBOT = robotChooser.getSelected();
//    m_robotContainer.resetRobot();
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
//    Constants.ROBOT = robotChooser.getSelected();
//    m_robotContainer.resetRobot();

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    NavX.getInstance().initializeHeadingAndNav();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    useTelemetry(); // output telemetry
  }

  @Override
  public void teleopInit() {
//    Constants.ROBOT = robotChooser.getSelected();
//    m_robotContainer.resetRobot();

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    NavX.getInstance().initializeHeadingAndNav();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
//    Constants.ROBOT = robotChooser.getSelected();
//    m_robotContainer.resetRobot();

    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
