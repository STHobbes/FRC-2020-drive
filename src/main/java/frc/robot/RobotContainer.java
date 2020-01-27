/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // The driver station buttons
  // - the joystick and buttons
  private final Joystick m_stick = new Joystick(0);

  private final JoystickButton m_trigger = new JoystickButton(this.m_stick, 1);
  private final JoystickButton m_thumb = new JoystickButton(this.m_stick, 2);
  private final JoystickButton m_topLL = new JoystickButton(this.m_stick, 3);
  private final JoystickButton m_topLR = new JoystickButton(this.m_stick, 4);
  private final JoystickButton m_topUL = new JoystickButton(this.m_stick, 5);
  private final JoystickButton m_topUR = new JoystickButton(this.m_stick, 6);
  private final JoystickButton m_button7 = new JoystickButton(this.m_stick, 7);
  private final JoystickButton m_button8 = new JoystickButton(this.m_stick, 8);
  private final JoystickButton m_button9 = new JoystickButton(this.m_stick, 9);
  private final JoystickButton m_button10 = new JoystickButton(this.m_stick, 10);
  private final JoystickButton m_button11 = new JoystickButton(this.m_stick, 11);
  private final JoystickButton m_button12 = new JoystickButton(this.m_stick, 12);

  private final POVButton m_pov_0 = new POVButton(this.m_stick, 0);
  private final POVButton m_pov_180 = new POVButton(this.m_stick, 180);

  // - the xbox controller and buttons
  private final XboxController m_xbox = new XboxController(1);
  private final JoystickButton xboxY = new JoystickButton(m_xbox, 1);
  private final JoystickButton xboxA = new JoystickButton(m_xbox, 2);
  private final JoystickButton xboxB = new JoystickButton(m_xbox, 3);
  private final JoystickButton xboxX = new JoystickButton(m_xbox, 4);

  // The robot's commands
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_stick);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // perform robot and driver initializations
    m_driveSubsystem.setRobot();
    // Set the default commands for subsystems
    m_driveSubsystem.setDefaultCommand(m_driveCommand);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    xboxA.whenPressed(new SetNextRobot(this));

    m_topUL.whileHeld(new BumpDriveGain(0.01));
    m_topLL.whileHeld(new BumpDriveGain(-0.01));
    m_topUR.whileHeld(new BumpTurnGain(0.01));
    m_topLR.whileHeld(new BumpTurnGain(-0.01));

    m_button7.whileHeld(new BumpTurnAtSpeedGain(-0.01));
    m_button8.whileHeld(new BumpTurnAtSpeedGain(0.01));
    m_button9.whileHeld(new BumpSensitivity(-0.05));
    m_button10.whileHeld(new BumpSensitivity(0.05));
    m_button11.whileHeld(new BumpDeadband(-0.005));
    m_button12.whileHeld(new BumpDeadband(0.005));

    m_pov_0.whileHeld(new BumpDeadband(0.005));
    m_pov_180.whileHeld(new BumpDeadband(-0.005));


  }

  public void resetRobot() {
    m_driveSubsystem.setRobot();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }

  public DriveSubsystem getDrive() {
    return m_driveSubsystem;
  }
}
