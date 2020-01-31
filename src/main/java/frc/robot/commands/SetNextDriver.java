/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class SetNextDriver extends CommandBase {

  private RobotContainer m_container;

  /**
   * Creates a new SetNextDriver command. This command steps through the driver configurations in {@link Constants.Drivers} with
   * wrap-around so a driver can select thier ideal driving profile when they are driving.
   *
   * @param container The container for all the robot subsystems, command, sensors, and driver controls.
   */
  public SetNextDriver(RobotContainer container) {
    m_container = container;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Constants.DRIVER = Constants.Drivers.getNextDriver(Constants.DRIVER);
    m_container.resetDriver();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
