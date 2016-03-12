package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <code>Command</code> for driving a certain distance (for use in auto period)
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class DriveDistance extends Command {

	private double distance;

	public DriveDistance(double distance) {
		requires(Robot.drive);
		this.distance = distance;
	}

	@Override
	protected void initialize() {
		System.out.println("DriveDistance init");
	}

	@Override
	protected void execute() {
		((TankDriveSubsystem) Robot.drive).driveDistance(distance);
	}

	@Override
	protected boolean isFinished() {
		return ((TankDriveSubsystem) Robot.drive).getDriveDistanceDone();
	}

	@Override
	protected void end() {
		System.out.println("DriveDistance end");
	}

	@Override
	protected void interrupted() {
		System.out.println("DriveDistance interupted");
	}
}
