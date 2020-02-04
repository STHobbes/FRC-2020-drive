/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BigWheelToColor;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.SetDriveCamera;
import frc.robot.commands.SetVisionCamera;
import frc.robot.commands.bigWheelToPosition;
import frc.robot.commands.resetBigWheelEncoders;
import frc.robot.commands.setBigWheelPower;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.controlPanelBigWheel;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import frc.robot.Constants.ColorTargets;



/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Buttons
  private final Joystick m_stick = new Joystick(0);

  private final JoystickButton m_trigger = new JoystickButton(this.m_stick, 1);
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

  // Subsystems
  private final Limelight m_limelight = new Limelight();
  private final controlPanelBigWheel m_wheel = new controlPanelBigWheel();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // Commands
  private final SetDriveCamera m_setDriveCamera = new SetDriveCamera(m_limelight);
  private final SetVisionCamera m_setVisionCamera = new SetVisionCamera(m_limelight);
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_stick);

  // Color sensor
  private final static I2C.Port i2cPort = I2C.Port.kOnboard;
  private final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final static ColorMatch m_colorMatcher = new ColorMatch();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // perform robot and driver initializations
    m_driveSubsystem.setRobot();
    // Set the default commands for subsystems
    m_driveSubsystem.setDefaultCommand(m_driveCommand);
    // Configure the button bindings
    configureButtonBindings();

    // color sensor stuff
    m_colorMatcher.addColorMatch(ColorTargets.BLUE_TARGET);
    m_colorMatcher.addColorMatch(ColorTargets.GREEN_TARGET);
    m_colorMatcher.addColorMatch(ColorTargets.RED_TARGET);
    m_colorMatcher.addColorMatch(ColorTargets.YELLOW_TARGET);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*
     * Button bindings for changing the camera mode
     * m_button3.whenPressed(m_setDriveCamera);
     * m_button5.whenPressed(m_setVisionCamera);
     */
    m_trigger.whenHeld(new setBigWheelPower(m_wheel, 1));
    m_sideButton.whenPressed(new bigWheelToPosition(m_wheel, 1, -18000));
    m_button3.whenPressed(new resetBigWheelEncoders(m_wheel));
    m_button9.whenPressed(new BigWheelToColor(m_wheel, 0.5, "Blue"));
    m_button10.whenPressed(new BigWheelToColor(m_wheel, 0.5, "Green"));
    m_button11.whenPressed(new BigWheelToColor(m_wheel, 0.5, "Yellow"));
    m_button12.whenPressed(new BigWheelToColor(m_wheel, 0.5, "Red"));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public Limelight getLimelight() {
    return m_limelight;
  }

  public controlPanelBigWheel getBigWheel() {
    return m_wheel;
  }

  public ColorSensorV3 getColorSensor() {
    return m_colorSensor;
  }

  // probably not the right way to do this
  public static String getColorAsString() {

    // color object with values
    Color detectedColor = m_colorSensor.getColor();

    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == ColorTargets.BLUE_TARGET) {
      colorString = "Blue";
    } else if (match.color == ColorTargets.RED_TARGET) {
      colorString = "Red";
    } else if (match.color == ColorTargets.GREEN_TARGET) {
      colorString = "Green";
    } else if (match.color == ColorTargets.YELLOW_TARGET) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    return colorString;
  }
}
