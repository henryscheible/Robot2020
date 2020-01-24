/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.IOException;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.TankDrive;

public class DriveMotionProfile extends Command {

  String name;
  TankDrive drivetrain;
  Boolean done;

  public DriveMotionProfile(String name, TankDrive drivetrain){
    done = false;
    this.name = name;
    this.drivetrain = drivetrain;
    requires(drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize(){
    try{
      drivetrain.loadMotionProfile(name);
    } catch (IOException e) {
      done = true;
    }
    drivetrain.startMotionProfile(name);
  }

  @Override
  protected void execute() {
    System.out.println("execute() on DriveMotionProfile");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return drivetrain.isMotionProfileFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("end() on DriveMotionProfile");
    drivetrain.cancelMotionProfile();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
