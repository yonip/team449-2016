package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * {@link CommandGroup} that inherits from {@link Auto} that puts down the wedge
 * and drives a certain distance and times out after some number of seconds.
 */
public class AutoPortcullis extends Auto {
	/**
	 * Instantiates a new <code>AutoPortcullis</code>, putting down wedge,
	 * driving a certain distance, and timing out after some number of seconds.
	 * 
	 * @param timeout
	 *            how long to drive before aborting the <code>Command</code> for
	 *            safety reasons
	 */
	public AutoPortcullis(double timeout) {
		super();
		addSequential(new BreachPortcullis());
		// TODO externalize distance
		addSequential(new DriveDistance(190, timeout));
		addSequential(new BreachStowed());
	}
}
