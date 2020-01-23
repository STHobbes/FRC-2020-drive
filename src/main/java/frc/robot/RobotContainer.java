/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.SetDriveCamera;
import frc.robot.commands.SetVisionCamera;
import frc.robot.subsystems.Limelight;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final Limelight m_limelight = new Limelight();

  // The driver station buttons
  // - the joystick and buttons
  private final Joystick m_stick = new Joystick(0);

  private final JoystickButton m_sideButton = new JoystickButton(this.m_stick, 2);
  private final JoystickButton m_button3 = new JoystickButton(this.m_stick, 3);
  private final JoystickButton m_button4 = new JoystickButton(this.m_stick, 4);
  private final JoystickButton m_button5 = new JoystickButton(this.m_stick, 5);
  private final JoystickButton m_button6 = new JoystickButton(this.m_stick, 6);
  private final JoystickButton m_button7 = new JoystickButton(this.m_stick, 7);
  private final JoystickButton m_button8 = new JoystickButton(this.m_stick, 8);
  private final JoystickButton m_button9 = new JoystickButton(this.m_stick, 9);
  private final JoystickButton m_button10 = new JoystickButton(this.m_stick, 10);
  private final JoystickButton m_button11 = new JoystickButton(this.m_stick, 11);
  private final JoystickButton m_button12 = new JoystickButton(this.m_stick, 12);

  // The robot's commands
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_stick);

  // Commands
  private final SetDriveCamera m_setDriveCamera = new SetDriveCamera(m_limelight);
  private final SetVisionCamera m_setVisionCamera = new SetVisionCamera(m_limelight);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Set the default commands for subsystems
    m_driveSubsystem.setDefaultCommand(m_driveCommand);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick}, and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }

  public Limelight getLimelight() {
    return m_limelight;
  }
}
