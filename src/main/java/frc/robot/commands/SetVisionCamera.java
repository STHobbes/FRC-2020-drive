/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

public class SetVisionCamera extends CommandBase {

  private final Limelight m_limelight;

  /**
   * Creates a new SetVisionCamera.
   */
  public SetVisionCamera(Limelight limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelight = limelight;
    addRequirements(m_limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_limelight.setVisionCamera();
  }

}
