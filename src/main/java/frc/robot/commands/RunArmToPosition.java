/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CollectorSubsystem;



public class RunArmToPosition extends CommandBase {

  private CollectorSubsystem m_position;
  private double m_positionPower;
  private double m_counts;
  
  public RunArmToPosition(CollectorSubsystem position, double positionPower, int counts) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_position = position;
    m_positionPower = positionPower;
    m_counts = counts;

    addRequirements(m_position);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_position.setPositionPower(m_positionPower);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_position.setPositionPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_positionPower > 0) {
      if (m_position.getPositionEncoder() > m_counts) {
        return true;
      }
        else return false;
    }
    else {
      if (m_position.getPositionEncoder() < m_counts) {
        return true;
      }
        else return false;
    }
  }
}
