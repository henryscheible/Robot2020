/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.motion;

/**
 * Add your docs here.
 */
public class CompositeMovement extends Movement {

    public void addMovement(Movement movement) {
        this.leftSpeed += movement.leftSpeed;
        this.rightSpeed += movement.rightSpeed;
    }

}
