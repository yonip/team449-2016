package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeUp;

/**
 * <code>CommandGroup</code> running robot during the autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class AutoDriveIntakeUp extends Auto {
	public AutoDriveIntakeUp(double distance, double timeout) {
		super();
		addSequential(new DriveDistance(30, timeout));
		addSequential(new IntakeUp());
		addSequential(new DriveDistance(distance - 30, timeout));
	}
}
