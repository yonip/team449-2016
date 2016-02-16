package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultDrive extends Command {

	public DefaultDrive() {
		requires(Robot.drive);
		System.out.println("Drive Robot bueno");
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double leftThrottle = 0;
		double rightThrottle = 0;

		leftThrottle = Robot.oi.getDriveAxisLeft() * ((TankDriveMap) (Robot.drive.map)).SPEED;
		rightThrottle = Robot.oi.getDriveAxisRight() * ((TankDriveMap) (Robot.drive.map)).SPEED;

		if (Robot.oi.isDriveStraightMode()) {
			leftThrottle = rightThrottle;
		}
		// pushing forward on the stick gives -1 so it is negated
		((TankDriveSubsystem) Robot.drive).setThrottle(leftThrottle, rightThrottle);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		((TankDriveSubsystem) Robot.drive).setThrottle(Robot.oi.getDriveAxisLeft(), Robot.oi.getDriveAxisRight());
	}
}
