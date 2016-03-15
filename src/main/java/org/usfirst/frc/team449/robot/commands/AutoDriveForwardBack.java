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
	/**
	 * Creates a command to drive through a defense and back through, facing 
	 * the direction it will be driving in.
	 * @param distance the distance the robot will drive for crossing a defense
	 * @param timeout the maximum time each driving segment will take (to prevent rogue robot)
	 */
	public AutoDriveForwardBack(double distance, double timeout) {
		super();
		addSequential(new AutoDrive(distance, timeout));
		addSequential(new TurnAngle(180));
		addSequential(new AutoDrive(distance, timeout));
		addSequential(new TurnAngle(0));
	}
}
