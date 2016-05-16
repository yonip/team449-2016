package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;

/**
 * Instantiates a new <code>AutoPortcullis</code>, putting down the wedge and
 * driving a certain distance and timing out after some number of seconds.
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class AutoPortcullis extends Auto {
	/**
	 * Instantiates a new <code>AutoPortcullis</code>, putting down wedge,
	 * driving a certain distance, and timing out after some number of seconds.
	 * 
	 * @param distance
	 *            distance to drive before stopping
	 * @param timeout
	 *            how long to drive before aborting the <code>Command</code> for
	 *            safety reasons
	 */
	public AutoPortcullis(double timeout) {
		super();
		addSequential(new BreachPortcullis());
		addSequential(new DriveDistance(190, timeout));
		addSequential(new BreachStowed());
	}
}
