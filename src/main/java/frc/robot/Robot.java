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

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private void dashboardTelemetry(int port, String key, double var) {
    SmartDashboard.putString(String.format("DB/String %d", port), String.format("%s: %4.3f", key, var));
  }
  private void dashboardTelemetry(int port, String key, String var) {
    SmartDashboard.putString(String.format("DB/String %d", port), String.format("%s: %s", key, var));
  }
  private void dashboardTelemetry(int port, String key, boolean var) {
    SmartDashboard.putString(String.format("DB/String %d", port),
            String.format("%s: %s", key, var ? "on" : "off" ));
  }

  void displayDriveParameters() {
    dashboardTelemetry(0, "robot", Constants.ROBOT.ROBOT_NAME);
    dashboardTelemetry(5, "twist", Constants.DRIVE_USE_TWIST);

    dashboardTelemetry(1, "speed gain", Constants.DRIVE_SPEED_GAIN);
    dashboardTelemetry(6, "speed senstvty", Constants.DRIVE_SPEED_SENSITIVITY);
    dashboardTelemetry(7, "speed deadband", Constants.DRIVE_SPEED_DEADBAND);

    dashboardTelemetry(3, "turn gain", Constants.DRIVE_TURN_GAIN);
    dashboardTelemetry(8, "turn senstvty", Constants.DRIVE_TURN_SENSITIVITY);
    dashboardTelemetry(4, "turn@speed gain", Constants.DRIVE_TURN_AT_SPEED_GAIN);
    dashboardTelemetry(9, "turn deadband", Constants.DRIVE_TURN_DEADBAND);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    // empty the telemetry display
    for (int i = 0; i < 10; i++) {
      SmartDashboard.putString(String.format("DB/String %d",i), " ");
    }
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
    displayDriveParameters();
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    displayDriveParameters();
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
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
