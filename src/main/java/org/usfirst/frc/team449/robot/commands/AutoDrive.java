package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * {@link CommandGroup} that inherits from {@link Auto} that drives the robot
 * forward during the autonomous period.
 */
public class AutoDrive extends Auto {
	/**
	 * Instantiates a new <code>AutoDrive</code>, driving a certain distance and
	 * timing out after some number of seconds.
	 * 
	 * @param distance
	 *            distance to drive before stopping
	 * @param timeout
	 *            how long to drive before aborting the {@link Command} for
	 *            safety reasons
	 */
	public AutoDrive(double distance, double timeout) {
		super();
		addSequential(new DriveDistance(distance, timeout));
	}
}
