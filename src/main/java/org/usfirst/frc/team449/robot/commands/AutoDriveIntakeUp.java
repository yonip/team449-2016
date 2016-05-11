package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeUp;

/**
 * <code>CommandGroup</code> that inherits from <code>Auto</code> that drives
 * the robot forward, raises intake, and continues driving during the autonomous
 * period.
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 * 
 */
public class AutoDriveIntakeUp extends Auto {
	/**
	 * Instantiates a new <code>AutoDrive</code>, driving a certain distance,
	 * raising intake, and driving distance - 30, timing out after some number
	 * of seconds.
	 * 
	 * @param distance
	 *            distance to drive before raising intake
	 * @param timeout
	 *            how long to drive before aborting the <code>Command</code> for
	 *            safety reasons
	 */
	public AutoDriveIntakeUp(double distance, double timeout) {
		super();
		addSequential(new DriveDistance(30, timeout));
		addSequential(new IntakeUp());
		addSequential(new DriveDistance(distance - 30, timeout));
	}
}
