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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.*;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SweeperSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ArmSubsystem m_armSubsystem = ArmSubsystem.getInstance();
  private final SweeperSubsystem m_sweeperSubsystem = SweeperSubsystem.getInstance();
  private final Limelight m_limelight = new Limelight();

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

  private final POVButton m_pov_up = new POVButton(this.m_stick, 0);
  private final POVButton m_pov_UR = new POVButton(this.m_stick, 45);
  private final POVButton m_pov_right = new POVButton(this.m_stick, 90);
  private final POVButton m_pov_DR = new POVButton(this.m_stick, 135);
  private final POVButton m_pov_down = new POVButton(this.m_stick, 180);
  private final POVButton m_pov_DL = new POVButton(this.m_stick, 225);
  private final POVButton m_pov_left = new POVButton(this.m_stick, 270);
  private final POVButton m_pov_UL = new POVButton(this.m_stick, 315);

  // - the xbox controller and buttons
  private final XboxController m_xbox = new XboxController(1);
  private final JoystickButton xboxA = new JoystickButton(m_xbox, 1);
  private final JoystickButton xboxB = new JoystickButton(m_xbox, 2);
  private final JoystickButton xboxX = new JoystickButton(m_xbox, 3);
  private final JoystickButton xboxY = new JoystickButton(m_xbox, 4);

  // The robot's commands
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_stick);
  private final RunSweeper m_runSweeper = new RunSweeper(m_sweeperSubsystem, m_stick);
  private final ManualCollector m_manualCollector = new ManualCollector(m_armSubsystem, m_xbox);
  private final SetDriveCamera m_setDriveCamera = new SetDriveCamera(m_limelight);
  private final SetVisionCamera m_setVisionCamera = new SetVisionCamera(m_limelight);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // perform robot and driver initializations
    m_driveSubsystem.setRobot();
    // Set the default commands for subsystems
    m_driveSubsystem.setDefaultCommand(m_driveCommand);
    m_sweeperSubsystem.setDefaultCommand(m_runSweeper);
    m_armSubsystem.setDefaultCommand(m_manualCollector);
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
//    xboxA.whenPressed(new SetNextRobot(this));
    xboxB.whenPressed(new SetNextDriver(this));

    // Use stick twist or left-right for turn
    m_trigger.whenPressed(new ToggleUseTwist());

    // setting gain - these are all on the hat.
    m_pov_up.whileHeld(new BumpSpeedGain(0.01));
    m_pov_down.whileHeld(new BumpSpeedGain(-0.01));
    m_pov_right.whileHeld(new BumpTurnGain(0.01));
    m_pov_left.whileHeld(new BumpTurnGain(-0.01));
    m_pov_UR.whileHeld(new BumpTurnAtSpeedGain(0.01));
    m_pov_UL.whileHeld(new BumpTurnAtSpeedGain(0.01));
    m_pov_DR.whileHeld(new BumpTurnAtSpeedGain(-0.01));
    m_pov_DL.whileHeld(new BumpTurnAtSpeedGain(-0.01));

    // setting sensitivity - there are on the upper stick buttons
    m_topUL.whileHeld(new BumpSpeedSensitivity(0.05));
    m_topLL.whileHeld(new BumpSpeedSensitivity(-0.05));
    m_topUR.whileHeld(new BumpTurnSensitivity(0.05));
    m_topLR.whileHeld(new BumpTurnSensitivity(-0.05));

    // Other stuff - deadband
    m_button11.whileHeld(new BumpSpeedDeadband(-0.005));
    m_button12.whileHeld(new BumpSpeedDeadband(0.005));
    m_button9.whileHeld(new BumpTurnDeadband(-0.005));
    m_button10.whileHeld(new BumpTurnDeadband(0.005));
  }

  public void resetRobot() {
    m_driveSubsystem.setRobot();
  }

  public void resetDriver() {

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

  public Limelight getLimelight() {
    return m_limelight;
  }
}
