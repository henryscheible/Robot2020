/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.StopLauncher;
import frc.robot.components.*;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */

public class Launcher extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DifferentialDrive diffDrive;
  XboxController controller;
  Talon left;
  Talon right;

  public Launcher(Talon left, Talon right, XboxController controller) {
    this.controller = controller;
    this.left = left;
    this.right = right;
    right.configureLauncherFalcon();
    left.follow(right);
    left.setInverted(true);
    SmartDashboard.putNumber("LauncherWheelsSetSpeed", 5000);
  }

  public void spinWheels(double speed) {
    right.setClosedLoopVelocity(speed);
  }

  public int getSpeed() {
    return right.getEncoderSpeed();
  }

  public void setZero() {
    right.set(0);
  }

  public void getCalculatedSpeed() {

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand();
    setDefaultCommand(new StopLauncher(Robot.launcher));
  }

}
