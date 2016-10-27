package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeUp;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * {@link CommandGroup} that inherits from {@link Auto} that drives the robot
 * forward, raises intake, and continues driving during the autonomous period.
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
	 *            how long to drive before aborting the {@link Command} for
	 *            safety reasons
	 */
	public AutoDriveIntakeUp(double distance, double timeout) {
		super();
		// TODO externalize constants
		addSequential(new DriveDistance(30, timeout));
		addSequential(new IntakeUp());
		addSequential(new DriveDistance(distance - 30, timeout));
	}
}
