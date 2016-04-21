package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;

/**
 * <code>CommandGroup</code> running robot during the autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class AutoDrive extends Auto {
	public AutoDrive(double distance, double timeout) {
		super();
		addSequential(new DriveDistance(distance, timeout));
	}
}
