/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.IOException;
import java.util.HashMap;

import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.TankDrive;
import com.ctre.phoenix.motion.MotionProfileStatus;

/**
 * Command to drive a motion profile using the MPE on the Talons in the Drivetrain.
 */
public class DriveMotionProfile extends Command {

  String name;
  TankDrive drivetrain;
  Boolean done;
  Boolean isInverted;
  MotorSafety motorSafetyInst;

  /**
   * 
   * @param name Name of the Motion Profile created in PathWeaver
   * @param drivetrain TankDrive subsystem object
   */
  public DriveMotionProfile(String name, TankDrive drivetrain, boolean isInverted){
    done = false;
    this.name = name;
    this.drivetrain = drivetrain;
    this.isInverted = isInverted;
    requires(drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize(){
    drivetrain.clearOldMotionProfiles();
    drivetrain.zeroEncoders();
    try{
      drivetrain.loadMotionProfile(name, isInverted);
    } catch (IOException e) {
      done = true;
    }
    drivetrain.startMotionProfile(name);
  }

  @Override
  protected void execute() {
    drivetrain.feedMotorSafety();
    HashMap<String, MotionProfileStatus> statuses = drivetrain.getMotionProfileStatuses();
    
    // SmartDashboard.putNumber("LeftTopBufferCnt", statuses.get("Left").topBufferCnt);
    // SmartDashboard.putNumber("LeftTopBufferRem", statuses.get("Left").topBufferRem);
    // SmartDashboard.putNumber("RightTopBufferCnt", statuses.get("Right").topBufferCnt);
    // SmartDashboard.putNumber("RightTopBufferRem", statuses.get("Right").topBufferRem);
    // SmartDashboard.putBoolean("RightHasUnderrun", statuses.get("Right").hasUnderrun);
    // SmartDashboard.putBoolean("LeftHasUnderrun", statuses.get("Left").hasUnderrun);
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
    drivetrain.clearOldMotionProfiles();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drivetrain.cancelMotionProfile();
    drivetrain.clearOldMotionProfiles();
  }
}
