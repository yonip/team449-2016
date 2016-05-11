package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.TurnAngle;

/**
 * <code>CommandGroup</code> that inherits from <code>Auto</code> that drives
 * the robot forward, turns around (180 degrees) and drives back during the
 * autonomous period.
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 * 
 */
public class AutoDriveForwardBack extends Auto {
	/**
	 * Instantiates a new <code>AutoDrive</code>, driving a certain distance,
	 * turn around, and drive back, timing out after some number of seconds.
	 * 
	 * @param distance
	 *            distance to drive before stopping
	 * @param timeout
	 *            how long to drive before aborting the <code>Command</code> for
	 *            safety reasons
	 */
	public AutoDriveForwardBack(double distance, double timeout) {
		super();
		addSequential(new AutoDrive(distance, timeout));
		addSequential(new TurnAngle(180));
		addSequential(new AutoDrive(distance, timeout));
		addSequential(new TurnAngle(0));
	}
}
