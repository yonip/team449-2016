package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.TurnAngle;

/**
 * <code>CommandGroup</code> running robot during the autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class AutoDriveForwardBack extends Auto {
	public AutoDriveForwardBack(double distance) {
		super();
		addSequential(new AutoDrive(distance, 4));
		addSequential(new TurnAngle(180));
		addSequential(new AutoDrive(distance, 4));
		addSequential(new TurnAngle(0));
	}
}
