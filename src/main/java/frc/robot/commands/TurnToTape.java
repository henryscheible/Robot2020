/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.LauncherAimingSubsystem;
import frc.robot.subsystems.TankDrive;
import frc.robot.components.Limelight;

public class TurnToTape extends Command {

  TankDrive drivetrain;
  LauncherAimingSubsystem subsystem;

  public TurnToTape(LauncherAimingSubsystem subsystem, TankDrive drivetrain) {
    // Use requires() here to declare subsystem dependencies
    requires(subsystem);
    requires(drivetrain);
    this.subsystem = subsystem;
    this.drivetrain = drivetrain;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute(){
    ((Limelight)subsystem.getSource()).setPipeline(0);
    drivetrain.driveMovement(subsystem.getDrivetrainAction());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // System.out.println("isFinished(); on exampleCommand")
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
